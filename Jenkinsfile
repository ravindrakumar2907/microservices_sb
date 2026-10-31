pipeline {
     agent any

        tools {
            // Double check this matches the exact Name you configured in Manage Jenkins -> Tools
            maven 'mavan-3.10.0'
        }

        // FIXED: Changed 'env' to 'environment'
        environment {
             BUILD_NO = '1.0'
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
        },
        stage('Run Projects (Deploy)') {
                            steps {
                                echo 'Running the projects...'

                                // FIXED: Using dir() ensures Jenkins safely enters and leaves the folder
                                dir('apiGateway') {
                                    sh 'mvn spring-boot:deploy'
                                }
                                dir('eurekaServer') {
                                    sh 'mvn spring-boot:deploy'
                                }
                                dir('productsService') {
                                    sh 'mvn spring-boot:deploy'
                                }
                                dir('todoService') {
                                    sh 'mvn spring-boot:deploy'
                                }
                            }
                }
    }

}
