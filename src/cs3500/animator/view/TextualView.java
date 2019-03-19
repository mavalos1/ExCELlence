package cs3500.animator.view;

import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextualView implements AnimationView {
  private int x, y, w, h, speed;
  private String outFile;
  private StringBuilder TextStr;
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
  public TextualView(int x, int y, int w, int h, int speed, String outFile) {
    this.setBounds(x, y, w, h);
    this.speed = speed;
    this.outFile = outFile;
    this.TextStr = new StringBuilder();

    if (outFile != "") {
      try {
        this.writer = new BufferedWriter(new FileWriter(outFile));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Initialize the view to a speed and output destination.
   * @param speed
   * @param outFile
   */
  public TextualView(int speed, String outFile) {
    this(0, 0, 0, 0, speed, outFile);
  }

  /**
   * Set the paramters of the view bound.
   * @param x
   * @param y
   * @param w
   * @param h
   */
  public void setBounds(int x, int y, int w, int h) {
    if (w < 0 || h < 0)
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
    StringBuilder lineOutput = new StringBuilder();

    for (Shape s : shapeList) {
      if (currentTick == 0) {
        String sType = "";
        if (s instanceof Ellipse) {
          sType = "ellipse";
        } else if (s instanceof Rectangle) {
          sType = "rectangle";
        }

        lineOutput.append(String.format("\nshape %s %s\n", s.getName(), sType));
      } else {
        lineOutput.append(String.format("%d ", currentTick));
        lineOutput.append(String.format("motion %s ", s.getName()));
        lineOutput.append(String.format("%.0f %.0f ", s.getPosition().getX(), s.getPosition().getY()));
        lineOutput.append(String.format("%.0f %.0f ", s.getSize().getW(), s.getSize().getH()));
        lineOutput.append(String.format("%d %d %d\n",
            s.getColor().getR(), s.getColor().getG(), s.getColor().getB()));
      }
    }

    TextStr.append(lineOutput);

    try {
      Thread.sleep(1000 / speed);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    if (this.outFile != "") {
      renderFile(lineOutput.toString());
    } else {
      renderConsole(lineOutput.toString());
    }
  }

  /**
   * Render the view output to a file.
   */
  public void renderFile(String lineOutput) {
    if (outFile == "") {
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
