import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.VisualView;
import org.junit.Test;

import java.awt.event.ActionListener;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for the visual view. It will execute a simple animation for visual.
 */
public class VisualViewTest {
  /**
   * This is the main method of the test class to launch the visual window.
   * @param args arguments
   */
  public static void main(String[] args) {
    AnimationModel model = new Model();
    AnimationView view = new VisualView();
    AnimationController controller = new Controller(model, view, 1);

    view.setBounds(10, 10, 30, 100);

    Rectangle r = new Rectangle("R", 0, 0, 100, 100, 50, 50, 50);
    Ellipse e = new Ellipse("C");
    Transition t = new Transition(2, 12, 20, 20, 30, 30, 30, 30, 30, 10, 10, 10, 10, 10, 10, 10);

    model.addShape(r, e);
    model.addTransition("R", t);
    model.addTransition("C", t);

    controller.start();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBoundsNegativeWidthHeight() {
    AnimationView view = new VisualView();
    view.setBounds(0,0, -100, -100);
  }
  @Test
  public void testGetSpeed() {
    AnimationModel model = new Model();
    AnimationView view = new VisualView();
    ActionListener controller = new Controller(model, view ,1);
    assertEquals(1, view.getSpeed());
  }

  @Test
  public void testSetSpeed() {
    AnimationModel model = new Model();
    AnimationView view = new VisualView();
    ActionListener controller = new Controller(model, view ,1);
    view.setSpeed(100);
    assertEquals(100, view.getSpeed());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSetOutput() {
    AnimationView view = new VisualView();
    view.setOutputFile("output.svg");
  }
}
