FROM openjdk:11
VOLUME /tmp
EXPOSE 8080 8081
ADD ./target/tui-backend-technical-test-1.0.0.jar tui-order-api.jar
ENTRYPOINT ["java","-jar","/tui-order-api.jar"]