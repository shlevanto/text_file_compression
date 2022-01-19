// do this with HashSet instead, lookup is O(1)
// merge with addAll method

public class PointSet {
  private int size;
  private int index;
  private Point[] set;

  public PointSet() {
    this.size = 0;
    this.index = 0;
    this.set = new Point[Board.size^2];
  }

  public void add(Point point) {
    if (contains(point)) {
      return;
    }

    this.set[index] = point;
    this.index++;
    this.size++;
  }

  public void remove(Point point) {
    Point[] newSet = new Point[Board.size^2];
        
    for (int i = 0; i < this.size; i++) {
      if (this.set[i].equals(point)) {
        for (int j = 0; j < i; j++) {
          newSet[j] = this.set[j];
        }
        for (int k = i; k < this.size - 1; k++) {
          newSet[k] = this.set[k+1];
        }
      }
    }
    this.set = newSet;
    this.size--;
    this.index--;
  }

  private boolean contains(Point point) {
    for (int i = 0; i < this.set.length; i++) {
      if (point.equals(this.set[i])) {
        return true;
      }
    }
    return false;
  }

  public int size() {
    return this.size;
  }

  public Point[] content() {
    return this.set;
  }

  public void setMinus(PointSet other) {
    for (int i = 0; i < other.size(); i++) {
      this.remove(other.content()[i]);
    }
  }

  public PointSet merge (PointSet other) {
    Point[] content = other.content();
    PointSet newPointSet = new PointSet();

    for (int i = 0; i < this.size; i++) {
      newPointSet.add(this.set[i]);
    }
    for (int i = 0; i < other.size(); i++) {
      newPointSet.add(content[i]);
    }

    return newPointSet;
  }

  public void print() {
    for (int i = 0; i < this.size; i++) {
      System.out.println(this.set[i]);
    }
  }
  
}
