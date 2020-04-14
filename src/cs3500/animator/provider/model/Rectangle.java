package cs3500.animator.provider.model;

import cs3500.animator.provider.model.AbstractShape;
import java.awt.Color;

import cs3500.animator.provider.model.AbstractShape;
import cs3500.animator.provider.model.Posn2D;
import cs3500.animator.provider.model.Transformation;

/**
 * Represents a cs3500.animator.model.Rectangle.
 */
public class Rectangle extends AbstractShape {

  /**
   * Default Constructor.
   *
   * @param startTime time that this shape first appears in the animation
   * @param endTime   time that this shape will disappear from the animation
   * @param color     starting color for the shape
   * @param startPos  starting position of the shape
   * @param width     width of the shape
   * @param height    height of the shape
   * @param shapeId   Unique name for the shape, used to identify the shape in the animation
   */
  public Rectangle(int startTime, int endTime, Color color,
                   Posn2D startPos, double width, double height, String shapeId) {

    super(startTime, endTime, color, startPos, width, height, shapeId);

  }

  /**
   * Empty Constructor, used for the addShape() method in the builder.
   * @param shapeId Unique identifier of the shape being created
   */
  public Rectangle(String shapeId) {
    this.startTime = 0;
    this.endTime = 0;
    this.color = null;
    this.startPos = null;
    this.width = 0;
    this.height = 0;
    this.shapeId = shapeId;
  }

  @Override
  public String getShapeInfo() {
    StringBuilder result = new StringBuilder();

    result.append(String.format("shape %s rectangle\n", this.shapeId));

    for (Transformation t : this.transformations) {
      result.append(t.getTransformInfo() + "\n");
    }
    return result.toString();
  }
}
