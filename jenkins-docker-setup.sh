# 1. Stop your existing jenkins container (replace 'jenkins' with your container name)
docker stop jenkins
docker rm jenkins

# 2. Restart it with the Docker socket mapped and root permissions
docker run -d -p 8080:8080 -p 50000:50000 \
  -v jenkins_home:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -u root \
  --name jenkins jenkins/jenkins:latest


# 1. Access your Jenkins container terminal as root user
docker exec -u 0 -it jenkins apt-get update

# 2. Install the Git package inside the container
docker exec -u 0 -it jenkins apt-get install -y git

apt-get install -y docker.io
apt-get install -y docker-compose-plugin
apt-get update
apt-get install -y curl

curl -SL https://github.com/docker/compose/releases/latest/download/docker-compose-linux-x86_64 \
-o /usr/local/lib/docker/cli-plugins/docker-compose
chmod +x /usr/local/lib/docker/cli-plugins/docker-compose
curl -SL https://github.com/docker/buildx/releases/download/v0.17.1/buildx-v0.17.1.linux-amd64 \
-o /usr/local/lib/docker/cli-plugins/docker-buildx

docker exec -it jenkins docker --version
docker exec -it jenkins docker compose version
docker exec -it jenkins docker compose version

docker-compose.yml
    user: root
    volumes:
      - jenkins_home:/var/jenkins_home
      - /var/run/docker.sock:/var/run/docker.sock
