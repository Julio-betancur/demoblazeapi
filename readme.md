# Proyecto de Automatización de Servicios REST - DemoBlaze

## Descripción del Proyecto
Este proyecto de automatización realiza pruebas sobre los servicios REST de la página [DemoBlaze](https://www.demoblaze.com/), específicamente en las funcionalidades de registro (signup) y login. Las pruebas cubren los siguientes casos:

1. **Crear un nuevo usuario** en signup.
2. **Intentar crear un usuario ya existente** en signup.
3. **Usuario y password correcto** en login.
4. **Usuario y password incorrecto** en login.

## Prerrequisitos
Para ejecutar esta automatización en un entorno local, asegúrate de tener instalados los siguientes componentes:

- JDK 17 o superior.
- IntelliJ IDEA.
- Gradle instalado y configurado.
- Plugins de IntelliJ: **Cucumber for Java** y **Lombok**.

## Configuración del Proyecto
Este proyecto utiliza el lenguaje de programación **Java** y el marco de automatización **SerenityBDD** con el patrón de diseño **Screenplay**. Las dependencias se gestionan con **Gradle**.

### Dependencias Principales
Las principales dependencias del proyecto están especificadas en el archivo `build.gradle`, incluyendo:

- Serenity Core
- Serenity-rest
- Serenity Cucumber
- Serenity Screenplay y Screenplay WebDriver
- Lombok

### Configuración de SerenityBDD
La configuración de la URL y otras propiedades de la prueba están alojadas en el archivo `serenity.conf`.

## Ejecución de las Pruebas
Para ejecutar las pruebas, sigue estos pasos:

1. Clona el repositorio del proyecto en tu máquina local.
2. Abre el proyecto en IntelliJ IDEA.
3. Asegúrate de que JDK 17 o superior esté configurado en tu entorno de desarrollo.
4. Instala los plugins **Cucumber for Java** y **Lombok** en IntelliJ IDEA.
5. Ejecuta el siguiente comando en la terminal de IntelliJ para iniciar las pruebas:

    ```sh
    gradle clean test
    ```



## Contacto
Para cualquier pregunta o asistencia adicional, puedes contactar a juliobetancur2@gmail.com
