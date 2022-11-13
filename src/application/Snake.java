package application;

import java.util.ArrayList;

public class Snake {

    public Snake() {
        this.lastCoordinates.add(new Coordinates(13, 8));
    }

    private Coordinates headCoordinates = new Coordinates(14, 8);
    private int length = 2;
    private ArrayList<Coordinates> lastCoordinates = new ArrayList<Coordinates>();
    private boolean alive = true;
    private String direction = "right";

    public Coordinates getHeadCoordinates() {
        return headCoordinates;
    }

    public void setHeadCoordinates(Coordinates headCoordinates) {
        this.headCoordinates = headCoordinates;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ArrayList<Coordinates> getLastCoordinates() {
        return lastCoordinates;
    }

    public void setLastCoordinates(ArrayList<Coordinates> lastCoordinates) {
        this.lastCoordinates = lastCoordinates;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void move() {
        for (int i = getLastCoordinates().size() - 1; i >= 0; i--) {
            Coordinates prevCoord;

            if (i == 0) {
                prevCoord = this.getHeadCoordinates();
            } else {
                prevCoord = this.getLastCoordinates().get(i - 1);
            }

            this.getLastCoordinates().get(i).setX(prevCoord.getX());
            this.getLastCoordinates().get(i).setY(prevCoord.getY());
        }

        if (direction.equals("right")) {
            this.getHeadCoordinates().setX(this.getHeadCoordinates().getX() + 1);
        } else if (direction.equals("left")) {
            this.getHeadCoordinates().setX(this.getHeadCoordinates().getX() - 1);
        } else if (direction.equals("up")) {
            this.getHeadCoordinates().setY(this.getHeadCoordinates().getY() - 1);
        } else if (direction.equals("down")) {
            this.getHeadCoordinates().setY(this.getHeadCoordinates().getY() + 1);
        }
    }

    public void nom(){
        Coordinates newCoord = this.getLastCoordinates().get(0);
        this.lastCoordinates.add(new Coordinates(newCoord.getX(), newCoord.getY()));
    }

    public void faceUp() {
        this.setDirection("up");
    }

    public void faceLeft() {
        this.setDirection("left");
    }

    public void faceRight() {
        this.setDirection("right");
    }

    public void faceDown() {
        this.setDirection("down");
    }

}
