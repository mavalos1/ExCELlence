import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for the animation model.
 */
public class ModelTest {
  @Test
  public void testEmptyModel() {
    AnimationModel model = new Model();
    assertEquals(true, model.getShapes().isEmpty());
  }

  @Test
  public void testAddShape() {
    AnimationModel model = new Model();
    Rectangle r = new Rectangle("R");
    Ellipse e = new Ellipse("C");
    model.addShape(r);
    model.addShape(e);
    assertEquals("R", model.getShape("R").getName());
    assertEquals("C", model.getShape("C").getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingTransition() {
    Rectangle r = new Rectangle("R");
    Transition t1 = new Transition(1, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    Transition t2 = new Transition(6, 15, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);

    r.addTransition(t1, t2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConsistentStartEndState() {
    Rectangle r = new Rectangle("R");
    Transition t1 = new Transition(1, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    Transition t2 = new Transition(10, 15, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);

    r.addTransition(t1, t2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoGapShape() {
    Rectangle r = new Rectangle("R");
    Transition t1 = new Transition(1, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    Transition t2 = new Transition(15, 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);

    r.addTransition(t1, t2);
  }
}
