import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;          // import all files of java.awt package 

/**
 * Write a description of class score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class score extends Actor
{
    /**
     * Act - do whatever the score wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void score001(int x){        // method that shows score of left team
     setImage(new GreenfootImage("Score " + x, 24, Color.BLUE, Color.WHITE));    
    }
    
    public void score002(int x){
        setImage(new GreenfootImage("Score " + x, 24, Color.BLUE, Color.WHITE));
    }
}
