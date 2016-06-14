package ru.darkvader.model;

import java.util.List;

/**
 * Created by Khairullin on 12/04/16.
 * Person with two emotion state: before and after.
 *
 * @author Khairullin
 */

public class Person {
    // Emotion state before
    private List<Emotion> emotions1;
    // Emotion state after
    private List<Emotion> emotions2;

    public Person() {
    }

    public List<Emotion> getEmotions1() {
        return emotions1;
    }

    public void setEmotions1(List<Emotion> emotions1) {
        this.emotions1 = emotions1;
    }

    public List<Emotion> getEmotions2() {
        return emotions2;
    }

    public void setEmotions2(List<Emotion> emotions2) {
        this.emotions2 = emotions2;
    }

    @Override
    public String toString() {
        return "Person{" +
                "emotions1=" + emotions1 +
                ", emotions2=" + emotions2 +
                '}';
    }

}
