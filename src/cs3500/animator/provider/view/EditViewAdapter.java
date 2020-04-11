package cs3500.animator.provider.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.provider.model.Transformation;
import cs3500.animator.provider.view.EditorAnimationView;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IEditView;

/**
 *
 */
public class EditViewAdapter implements IEditView {

  EditorAnimationView providerView;

  /**
   * The constructor of EditViewAdapter.
   */
  public EditViewAdapter(EditorAnimationView providerView) {
    this.providerView = providerView;
  }

  @Override
  public void addActionListener(ActionListener listener) {

  }

  @Override
  public boolean getCheckState() {
    return false;
  }

  @Override
  public JButton getPlayButton() {
    return null;
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
  public void render() { providerView.draw(); }

  @Override
  public void setOutputFileName(String outputFileName) {

  }

  @Override
  public void setDelay(int delay) {

  }
}
