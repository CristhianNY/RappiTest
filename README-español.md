# Prueba Ingreso Rappi

_El primer paso del proceso de selección consiste en superar esta prueba, está diseñada para evaluar tu conocimiento y experiencia como desarrollador Android._

##  Las capas de la aplicación  🚀

_Esta applicación se desarrollo con el patron arquitectonico MVP.

    Tenemos  capas : 
     
        . Interactor -- es nuestro puente entre la vista y el interactor  - HomeInteractor , DetailsInteractor
        . ViewModel --- son las interfaces llamadas HomeView, y DetailsView implementadas por HomeFragment y DetailsFragment

    Resposabilidad de cada clase:

    DetailMovieFragment : Es el fragmento que nos muestra los detalles de la pelicula
    HomeFragment  : fragmento que nos muestra la lista de peliculas  y su buscador por titulo
    HomeView DetailsView : son las interfaces que nos controlan las interacciones que debe tener las actividades y lo que se debe ejecutar en cada una
    HomeInteractor , Details Interactor : Son las encargadas de hacer los consumos usando retrofit 
    CustomFilter : se encarga de hacer un filtro a los items del recyclerview de las peliculas
    MovieApi : es la interfeaz que nos mapea con retrofit cada consumo
    ConstantsServices : esta clase contiene constanstes de la aplicación como apis y urls
    RecyclerviewMovieByCategoryAdapter:  es el adapter que se encarga de llenar el recyclerview de peliculas.
    MainActivity : actividad pricipal que contiene los fragmets y los llamados a cada uno de ellos con el navigationDrawable

._

## En qué consiste el principio de responsabilidad única? Cuál es su propósito? *


### Respuesta 🔧

_Su acrónimo en inglés SRP Single Responsibility Principle, es un principio sólido. Este principio prácticamente nos dice que cada clase debe tener su responsabilidad bien definida, porque si una clase tiene muchas responsabilidades, el código está acoplado y es muy susceptible de cambiar. entre menos responsabilidades asociadas tenga una clase mucho mejor._


## Qué características tiene, según su opinión, un “buen” código o código limpio?

### Respuesta 🔧

Un codigo Limpio tiene las siguientes caracteristicas: 

Variables legibles y bien definidas.
patrones de diseños arquitectonicos y de diseño bien definidos
Funciones o metodos con numero de argumentos limitados
no tener comentarios en el codigo 
no tener metodos vacios o sin implementar
Escribir pruebas Unitarias. 



---
⌨️ con ❤️ por [CristhianNY](https://github.com/CristhianNY) 😊
