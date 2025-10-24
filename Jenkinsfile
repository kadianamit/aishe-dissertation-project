pipeline {
    agent none // We will specify the agent for each stage

    environment {
        PATH = "/usr/local/bin:${env.PATH}"
        DOCKER_BIN = "/usr/local/bin/docker"
        WORKSPACE_DIR = "${env.WORKSPACE}"
    }

    stages {
        stage('Dev Check') {
            steps {
                sh '''
                    if [ -x "${DOCKER_BIN}" ]; then
                        echo "docker found at ${DOCKER_BIN}"
                    else
                        echo "docker not found at ${DOCKER_BIN}, falling back to host tools"
                    fi
                '''
            }
        }

        stage('Build All') {
            parallel {

                stage('Build AisheMasterService') {
                    steps {
                        sh '''
                            if [ -x "${DOCKER_BIN:-/usr/local/bin/docker}" ]; then
                                "${DOCKER_BIN:-/usr/local/bin/docker}" run --rm --dns 8.8.8.8 --add-host=host.docker.internal:host-gateway \
                                    -v "${WORKSPACE_DIR}/aishe_backend/AisheMasterService":/work -w /work \
                                    -v "${WORKSPACE_DIR}/.m2/repository":/root/.m2/repository \
                                    maven:3.8.6-jdk-11 mvn -Dmaven.repo.local=/root/.m2/repository -DskipTests -B -e clean package
                            else
                                echo "docker not found, running maven locally"
                                cd aishe_backend/AisheMasterService && mvn -DskipTests -B -e clean package
                            fi
                        '''
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'aishe_backend/AisheMasterService/target/*.jar', followSymlinks: false
                        }
                    }
                }

                stage('Build UserMgtService') {
                    steps {
                        sh '''
                            if [ -x "${DOCKER_BIN:-/usr/local/bin/docker}" ]; then
                                "${DOCKER_BIN:-/usr/local/bin/docker}" run --rm --dns 8.8.8.8 --add-host=host.docker.internal:host-gateway \
                                    -v "${WORKSPACE_DIR}/aishe_backend/UserMgtService":/work -w /work \
                                    -v "${WORKSPACE_DIR}/.m2/repository":/root/.m2/repository \
                                    maven:3.8.6-jdk-11 mvn -Dmaven.repo.local=/root/.m2/repository -DskipTests -B -e clean package
                            else
                                echo "docker not found, running maven locally"
                                cd aishe_backend/UserMgtService && mvn -DskipTests -B -e clean package
                            fi
                        '''
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'aishe_backend/UserMgtService/target/*.jar', followSymlinks: false
                        }
                    }
                }

                stage('Build Frontend') {
                    steps {
                        sh '''
                            if [ -x "${DOCKER_BIN:-/usr/local/bin/docker}" ]; then
                                "${DOCKER_BIN:-/usr/local/bin/docker}" run --rm \
                                    -v "${WORKSPACE_DIR}/aishe_frontend":/work -w /work \
                                    --ulimit nofile=65536:65536 \
                                    node:18 bash -c "
                                        # Raise file descriptor limit inside container
                                        ulimit -n 65536
                                        export NODE_OPTIONS=\"--max-old-space-size=4096\"

                                        # Use container-local npm cache
                                        mkdir -p .npm
                                        npm_config_cache=\$PWD/.npm npm ci --legacy-peer-deps

                                        # Check for @angular/cli in node_modules
                                        if [ ! -d node_modules/@angular/cli ]; then
                                            echo 'Fallback: Installing @angular/cli locally as dev dependency'
                                            npm i @angular/cli --no-save
                                        fi

                                        # Build the Angular project
                                        npm_config_cache=\$PWD/.npm npm run build

                                        # Copy the built artifacts back to the Jenkins workspace
                                        mkdir -p \"$WORKSPACE_DIR/aishe_frontend/dist\"
                                        cp -R dist/* \"$WORKSPACE_DIR/aishe_frontend/dist/\" || true
                                    "
                            else
                                echo "docker not found, running npm locally"
                                cd aishe_frontend
                                npm ci --legacy-peer-deps
                                if [ ! -d node_modules/@angular/cli ]; then
                                    echo 'Fallback: Installing @angular/cli locally as dev dependency'
                                    npm i @angular/cli --no-save
                                fi
                                npm run build
                            fi
                        '''
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