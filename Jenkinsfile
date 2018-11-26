pipeline {
    agent any
    environment {
        DISCORD_WEBHOOK = credentials('metallurgy-webhook')
    }
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
                archiveArtifacts 'build/libs/*.jar'
                $DISCORD_WEBHOOK
            }
        }
    }
}