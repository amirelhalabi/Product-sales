package amir.technical.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String mobile;

}
