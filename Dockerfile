FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Add this for better memory management
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Add this to use environment variables
CMD java $JAVA_OPTS -jar app.jar