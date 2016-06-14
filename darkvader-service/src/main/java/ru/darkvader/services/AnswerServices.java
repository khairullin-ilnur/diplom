package ru.darkvader.services;

import ru.darkvader.model.Answer;
import ru.darkvader.model.AnswerDTO;

/**
 * Created by ilnur on 12/03/16.
 * Declares methods used to obtain and modify Answer table.
 *
 * @author Khairullin
 */

public interface AnswerServices extends GenericCRUD<Answer> {

    /**
     * Creates a new Report by link on CSV.
     *
     * @param movieId  The created object movie id,
     * @param csvFile1 The created object first csv.
     * @param csvFile2 The created object second csv.
     * @return The created objects.
     */
    public AnswerDTO createReportByLink(String csvFile1, String csvFile2, String movieId);

}
