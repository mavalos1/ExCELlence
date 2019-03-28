import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.SVGView;
import org.junit.Test;

import java.awt.event.ActionListener;
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
  String expectedOutput = "<svg width=\"30\" height=\"100\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
      "\n" +
      "<rect id=\"R\" x=\"0.00\" y=\"0.00\" width=\"100.00\" height=\"100.00\" fill=\"rgb(50,50,50)\" visibility=\"visible\" >\n" +
      "\t<set attributeType=\"xml\" attributeName=\"x\" begin=\"2000ms\" to=\"20\" fill=\"freeze\" />\n" +
      "\t<set attributeType=\"xml\" attributeName=\"y\" begin=\"2000ms\" to=\"20\" fill=\"freeze\" />\n" +
      "\t<set attributeType=\"xml\" attributeName=\"width\" begin=\"2000ms\" to=\"30\" fill=\"freeze\" />\n" +
      "\t<set attributeType=\"xml\" attributeName=\"height\" begin=\"2000ms\" to=\"30\" fill=\"freeze\" />\n" +
      "\t<set attributeName=\"fill\" begin=\"2000ms\" to=\"rgb(30,30,30)\" fill=\"freeze\" />\n" +
      "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"10000ms\" attributeName=\"x\" from=\"20\" to=\"10\" fill=\"freeze\" />\n" +
      "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"10000ms\" attributeName=\"y\" from=\"20\" to=\"10\" fill=\"freeze\" />\n" +
      "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"10000ms\" attributeName=\"width\" from=\"30\" to=\"10\" fill=\"freeze\" />\n" +
      "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"10000ms\" attributeName=\"height\" from=\"30\" to=\"10\" fill=\"freeze\" />\n" +
      "\t<animate attributeName=\"fill\" begin=\"2000ms\" dur=\"10000ms\" from=\"rgb(30,30,30)\" to=\"rgb(10,10,10)\"  fill=\"freeze\"/>\n" +
      "</rect>\n" +
      "<ellipse id=\"C\" cx=\"0.00\" cy=\"0.00\" rx=\"0.00\" ry=\"0.00\" fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
      "\t<set attributeType=\"xml\" attributeName=\"cx\" begin=\"2000ms\" to=\"20\" fill=\"freeze\" />\n" +
      "\t<set attributeType=\"xml\" attributeName=\"cy\" begin=\"2000ms\" to=\"20\" fill=\"freeze\" />\n" +
      "\t<set attributeType=\"xml\" attributeName=\"rx\" begin=\"2000ms\" to=\"30\" fill=\"freeze\" />\n" +
      "\t<set attributeType=\"xml\" attributeName=\"ry\" begin=\"2000ms\" to=\"30\" fill=\"freeze\" />\n" +
      "\t<set attributeName=\"fill\" begin=\"2000ms\" to=\"rgb(30,30,30)\" fill=\"freeze\" />\n" +
      "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"10000ms\" attributeName=\"cx\" from=\"20\" to=\"10\" fill=\"freeze\" />\n" +
      "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"10000ms\" attributeName=\"cy\" from=\"20\" to=\"10\" fill=\"freeze\" />\n" +
      "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"10000ms\" attributeName=\"rx\" from=\"30\" to=\"10\" fill=\"freeze\" />\n" +
      "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"10000ms\" attributeName=\"ry\" from=\"30\" to=\"10\" fill=\"freeze\" />\n" +
      "\t<animate attributeName=\"fill\" begin=\"2000ms\" dur=\"10000ms\" from=\"rgb(30,30,30)\" to=\"rgb(10,10,10)\"  fill=\"freeze\"/>\n" +
      "</ellipse>\n" +
      "</svg>";

  @Test(expected = IllegalArgumentException.class)
  public void testSetBoundsNegativeWidthHeight() {
    AnimationView view = new SVGView();
    view.setBounds(0,0, -100, -100);
  }

  @Test
  public void testRenderConsoleSetBounds() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    AnimationModel model = new Model();
    AnimationView view = new SVGView();
    AnimationController controller = new Controller(model, view, 1);

    view.setBounds(10, 10, 30, 100);

    Rectangle r = new Rectangle("R", 0, 0, 100, 100, 50, 50, 50);
    Ellipse e = new Ellipse("C");
    Transition t = new Transition(2, 12, 20, 20, 30, 30, 30, 30, 30, 10, 10, 10, 10, 10, 10, 10);

    model.addShape(r, e);
    model.addTransition("R", t);
    model.addTransition("C", t);

    controller.start();

    assertEquals(expectedOutput + "\n", outContent.toString());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSetListener() {
    AnimationModel model = new Model();
    AnimationView view = new SVGView();
    ActionListener controller = new Controller(model, view ,1);
    view.setListener(controller);
  }

  @Test
  public void testGetSpeed() {
    AnimationModel model = new Model();
    AnimationView view = new SVGView();
    ActionListener controller = new Controller(model, view ,1);
    assertEquals(1, view.getSpeed());
  }

  @Test
  public void testSetSpeed() {
    AnimationModel model = new Model();
    AnimationView view = new SVGView();
    ActionListener controller = new Controller(model, view ,1);
    view.setSpeed(100);
    assertEquals(100, view.getSpeed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNonPositiveSpeed() {
    AnimationModel model = new Model();
    AnimationView view = new SVGView();
    ActionListener controller = new Controller(model, view ,1);
    view.setSpeed(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNegativeSpeed() {
    AnimationModel model = new Model();
    AnimationView view = new SVGView();
    ActionListener controller = new Controller(model, view ,1);
    view.setSpeed(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNullOutput() {
    AnimationView view = new SVGView();
    view.setOutputFile(null);
  }

  public void testRenderFileSetOutput() {
    String outputFile = "svgViewFile.txt";

    AnimationModel model = new Model();
    AnimationView view = new SVGView();
    AnimationController controller = new Controller(model, view, 1);

    view.setBounds(10, 10, 30, 100);
    view.setOutputFile(outputFile);

    Rectangle r = new Rectangle("R", 0, 0, 100, 100, 50, 50, 50);
    Ellipse e = new Ellipse("C");
    Transition t = new Transition(2, 12, 20, 20, 30, 30, 30, 30, 30, 10, 10, 10, 10, 10, 10, 10);

    model.addShape(r, e);
    model.addTransition("R", t);
    model.addTransition("C", t);

    controller.start();

    try {
      String content = new Scanner(new File(outputFile)).useDelimiter("\\Z").next();
      assertEquals(expectedOutput, content);
    } catch (IOException err) {
      err.printStackTrace();
    }
  }
}
