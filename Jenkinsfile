pipeline {
    agent any

    tools {
        maven 'maven3'
        jdk 'jdk11'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git changelog: false, poll: false, 
                url: 'https://github.com/ManojBharath/POC02_Packers_movers_app.git'
            }
        }
        
        stage('Maven-Clean') {
            steps {
                sh 'mvn clean'
            }
        }
        
        stage('Maven-Compile') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Maven-Test') {
            steps {
                sh 'mvn test'
            }
        }    
        
        stage('Maven-Package') {
            steps {
                sh 'mvn package'
            }
        }
        
        stage("Check Target files are cretaed") {
            steps {
                sh '''
                pwd 
                ls -l
                ls -l target 
                '''
            }
        }
        
        stage('Cleanup Old Docker') {
            steps {
                sh '''
                docker stop packers-container || true
                docker rm packers-container || true
                docker rmi packers-movers || true
                docker rmi -f $(docker images -q) || true
                '''
            }
            
        }
        
        stage("Docker build image from artifact") {
            steps {
                sh "docker build -t packers-movers ."
            }
        }
        
        stage('DockerHub Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    sh 'echo $PASS | docker login -u $USER --password-stdin'
                }
            }
        }
        
        stage('Tag Image to DockerHubRepo') {
            steps {
                sh """
                docker tag packers-movers pmb3107/packers-movers:${BUILD_NUMBER}
                """
            }
        }
        
        stage('Push Image to DockerHub') {
            steps {
                sh """
                docker push pmb3107/packers-movers:${BUILD_NUMBER}
                """
            }
        }
    } // End of Satges
} // End of pipeline