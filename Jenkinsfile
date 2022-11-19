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
                sh 'docker build -t nts_spring_boot-$BUILD_NUMBER  .' 
            }
            

        }
    }
}
