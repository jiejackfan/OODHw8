package cs3500.animator.provider.view;

import cs3500.animator.model.DifferentShapes;
import cs3500.animator.model.IShape;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.provider.model.Rectangle;
import cs3500.animator.provider.model.Ellipse;
import cs3500.animator.provider.model.PShape;
import cs3500.animator.provider.model.Posn2D;
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


// IMPORTANT: some changes were made ot this file by us the customer, please refer to
// ExperienceAsCustomer.txt for detailed changes.





/**
 * Drawing panel for the visual view.
 */
public class AnimationPanel extends JPanel {

  private List<Transformation> transformations;
  private Map<String, PShape> shapes;
  private int currentTick;
  private ReadOnlyModel m;

  /**
   * Default constructor for a panel.
   */
  public AnimationPanel(ReadOnlyModel m) {
    this.transformations = new ArrayList<Transformation>();
    this.shapes = new HashMap<String, PShape>();
    setPreferredSize(new Dimension(500, 500));
    this.m = m;
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    List<IShape> listOfShape = m.getFrame(currentTick);

    //Go through each shape in the List of shape and draw the corresponding shape.
    //Draws the shapes in order from first to last so the last object will be on top.
    for (IShape shape : listOfShape) {
      if (shape.getShape() == DifferentShapes.rectangle) {
        g2.setColor(shape.getColor());
        g2.fillRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
            (int) shape.getWidth(), (int) shape.getHeight());
      }
      else if (shape.getShape() == DifferentShapes.oval
          || shape.getShape() == DifferentShapes.ellipse) {
        g2.setColor(shape.getColor());
        g2.fillOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
            (int) shape.getWidth(), (int) shape.getHeight());
      }
    }
  }

  /*
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (PShape s : this.shapes.values()) {
      for (Transformation t : s.getTransformations()) {
        tweenShape(g2d, t);
        break;
      }
    }
  }
   */

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
    PShape shape = t.getShape();

    Color newColor = new Color(
        dTime * (endColor.getRed() - startColor.getRed()) / length + startColor.getRed(),
        dTime * (endColor.getGreen() - startColor.getGreen()) / length + startColor.getGreen(),
        dTime * (endColor.getBlue() - startColor.getBlue()) / length + startColor.getBlue());

    int newHeight = dTime * (endHeight - startHeight) / length + startHeight;
    int newWidth = dTime * (endWidth - startWidth) / length + startWidth;
    int newX = dTime * (int) (endPosn.getX() - startPosn.getX()) / length + (int) startPosn.getX();
    int newY = dTime * (int) (endPosn.getY() - startPosn.getX()) / length + (int) startPosn.getY();

    g.setColor(newColor);

    if (shape instanceof Rectangle) {
      g.fillRect(newX, newY, newWidth, newHeight);
    }

    else if (shape instanceof Ellipse) {
      g.fillOval(newX, newY, newWidth, newHeight);
    }
  }

  public void acceptTransformations(List<Transformation> transformations) {
    this.transformations = transformations;
  }

  public void acceptShapes(Map<String, PShape> shapes) {
    this.shapes = shapes;
  }

  public void getCurrentTick(int tick) {
    this.currentTick = tick;
  }

}
