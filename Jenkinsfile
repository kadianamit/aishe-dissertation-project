pipeline {
    agent none

    environment {
        PATH = "/opt/homebrew/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin"
    }

    stages {

        stage('Build All') {
            parallel {

                stage('Build AisheMasterService') {
                    agent { docker { image 'maven:3.8.6-jdk-11' } }
                    steps {
                        dir('aishe_backend/AisheMasterService') {
                            sh 'mvn -DskipTests clean package'
                        }
                    }
                }

                stage('Build UserMgtService') {
                    agent { docker { image 'maven:3.8.6-jdk-11' } }
                    steps {
                        dir('aishe_backend/UserMgtService') {
                            sh 'mvn -DskipTests clean package'
                        }
                    }
                }

                stage('Build Frontend') {
                    agent { docker { image 'node:18' args '--ulimit nofile=65536:65536' } }
                    steps {
                        dir('aishe_frontend') {
                            sh '''
                                export NODE_OPTIONS="--max-old-space-size=4096"
                                mkdir -p $WORKSPACE/.npm
                                npm_config_cache=$WORKSPACE/.npm npm ci --legacy-peer-deps
                                npm_config_cache=$WORKSPACE/.npm npm run build
                            '''
                        }
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            agent any
            steps {
                withSonarQubeEnv('SonarQube') {
                    script {
                        // Backend analysis
                        sh '''
                            mvn -f aishe_backend/pom.xml -DskipTests sonar:sonar \
                            -Dsonar.host.url=$SONAR_HOST_URL \
                            -Dsonar.login=$SONAR_AUTH_TOKEN
                        '''

                        // Frontend analysis
                        sh '''
                            docker run --rm \
                            -v "$WORKSPACE/aishe_frontend":/usr/src \
                            -w /usr/src sonarsource/sonar-scanner-cli \
                            -Dsonar.projectKey=aishe-frontend \
                            -Dsonar.sources=. \
                            -Dsonar.host.url=$SONAR_HOST_URL \
                            -Dsonar.login=$SONAR_AUTH_TOKEN
                        '''
                    }
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline completed — cleaning workspace."
            cleanWs()
        }
    }
}