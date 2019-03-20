import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.TextualView;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TextualViewTest {

  @Test
  public void testSetBounds_success() {
    TextualView textualView = new TextualView(10,10,300,300);
    textualView.setBounds(0,0, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBounds_error() {
    TextualView textualView = new TextualView(10,10,300,300);
    textualView.setBounds(0,0, -100, -100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRenderFile_error() {
    TextualView textualView = new TextualView(10,10,300,300);
    textualView.renderFile("");
  }

  @Test
  public void testRenderConsole() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    TextualView textualView = new TextualView(10,10,300,300);

    textualView.renderConsole("hello world");

    assertEquals(outContent.toString(), "hello world");

  }

  @Test
  public void testRender() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    AnimationModel model = new Model();
    AnimationView view = new TextualView(10, 10, 300, 300);
    AnimationController controller = new Controller(model, view, 1);

    Rectangle rect = new Rectangle("R", 0, 0, 100, 100, 50, 50, 50);
    rect.addTransition(
            new Transition(2, 12, 20, 20, 30, 30, 30, 30, 30, 10, 10, 10, 10, 10, 10, 10));
    model.addShape(rect);

    controller.animate();

    assertEquals(outContent.toString(), "shape R rectangle\n" +
            "1 motion R 20 20 30 30 30 30 30\n" +
            "2 motion R 20 20 30 30 30 30 30\n" +
            "3 motion R 19 19 28 28 28 28 28\n" +
            "4 motion R 18 18 26 26 26 26 26\n" +
            "5 motion R 17 17 24 24 24 24 24\n" +
            "6 motion R 16 16 22 22 22 22 22\n" +
            "7 motion R 15 15 20 20 20 20 20\n" +
            "8 motion R 14 14 18 18 18 18 18\n" +
            "9 motion R 13 13 16 16 16 16 16\n" +
            "10 motion R 12 12 14 14 14 14 14\n" +
            "11 motion R 11 11 12 12 12 12 12\n" +
            "12 motion R 10 10 10 10 10 10 10\n");
  }

}
