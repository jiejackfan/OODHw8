package cs3500.animator.provider.view;

import cs3500.animator.provider.model.PShape;
import cs3500.animator.provider.model.Transformation;
import java.util.List;
import java.util.Map;

/**
 * This interface represents all operations supported for viewing an Animation.
 */
public interface AnimationView {

  /**
   * Displays a representation of the Animation model depending on the type of view
   * that is selected.
   */
  void draw();

  /**
   * Receives the transformations contained within the model for use in the draw() method.
   * @param transformations transformations that are to be drawn.
   */
  void getTransformations(List<Transformation> transformations);

  /**
   * Receives the shapes contained within the model for use in the draw() method.
   * @param shapes shapes that are to be drawn
   */
  void getShapes(Map<String, PShape> shapes);

  /**
   * Retrieve the final tick of the final transformation from the model in order to know when this
   * animation is finished.
   * @param tick final tick of the last transformation in the model.
   */
  void getEndTick(int tick);

  /**
   * Returns the output of the view in an Appendable.
   * @return
   */
  Appendable getOutput();


}
