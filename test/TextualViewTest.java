import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.SingleAnimation;
import cs3500.animator.model.helper.Size;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.TextualView;

public class TextualViewTest {
  public static void main(String[] args) {
    AnimationModel model = new SingleAnimation(new Rectangle("R"));
    model.addAnimation("R", new Transition(5, 0, 5, 5, 5, 0, 0, 0, 10));
    AnimationView view = new TextualView(0, 0, 360, 360, 1, model);

    Ellipse c = new Ellipse("C");
    c.setSize(new Size(20, 20));
    model.addShape(c);
    model.addAnimation("C", new Transition(-5, 5, -5, -5, -5, 1, 1, 0, 5));

    view.render();
  }
}
