pipeline {
  agent { label 'worker_node1' }
  stages {
    stage('Source') { // Get code
      steps {
        // get code from our Git repository
        git 'https://github.com/hailjacob/parcel_delivery'
      }
    }
    stage('Compile') { // Compile and do unit testing
      steps {
        // run Gradle to execute compile and unit testing
        sh 'gradle clean compileJava test'
      }
    }
  }
}
