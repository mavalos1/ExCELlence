package cs3500.nguyenmayeux.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.nguyenmayeux.model.helper.Position2D;
import cs3500.nguyenmayeux.model.helper.Transition;
import cs3500.nguyenmayeux.model.shapes.Rectangle;
import cs3500.nguyenmayeux.model.shapes.Shape;

public class MainTest {

  public static void main(String[] args) {
    Rectangle rect = new Rectangle("R");
    Transition asdf = new Transition(new Position2D(5, 0), 5, 5, 5, 0, 0, 0, 10);
    rect.addTransition(asdf);
    List<Shape> shapes = new ArrayList<Shape>();
    shapes.add(rect);

    SingleAnimation anim = new SingleAnimation(shapes);
    anim.animate();
    System.out.println(anim.parseAnimationOutput(rect));
  }
}
