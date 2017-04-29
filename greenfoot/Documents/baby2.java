import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class baby2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class baby2 extends Actor
{
    /**
     * Act - do whatever the baby2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int counter = 0;      // altering time
    int enter = 0;          // for knowing  intermediate or advance mode.
    /* enter = 0 means do nothing
       enter = 1 means move babies
       */
    int alter = 0;      // for baby movement direction altering
    int pressed = 0;        // basic mode

    public void act(){
        // Add your action code here.
        
        if(Greenfoot.isKeyDown("1")){       
            pressed = 1;
        }
            
        if(Greenfoot.isKeyDown("escape")){
            enter = 0;
        }else if(Greenfoot.isKeyDown("space")){
            enter = 1;
        }
        
        if(pressed == 0){
            
           if(Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("3")){            // determines the mode is now on either intermediate or advance
              enter = 1;
           }
           
        }
        
        if(enter == 1){
                
           move();      // call move method that moves babies.
          
        }
   
    }

    
    public void move(){
        
        Actor getbaby = ((baby2) getWorld().getObjects(baby2.class).get(0));        // gets the middle baby. middle baby is fast moving
       
        if(alter == 0){
                       
            setLocation(getX(), getY() - 2);        // moves all babies up
            getbaby.setLocation(getbaby.getX(), getbaby.getY() - 1);    //move middle baby up
            counter++;      //counter increment
            
        }else if(alter == 1){
            
            setLocation(getX(), getY() + 2);            // moves all babies down
            getbaby.setLocation(getbaby.getX(), getbaby.getY() + 1);        // move middle baby down
            counter--;          //counter decrement
            
        }
       
        if(counter == 80){              // alter duration
            
            alter = 1;              // alter value change for change in movement direction of babies
            
        }else if(counter == 0){
            
            alter = 0;              // alter value change for change in movement direction of babies
            
        }
       
       
    }
    
}

