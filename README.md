## [Máster en Ingeniería Web por la Universidad Politécnica de Madrid (miw-upm)](http://miw.etsisi.upm.es)
## Back-end con Tecnologías de Código Abierto (BETCA) (Spring)
> Este proyecto es un apoyo docente de la asignatura. Cada release liberada corresponde al código utilizado en clase del curso indicado

### Estado del código
[![Build Status](https://travis-ci.org/miw-upm/betca-spring.svg?branch=develop)](https://travis-ci.org/miw-upm/betca-spring)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=es.upm.miw%3Abetca-spring&metric=alert_status)](https://sonarcloud.io/dashboard?id=es.upm.miw%3Abetca-spring)

### Tecnologías necesarias
`Java` `Maven` `Spring-Boot` `Reactor` `WebFlux` `MondoDB` `JPA`

### :gear: Instalación del proyecto
1. Clonar el repositorio en tu equipo, **mediante consola**:
```sh
> cd <folder path>
> git clone https://github.com/miw-upm/betca-spring
```
2. Importar el proyecto mediante **IntelliJ IDEA**
   1. **Import Project**, y seleccionar la carpeta del proyecto.
   1. Marcar **Create Project from external model**, elegir **Maven**.
   1. **Next** … **Finish**.   
3. Instalar MondoDB
   1. Web: https://docs.mongodb.com/getting-started/shell/installation/
   1. Instalar el `*.zip`, version __Community Server__   
      * Descomprimir y crear la carpeta `%path%/data/db`
      * Arrancar el servidor: `%path%/bin> mongod --dbpath %path%/data/db`
      * Se puede arrancar la consola: `%path%/bin> mongo`

### :book: Diapositivas (/docs)
* [Diapositivas de BETCA-Spring](docs/miw-betca-diapositivas-spring.pdf).   

### :movie_camera: Videos (www.youtube.com/miw-upm)
* Lista de reproducción: **BETCA-Spring (Curso 2019-20). Back-end con Tecnologías de Código Abierto**

### :page_with_curl: Enunciado de la práctica
* Desarrollar una historia completa (Full-Stack: Angular-Spring) sobre la aplicacion TPV.
* Todo el software deberá estar en ingles.
* [Historias de Usuario.](https://github.com/miw-upm/betca-tpv-spring/wiki)

### :memo:Metodología de trabajo
* Se dispone de dos proyectos compartidos, uno para [Front-end: Angular](https://github.com/miw-upm/betca-tpv-angular/projects/2),
 y otro para el [Back-end: Spring](https://github.com/miw-upm/betca-tpv-spring/projects/1).
* Dividir la `Historia` en **tareas** mas pequeñas, cada tarea un **Issue#**. Asignaros la **issue#xx** y asociarle la etiqueta oportuna de la **Historia**.
Asignar el **Hito**. Justo antes de empezar una **tarea**, asignar la estimación de tiempo: **puntos**. Al finalizar una **tarea**, asignarle el tiempo consumido y cerrarla.
Utilizar un flujo de trabajo ramificado, visto en `IWVG: Ecosistema` o `APAW`. Acordaros de incluir `#?` en los mensajes de los commits.
* Como referencia podríamos tener entre 5 y 15 tareas.  
* Realizar fusiones frecuentes con develop del código estable, y subirlo al remoto. **Siempre vigilar la estabilidad de código**.  
* Como ejemplo proponemos la siguiente división:  
   * **Tarea 1 (Front-End)**. Vista en Angular. Crearemos en el proyecto de Angular el HTML y los componentes necesarios para su presentación. No debe tener nada de proceso, sólo nos concentraremos en la vista.
   * **Tarea 2 (Front-End)**. Incluir los servicios de Angular. El servicio no llega a realizar las peticiones al API, sino que devuelve valores predeterminados e imprime por **console.log()** las peticiones al API.
   * **Tarea 3 (Front-End)**. Se realiza los servicios de Angular realizando las peticiones. Como el servido no esta realizado, daran error de API no encontrado.
   * **Tarea 4 (Front-End)**. Refactorizar, reoordenar, simplificar...
   * **Tarea 5 (Back-End)**. La API devuelve valores fijos, con los dtos necesarios e imprime logs con las peticiones.
   * **Tarea 6 (Back-End)**. Se realizan los controladores del API con sus correspondientes Test.
   * **Tarea 7 (Back-End)**. Se programa las querys necesarias, se crean los documentos necesarios con sus repositorios...
   * **Tarea 8 (Back-End)**. Refactorizar, reoordenar, simplificar...   
   * **Tarea 9**. Se realizan las pruebas de aceptación.
   * **Tarea 10**. Se podrían realizar bugs.
   * **Tarea 11**. Refactorizar, reoordenar, simplificar...   
   
#### Tareas transversales
* Planificar antes los cambios a realizar, y cuando se tiene claro, actualizar la rama **issue#xx** con **develop** justo antes de empezar. Realizar una **estimación temporal** y **anotarlo en la tarea**.
* Cuando nos sentamos a trabajar, comprobar que la rama **issue#xx** está actualizada respecto a **develop**.
* No es recomendable dejar de trabajar sin aportar a develop las mejoras, siempre **sin romper develop**.
* Realizar aportaciones frecuentes a la rama **develop**, del código estable, aunque este a medias. **Ojo** con los ficheros muy susceptibles de colisionar, como por ejemplo **app.module.ts**, **app-routing.module.ts**, **home.component.ts**..., en este caso, modificarlos y subirlos a **develop** con rapidez.
* Vigilar y pensar bien los **comentarios de los commits**, acordarse de añadir la referencia del issue: **#xx**.
* Cuando se termina una **tarea** o **issue#xx**, añadir el **tiempo real** utilizado y cerrarlo.

### :clap: Entraga de la práctica
* Indicar como texto en la subida el nombre de la **Historia** y tu **cuenta de GitHub**.

### :ballot_box_with_check: Criterios de evaluación (ver rúbrica)
* Completud y corrección de lo entregado respecto al enunciado.
* Adecuación de la temporalidad de desarrollo según el enunciado.
* Mantenimiento de calidad del código (Travis-CI, Sonar, Better Code Hub,...).
* Uso correcto del flujo de trabajo ramificado (Git Workflow).
* Commits correctos y completos.
* Gestión adecuada y completa (Scrum, uso de issues, estimación, tiempo real...) durante el desarrollo.
* Planificación temporal adecuada y equilibrada en el desarrollo.
* Calidad del código. Todos los aspectos vistos en teoría, y poniendo espeacial enfásis en:
   * Formatear.
      * Herramienta del IDE.
      * Líneas en blanco.
      * Ordenar métodos.
      * Repasar nombres de clases, métodos, atributos, parámetros y variables.
   * Sencillez del código.
      * Simplificar el código.
      * Eliminar comentarios.
      * Estructuras anidadas: <3.
      * Complejidad ciclomática: <8-12.
   * Métricas.
      * Paquete: <20 clases.
      * Clases: <500-200 líneas, <20 métodos.
      * Métodos: <3-5 parámetros, <15 líneas.
      * Cobertura de test: >80%.
   * Eliminar redundancias (copy & paste).
   * Eliminar código muerto.
   * Tratamiento de errores. 
   * Calidad de la arquitectura (GRASP, SOLID, **patrones**...).
