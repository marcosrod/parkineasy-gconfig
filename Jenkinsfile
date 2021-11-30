pipeline {
  agent any
  tools {
    maven '3.8.4'
  }
  stages {
    stage("build") {
      steps {
        sh 'mvn -v'
      }
    }
    stage("deploy") {
      steps {
        echo 'deploy'
      }
    }
  }
}
