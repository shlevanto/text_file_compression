/* 
aina kun laudalla asetetaan kivi, pitää tarkistaa:
1. onko naapurikohdissa olevilla ryhmillä vapauksia jäljellä
2. onko kivellä ja sen ryhmällä itsellään vapauksia jäljellä

*/ 

public class Group {
  private int color;
  
  // tätä varten täytyy koodata HashSet tai HashMap tietorakenne
  // tai sitten vapaudet on pelilaudan kokoinen boolean taulukko?
  private PointSet stones;
  private PointSet liberties;

  public Group(int color, PointSet stones, PointSet liberties) {
    this.color = color;
    this.stones = stones;
    this.liberties = liberties;
  }

  public void merge(Group other) {
    if (this.color != other.getColor()) {
      return;
    }

    PointSet combinedStones = other.getStones().merge(this.stones);
    PointSet combinedLiberties = other.getLiberties().merge(this.liberties);
    
    combinedLiberties.setMinus(combinedStones);

    this.liberties = combinedLiberties;
    this.stones = combinedStones;

  }

  public PointSet getStones() {
    return this.stones;
  }

  public PointSet getLiberties() {
    return this.liberties;
  }

  public int numLiberties() {
    return this.liberties.size();
  }

  public int getColor() {
    return this.color;
  }

}