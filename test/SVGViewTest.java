import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.SVGView;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for the SVG View.
 */
public class SVGViewTest {
  String output = "<svg width=\"100\" height=\"100\" version=\"1.1\" xmlns=\"http://www.w3" +
      ".org/2000/svg\">\n" +
      "\n" +
      "<rect id=\"R\" x=\"20.00\" y=\"20.00\" width=\"30.00\" height=\"30.00\" fill=\"rgb(44," +
      "44,44)\" visibility=\"visible\" >\n"  +
      "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"10000ms\" attributeName=\"x\" " +
      "from=\"20\" to=\"10\" fill=\"freeze\" />\n"  +
      "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"10000ms\" attributeName=\"y\" " +
      "from=\"20\" to=\"10\" fill=\"freeze\" />\n"  +
      "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"10000ms\" " +
      "attributeName=\"width\" from=\"30\" to=\"10\" fill=\"freeze\" />\n"  +
      "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"10000ms\" " +
      "attributeName=\"height\" from=\"30\" to=\"10\" fill=\"freeze\" />\n"  +
      "\t<animate attributeName=\"fill\" begin=\"2000ms\" dur=\"10000ms\" from=\"rgb(300,300," +
      "300)\" to=\"rgb(10,10,10)\"/>\n"  +
      "</rect>\n" +
      "<ellipse id=\"C\" cx=\"50.00\" cy=\"50.00\" rx=\"25.00\" ry=\"25.00\" fill=\"rgb(80,250," +
      "124)\" visibility=\"visible\" >\n"  +
      "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" attributeName=\"cx\" " +
      "from=\"50\" to=\"10\" fill=\"freeze\" />\n"  +
      "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" attributeName=\"cy\" " +
      "from=\"50\" to=\"10\" fill=\"freeze\" />\n"  +
      "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" attributeName=\"cx\" " +
      "from=\"50\" to=\"10\" fill=\"freeze\" />\n"  +
      "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" attributeName=\"cy\" " +
      "from=\"50\" to=\"10\" fill=\"freeze\" />\n"  +
      "\t<animate attributeName=\"fill\" begin=\"5000ms\" dur=\"5000ms\" from=\"rgb(80,250,124)" +
      "\" to=\"rgb(10,10,10)\"/>\n"  +
      "</ellipse>\n" +
      "</svg>";

  @Test(expected = IllegalArgumentException.class)
  public void testSetBoundsNegativeSize() {
    SVGView view = new SVGView(10, 10, 500, 500, 1);
    view.setBounds(10, 10, -100, -100);
  }

  @Test(expected = NullPointerException.class)
  public void testRenderFileNullName() {
    SVGView view = new SVGView(10, 10, 500, 500, 1, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNonPositiveSpeed() {
    SVGView view = new SVGView(10, 10, 500, 500, -1, "out.svg");
  }

  @Test
  public void testRender() {
    AnimationModel model = new Model();
    SVGView view = new SVGView(10, 10, 500, 500, 1, "out.svg");
    AnimationController controller = new Controller(model, view, 10);

    view.setBounds(0, 0, 100, 100);

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

    try {
      String content = new Scanner(new File("out.svg")).useDelimiter("\\Z").next();
      assertEquals(output, content);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testRenderSystemOut() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    AnimationModel model = new Model();
    SVGView view = new SVGView(10, 10, 500, 500, 1);
    AnimationController controller = new Controller(model, view, 10);
    view.setBounds(0, 0, 100, 100);

    Rectangle rect = new Rectangle("R", 0, 0, 100, 100, 50, 50, 50);
    rect.addTransition(
        new Transition(2, 12, 20, 20, 30, 30, 300, 300, 300, 10, 10, 10, 10, 10, 10, 10));
    model.addShape(rect);

    Ellipse ell = new Ellipse("C", 50, 50, 50, 50, 250, 250, 250);
    ell.addTransition(
        new Transition(5, 10, 50, 50, 50, 50, 80,250,124, 10, 10, 10, 10, 10, 10, 10));
    model.addShape(ell);

    controller.animate();

    assertEquals(output + "\n", outContent.toString());
  }
}
