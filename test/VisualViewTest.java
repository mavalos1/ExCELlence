import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.VisualView;

/**
 * This is the test class for the visual view. It will execute a simple animation for visual.
 */
public class VisualViewTest {
  /**
   * This is the main method of the test class to launch the visual window.
   * @param args
   */
  public static void main(String[] args) {
    AnimationModel model = new Model();
    AnimationView view = new VisualView();
    AnimationController controller = new Controller(model, view, 30);

    Rectangle rect = new Rectangle("R", 0, 0, 100, 100, 255,0,0);
    rect.addTransition(
            new Transition(1, 120, 0, 0, 30, 30, 255, 0,
                    0, 10, 10, 10, 10, 255, 0, 0));
    model.addShape(rect);

    controller.renderView();
    controller.nextTick();
    controller.renderView();

    controller.animate();
  }
}
