pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning the microservices source code...'
                // Explicitly clones your repo into the workspace folder
                checkout scm: [
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[url: 'https://github.com', credentialsId: 'github-jenkins-token']]
                ]
            }
        }

        stage('Stop Existing Services') {
            steps {
                echo 'Tearing down old containers...'
                sh 'sh stop-compose.sh'
            }
        }

        stage('Build and Launch Services') {
            steps {
                echo 'Building and starting microservices...'
                sh 'sh start-compose.sh'
            }
        }
    }
}
