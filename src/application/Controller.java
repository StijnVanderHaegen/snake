package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private Snake snake = new Snake();
    private Apple apple = new Apple();
    private Random random = new Random();
    private Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.25), ob -> this.snakeMovement()));
    public Rectangle snakeHead;
    public Rectangle snakeChunk;
    private final ArrayList<Rectangle> chunkList = new ArrayList<>();
    public Rectangle snakeFood;
    public Button startButton;
    public AnchorPane container;
    public Text scoreText;
    public Text gameOverText;
    private int score = -1;

    public void runSnake() {
    	gameOverText.setVisible(false);
        startButton.setVisible(false);
        randomizeApple();
        this.snake.setHeadCoordinates(new Coordinates(14,8));
        this.snake.getLastCoordinates().clear();
        this.snake.getLastCoordinates().add(new Coordinates(13,8));
        for (int i = 5; i < container.getChildren().size(); i++) {
			for (int j = 0; j < chunkList.size(); j++) {
				if (container.getChildren().get(i).equals(chunkList.get(j))) {
					container.getChildren().remove(i);
				}
			}
		}
        chunkList.clear();
        chunkList.add(snakeChunk);
        if (score==-1) {
        snakeHead.getScene().addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.Z||key.getCode() == KeyCode.UP) {
                snake.setDirection("up");
            } else if (key.getCode() == KeyCode.Q||key.getCode() == KeyCode.LEFT) {
                snake.setDirection("left");
            } else if (key.getCode() == KeyCode.D||key.getCode() == KeyCode.RIGHT) {
                snake.setDirection("right");
            } else if (key.getCode() == KeyCode.S||key.getCode() == KeyCode.DOWN) {
                snake.setDirection("down");
            }
        });
        }
        score=0;
        scoreText.setText("score: 0");
        timeline.setCycleCount(-1);
        timeline.playFromStart();
    }

    public void snakeMovement() {
        snake.move();

        if (apple.getCoordinates().equals(snake.getHeadCoordinates())) {
            snake.nom();
            this.raiseScore();
            this.randomizeApple();
        }

        if (snake.getHeadCoordinates().getX() > 24 || snake.getHeadCoordinates().getX() < 0 || snake.getHeadCoordinates().getY() > 16 || snake.getHeadCoordinates().getY() < 0) {
        	timeline.stop();
        	badEnding();
        }

        for (int i = 0; i < snake.getLastCoordinates().size(); i++) {
            if (snake.getHeadCoordinates().equals(snake.getLastCoordinates().get(i))) {
                badEnding();
            }
        }

        drawSnake();
    }

    public void drawApple(){
        snakeFood.setX(apple.getCoordinates().getRealX());
        snakeFood.setY(apple.getCoordinates().getRealY());
    }

    public void drawSnake(){
        snakeHead.setX(snake.getHeadCoordinates().getRealX());
        snakeHead.setY(snake.getHeadCoordinates().getRealY());

        for (int i = 0; i < snake.getLastCoordinates().size(); i++) {
            chunkList.get(i).setX(snake.getLastCoordinates().get(i).getRealX());
            chunkList.get(i).setY(snake.getLastCoordinates().get(i).getRealY());
        }
    }
    public void raiseScore() {
    	score+=100;
        scoreText.setText("score: "+score);
    }
    public void addChunck() {
    	Rectangle rect = new Rectangle(0, 0, 25, 25);
        rect.setArcHeight(5);
        rect.setArcWidth(5);
        container.getChildren().add(rect);
        chunkList.add(rect);
    }
    public void randomizeApple() {
    	apple.getCoordinates().setX(random.nextInt(24));
        apple.getCoordinates().setY(random.nextInt(16));
        drawApple();
    }
    public void badEnding() {
    	timeline.stop();
    	gameOverText.setVisible(true);
    	startButton.setVisible(true);
    }
}
