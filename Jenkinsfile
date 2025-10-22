pipeline {
  agent none

  environment {
    PATH = "/usr/local/bin:/opt/homebrew/bin:/usr/bin:/bin:/usr/sbin:/sbin"
    SONAR_HOST_URL = "http://localhost:9001"
    SONAR_AUTH_TOKEN = credentials('sonarqube-token1') // use your Jenkins credential ID here
  }

  stages {

    stage('Build All (parallel)') {
      parallel {

        stage('Build AisheMasterService') {
          agent any
          steps {
            dir('aishe_backend/AisheMasterService') {
              echo "Building AisheMasterService..."
              sh '''
                docker run --rm \
                  -v "$PWD":/workspace \
                  -w /workspace \
                  maven:3.8.6-jdk-11 \
                  mvn -DskipTests -e clean package
              '''
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
            }
          }
        }

        stage('Build UserMgtService') {
          agent any
          steps {
            dir('aishe_backend/UserMgtService') {
              echo "Building UserMgtService..."
              sh '''
                docker run --rm \
                  -v "$PWD":/workspace \
                  -w /workspace \
                  maven:3.8.6-jdk-11 \
                  mvn -DskipTests -e clean package
              '''
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
            }
          }
        }

        stage('Build Frontend') {
          agent any
          steps {
            dir('aishe_frontend') {
              echo "Building Frontend..."
              sh '''
                docker run --rm \
                  --ulimit nofile=65536:65536 \
                  -v "$PWD":/workspace \
                  -w /workspace \
                  node:18 \
                  /bin/sh -c "npm install --legacy-peer-deps && npm run build"
              '''
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'dist/**', followSymlinks: false
            }
          }
        }

        stage('SonarQube Analysis') {
          agent any
          steps {
            echo "Running SonarQube analysis for backend and frontend..."

            // Backend Sonar analysis
            sh '''
              docker run --rm \
                -v "$WORKSPACE/aishe_backend":/workspace \
                -w /workspace/AisheMasterService \
                -e SONAR_HOST_URL=$SONAR_HOST_URL \
                -e SONAR_AUTH_TOKEN=$SONAR_AUTH_TOKEN \
                maven:3.8.6-jdk-11 \
                mvn -DskipTests -e sonar:sonar \
                -Dsonar.login=$SONAR_AUTH_TOKEN \
                -Dsonar.host.url=$SONAR_HOST_URL
            '''

            // Frontend Sonar analysis
            sh '''
              docker run --rm \
                -v "$WORKSPACE/aishe_frontend":/usr/src \
                -w /usr/src \
                -e SONAR_HOST_URL=$SONAR_HOST_URL \
                -e SONAR_AUTH_TOKEN=$SONAR_AUTH_TOKEN \
                sonarsource/sonar-scanner-cli \
                -Dsonar.projectKey=aishe-frontend \
                -Dsonar.sources=. \
                -Dsonar.login=$SONAR_AUTH_TOKEN \
                -Dsonar.host.url=$SONAR_HOST_URL
            '''
          }
          post {
            always {
              echo "Checking Sonar report file (if exists)..."
              sh '''
                if [ -f report-task.txt ]; then
                  echo "report-task.txt found:"; cat report-task.txt
                else
                  echo "report-task.txt not found"
                fi
              '''
            }
          }
        }

      } // end parallel
    } // end Build All
  } // end stages

  post {
    always {
      echo "Pipeline finished - cleaning workspace..."
      script { deleteDir() }
    }
  }
}