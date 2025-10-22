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

                stage('SonarQube Analysis') {
                  agent { label 'master' } // run on controller where Sonar env is configured
                  steps {
                    withSonarQubeEnv('SonarQube-Local') { // <-- use the exact Sonar name you configured in Jenkins
                      script {
                        // Backend: run mvn from the backend folder so POM is found inside the container/agent workspace
                        dir('aishe_backend') {
                          sh '''
                            echo "Running Sonar for backend in: $PWD"
                            mvn -DskipTests -e sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN -Dsonar.host.url=$SONAR_HOST_URL
                          '''
                        }

                        // Frontend: run sonar-scanner CLI inside Docker but mount the workspace and run from the frontend folder
                        sh '''
                          echo "Running Sonar for frontend using sonar-scanner docker image"
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
                      // show a short message if file missing
                      sh 'test -f ${WORKSPACE}/report-task.txt && echo "report-task.txt exists" || echo "report-task.txt NOT found"'
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
                            args '--ulimit nofile=65536:65536'
                        }
                    }
                    steps {
                        dir('aishe_frontend') {
                            sh '''
                                # Create a container-local temp directory and copy project (excluding node_modules)
                                TMPDIR=$(mktemp -d)
                                tar -cf - --exclude=node_modules . | (cd "$TMPDIR" && tar -xf -)
                                cd "$TMPDIR"

                                # Raise file descriptor limit inside container
                                ulimit -n 65536
                                export NODE_OPTIONS="--max-old-space-size=4096"

                                # Use container-local npm cache
                                mkdir -p .npm
                                npm_config_cache=$PWD/.npm npm ci --legacy-peer-deps

                                # Build the Angular project
                                npm_config_cache=$PWD/.npm npm run build

                                # Copy the built artifacts back to the Jenkins workspace
                                mkdir -p "$WORKSPACE/aishe_frontend/dist"
                                cp -R dist/* "$WORKSPACE/aishe_frontend/dist/" || true
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
        } // end stage
    } // end stages
} // end pipeline

