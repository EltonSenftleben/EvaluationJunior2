# EvaluationJunior2
Aplicación de prueba usando Jetpack Compose con Material Desing 3, MVVM, dagger hilt, room.

## Instrucciones
Crear un aplicación móvil en base a las siguientes características, en un tiempo de 2 días:

Desarrollo en kotlin.
Arquitectura MVVM o MVP
Consumir un servicio API Rest (https://www.themoviedb.org/documentation/api), eres libre de utilizar cualquier end-point del API.
Compose UI
Utilizar el componente de navegación de jetpack compose( la aplicación debe ser una single activity).
Usar animaciones de tu elección.
Para cargar imágenes puedes utilizar Glide, Coil o Fresco.
Los datos obtenidos desde el api, deberán estar disponibles aún sin conexión, para ello debes usar alguna de las siguientes opciones de almacenamiento 
SQLite, Realm, Room o SharedPreferences.

Nota: Se revisará el empleo de buenas prácticas de programación, arquitectura de la aplicación y patrones de diseño empleados.

## Aclaración del proyecto
Para la persistencia de datos se usó Room, con el fin de almacenar las imágenes para su posterior visualización sin conexión. Cabe aclarar 
que **almacenar imagenes de forma local se considera una mala práctica** debido al espacio requerido, la dificultad para su almacenaje y que extiende 
el código considerablemente, lo que podría interpretarse como código ambiguo.

Teniendo esto en mente y de acuerdo a las instrucciones, se buscó almacenar **todos** los datos, incluyendo imágenes y, aunque esto conyeba a que el 
proyecto sea poco óptimo; con el uso de corrutinas, compresión de imágenes y el uso de Coil como una de las librerías de renderizado de imágenes más 
óptimizada, se puede considerar una aplicación funcional.
