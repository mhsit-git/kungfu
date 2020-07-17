FROM openjdk:14.0.1-oracle
MAINTAINER meihua
ADD ./target/kungfu.jar kungfu.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","kungfu.jar"]