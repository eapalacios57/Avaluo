pipeline {
    agent any
     options {
        buildDiscarder logRotator(
                    daysToKeepStr: '16',
                    numToKeepStr: '10'
        )
    }
    stages{
        stage('Set variables'){
            agent {
                label 'master' 
            }
            when { anyOf { branch 'develop'; branch 'stage'; branch 'master' } }
            steps{
                script{
                    
                   JENKINS_FILE = readJSON file: 'Jenkinsfile.json';
                   urlWlBirc  = JENKINS_FILE[BRANCH_NAME]['urlWlBirc'];
                   idUserANDPassWlBirc = JENKINS_FILE[BRANCH_NAME]['idUserANDPassWlBirc'];
                   idUserANDPassShh = JENKINS_FILE[BRANCH_NAME]['idUserANDPassShh'];
                   artifactNameWlBirc = JENKINS_FILE[BRANCH_NAME]['artifactNameWlBirc'];
                   domainWlBirc = JENKINS_FILE[BRANCH_NAME]['domainWlBirc'];
                   pathwlBirc = JENKINS_FILE[BRANCH_NAME]['pathwlBirc'];
                   clusterWlBirc = JENKINS_FILE[BRANCH_NAME]['clusterWlBirc'];
                   extension = JENKINS_FILE[BRANCH_NAME]['extension'];
                   projectName = JENKINS_FILE[BRANCH_NAME]['projectName'];
                   channelName = JENKINS_FILE[BRANCH_NAME]['channelName']

                  
                   remote.name = projectName
                   remote.host = JENKINS_FILE[BRANCH_NAME]['serverWlSshBirc']
                   remote.port = JENKINS_FILE[BRANCH_NAME]['puertoWlSshBirc']
                   remote.allowAnyHosts = true 
                   remote.pty = true
                }
            }
        }
        stage('Build') {
            when { anyOf { branch 'develop';  branch 'stage'; branch 'master' } }
            agent {
                label 'Linux'
            }
            steps {
                sh """
                docker run --privileged -d --network host -v "\$(pwd)":/project-maven --name maven-$BRANCH_NAME -e TZ="America/Bogota" maven:3.6.1-jdk-8-alpine tail -f /dev/null
                docker exec maven-$BRANCH_NAME bash -c 'cd /project-maven/avaluos_planificador/asesoftwareUtil/ && mvn clean install && cd ../ClienteS3/serviciosAmazonWS && mvn clean install && cd ../../../libs &&
                mvn install:install-file -Dfile=SrvScnGestorFilenet.jar -DgroupId=com.bolivar.avaluos.clientes  -DartifactId=fineNetWebServiceClient -Dversion=1.0.0 -Dpackaging=jar &&
                mvn install:install-file -Dfile=NotificacionAseguradoras.jar -DgroupId=com.bolivar.avaluos.clientes  -DartifactId=notificacionWebServiceClient -Dversion=1.0.0 -Dpackaging=jar &&
                mvn install:install-file -Dfile=FcaWebServiceCliente-1.0.0.jar -DgroupId=com.bolivar.avaluos.clientes  -DartifactId=FcaWebServiceCliente -Dversion=1.0.0 -Dpackaging=jar &&
                mvn install:install-file -Dfile=dosFonts.jar -DgroupId=dosFonts  -DartifactId=dosFonts -Dversion=1.0.0 -Dpackaging=jar &&
                mvn install:install-file -Dfile=fontArial.jar -DgroupId=fontArial  -DartifactId=fontArial -Dversion=1.0.0 -Dpackaging=jar &&
                cd ../avaluos_planificador/avaluos/ && mvn clean install'
                """
                sh 'pwd'
                sh 'ls -la avaluos_planificador/avaluos/avaluosEar/target/'
                stash includes: 'avaluos_planificador/avaluos/avaluosEar/target/avaluosEar-1.0-SNAPSHOT.ear', name: 'artefact'
                sh 'docker rm -f maven-$BRANCH_NAME'

            }
        }
        stage('Upload Artifact'){      
            agent {
                label 'master' 
            }
            when { anyOf { branch 'develop';  branch 'stage'; branch 'master' } } 
            steps{
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    script{
                        withCredentials([usernamePassword(credentialsId: "${idUserANDPassShh}", passwordVariable: 'password', usernameVariable: 'userName')]) {
                            remote.user = userName
                            remote.password = password
                        }
                        echo "Copy ear to Server WebLogic";
                        //unstash 'artefact'
                        sshCommand remote: remote, command: "test -f /home/devops/applications/${projectName}/DeploysTemp/${BRANCH_NAME} || mkdir -p /home/devops/applications/${projectName}/DeploysTemp/${BRANCH_NAME}/"
                        sshPut remote: remote, from: "avaluos_planificador/avaluos/avaluosEar/target/avaluosEar-1.0-SNAPSHOT.ear", into: "/home/devops/applications/${projectName}/DeploysTemp/${BRANCH_NAME}/"
                    }
                }
            }
            post {
                success {
                    println "Upload Artifact <<<<<< success >>>>>>"
                    script{
                        statusCode='success';
                    }    
                }
                unstable {
                    println "Upload Artifact <<<<<< unstable >>>>>>"
                    script{
                        statusCode='unstable';
                    }
                }
                failure {
                    println "Upload Artifact <<<<<< failure >>>>>>"
                    script{
                        statusCode='failure';
                        sshCommand remote: remote, sudo: true, command:"rm -rf ${pathwlBirc}/DeploysTemp/${JOB_BASE_NAME}/${artifactNameWlBirc}.${extension}"
                    }
                }
            }
                
        }
    }
}
