# API de Franquicias

## Requisitos
- Java 17
- Docker
- MySQL

## Pasos para ejecutar
1. Clonar el repositorio (https://github.com/Pulsebas/franquicia.git) rama main.
2. Configurar la base de datos en `application.properties`. Se usa Railway MySQL 
3. Ejecutar el proyecto con `mvn spring-boot:run`.
4. Opcional: Usar Docker con `docker build -t franquicias-api .` y `docker run -p 8080:8080 franquicias-api`.