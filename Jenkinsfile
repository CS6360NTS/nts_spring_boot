#!groovy

pipeline {
    agent any
    environment {

        GITHUB_AUTH_TOKEN = credentials('55fb07f1-fef6-4cf3-b9c7-067db4ecb7cb')
        GITHUB_REPO = 'nts_spring_boot'
        //GCP_CONTAINER_SECRET = credentials('gcp-blume-shared-infra-dev-container-registry')
        //GCP_CONTAINER_REGISTRY = 'blume-shared-infra-dev'
		
	//GCP_CONTAINER_SECRET = credentials('gcp-blume-shared-container-images')
        //GCP_CONTAINER_REGISTRY = 'blume-shared-container-images'
    }
    options {
        skipDefaultCheckout()
        timeout(time: 1, unit: 'HOURS')
    }
    stages {
        stage('CI Build') {
 
            steps {
                checkout scm
                sh "mvn clean package"

                
                script {
                	def shortCommit = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()
                    
                    def jobName = "${JOB_NAME}"
                    
                    jobNameParts = jobName.split('/')
                    
					def jenkinsJobName = jobNameParts[jobNameParts.length - 1].toLowerCase().replaceAll(" ","-").replaceAll("_","-")
                    
                    DOCKER_LOGIN = sh returnStdout: true, script: "docker login -u _json_key -p \"\${GCP_CONTAINER_SECRET}\" https://us.gcr.io"
                    		    		
			    	docker.withRegistry('https://us.gcr.io') {
			    	    
                		def customImage = docker.build("${GCP_CONTAINER_REGISTRY}/${GITHUB_REPO}:${jenkinsJobName}-${shortCommit}")
                
        			    customImage.push()
        			}
                }

            }
        }
   
    }
}
