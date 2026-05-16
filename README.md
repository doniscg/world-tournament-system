# World Tournament System

Prueba técnica fullstack desarrollada para la gestión de equipos y grupos de un torneo mundial.

El sistema permite la creación de equipos, organización de grupos y asignación automática de equipos a cada grupo.

---

## Tecnologías utilizadas

### Backend
- Java
- Spring Boot
- PostgreSQL

### Frontend
- Angular
- TypeScript
- Bootstrap

---

## Funcionalidades

- Gestión de grupos del mundial
- Gestión de equipos
- Asignación de equipos a grupos
- Consumo de API REST
- Validaciones en frontend y backend

---

# Configuración de Base de Datos

El proyecto utiliza PostgreSQL.

Debe crearse una base de datos con el nombre de preferencia.

Ejemplo:

```sql
CREATE DATABASE mundial_db;
```

Luego configurar las credenciales en:

```text
backend/src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mundial_db?currentSchema=mundial_db
spring.datasource.username=postgres
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
```

---

# Ejecución del Proyecto

## Backend

```bash
cd mundial-backend
mvn spring-boot:run
```

---

## Frontend

```bash
cd mundial-frontend
npm install
ng serve
```

---

# 🌐 Demo en producción

El sistema ya se encuentra desplegado en un servidor en la nube utilizando AWS Lightsail.

👉 Frontend en producción:

https://dxc-program.duckdns.org/mundial-frontend/

---

## Notas

- Backend y frontend están desplegados en un entorno real.
- El sistema está listo para pruebas funcionales.
- Proyecto orientado a evaluación técnica.

---

# Autor

Desarrollado como prueba técnica fullstack.