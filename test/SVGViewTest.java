import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.SingleAnimation;
import cs3500.animator.model.helper.Color;
import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.SVGView;

public class SVGViewTest {
  public static void main(String[] args) {
    Rectangle r = new Rectangle("R", new Position2D(10, 10), new Size(100, 100),
        new Color(100,100, 100), 0.0);
    AnimationModel model = new SingleAnimation(r);
    model.addAnimation("R", new Transition(50, 0, 15, 15, 15, 0, 0, 0, 10));
    AnimationView view = new SVGView(0, 0, 360, 360, 1, model);

    Ellipse c = new Ellipse("C");
    c.setSize(new Size(20, 20));
    model.addShape(c);
    model.addAnimation("C", new Transition(5, 5, 15, 15, 15, 10, 10, 0, 5));

    view.render();
  }
}
