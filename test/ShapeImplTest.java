import cs3500.nguyenmayeux.model.helper.Transition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import cs3500.nguyenmayeux.model.shapes.Oval;
import cs3500.nguyenmayeux.model.helper.Color;
import cs3500.nguyenmayeux.model.helper.Position2D;
import cs3500.nguyenmayeux.model.SingleAnimation;
import cs3500.nguyenmayeux.model.shapes.Rectangle;
import cs3500.nguyenmayeux.model.shapes.Shape;

public class ShapeImplTest {

  @Test
  public void testShapeTest() {
    Shape rect = new Rectangle("R");
    Color c = new Color();
    c.setR(50);
    c.setG(150);
    c.setB(80);

    rect.setColor(c);
    rect.setHeight(5);
    rect.setWidth(10);
    rect.setPosition(new Position2D(5, 5));
    rect.setRotation(0);

    Transition t = new Transition(
        new Position2D(1,1), 5, -5, 5, 2, 1, 3, 5);

    ((Rectangle) rect).addTransition(t);

    Shape circle = new Oval("C");
    circle.setColor(new Color(20, 240, 40));
    circle.setHeight(10);
    circle.setWidth(5);
    circle.setPosition(new Position2D(10, 10));
    circle.setRotation(20);
    Transition tc = new Transition(
        new Position2D(-1, -1), -1, 1, -1, -2, -1, -3, 5);
    ((Oval) circle).addTransition(tc);

    List<Shape> shapes = new ArrayList<>();
    shapes.add(rect);
    shapes.add(circle);

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

    String output = "shape R rectangle\n" +
        "motion R\t0 6.0 6.0 11.0 7.0 55 145 85\n" +
        "motion R\t1 7.0 7.0 12.0 9.0 60 140 90\n" +
        "motion R\t2 8.0 8.0 13.0 11.0 65 135 95\n" +
        "motion R\t3 9.0 9.0 14.0 13.0 70 130 100\n" +
        "motion R\t4 10.0 10.0 15.0 15.0 75 125 105\n";
    assertEquals(output, anim.parseAnimationOutput(rect));

    String outputAll =
        output + "\n" +
        "shape C oval\n" +
        "motion C\t0 9.0 9.0 4.0 8.0 19 241 39\n" +
        "motion C\t1 8.0 8.0 3.0 6.0 18 242 38\n" +
        "motion C\t2 7.0 7.0 2.0 4.0 17 243 37\n" +
        "motion C\t3 6.0 6.0 1.0 2.0 16 244 36\n" +
        "motion C\t4 5.0 5.0 0.0 0.0 15 245 35\n" +
        "\n";
    assertEquals(outputAll, anim.parseAllOutputs());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameShape() {
    Shape rect = new Rectangle("R");
    rect.setName(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthShape() {
    Shape rect = new Rectangle("R");
    rect.setWidth(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightShape() {
    Shape rect = new Rectangle("R");
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
}
  