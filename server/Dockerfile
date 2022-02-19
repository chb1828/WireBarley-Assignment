FROM openjdk:8-alpine

EXPOSE 8080

RUN mkdir -p /var/log/assignment
VOLUME ["/var/log/assignment"]

ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.chb.assignment.Application"]