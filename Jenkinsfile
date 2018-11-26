pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh "./gradlew build"
                sh "./gradlew check"
            }
        }
        stage('deploy') {
            steps {
                archiveArtifacts '*.jar'
            }
        }
    }
}