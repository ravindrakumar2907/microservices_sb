pipeline {
    agent any

    stages {

        stage('Stop Existing Services') {
            steps {
                echo 'Tearing down old containers using interpreter...'
                // Using 'sh sh' bypasses any 'Permission denied' execution limits on the script file
                sh 'sh stop-compose.sh'
            }
        }

        stage('Build and Launch Services') {
            steps {
                echo 'Building and starting microservices...'
                // Triggers Docker Compose orchestration to handle the clean build and boot sequence
                sh 'sh start-compose.sh'
            }
        }
    }
}
