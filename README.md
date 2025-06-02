# TFC_LuisNoMosquera

Repositorio para el Trabajo de Fin de Ciclo de Luis Alfonso No Mosquera

> [!NOTE]
> Este proyecto está en fase de pruebas. Sigue estos pasos para configurar el entorno local.

## 📌 Descripción

   **Snap Labs** es una plataforma para conectar a jugadores de **Marvel Snap** de todo el mundo.

   Donde puedes explorar los datos actualizados del juego, copiar y usar mazos creados por la comunidad e incluso subir tus propios mazos.
   
   Si también eres un artista, existe un apartado donde compartir arte personalizado de los personajes del juego.   

## ⚙️ Requisitos
   
   - [Docker Desktop](https://www.docker.com/products/docker-desktop/)

## 🚀 Pasos para ejecutar

   1. **Descargar imagen de Docker Hub y ejecutar contenedor**

   Abre una terminal en tu SO y ejecuta el siguiente comando:
   ```bash
   docker run -d --name snaplabs -p 8080:8080 luisno/snaplabs-image
   ```
   Esto descargará la imagen en tu ordenador, si no la tenías previamente descargada, y creará un nuevo contenedor donde se ejecutará la aplicación.
   
   El parámetro '-d' indica a la terminal que el contenedor se ejecute en segundo plano.
   
   El parámetro '--name snaplabs' asigna un nombre al contenedor para facilitar el manejo del contenedor.
   
   El parámetro '-p 8080:8080' indica el puerto donde va a ser accesible el contenedor.
   
   Por último, 'luisno/snaplabs-image' es el nombre de la imagen que se va a descargar desde el hub y contiene el código necesario para el despliegue de la web.

   2. **Acceso desde el navegador**
   
   Una vez desplegada la aplicación ya se puede acceder a ella desde el navegador de confianza, aunque recomiendo Microsoft Edge o uno basado en Chrome.
   Para ello, escribe en la barra superior de búsqueda:
   
   [`http://localhost:8080/`](http://localhost:8080/)
   
   Accederás al menú principal de la web.

   3. **Parar el contenedor**
   
   Si ya acabaste de usar la web y quieres parar la ejecución del contenedor, abre una nueva terminal, o usa la anterior si no la habías cerrado, y ejecuta el siguiente comando:
   ```bash
   docker stop snaplabs
   ```
   Si otro día quieres volver a acceder a la página web solo tienes que ejecutar:
   ```bash
   docker start snaplabs
   ```
## 🧰 Tecnologías utilizadas

   - Java
   - HTML/CSS/Js
   - Docker

## 👤 Autor

   Luis Alfonso No Mosquera

   Técnico Superior de Desarrollo de Aplicaciones Multiplataforma
   
   Estudiande de Ciclo Superior de Desarrollo de Aplicaciones Web en IES Fernando Wirtz
   
   📧 luisalfonsono@gmail.com  
   
   🔗 [LinkedIn](https://www.linkedin.com/in/luis-alfonso-no-mosquera-a18020329/)