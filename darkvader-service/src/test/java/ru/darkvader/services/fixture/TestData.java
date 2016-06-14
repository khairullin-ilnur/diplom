package ru.darkvader.services.fixture;

import ru.darkvader.model.Answer;
import ru.darkvader.model.Movie;

/**
 * Created by Khairullin on 22.08.2014.
 *
 * @author Khairullin
 */

public class TestData {

    public static Movie standardMovie() {
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieTitle("Test title");
        movie.setMovieViewCount("Test count");
        movie.setMovieLikeCount("Test count");
        movie.setMovieDislikeCount("Test count");

        return movie;
    }

    public static Answer standardAnswer() {
        Answer answer = new Answer();
        answer.setAnswerId(1);
//        answer.setAnswerMovieId("Test movie id");

        return answer;
    }

}
