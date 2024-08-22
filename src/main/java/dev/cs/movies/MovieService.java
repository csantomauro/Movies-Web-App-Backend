package dev.cs.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    Logger logger = LoggerFactory.getLogger(MovieService.class);
    @Autowired//this will let the framework know that we want it to instantiate this class for us, so we don't have to make a constructor
    private MovieRepository movieRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }
    public Optional<Movie> singleMovieById(ObjectId id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> singleMovie(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }
    public Boolean addMovie(Movie movie) {
        if(movieRepository.findMovieByImdbId(movie.getImdbId()).isPresent()) {
            logger.error("Attempt to insert a movie with an existing IMDb Id: " + movie.getImdbId());
            return false;
        }else{
            movieRepository.insert(movie);
            return true;
        }
    }
    public void deleteMovie(String imdbId) {
        mongoTemplate.remove(
                Query.query(Criteria.where("imdbId").is(imdbId)),
                Movie.class
        );
    }
}
