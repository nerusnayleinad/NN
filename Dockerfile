FROM alpine:3.24.1

RUN apk update && apk add \
    openjdk25-jdk \
    maven

ADD ./api /code

WORKDIR /code

ENTRYPOINT ["mvn"]

CMD ["spring-boot:run"]