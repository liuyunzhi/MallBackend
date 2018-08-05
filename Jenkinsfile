pipeline {
    agent any

    stages {
        stage('Dev') {
            steps {
                sh './gradlew build'
            }
        }
        stage('CheckStyle') {
            steps {
                sh './gradlew checkstyleMain'
            }
        }
    }
}