package cs3500.animator.controller;

/**
 * Represents a controller for an animation.
 * <p>Classes implementing this interface should be able to accept user-input from mouse-click and
 * keyboard events in order to inform the model of the appropriate action.</p>
 */
public interface AnimationController {

  /**
   * Configures the listeners on the view and starts the animation.
   */
  void start();

}
