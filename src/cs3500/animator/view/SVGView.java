package cs3500.animator.view;

import cs3500.animator.model.shapes.Shape;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class represents the implementation of the SVG view.
 * <p>
 *   The SVG view will render the shapes passed to it into a string, the console, or a file in
 *   SVG-style code.
 *   The visual view allows to set custom bounds and initiate render of one frame.
 * </p>
 */
public class SVGView implements AnimationView {
  private int w;
  private int h;
  private int speed;
  private String outFile;
  private StringBuilder svgStr;
  private BufferedWriter writer;

  /**
   * Initialize the view.
   * @param x x-coordinate of the top-left corner of the view
   * @param y y-coordinate of the top-left corner of the view
   * @param w width of the view
   * @param h height of the view
   * @param speed the speed of the animation
   * @param outFile name of the output file
   */
  public SVGView(int x, int y, int w, int h, int speed, String outFile) {
    setBounds(x, y, w, h);
    setSpeed(speed);
    this.outFile = outFile;
    this.svgStr = new StringBuilder();

    if (!outFile.equals("")) {
      try {
        this.writer = new BufferedWriter(new FileWriter(outFile));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Initialize the view to a default.
   */
  public SVGView() {
    this(0, 0, 0, 0, 1, "");
  }

  /**
   * Set the output destination file. Print to system console if not specified.
   * @param outFile the name of the output file
   */
  public void setOutputFile(String outFile) {
    if (outFile == null) {
      throw new IllegalArgumentException("Null output name");
    }

    this.outFile = outFile;
  }

  /**
   * Set the paramters of the view bound.
   * @param x x-coordinate of the top-left corner of the view
   * @param y y-coordinate of the top-left corner of the view
   * @param w width of the view
   * @param h height of the view
   */
  public void setBounds(int x, int y, int w, int h) {
    if (w < 0 || h < 0) {
      throw new IllegalArgumentException("Invalid view size");
    }

    this.w = w;
    this.h = h;
  }

  /**
   * Render the shapes provided at the current tick.
   * @param currentTick the current tick of the model
   * @param shapeList the list of shapes to be rendered
   */
  public void render(int currentTick, List<Shape> shapeList) {
    if (currentTick != 0) {
      return;
    }

    svgStr.append(String.format("<svg width=\"%d\" height=\"%d\" version=\"1.1\" " +
        "xmlns=\"http://www.w3.org/2000/svg\">\n", w, h));
    for (Shape s : shapeList) {
      svgStr.append(s.toSVG(1000 / speed));
    }
    svgStr.append("\n</svg>\n");

    if (!outFile.equals("")) {
      renderFile();
    } else {
      renderConsole();
    }
  }

  /**
   * Render the view output to a file.
   */
  private void renderFile() {
    try {
      writer = new BufferedWriter(new FileWriter(outFile));
      writer.write(svgStr.toString());
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Render the view output to the system console.
   */
  private void renderConsole() {
    System.out.print(svgStr.toString());
  }

  /**
   * Set the event listener to a controller.
   * @param l the listener to set to
   */
  public void setListener(ActionListener l) {
    throw new UnsupportedOperationException("SVG view does not support action listener");
  }

  /**
   * Get the speed input by the user.
   * @return the speed in the input box
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Set the speed input box to a value.
   * @param speed the speed to set
   */
  public void setSpeed(int speed) {
    if (speed <= 0) {
      throw new IllegalArgumentException("Invalid animation speed");
    }

    this.speed = speed;
  }
}
