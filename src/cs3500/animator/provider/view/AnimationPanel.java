package cs3500.animator.provider.view;

import cs3500.animator.model.Ellipse;
import cs3500.animator.provider.model.Posn2D;
import cs3500.animator.model.Rectangle;
import cs3500.animator.provider.model.Shape;
import cs3500.animator.provider.model.Transformation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

/**
 * Drawing panel for the visual view.
 */
public class AnimationPanel extends JPanel {

  private List<Transformation> transformations;
  private Map<String, Shape> shapes;
  private int currentTick;

  /**
   * Default constructor for a panel.
   */
  public AnimationPanel() {
    this.transformations = new ArrayList<Transformation>();
    this.shapes = new HashMap<String, Shape>();
    setPreferredSize(new Dimension(500, 500));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (Shape s : this.shapes.values()) {
      for (Transformation t : s.getTransformations()) {
        tweenShape(g2d, t);
        break;
      }
    }
  }

  /**
   * Private helper function to calculate the intermediate values of a shape's properties.
   * @param g graphics object
   * @param t transformation that is to be drawn on
   */
  private void tweenShape(Graphics2D g, Transformation t) {
    int startTime = t.getStartTick();
    Posn2D startPosn = t.getStartPosn();
    Posn2D endPosn = t.getEndPosn();
    Color startColor = t.getStartColor();
    Color endColor = t.getEndColor();
    int length = t.getEndTick() - startTime; //tb - t
    int startHeight = t.getStartHeight();
    int endHeight = t.getEndHeight();
    int startWidth = t.getStartWidth();
    int endWidth = t.getEndWidth();
    int dTime = this.currentTick - startTime; // t - ta
    Shape shape = t.getShape();

    Color newColor = new Color(
        dTime * (endColor.getRed() - startColor.getRed()) / length + startColor.getRed(),
        dTime * (endColor.getGreen() - startColor.getGreen()) / length + startColor.getGreen(),
        dTime * (endColor.getBlue() - startColor.getBlue()) / length + startColor.getBlue());

    int newHeight = dTime * (endHeight - startHeight) / length + startHeight;
    int newWidth = dTime * (endWidth - startWidth) / length + startWidth;
    int newX = dTime * (int) (endPosn.getX() - startPosn.getX()) / length + (int) startPosn.getX();
    int newY = dTime * (int) (endPosn.getY() - startPosn.getX()) / length + (int) startPosn.getY();

    g.setColor(newColor);

    /*
    //if (shape instanceof Rectangle) {
      g.fillRect(newX, newY, newWidth, newHeight);
    }
    // (shape instanceof Ellipse) {
      g.fillOval(newX, newY, newWidth, newHeight);
    }
    */

  }

  public void acceptTransformations(List<Transformation> transformations) {
    this.transformations = transformations;
  }

  public void acceptShapes(Map<String, Shape> shapes) {
    this.shapes = shapes;
  }

  public void getCurrentTick(int tick) {
    this.currentTick = tick;
  }

}
