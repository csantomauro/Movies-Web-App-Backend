package dev.cs.movies;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends MongoRepository<Director, String> {
}
