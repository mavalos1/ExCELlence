import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


import cs3500.nguyenmayeux.model.Color;
import cs3500.nguyenmayeux.model.Position2D;
import cs3500.nguyenmayeux.model.SingleAnimation;
import cs3500.nguyenmayeux.model.shapes.Rectangle;
import cs3500.nguyenmayeux.model.shapes.Shape;
import cs3500.nguyenmayeux.model.transitions.Transition;
import cs3500.nguyenmayeux.model.transitions.TransitionImpl;

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

    Transition t = new TransitionImpl(
        new Position2D(1,1), 5, -5, 5, 2, 1, 3, 5, false);

    ((Rectangle) rect).addTransition(t);

    List<Shape> shapes = new ArrayList<>();
    shapes.add(rect);

    SingleAnimation anim = new SingleAnimation(shapes);
    anim.animate();

    assertEquals(rect.getColor().getR(), 75);
    assertEquals(rect.getColor().getG(), 125);
    assertEquals(rect.getColor().getB(), 105);
    assertEquals(rect.getPosition().getX(), 10, 0.001);
    assertEquals(rect.getPosition().getY(), 10, 0.001);
    assertEquals(rect.getHeight(), 15, 0.001);
    assertEquals(rect.getWidth(), 15, 0.001);
    assertEquals(rect.getName(), "R");
    assertEquals(rect.getRotation(), 15, 0.001);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameShape() {
    Shape rect = new Rectangle();
    rect.setName(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthShape() {
    Shape rect = new Rectangle();
    rect.setWidth(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightShape() {
    Shape rect = new Rectangle();
    rect.setHeight(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeColor() {
    Color c = new Color(-1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullShapeList() {
    List<Shape> shapes = null;
    SingleAnimation animation = new SingleAnimation(shapes);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyShapeList() {
    List<Shape> shapes = new ArrayList<>();
    SingleAnimation animation = new SingleAnimation(shapes);
  }
}
