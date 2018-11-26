pipeline {
    agent any
    stages {
        stage('clean') {
            steps {
                bat 'gradlew clean'
            }
        }
        stage('build') {
            steps {
                bat 'gradlew build'
                bat 'gradlew check'
            }
        }
        stage('deploy') {
            steps {
                bat 'gradlew assemble'
                archiveArtifacts '*.jar'
            }
        }
    }
}