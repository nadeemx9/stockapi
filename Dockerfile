#FROM openjdk:17
#EXPOSE 8080
#ADD target/stockapi-github-action.jar stockapi-github-action.jar
#ENTRYPOINT ["java","-jar","/stockapi-github-action.jar"]

FROM openjdk:17

# Copy the wait-for-it script
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Add the JAR file
ADD target/stockapi-github-action.jar stockapi-github-action.jar

# Entrypoint with wait-for-it script
ENTRYPOINT ["/wait-for-it.sh", "db:5432", "--", "java", "-jar", "/stockapi-github-action.jar"]

