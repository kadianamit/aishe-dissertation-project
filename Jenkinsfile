pipeline {
  agent none

  environment {
    // ensure Jenkins shell finds docker / mvn on macOS Homebrew installs etc
    PATH = "/usr/local/bin:/opt/homebrew/bin:/usr/bin:/bin:/usr/sbin:/sbin"
    // optional: set JAVA_TOOL_OPTIONS or other globals if needed
  }

  stages {
    stage('Build All (parallel)') {
      parallel {
        stage('Build AisheMasterService') {
          agent { label 'any' }
          steps {
            dir('aishe_backend/AisheMasterService') {
              script {
                // run mvn inside official maven container; workspace mounted to /workspace
                sh """
                  docker run --rm \
                    -v "$WORKSPACE/aishe_backend/AisheMasterService":/workspace \
                    -w /workspace \
                    maven:3.8.6-jdk-11 \
                    mvn -DskipTests -e clean package
                """
              }
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'aishe_backend/AisheMasterService/target/*.jar', followSymlinks: false
            }
          }
        }

        stage('Build UserMgtService') {
          agent { label 'any' }
          steps {
            dir('aishe_backend/UserMgtService') {
              script {
                sh """
                  docker run --rm \
                    -v "$WORKSPACE/aishe_backend/UserMgtService":/workspace \
                    -w /workspace \
                    maven:3.8.6-jdk-11 \
                    mvn -DskipTests -e clean package
                """
              }
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'aishe_backend/UserMgtService/target/*.jar', followSymlinks: false
            }
          }
        }

        stage('Build Frontend') {
          agent { label 'any' }
          steps {
            dir('aishe_frontend') {
              script {
                // Use a temp dir inside container to avoid ENFILE on macOS mounted node_modules
                sh '''
                  # create container-local temp dir and run npm install/build there, then copy dist back
                  TMPDIR=$(docker run --rm -v "$PWD":/workspace -w /workspace --entrypoint mktemp node:18 -d /tmp/tmp.XXXXXX)
                  # run inside node container with ulimits
                  docker run --rm \
                    --ulimit nofile=65536:65536 \
                    -v "$PWD":/workspace \
                    -w /workspace \
                    node:18 \
                    /bin/sh -c "export NODE_OPTIONS=--max-old-space-size=4096 && mkdir -p /workspace/.npm && npm_config_cache=/workspace/.npm npm ci --legacy-peer-deps && npm_config_cache=/workspace/.npm npm run build"
                '''
              }
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'aishe_frontend/dist/**', followSymlinks: false
            }
          }
        }

        stage('SonarQube Analysis') {
          agent { label 'any' }
          steps {
            script {
              // Backend: run maven sonar inside docker maven image (adjust projectKey if you want)
              sh """
                docker run --rm \
                  -v "$WORKSPACE/aishe_backend":/workspace \
                  -w /workspace/AisheMasterService \
                  -e SONAR_HOST_URL=${env.SONAR_HOST_URL} \
                  -e SONAR_AUTH_TOKEN=${env.SONAR_AUTH_TOKEN} \
                  maven:3.8.6-jdk-11 \
                  mvn -DskipTests -e sonar:sonar -Dsonar.login=${env.SONAR_AUTH_TOKEN} -Dsonar.host.url=${env.SONAR_HOST_URL}
              """

              // Frontend: use sonar-scanner CLI docker image (assumes sonar-scanner installed image)
              sh """
                docker run --rm \
                  -v "$WORKSPACE/aishe_frontend":/usr/src \
                  -w /usr/src \
                  -e SONAR_HOST_URL=${env.SONAR_HOST_URL} \
                  -e SONAR_AUTH_TOKEN=${env.SONAR_AUTH_TOKEN} \
                  sonarsource/sonar-scanner-cli \
                  -Dsonar.projectKey=aishe-frontend \
                  -Dsonar.sources=. \
                  -Dsonar.login=${env.SONAR_AUTH_TOKEN} \
                  -Dsonar.host.url=${env.SONAR_HOST_URL}
              """
            }
          }
          post {
            always {
              // try to show analysis status file if present
              script {
                sh 'if [ -f report-task.txt ]; then echo "Found report-task.txt"; cat report-task.txt; else echo "report-task.txt not found"; fi || true'
              }
            }
          }
        }
      } // end parallel
    } // end stage Build All
  } // end stages

  post {
    always {
      echo "Pipeline finished - cleaning workspace (manual delete if plugin missing)."
      // cleanWs is not used because it may not be installed. Use deleteDir() as safer fallback.
      script {
        deleteDir()
      }
    }
  }
}