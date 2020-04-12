package cs3500.animator.provider.model;

import cs3500.animator.provider.model.Posn2D;
import cs3500.animator.provider.model.Transformation;
import java.awt.Color;

/**
 * Concrete implementation of a Transformation that can be applied to a shape.
 */
public class TransformationBuilder implements Transformation {

  protected PShape shape;
  protected int startTick;
  protected int endTick;
  protected Posn2D startPos;
  protected Posn2D endPos;
  protected Color startColor;
  protected Color endColor;
  protected int startHeight;
  protected int startWidth;
  protected int endHeight;
  protected int endWidth;
  protected String shapeId;

  /**
   * Default constructor of a possible shape transformation.
   * @param shape Shape that this transformation will be applied to
   * @param shapeId Unique name of the shape that this transformation will be applied to
   * @param startTick Start time of this transformation
   * @param endTick end time of this transformation
   */
  public TransformationBuilder(PShape shape, String shapeId, int startTick, int endTick) {
    Transformation prev;
    if (shape.getTransformations().size() > 0) {
      prev = shape.getTransformations().get( shape.getTransformations().size() - 1 );

      this.startPos = prev.getEndPosn();
      this.endPos = this.startPos;

      this.startColor = prev.getEndColor();
      this.endColor = this.startColor;

      this.startHeight = (int) Math.round(prev.getEndHeight());
      this.endHeight = this.startHeight;

      this.startWidth = (int) Math.round(prev.getEndWidth());
      this.endWidth = this.startWidth;
    }
    else {
      this.startPos = shape.getStartPos();
      this.endPos = getEndPosn();

      this.startColor = shape.getColor();
      this.endColor = getEndColor();

      this.startHeight = (int) Math.round(shape.getHeight());
      this.endHeight = getEndHeight();

      this.startWidth = (int) Math.round(shape.getWidth());
      this.endWidth = getEndWidth();
    }

    this.shape = shape;
    this.shapeId = shapeId;

    this.startTick = startTick;
    this.endTick = endTick;
    validTicks();
  }


  /**
   * Setter for the position of this transformation.
   * @param startPos starting position of the shape when this transformation begins
   * @param endPos ending position of the shape when this transformation ends
   * @return
   */
  public TransformationBuilder setPosn(Posn2D startPos, Posn2D endPos) {
    this.startPos = startPos;
    this.endPos = endPos;
    return this;
  }

  /**
   * Setter for the color of the shape over the course of this transformation.
   * @param startColor starting color of the shape when this transformation begins
   * @param endColor ending color of the shape when this transformation ends
   * @return
   */
  public TransformationBuilder setColor(Color startColor, Color endColor) {
    this.startColor = startColor;
    this.endColor = endColor;
    return this;
  }

  /**
   * Setter for the dimensions of the shape when this transformation begins.
   * @param startHeight Height of the shape when this transformation begins
   * @param startWidth Width of the shape when this transformation begins
   * @param endHeight Height of the shape when this transformation ends
   * @param endWidth Width of the shape when this transformation ends
   * @return
   */
  public TransformationBuilder setScale(int startHeight, int startWidth,
      int endHeight, int endWidth) {
    this.startHeight = startHeight;
    this.endHeight = endHeight;
    this.startWidth = startWidth;
    this.endWidth = endWidth;
    return this;
  }

  /**
   * Private helper to check the validity of the timing of this transformation. Start and end times
   * of the transformation have to be positive. The end time of the transformation cannot be less
   * than the start time.
   */
  private void validTicks() {
    if (this.startTick < 0 || this.endTick < 0) {
      throw new IllegalArgumentException("Start/End times cannot be negative.");
    } else if (this.endTick < this.startTick) {
      throw new IllegalArgumentException("End time cannot be less than start time.");
    }
  }

  //GETTERS
  @Override
  public int getStartTick() {
    return startTick;
  }

  @Override
  public int getEndTick() {
    return endTick;
  }

  @Override
  public Posn2D getStartPosn() {
    return startPos;
  }

  @Override
  public Posn2D getEndPosn() {
    return endPos;
  }

  @Override
  public int getStartHeight() {
    return startHeight;
  }

  @Override
  public int getEndHeight() {
    return endHeight;
  }

  @Override
  public int getStartWidth() {
    return startWidth;
  }

  @Override
  public int getEndWidth() {
    return endWidth;
  }

  @Override
  public Color getStartColor() {
    return startColor;
  }

  @Override
  public Color getEndColor() {
    return endColor;
  }

  @Override
  public PShape getShape() {
    return shape;
  }

  @Override
  public String getTransformInfo() {
    StringBuilder result = new StringBuilder();

    result.append("motion ");
    result.append(shapeId + " ");
    result.append(getStartTick() + " ");
    result.append(getStartPosn().getX() + " ");
    result.append(getStartPosn().getY() + " ");
    result.append(getStartWidth() + " ");
    result.append(getStartHeight() + " ");
    result.append(getStartColor().getRed() + " ");
    result.append(getStartColor().getGreen() + " ");
    result.append(getStartColor().getBlue() + " ");

    result.append(getEndTick() + " ");
    result.append(getEndPosn().getX() + " ");
    result.append(getEndPosn().getY() + " ");
    result.append(getEndWidth() + " ");
    result.append(getEndHeight() + " ");
    result.append(getEndColor().getRed() + " ");
    result.append(getEndColor().getGreen() + " ");
    result.append(getEndColor().getBlue() + " ");

    return result.toString();
  }

  public void setStartPosn(Posn2D p) {
    startPos = p;
    endPos = p;
  }


}
