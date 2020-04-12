package cs3500.animator.provider.model;

import cs3500.animator.provider.model.PShape;
import cs3500.animator.provider.model.Posn2D;
import cs3500.animator.provider.model.Transformation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that contains information common to all shapes that will exist in our animation.
 *
 * <p>
 * Transformations that are applied to *this* shape will be stored by the shape itself, not the
 * model
 * </p>
 */
public abstract class AbstractShape implements PShape {

  protected int startTime;
  protected int endTime;
  protected Color color;
  protected Posn2D startPos;
  protected double width;
  protected double height;
  protected String shapeId;
  protected ArrayList<Transformation> transformations;

  /**
   * Default constructor for a shape. The parameters below represent information that is common to
   * all shapes that will exist in our animation.
   *
   * @param startTime time that this shape first appears in the animation
   * @param endTime   time that this shape will disappear from the animation
   * @param color     starting color for the shape
   * @param startPos  starting position of the shape
   * @param width     width of the shape
   * @param height    height of the shape
   * @param shapeId   Unique name for the shape, used to identify the shape in the animation
   */
  public AbstractShape(int startTime, int endTime, Color color, Posn2D startPos, double width,
      double height, String shapeId) {
    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Start and end times of the shape have to be positive.");
    }
    if (startTime > endTime) {
      throw new IllegalArgumentException("Start time must be before end time.");
    }
    if (startPos == null) {
      throw new IllegalArgumentException("Starting position cannot be null.");
    }
    if (startPos.getX() < 0 || startPos.getY() < 0) {
      throw new IllegalArgumentException("Starting position of this shape have to be positive.");
    }
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException(
          "cs3500.animator.model.Shape cannot have negative dimensions");
    }
    if (shapeId == null) {
      throw new IllegalArgumentException("Shape ID cannot be negative.");
    }

    this.startTime = startTime;
    this.endTime = endTime;
    this.color = color;
    this.startPos = startPos;
    this.width = width;
    this.height = height;
    this.shapeId = shapeId;
    this.transformations = new ArrayList<>();
  }


  /**
   * Empty constructor, used for the addShape method in the model's builder.
   */
  public AbstractShape() {
    this.startTime = 0;
    this.startPos = new Posn2D(0, 0);
    this.width = 0;
    this.height = 0;
    this.transformations = new ArrayList<>();
  }

  public abstract String getShapeInfo();

  /**
   * Adds the given transformation to this shape.
   *
   * @param t transformation that is to be applied to this shape
   */
  @Override
  public void addTransformation(Transformation prev, Transformation t) {
    if (transformations.contains(t)) {
      throw new IllegalArgumentException("Given transformation already exists for this shape");
    } else if (prev == null) {
      transformations.add(t);
      this.setStartColor( t.getStartColor() );
      this.setDimensions( t.getStartHeight(), t.getStartWidth());
    } else {
      t.setStartPosn(prev.getEndPosn());
      if (t.getStartTick() != prev.getEndTick()) {
        throw new IllegalArgumentException("Shape cannot have time gaps in its transformations");
      }
      else {
        transformations.add(t);
      }
    }
  }

  @Override
  public void modifyTransformation(Transformation t) {
    for (Transformation trans : this.transformations) {
      if (trans.getStartTick() == t.getStartTick()) {
        this.transformations.remove( trans );
        this.transformations.add( t );
      }
    }
  }

  /**
   * Removes the given transformation from this shape.
   *
   * @param t transformation that is to be removed from this shape
   */
  public void removeTransformation(Transformation t) {
    if (!transformations.contains(t)) {
      throw new IllegalArgumentException("Given transformation does not exist for the given shape");
    } else {
      transformations.remove(t);
    }
  }

  /**
   * Return a list of all transformations that are applied to this shape.
   *
   * @return
   */
  public List<Transformation> getTransformations() {
    return new ArrayList<>(transformations);
  }

  //GETTERS
  @Override
  public int getStartTime() {
    return startTime;
  }

  @Override
  public int getEndTime() {
    return endTime;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public Posn2D getStartPos() {
    return startPos;
  }

  @Override
  public double getWidth() {
    return width;
  }

  @Override
  public double getHeight() {
    return height;
  }

  @Override
  public String getShapeId() {
    return shapeId;
  }

  public void setStartColor(Color c) {
    color = c;
  }

  public void setDimensions(int h, int w) {
    height = h;
    width = w;
  }

}
