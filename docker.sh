mvn clean package -DskipTests=true && \
#docker build -t vitr/otusjavaee .
#docker rm -f otusjavaee || true
#docker run -d --name -p 8080:8080 -add-host localhost:10.0.2.2 otusjavaee vitr/otusjavaee

docker-compose up -d --force-recreate --build