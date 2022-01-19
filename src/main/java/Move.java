public class Move {
  private Point point;
  private String color;
  
  public Move(Point point, String color) {
    this.point = point;
    this.color = color;
  }

  public Point getPoint() {
    return this.point;
  }

  public String getColor() {
    return this.color;
  }

  public void pass() {
    
  }

  public void resign() {

  }
}
