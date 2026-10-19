# Sistema de Gestión de Inventario - Indumentaria AS
**Materia:** Lenguajes de Programación II
**Profesor:** Francisco Paredes / Axel Ruiz Valdez
**Alumno:** Roberto Daniel Sanchez

## 1. Descripción del Proyecto
Este proyecto es el backend de una API REST para gestionar el inventario, los proveedores y los usuarios del negocio "Indumentaria AS".
El sistema está desarrollado con Java 21 y Spring Boot, utilizando una arquitectura multicapa (Controller, Service, Repository, Mapper, DTO y Entity) para garantizar un código limpio y mantenible.

## 2. Funcionalidades Principales (CRUD)
El sistema expone endpoints REST para manejar las siguientes operaciones:
* **Prendas:** Crear, listar catálogo completo, buscar por ID, modificar datos o stock, y eliminar prendas.
* **Usuarios:** Registro de nuevos empleados/administradores y listado del personal.
* **Proveedores:** Estructura preparada para el registro y vinculación de fabricantes.

## 3. Manejo de Errores y Validaciones
* El sistema cuenta con validaciones de entrada de datos (ej. `@NotBlank`, `@Positive`) que retornan un `400 Bad Request` si se intenta guardar una prenda con nombre vacío o precio negativo.
* Se implementó una excepción personalizada (`RecursoNoEncontradoException`) que intercepta las búsquedas de IDs inexistentes, devolviendo un código `404 Not Found` controlado.

## 4. Instrucciones para ejecutar la Base de Datos (MySQL)
Para que el proyecto se ejecute correctamente de forma local, es necesario contar con un motor MySQL (por ejemplo, a través de XAMPP).
1. Iniciar el servicio de MySQL.
2. Crear una base de datos vacía llamada `lpii`.
3. Ejecutar la aplicación Spring Boot. Hibernate se encargará automáticamente de crear las tablas (`prendas`, `proveedores`, `usuarios`) gracias a la propiedad `spring.jpa.hibernate.ddl-auto=update` configurada en el `application.properties`.

## 5. Colección de Postman
Las pruebas de funcionamiento de la API están disponibles en el archivo de colección de Postman adjunto en este repositorio.