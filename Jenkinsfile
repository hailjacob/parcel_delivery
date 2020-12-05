
pipeline {
  environment {
    registry = 'hailaliya/test-repo'
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  agent any
  stages {
    stage('Cloning Git') {
      steps {
        git 'https://github.com/hailjacob/parcel_delivery.git'
      }
    }
     stage('Test') {
       steps {
           sh 'gradle clean test'
         }
    }
     stage('Build') {
       steps {
           sh 'gradle build'
         }
    }
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Pushing Image') {
      steps{
        script {
          docker.withRegistry('https://registry-1.docker.io/v2/', registryCredential) {
            dockerImage.push()
          }
        }
      }
    }
    stage('Deploy to kubernetes') {
      steps{
        sh 'kubectl create deployment hello-minikube --image=hailaliya/test-repo:104'
        sh 'kubectl expose deployment hello-minikube --type=NodePort --port=8090'
      }
    }
  }
}
