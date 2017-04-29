import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class baby1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class baby1 extends Actor
{
    /**
     * Act - do whatever the baby1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int enter = 0;
    int choose = 0;
    public void act() 
    {
        // Add your action code here.
        if(Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("3")){
            enter = 1;
        }
        
        if(enter == 1){
            
            if(Greenfoot.isKeyDown("a")){
                choose = 1;
            }else if(Greenfoot.isKeyDown("s")){
                choose = 0;
            }else if (Greenfoot.isKeyDown("d")){
                choose = 2;
            }
            
            if(choose == 0 || choose == 1 || choose == 2){
                
                Actor getbaby = ((baby1) getWorld().getObjects(baby1.class).get(choose));    // select the object of baby.
                
                if(Greenfoot.isKeyDown("up")){
                    getbaby.setLocation(getbaby.getX(), getbaby.getY() - 3);
                }else if(Greenfoot.isKeyDown("down")){
                    getbaby.setLocation(getbaby.getX(), getbaby.getY() + 3);                    
                }
                
            }
            
        }    
    }
}
