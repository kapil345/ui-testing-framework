# java is already installed on this image
FROM bellsoft/liberica-openjdk-alpine:21

# workspace
WORKDIR /home/selenium-docker

# Add the required files
ADD target/docker-resources ./