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
                configFileProvider([configFile(fileId: '387c313c-9cde-4dae-bcd4-74214b88e591', variable: 'MAVEN_SETTINGS')]) {
                    sh '''
                    mvn deploy -s $MAVEN_SETTINGS
                    '''
                }
            }
        }
    }
}
