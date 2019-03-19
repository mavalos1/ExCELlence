import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.SVGView;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SVGViewTest {
  public static void main(String[] args) {
    AnimationModel model = new Model();
    AnimationView view = new SVGView(10, 10, 500, 500, 1, "out.svg");
    AnimationController controller = new Controller(model, view, 10);

    Rectangle rect = new Rectangle("R", 0, 0, 100, 100, 50, 50, 50);
    rect.addTransition(
        new Transition(2, 12, 20, 20, 30, 30, 300, 300, 300, 10, 10, 10, 10, 10, 10, 10));
    model.addShape(rect);

    Ellipse ell = new Ellipse("C", 50, 50, 50, 50, 250, 250, 250);
    ell.addTransition(
        new Transition(5, 10, 50, 50, 50, 50, 80,250,124, 10, 10, 10, 10, 10, 10, 10));
    model.addShape(ell);

    controller.renderView();
    controller.nextTick();
    controller.renderView();

    controller.animate();
  }

  @Test
  public void testSetBounds() {
    AnimationModel model = new Model();
    AnimationView view = new SVGView(10, 10, 500, 500, 1, "out.svg");
    view.setBounds(10, 10, 100, 100);
  }
}
