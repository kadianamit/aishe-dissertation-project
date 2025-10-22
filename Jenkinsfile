pipeline {
    agent none // Specify agent per stage

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
                            // Copy project into a container-local temp dir, build there, then copy dist back
                            sh '''
                                # create a container-local temp dir and copy sources into it
                                TMPDIR=$(mktemp -d)
                                cp -R . "$TMPDIR"
                                cd "$TMPDIR"

                                # increase fd limit inside container
                                ulimit -n 65536
                                export NODE_OPTIONS="--max-old-space-size=4096"

                                # use a local cache within the container's tmp dir
                                mkdir -p .npm
                                npm_config_cache=$PWD/.npm npm ci --legacy-peer-deps

                                # build (no unsupported flags)
                                npm_config_cache=$PWD/.npm npm run build

                                # copy built artifacts back to the mounted workspace so Jenkins can archive them
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
            }
        }
    }
}