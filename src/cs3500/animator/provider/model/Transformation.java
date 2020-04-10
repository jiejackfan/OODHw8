package cs3500.animator.provider.model;

import java.awt.Color;

/**
 * This interface represents the possible transformations that are applied to a shape over the
 * course of the animation.
 */
public interface Transformation {

  int getStartTick();

  int getEndTick();

  Posn2D getStartPosn();

  Posn2D getEndPosn();

  int getStartWidth();

  int getStartHeight();

  int getEndWidth();

  int getEndHeight();

  Color getStartColor();

  Color getEndColor();

  Shape getShape();


  /**
   * This method will return information about the transformation on a single line in this format. #
   *                  start                           end #        --------------------------
   * ---------------------------- #        t  x   y   w  h   r   g  b    t   x   y   w  h   r   g
   * b
   *
   * @return string representation of the transformation
   */
  String getTransformInfo();

  void setStartPosn(Posn2D endPosn);
}
