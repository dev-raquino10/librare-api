package com.librare.library.genre;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "genres")
public class GenreEntity {

    @Id
    private String id;
    private String name;
}