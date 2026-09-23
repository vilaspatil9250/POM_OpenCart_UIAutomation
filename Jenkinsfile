pipeline 
{
    agent any
    
    tools{
        maven 'maven'
        }

    stages 
    {
      stage('Build') 
        {
            steps
            {
                  echo("Build is successful")
            }
        }
        
      stage("Deploy to Dev env"){
            steps{
                echo("deploy to Dev env done")
            }
        }
        
        stage('Unit Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/vilaspatil9250/POM_OpenCart_UIAutomation.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_singletc.xml -Denv=dev"
                    
                }
            }
        }    
        
       stage('Publish Allure Reports - Dev env') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
           
        stage("Deploy to QA env"){
            steps{
                echo("deploy to QA env done")
            }
        }
                    
        stage('Regression Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/vilaspatil9250/POM_OpenCart_UIAutomation.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml -Denv=qa"
                    
                }
            }
        }
            
       stage('Publish Allure Reports - QA env') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        
        stage('Publish ChainTest HTML Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'target/chaintest', 
                                  reportFiles: 'Index.html', 
                                  reportName: 'HTML Regression ChainTest Report', 
                                  reportTitles: ''])
            }
        }
        
        stage("Deploy to UAT env"){
            steps{
                echo("deploy to UAT env")
            }
        }
        
        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/vilaspatil9250/POM_OpenCart_UIAutomation.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_sanity.xml -Denv=uat"
                    
                }
            }
        }
        
        
        
        stage('Publish sanity ChainTest Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'target/chaintest', 
                                  reportFiles: 'Index.html', 
                                  reportName: 'HTML Sanity ChainTest Report', 
                                  reportTitles: ''])
            }
        }
        
        
        stage("Deploy to PROD env"){
            steps{
                echo("deploy to PROD env")
            }
        }


        stage('Sanity Automation Test on PROD env') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/vilaspatil9250/POM_OpenCart_UIAutomation.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_sanity.xml -Denv=prod"
                    
                }
            }
        }
        
       stage('Publish Allure Reports - PROD env') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        
    }
}