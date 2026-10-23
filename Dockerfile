# ---------------------------------------------------
# Etapa 1: Base con Java 25 (Gradle Wrapper)
# ---------------------------------------------------
FROM eclipse-temurin:25-jdk AS base
WORKDIR /app

# Copiar archivos del wrapper de Gradle y de configuración de dependencias
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Otorgar permisos de ejecución al script gradlew
RUN chmod +x gradlew

# Descargar dependencias para aprovechar el caché de capas de Docker
RUN ./gradlew dependencies --no-daemon || true

# Copiar el código fuente
COPY src src

# ---------------------------------------------------
# Etapa 2: Entorno de Desarrollo (Dev)
# ---------------------------------------------------
FROM base AS dev
ENV SPRING_PROFILES_ACTIVE=dev
EXPOSE 8888
# Ejecuta la aplicación mediante Gradle directamente (ideal para desarrollo)
CMD ["./gradlew", "bootRun", "--no-daemon"]

# ---------------------------------------------------
# Etapa 3: Compilación / Build para Producción
# ---------------------------------------------------
FROM base AS builder
RUN ./gradlew bootJar --no-daemon

# ---------------------------------------------------
# Etapa 4: Entorno de Producción (Prod)
# ---------------------------------------------------
FROM eclipse-temurin:25-jre AS prod
WORKDIR /app

# Copiar únicamente el JAR generado en la etapa anterior
COPY --from=builder /app/build/libs/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 8888

# Ejecución liviana y directa del ejecutable
ENTRYPOINT ["java", "-jar", "app.jar"]