package com.example.filmlist;

public class StringManager {

    public String apiUrl="https://api.themoviedb.org/3/movie/";
    public String apiKey="?api_key=18f552217e447f369638f70fa4f06a20";
    public String popular="popular";
    public String sinconexion="NO tienes conexion a Internet :(";
    public String original_title="original_title";
    public String overview="overview";
    public String vote_average="vote_average";
    public String poster_path="poster_path";
    public String genres="genres";
    public String id="id";
    public String name="name";
    public String results="results";
    public String español="&language=es";
    public String release_date="release_date";
    public String ingles="&language=en";

    public String now_playing="now_playing";
    public String upcoming="upcoming";

    public String top_rated="top_rated";

    public String cast="cast";
    public String profile_path="profile_path";
    public String character="character";

    public String recommendations="/recommendations";
    public String busqueda="https://api.themoviedb.org/3/search/movie?api_key=18f552217e447f369638f70fa4f06a20&query=";
    public String urlgeneros="https://api.themoviedb.org/3/discover/movie?api_key=18f552217e447f369638f70fa4f06a20&with_genres=";

    public String urlcast="/credits?api_key=18f552217e447f369638f70fa4f06a20";

    public String urlactor="https://api.themoviedb.org/3/person/";
    public String urlPliActores="/movie_credits?api_key=18f552217e447f369638f70fa4f06a20";
    //---------------------------gestion opciones rv------------------------------//


    public String INICIAL="INICIAL";
    public String BUSQUEDA="BUSQUEDA";
    public String POPULARES="POPULARES";
    public String TOPRATED="TOPRATED";
    public String ESTRENOS="ESTRENOS";
    public String RECOMENDACIONES="RECOMENDACIONES";

    public String GENERO="GENERO";
    public String ACTORES="ACTORES";
    public String PELISACTORES="PELISACTORES";
    public String ACTORESFAV="ACTORESFAV";
    //---------------------------//

    public String VISTAS="VISTAS";
    public String FAVORITAS="FAVORITAS";
    public String PENDIENTES="PENDIENTES";
    public String VALORACIONES="VALORACIONES";
    public String[] GENEROS=new String[]{"Action","Adventure","Animation","Comedy","Crime","Documentary","Drama","Family","Fantasy","History","Horror","Music","Mystery","Romance","Science Fiction","Thriller","War","Western"};
    public String[] IDGEN=new String[]{"28","12","16","35","80","99","18","10751","14","36","27","10402","9648","10749","878","53","10752","37"};


}
