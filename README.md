Proyecto solicitado desarrollado en **Java Swing + MySQL** para la gestión de usuarios.  
El sistema permite **registrar**, **iniciar sesión**, **visualizar**, **actualizar** y **eliminar** usuarios mediante una interfaz gráfica.

## Descripción

Este proyecto fue realizado como una práctica de escritorio utilizando **Java Swing** para la interfaz gráfica y **MySQL** para el almacenamiento de los datos.  
Su propósito principal es manejar el acceso de usuarios a través de un login y permitir la administración de registros dentro del sistema.

Además de la funcionalidad, se trabajó la parte visual para que la aplicación tuviera una interfaz más ordenada, moderna y consistente en todas sus ventanas.

## Funcionalidades

- Inicio de sesión de usuarios
- Registro de nuevos usuarios
- Validación de campos vacíos
- Validación de coincidencia de contraseñas
- Validación de usuario y correo duplicados
- Visualización de usuarios en una tabla
- Actualización de datos de usuarios
- Eliminación de usuarios
- Cierre de sesión

## Estructura del proyecto

El proyecto está organizado en los siguientes packages:

### `Modelo`
Contiene las clases que representan la estructura de los datos.

- `Cuenta`
- `Usuario`

### `data`
Contiene la conexión y el acceso a la base de datos.

- `Conexion`
- `UsuarioDAO`

### `vista`
Contiene todas las ventanas de la interfaz gráfica.

- `LoginVentana`
- `VentanaRegistro`
- `VentanaPrincipal`

### `WilmerProyecto4`
Contiene la clase principal del proyecto.

- `Main`

## Tecnologías utilizadas

- **Java**
- **Java Swing**
- **MySQL**
- **JDBC**
- **Eclipse IDE** *(o el entorno que hayas usado)*

## Base de datos

La base de datos utilizada en el proyecto es:

```sql
