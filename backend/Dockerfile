# ============================================
# Etapa 1: Compilar con Maven + JDK 21
# ============================================
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copiar Maven Wrapper y pom.xml primero (cachea dependencias)
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Dar permisos de ejecucion al wrapper
RUN chmod +x mvnw

# Descargar dependencias (capa cacheada si pom.xml no cambia)
RUN ./mvnw dependency:go-offline -B

# Copiar el codigo fuente
COPY src/ src/

# Compilar el JAR sin ejecutar tests
RUN ./mvnw package -DskipTests -B

# ============================================
# Etapa 2: Imagen ligera solo con JRE
# ============================================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar el JAR compilado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Puerto (Cloud Run lo inyecta via $PORT)
EXPOSE 8080

# Ejecutar la app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
