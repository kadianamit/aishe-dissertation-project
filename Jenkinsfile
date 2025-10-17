pipeline {
    agent any

    stages {
        stage('Build AisheMasterService') {
            steps {
                dir('aishe_backend/AisheMasterService') {
                    script {
                        if (isUnix()) {
                            sh './mvnw clean install'
                        } else {
                            bat 'mvnw.cmd clean install'
                        }
                    }
                }
            }
        }
        stage('Build UserMgtService') {
            steps {
                dir('aishe_backend/UserMgtService') {
                    script {
                        if (isUnix()) {
                            sh './mvnw clean install'
                        } else {
                            bat 'mvnw.cmd clean install'
                        }
                    }
                }
            }
        }
        stage('Build Frontend') {
            steps {
                dir('aishe_frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
    }
}
