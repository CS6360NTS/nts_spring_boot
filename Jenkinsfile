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
                //sh '/usr/local/maven/bin/mvn -Dmaven.test.failure.ignore=true install' 
                sh '/opt/maven/bin/mvn -Dmaven.test.failure.ignore=true install'
            }
            

        }
        stage ('Docker Build') {
            steps {
                sh 'docker build -t gcr.io/t-operative-380102/app:3' 
            }
            

        }
        stage ('Docker push') {
            steps {
                sh 'gcloud auth configure-docker'
                sh 'docker push  gcr.io/t-operative-380102/app:3' 
            }
            

        }        
    }
}
