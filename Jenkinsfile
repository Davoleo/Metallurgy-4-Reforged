pipeline {
    agent any
    stages {
        stage('clean') {
            steps {
                sh "./gradlew clean"
            }
        }
        stage('build') {
            steps {
                sh "./gradlew build"
                sh "./gradlew check"
            }
        }
        stage('deploy') {
            steps {
                sh "./gradlew assemble"
            }
        }
    }
}