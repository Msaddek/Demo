pipeline {
    agent any

    tools {
        maven 'M3'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "---|CHECKOUT STAGE|---"
                git branch: "master", url:'https://github.com/Msaddek/Demo.git'
            }
        }

        stage('Compile') {
            steps {
                echo "---|COMPILATION STAGE|---"
                sh 'mvn compile'
            }
        }

        stage('Test'){
            steps {
                echo "---|Test Project|---"
                sh 'mvn test'
            }

            post {
                success {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                echo "---|Package Project|---"
                sh 'mvn package -DskipTests'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

        stage('SSH transfer') {
            steps {
                script {
                    sshPublisher(publishers: [
                        sshPublisherDesc(configName: 'docker-host', transfers:[
                            sshTransfer(
                                execCommand: '''
                                    sudo docker stop demo || true
                                    sudo docker rm demo || true
                                    sudo rmi demo || true
                                '''
                            ),
                            sshTransfer(
                                sourceFiles:"target/*.jar",
                                removePrefix: "target",
                                remoteDirectory: "//home//vagrant",
                                execCommand: "ls /home/vagrant"
                            ),
                            sshTransfer(
                                sourceFiles:"Dockerfile",
                                removePrefix: "",
                                remoteDirectory: "//home//vagrant",
                                execCommand: '''
                                    cd /home/vagrant
                                    sudo docker build -t demo .;
                                    sudo docker run -d --name demo -p 8080:8080 demo
                                '''
                            )
                        ])
                    ])
                }
            }
        }
    }
}