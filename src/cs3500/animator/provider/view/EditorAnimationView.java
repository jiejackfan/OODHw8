
package cs3500.animator.provider.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * This interface defines all the operations that allows a user to edit the associated animation.
 */
public interface EditorAnimationView extends AnimationView {

  /**
   * Visually play the animation.
   */
  void start();

  /**
   * Pause the animation.
   */
  void pause();

  /**
   * Restart the animation.
   */
  void restart();

  /**
   * Enable restarting of the animation.
   */
  void toggleLooping();

  /**
   * Increase the speed of the animation.
   */
  void increaseSpeed();

  /**
   * Decrease the speed of the animation.
   */
  void decreaseSpeed();

  /**
   * Display the given error message in a error message window.
   * @param s message to be displayed
   */
  void displayErrorMessage(String s);

  /**
   * Adds a listener for action events that happen in this view.
   *
   * @param ae The listener to add.
   */
  void addActionListener(ActionListener ae);

  /**
   * Adds a listener for all keyboard events that happen in this view.
   * @param kl keylistener to be added
   */
  void addKeyListener(KeyListener kl);

  /**
   * Puts the focus back on the main frame of the view.
   */
  void resetFocus();

  /**
   * Returns the String in the ID text field.
   *
   * @return the String in the ID text field.
   */
  String getIdField();

  /**
   * Returns the String in the Shape text field.
   *
   * @return the String in the Shape text field.
   */
  String getShapeOption();

  /**
   * Returns the String in the tick text field.
   *
   * @return the String in the tick text field.
   */
  String getTickField();

  /**
   * Returns the String in the x text field.
   *
   * @return the String in the x text field.
   */
  String getXField();

  /**
   * Returns the String in the y text field.
   *
   * @return the String in the y text field.
   */
  String getYField();

  /**
   * Returns the String in the width text field.
   *
   * @return the String in the width text field.
   */
  String getWidthField();

  /**
   * Returns the String in the height text field.
   *
   * @return the String in the height text field.
   */
  String getHeightField();

  /**
   * Returns the String in the red text field.
   *
   * @return the String in the red text field.
   */
  String getRedField();

  /**
   * Returns the String in the blue text field.
   *
   * @return the String in the blue text field.
   */
  String getBlueField();

  /**
   * Returns the String in the green text field.
   *
   * @return the String in the green text field.
   */
  String getGreenField();

  /**
   * Returns a String describing the animation editing option selected.
   *
   * @return a String describing the animation editing option selected.
   */
  String getEditOption();


}
