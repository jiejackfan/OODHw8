package cs3500.animator.provider;

import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.provider.model.Posn2D;
import cs3500.animator.provider.view.EditorView;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import cs3500.animator.provider.model.Transformation;
import cs3500.animator.provider.view.EditorAnimationView;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IEditView;
import javax.swing.JButton;

/**
 *
 */
public class IEditViewAdapter implements IEditView {

  EditorAnimationView providerEdit;

  public IEditViewAdapter(ReadOnlyModel m, int width, int height, int x, int y, int speed) {
    providerEdit = new EditorView(width, height, new Posn2D(x, y), speed);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    providerEdit.addActionListener(listener);
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

  }

  @Override
  public void setOutputFileName(String outputFileName) {

  }

  @Override
  public void setDelay(int delay) {

  }
}