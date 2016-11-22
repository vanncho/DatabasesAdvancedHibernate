package app.services;


import app.entities.stars.Star;

public interface StarService {

    void create(Star star);

    Star findStarByName(String name);
}
