pipeline {
    agent none // We will specify the agent for each stage

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
                      docker {
                        image 'node:18'
                        args  '--ulimit nofile=65536:65536'
                      }
                    }
                    steps {
                        dir('aishe_frontend') {
                            sh "export NODE_OPTIONS=\"--max-old-space-size=4096\" && mkdir -p $WORKSPACE/.npm && npm_config_cache=$WORKSPACE/.npm npm ci --legacy-peer-deps && npm_config_cache=$WORKSPACE/.npm npm run build -- --max-workers=2"
                        }
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'aishe_frontend/dist/**', followSymlinks: false
                        }
                    }
                }
            }
        }
    }
}
