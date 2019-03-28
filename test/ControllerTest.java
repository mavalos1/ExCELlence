import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.helper.Color;
import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for the controller.
 */
public class ControllerTest {
  AnimationModel model = new Model();
  SVGView view = new SVGView(10, 10, 500, 500, 1, "out.svg");

  @Test(expected = IllegalArgumentException.class)
  public void testNonPositiveSpeed() {
    AnimationController c = new Controller(model, view, 0);
  }

  @Test(expected = NullPointerException.class)
  public void testNullModel() {
    AnimationController c = new Controller(null, view, 1);
  }

  @Test(expected = NullPointerException.class)
  public void testNullView() {
    AnimationController c = new Controller(model, null, 1);
  }

  @Test(expected = NullPointerException.class)
  public void testNullType() {
    AnimationController c = new Controller(null, 1, "output.txt");
  }

  @Test(expected = NullPointerException.class)
  public void testNullOutputFile() {
    AnimationController c = new Controller("svg", 1, null);
  }

  @Test
  public void testNextTickRenderStartSetBoundsAddShapeAddTransition() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    AnimationModel model = new Model();
    AnimationView view = new TextualView();
    AnimationController controller = new Controller(model, view, 1);

    controller.addShape("R", "rectangle");
    controller.addTransition("R",
        1, 1, 1, 1, 1, 1, 1, 1,
        11, 10, 10, 10, 10, 10, 10, 10
    );

    Shape r = model.getShape("R");

    assertEquals(new Position2D(0, 0), r.getPosition());
    assertEquals(new Size(0, 0), r.getSize());
    assertEquals(new Color(0, 0, 0), r.getColor());

    controller.setBounds(20, 20, 400, 400);
    controller.renderView();
    assertEquals("canvas 20 20 400 400\n" +
        "shape R rectangle\n", outContent.toString());

    controller.nextTick();
    controller.renderView();

    assertEquals(new Position2D(1, 1), r.getPosition());
    assertEquals(new Size(1, 1), r.getSize());
    assertEquals(new Color(1, 1, 1), r.getColor());
    assertEquals("canvas 20 20 400 400\n" +
        "shape R rectangle\n" +
        "1 motion R 1 1 1 1 1 1 1\n", outContent.toString());

    controller.start();

    assertEquals(new Position2D(10, 10), r.getPosition());
    assertEquals(new Size(10, 10), r.getSize());
    assertEquals(new Color(10, 10, 10), r.getColor());
    assertEquals("canvas 20 20 400 400\n" +
        "shape R rectangle\n" +
        "1 motion R 1 1 1 1 1 1 1\n" +
        "1 motion R 1 1 1 1 1 1 1\n" +
        "2 motion R 2 2 2 2 2 2 2\n" +
        "3 motion R 3 3 3 3 3 3 3\n" +
        "4 motion R 4 4 4 4 4 4 4\n" +
        "5 motion R 5 5 5 5 5 5 5\n" +
        "6 motion R 6 6 6 6 6 6 6\n" +
        "7 motion R 6 6 6 6 6 6 6\n" +
        "8 motion R 7 7 7 7 7 7 7\n" +
        "9 motion R 8 8 8 8 8 8 8\n" +
        "10 motion R 9 9 9 9 9 9 9\n" +
        "11 motion R 10 10 10 10 10 10 10\n", outContent.toString());
  }

  @Test(expected = NullPointerException.class)
  public void testNullNameShape() {
    AnimationModel model = new Model();
    AnimationView view = new TextualView();
    AnimationController controller = new Controller(model, view, 1);

    controller.addShape(null, "rectangle");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyNameShape() {
    AnimationModel model = new Model();
    AnimationView view = new TextualView();
    AnimationController controller = new Controller(model, view, 1);

    controller.addShape("", "rectangle");
  }

  @Test(expected = NullPointerException.class)
  public void testNullTypeShape() {
    AnimationModel model = new Model();
    AnimationView view = new TextualView();
    AnimationController controller = new Controller(model, view, 1);

    controller.addShape("R", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyTypeShape() {
    AnimationModel model = new Model();
    AnimationView view = new TextualView();
    AnimationController controller = new Controller(model, view, 1);

    controller.addShape("R", "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnsupportedTypeShape() {
    AnimationModel model = new Model();
    AnimationView view = new TextualView();
    AnimationController controller = new Controller(model, view, 1);

    controller.addShape("R", "triangle");
  }

  @Test(expected = NullPointerException.class)
  public void testNullTransitionName() {
    AnimationModel model = new Model();
    AnimationView view = new TextualView();
    AnimationController controller = new Controller(model, view, 1);

    controller.addTransition(null, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
  }

  @Test
  public void testAddDeleteKeyFramesRestart() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    AnimationModel model = new Model();
    AnimationView view = new TextualView();
    AnimationController controller = new Controller(model, view, 1);

    controller.setBounds(20, 20, 400, 400);

    controller.addShape("R", "rectangle");
    controller.addKeyFrame("R", 1, 1, 1, 1, 1, 1, 1, 1);
    controller.addKeyFrame("R", 11, 10, 10, 10, 10, 10, 10, 10);

    controller.renderView();
    controller.nextTick();

    Shape r = model.getShape("R");

    assertEquals(new Position2D(1, 1), r.getPosition());
    assertEquals(new Size(1, 1), r.getSize());
    assertEquals(new Color(1, 1, 1), r.getColor());

    controller.renderView();
    assertEquals("canvas 20 20 400 400\n" +
        "shape R rectangle\n" +
        "1 motion R 1 1 1 1 1 1 1\n", outContent.toString());

    controller.start();
    assertEquals(new Position2D(10, 10), r.getPosition());
    assertEquals(new Size(10, 10), r.getSize());
    assertEquals(new Color(10, 10, 10), r.getColor());
    assertEquals("canvas 20 20 400 400\n" +
        "shape R rectangle\n" +
        "1 motion R 1 1 1 1 1 1 1\n" +
        "1 motion R 1 1 1 1 1 1 1\n" +
        "2 motion R 2 2 2 2 2 2 2\n" +
        "3 motion R 3 3 3 3 3 3 3\n" +
        "4 motion R 4 4 4 4 4 4 4\n" +
        "5 motion R 5 5 5 5 5 5 5\n" +
        "6 motion R 6 6 6 6 6 6 6\n" +
        "7 motion R 6 6 6 6 6 6 6\n" +
        "8 motion R 7 7 7 7 7 7 7\n" +
        "9 motion R 8 8 8 8 8 8 8\n" +
        "10 motion R 9 9 9 9 9 9 9\n" +
        "11 motion R 10 10 10 10 10 10 10\n", outContent.toString());

    controller.restart();
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    controller.addKeyFrame("R", 5, 0, 0, 0, 0, 0, 0, 0);

    controller.start();
    assertEquals("canvas 20 20 400 400\n" +
        "shape R rectangle\n" +
        "1 motion R 1 1 1 1 1 1 1\n" +
        "2 motion R 1 1 1 1 1 1 1\n" +
        "3 motion R 1 1 1 1 1 1 1\n" +
        "4 motion R 0 0 0 0 0 0 0\n" +
        "5 motion R 0 0 0 0 0 0 0\n" +
        "6 motion R 2 2 2 2 2 2 2\n" +
        "7 motion R 3 3 3 3 3 3 3\n" +
        "8 motion R 5 5 5 5 5 5 5\n" +
        "9 motion R 7 7 7 7 7 7 7\n" +
        "10 motion R 8 8 8 8 8 8 8\n" +
        "11 motion R 10 10 10 10 10 10 10\n", outContent.toString());

    controller.restart();
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    controller.deleteKeyFrame("R", 5);

    controller.start();
    assertEquals("canvas 20 20 400 400\n" +
        "shape R rectangle\n" +
        "1 motion R 1 1 1 1 1 1 1\n" +
        "2 motion R 2 2 2 2 2 2 2\n" +
        "3 motion R 3 3 3 3 3 3 3\n" +
        "4 motion R 4 4 4 4 4 4 4\n" +
        "5 motion R 5 5 5 5 5 5 5\n" +
        "6 motion R 6 6 6 6 6 6 6\n" +
        "7 motion R 6 6 6 6 6 6 6\n" +
        "8 motion R 7 7 7 7 7 7 7\n" +
        "9 motion R 8 8 8 8 8 8 8\n" +
        "10 motion R 9 9 9 9 9 9 9\n" +
        "11 motion R 10 10 10 10 10 10 10\n", outContent.toString());
  }

  @Test
  public void testSetSpeed() {
    AnimationModel model = new Model();
    AnimationView view = new SVGView();
    AnimationController controller = new Controller(model, view, 1);

    controller.setSpeed(100);

    assertEquals(100, view.getSpeed());
  }
}
