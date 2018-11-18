#mvn clean package -DskipTests=true && \
docker build -t vitr/otusjavaee .
docker rm -f otusjavaee || true
docker run -d --name otusjavaee -p 8080:8080 vitr/otusjavaee

#docker-compose up -d --force-recreate --build