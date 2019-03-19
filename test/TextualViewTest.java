import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.TextualView;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TextualViewTest {
  public static void main(String[] args) {
    AnimationModel model = new Model();
    AnimationView view = new TextualView(10, 10, 300, 300);
    AnimationController controller = new Controller(model, view, 1);

    Rectangle rect = new Rectangle("R", 0, 0, 100, 100, 50, 50, 50);
    rect.addTransition(
        new Transition(2, 12, 20, 20, 30, 30, 30, 30, 30, 10, 10, 10, 10, 10, 10, 10));
    model.addShape(rect);

    controller.renderView();
    controller.nextTick();
    controller.renderView();

    controller.animate();
  }
}
