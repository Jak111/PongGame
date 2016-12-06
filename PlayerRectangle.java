/*
 *              Beschreibung: 
 *                     Autor: Jonas Niedermair
 *                     Datum: 02.12.2016
 *    Letztes Änderungsdatum:
 */
package PongGame;

/**
 *
 * @author Jak11
 */
public class PlayerRectangle {
    double posX,posY,width,height;
    
    public PlayerRectangle(double posX, double posY, double width, double height){
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        
    }
    //SETTER
    public void setPosX(double posX){
        this.posX = this.posX + posX;
    }
    public void setPosY(double posY){
        this.posY = posY;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public void setWidth(double width){
        this.width = width;
    }
    
    //GETTER
    public double getPosX(){
        return posX;
    }
    public double getPosY(){
        return posY;
    }
    public double getHeight(){
        return height;
    }
    public double getWidth(){
        return width;
    }
    

    
}
