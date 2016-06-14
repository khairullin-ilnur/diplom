package ru.darkvader.model;

import java.util.ArrayList;

/**
 * Created by Khairullin on 30/05/16.
 *
 * @author Khairullin
 */
public class AnswerDTO {

    // Total
    private Answer answer;
    // Person emotion point for cube
    private ArrayList<PersonPoint> personPoints;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public ArrayList<PersonPoint> getPersonPoints() {
        return personPoints;
    }

    public void setPersonPoints(ArrayList<PersonPoint> personPoints) {
        this.personPoints = personPoints;
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "answer=" + answer +
                ", personPoints=" + personPoints +
                '}';
    }

}
