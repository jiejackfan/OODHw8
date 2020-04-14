package cs3500.animator.provider.view;

import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.provider.model.PShape;
import cs3500.animator.provider.model.Posn2D;
import cs3500.animator.provider.model.Transformation;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;


//IMPORTANT: some changes were made to this file by the customer, please refer to
//ExperienceAsCustomer.txt for detailed changes.





/**
 * Represents a frame-by-frame animation view where the animation can be controlled and modified by
 * a user.
 */
public class EditorView extends JFrame implements EditorAnimationView {
  private static int FRAMES_PER_SECOND = 1;
  private double currTick;
  private int endTick;
  private int speed; // ticks per second
  private boolean isLooping;
  private boolean isPlaying;
  private Timer timer;
  private AnimationPanel picturePanel;
  private JPanel shapeSelectPanel;
  private JButton playBtn;
  private JButton pauseBtn;
  private JButton restartBtn;
  private JButton doEditBtn;
  private ButtonGroup keyFrameGroup;
  private ButtonGroup shapeGroup;
  private JLabel speedLbl;
  private JLabel loopingLbl;
  private JLabel playingLbl;
  private JTextField idTF;
  private JTextField tickTF;
  private JTextField xTF;
  private JTextField yTF;
  private JTextField wTF;
  private JTextField hTF;
  private JTextField rTF;
  private JTextField gTF;
  private JTextField bTF;
  private JRadioButton addKeyFrameRB;
  private JRadioButton modifyKeyFrameRB;
  private JRadioButton removeKeyFrameRB;
  private JRadioButton addShapeRB;
  private JRadioButton removeShapeRB;
  private JRadioButton rectangleRB;
  private JRadioButton ellipseRB;

  private ReadOnlyModel m;

  /**
   * Constructs an EditorView.
   *
   * @param width  The width of the window.
   * @param height The height of the window.
   * @param speed  The speed of the animation.
   */
  public EditorView(ReadOnlyModel m, int width, int height, Posn2D windowLocation, int speed) {
    super();

    this.m = m;

    if (width < 0) {
      throw new IllegalArgumentException("width is negative.");
    }
    if (height < 0) {
      throw new IllegalArgumentException("height is negative.");
    }
    if (speed < 0) {
      throw new IllegalArgumentException("Negative ticks per second.");
    }

    this.speed = speed;
    this.endTick = 0;
    this.currTick = 0.0;
    this.isLooping = false;
    this.isPlaying = true;

    this.initGui(width, height);
    this.initTimer();
  }

  /**
   * Initializes the user interface.
   *
   * @param width  The width of the picture panel.
   * @param height The height of the picture panel.
   */
  private void initGui(int width, int height) {
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    /* Initialize panels */

    // the user control panel
    JPanel userControlPanel = new JPanel();
    userControlPanel.setLayout(new BoxLayout(userControlPanel, BoxLayout.Y_AXIS));
    userControlPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    this.add(userControlPanel);

    // the panel containing the animation, framed by a grey border
    this.picturePanel = new AnimationPanel(m);
    this.picturePanel.setPreferredSize(new Dimension(width, height));
    this.picturePanel.setMaximumSize(new Dimension(width, height));
    this.picturePanel.setBorder(new LineBorder(Color.GRAY));
    this.picturePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    this.add(this.picturePanel);

    // the label panel
    JPanel labelPanel = new JPanel();
    labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
    labelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    userControlPanel.add(labelPanel);

    // the button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    userControlPanel.add(buttonPanel);

    // radio button panel
    JPanel radioPanel = new JPanel();
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));
    radioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    userControlPanel.add(radioPanel);

    // key frame editing panel
    JPanel keyFramePanel = new JPanel();
    keyFramePanel.setLayout(new BoxLayout(keyFramePanel, BoxLayout.Y_AXIS));
    keyFramePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    keyFramePanel.setBorder(new LineBorder(Color.GRAY));
    //
    radioPanel.add(keyFramePanel);

    // shape selection panel
    this.shapeSelectPanel = new JPanel();
    this.shapeSelectPanel.setLayout(new BoxLayout(this.shapeSelectPanel, BoxLayout.Y_AXIS));
    this.shapeSelectPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    this.shapeSelectPanel.setBorder(new LineBorder(Color.GRAY));
    radioPanel.add(this.shapeSelectPanel);

    // text field panel
    JPanel textFieldPanel = new JPanel();
    textFieldPanel.setLayout(new GridLayout(2, 9));
    textFieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    textFieldPanel.setPreferredSize(new Dimension(width, 40));
    userControlPanel.add(textFieldPanel);

    /* Initialize buttons */

    // the play button
    this.playBtn = new JButton("Play");
    this.playBtn.setActionCommand("Play Button");
    buttonPanel.add(this.playBtn);

    // the pause button
    this.pauseBtn = new JButton("Pause");
    this.pauseBtn.setActionCommand("Pause Button");
    buttonPanel.add(this.pauseBtn);

    // the restart button
    this.restartBtn = new JButton("Restart");
    this.restartBtn.setActionCommand("Restart Button");
    buttonPanel.add(this.restartBtn);

    // the edit button
    this.doEditBtn = new JButton("Do Edit");
    this.doEditBtn.setActionCommand("Do Edit Button");
    radioPanel.add(this.doEditBtn);

    /* Initialize labels */

    // the instruction label
    String instructions = "Use up and down arrow keys to control the animation speed. "
        + "Press L to enable or disable looping.";
    JLabel instructionLbl = new JLabel(instructions);
    instructionLbl.setFont(new Font("Arial", Font.BOLD, 16));
    labelPanel.add(instructionLbl);

    // speed label
    this.speedLbl = new JLabel();
    this.updateSpeedLabel();
    labelPanel.add(speedLbl);

    // looping label
    this.loopingLbl = new JLabel();
    this.updateLoopingLabel();
    labelPanel.add(loopingLbl);

    // playing label
    this.playingLbl = new JLabel();
    this.updatePlayingLabel();
    labelPanel.add(playingLbl);

    /* Initialize animation edit panel */

    JLabel idLbl = new JLabel("Shape ID");
    JLabel tickLbl = new JLabel("Tick");
    JLabel xLbl = new JLabel("x-coordinate");
    JLabel yLbl = new JLabel("y-coordinate");
    JLabel wLbl = new JLabel("Width");
    JLabel hLbl = new JLabel("Height");
    JLabel rLbl = new JLabel("Red");
    JLabel gLbl = new JLabel("Green");
    JLabel bLbl = new JLabel("Blue");
    idTF = new JTextField();
    tickTF = new JTextField();
    xTF = new JTextField();
    yTF = new JTextField();
    wTF = new JTextField();
    hTF = new JTextField();
    rTF = new JTextField();
    gTF = new JTextField();
    bTF = new JTextField();

    textFieldPanel.add(idLbl);
    textFieldPanel.add(tickLbl);
    textFieldPanel.add(xLbl);
    textFieldPanel.add(yLbl);
    textFieldPanel.add(wLbl);
    textFieldPanel.add(hLbl);
    textFieldPanel.add(rLbl);
    textFieldPanel.add(gLbl);
    textFieldPanel.add(bLbl);
    textFieldPanel.add(idTF);
    textFieldPanel.add(tickTF);
    textFieldPanel.add(xTF);
    textFieldPanel.add(yTF);
    textFieldPanel.add(wTF);
    textFieldPanel.add(hTF);
    textFieldPanel.add(rTF);
    textFieldPanel.add(gTF);
    textFieldPanel.add(bTF);

    /* initialize radio buttons choosing key frame action */
    this.keyFrameGroup = new ButtonGroup();
    this.addKeyFrameRB = new JRadioButton("Add key frame");
    addKeyFrameRB.setActionCommand("Add key frame RB");
    this.modifyKeyFrameRB = new JRadioButton("Modify key frame");
    modifyKeyFrameRB.setActionCommand("Modify key frame RB");
    this.removeKeyFrameRB = new JRadioButton("Remove key frame");
    removeKeyFrameRB.setActionCommand("Remove key frame RB");
    this.addShapeRB = new JRadioButton("Add shape");
    addShapeRB.setActionCommand("Add shape RB");
    this.removeShapeRB = new JRadioButton("Remove shape");
    removeShapeRB.setActionCommand("Remove shape RB");

    keyFrameGroup.add(addKeyFrameRB);
    keyFrameGroup.add(modifyKeyFrameRB);
    keyFrameGroup.add(removeKeyFrameRB);
    keyFrameGroup.add(addShapeRB);
    keyFrameGroup.add(removeShapeRB);

    keyFramePanel.add(new JLabel("Choose one"));
    keyFramePanel.add(addKeyFrameRB);
    keyFramePanel.add(modifyKeyFrameRB);
    keyFramePanel.add(removeKeyFrameRB);
    keyFramePanel.add(addShapeRB);
    keyFramePanel.add(removeShapeRB);

    /* Initialize shape select panel */
    this.shapeGroup = new ButtonGroup();
    this.rectangleRB = new JRadioButton("Rectangle");
    this.rectangleRB.setActionCommand("Rectangle RB");
    this.ellipseRB = new JRadioButton("Ellipse");
    this.ellipseRB.setActionCommand("Ellipse RB");

    shapeGroup.add(rectangleRB);
    shapeGroup.add(ellipseRB);

    shapeSelectPanel.add(new JLabel("Select a shape"));
    shapeSelectPanel.add(rectangleRB);
    shapeSelectPanel.add(ellipseRB);

    this.configureRadioButtons();
    addKeyFrameRB.doClick();
    rectangleRB.doClick();

    this.pack();
    this.setVisible(true);
    this.setResizable(true);
    this.resetFocus();
  }

  private void updatePlayingLabel() {
    this.playingLbl.setText("Animation playing: " + this.isPlaying);
  }

  private void configureRadioButtons() {
    this.addKeyFrameRB.addActionListener(e -> {
      this.idTF.setEnabled(true);
      this.tickTF.setEnabled(true);
      this.xTF.setEnabled(false);
      this.yTF.setEnabled(false);
      this.wTF.setEnabled(false);
      this.hTF.setEnabled(false);
      this.rTF.setEnabled(false);
      this.gTF.setEnabled(false);
      this.bTF.setEnabled(false);
      this.shapeSelectPanel.setVisible(false);
      this.resetFocus();
    });
    this.modifyKeyFrameRB.addActionListener(e -> {
      this.idTF.setEnabled(true);
      this.tickTF.setEnabled(true);
      this.xTF.setEnabled(true);
      this.yTF.setEnabled(true);
      this.wTF.setEnabled(true);
      this.hTF.setEnabled(true);
      this.rTF.setEnabled(true);
      this.gTF.setEnabled(true);
      this.bTF.setEnabled(true);
      this.shapeSelectPanel.setVisible(false);
      this.resetFocus();
    });
    this.removeKeyFrameRB.addActionListener(e -> {
      this.idTF.setEnabled(true);
      this.tickTF.setEnabled(true);
      this.xTF.setEnabled(false);
      this.yTF.setEnabled(false);
      this.wTF.setEnabled(false);
      this.hTF.setEnabled(false);
      this.rTF.setEnabled(false);
      this.gTF.setEnabled(false);
      this.bTF.setEnabled(false);
      this.shapeSelectPanel.setVisible(false);
      this.resetFocus();
    });
    this.addShapeRB.addActionListener(e -> {
      this.idTF.setEnabled(true);
      this.tickTF.setEnabled(false);
      this.xTF.setEnabled(false);
      this.yTF.setEnabled(false);
      this.wTF.setEnabled(false);
      this.hTF.setEnabled(false);
      this.rTF.setEnabled(false);
      this.gTF.setEnabled(false);
      this.bTF.setEnabled(false);
      this.shapeSelectPanel.setVisible(true);
      this.resetFocus();
    });
    this.removeShapeRB.addActionListener(e -> {
      this.idTF.setEnabled(true);
      this.tickTF.setEnabled(false);
      this.xTF.setEnabled(false);
      this.yTF.setEnabled(false);
      this.wTF.setEnabled(false);
      this.hTF.setEnabled(false);
      this.rTF.setEnabled(false);
      this.gTF.setEnabled(false);
      this.bTF.setEnabled(false);
      this.shapeSelectPanel.setVisible(false);
      this.resetFocus();
    });
    this.rectangleRB.addActionListener(e -> this.resetFocus());
    this.ellipseRB.addActionListener(e -> this.resetFocus());
  }

  /**
   * Initializes the timer controlling the animation.
   */
  private void initTimer() {
    this.timer = new Timer(1000 / speed, e -> {
      if (currTick < endTick) {
        if (isPlaying) {
          picturePanel.getCurrentTick((int) Math.round(currTick));
          //currTick = Math.min(currTick + 1.0 * this.speed / FRAMES_PER_SECOND, this.endTick);
          currTick = Math.min(currTick + 1, this.endTick);
          this.repaint();
        }
      } else {
        picturePanel.getCurrentTick(endTick);
        this.repaint();

        if (isLooping) {
          currTick = 0;
        }
      }
    });
  }

  /**
   * Renders this view in some way.
   */
  @Override
  public void draw() {
    // the timer operates concurrently, so the program can continue without stopping the timer
    timer.start();
  }

  /**
   * Receives the transformations contained within the model for use in the draw() method.
   *
   * @param transformations transformations that are to be drawn.
   */
  @Override
  public void getTransformations(List<Transformation> transformations) {
    this.picturePanel.acceptTransformations(transformations);
  }

  /**
   * Receives the shapes contained within the model for use in the draw() method.
   *
   * @param shapes shapes that are to be drawn
   */
  @Override
  public void getShapes(Map<String, PShape> shapes) {
    this.picturePanel.acceptShapes(shapes);
  }


  /**
   * Takes in the last tick time of this animation.
   *
   * @param tick The last tick.
   */
  @Override
  public void getEndTick(int tick) {
    this.endTick = tick;
  }

  /**
   * Returns the output of the view in an Appendable.
   *
   * @return
   */
  @Override
  public Appendable getOutput() {
    return null;
  }

  /**
   * Adds a listener for action events that happen in this view.
   *
   * @param listener The listener to add.
   */
  @Override
  public void addActionListener(ActionListener listener) {
    System.out.println("added listener");
    this.playBtn.addActionListener(listener);
    this.pauseBtn.addActionListener(listener);
    this.restartBtn.addActionListener(listener);
    this.doEditBtn.addActionListener(listener);
  }


  /**
   * Plays the animation.
   */
  @Override
  public void start() {
    this.isPlaying = true;
    this.updatePlayingLabel();
  }

  /**
   * Pauses the animation.
   */
  @Override
  public void pause() {
    this.isPlaying = false;
    this.updatePlayingLabel();
  }

  /**
   * Restarts the animation from the beginning.
   */
  @Override
  public void restart() {
    this.currTick = 0;
  }

  /**
   * Turns on the looping if it's off and vice versa.
   */
  @Override
  public void toggleLooping() {
    this.isLooping = !this.isLooping;
    this.updateLoopingLabel();
  }

  /**
   * Increases the speed of the animation by 5 ticks per second.
   */
  @Override
  public void increaseSpeed() {
    this.speed += 10;
    this.updateSpeedLabel();
    timer.setDelay(1000 / speed);
  }

  /**
   * Decreases the speed of the animation by 5 ticks per second. If decrementing makes it negative,
   * the speed becomes 1 tick per second.
   */
  @Override
  public void decreaseSpeed() {
    if (this.speed - 10 >= 1) {
      this.speed -= 10;
    } else {
      this.speed = 1;
    }
    timer.setDelay(1000 / speed);

    this.updateSpeedLabel();
  }

  /**
   * Sets the focus to the main frame of this view.
   */
  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  /**
   * Returns the String in the ID text field.
   *
   * @return the String in the ID text field.
   */
  @Override
  public String getIdField() {
    return this.idTF.getText();
  }

  /**
   * Returns the String in the Shape text field.
   *
   * @return the String in the Shape text field.
   */
  @Override
  public String getShapeOption() {
    return this.shapeGroup.getSelection().getActionCommand();
  }

  /**
   * Returns the String in the tick text field.
   *
   * @return the String in the tick text field.
   */
  @Override
  public String getTickField() {
    return this.tickTF.getText();
  }

  /**
   * Returns the String in the x text field.
   *
   * @return the String in the x text field.
   */
  @Override
  public String getXField() {
    return this.xTF.getText();
  }

  /**
   * Returns the String in the y text field.
   *
   * @return the String in the y text field.
   */
  @Override
  public String getYField() {
    return this.yTF.getText();
  }

  /**
   * Returns the String in the width text field.
   *
   * @return the String in the width text field.
   */
  @Override
  public String getWidthField() {
    return this.wTF.getText();
  }

  /**
   * Returns the String in the height text field.
   *
   * @return the String in the height text field.
   */
  @Override
  public String getHeightField() {
    return this.hTF.getText();
  }

  /**
   * Returns the String in the red text field.
   *
   * @return the String in the red text field.
   */
  @Override
  public String getRedField() {
    return this.rTF.getText();
  }

  /**
   * Returns the String in the blue text field.
   *
   * @return the String in the blue text field.
   */
  @Override
  public String getBlueField() {
    return this.bTF.getText();
  }

  /**
   * Returns the String in the green text field.
   *
   * @return the String in the green text field.
   */
  @Override
  public String getGreenField() {
    return this.gTF.getText();
  }

  /**
   * Returns a String describing the animation editing option selected.
   *
   * @return a String describing the animation editing option selected.
   */
  @Override
  public String getEditOption() {
    return this.keyFrameGroup.getSelection().getActionCommand();
  }

  /**
   * Displays the given error message.
   *
   * @param msg The message.
   */
  @Override
  public void displayErrorMessage(String msg) {
    JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
  }

  private void updateSpeedLabel() {
    this.speedLbl.setText("Ticks per second: " + this.speed);
  }

  private void updateLoopingLabel() {
    this.loopingLbl.setText("Looping: " + this.isLooping);
  }
}