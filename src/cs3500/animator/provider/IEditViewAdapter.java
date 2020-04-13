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
import java.awt.event.KeyListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.animator.provider.model.Transformation;
import cs3500.animator.provider.view.EditorAnimationView;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IEditView;
import java.util.Objects;
import javax.swing.JButton;

/**
 *
 */
public class IEditViewAdapter implements IEditView, ActionListener {

  EditorAnimationView providerEdit;
  IModel m;

  public IEditViewAdapter(IModel m, int width, int height, int x, int y, int speed) {
    providerEdit = new EditorView(m, width, height, new Posn2D(x, y), speed);
    this.m = m;
  }

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
  public void addActionListener(ActionListener listener) {
    providerEdit.addActionListener(this);
    this.setKeyListeners();
  }

  @Override
  public boolean getCheckState() {
    return false;
  }

  @Override
  public void changeResumeButtonColor(Color color) {

  }

  @Override
  public List<String> getShapePanelInput() {
    return null;
  }

  @Override
  public List<String> getInsertPanelInput() {
    return null;
  }

  @Override
  public List<String> getEditPanelInput() {
    return null;
  }

  @Override
  public void showErrorMsg(String string) {

  }

  @Override
  public File getLoadLocation() {
    return null;
  }

  @Override
  public File getSaveLocation() {
    return null;
  }

  @Override
  public void clearAnimatorPanel() {

  }

  @Override
  public void loadNewAnimatorPanel(ReadOnlyModel m, int width, int height) {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void render() {
    providerEdit.getEndTick(m.getMaxTick());
    providerEdit.start();
    providerEdit.draw();
  }

  @Override
  public void setOutputFileName(String outputFileName) {

  }

  @Override
  public void setDelay(int delay) {

  }

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
  /*
  @Override
  public void keyTyped(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP: //toggle color
        providerEdit.increaseSpeed();
        break;
      case KeyEvent.VK_DOWN:
        providerEdit.decreaseSpeed();
        break;
      case KeyEvent.VK_L:
        providerEdit.toggleLooping();
        break;
      default:
        break;
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }
  */
}