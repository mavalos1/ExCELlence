import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.TextualView;

import org.junit.Test;

import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for the textual view.
 */
public class TextualViewTest {
  String expectedOutput = "canvas 10 10 30 100\n" +
      "shape R rectangle\n" +
      "shape C ellipse\n" +
      "1 motion R 0 0 100 100 50 50 50\n" +
      "1 motion C 0 0 0 0 0 0 0\n" +
      "2 motion R 20 20 30 30 30 30 30\n" +
      "2 motion C 20 20 30 30 30 30 30\n" +
      "3 motion R 19 19 28 28 28 28 28\n" +
      "3 motion C 19 19 28 28 28 28 28\n" +
      "4 motion R 18 18 26 26 26 26 26\n" +
      "4 motion C 18 18 26 26 26 26 26\n" +
      "5 motion R 17 17 24 24 24 24 24\n" +
      "5 motion C 17 17 24 24 24 24 24\n" +
      "6 motion R 16 16 22 22 22 22 22\n" +
      "6 motion C 16 16 22 22 22 22 22\n" +
      "7 motion R 15 15 20 20 20 20 20\n" +
      "7 motion C 15 15 20 20 20 20 20\n" +
      "8 motion R 14 14 18 18 18 18 18\n" +
      "8 motion C 14 14 18 18 18 18 18\n" +
      "9 motion R 13 13 16 16 16 16 16\n" +
      "9 motion C 13 13 16 16 16 16 16\n" +
      "10 motion R 12 12 14 14 14 14 14\n" +
      "10 motion C 12 12 14 14 14 14 14\n" +
      "11 motion R 11 11 12 12 12 12 12\n" +
      "11 motion C 11 11 12 12 12 12 12\n" +
      "12 motion R 10 10 10 10 10 10 10\n" +
      "12 motion C 10 10 10 10 10 10 10";

  @Test(expected = IllegalArgumentException.class)
  public void testSetBoundsNegativeWidthHeight() {
    AnimationView view = new TextualView();
    view.setBounds(0,0, -100, -100);
  }

  @Test
  public void testRenderConsoleSetBounds() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    AnimationModel model = new Model();
    AnimationView view = new TextualView();
    AnimationController controller = new Controller(model, view, 1);

    view.setBounds(10, 10, 30, 100);

    Rectangle r = new Rectangle("R", 0, 0, 100, 100, 50, 50, 50, 0);
    Ellipse e = new Ellipse("C");
    Transition t = new Transition(2, 12, 20, 20, 30, 30, 30, 30, 30, 0,10, 10, 10, 10, 10, 10, 10
        , 0);

    model.addShape(r, e);
    model.addTransition("R", t);
    model.addTransition("C", t);

    controller.start();

    assertEquals(expectedOutput + "\n", outContent.toString());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSetListener() {
    AnimationModel model = new Model();
    AnimationView view = new TextualView();
    ActionListener controller = new Controller(model, view ,1);
    view.setListener(controller);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetSpeed() {
    AnimationModel model = new Model();
    AnimationView view = new TextualView();
    ActionListener controller = new Controller(model, view ,1);
    view.getSpeed();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSetSpeed() {
    AnimationModel model = new Model();
    AnimationView view = new TextualView();
    ActionListener controller = new Controller(model, view ,1);
    view.setSpeed(100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNullOutput() {
    AnimationView view = new TextualView();
    view.setOutputFile(null);
  }

  @Test
  public void testRenderFileSetOutput() {
    String outputFile = "textualViewFile.txt";

    AnimationModel model = new Model();
    AnimationView view = new TextualView();
    AnimationController controller = new Controller(model, view, 1);

    view.setBounds(10, 10, 30, 100);
    view.setOutputFile(outputFile);

    Rectangle r = new Rectangle("R", 0, 0, 100, 100, 50, 50, 50, 0);
    Ellipse e = new Ellipse("C");
    Transition t = new Transition(2, 12, 20, 20, 30, 30, 30, 30, 30, 0, 10, 10, 10, 10, 10, 10, 10, 0);

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
