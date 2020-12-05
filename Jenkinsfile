
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
        sh 'kubectl create deployment hello-minikube --image=hailaliya/test-repo:104'
        sh 'kubectl expose deployment hello-minikube --type=NodePort --port=8090'
      }
    }
  }
}
