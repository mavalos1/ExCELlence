package cs3500.animator.view;

import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SVGView implements AnimationView {
  private int x, y, w, h, speed;
  private String outFile;
  private StringBuilder SVGStr;
  private BufferedWriter writer;

  /**
   * Initialize the view.
   * @param x
   * @param y
   * @param w
   * @param h
   * @param speed
   * @param outFile
   */
  public SVGView(int x, int y, int w, int h, int speed, String outFile) {
    if (speed <= 0) {
      throw new IllegalArgumentException("Invalid animation speed");
    }

    this.setBounds(x, y, w, h);
    this.speed = speed;
    this.outFile = outFile;
    SVGStr = new StringBuilder();

    if (outFile != "") {
      try {
        this.writer = new BufferedWriter(new FileWriter(outFile));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Initialize the view to a speed and an output destination.
   * @param speed
   * @param outFile
   */
  public SVGView(int speed, String outFile) {
    this(0, 0, 0, 0, speed, outFile);
  }

  /**
   * Initialize the view.
   * @param x
   * @param y
   * @param w
   * @param h
   * @param speed
   */
  public SVGView(int x, int y, int w, int h, int speed) {
    this(x, y, w, h, speed, "");
  }

  /**
   * Set the paramters of the view bound.
   * @param x
   * @param y
   * @param w
   * @param h
   */
  public void setBounds(int x, int y, int w, int h) {
    if (w < 0 || h < 0) {
      throw new IllegalArgumentException("Invalid view size");
    }

    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  /**
   * Render the shapes provided at the current tick.
   * @param currentTick
   * @param shapeList
   */
  public void render(int currentTick, List<Shape> shapeList) {
    if (currentTick != 0) {
      return;
    }

    SVGStr.append(String.format("<svg width=\"%d\" height=\"%d\" version=\"1.1\" " +
        "xmlns=\"http://www.w3.org/2000/svg\">\n", w, h));
    for (Shape s : shapeList) {
      SVGStr.append(s.toSVG(1000 / speed));
    }
    SVGStr.append("\n</svg>\n");

    if (outFile != "") {
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
      this.writer.write(SVGStr.toString());
      this.writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Render the view output to the system console.
   */
  public void renderConsole() {
    System.out.print(SVGStr.toString());
  }
}
