# Start with a base image containing Java runtime (JDK 11 in this case)
FROM openjdk:21


# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar file to the container
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
