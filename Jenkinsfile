pipeline {
  agent any

  environment {
    // Jenkins credential (secret text) id must match whatever you created
    SONAR_AUTH_TOKEN = credentials('sonarqube-token1')
    // Containers will resolve this to the Jenkins host when --add-host is used
    SONAR_HOST_URL   = 'http://host.docker.internal:9001'
    // Use WORKSPACE_DIR so docker can mount the workspace path reliably
    WORKSPACE_DIR    = "${env.WORKSPACE}"
    // Optional: ensure PATH contains common locations (keeps logs consistent)
    PATH             = "/usr/local/bin:/opt/homebrew/bin:/usr/bin:/bin:/usr/sbin:/sbin"
  }

  stages {

    stage('Declarative: Checkout SCM') {
      steps {
        checkout scm
      }
    }

    stage('Tool check') {
      steps {
        script {
          sh '''
            echo "---- PATH ----"
            echo $PATH
            echo "---- Docker ----"
            which docker || true
            docker --version || true
            echo "---- Maven ----"
            which mvn || true
            mvn -v || true
          '''
        }
      }
    }

    stage('Build All') {
      parallel {
        stage('Build AisheMasterService') {
          steps {
            dir('aishe_backend/AisheMasterService') {
              sh """
                docker run --rm --dns 8.8.8.8 --add-host=host.docker.internal:host-gateway \
                  -v "${WORKSPACE_DIR}/aishe_backend/AisheMasterService":/work \
                  -w /work \
                  maven:3.8.6-jdk-11 \
                  mvn -f pom.xml -DskipTests -e clean package
              """
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'aishe_backend/AisheMasterService/target/*.jar', followSymlinks: false
            }
          }
        } // Build AisheMasterService

        stage('Build UserMgtService') {
          steps {
            dir('aishe_backend/UserMgtService') {
              sh """
                docker run --rm --dns 8.8.8.8 --add-host=host.docker.internal:host-gateway \
                  -v "${WORKSPACE_DIR}/aishe_backend/UserMgtService":/work \
                  -w /work \
                  maven:3.8.6-jdk-11 \
                  mvn -f pom.xml -DskipTests -e clean package
              """
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'aishe_backend/UserMgtService/target/*.jar', followSymlinks: false
            }
          }
        } // Build UserMgtService

        stage('Build Frontend') {
          steps {
            dir('aishe_frontend') {
              sh '''
                docker run --rm --dns 8.8.8.8 --add-host=host.docker.internal:host-gateway \
                  -v "${WORKSPACE_DIR}/aishe_frontend":/usr/src/app \
                  -w /usr/src/app \
                  node:18 \
                  /bin/sh -c "ulimit -n 65536 && export NODE_OPTIONS=--max-old-space-size=4096 && mkdir -p /tmp/.npm && npm_config_cache=/tmp/.npm npm ci --legacy-peer-deps && npm_config_cache=/tmp/.npm npm run build"
              '''
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'aishe_frontend/dist/**', followSymlinks: false
            }
          }
        } // Build Frontend

      } // parallel
    } // Build All

    stage('SonarQube Analysis') {
      steps {
        // Backend modules: run sonar per-module (adjust projectKey if you want other names)
        dir('aishe_backend/AisheMasterService') {
          sh """
            docker run --rm --dns 8.8.8.8 --add-host=host.docker.internal:host-gateway \
              -v "${WORKSPACE_DIR}/aishe_backend/AisheMasterService":/work \
              -w /work \
              maven:3.8.6-jdk-11 \
              mvn -f pom.xml -DskipTests -e sonar:sonar \
                -Dsonar.projectKey=aishe-AisheMasterService \
                -Dsonar.login=${SONAR_AUTH_TOKEN} \
                -Dsonar.host.url=${SONAR_HOST_URL}
          """
        }

        dir('aishe_backend/UserMgtService') {
          sh """
            docker run --rm --dns 8.8.8.8 --add-host=host.docker.internal:host-gateway \
              -v "${WORKSPACE_DIR}/aishe_backend/UserMgtService":/work \
              -w /work \
              maven:3.8.6-jdk-11 \
              mvn -f pom.xml -DskipTests -e sonar:sonar \
                -Dsonar.projectKey=aishe-UserMgtService \
                -Dsonar.login=${SONAR_AUTH_TOKEN} \
                -Dsonar.host.url=${SONAR_HOST_URL}
          """
        }

        // If you have any nested/module poms (example WebdcfUnlock), run sonar there too:
        // dir('aishe_backend/UserMgtService/src/main/resources/WebdcfUnlock/Authorization') { ... }

        // Frontend sonar scan using sonar-scanner-cli container
        dir('aishe_frontend') {
          sh """
            docker run --rm --dns 8.8.8.8 --add-host=host.docker.internal:host-gateway \
              -v "${WORKSPACE_DIR}/aishe_frontend":/usr/src \
              -w /usr/src \
              sonarsource/sonar-scanner-cli \
              -Dsonar.projectKey=aishe-frontend \
              -Dsonar.sources=. \
              -Dsonar.login=${SONAR_AUTH_TOKEN} \
              -Dsonar.host.url=${SONAR_HOST_URL}
          """
        }
      }
      post {
        failure {
          echo "SonarQube stage failed — check Sonar server and token, and ensure host.docker.internal is reachable from containers."
        }
      }
    } // SonarQube Analysis

  } // end stages

  post {
    always {
      echo "Pipeline finished"
    }
    success {
      echo "Pipeline succeeded"
    }
    failure {
      echo "Pipeline failed — check console output for details"
    }
  }
}