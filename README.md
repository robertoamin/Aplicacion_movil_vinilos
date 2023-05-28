# Vinilos

Breve descripción del proyecto y su propósito.

## Descripción

Vinilos es una aplicación móvil desarrollada en Kotlin utilizando el framework Jetpack de Android. Está diseñada para usuarios que deseen explorar y descubrir música, así como para coleccionistas de música que deseen llevar un control de los álbumes que poseen.

La aplicación consume un API público alojado en Heroku, desarrollado en Python con el framework Flask. El API proporciona endpoints HTTP que responden con datos en formato JSON, incluyendo listados de artistas, álbumes y otra información relacionada.

La funcionalidad principal de la aplicación incluye:

- Listado de artistas musicales con detalles como género, discografía y biografía.
- Listado de álbumes musicales con información detallada como título, artista, año de lanzamiento, portada y lista de canciones.
- Crear Album con nombre, descripcion, cover, release date, gender y casa musical
- Ver el detalle de un Album
- Ver el detalle de un artista
- Listado de coleccionistas con nombre y la inicial
- Detalle de un coleccionista
- Control de colecciones de música, permitiendo a los coleccionistas marcar los álbumes que poseen.
- Se pueden agregar artistas a un coleccionista, para asi seguirlos

## Requisitos Previos

- Android 13.0 Google API 33 o superior.
- Emulador usado: Device Pixel_3a_API33_x86_64
- Librerias utilizadas: Glide, Volley, recyclerview, Junitest, kotlinx.coorutines, espresso
- Java version 1.8
- Conexión a Internet.

## Instalación

1. Clona este repositorio desde la rama Master: `git clone https://github.com/tuusuario/Aplicacion_movil_vinilos`
2. Abre el proyecto en Android Studio y sincroniza el Gradle
3. Realiza las configuraciones necesarias para el entorno de desarrollo local, si las hay.
4. Compila y ejecuta la aplicación en un emulador o dispositivo Android.

## Uso General

1. Al abrir la aplicación, se mostrará un menu principal con 2 cards: Usuario y Coleccionista
2. Como Usuario, haz clic en Usuario para ver la lista de albums musicales disponibles
3. Haz clic en un card de la lista de albums para ver los detalles, como el cover, release date, genre, descripcion, etc.
4. Puedes crear un nuevo Album desde el listado de albums, dandole click al boton con el +, permite adicionar un album
5. Regresa con la flecha del menu superior a la lista de albums musicales disponibles.
6. Puedes regresar al menu principal dando clic en el boton con figura de casita en la barra de menu inferior
7. Si eres un coleccionista, haz clic en Coleccionista para ver la lista de estos, sin embargo en esta entrega esa lista aun no esta desarrollada por lo que te saldara una pantalla sin informacion.
8. Aun cuando en esta pantalla no se despliega aun informacion, si hay una barra de menu inferior, en donde puedes dar clic en artistas y te llevara al listado de artistas disponibles.
9. En futuras versiones se podra acceder a la informacion detallada del artista seleccionado.
10. Desde la vista de listado de artista, puedes navegar por la aplicacion a traves del menu de la barra inferior.


## Tecnologías Utilizadas

- Kotlin
- Jetpack (ViewModel, [otras bibliotecas de Jetpack utilizadas])
- API: https://back-vinilos.herokuapp.com/
- Lenguaje de programación del API: Python con Flask

## Licencia

[Agregar la licencia del proyecto si corresponde]

## Contacto

Si tienes alguna pregunta o sugerencia relacionada con este proyecto, no dudes en ponerte en contacto con nosotros. [r.amin@uniandes.edu.co]
