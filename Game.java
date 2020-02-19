
/**
  **PURPOSE:[ this class  has two or more players that take turns attacking the other player’s ship ]
  */
import java.util.Scanner;
public class Game {
  
  private int turn=0; //varibale that tracks which players turn to attack 
  private Gameboard [] array;  //an array of Gameboard 
  private int height;   //height of gameboard array
  private int width;  //width of gameboard array 
  private boolean isOpponent;  //checks if the it is opponent board or not
  
    /**
 * constructor that fills the gameboard array 
 */
  public Game(int height,int width,int numPlayers) { 
   this.height=height;
   this.width=width;
   array=new Gameboard[numPlayers];
   
     int i=0;
   do{   
     if(i==0)
      isOpponent=false;
   else 
      isOpponent=true;
      array[i]=new Gameboard(height,width,isOpponent);
      i++;
   }while( i<array.length);
  }
  
  /**
 * this method produces random number from 0 to max
 */
  public static int random(int max) {
    return (int)(Math.random() * max);
  }
  
  /**
 * this method attacks a random ship in random row and column
 */
  public void randomAttack(){
    int randomBoard=random(array.length);
    int randomTileRow=random(height);
    int randomTileColumn=random(width);
    while(turn<array.length && randomBoard!=turn  && !array[randomBoard].hasLost()){
      array[randomBoard].doAttack(randomTileRow,randomTileColumn);
      System.out.println("Player "+randomBoard+" has been attacked at Row:"+randomTileRow+",Column:"+randomTileColumn);
      turn++;
    }
  }
    /**
 * this method attacks the player in provided row and column
 */
  public void attack(int playerID,int row,int column){
      array[playerID].doAttack(row,column);
    }
  
  /**
 * this method adds ship to the provided plaers's gameboard
 */
  public void addShip(int player,int row,int col,int length,int direction,char letter){
    array[player].addShip(row,col,length,direction,letter);
  }
  
    /**
 * this method plays the game where everyone has their turn
 * if Player 0 provides wrong input for player ID or row or column his turn will be gone
 * All the other player will have their turn
 */
  public  void playGame(){
    
    int activeShip=activeBoard();
    while(activeShip!=1){
      while(turn==0 ){
        if(array[0].hasLost()){
          turn++;
          break;
        }
        player();
        activeShip=activeBoard();
        
      }  
      while(turn>0 && turn<array.length && activeShip!=1){
        randomAttack();
        activeShip=activeBoard();
      }
     if(turn>=array.length && activeShip!=1){
      turn=0;
      activeShip=activeBoard();
     }
    }
    System.out.println("Congratulations Player "+turn+",You have won it!");  
  }
  
  /**
 * this is a helper method for playGame  method which counts how many active ship is there 
 */
  private int  activeBoard(){
    int index=0;
    for(int i=0;i<array.length;i++){
      if(!array[i].hasLost()){ 
        index++; 
      }
    }
      return index; 
  }
  
  /**
 * this is a helper method for playGame method 
 * this method asks player 0 to provide player id ,row and column to attack
 */
  private void player(){
    Scanner input=new Scanner(System.in);
    
    for(int i=0;i<array.length;i++){
      System.out.println(array[i]);
      }
      System.out.println("Enter a player ID to attack:");
      int playerID=input.nextInt();
      System.out.println("Enter the row to attack:");
      int row=input.nextInt();
      System.out.println("Enter the column to attack:");
      int column=input.nextInt();
    if(playerID>=array.length || playerID<=0){ 
      System.out.println("This player ID do not exist,You missed your turn!");
      turn++;
  } else if(row>=height || row<0) {
      System.out.println("Wrong input for row,You missed your turn!");
      turn++;
  } else if( column>=width || column<0){
      System.out.println("Wrong input for column,You missed your turn!");
      turn++;
  }else {
       attack(playerID,row,column);
       turn++;
      }
  }
}


