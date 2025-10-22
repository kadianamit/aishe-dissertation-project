pipeline {
  agent any
  environment {
    // Bind your Sonar token credential (create a Secret Text with id 'sonarqube-token1')
    SONAR_AUTH_TOKEN = credentials('sonarqube-token1')
    SONAR_HOST_URL = 'http://localhost:9001' // adjust if different
    // Ensure workspace is available for docker mount
    WORKSPACE_DIR = "${env.WORKSPACE}"
  }

  stages {
    stage('Build All') {
      parallel {
        stage('Build AisheMasterService') {
          steps {
            dir('aishe_backend/AisheMasterService') {
              // run maven inside a maven container with DNS override
              sh """
                docker run --rm --dns 8.8.8.8 \
                  -v "${WORKSPACE_DIR}/aishe_backend/AisheMasterService":/work \
                  -w /work \
                  maven:3.8.6-jdk-11 \
                  mvn -DskipTests -e clean package
              """
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'aishe_backend/AisheMasterService/target/*.jar', followSymlinks: false
            }
          }
        }

        stage('Build UserMgtService') {
          steps {
            dir('aishe_backend/UserMgtService') {
              sh """
                docker run --rm --dns 8.8.8.8 \
                  -v "${WORKSPACE_DIR}/aishe_backend/UserMgtService":/work \
                  -w /work \
                  maven:3.8.6-jdk-11 \
                  mvn -DskipTests -e clean package
              """
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'aishe_backend/UserMgtService/target/*.jar', followSymlinks: false
            }
          }
        }

        stage('Build Frontend') {
          steps {
            dir('aishe_frontend') {
              // run node build inside node container with DNS override, use local tmp inside container to avoid macOS ENFILE issues
              sh '''
                docker run --rm --dns 8.8.8.8 \
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
        }

        stage('SonarQube Analysis') {
          steps {
            // Backend: run sonar for each module where pom.xml exists
            // AisheMasterService
            dir("${WORKSPACE_DIR}/aishe_backend/AisheMasterService") {
              sh """
                docker run --rm --dns 8.8.8.8 \
                  -v "${WORKSPACE_DIR}/aishe_backend/AisheMasterService":/work \
                  -w /work \
                  maven:3.8.6-jdk-11 \
                  mvn -DskipTests -e -f pom.xml sonar:sonar \
                    -Dsonar.projectKey=aishe-AisheMasterService \
                    -Dsonar.login=${SONAR_AUTH_TOKEN} \
                    -Dsonar.host.url=${SONAR_HOST_URL}
              """
            }

            // UserMgtService (top-level)
            dir("${WORKSPACE_DIR}/aishe_backend/UserMgtService") {
              sh """
                docker run --rm --dns 8.8.8.8 \
                  -v "${WORKSPACE_DIR}/aishe_backend/UserMgtService":/work \
                  -w /work \
                  maven:3.8.6-jdk-11 \
                  mvn -DskipTests -e -f pom.xml sonar:sonar \
                    -Dsonar.projectKey=aishe-UserMgtService \
                    -Dsonar.login=${SONAR_AUTH_TOKEN} \
                    -Dsonar.host.url=${SONAR_HOST_URL}
              """
            }

            // WebdcfUnlock nested POM inside UserMgtService resources (if present)
            dir("${WORKSPACE_DIR}/aishe_backend/UserMgtService/src/main/resources/WebdcfUnlock/Authorization") {
              sh """
                if [ -f pom.xml ]; then
                  docker run --rm --dns 8.8.8.8 \
                    -v "${WORKSPACE_DIR}/aishe_backend/UserMgtService/src/main/resources/WebdcfUnlock/Authorization":/work \
                    -w /work \
                    maven:3.8.6-jdk-11 \
                    mvn -DskipTests -e -f pom.xml sonar:sonar \
                      -Dsonar.projectKey=aishe-UserMgtService-WebdcfUnlock-Authorization \
                      -Dsonar.login=${SONAR_AUTH_TOKEN} \
                      -Dsonar.host.url=${SONAR_HOST_URL}
                else
                  echo "No nested pom in WebdcfUnlock/Authorization — skipping."
                fi
              """
            }

            // Frontend: sonar-scanner using sonar-scanner-cli docker image
            dir("${WORKSPACE_DIR}/aishe_frontend") {
              sh """
                docker run --rm --dns 8.8.8.8 \
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
        }

      } // end parallel
    } // end Build All
  } // end stages

  post {
    always {
      echo "Pipeline finished"
    }
  }
}
