public class Point {
  public int row;
  public int col;

  public Point(int y, int x) {
    this.row = y;
    this.col = x;
  }

  public Point[] neighbors() {
    Point left = new Point(this.row, this.col - 1);
    Point right = new Point(this.row, this.col + 1);
    Point above = new Point(this.row - 1, this.col);
    Point below = new Point(this.row + 1, this.col);

    Point[] neighborList = new Point[4];
    neighborList[0] = left;
    neighborList[1] = right;
    neighborList[2] = above;
    neighborList[3] = below;
    
    return neighborList;
  }

  @Override
  public String toString() {
    return
      "("
      + Integer.toString(this.row) 
      + ","
      + Integer.toString(this.col)
      + ")";
  }

  @Override 
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (getClass() != o.getClass()) {
      return false;
    }
    
    Point point = (Point) o;
    
    return (row == point.row) & (col == point.col);
  }
}

