# Imagen base Java 21
FROM eclipse-temurin:21-jre

# Directorio de trabajo
WORKDIR /app

# Copiamos el jar
COPY target/*.jar app.jar

# Puerto (Cloud Run lo inyecta)
EXPOSE 8080

# Ejecutar la app
ENTRYPOINT ["java","-jar","/app/app.jar"]
