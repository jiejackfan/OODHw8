package cs3500.animator.provider;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;

import cs3500.animator.provider.model.Transformation;
import cs3500.animator.provider.view.EditorAnimationView;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IEditView;

/**
 *
 */
public class IEditViewAdapter implements EditorAnimationView {

  IEditView editView = new EditView();

  @Override
  public void start() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void restart() {

  }

  @Override
  public void toggleLooping() {

  }

  @Override
  public void increaseSpeed() {

  }

  @Override
  public void decreaseSpeed() {

  }

  @Override
  public void displayErrorMessage(String s) {

  }

  @Override
  public void addActionListener(ActionListener ae) {

  }

  @Override
  public void addKeyListener(KeyListener kl) {

  }

  @Override
  public void resetFocus() {

  }

  @Override
  public String getIdField() {
    return null;
  }

  @Override
  public String getShapeOption() {
    return null;
  }

  @Override
  public String getTickField() {
    return null;
  }

  @Override
  public String getXField() {
    return null;
  }

  @Override
  public String getYField() {
    return null;
  }

  @Override
  public String getWidthField() {
    return null;
  }

  @Override
  public String getHeightField() {
    return null;
  }

  @Override
  public String getRedField() {
    return null;
  }

  @Override
  public String getBlueField() {
    return null;
  }

  @Override
  public String getGreenField() {
    return null;
  }

  @Override
  public String getEditOption() {
    return null;
  }

  @Override
  public void draw() {

  }

  @Override
  public void getTransformations(List<Transformation> transformations) {

  }

  @Override
  public void getShapes(Map<String, Shape> shapes) {

  }

  @Override
  public void getEndTick(int tick) {

  }

  @Override
  public Appendable getOutput() {
    return null;
  }
}
