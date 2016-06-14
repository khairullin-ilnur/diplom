package ru.darkvader.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.darkvader.exception.NotFoundException;
import ru.darkvader.exception.ValidationException;
import ru.darkvader.model.AnswerDTO;
import ru.darkvader.model.Movie;
import ru.darkvader.services.AnswerServices;
import ru.darkvader.services.MovieServices;
import ru.darkvader.services.PredictionServices;

/**
 * Created by Khairullin on 12/03/16.
 *
 * @author Khairullin
 */

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private AnswerServices answerServices;
    @Autowired
    private MovieServices movieServices;
    @Autowired
    private PredictionServices predictionServices;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "parse", method = RequestMethod.POST)
    public String leaveRoom(@RequestParam String inputMovie,
                            @RequestParam String inputQuiz1,
                            @RequestParam String inputQuiz2,
                            ModelMap modelMap) throws ValidationException, NotFoundException {

        // Parse and save movie to database
        Movie movie = movieServices.createByLink(inputMovie);

        // Parse ans save answer to database
        AnswerDTO answer = answerServices.createReportByLink(inputQuiz1, inputQuiz2, "" + movie.getMovieId());

        // Make prediction
        Movie predictionMovie = predictionServices.makeMoviePrediction(answer.getAnswer(), movie);

        // Put models for view
        modelMap.put("movie", movie);
        modelMap.put("answer", answer.getAnswer());
        modelMap.put("personPoints", answer.getPersonPoints());
        modelMap.put("predictionMovie", predictionMovie);

        // View graphs page
        return "graphs";
    }

}
