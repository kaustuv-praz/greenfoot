import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;     // import all files of java.util package 

/**
 * Write a description of class ball1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ball1 extends Actor
{
    /**
     * Act - do whatever the ball1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int enter = 0;          //integer variable to know which mode the player wants to play

    int counter_wall = 0, counter_baby = 0, counter_score = 0;              /* counter to set respective object for 1 time only, if this is not done, program will go adding objects 
                                                                                on each screen  */
    int speed = 4;      // speed of ball
    int speed_counter = 0;      // counter to increase ball speed
    int ang;        //for certain random angle
    int baby1_score = 0, baby2_score = 0;             //to count the score of babies
    
    int mode_set = 0;           // check if any mode is set or not  ...not for basic, intermediate or advance
    int current_mode = 0;           // to know current mode of game when paused
    public void act() 
    {
        if(mode_set == 0){
            
            if(Greenfoot.isKeyDown("1")){       // checking the user input to change game mode
                
                enter = 1;      
                mode_set = 1;                   /* mode set 1 is, we are playing game, and we could use escape to pause game */

            }else if(Greenfoot.isKeyDown("2")){
                
                enter = 2;
                mode_set = 1;                              
                
            }else if(Greenfoot.isKeyDown("3")){
                
                enter = 3;
                mode_set = 1;                                
                
            }
            
        }
        
        if(mode_set == 1){              // applies when mode_set = 1
            
            if(Greenfoot.isKeyDown("escape")){
                enter = 4;              // pause the game
                mode_set = 2;                   /* mode set 2 is we have pause game, now we can choose new game mode or resume the game*/
            }
            
        }
        
        if(enter == 1){     // check the user input if input is 1 then basic mode is set
            basicMode();
        }else if(enter == 2){       // if input is 2 intermediate mode is set        
            intermediateMode();
        }else if(enter == 3){   // if input is 3 advance mode is set
            advanceMode();
        }else if(enter == 4){
            gamePaused();       // call method that pauses the game
            
            if(Greenfoot.isKeyDown("space")){       // for resuming game
                enter = current_mode;           // return to game where we paused
            }
            
        }else if (enter == 5){      // end of current game, shows options of new game 
            gameOver();
        }
    }
    
    public void basicMode(){/* code for basic mode. ball gets reflect when it touches a baby
                                */
            move(3);        // moves the ball 3 steps on the direction where the ball is headed.
            
            Actor net = getOneIntersectingObject(barrier.class);    // gives null value if ball is not in touch with barrier
            
            if (net!=null)      // check if ball is intersected with a barrier, even if ball gets intersect with barrier it goes forward with speed of 1 step 
            {
                move(1);
            }
                
            Actor baby_1 = getOneIntersectingObject(baby1.class);   // gives null value if ball is not in touch with baby1
            if (baby_1!=null)           // checks if ball is in touch with baby1 ie baby of left side
            {
                turn(180);      // if the ball touch the baby1 the ball direction is turn by 180 degree. ie went to reverse direction
                move(5);        // during in touch with baby the speed of baby gets 5 steps.
            }
            
            Actor baby_2 = getOneIntersectingObject(baby2.class);       // gives null value if ball is not in touch with baby2
            if (baby_2!=null)
            {
                turn(180);      // if the ball touch the baby2 the ball direction is turn by 180 degree. ie went to reverse direction
                move(5);        // during in touch with baby the speed of baby gets 5 steps.
            }
            
            current_mode = 1;
            
    }
    
    public void intermediateMode(){     // codes for intermediate mode.
        
        move(speed);            // ball movement speed
        createBabies();         // function that adds 2 babies on both side.
        intermediateReflection();           // reflection method of ball in intermediate mode
        scoreSystem();              // score system method calling
        current_mode = 2;       // sets current mode       
        
    }
    
    public void intermediateReflection(){
        int random = Greenfoot.getRandomNumber(90);      // set random number from 0 to 89 on a variable named random
        
        if(random % 2 == 0){                // perform conditional checking for even or odd number
            random = random * - 1;          // this code makes the random number either positive or negative randomly
        }
        
            /* the following code is similar to that of code of basicMode ball reflection,
               but some extra chunks of codes are added for the reflection of ball in random direction
               also codes for the reflection when the ball hit the wall is added
               */
              
            Actor net = getOneIntersectingObject(barrier.class);    
            if (net!=null)
            {
                move(1);
            }
                
            Actor baby_1 = getOneIntersectingObject(baby1.class);       /* ball and baby 1 touching */
            if (baby_1!=null)
            {
                
                turn(180 + random);     // makes the ball reflect on random direction after touching baby1
                move(5);
                
            }
            
            Actor baby_2 = getOneIntersectingObject(baby2.class);       /* ball and baby2 touching */
            if (baby_2!=null)
            {
                
                turn(180 + random);         // makes the ball reflect on random direction after touching baby2
                move(5);
                
            }
            
            if(isAtEdge() == true){     // isAtEdge() gives value true if the ball touches wall or edge of world
                
                if(getX() <= 10){
                    baby2Score();               //calls baby2Score() method
                }else if(getX() >= 590){
                    baby1Score();               //calls baby1Score() method
                }
                
                turn(180 + random);         // makes the ball reflect on random direction after touching 4 walls of the game screen
                move(5);
                
            }
    
    }
    
    public void advanceMode(){      // codes for advance mode 
        
        move(speed);
        createWall();           // calls createWall() method
        createBabies();             //calls createBabies() method
        intersect();            //calls intersect() method
        isWorldEnd();               //calls isWorldEnd() method
        scoreSystem();          //calls scoreSystem() method
        current_mode = 3;
        
        if(speed_counter % 1000 == 0){      // speed_counter value checking, if perfectly divisible by 1000, speed value is increased
            
            speed++;        // increase in speed value

        }
        
        speed_counter++;        // increament of speed_counter

    }
    
        
    public void createBabies(){     //this chunk of code creates extra 2 babies on each sideo of the team.
        
        if(counter_baby == 0){
            // this code creates babies at the left side//
            baby1 baby1 = new baby1();                  
            getWorld().addObject(baby1,108,85);
            baby1 baby12 = new baby1();                 // creates new object baby12 of baby1
            getWorld().addObject(baby12,108,319);       // set the object baby12 at position 158 px x-axis and 319px y-axis.
            
            // this code creates babies at the right side //
        
            baby2 baby23 = new baby2();
            getWorld().addObject(baby23,496,150);
            baby2 baby24 = new baby2();
            getWorld().addObject(baby24,496,360);
            
            
            
        }
        
        counter_baby++;
        }   //babies creating code ends here
        
        
        
   public void createWall(){           // codes that creates colorful wall. for better graphic
       
        if(counter_wall == 0){              // to create wall just once. if not this then game will lag
            
        for(int count = 0; count < 61 ; count += 1){        // this code creates new objects of xborder replicate it to cover whole screen. ie. less memory, red wall
            
            xborder xborder = new xborder();            
            getWorld().addObject(xborder, 10*count , 2);        // code that creates wall on top of screen
            xborder xborder2 = new xborder();            
            getWorld().addObject(xborder2, 10*count, 398);      //code that creates wall on bottom of screen

        }
        
        for(int count = 0; count < 41; count +=1){      // code for left and right border blue color
            
            yborder yborder = new yborder();
            getWorld().addObject(yborder, 2, 10*count);     // creates wall on right side
            yborder yborder2 = new yborder();
            getWorld().addObject(yborder2, 598, 10*count);      //creates wall on left side
            
        } 
        
    }
    
        counter_wall++;  // increament on counter_wall
   } 
    
   public void intersect(){  // code for ball intersecting babies this code is use for advance mode
            /* on this code the ball don't just get reflected randomly but it reflect in fix manner with very little random difference */
            
        ang = Greenfoot.getRandomNumber(78) - Greenfoot.getRandomNumber(25);            // set random number to numeric variable ang
        
        if(ang % 2 == 0){
            ang = ang * -1;
        }
            
        Actor baby_1 = getOneIntersectingObject(baby1.class);       //checks if the ball gets intersect with baby1
        Actor baby_2 = getOneIntersectingObject(baby2.class);       //checks if the ball gets intersect with baby2
        
        int roto = getRotation();       // put the current rotation angle of ball in roto variable
        
        
        /* the following set of codes is used so that the ball only gets reflect when it wents toward baby from front side of baby
         * i.e is if the ball goes to the baby from baby's back side then the intersection between baby and ball will be ignored.
         * where as when the ball hits the baby on it's front side the ball will get reflected by 180 +- some value.
           */
        if( roto < 90 || roto > 270 ){      //checks from which side the ball is arriving to the babies, and perform activity as required.
            
                                    /* for baby2 i.e baby on right side [ (roto < 90 || roto > 270)= true ] means that the ball is heading toward the baby from front side
                                       thus the reflection is done by following codes*/
            if (baby_2 != null){        // checks if the ball had touch the baby2 or not
                turn(180 - ang);
                move(5);
                ballSound();
            }
            
        }else{  
                                   /* the remaining angle would be front side for baby1 */
            if (baby_1!=null){          // checks if the ball had touch the baby1 or not
                turn(180 - ang);
                move(5);
                ballSound();
            }
            
        }
   }
    
    
    public void isWorldEnd(){ //code for reflection of ball on wall this code is only use for advance mode 
                                    
                            /* for advance mode the reflection on wall code is modified, for better and more natural gaming experience.
                             * the reflection is no more randomized
                             * by this code reflection of ball depends on intersection angle of ball and wall, and the direction of ball.
                               */
                              
        if(getY() <= 10){   // this code is for reflection of ball on the top part of the wall/border
            
             if(getRotation() < 270){       /* getRotation gets intersection angle, as per code if intersection angle is less than 270 degree then balls' rotation will be set to 135 degree
                                                that mimmic natural reflection*/                 
                setRotation(135); 
                ballWallSound();
            }
            
            if(getRotation() >= 270){
                
                setRotation(45);
                ballWallSound();                
            }
            
        }
        
        if(getY() >= 390){          // codes for reflection of ball on bottom part of border
            
            if(getRotation() >= 90){
                
                setRotation(225);
                ballWallSound();                
            }
            
            if(getRotation() < 90){
                
                setRotation(315); 
                ballWallSound();                
            }
            
        }

        if( getX() <= 10){      // codes for reflection of ball on the left side of border
            
            if(getRotation() >= 180){
                
                setRotation(315);
                ballWallSound();                
            }
            
            if(getRotation() < 180){
                
                setRotation(45);
                ballWallSound();                
            }
            baby2Score();           //baby2Score() function is called when ball touches left side of world.
                    
        }
        
        if(getX() >= 590){      //codes for reflection of ball on the right side of border

           if(getRotation() >= 270){
               
               setRotation(225);
                ballWallSound();               
            }
            
           if(getRotation() < 90){
               
               setRotation(135);
                ballWallSound();               
            }
            
            baby1Score();           //baby1Score() function is called when ball touches right side of world.

        }
    }
    
    
    
    public void baby1Score(){ //baby1Score() method.          
        baby1_score++;                          // value of baby1_score variable is increase by 1
        baby2_score--;                                  // value of baby2_score variable is decrease by 1
        
        score score01 = new score();          // creates score001 class new object
        score01.score001(baby1_score);          // call the constructor of score001
        getWorld().addObject(score01, 50, 20);              // adds the updated object of score001 in world
        
        score score02 = new score();                  // creates score002 class new object
        score02.score002(baby2_score);                  // call the constructor of score002
        getWorld().addObject(score02, 550, 20);                 // adds the updated object of score002 in world
        
        winner();                                   // calls the winner() method
    }
    
    
    
    public void baby2Score(){
        baby2_score++;                                  // value of baby2_score variable is increase by 1
        baby1_score--;                                        // value of baby1_score variable is decrease by 1      

        score score02 = new score();                  // creates score002 class new object
        score02.score002(baby2_score);                  // call the constructor of score002
        getWorld().addObject(score02, 550, 20);                 // adds the updated object of score002 in world
        
        score score01 = new score();          // creates score001 class new object
        score01.score001(baby1_score);          // call the constructor of score001
        getWorld().addObject(score01, 50, 20);              // adds the updated object of score001 in world
        
        winner();                                       // calls the winner() method.
    }
    
    
    public void scoreSystem(){      //score system method
        
        if(counter_score == 0){   // to apply for 1 time only, reduce lagging
            
          baby1Score();         // calls method
          baby2Score();         //calls method
          counter_score++;
          
        } 
        
    }
    
    
    
    public void winner(){   
                /* this method declares the winner of game when score reaches or exceeds the set value */
                
        if(baby1_score >= 5){       // checks the baby1_score if it reaches or exceeds the set value
            
            mode_set = 3;
            winSound();             // win sound method
            gameOver();         // calling gameover method
            youWin youwin = new youWin();       // creating new object 
            getWorld().addObject(youwin,304,202);       // displaying content of object ie image
            enter = 5;                                      
          
        }else if(baby2_score >=5){        // checks the baby1_score if it reaches or exceeds the set value
            
            mode_set = 3;
            loseSound();            // lose sound method called
            gameOver();            
            youLose youlose = new youLose();
            getWorld().addObject(youlose,304,202);          // lose message displayed
            enter = 5;
        
        }
        
    }
    
    
    
    
    public void gamePaused(){       // method that work to pause game and display pause message
        
        paused paused = new paused();               // new paused method
        getWorld().addObject(paused,304,202);               // display message on screen/ add object
            
        if(mode_set == 2){                          // condition checker 
            
            if(Greenfoot.isKeyDown("1")){
                
                Greenfoot.setWorld(new MyWorld());
                enter = 1;
                mode_set = 1;
                
            }else if(Greenfoot.isKeyDown("2")){
                
                Greenfoot.setWorld(new MyWorld());
                enter = 2;
                mode_set = 1;
                
            }else if(Greenfoot.isKeyDown("3")){
                
                Greenfoot.setWorld(new MyWorld());
                enter = 3;
                mode_set = 1;
                
            }else if(Greenfoot.isKeyDown("space")){
                
                mode_set = 1;
                
            }
            
        }
        
    }
    
    public void gameOver(){     // when game paused or finished then programme asks to choose mode for new game; this code does that.
        
        if(mode_set == 3){
            
            if(Greenfoot.isKeyDown("1")){               // checks what user press if true carries following tasks
                    Greenfoot.setWorld(new MyWorld());          // creates new world.. deletes all previous records
                    enter = 1;                              // enters the basic mode
                    mode_set = 1;                                                                           
                }else if(Greenfoot.isKeyDown("2")){
                    Greenfoot.setWorld(new MyWorld());          // creates new world
                    enter = 2;                                      // enters intermediate mode
                    mode_set = 1;
                }else if(Greenfoot.isKeyDown("3")){
                    Greenfoot.setWorld(new MyWorld());          // creates new world
                    enter = 3;                                        // enters advance mode
                    mode_set = 1;
            }
            
        }
        
    }
    
    /* all sound that are used */
    public void ballSound(){ // sound when ball hits baby
        Greenfoot.playSound("ball_sound.wav");        // sound addded
    }
    
    public void ballWallSound(){        //sound when ball hits wall
        Greenfoot.playSound("ball_wall.wav");
    }
    
    public void winSound(){             // sound when player wins the game
        Greenfoot.playSound("win_sound.wav");
    }
    
    public void loseSound(){            // sound when the player lose the game
        Greenfoot.playSound("lose_sound.wav");
    }
}
