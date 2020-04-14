package cs3500.animator.provider;

import cs3500.animator.model.IModel;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.provider.model.Posn2D;
import cs3500.animator.provider.view.AnimationKeyListeners;
import cs3500.animator.provider.view.EditorView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.animator.provider.view.EditorAnimationView;
import cs3500.animator.view.IEditView;
import java.util.Objects;
import javax.swing.JButton;

/**
 * The view adpater to adapt the Provider EditorView to our View. This adapter needs to understand
 *  our view, therefore it implements IEditView. This adpater also needs to take care of button and
 *  key listening specific to the Provider EditView. In order to control the EditorView, there will
 *  be a delegate object of the EditorView. This adapter handles initialization of provider's
 *  EditorView, handles key & button events, help render the panel.
 */
public class IEditViewAdapter implements IEditView, ActionListener {

  EditorAnimationView providerEdit;
  IModel m;

  /**
   * Constructor function being called in main function ExcellenceCLI to initialize the adapter and
   *  also initialize the delegate Provider EditorView.
   * @param m a model so EditorView and Panel can get access to our model.
   * @param width of the animation panel as an integer.
   * @param height of the animation panel as an integer.
   * @param x value of the window location.
   * @param y value of the window location.
   * @param speed that the animation will intialize with.
   */
  public IEditViewAdapter(IModel m, int width, int height, int x, int y, int speed) {
    providerEdit = new EditorView(m, width, height, new Posn2D(x, y), speed);
    this.m = m;
  }

  @Override
  public void addActionListener(ActionListener listener) {
    //tell the EditorView that the adapter is button press's listener.
    providerEdit.addActionListener(this);

    //sets up key listener.
    this.setKeyListeners();
  }

  /**
   * Sets up the keyListener class and set the EditView as the keyListener. Assign up key press to
   *  run increaseSpeed() in EditorView. Assign down key press to run decreaseSpeed() in EditorView.
   *  Assign "l" key to enable or disable looping.
   */
  private void setKeyListeners() {
    Map<Character, Runnable> keyboardCharToAction = new HashMap<>();
    Map<Integer, Runnable> keyPressToAction = new HashMap<>();
    Map<Integer, Runnable> keyReleaseToAction = new HashMap<>();
    AnimationKeyListeners listener = new AnimationKeyListeners();

    keyPressToAction.put(KeyEvent.VK_UP, () -> {
      providerEdit.increaseSpeed();
    });
    keyPressToAction.put(KeyEvent.VK_DOWN, () -> {
      providerEdit.decreaseSpeed();
    });
    keyboardCharToAction.put('l', () -> providerEdit.toggleLooping());

    listener.setKeyboardCharToAction(keyboardCharToAction);
    listener.setKeyPressToAction(keyPressToAction);
    listener.setKeyReleaseToAction(keyReleaseToAction);
    providerEdit.addKeyListener(listener);
  }

  @Override
  public void render() {
    providerEdit.getEndTick(m.getMaxTick());
    providerEdit.start();
    providerEdit.draw();
  }

  /**
   * Handles the button events.
   * @param e a button event.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (Objects.requireNonNull(e.getActionCommand())) {
      case "Play Button":
        providerEdit.start();
        providerEdit.resetFocus();
        break;
      case "Pause Button" :
        providerEdit.pause();
        providerEdit.resetFocus();
        break;
      case "Restart Button":
        providerEdit.restart();
        providerEdit.resetFocus();
        break;
      case "Do Edit Button":
        String editOption = providerEdit.getEditOption();
        String shapeId = providerEdit.getIdField();
        try {
          switch (editOption) {
            case "Add key frame RB":
              m.insertKeyframe(
                  shapeId,
                  Integer.parseInt(providerEdit.getTickField())
              );
              break;
            case "Modify key frame RB":
              m.modifyKeyframe(
                  shapeId,
                  Integer.parseInt(providerEdit.getTickField()),
                  Integer.parseInt(providerEdit.getXField()),
                  Integer.parseInt(providerEdit.getYField()),
                  Integer.parseInt(providerEdit.getWidthField()),
                  Integer.parseInt(providerEdit.getHeightField()),
                  Integer.parseInt(providerEdit.getRedField()),
                  Integer.parseInt(providerEdit.getGreenField()),
                  Integer.parseInt(providerEdit.getBlueField())
              );
              break;
            case "Remove key frame RB":
              m.deleteKeyframe(shapeId, Integer.parseInt(providerEdit.getTickField()));
              break;
            case "Add shape RB":
              switch (providerEdit.getShapeOption()) {
                case "Rectangle RB":
                  m.createShape("rectangle", shapeId);
                  break;
                case "Ellipse RB":
                  m.createShape("ellipse", shapeId);
                  break;
                default:
                  throw new IllegalArgumentException("Unsupported shape.");
              }
              break;
            case "Remove shape RB":
              m.removeShape(shapeId);
              break;
            default:
              throw new IllegalArgumentException("Unsupported editing option.");
          }
        } catch (IllegalArgumentException iae) {
          providerEdit.displayErrorMessage(iae.getMessage());
        }
      default:
        break;
    }
  }

  @Override
  public boolean getCheckState() {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }

  @Override
  public JButton getPlayButton() {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }

  @Override
  public void changeResumeButtonColor(Color color) {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }

  @Override
  public List<String> getShapePanelInput() {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }

  @Override
  public List<String> getInsertPanelInput() {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }

  @Override
  public List<String> getEditPanelInput() {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }

  @Override
  public void showErrorMsg(String string) {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }

  @Override
  public File getLoadLocation() {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }

  @Override
  public File getSaveLocation() {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }

  @Override
  public void clearAnimatorPanel() {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }

  @Override
  public void loadNewAnimatorPanel(ReadOnlyModel m, int width, int height) {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }

  @Override
  public void setOutputFileName(String outputFileName) {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }

  @Override
  public void setDelay(int delay) {
    throw new UnsupportedOperationException("Provider view adapter doesn't need this method");
  }


}