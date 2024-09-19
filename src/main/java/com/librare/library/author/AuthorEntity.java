package com.librare.library.author;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "authors")
@Data
public class AuthorEntity {

    @Id
    private String id;
    private String name;
    private String birthDate;
    private String deathDate;
}