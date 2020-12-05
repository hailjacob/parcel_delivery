
pipeline {
  environment {
    registry = 'hailaliya/test-repo'
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  agent any
  stages {
    stage('Deploy to kubernetes') {
      steps{
        script{
        sh 'kubectl apply -f deployment.yaml'
        }
      }
    }
  }
}
