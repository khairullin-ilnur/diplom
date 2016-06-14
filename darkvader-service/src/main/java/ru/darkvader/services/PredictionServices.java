package ru.darkvader.services;

import ru.darkvader.model.Answer;
import ru.darkvader.model.Movie;

/**
 * Created by Khairullin on 25/05/16.
 * Declares methods used to make predictions of movie.
 *
 * @author Khairullin
 */
public interface PredictionServices {

    /**
     * Make prediction movie.
     *
     * @param answer Current answers.
     * @param movie  Current movie.
     * @return The prediction movie.
     */
    public Movie makeMoviePrediction(Answer answer, Movie movie);

}
