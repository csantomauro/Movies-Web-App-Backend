package dev.cs.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);
    }
    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getMovie(@PathVariable String imdbId) {
        return new ResponseEntity<Optional<Movie>>(movieService.singleMovie(imdbId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createMovie(@RequestBody Movie movie) {
        String errorMessage = "The movie could not be added. The IMDb ID might already exist or be invalid.";
        if(movieService.addMovie(movie)) return new ResponseEntity<>(movie, HttpStatus.CREATED);
        else return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{imdbId}")
    public ResponseEntity<String> deleteMovie(@PathVariable String imdbId) {
        movieService.deleteMovie(imdbId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
