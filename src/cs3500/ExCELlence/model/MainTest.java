package cs3500.ExCELlence.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.ExCELlence.model.shapes.Rectangle;
import cs3500.ExCELlence.model.shapes.Shape;
import cs3500.ExCELlence.model.transitions.Transition;
import cs3500.ExCELlence.model.transitions.TransitionImpl;

public class MainTest {

  public static void main(String[] args) {
    Rectangle rect = new Rectangle();
    Transition asdf = new TransitionImpl(new Position2D(5, 0), new Color(), 0, 0, 0, 10, true);
    rect.setName("R");
    rect.addTransition(asdf);
    List<Shape> shapes = new ArrayList<Shape>();
    shapes.add(rect);

    SingleAnimation anim = new SingleAnimation(shapes);
    anim.animate();
    System.out.println(anim.parseAnimationOutput(rect));
  }
}
