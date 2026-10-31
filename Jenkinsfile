pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Pulling the latest codebase...'
            }
        }

        stage('Stop Existing Services') {
            steps {
                echo 'Tearing down old containers using script...'
                // Executes your stop script from the root workspace directory
                sh './stop-compose.sh'
            }
        }

        stage('Build and Launch Services') {
            steps {
                echo 'Building and starting microservices using script...'
                // Executes your start script to handle build orchestration
                sh './start-compose.sh'
            }
        }
    }
}
