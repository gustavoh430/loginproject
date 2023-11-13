node {
    checkout scm

    docker.withRegistry('https://registry.example.com', 'dockerHub') {

        def customImage = docker.build("gustavoh430/loginappjenkins")

        /* Push the container to the custom Registry */
        customImage.push()
    }
}
