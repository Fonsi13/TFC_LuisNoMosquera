# TFC_LuisNoMosquera
Repositorio para el Trabajo de Fin de Ciclo de Luis Alfonso No Mosquera
> [!NOTE]
> Este proyecto está en fase de pruebas. Sigue estos pasos para configurar el entorno local.

1. **Prerrequisitos**
    - **IDE**:
      - IntelliJ IDEA Community Edition, yo estoy usando la versión 2024.3.4.1
      - Java 21

    - **Base de datos**:
      - MySQL
      - Gestor de BD: A elección propia, yo utilizo DBeaver.

2. **Configuración de la Base de Datos**
   - **Crear la BD**:
      - Ejecutar los scripts SQL ubicados en la carpeta 'SQL'
      - Primero ejecutar 'estructura_bd.sql' para crear el esqueleto de la base de datos
      - Después ejecutar 'data_bd.sql' para insertar dos usuarios creados

   - **Configurar credenciales**:
      - Asegúrate de que el archivo application.properties (en src/main/resources) tenga tus credenciales de MySQL:
      ```properties
        spring.datasource.username=tu_usuario
        spring.datasource.password=tu_contraseña
      ```

3. **Ejecutar el Proyecto**
   - **Abrir en IntelliJ**:
      - Clona el repositorio o descarga la carpeta 'snaplabs' y abre el proyecto

   - **Iniciar la aplicación**:
      - Ejecuta la clase principal (@SnapLabsApplication) desde el IDE
      
   - **Acceder**:
      - Escribe en tu navegador:
      [`http://localhost:8080/`](http://localhost:8080/)
