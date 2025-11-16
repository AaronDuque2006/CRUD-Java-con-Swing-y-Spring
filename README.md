## Requisitos

- JDK de java en la version 21+.
- MySQL.
- IDE para Java.

Debes Crear una base de datos llamada **"zona_fit_db"** en el archivo llamado **"aplication. properties"** debes poner el usuario de la base de datos al igual que su contraseña, en mi caso use "root" para ambas respectivamente.

La base de datos tendra una tabla llamada "clientes" con los siguientes campos.

```
CREATE TABLE cliente (
    id INT(20) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    membresia INT(11) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
```
## Ejecutar el Programa

Debes ir al archivo llamado **"ZonaFitAplicationSwing.class"** y ejecutar el metodo main.


> Nota: Al clonar el repositorio asegura que las dependencias se instalen bien, si hay algun fallo, ve al archivo pom.xml al icono que es una "m", luego al icono que son unas flechas que forman un circulo. Instala el plugin de Lombok para evitar algun inconveniente. 
