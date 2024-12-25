pipeline {
    agent { label 'Slave' }
    stages {
        stage("Checkout Code") {
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
        stage("Build") {
            steps {
                sh '''
                mvn clean install
                '''
            }
        }
        stage("Test") {
            agent { label 'Slave1' }  // This stage will run on Slave1
            steps {
                sh '''
                mvn test
                '''
            }
        }
        stage("Deploy To Artifactory") {
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
