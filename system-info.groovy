pipeline {
    agent any
    stages {
        stage('Get System Info') {
            steps {
                script {
                    def systemInfo = [:]
                    systemInfo['OS'] = sh(script: 'uname -s', returnStdout: true).trim()
                    systemInfo['Hostname'] = sh(script: 'hostname', returnStdout: true).trim()
                    systemInfo['Uptime'] = sh(script: 'uptime -p', returnStdout: true).trim()
                    systemInfo['CPU'] = sh(script: 'lscpu | grep "Model name"', returnStdout: true).trim()
                    systemInfo['Memory'] = sh(script: 'free -h | grep "Mem:"', returnStdout: true).trim()
                    systemInfo['Disk'] = sh(script: 'df -h | grep "^/dev"', returnStdout: true).trim()
                    echo "System Information: ${systemInfo}"
                }
            }
        }
    }
}
    post {
        always {
            echo 'Pipeline completed.'
        }
    }
