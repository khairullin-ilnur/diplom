package ru.darkvader.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.darkvader.exception.NotFoundException;
import ru.darkvader.model.Answer;
import ru.darkvader.services.AnswerServices;
import ru.darkvader.services.fixture.TestConstants;
import ru.darkvader.services.fixture.TestData;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Khairullin on 12/03/16.
 *
 * @author Khairullin
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ru.darkvader.config.TestConfig.class})
@Transactional
public class AnswerServicesImplTest {

    @Autowired
    private AnswerServices answerServices;

    @Test
    public void testFindAll() throws Exception {
        List<Answer> answers = answerServices.findAll();
        assertNotNull(answers);
    }

    @Test
    public void testCRUD() throws NotFoundException {
        //Create movie object
        Answer answer = TestData.standardAnswer();

        //Check that empty database
        assertEquals(0, answerServices.findAll().size());

        //Check insert movie to database
        answerServices.create(answer);
        assertEquals(1, answerServices.findAll().size());

        //Check get by id from database
        assertEquals(TestConstants.AnswerData.ANSWER_MOVIE_ID, answerServices.findById(answer.getAnswerId()).getAnswerMovieId());

        //Check update post in database
//        answer.setAnswerMovieId(TestConstants.AnswerData.UPD_ANSWER_MOVIE_ID);
        answerServices.update(answer);
        assertEquals(TestConstants.AnswerData.UPD_ANSWER_MOVIE_ID, answerServices.findById(answer.getAnswerId()).getAnswerMovieId());

        //Check delete post from database
        answerServices.delete(answer.getAnswerId());
        assertEquals(0, answerServices.findAll().size());
    }

    @Test
    public void testCreateByLink() throws NotFoundException {
        //Check that empty database
//        assertEquals(0, answerServices.findAll().size());

        //Check insert movie to database
//        answerServices.createByLink(TestConstants.AnswerData.CSV_FILE_PATH, TestConstants.AnswerData.ANSWER_MOVIE_LINK);
//        assertEquals(3, answerServices.findAll().size());

        //Check get by id from database
//        assertEquals(TestConstants.AnswerData.ANSWER_MOVIE_LINK, answerServices.findById(TestConstants.AnswerData.ANSWER_ID).getAnswerMovieId());

        //Check delete post from database
//        answerServices.delete(TestConstants.AnswerData.ANSWER_ID);
//        assertEquals(2, answerServices.findAll().size());
    }

    @Test
    public void testCSV() {
        answerServices.createReportByLink("Опрос 1.csv", "Опрос 2.csv", "5");
    }

}
