public class Board {
  private String[][] grid;
  public static int size;

  public Board(final int boardSize) {
    Board.size = boardSize;
    this.grid = new String[size][size];
    
    int i, j = 0;
    for (i = 0; i < size; i++) {
      for (j = 0; j < size; j++) { 
        this.grid[i][j] = "."; 
      }
    }
  }

  public Boolean onBoard(Point point) {
    if (
      point.row >= 0 
      & point.row <= Board.size 
      & point.col >= 0
      & point.col <= Board.size) {
      return true;
    }

    return false;
  }
  
  public void placeStone(Move move) {
    Point point = move.getPoint();
    String color = move.getColor();
    
    if (onBoard(point) & grid[point.row][point.col].equals(".")) {  
      
      // check neigbors
      Point[] neighbors = point.neighbors();
      for (Point p : neighbors) {
        if (!onBoard(p)) {
          continue;
        }

      }

      PointSet stones = new PointSet();
      stones.add(point);

      //Group newGroup = new Group(player, stones, Liberties liberties);

      this.grid[point.row][point.col] = color;
    }
  }

  public void print() {
    for (int i = 0; i < Board.size; i++) {
      for (int j = 0; j < Board.size; j++) {
        System.out.print(this.grid[i][j]);
        System.out.print(" ") ;
      }
      System.out.println();
    }

    for (int n = 0; n < Board.size; n++) {
      System.out.print("*");
    }
    System.out.println();
  }
  
}
