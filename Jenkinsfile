pipeline {
    agent {
        node {
            label 'Linux'
        }
    }
    stages{
        stage('Build') {
            steps {
                sh """
                docker run --privileged -d --network host -v "\$(pwd)":project-maven --name maven-$BRANCH_NAME -e TZ="America/Bogota"  tail -f /dev/null
                
                """
            }
        }
    }
}
