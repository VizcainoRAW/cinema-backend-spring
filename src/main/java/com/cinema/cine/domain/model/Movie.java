package com.cinema.cine.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    private Long id;
    private String name;
    private String imagePath;

    public Movie(){}

    public Movie(String name, String imagePath){
        this.name = name;
        this.imagePath = imagePath;
    }

}