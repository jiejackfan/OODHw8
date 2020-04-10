package cs3500.animator.provider.view;


import cs3500.animator.provider.model.Posn2D;
import cs3500.animator.provider.model.Shape;
import cs3500.animator.provider.model.Transformation;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Visual view.
 */
public class VisualView extends JFrame implements AnimationView {

  private AnimationPanel panel;
  private int tickRate;
  private int endTick;
  private int currentTick;

  /**
   * Default constructor for a VisualView.
   * @param width width of the animation window
   * @param height height of the animation window
   * @param windowLocation location of the top-left corner of the animation window
   * @param tickRate speed at which the animation will be played
   */
  public VisualView(int width, int height, Posn2D windowLocation, int tickRate) {
    super();

    this.tickRate = tickRate;
    this.endTick = 0;
    this.currentTick = 0;

    this.setTitle("Shape Animator");
    this.setSize(width, height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    this.panel = new AnimationPanel();
    this.panel.setSize(width, height);
    this.add(this.panel, BorderLayout.CENTER);

    this.setLocation((int) windowLocation.getX(), (int) windowLocation.getY());
    this.setVisible(true);
    this.pack();

  }

  @Override
  public void draw() {
    final Timer timer = new Timer(1000 / this.tickRate, null);
    timer.addActionListener(e -> {
      if (currentTick <= endTick) {
        panel.getCurrentTick(this.currentTick);
        this.currentTick++;
        this.repaint();
      } else {
        timer.stop();
      }
    });
    timer.start();
  }

  @Override
  public void getTransformations(List<Transformation> transformations) {
    this.panel.acceptTransformations(transformations);
  }

  @Override
  public void getShapes(Map<String, Shape> shapes) {
    this.panel.acceptShapes(shapes);
  }

  @Override
  public void getEndTick(int tick) {
    this.endTick = tick;
  }

  @Override
  public Appendable getOutput() {
    throw new UnsupportedOperationException("");
  }
}
