/**
  *[ Gameboard ]
  *COMP1020 SECTION[A02]
  *INSTRUCTOR:[ Olivier Tremblay-Savard ]
  *NAME:[ Abdullah Al Noman]
  *ASSIGNMENT:[Assignment 2]
  *QUESTION:[ All 4 phases were completed]
  **PURPOSE:[ This class is a two-dimensional array of Tile objects, creating a game board for a single player]
  */
public class Gameboard {
  
  private Tile [][] player;   //variable that stores two dimensional tiles 
  private boolean isOpponent;   //variable that checks if it's opponent board or not
  public static final int UP=0,RIGHT=1,DOWN=2,LEFT=3;  //constant that controls the direction of the ship 
  
  /**
 * Constructor 
 */
  public Gameboard(int row,int column,boolean isOpponent) { 
    
    player=new Tile[row][column];
    this.isOpponent=isOpponent;
    for(int i=0;i<row;i++){
      for(int j=0;j<column;j++){
        player[i][j]=new Tile();
      }
    }   
  }
  
  /**
 * this method returns a string representation of the whole gameboard 
 */
  public String toString(){
    String s="";
    
    for(int i=0;i<player.length;i++){
      for(int j=0;j<player[i].length;j++){
        s+=player[i][j].getDisplay(isOpponent);
        if(j==player[i].length-1)
          s+="\n";
      }
    }
     
    return s;
  }
  
  /**
 * this method fetches a row from the gameboard and returns it as a string 
 */
  public String getRow(int rowNum){
    String row="";
    for(int i=0;i<player[rowNum].length;i++){
        row+=player[rowNum][i].getDisplay(isOpponent);
    }
    return row;
  }
  
  /**
 * sets the isAttacked flag in the provided,row and column 
 */
  public void doAttack(int row,int column){
    player[row][column].attack();
  }
  
  /**
 * this method extract a row from the tiles array
 */
  public Tile[] extractRow(int row){
    Tile[] temp=new Tile[player[row].length];
    for(int i=0;i<temp.length;i++){
      temp[i]=player[row][i];
  }
    return temp;
  }

/**
 * this method extract a column from the tiles array
 */
  public Tile[] extractColumn(int column){
     Tile[] temp=new Tile[player.length];
    for(int i=0;i<temp.length;i++){
      temp[i]=player[i][column];
  }
    return temp;
  }
  
  /**
 * this method reverse the provided array
 */
  public static void reverse(Tile[] data){
    int count=data.length-1;
    Tile [] temp=new Tile[data.length];
    for(int i=0;i<temp.length;i++){
      temp[i]=data[count];
      count--;
  }
    for(int i=0;i<data.length;i++){
      data[i]=temp[i];
  }
  }
  
  /**
 * this method addship to the tiles and also returns a boolean if ship can be placed or  not 
 */
  public boolean addShip(int row, int col,int length,int direction,char display){
    boolean bool=false;
    int start;
    Tile [] temp;
    if(direction==UP){
      temp=extractColumn(col);
      start=temp.length-1-row;
      reverse(temp);
    }else if(direction==RIGHT){
      temp=extractRow(row);
      start=col;  
    }else if(direction==DOWN){
      temp=extractColumn(col);
      start=row;
    }else{
      temp=extractRow(row);
      start=temp.length-1-col;
      reverse(temp);
    }
    if(canPlaceShipInArray(temp,start,length)){
      placeShipInArray(temp,start,length,display);
      bool=true;
    }
    return bool;
  }
  
  /**
 * this method checks if a ship can be placed in the provided tiles and from the start position
 */
  private static boolean canPlaceShipInArray(Tile [] tiles,int start,int length){
    boolean placeShip=(start+length-1)<tiles.length;
    
    for(int i=start;i<start+length && i<tiles.length;i++){
      if( !tiles[i].canPlaceShip())
        placeShip=false;
    }
    return placeShip;      
  }
  
 /**
 * this method places the ship in the provided tiles and start position
 */
  private static void placeShipInArray(Tile[]tiles,int start,int length,char letter){
    for(int i=start;i<(start+length);i++){
      tiles[i].setDisplayLetter(letter);
    }       
  }
  
 /**
 * this method checks if a gameboard has lost or not
 */
  public boolean hasLost(){
    boolean lost=true;
    for(int i=0;i<player.length;i++){
      for(int j=0;j<player[i].length;j++){
      if(player[i][j].activeship())
        lost=false;
     }
   }
     return lost;
 }
}

    