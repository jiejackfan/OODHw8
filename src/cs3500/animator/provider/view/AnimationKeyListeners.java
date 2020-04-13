package cs3500.animator.provider.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * Configures listeners for keyboard events.
 */
public class AnimationKeyListeners implements KeyListener {

  private Map<Character, Runnable> keyboardCharToAction;
  private Map<Integer, Runnable> keyPressToAction;
  private Map<Integer, Runnable> keyReleaseToAction;

  /**
   * Default constructor with empty that makes empty fields.
   */
  public AnimationKeyListeners() {
    this.keyboardCharToAction = Collections.emptyMap();
    this.keyPressToAction = Collections.emptyMap();
    this.keyReleaseToAction = Collections.emptyMap();

  }

  /**
   * Setter for typed keyboard characters.
   * @param charsToAction the given map
   */
  public void setKeyboardCharToAction(Map<Character, Runnable> charsToAction) {
    this.keyboardCharToAction = Objects.requireNonNull(charsToAction);
  }

  /**
   * Setter for key-pressed actions.
   * @param keyPressToAction the given map
   */
  public void setKeyPressToAction(Map<Integer, Runnable> keyPressToAction) {
    this.keyPressToAction = Objects.requireNonNull(keyPressToAction);
  }

  /**
   * Setter for key-released actions.
   * @param keyReleaseToAction the given map
   */
  public void setKeyReleaseToAction(Map<Integer, Runnable> keyReleaseToAction) {
    this.keyReleaseToAction = Objects.requireNonNull(keyReleaseToAction);
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {
    char cmd = keyEvent.getKeyChar();

    if (keyboardCharToAction.containsKey(cmd)) {
      keyboardCharToAction.get(cmd).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    Integer cmd = keyEvent.getKeyCode();

    if (keyPressToAction.containsKey(cmd)) {
      keyPressToAction.get(cmd).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {
    Integer cmd = keyEvent.getKeyCode();

    if (keyReleaseToAction.containsKey(cmd)) {
      keyReleaseToAction.get(cmd).run();
    }
  }
}
