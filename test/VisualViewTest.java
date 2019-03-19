import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.SingleAnimation;
import cs3500.animator.model.helper.Color;
import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.VisualView;

public class VisualViewTest {
  public static void main(String[] args) {
    Rectangle r = new Rectangle("R", new Position2D(10, 10), new Size(100, 100),
        new Color(0,0, 0), 0.0);
    r.setColor(new Color(0,255,0));
    AnimationModel model = new SingleAnimation(r);
    model.addAnimation("R", new Transition(5, 0, 0, 0, 0, 0, 0, 0, 10));

    Ellipse c = new Ellipse("C");
    c.setSize(new Size(20, 20));
    c.setColor(new Color(255, 0, 0));
    model.addShape(c);
    model.addAnimation("C", new Transition(5, 5, 0, 0, 0, 1, 1, 0, 5));

    AnimationView view = new VisualView(0, 0, 360, 360, 10, model);
    view.render();
  }
}
