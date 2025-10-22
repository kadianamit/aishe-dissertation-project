pipeline {
  agent none

  stages {
    stage('Build All') {
      parallel {
        stage('Build AisheMasterService') {
          agent {
            docker { image 'maven:3.8.6-jdk-11' }
          }
          steps {
            dir('aishe_backend/AisheMasterService') {
              sh 'mvn -DskipTests -e clean package'
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'aishe_backend/AisheMasterService/target/*.jar', followSymlinks: false
            }
          }
        }

        stage('Build UserMgtService') {
          agent {
            docker { image 'maven:3.8.6-jdk-11' }
          }
          steps {
            dir('aishe_backend/UserMgtService') {
              sh 'mvn -DskipTests -e clean package'
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'aishe_backend/UserMgtService/target/*.jar', followSymlinks: false
            }
          }
        }

        stage('Build Frontend') {
          agent {
            docker { image 'node:18' args '--ulimit nofile=65536:65536' }
          }
          steps {
            dir('aishe_frontend') {
              // Use workspace-local npm cache and reasonable Node memory
              sh '''
                ulimit -n 65536
                export NODE_OPTIONS="--max-old-space-size=4096"
                mkdir -p "$WORKSPACE/.npm"
                npm_config_cache="$WORKSPACE/.npm" npm ci --legacy-peer-deps
                npm_config_cache="$WORKSPACE/.npm" npm run build
              '''
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'aishe_frontend/dist/**', followSymlinks: false
            }
          }
        }
      } // end parallel
    } // end Build All

    stage('SonarQube Analysis') {
      agent any
      steps {
        // Make sure this name exactly matches your Sonar config in Jenkins
        withSonarQubeEnv('SonarQube-Local') {
          script {
            // Backend (run mvn from the backend folder so POM is found)
            dir('aishe_backend') {
              sh '''
                echo "Running Sonar for backend in: $PWD"
                mvn -DskipTests -e sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN -Dsonar.host.url=$SONAR_HOST_URL
              '''
            }

            // Frontend: use sonar-scanner CLI in Docker and mount frontend workspace
            sh '''
              echo "Running Sonar for frontend using sonar-scanner docker image"
              docker run --rm \
                -v "$WORKSPACE/aishe_frontend":/usr/src/app \
                -w /usr/src/app \
                sonarsource/sonar-scanner-cli \
                -Dsonar.projectKey=aishe-frontend \
                -Dsonar.sources=. \
                -Dsonar.login=$SONAR_AUTH_TOKEN \
                -Dsonar.host.url=$SONAR_HOST_URL
            '''
          } // end script
        } // end withSonarQubeEnv
      } // end steps
      post {
        always {
          sh 'test -f ${WORKSPACE}/report-task.txt && echo "report-task.txt exists" || echo "report-task.txt NOT found"'
        }
      }
    } // end SonarQube Analysis
  } // end stages
} // end pipeline