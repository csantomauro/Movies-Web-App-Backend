package dev.cs.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "directors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Director {
    @Id
    private String id;
    private String name;
    private String dateOfBirth;
    private String nationality;
    @DocumentReference
    private List<Movie> movies;
}
