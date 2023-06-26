FROM eclipse-temurin:17-jdk-jammy

COPY . /app

WORKDIR /app

RUN javac /app/Main.java

CMD ["java", "Main"]
