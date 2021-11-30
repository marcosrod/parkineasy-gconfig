pipeline {
  agent any
  tools {
    maven 'Maven 3.8.4'
  }
  stages {
    stage("build") {
      steps {
        sh 'mvn clean package'
      }
    }
    stage("deploy") {
      steps {
        echo 'deploy'
      }
    }
  }
}
