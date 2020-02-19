/**
  *[ Tile ]
  *COMP1020 SECTION[A02]
  *INSTRUCTOR:[ Olivier Tremblay-Savard ]
  *NAME:[ Abdullah Al Noman]
  *ASSIGNMENT:[Assignment 2]
  *QUESTION:[ All 4 phases were completed]
  **PURPOSE:[ The class is a representation of a single space on the board ]
  */
public class Tile {
  private boolean wasAttacked;  //boolean variable  which check if a ship was attacked or not 
  private boolean hasShip;     //boolean variable which check if a tile already has a ship or not 
  private char  displayLetter;  //variable which sets the character to show in display 
  
   /**
 * Consturctor sets displayLetter to the character '~'
 */
  public Tile() { 
    
    setDisplayLetter('~');
    wasAttacked=false;
    hasShip=false;
    
  }
  
   /**
 * mutator/setter method for displayLetter
 * sets hasShip to true
 */
  public void setDisplayLetter(char letter){
    displayLetter=letter;
    hasShip=true;
  }
  
   /**
 * this mehtod shows the tile to the user 
 */
  public  String getDisplay(boolean isOpponent){
    String temp="";
    if(isOpponent==false){
      if(!wasAttacked && hasShip)
        temp+=displayLetter;
      if(!wasAttacked && !hasShip)
        temp+="~";
      if(wasAttacked && !hasShip)
        temp+="^";
      if(wasAttacked && hasShip)
      temp+="*";
    }
    if(isOpponent){
      if(!wasAttacked)
        temp+="~";
       if(wasAttacked && !hasShip)
        temp+="^";
      if(wasAttacked && hasShip)
        temp+="*";
    }
        return temp;
  }
  
   /**
 * this method sets wasAttacked to true 
 */
  public void attack(){
    wasAttacked=true;
  }
  
   /**
 * this methods checks if a ship can be placed
 */
  public boolean canPlaceShip(){
    boolean placeShip=false;
    if(!wasAttacked && !hasShip)
      placeShip=true;
    return placeShip;
  }
  
   /**
 * this method checks if there is an activeship or not 
 */
  public boolean activeship(){
    boolean shipActive=false;
    if(hasShip && !wasAttacked)
      shipActive=true;
    return shipActive;
  }
      
}
