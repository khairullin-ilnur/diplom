package ru.darkvader.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khairullin on 12/04/16.
 * Emotion entity.
 *
 * @author Khairullin
 */

public class Emotion {

    private EmotionType emotionType;
    private List<Integer> emotionValues;
    private int emotionTotal;

    public Emotion() {
        emotionValues = new ArrayList<Integer>();
        emotionTotal = 0;
    }

    public Emotion(EmotionType emotionType) {
        this.emotionType = emotionType;
        emotionTotal = 0;
    }

    public List<Integer> getEmotionValues() {
        return emotionValues;
    }

    public void setEmotionValues(List<Integer> emotionValues) {
        this.emotionValues = emotionValues;
    }

    public EmotionType getEmotionType() {
        return emotionType;
    }

    public void setEmotionType(EmotionType emotionType) {
        this.emotionType = emotionType;
    }

    public int getEmotionTotal() {
        return emotionTotal;
    }

    public void setEmotionTotal(int emotionTotal) {
        this.emotionTotal = emotionTotal;
    }

    @Override
    public String toString() {
        return "Emotion{" +
                "emotionType=" + emotionType +
                ", emotionValues=" + emotionValues +
                ", emotionTotal=" + emotionTotal +
                '}';
    }

}
