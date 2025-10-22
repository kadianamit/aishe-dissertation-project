pipeline {
  agent none

  /* Ensure Jenkins can find docker/mvn on macOS (Homebrew paths) */
  environment {
    PATH = "/usr/local/bin:/opt/homebrew/bin:${env.PATH}"
    // If you set SONAR credentials as env or credentialsId, keep them here or use withCredentials in stages.
  }

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
            docker { image 'node:18' }
          }
          steps {
            dir('aishe_frontend') {
              // use workspace-local npm cache to avoid permission/ENFILE issues on macOS mounts
              sh '''
                ulimit -n 65536
                export NODE_OPTIONS="--max-old-space-size=4096"
                TMP_NPM_CACHE="$WORKSPACE/.npm"
                mkdir -p "$TMP_NPM_CACHE"
                npm_config_cache="$TMP_NPM_CACHE" npm ci --legacy-peer-deps
                npm_config_cache="$TMP_NPM_CACHE" npm run build
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
          agent { label 'master' } // or an agent where sonar env and docker are available
          steps {
            withSonarQubeEnv('SonarQube-Local') {
              script {
                // Backend Maven analysis
                dir('aishe_backend') {
                  sh "mvn -DskipTests -e sonar:sonar -Dsonar.login=${SONAR_AUTH_TOKEN} -Dsonar.host.url=${SONAR_HOST_URL}"
                }

                // Frontend analysis using workspace-mounted scanner image (requires docker CLI)
                sh """
                  docker run --rm \
                    -v "$WORKSPACE/aishe_frontend":/usr/src \
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
        }

      } // end parallel
    } // end Build All
  } // end stages

  post {
    always {
      echo "Pipeline finished. Check console for details."
    }
  }
} // end pipeline