# Sistema de Gestión de Inventario - Indumentaria AS
**Materia:** Lenguajes de Programación II

**Profesores:** Francisco Paredes / Axel Ruiz Valdez

**Alumnos:** Roberto Daniel Sanchez, Mariana Mercado, Denis Flores

## 1. Descripción del Proyecto
Este proyecto es el backend de una API REST para gestionar el inventario, los proveedores y los usuarios del negocio "Indumentaria AS". El sistema está desarrollado con Java 21 y Spring Boot, utilizando una arquitectura multicapa (Controller, Service, Repository, Mapper, DTO y Entity) para garantizar un código limpio y mantenible.

## 2. Funcionalidades Principales (CRUD)
El sistema expone endpoints REST para manejar las siguientes operaciones:
- **Prendas:** Crear, listar catálogo completo, buscar por ID, modificar datos o stock, y eliminar prendas.
- **Usuarios:** Registro de nuevos empleados/administradores y listado del personal.
- **Proveedores:** Estructura preparada para el registro y vinculación de fabricantes.

## 3. Manejo de Errores y Validaciones
- El sistema cuenta con validaciones de entrada de datos (ej. `@NotBlank`, `@Positive`) que retornan un `400 Bad Request` si se intenta guardar una prenda con nombre vacío o precio negativo.
- Se implementó una excepción personalizada (`RecursoNoEncontradoException`) que intercepta las búsquedas de IDs inexistentes, devolviendo un código `404 Not Found` controlado.

## 4. Instrucciones para ejecutar la Base de Datos (MySQL)
Para que el proyecto se ejecute correctamente de forma local, es necesario contar con un motor MySQL (por ejemplo, a través de XAMPP).
1. Iniciar el servicio de MySQL.
2. Crear una base de datos vacía llamada `lpii`.
3. Ejecutar la aplicación Spring Boot. Hibernate se encargará automáticamente de crear las tablas (`prendas`, `proveedores`, `usuarios`) gracias a la propiedad `spring.jpa.hibernate.ddl-auto=update` configurada en el `application.properties`.

## 5. Colección de Postman
Las pruebas de funcionamiento de la API están disponibles en el archivo de colección de Postman adjunto en este repositorio.

## 6. Correcciones y Mejoras Implementadas (Feedback 1° Entregable)
Para alinear el proyecto a los estándares solicitados por la cátedra, se aplicaron las siguientes mejoras sobre la base inicial:
- **Relaciones:** Se completó el CRUD de `Proveedor`. Se integró `proveedorId` al DTO de `Prenda` y se validó su existencia previa en el Service. Se agregó el endpoint relacional `GET /api/proveedores/{id}/prendas`. Se ajustó la entidad para evitar eliminaciones accidentales en cascada.
- **Validaciones:** Se implementó la anotación `@Valid` en los métodos POST y PUT de los controladores correspondientes.
- **Respuestas HTTP:** Se reemplazaron retornos genéricos por `ResponseEntity` con los códigos de estado adecuados (`201 Created`, `204 No Content`), separando los DTOs de entrada y salida.
- **Manejo de Errores Global:** Se expandió el `@RestControllerAdvice` con un DTO de error estructurado (`ErrorResponseDTO`), capturando errores de validación, recursos no encontrados y violaciones de integridad referencial (código `409 Conflict` al intentar borrar un proveedor con prendas asociadas).
- **Organización y Arquitectura:** Se reemplazó el uso de `@Autowired` por inyección de dependencias mediante constructor. Se estandarizaron las rutas REST eliminando sufijos (`/add`, `/all`, `/buscar`). Se limpió el archivo `pom.xml` eliminando dependencias duplicadas y se borró el archivo obsoleto `Main.java`.

## 7. Segundo Entregable: Seguridad y Autenticación
- **Spring Security:** Integración de seguridad con esquema de autenticación **Basic Auth**.
- **Registro Público:** Creación de un endpoint para registrar usuarios que asigna automáticamente el rol básico por defecto (`ROLE_USER`).
- **Encriptación:** Implementación de `BCryptPasswordEncoder` para encriptar las contraseñas en la base de datos de forma segura.
- **Restricción de Operaciones (Roles):** Se protegió el endpoint de eliminación de usuarios (DELETE). Esta acción está permitida exclusivamente para cuentas que posean el rol de administrador (`ROLE_ADMIN`).

### Pruebas y Credenciales (Basic Auth)
Para probar los endpoints protegidos en Postman (pestaña "Authorization" -> "Basic Auth"), utilizar las siguientes credenciales:
- **Cuenta Administrador (ROLE_ADMIN):**
    - **Email:** `daniel@indumentaria.com`
    - **Password:** `micontrasenasegura`
    - *Nota técnica para la evaluación:* Para generar un administrador de prueba desde cero, se debe registrar el usuario vía el endpoint público (POST) y luego modificar manualmente la columna `rol` al valor `ROLE_ADMIN` directamente en el motor de base de datos.