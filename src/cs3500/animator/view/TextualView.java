package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.shapes.Shape;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextualView extends ViewImpl {
  /**
   * Initialize the view to a position, canvas size, and animation speed
   *
   * @param x
   * @param y
   * @param h
   * @param w
   * @param tickPerSecond
   */
  public TextualView(int x, int y, int h, int w, int tickPerSecond, AnimationModel model) {
    super(x, y, h, w, tickPerSecond, model);
  }

  /**
   * Render the animation to a specified output.
   */
  public void render() {
    String canvasInfo =
        String.format("canvas %.0f %.0f %.0f %.0f\n",
            this.getCanvasPosition().getX(), this.getCanvasPosition().getY(),
            this.getCanvasSize().getW(), this.getCanvasSize().getH());

    System.out.println(canvasInfo);

    List<Shape> shapes = model.getAllShapes();
    for (Shape s : shapes) {
      System.out.println(String.format("shape %s %s", s.getName(), s.getShapeType()));
    }

    System.out.println();

    try {
      this.animate();
    } catch (InterruptedException e) {
      System.out.print(e);
    }
  }
  protected void animate() throws InterruptedException {
    long tickMS = 1000 / this.tPs;
    boolean shouldPlay = true;

    while (shouldPlay) {
      shouldPlay = false;

      List<Shape> shapes = model.getAllShapes();
      for (Shape s : shapes) {
        renderShapeView(s);

        if (s.hasTransition()) {
          shouldPlay = true;
        }
      }

      model.tick();
      Thread.sleep(tickMS);
    }
  }

  protected void renderShapeView(Shape s) {
    System.out.println(String.format("motion %s %d %.0f %.0f %.0f %.0f %d %d %d",
        s.getName(), model.getCurrentTick(),
        s.getPosition().getX(), s.getPosition().getY(),
        s.getSize().getW(), s.getSize().getH(),
        s.getColor().getR(), s.getColor().getG(), s.getColor().getB()));
  }
}