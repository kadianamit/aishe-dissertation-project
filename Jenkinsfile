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
            docker { image 'node:18' }
          }
          steps {
            dir('aishe_frontend') {
              sh '''
                echo "Building Angular Frontend..."
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
        withSonarQubeEnv('SonarQube-Local') {
          script {
            echo "🔍 Starting SonarQube Analysis..."

            // Backend analysis
            dir('aishe_backend') {
              sh '''
                echo "Running Sonar for backend..."
                mvn -DskipTests -e sonar:sonar \
                  -Dsonar.projectKey=aishe-backend \
                  -Dsonar.login=$SONAR_AUTH_TOKEN \
                  -Dsonar.host.url=$SONAR_HOST_URL
              '''
            }

            // Frontend analysis
            sh '''
              echo "Running Sonar for frontend..."
              docker run --rm \
                -v "$WORKSPACE/aishe_frontend":/usr/src/app \
                -w /usr/src/app \
                sonarsource/sonar-scanner-cli \
                -Dsonar.projectKey=aishe-frontend \
                -Dsonar.sources=. \
                -Dsonar.login=$SONAR_AUTH_TOKEN \
                -Dsonar.host.url=$SONAR_HOST_URL
            '''
          }
        }
      }
      post {
        always {
          echo "✅ SonarQube analysis completed (check in Sonar dashboard)."
        }
      }
    } // end SonarQube Analysis

  } // end stages
} // end pipeline