package com.example.springbootjwt.Controller;

import com.example.springbootjwt.Exception.IdException;
import com.example.springbootjwt.Exception.MovieException;
import com.example.springbootjwt.Model.Movie;
import com.example.springbootjwt.Service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping()
    public Movie createMovie(@RequestBody Movie movie){
        return movieService.createMovie(movie);
    }

    @PostMapping("/add")
    public Movie saveMovie(@RequestBody Movie movie) throws MovieException{
        if (MovieService.payLoadVal(movie)){
            return movieService.createMovie(movie);
        }
        throw new MovieException("Movie Can't be created");
    }

    @GetMapping("/get/{id}")
    public Movie getMovieById(@PathVariable("id") ObjectId id) throws MovieException {
        return movieService.getByMovieId(id);
    }

    @GetMapping("/getAll")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovieById(@PathVariable("id") ObjectId id) throws IdException {
        movieService.deleteMovieById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllMovies(){
        movieService.deleteAllMovies();
    }

    @PutMapping("/update/{id}")
    public Movie updateMovieById(ObjectId id) throws IdException {
        return movieService.updateMovieById(id);
    }

    @GetMapping("/totalCount")
    public int movieCount(){
        return movieService.MovieCount();
    }
}
