package cs3500.animator.view;

import cs3500.animator.model.shapes.Shape;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class represents the implementation of the textual view.
 * <p>
 *   The textual view will render the shapes passed to it into a string, the console, or a file.
 *   The textual view allows to set custom bounds and initiate render of one frame.
 * </p>
 */
public class TextualView implements AnimationView {
  private int x;
  private int y;
  private int w;
  private int h;
  private String outFile;
  private StringBuilder textStr;
  private BufferedWriter writer;

  /**
   * Initialize the view.
   * @param x x-coordinate of the top-left corner of the view
   * @param y y-coordinate of the top-left corner of the view
   * @param w width of the view
   * @param h height of the view
   * @param outFile name of the output file
   */
  public TextualView(int x, int y, int w, int h, String outFile) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.setBounds(x, y, w, h);
    this.outFile = outFile;
    this.textStr = new StringBuilder();

    if (!outFile.equals("")) {
      try {
        this.writer = new BufferedWriter(new FileWriter(outFile));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Initialize the view and output destination.
   * @param outFile name of the output file
   */
  public TextualView(String outFile) {
    this(0, 0, 0, 0, outFile);
  }

  /**
   * Initialize the view.
   * @param x x-coordinate of the top-left corner of the view
   * @param y y-coordinate of the top-left corner of the view
   * @param w width of the view
   * @param h height of the view
   */
  public TextualView(int x, int y, int w, int h) {
    this(x, y, w, h, "");
  }

  /**
   * Set the parameters of the view bound.
   * @param x x position
   * @param y y position
   * @param w width
   * @param h height
   */
  public void setBounds(int x, int y, int w, int h) {
    if (w < 0 || h < 0) {
      throw new IllegalArgumentException("Width or height is negative");
    }
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  /**
   * Render the shapes provided at the current tick.
   * @param currentTick the current tick.
   * @param shapeList the shapeList.
   */
  public void render(int currentTick, List<Shape> shapeList) {
    StringBuilder lineOutput = new StringBuilder();

    for (Shape s : shapeList) {
      if (currentTick == 0) {
        lineOutput.append(String.format("shape %s %s\n", s.getName(), s.getShapeType()));
      } else {
        lineOutput.append(String.format("%d ", currentTick));
        lineOutput.append(String.format("motion %s ", s.getName()));
        lineOutput.append(String.format("%.0f %.0f ",
            s.getPosition().getX(), s.getPosition().getY()));
        lineOutput.append(String.format("%.0f %.0f ", s.getSize().getW(), s.getSize().getH()));
        lineOutput.append(String.format("%d %d %d\n",
            s.getColor().getR(), s.getColor().getG(), s.getColor().getB()));
      }
    }

    textStr.append(lineOutput);

    if (!outFile.equals("")) {
      renderFile(lineOutput.toString());
    } else {
      renderConsole(lineOutput.toString());
    }
  }

  /**
   * Render the view output to a file.
   */
  public void renderFile(String lineOutput) {
    if (outFile.equals("")) {
      throw new IllegalArgumentException("No output file destination");
    }

    try {
      this.writer = new BufferedWriter(new FileWriter(outFile, true));
      this.writer.write(lineOutput);
      this.writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Render the view output to the system console.
   */
  public void renderConsole(String lineOutput) {
    System.out.print(lineOutput);
  }
}
