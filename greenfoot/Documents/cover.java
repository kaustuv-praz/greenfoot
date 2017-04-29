import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class cover here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class cover extends Actor
{
    /**
     * Act - do whatever the cover wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        // checking the user input to change game mode
        
        if(Greenfoot.isKeyDown("1")){       // checks if the pressed key is 1. if it is 1 then executes the below statement.
            
            getWorld().removeObject(this);      // removes the object of intro class
            
        }else if(Greenfoot.isKeyDown("2")){     // checks if the pressed key is 2. if it is 2 then executes the below statement.
            
            getWorld().removeObject(this);
            
        }else if(Greenfoot.isKeyDown("3")){
            
            getWorld().removeObject(this);
            
        }
        
    }   
    
}
