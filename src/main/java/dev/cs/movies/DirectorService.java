package dev.cs.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private MovieService movieService;

    public List<Director> allDirectors(){
        return directorRepository.findAll();
    }
    public void addDirector(Director director){
        directorRepository.save(director);
    }
    public void deleteDirector(String id){
        directorRepository.deleteById(id);
    }
    public Director addMovie(String directorId, String movieId){
        // Find the director by their ID
        Optional<Director> directorOpt = directorRepository.findById(directorId);
        if (!directorOpt.isPresent()) {
            throw new IllegalArgumentException("Director with ID " + directorId + " not found.");
        }

        Director director = directorOpt.get();

        // Find the movie by its IMDb ID
        Optional<Movie> movieOpt = movieService.singleMovie(movieId);
        if (!movieOpt.isPresent()) {
            throw new IllegalArgumentException("Movie with IMDb ID " + movieId + " not found.");
        }

        Movie movie = movieOpt.get();

        // Check if the movie is already in the director's list
        if (director.getMovies().stream().anyMatch(existingMovie -> existingMovie.getImdbId().equals(movieId))) {
            throw new IllegalArgumentException("Movie with IMDb ID " + movieId + " is already associated with this director.");
        }
        // Add the movie to the director's list of movies
        director.getMovies().add(movie);

        // Save the updated director back to the database
        directorRepository.save(director);

        return director;
    }
}
