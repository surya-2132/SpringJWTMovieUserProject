package com.example.springbootjwt.Service;

import com.example.springbootjwt.Exception.IdException;
import com.example.springbootjwt.Exception.MovieException;
import com.example.springbootjwt.Model.Movie;
import com.example.springbootjwt.Repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
        private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Movie getByMovieId(ObjectId id) throws MovieException {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isEmpty()){
            throw new MovieException("Movie NOT Found");
        }
        return movie.orElseGet(movie::get);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public void deleteMovieById(ObjectId id) throws IdException {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            movieRepository.deleteById(id);
        }else{
            throw new IdException("Movie NOT Found");
        }
    }

    public void deleteAllMovies(){
         movieRepository.deleteAll();
    }

//    public String updateMovieById(String idStr, Movie movie){
//        movieRepository.findById(idStr);
//    }

    public Movie updateMovieById(ObjectId id) throws IdException {
        Optional<Movie> optMovie = movieRepository.findById(id);
        if (optMovie.isPresent()){
            optMovie.get().setName(optMovie.get().getName());//set via JSON->raw in postman
            optMovie.get().setReleaseDate(optMovie.get().getReleaseDate());
            movieRepository.save(optMovie.get());
        }else {
            throw new IdException("Movie not found");
        }
        return optMovie.get();
    }

    public int MovieCount(){
        return movieRepository.findAll().size();
    }


    public static boolean payLoadVal(Movie movie){
        return movie.getId() == null;
    }
}
