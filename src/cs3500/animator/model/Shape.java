
package cs3500.animator.model;

import java.awt.Color;
import java.util.List;

/**
 * This interface represents a 2D shape that can be animated via a {@code
 * cs3500.animator.model.Transformation}.
 */
public interface Shape {

  /**
   * Produces a string representation of this shape in the form of "shape XX typeofshape" where XX
   * is replaced by the unique string ID of the shape followed by the type of shape. (e.g. shape R
   * rectangle).
   *
   * @return String that includes this shape's unique ID and type
   */
  String getShapeInfo();

  /**
   * Adds a transformation to this shape.
   *
   * @param t transformation that is to be applied to this shape
   */
  void addTransformation(Transformation prev, Transformation t);

  /**
   * Remove the given transformation from this shape.
   *
   * @param t transformation that is to be removed from this shape
   */
  void removeTransformation(Transformation t);

  /**
   * Return a list of transformations that are to be applied to this shape.
   *
   * @return list of transformations
   */
  List<Transformation> getTransformations();

  /**
   * Modifies the transformation that overlap's with the given transformation's start/end ticks.
   * @param t transformation to be modified
   */
  void modifyTransformation(Transformation t);

  int getStartTime();

  int getEndTime();

  Color getColor();

  Posn2D getStartPos();

  double getWidth();

  double getHeight();

  String getShapeId();

}
