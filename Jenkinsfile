pipeline {
    agent any

    stages {
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
