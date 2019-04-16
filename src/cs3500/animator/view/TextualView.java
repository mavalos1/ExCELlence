package cs3500.animator.view;

import cs3500.animator.model.shapes.Shape;

import java.awt.event.ActionListener;
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
  private StringBuilder lineOutput;
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
    this.setBounds(x, y, w, h);
    this.outFile = outFile;
    this.lineOutput = new StringBuilder();
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
   * Initialize the view to a default.
   */
  public TextualView() {
    this(0, 0, 0, 0, "");
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
   * Render the shapes provided at the current tick.
   * @param currentTick the current tick.
   * @param shapeList the shapeList.
   */
  public void render(int currentTick, List<Shape> shapeList) {
    lineOutput = new StringBuilder();

    if (currentTick == 0) {
      lineOutput.append(String.format("canvas %d %d %d %d\n", x, y, w, h));
    }

    for (Shape s : shapeList) {
      if (currentTick == 0) {
        lineOutput.append(String.format("shape %s %s\n", s.getName(), s.getShapeType()));
      } else {
        lineOutput.append(String.format("%d ", currentTick));
        lineOutput.append(String.format("motion %s ", s.getName()));
        lineOutput.append(String.format("%.0f %.0f ",
            s.getPosition().getXCoord(), s.getPosition().getYCoord()));
        lineOutput.append(String.format("%.0f %.0f ", s.getSize().getW(), s.getSize().getH()));
        lineOutput.append(String.format("%.0f %.0f %.0f\n",
            s.getColor().getR(), s.getColor().getG(), s.getColor().getB()));
      }
    }

    textStr.append(lineOutput);

    if (!outFile.equals("")) {
      if (currentTick == 0) {
        try {
          writer = new BufferedWriter(new FileWriter(outFile, false));
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      renderFile();
    } else {
      renderConsole();
    }
  }

  /**
   * Render the view output to a file.
   */
  private void renderFile() {
    if (outFile.equals("")) {
      throw new IllegalStateException("No output file destination");
    }

    try {
      writer = new BufferedWriter(new FileWriter(outFile, true));
      writer.write(lineOutput.toString());
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Render the view output to the system console.
   */
  private void renderConsole() {
    System.out.print(lineOutput.toString());
  }

  /**
   * Set the event listener to a controller.
   * @param l the listener to set to
   */
  public void setListener(ActionListener l) {
    throw new UnsupportedOperationException("Textual view does not support action listener");
  }

  /**
   * Get the speed input by the user.
   * @return the speed in the input box
   */
  public int getSpeed() {
    throw new UnsupportedOperationException("Textual view does not support input");
  }

  /**
   * Set the speed input box to a value.
   * @param speed the speed to set
   */
  public void setSpeed(int speed) {
    throw new UnsupportedOperationException("Textual view does not support input");
  }
}
