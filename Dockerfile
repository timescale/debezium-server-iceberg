FROM eclipse-temurin:21-jdk as builder
RUN apt-get -qq update && apt-get -qq install maven gradle unzip
COPY . /app
WORKDIR /app
#RUN --mount=type=bind,rw,target=/Users/dodd/src/github.com/apache/iceberg,dst=/src/github.com/apache/iceberg \
#    cd /src/github.com/apache/iceberg && ls -lah /src/github.com/apache/iceberg && ./gradlew -DallModules -DallVersions -DallVariants generatePomFile -x test
#RUN mvn package -Passembly -Dmaven.test.skip -nsu --quiet
RUN --mount=from=m2,rw,target=/Users/dodd/.m2,dst=/root/.m2 mvn package -Passembly -Dmaven.test.skip -nsu --quiet
RUN unzip /app/debezium-server-iceberg-dist/target/debezium-server-iceberg-dist*.zip -d appdist

FROM eclipse-temurin:21-jre
COPY --from=builder /app/appdist/debezium-server-iceberg/ /app/

WORKDIR /app
EXPOSE 8080 8083
VOLUME ["/app/conf", "/app/data"] 

ENTRYPOINT ["/app/run.sh"]