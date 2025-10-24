pipeline {
  agent any

  environment {
    PATH = "/usr/local/bin:${env.PATH}"
    DOCKER_BIN = "/usr/local/bin/docker"
    WORKSPACE_DIR = "${env.WORKSPACE}"
    MAVEN_IMG = "maven:3.8.6-jdk-11"
    NODE_IMG  = "node:18"
    DNS_SERVER = "8.8.8.8"
  }

  options {
    skipDefaultCheckout(true)
    timestamps()
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build All (parallel)') {
      parallel {
        stage('Build AisheMasterService') {
          steps {
            script {
              sh '''#!/bin/bash
set -euo pipefail
if [ -x "${DOCKER_BIN}" ]; then
  "${DOCKER_BIN}" run --rm --dns ${DNS_SERVER} --add-host=host.docker.internal:host-gateway \
    -v "${WORKSPACE_DIR}/aishe_backend/AisheMasterService":/work -w /work \
    -v "${WORKSPACE_DIR}/.m2/repository":/root/.m2/repository \
    ${MAVEN_IMG} mvn -Dmaven.repo.local=/root/.m2/repository -DskipTests -B -e clean package
else
  echo "docker not found; running maven locally"
  (cd aishe_backend/AisheMasterService && mvn -Dmaven.repo.local="$HOME/.m2/repository" -DskipTests -B -e clean package)
fi
'''
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'aishe_backend/AisheMasterService/target/*.jar', followSymlinks: false
            }
          }
        }

        stage('Build UserMgtService') {
          steps {
            script {
              sh '''#!/bin/bash
set -euo pipefail
if [ -x "${DOCKER_BIN}" ]; then
  "${DOCKER_BIN}" run --rm --dns ${DNS_SERVER} --add-host=host.docker.internal:host-gateway \
    -v "${WORKSPACE_DIR}/aishe_backend/UserMgtService":/work -w /work \
    -v "${WORKSPACE_DIR}/.m2/repository":/root/.m2/repository \
    ${MAVEN_IMG} mvn -Dmaven.repo.local=/root/.m2/repository -DskipTests -B -e clean package
else
  echo "docker not found; running maven locally"
  (cd aishe_backend/UserMgtService && mvn -Dmaven.repo.local="$HOME/.m2/repository" -DskipTests -B -e clean package)
fi
'''
            }
          }
          post {
            success {
              archiveArtifacts artifacts: 'aishe_backend/UserMgtService/target/*.jar', followSymlinks: false
            }
          }
        }

        stage('Build Frontend') {
          steps {
            script {
              sh '''#!/bin/bash
set -euo pipefail
if [ -x "${DOCKER_BIN}" ]; then
  "${DOCKER_BIN}" run --rm --dns ${DNS_SERVER} --add-host=host.docker.internal:host-gateway \
    -v "${WORKSPACE_DIR}/aishe_frontend":/work -w /work \
    --ulimit nofile=65536:65536 \
    ${NODE_IMG} bash -lc "\
      ulimit -n 65536 && \
      export NODE_OPTIONS='--max-old-space-size=4096' && \
      mkdir -p .npm && npm_config_cache=\$PWD/.npm npm ci --legacy-peer-deps && \
      if [ ! -d node_modules/@angular/cli ]; then npm i @angular/cli --no-save; fi && \
      npm_config_cache=\$PWD/.npm npm run build && \
      mkdir -p '${WORKSPACE_DIR}/aishe_frontend/dist' && cp -R dist/* '${WORKSPACE_DIR}/aishe_frontend/dist/' || true"
else
  echo "docker not found; running npm locally"
  (cd aishe_frontend && npm ci --legacy-peer-deps && \
    if [ ! -d node_modules/@angular/cli ]; then npm i @angular/cli --no-save; fi && \
    npm run build)
fi
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

  } // end stages

  post {
    always {
      echo "Workspace: ${WORKSPACE_DIR}"
      sh(script: 'ls -la', returnStdout: false)
    }
    success {
      echo "Pipeline completed successfully."
    }
    failure {
      echo "Pipeline failed. Check console output for details."
    }
  }
}
