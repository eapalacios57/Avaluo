def remote = [:]
pipeline {
    agent any
     options {
        buildDiscarder logRotator(
                    daysToKeepStr: '16',
                    numToKeepStr: '10'
        )
    }
    stages{
        stage('SonarQube analysis') {
           agent any
           steps {
               script {
                   //last_stage = env.STAGE_NAME
                   def SCANNERHOME = tool name: 'sonar-scanner'
                   def projectKey="Avaluos-Project"
                   def organization='segurosbolivar'
                   def pathSourceSonar='avaluos_planificador'
                   def sonarURL='http://192.168.100.158:9000/'
                   withSonarQubeEnv('SonarCloud'){
                         sh """
                           ${SCANNERHOME}/bin/sonar-scanner -e -Dsonar.java.binaries=. -Dsonar.projectKey=${projectKey} -Dsonar.sources=${pathSourceSonar} -Dsonar.host.url=${sonarURL}
                         """
                   }
               }
               echo "SonarQube analysis";              
           }
        } 
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
                        unstash 'artefact'
                        sshCommand remote: remote, command: "test -f /home/devops/applications/${projectName}/DeploysTemp/${BRANCH_NAME} || mkdir -p /home/devops/applications/${projectName}/DeploysTemp/${BRANCH_NAME}/"
                        sshPut remote: remote, from: "avaluos_planificador/avaluos/avaluosEar/target/${artifactNameWlBirc}.${extension}", into: "/home/devops/applications/${projectName}/DeploysTemp/${BRANCH_NAME}/"
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
                    }
                }
            }
                
        }
        stage('Stop App'){
            agent {
                label 'master' 
            }
            environment {
                WEBLOGIC_CREDENTIAL = credentials("${idUserANDPassWlBirc}")
            }  
            when { anyOf { branch 'develop'; branch 'stage'; branch 'master' } } 
            steps{
                script{
                    try{
                        sshCommand remote: remote, command: "cd ${domainWlBirc} && . ./setDomainEnv.sh ENV && java weblogic.Deployer -adminurl ${urlWlBirc} -username ${WEBLOGIC_CREDENTIAL_USR} -password ${WEBLOGIC_CREDENTIAL_PSW} -stop -name $artifactNameWlBirc"
                        statusCodeStop='success';
                    } catch (err) {
                        statusCodeStop='failure';
                        statusCodeUndeploy='failure';   
                        echo "Error al parar la aplicacion"
                    }
                }
            }
            post {
                success {
                    println "Stage Stop App <<<<<< success >>>>>>"
                }
                unstable {
                    println "Stage Stop App <<<<<< unstable >>>>>>"    
                    script{
                        statusCodeStop='unstable';
                        statusCodeUndeploy='failure';
                    }              
                }
                failure {
                    println "Stage Stop App <<<<<< failure >>>>>>"
                    script{
                        statusCodeStop='failure';
                        statusCodeUndeploy='failure';
                    }
                }
            }          
        }
         stage('Undeploy'){
           agent {
                label 'master' 
            }
            environment {
                WEBLOGIC_CREDENTIAL = credentials("${idUserANDPassWlBirc}")
            }  
           when { anyOf { branch 'develop';  branch 'stage'; branch 'master' } } 
           steps{
               //Manejo del status code de este stage
                script{
                    echo "Estatus Code Stage Anterior(Stop App): ${statusCodeStop}";
                    if( statusCodeStop == 'success' ){
                        sshCommand remote: remote, command: "cd  ${domainWlBirc} && . ./setDomainEnv.sh ENV && java weblogic.Deployer -adminurl $urlWlBirc -username ${WEBLOGIC_CREDENTIAL_USR} -password ${WEBLOGIC_CREDENTIAL_PSW} -undeploy -name ${artifactNameWlBirc} -targets ${clusterWlBirc} -usenonexclusivelock -graceful -ignoresessions"
                    } else {
                        ///validar que en el pipe line no salga en verde 
                        echo "Sin artefacto para hacer undeploy"
                    }
                }
            }
            post {
               success {
                    println "Stage Undeploy <<<<<< success >>>>>>"
                    script{
                        if( statusCodeStop == 'success' ){
                            statusCodeUndeploy='success';
                        }else{
                            statusCodeUndeploy='failure';
                        }
                    }
                }
                unstable {
                    script{
                        statusCodeUndeploy='unstable';
                    } 
                    println "Stage Undeploy <<<<<< unstable >>>>>>"
               }
               failure {
                    println "Stage Undeploy <<<<<< failure >>>>>>"   
                    script{
                        if( statusCodeStop == 'success' ){
                            echo "Start App";
                            sshCommand remote: remote, sudo:true, command:"sh ${domainWlBirc}/setDomainEnv.sh ENV && java weblogic.Deployer -adminurl ${urlWlBirc} -username ${WEBLOGIC_CREDENTIAL_USR} -password ${WEBLOGIC_CREDENTIAL_PSW} -start -name ${artifactNameWlBirc}"
                            
                            autoCancelled = true
                            error('Error al hacer undeploy se inicia de nuevo el artefacto')
                        }    
                        statusCodeUndeploy='failure';
                    }
                }
            }              
        }                   
        /*stage('Deploy'){      
            agent {
                label 'master' 
            }
            environment {
                WEBLOGIC_CREDENTIAL = credentials("${idUserANDPassWlBirc}")
            }  
            when { anyOf { branch 'develop';  branch 'stage'; branch 'master' } } 
            steps{
                script{
                    if( (statusCodeStop == 'success' && statusCodeUndeploy == 'success') || statusCodeStop != 'success' ){
                        sshCommand remote: remote, sudo:true, command:"test -f ${pathwlBirc}/DeploysTemp/${BRANCH_NAME} || sudo mkdir -p ${pathwlBirc}/DeploysTemp/${BRANCH_NAME} && sudo chown -R wlogic12c:oinstall ${pathwlBirc}/"                                
                        sshCommand remote: remote, sudo:true, command:"mv /home/devops/applications/${projectName}/DeploysTemp/${BRANCH_NAME}/${artifactNameWlBirc}.${extension} ${pathwlBirc}/DeploysTemp/${BRANCH_NAME}"
                        sshCommand remote: remote, sudo:true, command:"chown  wlogic12c:oinstall ${pathwlBirc}/DeploysTemp/${BRANCH_NAME}/${artifactNameWlBirc}.${extension}"
                        sshCommand remote: remote, command:"cd ${domainWlBirc} && . ./setDomainEnv.sh ENV && java weblogic.Deployer -adminurl ${urlWlBirc} -username ${WEBLOGIC_CREDENTIAL_USR} -password ${WEBLOGIC_CREDENTIAL_PSW} -deploy -source ${pathwlBirc}/DeploysTemp/${BRANCH_NAME}/${artifactNameWlBirc}.${extension} -targets ${clusterWlBirc} -usenonexclusivelock"
                    }
                }
            }
            post {
                success {
                    println "Stage Deploy <<<<<< success >>>>>>"
                    script{
                        statusCode='success';
                    }    

                    echo "backup ";
                    ///validar la existenia de un artefacto al cual se le deva crear un backups
                    sshCommand remote: remote, sudo: true, command:"test -f ${pathwlBirc}/Deploy/${JOB_BASE_NAME}/${artifactNameWlBirc}.${extension} && sudo mv ${pathwlBirc}/Deploy/${JOB_BASE_NAME}/${artifactNameWlBirc}.${extension} ${pathwlBirc}/DeploysHistory/${JOB_BASE_NAME}/${artifactNameWlBirc}_`date +\"%Y-%m-%d-%Y_%H:%M\"`.${extension} || echo \"No se encontro artefacto para realizar backup\""

                    sshCommand remote: remote, sudo: true, command:"mv ${pathwlBirc}/DeploysTemp/${JOB_BASE_NAME}/${artifactNameWlBirc}.${extension}  ${pathwlBirc}/Deploy/${JOB_BASE_NAME}"
                    
                    sshCommand remote: remote, sudo: true, command:"rm -rf ${pathwlBirc}/DeploysTemp/${JOB_BASE_NAME}/${artifactNameWlBirc}.${extension}"
                }
                unstable {
                    println "Stage Deploy <<<<<< unstable >>>>>>"
                    script{
                        statusCode='unstable';
                    }
                }
                failure {
                    println "Stage Deploy <<<<<< failure >>>>>>"
                
                    script{
                        if( statusCodeStop == 'success' && statusCodeUndeploy == 'success' ){

                            echo "2. desplegar de la carpeta deploy";
                            sshCommand remote: remote, command:"cd ${domainWlBirc} && . ./setDomainEnv.sh ENV && java weblogic.Deployer -adminurl ${urlWlBirc} -username ${WEBLOGIC_CREDENTIAL_USR} -password ${WEBLOGIC_CREDENTIAL_PSW} -deploy -source ${pathwlBirc}/Deploy/${JOB_BASE_NAME}/${artifactNameWlBirc}.${extension} -targets ${clusterWlBirc} -usenonexclusivelock"
                            
                            //validar la necesidad de realizar el start
                            //echo "3. start a la aplicación";
                            //sshCommand remote: remote, command:"cd ${domainWlBirc} && . ./setDomainEnv.sh ENV && java weblogic.Deployer -adminurl ${urlWlBirc} -username ${WEBLOGIC_CREDENTIAL_USR} -password ${WEBLOGIC_CREDENTIAL_PSW} -start -name ${artifactNameWlBirc}"
                            
                        }else if( statusCodeStop == 'success' && statusCodeUndeploy != 'success' ){
                            echo "No se pudo desplegar verificar que el ambiente se encuentre estable con la version anterior";
                        
                        }else if( statusCodeStop != 'success'){
                            echo "No se pudo desplegar, por favor verificar por que no se encontro un artefacto inicial para restaurar";
                        }
                        
                        statusCode='failure';
                    }
                }
            }                
        }*/
        stage('shutdown cluster'){      
            agent {
                label 'master' 
            }
            environment {
                WEBLOGIC_CREDENTIAL = credentials("${idUserANDPassWlBirc}")
            }  
            when { anyOf { branch 'develop'; branch 'stage'; branch 'master' } } 
            steps{
                sh """
                    rm -rf shutdown.py
                    
                    touch shutdown.py
                    echo 'print("Conección con el servidor ${clusterWlBirc}")' >> shutdown.py 
                    echo 'connect("${WEBLOGIC_CREDENTIAL_USR}","${WEBLOGIC_CREDENTIAL_PSW}","${urlWlBirc}")' >> shutdown.py
                    echo 'print("shutdown: ${clusterWlBirc}")' >> shutdown.py
                    echo 'shutdown("${clusterWlBirc}")' >> shutdown.py
                    echo 'print("state: ${clusterWlBirc}")' >> shutdown.py
                    echo 'state("${clusterWlBirc}")' >> shutdown.py
                """

                sshPut remote: remote, from: "shutdown.py", into: "/home/devops/applications/${projectName}/DeploysTemp/"

                sshCommand remote: remote, command: "cd ${domainWlBirc} && . ./setDomainEnv.sh ENV && java weblogic.WLST  /home/devops/applications/${projectName}/DeploysTemp/shutdown.py"
                
                sshCommand remote: remote, command: "rm /home/devops/applications/${projectName}/DeploysTemp/shutdown.py"

                sh """
                    rm -rf shutdown.py
                """
            }
        }
        stage('start cluster'){      
            agent {
                label 'master' 
            }
            environment {
                WEBLOGIC_CREDENTIAL = credentials("${idUserANDPassWlBirc}")
            }  
            when { anyOf { branch 'develop'; branch 'stage'; branch 'master' } }
            steps{
                sh """
                    rm -rf startCluster.py
                    
                    touch startCluster.py
                    echo 'print("Conección con el servidor ${clusterWlBirc}")' >> startCluster.py 
                    echo 'connect("${WEBLOGIC_CREDENTIAL_USR}","${WEBLOGIC_CREDENTIAL_PSW}","${urlWlBirc}")' >> startCluster.py
                    echo 'print("deploy  app in ${clusterWlBirc}")' >> startCluster.py 
                    echo 'deploy("${artifactNameWlBirc}", "${pathwlBirc}/Deploy/${JOB_BASE_NAME}/${artifactNameWlBirc}.${extension}", "${clusterWlBirc}")' >> startCluster.py
                    echo 'print("start: ${clusterWlBirc}")' >> startCluster.py
                    echo 'start("${clusterWlBirc}")' >> startCluster.py
                    echo 'print("state: ${clusterWlBirc}")' >> startCluster.py
                    echo 'state("${clusterWlBirc}")' >> startCluster.py
                """

                sshPut remote: remote, from: "startCluster.py", into: "/home/devops/applications/${projectName}/DeploysTemp/"

                sshCommand remote: remote, command: "cd ${domainWlBirc} && . ./setDomainEnv.sh ENV && java weblogic.WLST  /home/devops/applications/${projectName}/DeploysTemp/startCluster.py"
                
                sshCommand remote: remote, command: "rm /home/devops/applications/${projectName}/DeploysTemp/startCluster.py"

                sh """
                    rm -rf startCluster.py
                """
            }
             post {
                success {
                    println "Stage Deploy <<<<<< success >>>>>>"
                    script{
                        statusCode='success';
                    }    

                    echo "backup ";
                    ///validar la existenia de un artefacto al cual se le deva crear un backups
                    sshCommand remote: remote, sudo: true, command:"test -f ${pathwlBirc}/Deploy/${JOB_BASE_NAME}/${artifactNameWlBirc}.${extension} && sudo mv ${pathwlBirc}/Deploy/${JOB_BASE_NAME}/${artifactNameWlBirc}.${extension} ${pathwlBirc}/DeploysHistory/${JOB_BASE_NAME}/${artifactNameWlBirc}_`date +\"%Y-%m-%d-%Y_%H:%M\"`.${extension} || echo \"No se encontro artefacto para realizar backup\""

                    sshCommand remote: remote, sudo: true, command:"mv ${pathwlBirc}/DeploysTemp/${JOB_BASE_NAME}/${artifactNameWlBirc}.${extension}  ${pathwlBirc}/Deploy/${JOB_BASE_NAME}"
                    
                    sshCommand remote: remote, sudo: true, command:"rm -rf ${pathwlBirc}/DeploysTemp/${JOB_BASE_NAME}/${artifactNameWlBirc}.${extension}"
                }
                unstable {
                    println "Stage Deploy <<<<<< unstable >>>>>>"
                    script{
                        statusCode='unstable';
                    }
                }
                failure {
                    println "Stage Deploy <<<<<< failure >>>>>>"
                
                    script{
                        if( statusCodeStop == 'success' && statusCodeUndeploy == 'success' ){

                            echo "2. desplegar de la carpeta deploy";
                            sshCommand remote: remote, command:"cd ${domainWlBirc} && . ./setDomainEnv.sh ENV && java weblogic.Deployer -adminurl ${urlWlBirc} -username ${WEBLOGIC_CREDENTIAL_USR} -password ${WEBLOGIC_CREDENTIAL_PSW} -deploy -source ${pathwlBirc}/Deploy/${JOB_BASE_NAME}/${artifactNameWlBirc}.${extension} -targets ${clusterWlBirc} -usenonexclusivelock"
                            
                            //validar la necesidad de realizar el start
                            //echo "3. start a la aplicación";
                            //sshCommand remote: remote, command:"cd ${domainWlBirc} && . ./setDomainEnv.sh ENV && java weblogic.Deployer -adminurl ${urlWlBirc} -username ${WEBLOGIC_CREDENTIAL_USR} -password ${WEBLOGIC_CREDENTIAL_PSW} -start -name ${artifactNameWlBirc}"
                            
                        }else if( statusCodeStop == 'success' && statusCodeUndeploy != 'success' ){
                            echo "No se pudo desplegar verificar que el ambiente se encuentre estable con la version anterior";
                        
                        }else if( statusCodeStop != 'success'){
                            echo "No se pudo desplegar, por favor verificar por que no se encontro un artefacto inicial para restaurar";
                        }
                        
                        statusCode='failure';
                    }
                }
            }     
        } 
    }
}
