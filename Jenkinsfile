pipeline {
    agent none  // No global agent since we'll specify per stage
    
    stages {
        stage("Checkout Code") {
            agent { label 'Slave' }
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[
                        url: 'https://github.com/rak134/sampleapp.git',
                        credentialsId: 'github-token'
                    ]]
                ])
            }
        }
        
        stage("Parallel Stages") {
            parallel {
                stage("Build") {
                    agent { label 'Slave' }
                    steps {
                        sh '''
                        mvn clean install
                        '''
                    }
                }
                
                stage("Test") {
                    agent { label 'Slave1' }
                    steps {
                        sh '''
                        mvn test
                        '''
                    }
                }
            }
        }
        
        stage("Deploy To Artifactory") {
            agent { label 'Slave' }
            steps {
                configFileProvider([configFile(fileId: '8f023859-bc45-44cd-ae4a-e3462a3edcf3', variable: 'MAVEN_SETTINGS')]) {
                    sh '''
                    mvn deploy -s $MAVEN_SETTINGS
                    '''
                }
            }
        }
    }
}
