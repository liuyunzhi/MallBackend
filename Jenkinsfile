pipeline {
    agent any

    stages {
        stage('Dev') {
            steps {
                sh './gradlew bootRun'
            }
        }
        stage('CheckStyle') {
            steps {
                sh './gradlew checkstyleMain'
            }
        }
    }
}