pipeline {
    agent any

    environment {
        HTTP_PROXY = System.getenv('HTTP_PROXY') ?: ''
        HTTPS_PROXY = System.getenv('HTTPS_PROXY') ?: ''
        NO_PROXY = System.getenv('NO_PROXY') ?: ''
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build AisheMasterService') {
            agent {
                docker {
                    image 'maven:3.8.6-jdk-11'
                    args """
                        -v ${env.WORKSPACE}/.m2/repository:/root/.m2/repository
                        -v ${System.getenv('HOME')}/.m2/settings.xml:/root/.m2/settings.xml
                        --dns 8.8.8.8
                        --add-host=host.docker.internal:host-gateway
                        -e HTTP_PROXY=${env.HTTP_PROXY}
                        -e HTTPS_PROXY=${env.HTTPS_PROXY}
                        -e NO_PROXY=${env.NO_PROXY}
                    """
                }
            }
            steps {
                withCredentials([string(credentialsId: 'sonarqube-token1', variable: 'SONAR_AUTH_TOKEN')]) {
                    sh '''
                        mvn -B -f aishe_backend/AisheMasterService/pom.xml clean package -DskipTests -Dsonar.host.url=http://host.docker.internal:9001 -Dsonar.login=$SONAR_AUTH_TOKEN
                    '''
                }
            }
        }

        stage('Build UserMgtService') {
            agent {
                docker {
                    image 'maven:3.8.6-jdk-11'
                    args """
                        -v ${env.WORKSPACE}/.m2/repository:/root/.m2/repository
                        -v ${System.getenv('HOME')}/.m2/settings.xml:/root/.m2/settings.xml
                        --dns 8.8.8.8
                        --add-host=host.docker.internal:host-gateway
                        -e HTTP_PROXY=${env.HTTP_PROXY}
                        -e HTTPS_PROXY=${env.HTTPS_PROXY}
                        -e NO_PROXY=${env.NO_PROXY}
                    """
                }
            }
            steps {
                sh 'mvn -B -f aishe_backend/UserMgtService/pom.xml clean package -DskipTests'
            }
        }

        stage('Build Frontend') {
            agent {
                docker {
                    image 'node:16'
                    args """
                        -w /app
                        -v ${env.WORKSPACE}/aishe_frontend:/app
                        -e HTTP_PROXY=${env.HTTP_PROXY}
                        -e HTTPS_PROXY=${env.HTTPS_PROXY}
                        -e NO_PROXY=${env.NO_PROXY}
                    """
                }
            }
            steps {
                sh './ci/build-frontend.sh'
            }
        }
    }
}
