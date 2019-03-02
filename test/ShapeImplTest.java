//package cs3500.ExCELlence.model.shapes;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


import cs3500.ExCELlence.model.Color;
import cs3500.ExCELlence.model.Position2D;
import cs3500.ExCELlence.model.SingleAnimation;
import cs3500.ExCELlence.model.shapes.Rectangle;
import cs3500.ExCELlence.model.shapes.Shape;
import cs3500.ExCELlence.model.transitions.Transition;
import cs3500.ExCELlence.model.transitions.TransitionImpl;

public class ShapeImplTest {

  @Test
  public void testShapeTest() {
    Shape rect = new Rectangle();
    Color c = new Color();
    c.setR(50);
    c.setG(150);
    c.setB(80);

    rect.setColor(c);
    rect.setHeight(5);
    rect.setWidth(10);
    rect.setName("R");
    rect.setPosition(new Position2D(5, 5));
    rect.setRotation(0);

    Color c2 = new Color();
    c2.setR(5);
    c2.setG(-5);
    c2.setB(5);
    Transition t = new TransitionImpl(new Position2D(1,1), c2, 2, 1, 3, 5, false);

    ((Rectangle) rect).addTransition(t);

    List<Shape> shapes = new ArrayList<>();
    shapes.add(rect);

    SingleAnimation anim = new SingleAnimation(shapes);
    anim.animate();

    assertEquals(rect.getColor().getB(), 105);
  }
}
