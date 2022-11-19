pipeline {
    agent any

    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    export MAVEN_HOME=/usr/local/maven
                    export PATH=$PATH:$MAVEN_HOME/bin
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
    }
}
