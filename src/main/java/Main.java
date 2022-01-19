import foo.Multiplier;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Board board = new Board(19);
    String[] cols = new String[] {
      "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T"};
    System.out.println(cols[2]);

    PointSet a  = new PointSet();
    Point p1 = new Point(1,1);
    Point p2 = new Point(1,7);
    Point p3 = new Point(1,2);
    Point p4 = new Point(5,2);

    a.add(p1);
    a.add(p2);
    a.add(p3);
    a.add(p4);
    a.print();

    System.out.println();

    a.remove(p2);
    a.print();
  
    System.out.println();

    a.add(new Point(8,8));
    a.print();
    System.out.println(a.size());
    
    PointSet b = new PointSet();
    b.add(new Point(9,9));
    b.print();
    PointSet c = a.merge(b);
    System.out.println("***");
    c.print();
    System.out.println("***");
    c.setMinus(a);
    c.print();
    System.out.println("***");
    

  }

}

