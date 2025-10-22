pipeline {
    agent any

    environment {
        // Make sure PATH includes Docker & Maven (from macOS Homebrew)
        PATH = "/usr/local/bin:/opt/homebrew/bin:/usr/bin:/bin:/usr/sbin:/sbin"
        SONAR_HOST_URL = "http://localhost:9001"
    }

    options {
        timestamps()
        ansiColor('xterm')
        buildDiscarder(logRotator(numToKeepStr: '15'))
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo "Checking out code from GitHub..."
                checkout scm
            }
        }

        stage('Verify Tools') {
            steps {
                sh '''
                    echo "---- Verifying Docker and Maven paths ----"
                    echo "PATH: $PATH"
                    which docker || echo "Docker not found"
                    docker --version || echo "Docker version failed"
                    which mvn || echo "Maven not found"
                    mvn -v || echo "Maven version failed"
                '''
            }
        }

        stage('Build All') {
            parallel {
                stage('Build AisheMasterService') {
                    steps {
                        sh '''
                            echo "=== Building AisheMasterService ==="
                            docker run --rm \
                                -v "$WORKSPACE":/workspace \
                                -w /workspace/aishe_backend/AisheMasterService \
                                maven:3.8.6-jdk-11 mvn -DskipTests -e clean package
                        '''
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'aishe_backend/AisheMasterService/target/*.jar', allowEmptyArchive: true
                        }
                    }
                }

                stage('Build UserMgtService') {
                    steps {
                        sh '''
                            echo "=== Building UserMgtService ==="
                            docker run --rm \
                                -v "$WORKSPACE":/workspace \
                                -w /workspace/aishe_backend/UserMgtService \
                                maven:3.8.6-jdk-11 mvn -DskipTests -e clean package
                        '''
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'aishe_backend/UserMgtService/target/*.jar', allowEmptyArchive: true
                        }
                    }
                }

                stage('Build Frontend') {
                    steps {
                        sh '''
                            echo "=== Building Angular Frontend ==="
                            mkdir -p "$WORKSPACE/.npm"
                            docker run --rm \
                                -v "$WORKSPACE/aishe_frontend":/usr/src \
                                -v "$WORKSPACE/.npm":/root/.npm \
                                -w /usr/src \
                                node:18 bash -c "
                                    export NODE_OPTIONS=--max-old-space-size=4096
                                    npm_config_cache=/root/.npm npm ci --legacy-peer-deps
                                    npm run build
                                "
                        '''
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'aishe_frontend/dist/**', allowEmptyArchive: true
                        }
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            agent { label 'any' }  // no master label required
            steps {
                echo "=== Running SonarQube Analysis ==="
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        echo "Running SonarQube Maven scanner..."
                        cd aishe_backend
                        mvn -DskipTests -e sonar:sonar \
                            -Dsonar.host.url=$SONAR_HOST_URL \
                            -Dsonar.login=$SONAR_AUTH_TOKEN
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline completed successfully! Artifacts archived and SonarQube scan done."
        }
        failure {
            echo "❌ Pipeline failed — check console output for details."
        }
        always {
            echo "Cleaning up workspace..."
        }
    }
}