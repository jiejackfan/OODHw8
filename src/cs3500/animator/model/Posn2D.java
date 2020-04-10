package cs3500.animator.model;
import java.util.Objects;


/**
 * This class represents a 2D position Code for this class is borrowed from CS3500 practice exam.
 */
public final class Posn2D {

  private double x;
  private double y;

  /**
   * Initialize this object to the specified position.
   */
  public Posn2D(double x, double y) {
    this.setX(x);
    this.setY(y);
  }

  /**
   * Copy constructor.
   */
  public Posn2D(Posn2D v) {
    this.setX(v.x);
    this.setY(v.y);
  }

  /**
   * get the x coordinate of this position.
   */
  public double getX() {
    return x;
  }

  /**
   * get the y coordinate of this position.
   */
  public double getY() {
    return y;
  }

  /**
   * Set the x coordinate of this object.
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Set the y coordiante of this object.
   */
  public void setY(double y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Posn2D)) {
      return false;
    }

    Posn2D that = (Posn2D) a;

    return ((Math.abs(this.x - that.x) < 0.01)
        && (Math.abs(this.y - that.y) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }
}