pipeline {
    agent any

    stages {
        stage ('Initialize') {
            steps {
                sh '''

                    echo "MAVEN_HOME = ${MAVEN_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh '/usr/local/maven/bin/mvn -Dmaven.test.failure.ignore=true install' 
            }
            

        }
        stage ('Docker Build') {
            steps {
                sh 'docker build -t gcr.io/nts-spring-boot/app:$BUILD_NUMBER  .' 
            }
            

        }
        stage ('Docker push') {
            steps {
                sh 'gcloud auth configure-docker'
                sh 'docker push  gcr.io/nts-spring-boot/app:$BUILD_NUMBER' 
            }
            

        }        
    }
}
