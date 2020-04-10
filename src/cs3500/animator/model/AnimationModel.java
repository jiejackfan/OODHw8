package cs3500.animator.model;

import java.util.List;
import java.util.Map;

/**
 * This interface represents an animation of {@code Shapes} on a 2D surface. The animation origin at
 * (0, 0) is the top-left corner of the screen.
 */

public interface AnimationModel {

  /**
   * Adds the given type of shape and its ID to this animation. The given ID is used as a unique
   * identifier to a specific cs3500.animator.model.Shape.
   *
   * @param id    A unique string identifier for the shape that is to be added
   * @param shape The {@code cs3500.animator.model.Shape} that is to be added
   * @throws IllegalArgumentException if the given ID already exists in the animation
   */
  void addShape(String id, Shape shape) throws IllegalArgumentException;

  /**
   * Removes the shape with the given ID from the animation.
   *
   * @param id The ID of the shape to be removed.
   * @throws IllegalArgumentException if the shape doesn't exist in the animation
   */
  void removeShape(String id) throws IllegalArgumentException;

  /**
   * Adds the given transformation to the shape that is identified by the given
   * cs3500.animator.model.Shape ID.
   *
   * @param id The unique ID of the shape that the transformation is applied to
   * @param t  cs3500.animator.model.Transformation that is to be applied to the shape with the
   *           given ID
   */
  void addTransformation(String id, Transformation t);

  /**
   * Removes the given transformation from the shape that is identified by the given shape ID.
   *
   * @param id The unique ID of the shape that the transformation should be removed from
   * @param t  cs3500.animator.model.Transformation that is to be removed
   */
  void removeTransformation(String id, Transformation t);

  /**
   * Produces a textual description of the shapes in this animation model and the transformations
   * that are applied to those shapes in the format shown in the assignment description (2.1).
   *
   * @return
   */
  String output();

  /**
   * Produces a list of the shapes that are in this animation.
   *
   * @return
   */
  Map<String, Shape> getShapeList();

  /**
   * Produces a list of the transformations that are applied to each shape in this animation model.
   *
   * @return
   */
  List<Transformation> getTransformationList();

  /**
   * Gets the time at which this animation will end.
   *
   * @return
   */
  int getEndTick();

  /**
   * Gets the height of the window where this animation will be played.
   *
   * @return
   */
  int getHeight();

  /**
   * Gets the width of the window where this animation will be played.
   *
   * @return
   */
  int getWidth();

  /**
   * Gets the location of the top-left corner of the window where this animation will be played.
   *
   * @return
   */
  Posn2D getWindowLocation();

  /**
   * Adds the given keyframe of a shape to the animation.
   *
   * @param shapeId name of the shape
   * @param startTime start time of this keyframe
   * @param x starting x position of this keyframe
   * @param y starting y position of this keyframe
   * @param width starting width of this keyframe
   * @param height starting height of this keyframe
   * @param r starting red color value
   * @param g starting green color value
   * @param b starting blue color value
   */
  void addKeyFrame(String shapeId, int startTime, int x, int y, int width,
      int height, int r, int g, int b);

  /**
   * Modifies the keyframe of a shape at the given time.
   *
   * @param shapeId name of the shape
   * @param startTime start time of this keyframe
   * @param x starting x position of this keyframe
   * @param y starting y position of this keyframe
   * @param width starting width of this keyframe
   * @param height starting height of this keyframe
   * @param r starting red color value
   * @param g starting green color value
   * @param b starting blue color value
   */
  void modifyKeyFrame(String shapeId, int startTime, int x, int y, int width,
      int height, int r, int g, int b);

  /**
   * Removes the keyframe of the given shape at the given time.
   *
   * @param shapeId name of the shape where this keyframe is removed from
   * @param parseInt time that this keyframe is removed from the animation
   */
  void removeKeyFrame(String shapeId, int parseInt);
}
