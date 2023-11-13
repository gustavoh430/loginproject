node {
    checkout scm

    docker.withRegistry('https://registry.example.com', 'dockerHub') {

        def customImage = docker.build("gustavoh430/loginappjenkins")

        /* Push the container to the custom Registry */
        customImage.push()
    }
    stage('Test') {
            steps {
                sh './mvnw test'
                // bat '.\\mvnw test'
            }

            post {
                always {
                    junit '**/target/TEST-*.xml'
                }
            }
        }
    }

