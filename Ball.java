/*
 *              Beschreibung: 
 *                     Autor: Jonas Niedermair
 *                     Datum: 02.12.2016
 *    Letztes Änderungsdatum:
 */
package PongGame;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 *
 * @author Jak11
 */
public class Ball {
    
     private double posX, posY, radius;
     private Point2D center;
    
    public Ball(double posX, double posY, double radius){
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        setCenter(posX,posY);
        
    }
    
    //SETTER - METHODS
    public void setCenter(double posX, double posY){
        center = new Point2D(posX, posY);
    }
    public void setRadius(double r){
        radius = r;
    }
    public void setPosX(double x){
        posX = x;
    }
    public void setPosY(double y){
        posY = y;
    }
    
    //GETTER - METHODS
    public double getPosY(){
        return posY;
    }
    public double getPosX(){
        return posX;
    }    
    public double getRadius(){
        return radius;
    }
}
