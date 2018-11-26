pipeline {
    agent any
    environment {
        DISCORD_WEBHOOK = credentials('discord_webhook')
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
                environment {
                    DISCORD_WEBHOOK = credentials('discord_webhook')
            }
            steps {
                bat 'gradlew assemble'
                archiveArtifacts 'build/libs/*.jar'
                discordSend description: 'Metallurgy Pipeline Build', footer: 'this is a beautiful footer', link: env.BUILD_URL, result: currentBuild.currentResult, title: 'Metallurgy-4-Reforged', webhookURL: '$DISCORD_WEBHOOK'
            }
        }
    }
}