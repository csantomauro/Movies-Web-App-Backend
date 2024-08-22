package dev.cs.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping
    public ResponseEntity<List<Director>> getAllDirectors() {
        return new ResponseEntity<List<Director>>(directorService.allDirectors(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Director> createDirector(@RequestBody Director director) {
        directorService.addDirector(director);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Director> deleteDirector(@PathVariable String id) {
        directorService.deleteDirector(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/movies")
    public ResponseEntity<Director> addMovie(@RequestBody Map<String, String> payload) {
        String directorId = payload.get("directorId");
        String movieId = payload.get("movieId");
        return new ResponseEntity<Director>(directorService.addMovie(directorId, movieId), HttpStatus.CREATED);
    }
}
