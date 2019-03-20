import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.view.SVGView;
import org.junit.Test;

/**
 * This is the test class for the controller.
 */
public class ControllerTest {
  AnimationModel model = new Model();
  SVGView view = new SVGView(10, 10, 500, 500, 1, "out.svg");

  @Test(expected = IllegalArgumentException.class)
  public void testNonPositiveSpeed() {
    AnimationController c = new Controller(model, view, -1);
  }

  @Test(expected = NullPointerException.class)
  public void testNullModel() {
    AnimationController c = new Controller(null, view, 1);
  }

  @Test(expected = NullPointerException.class)
  public void testNullView() {
    AnimationController c = new Controller(model, null, 1);
  }

  @Test(expected = NullPointerException.class)
  public void testNullType() {
    AnimationController c = new Controller(null, 1, "output.txt");
  }

  @Test(expected = NullPointerException.class)
  public void testNullOutputFile() {
    AnimationController c = new Controller("svg", 1, null);
  }

}
