#!groovy

pipeline {
    agent none
    environment {

        GITHUB_AUTH_TOKEN = credentials('jx-release-version-github-token')
        GITHUB_REPO = 'visibility-api'
        //GCP_CONTAINER_SECRET = credentials('gcp-blume-shared-infra-dev-container-registry')
        //GCP_CONTAINER_REGISTRY = 'blume-shared-infra-dev'
		
	GCP_CONTAINER_SECRET = credentials('gcp-blume-shared-container-images')
        GCP_CONTAINER_REGISTRY = 'blume-shared-container-images'
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
		    
                milestone label: 'build', ordinal: 1
                
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
