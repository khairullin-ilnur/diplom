package ru.darkvader.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.darkvader.dao.AnswerDAO;
import ru.darkvader.dao.MovieDAO;
import ru.darkvader.model.Answer;
import ru.darkvader.model.Movie;
import ru.darkvader.services.PredictionServices;

import java.util.ArrayList;

import static java.lang.Math.pow;

/**
 * Created by Khirullin on 25/05/16.
 * This implementation of the PredictionServices interface.
 *
 * @author Khairullin
 */

@Service
@Transactional
public class PredictionServicesImpl implements PredictionServices {

    @Autowired
    private AnswerDAO answerDAO;
    @Autowired
    private MovieDAO movieDAO;

    @Override
    public Movie makeMoviePrediction(Answer answer, Movie movie) {
        ArrayList<Answer> answers = (ArrayList<Answer>) answerDAO.findAll();
        float minDistance = 0;
        Answer nearestAnswer = null;

        for (int i = 0; i < answers.size() - 1; i++) {
            float distance = (float) pow(answer.getQuestionAnger() - answers.get(i).getQuestionAnger(), 2);
            distance = distance + (float) pow(answer.getQuestionContempt() - answers.get(i).getQuestionContempt(), 2);
            distance = distance + (float) pow(answer.getQuestionDistress() - answers.get(i).getQuestionDistress(), 2);
            distance = distance + (float) pow(answer.getQuestionEnjoyment() - answers.get(i).getQuestionEnjoyment(), 2);
            distance = distance + (float) pow(answer.getQuestionFear() - answers.get(i).getQuestionFear(), 2);
            distance = distance + (float) pow(answer.getQuestionInterest() - answers.get(i).getQuestionInterest(), 2);
            distance = distance + (float) pow(answer.getQuestionNeutral() - answers.get(i).getQuestionNeutral(), 2);
            distance = distance + (float) pow(answer.getQuestionShame() - answers.get(i).getQuestionShame(), 2);
            distance = distance + (float) pow(answer.getQuestionSurprise() - answers.get(i).getQuestionSurprise(), 2);

            if (minDistance < distance) {
                minDistance = distance;
                nearestAnswer = answers.get(i);
            }
        }

        return movieDAO.findOne(nearestAnswer.getAnswerMovieId());
    }
}
