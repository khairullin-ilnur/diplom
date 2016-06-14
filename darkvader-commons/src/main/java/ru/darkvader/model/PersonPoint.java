package ru.darkvader.model;

/**
 * Created by Khairullin on 30/05/16.
 * Person point for emotion cube.
 *
 * @author Khairullin
 */
public class PersonPoint {
    // Value by x-axis
    private String x;
    // Value by y-axis
    private String y;
    // Value by z-axis
    private String z;
    // Value by x-axis before
    private String oldX;
    // Value by y-axis before
    private String oldY;
    // Value by z-axis before
    private String oldZ;
    // Value by x-axis after
    private String newX;
    // Value by y-axis after
    private String newY;
    // Value by z-axis after
    private String newZ;

    public PersonPoint() {
    }

    public PersonPoint(double x, double y, double z) {
        this.x = "" + Double.toString(x).charAt(0) + Double.toString(x).charAt(1) + Double.toString(x).charAt(2) + Double.toString(x).charAt(3);
        this.y = "" + Double.toString(y).charAt(0) + Double.toString(y).charAt(1) + Double.toString(y).charAt(2) + Double.toString(y).charAt(3);
        this.z = "" + Double.toString(z).charAt(0) + Double.toString(z).charAt(1) + Double.toString(z).charAt(2) + Double.toString(z).charAt(3);
    }

    public void setOldState(double x, double y, double z) {
        this.oldX = "" + Double.toString(x).charAt(0) + Double.toString(x).charAt(1) + Double.toString(x).charAt(2) + Double.toString(x).charAt(3);
        this.oldY = "" + Double.toString(y).charAt(0) + Double.toString(y).charAt(1) + Double.toString(y).charAt(2) + Double.toString(y).charAt(3);
        this.oldZ = "" + Double.toString(z).charAt(0) + Double.toString(z).charAt(1) + Double.toString(z).charAt(2) + Double.toString(z).charAt(3);
    }

    public void setNewState(double x, double y, double z) {
        this.newX = "" + Double.toString(x).charAt(0) + Double.toString(x).charAt(1) + Double.toString(x).charAt(2) + Double.toString(x).charAt(3);
        this.newY = "" + Double.toString(y).charAt(0) + Double.toString(y).charAt(1) + Double.toString(y).charAt(2) + Double.toString(y).charAt(3);
        this.newZ = "" + Double.toString(z).charAt(0) + Double.toString(z).charAt(1) + Double.toString(z).charAt(2) + Double.toString(z).charAt(3);
    }

    public String getX() {
        return x;
    }

    public void setX(double x) {
        this.x = "" + Double.toString(x).charAt(0) + Double.toString(x).charAt(1) + Double.toString(x).charAt(2) + Double.toString(x).charAt(3);
    }

    public String getY() {
        return y;
    }

    public void setY(double y) {
        this.y = "" + Double.toString(y).charAt(0) + Double.toString(y).charAt(1) + Double.toString(y).charAt(2) + Double.toString(y).charAt(3);
    }

    public String getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = "" + Double.toString(z).charAt(0) + Double.toString(z).charAt(1) + Double.toString(z).charAt(2) + Double.toString(z).charAt(3);
    }

    public String getOldX() {
        return oldX;
    }

    public void setOldX(double oldX) {
        this.x = "" + Double.toString(oldX).charAt(0) + Double.toString(oldX).charAt(1) + Double.toString(oldX).charAt(2) + Double.toString(oldX).charAt(3);

    }

    public String getOldY() {
        return oldY;
    }

    public void setOldY(double oldY) {
        this.oldY = "" + Double.toString(oldY).charAt(0) + Double.toString(oldY).charAt(1) + Double.toString(oldY).charAt(2) + Double.toString(oldY).charAt(3);
    }

    public String getOldZ() {
        return oldZ;
    }

    public void setOldZ(double oldZ) {
        this.oldZ = "" + Double.toString(oldZ).charAt(0) + Double.toString(oldZ).charAt(1) + Double.toString(oldZ).charAt(2) + Double.toString(oldZ).charAt(3);
    }

    public String getNewX() {
        return newX;
    }

    public void setNewX(double newX) {
        this.newX = "" + Double.toString(newX).charAt(0) + Double.toString(newX).charAt(1) + Double.toString(newX).charAt(2) + Double.toString(newX).charAt(3);
    }

    public String getNewY() {
        return newY;
    }

    public void setNewY(double newY) {
        this.newY = "" + Double.toString(newY).charAt(0) + Double.toString(newY).charAt(1) + Double.toString(newY).charAt(2) + Double.toString(newY).charAt(3);
    }

    public String getNewZ() {
        return newZ;
    }

    public void setNewZ(double newZ) {
        this.newZ = "" + Double.toString(newZ).charAt(0) + Double.toString(newZ).charAt(1) + Double.toString(newZ).charAt(2) + Double.toString(newZ).charAt(3);
    }

    @Override
    public String toString() {
        return "PersonPoint{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

}
