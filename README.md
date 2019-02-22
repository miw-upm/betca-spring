# Proyecto para docencia Spring 5
#### Back-end con Tecnologías de Código Abierto (SPRING)
#### [Máster en Ingeniería Web por la U.P.M.](http://miw.etsisi.upm.es)

[![Build Status](https://travis-ci.org/miw-upm/betca-spring5.svg?branch=develop)](https://travis-ci.org/miw-upm/betca-spring5)

> Proyecto **DEMO** de Back-end con la tecnología Spring5.  

### Tecnologías necesarias
* Java
* Maven
* Spring
* JPA
* Mongodb

### Clonar en repositorio en tu equipo mediante consola:
1. Situarse en una carpeta raíz donde se encuentran los proyectos, mediante la consola:  
 **>cd %ruta-de-la-carpeta%**
1. Clonar el repositorio, se visualizará el contenido de la rama por defecto:  
 **>git clone https://github.com/miw-upm/betca-spring5**
 
### Importar el proyecto mediante IntelliJ IDEA
1. **Import Project**, y seleccionar la carpeta del proyecto.
1. Marcar **Create Project from external model**, elegir **Maven**.
1. **Next** … **Finish**.

### Instalar MondoDB
1. Web: https://docs.mongodb.com/getting-started/shell/installation/
1. Instalar el `*.zip`, version __Community Server__   
   * Descomprimir y crear la carpeta `%path%/data/db`
   * Arrancar el servidor: `%path%/bin> mongod --dbpath %path%/data/db`
   * Se puede arrancar la consola: `%path%/bin> mongo`