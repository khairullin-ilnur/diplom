package ru.darkvader.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.darkvader.model.Answer;

import java.util.ArrayList;

/**
 * Created by Khairullin on 12/03/16.
 * DAO repository on Answer table.
 * Specifies methods used to obtain and modify group related information
 * which is stored in the database.
 *
 * @author Khairullin
 */

@Repository
public interface AnswerDAO extends CrudRepository<Answer, Integer> {

    /**
     * Search answers in database by search query. Search query contains
     * movie id.
     *
     * @param movieId String which contains movie id.
     * @return Array list which contains answers.
     * If no answer is found, this method returns an empty list.
     */
    @Query("SELECT DISTINCT a FROM Answer AS a WHERE a.answerMovieId like :movieId")
    public ArrayList<Answer> searchAnswerByMovie(@Param("movieId") String movieId);

}