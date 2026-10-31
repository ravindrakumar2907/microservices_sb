pipeline {
     agent any

        tools {
            // Double check this matches the exact Name you configured in Manage Jenkins -> Tools
            maven 'mavan-3.10.0'
        }

        // FIXED: Changed 'env' to 'environment'
        environment {
            // MAVEN_HOME = '/usr/share/maven'
        }
    stages {
        stage('Clean Compile') {
                    steps {
                        echo 'Cleaning and compiling the code...'

                        // FIXED: Using dir() ensures Jenkins safely enters and leaves the folder
                        dir('apiGateway') {
                            sh 'mvn clean compile'
                        }
                        dir('eurekaServer') {
                            sh 'mvn clean compile'
                        }
                        dir('productsService') {
                            sh 'mvn clean compile'
                        }
                        dir('todoService') {
                            sh 'mvn clean compile'
                        }
                    }
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
