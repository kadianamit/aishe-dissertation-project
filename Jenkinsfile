pipeline {
  agent any

  environment {
    PATH = "/usr/local/bin:/opt/homebrew/bin:/usr/bin:/bin:/usr/sbin:/sbin:${env.PATH}"
    // Sonar token stored in Jenkins credentials (type: Secret text, id: sonarqube-token1)
    SONAR_AUTH_TOKEN = credentials('aishetoken')
    SONAR_HOST_URL   = 'http://host.docker.internal:9001'   // rely on add-host for container reachability
    // Use the Jenkins workspace absolute path so containers see the same paths as the host
    WORKSPACE_DIR = "${env.WORKSPACE}"
    // local maven repo inside workspace to reduce permission/lock issues
    MAVEN_REPO = "${env.WORKSPACE}/.m2/repository"
    ENABLE_SONAR = 'true' // Added: Enable SonarQube analysis
  }

  stages {

    stage('Check Docker') {
      steps {
        sh 'which docker && docker version'
      }
    }

    stage('Check prerequisites') {
      steps {
        sh '''
          echo "---- PATH ----"
          echo "$PATH"
          echo "---- WORKSPACE ----"
          echo "${WORKSPACE_DIR}"
          which docker || (echo "docker not found in PATH" && exit 1)
        '''
        sh "mkdir -p ${MAVEN_REPO}"
      }
    }

    stage('Build All') {
      parallel {

        stage('Build AisheMasterService') {
          steps {
            dir("${WORKSPACE_DIR}/aishe_backend/AisheMasterService") {
              sh """
                docker run --rm \
                  --dns 8.8.8.8 \
                  --add-host=host.docker.internal:host-gateway \
                  --ulimit nofile=65536:65536 \
                  -v "${WORKSPACE_DIR}:${WORKSPACE_DIR}" \
                  -v "${MAVEN_REPO}:/root/.m2/repository" \
                  -w "${WORKSPACE_DIR}/aishe_backend/AisheMasterService" \
                  maven:3.8.6-jdk-11 \
                  mvn -B -Dmaven.repo.local=/root/.m2/repository -DskipTests -e clean package
              """
            }
          }
          post {
            success { archiveArtifacts artifacts: 'aishe_backend/AisheMasterService/target/*.jar', followSymlinks: false }
          }
        }

        stage('Build UserMgtService') {
          steps {
            dir("${WORKSPACE_DIR}/aishe_backend/UserMgtService") {
              sh '''
                docker run --rm --dns 8.8.8.8 --add-host=host.docker.internal:host-gateway \
                  --ulimit nofile=131072:131072 \
                  -v ${WORKSPACE}:/workspace \
                  -v ${WORKSPACE}/.m2/repository:/root/.m2/repository \
                  -w /workspace/aishe_backend/UserMgtService \
                  -e MAVEN_OPTS="-Xms256m -Xmx1024m" \
                  maven:3.8.6-jdk-11 /bin/sh -lc 'ulimit -n 131072 && mvn -B -Dmaven.repo.local=/root/.m2/repository -DskipTests package'
              '''
            }
          }
          post {
            success { archiveArtifacts artifacts: 'aishe_backend/UserMgtService/target/*.jar', followSymlinks: false }
          }
        }

        stage('Build Frontend') {
          steps {
            dir("${WORKSPACE_DIR}/aishe_frontend") {
              // use a stable node image; use local tmp for npm cache to avoid macOS ENFILE/permission issues
              sh '''
                docker run --rm \
                  --dns 8.8.8.8 \
                  --add-host=host.docker.internal:host-gateway \
                  --ulimit nofile=131072:131072 \
                  -v "${WORKSPACE_DIR}:${WORKSPACE_DIR}" \
                  -v "${WORKSPACE_DIR}/.npm-cache":/root/.npm \
                  -w "${WORKSPACE_DIR}/aishe_frontend" \
                  node:18 \
                  /bin/sh -c "for i in 1 2 3; do npm install --no-audit --legacy-peer-deps && npm run build && break || sleep 15; done"
              '''
            }
          }
          post {
            success { archiveArtifacts artifacts: 'aishe_frontend/dist/**', followSymlinks: false }
          }
        }
      } // parallel
    } // Build All

    // New SonarQube Analysis stage
    stage('SonarQube Analysis') {
      when {
        allOf {
          expression { env.ENABLE_SONAR == 'true' }
          expression { currentBuild.currentResult == 'SUCCESS' }
        }
      }
      steps {
        script {
          withSonarQubeEnv('SonarLocal') {
            // Backend (multi-module) - run sonar from root of backend so maven picks up modules
            dir("${WORKSPACE_DIR}/aishe_backend/AisheMasterService") {
              sh """
                docker run --rm --dns 8.8.8.8 --add-host=host.docker.internal:host-gateway \
                --ulimit nofile=65536:65536 \
                -v ${WORKSPACE}:/workspace \
                -v ${WORKSPACE}/.m2/repository:/root/.m2/repository \
                -w /workspace/aishe_backend/AisheMasterService \
                -e SONAR_HOST_URL=${SONAR_HOST_URL} \
                -e SONAR_AUTH_TOKEN=${SONAR_AUTH_TOKEN} \
                maven:3.8.6-jdk-11 mvn -B -Dmaven.repo.local=/root/.m2/repository -DskipTests \
                org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                -Dsonar.host.url=${SONAR_HOST_URL} \
                -Dsonar.login=${SONAR_AUTH_TOKEN}
              """
            }
            dir("${WORKSPACE_DIR}/aishe_backend/UserMgtService") {
              sh """
                docker run --rm --dns 8.8.8.8 --add-host=host.docker.internal:host-gateway \
                --ulimit nofile=65536:65536 \
                -v ${WORKSPACE}:/workspace \
                -v ${WORKSPACE}/.m2/repository:/root/.m2/repository \
                -w /workspace/aishe_backend/UserMgtService \
                -e SONAR_HOST_URL=${SONAR_HOST_URL} \
                -e SONAR_AUTH_TOKEN=${SONAR_AUTH_TOKEN} \
                maven:3.8.6-jdk-11 mvn -B -Dmaven.repo.local=/root/.m2/repository -DskipTests \
                org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                -Dsonar.host.url=${SONAR_HOST_URL} \
                -Dsonar.login=${SONAR_AUTH_TOKEN}
              """
            }

            // Frontend - use sonar-scanner-cli container and point to frontend sources
            dir("${WORKSPACE_DIR}/aishe_frontend") {
              			  sh """
              				docker run --rm --ulimit nofile=262144:262144 \
              				  -e SONAR_HOST_URL=$SONAR_HOST_URL -e SONAR_AUTH_TOKEN=$SONAR_AUTH_TOKEN \
              				  -v ${WORKSPACE}:/workspace -w /workspace/aishe_frontend \
              				  sonarsource/sonar-scanner-cli:latest \
              				  -Dsonar.projectKey=aishe-frontend \
              				  -Dsonar.projectBaseDir=/workspace/aishe_frontend \
              				  -Dsonar.sources=src \
              				  -Dsonar.inclusions=src/**/*.ts,src/**/*.html,src/**/*.css \
              				  -Dsonar.exclusions=**/node_modules/**,**/dist/**,**/*.spec.ts,**/*.min.js,**/*.map,**/coverage/** \
              				  -Dsonar.scm.disabled=true \
              				  -Dsonar.host.url=$SONAR_HOST_URL \
              				  -Dsonar.login=$SONAR_AUTH_TOKEN
              			  """
              			}
              		  }
              		}
              	  }
              	}
              	stage('Build Docker Images') {
              	  parallel {
              		stage('Build AisheMasterService Image') {
              		  steps {
              			dir("aishe_backend/AisheMasterService") {
              			  sh 'docker build -t aishe-master-service:latest .'
              			}
              		  }
              		}
              		stage('Build UserMgtService Image') {
              		  steps {
              			dir("aishe_backend/UserMgtService") {
              			  sh 'docker build -t user-mgt-service:latest .'
              			}
              		  }
              		}
              		stage('Build Frontend Image') {
              		  steps {
              			dir("aishe_frontend") {
              			  sh 'docker build -t aishe-frontend:latest .'
              			}
              		  }
              		}
              	  }
              	}
              
              	stage('Deploy to Kubernetes') {
              	  steps {
              		dir("aishe_backend/k8s") {
              		  sh 'ls -la master-deployment.yaml'
              		  sh 'ls -la master-service.yaml'
              		  sh 'ls -la user-mgt-deployment.yaml'
              		  sh 'ls -la user-mgt-service.yaml'
              		  sh 'kubectl apply -f master-deployment.yaml'
              		  sh 'kubectl apply -f master-service.yaml'
              		  sh 'kubectl apply -f user-mgt-deployment.yaml'
              		  sh 'kubectl apply -f user-mgt-service.yaml'
              		}
              		dir("aishe_frontend/k8s") {
              		  sh 'ls -la frontend-deployment.yaml'
              		  sh 'ls -la frontend-service.yaml'
              		  sh 'kubectl apply -f frontend-deployment.yaml'
              		  sh 'kubectl apply -f frontend-service.yaml'
              		}
              	  }
              	}
                }
              
                post {
              	always {
              	  echo "Pipeline finished"
              	  // Wrap waitForQualityGate in a try-catch block
              	  script {
              		if (env.ENABLE_SONAR == 'true' && currentBuild.currentResult == 'SUCCESS') {
              		  try {
              			timeout(time: 1, unit: 'HOURS') { // Adjust timeout as needed
              			  waitForQualityGate abortPipeline: false
              			}
              		  } catch (Exception e) {
              			echo "SonarQube Quality Gate check failed or timed out: ${e.getMessage()}"
              			// Optionally, mark the build as UNSTABLE here if the quality gate fails
              			// currentBuild.result = 'UNSTABLE'
              		  }
              		}
              	  }
              	}
              	success {
              	  echo "Success!"
              	}
              	failure {
              	  echo "One or more stages failed - inspect console log."
              	}
                }
              }