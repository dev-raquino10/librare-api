package com.librare.library.author;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
    private String id;
    private String name;
}