package cs3500.animator.view;

import cs3500.animator.model.shapes.Shape;

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
    if (speed <= 0) {
      throw new IllegalArgumentException("Invalid animation speed");
    }

    this.setBounds(x, y, w, h);
    this.speed = speed;
    this.outFile = outFile;
    svgStr = new StringBuilder();

    if (!outFile.equals("")) {
      try {
        this.writer = new BufferedWriter(new FileWriter(outFile));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Initialize the view to a speed and an output destination.
   * @param speed the speed of the animation
   * @param outFile the name of the output file
   */
  public SVGView(int speed, String outFile) {
    this(0, 0, 0, 0, speed, outFile);
  }

  /**
   * Initialize the view.
   * @param x x-coordinate of the top-left corner of the view
   * @param y y-coordinate of the top-left corner of the view
   * @param w width of the view
   * @param h height of the view
   * @param speed the speed of the animation
   */
  public SVGView(int x, int y, int w, int h, int speed) {
    this(x, y, w, h, speed, "");
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
  public void renderFile() {
    try {
      this.writer.write(svgStr.toString());
      this.writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Render the view output to the system console.
   */
  public void renderConsole() {
    System.out.print(svgStr.toString());
  }
}
