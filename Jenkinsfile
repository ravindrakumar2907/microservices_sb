pipeline {
    agent any

    tools{
        maven 'mavan-3.10.0'  // installed in jenkins tool
    }
    env {
        //MAVEN_HOME = '/usr/share/maven'
    }
    stages {
        stage('Clean Compile') {
            steps {
                echo 'Cleaning and compiling the code...'
                sh 'cd apiGateway && mvn clean compile'
                sh 'cd eurekaServer && mvn clean compile'
                sh 'cd productsService && mvn clean compile'
                sh 'cd todoService && mvn clean compile'
            }
        }
        /*
        stage('Deploy') {
                    steps {
                        echo 'Deploying the application...'
                        sh 'cd eurekaServer && mvn spring-boot:deploy'
                        sh 'cd apiGateway && mvn spring-boot:deploy'
                        sh 'cd productsService && mvn spring-boot:deploy'
                        sh 'cd todoService && mvn spring-boot:deploy'
                    }
                }
    }*/
    }
}
