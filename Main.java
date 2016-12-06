/*
 *              Beschreibung: Pong Spiel
 *                     Autor: Jonas Niedermair
 *                     Datum: 02.12.2016
 *    Letztes Änderungsdatum:
 */
package PongGame;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import jdk.nashorn.internal.ir.LoopNode;

/**
 *
 * @author Jak11
 */
public class Main extends Application {
    
    private int score = 0;
    private int level = 1;
    private int time = 20;
    private boolean gameOver = false;
    private Timeline timeline;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pong Game");
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
        
        Canvas canvas = new Canvas(800, 500);
        root.getChildren().add(canvas);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        PlayerRectangle playerRec = new PlayerRectangle(((canvas.getWidth() - (canvas.getWidth()/2) - 35)),470,70, 10);
        Ball ball = new Ball(100, 100, 20);
        
        Circle circle = new Circle(0, 0, 0);
        new AnimationTimer(){
            public void handle(long currentNanoTime){    
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
               
                
                gc.setFill(Color.WHITE);
                gc.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 18));
                gc.fillText("Score: " + score,700, 20);
                
                gc.fillText("Level: " + level, 100, 20);
               
                gc.setFill(Color.RED);
                gc.fillOval(ball.getPosX(), ball.getPosY(), ball.getRadius(), ball.getRadius());
                
                gc.setFill(Color.BLUE);
                gc.fillRect(playerRec.getPosX(), playerRec.getPosY(), playerRec.getWidth(), playerRec.getHeight());
                
                
                if(gameOver){
                    gc.setFill(Color.WHITE);
                    gc.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 35));     
                    gc.fillText("GAME OVER!", (canvas.getWidth()/2), 200);
                    gc.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 20)); 
                    gc.fillText("PRESS SPACE TO START A NEW GAME", (canvas.getWidth()/2), 250);
                }
            }
        }.start();
            
        
            KeyFrame k = new KeyFrame(Duration.millis(time), new EventHandler<ActionEvent>() {
            double deltaX = 3;
            double deltaY = 3;

            @Override
            public void handle(final ActionEvent t) {
                ball.setPosX(ball.getPosX() + deltaX);
                ball.setPosY(ball.getPosY() + deltaY);
                Bounds bounds = canvas.getBoundsInLocal();
                boolean atRightBorder = ball.getPosX() >= (bounds.getMaxX() - ball.getRadius());
                boolean atLeftBorder = ball.getPosX() <= (bounds.getMinX());
                boolean playerCollision = false;
                boolean borderBottom = ball.getPosY() >= (bounds.getMaxY() - ball.getRadius());
                boolean atTopBorder = ball.getPosY() <= (bounds.getMinY() + ball.getRadius());
                
                //PLAYER kollison erkennen
                if((ball.getPosY() + ball.getRadius()) == 474 && (ball.getPosX() + ball.getRadius()) >= playerRec.getPosX() && ball.getPosX() <= (playerRec.getPosX() + playerRec.getWidth()))
                    playerCollision = true;         
                if (atLeftBorder || atRightBorder){
                    deltaX *= -1;
                }
                if (playerCollision || atTopBorder) {
                    deltaY *= -1;
                }
                if (playerCollision){
                    score++;
                    if(score == 3){
                        level = 2;
                        timeline.setRate(1.1);
                    }else if(score == 6){
                        level++;
                        timeline.setRate(1.2);
                    }else if(score == 9){
                        level++;
                        timeline.setRate(1.3);
                    }else if(score == 12){
                        level++;
                        timeline.setRate(1.4);
                    }else if(score == 15){
                        level++;
                        timeline.setRate(1.5);
                    }else if(score == 18){
                        level++;
                        timeline.setRate(1.6);
                    }else if(score == 21){
                        level++;
                        timeline.setRate(1.7);
                    }else if(score == 24){
                        level++;
                        timeline.setRate(1.8);
                    }else if(score == 27){
                        level++;
                        timeline.setRate(1.9);
                    }
                    
   
                }
                //GAME OVER
                if(borderBottom){
                score = 0;
                deltaX = 3;
                deltaY = 3;
                gameOver = true;
                stopTimeline();
                ball.setPosX(100);
                ball.setPosY(100);
                }
                        scene.setOnKeyPressed(
        new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
                String code = e.getCode().toString();
                if(code.equals("LEFT")&& !gameOver){
                    if(playerRec.getPosX() > 0){
                        playerRec.setPosX(-25);
                    }
                     
                }
                if(code.equals("RIGHT") && !gameOver){
                    if(playerRec.getPosX() < (canvas.getWidth()-playerRec.getWidth())){
                        playerRec.setPosX(25);
                    }
                     
                }
                if(code.equals("SPACE")){
                    playTimeLine();
                    gameOver = false;
                }

            }
        });
            }
        });
        timeline = new Timeline(k);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        

        
        primaryStage.show();

    }
    private void stopTimeline(){
        timeline.pause();
    }
    private void playTimeLine(){
        timeline.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
   
    
}
