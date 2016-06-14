package ru.darkvader.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Khairullin on 12/03/16.
 * Google Form report bean.
 * An entity class which contains the information of a single test.
 *
 * @author Khairullin
 */

@Entity
@Table(name = "answer", indexes = {
        @Index(columnList = "ANSWER_MOVIE_ID", name = "INDEX_ANSWER_MOVIE_ID")}
)
public class Answer implements Serializable {

    @Id
    @Column(name = "ANSWER_ID")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private int answerId;

    @Basic
    @Column(name = "ANSWER_MOVIE_ID")
    private int answerMovieId;

    @Basic
    @Column(name = "ANSWER_CONTEMPT", nullable = false)
    private float questionContempt;

    @Basic
    @Column(name = "ANSWER_SURPRISE", nullable = false)
    private float questionSurprise;

    @Basic
    @Column(name = "ANSWER_FEAR", nullable = false)
    private float questionFear;

    @Basic
    @Column(name = "ANSWER_ENJOYMENT", nullable = false)
    private float questionEnjoyment;

    @Basic
    @Column(name = "ANSWER_INTEREST", nullable = false)
    private float questionInterest;

    @Basic
    @Column(name = "ANSWER_ANGER", nullable = false)
    private float questionAnger;

    @Basic
    @Column(name = "ANSWER_SHAME", nullable = false)
    private float questionShame;

    @Basic
    @Column(name = "ANSWER_DISTRESS", nullable = false)
    private float questionDistress;

    @Basic
    @Column(name = "ANSWER_NEUTRAL", nullable = false)
    private float questionNeutral;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getAnswerMovieId() {
        return answerMovieId;
    }

    public void setAnswerMovieId(int answerMovieId) {
        this.answerMovieId = answerMovieId;
    }

    public float getQuestionContempt() {
        return questionContempt;
    }

    public void setQuestionContempt(float questionContempt) {
        this.questionContempt = questionContempt;
    }

    public float getQuestionSurprise() {
        return questionSurprise;
    }

    public void setQuestionSurprise(float questionSurprise) {
        this.questionSurprise = questionSurprise;
    }

    public float getQuestionFear() {
        return questionFear;
    }

    public void setQuestionFear(float questionFear) {
        this.questionFear = questionFear;
    }

    public float getQuestionEnjoyment() {
        return questionEnjoyment;
    }

    public void setQuestionEnjoyment(float questionEnjoyment) {
        this.questionEnjoyment = questionEnjoyment;
    }

    public float getQuestionInterest() {
        return questionInterest;
    }

    public void setQuestionInterest(float questionInterest) {
        this.questionInterest = questionInterest;
    }

    public float getQuestionAnger() {
        return questionAnger;
    }

    public void setQuestionAnger(float questionAnger) {
        this.questionAnger = questionAnger;
    }

    public float getQuestionShame() {
        return questionShame;
    }

    public void setQuestionShame(float questionShame) {
        this.questionShame = questionShame;
    }

    public float getQuestionDistress() {
        return questionDistress;
    }

    public void setQuestionDistress(float questionDistress) {
        this.questionDistress = questionDistress;
    }

    public float getQuestionNeutral() {
        return questionNeutral;
    }

    public void setQuestionNeutral(float questionNeutral) {
        this.questionNeutral = questionNeutral;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId='" + answerId + '\'' +
                ", answerMovieId='" + answerMovieId + '\'' +
                ", questionContempt=" + questionContempt +
                ", questionSurprise=" + questionSurprise +
                ", questionFear=" + questionFear +
                ", questionEnjoyment=" + questionEnjoyment +
                ", questionInterest=" + questionInterest +
                ", questionAnger=" + questionAnger +
                ", questionShame=" + questionShame +
                ", questionDistress=" + questionDistress +
                ", questionNeutral=" + questionNeutral +
                '}';
    }

}
