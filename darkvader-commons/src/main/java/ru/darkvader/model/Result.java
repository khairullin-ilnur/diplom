package ru.darkvader.model;

/**
 * Created by Khairullin on 29/04/16.
 * Result bean.
 *
 * @author Khairullin
 */

public class Result {

    // Basic emotions
    private float Fear;
    private float Enjoyment;
    private float Contempt;
    private float Surprise;
    private float Interest;
    private float Anger;
    private float Shame;
    private float Distress;
    private float Neutral;

    public float getFear() {
        return Fear;
    }

    public void setFear(float fear) {
        Fear = fear;
    }

    public float getEnjoyment() {
        return Enjoyment;
    }

    public void setEnjoyment(float enjoyment) {
        Enjoyment = enjoyment;
    }

    public float getContempt() {
        return Contempt;
    }

    public void setContempt(float contempt) {
        Contempt = contempt;
    }

    public float getSurprise() {
        return Surprise;
    }

    public void setSurprise(float surprise) {
        Surprise = surprise;
    }

    public float getInterest() {
        return Interest;
    }

    public void setInterest(float interest) {
        Interest = interest;
    }

    public float getAnger() {
        return Anger;
    }

    public void setAnger(float anger) {
        Anger = anger;
    }

    public float getShame() {
        return Shame;
    }

    public void setShame(float shame) {
        Shame = shame;
    }

    public float getDistress() {
        return Distress;
    }

    public void setDistress(float distress) {
        Distress = distress;
    }

    public float getNeutral() {
        return Neutral;
    }

    public void setNeutral(float neutral) {
        Neutral = neutral;
    }

    @Override
    public String toString() {
        return "Result{" +
                "Fear=" + Fear +
                ", Enjoyment=" + Enjoyment +
                ", Contempt=" + Contempt +
                ", Surprise=" + Surprise +
                ", Interest=" + Interest +
                ", Anger=" + Anger +
                ", Shame=" + Shame +
                ", Distress=" + Distress +
                ", Neutral=" + Neutral +
                '}';
    }
}
