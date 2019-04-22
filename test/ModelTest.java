import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.helper.Color;
import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;
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
  public void testAddGetShape() {
    AnimationModel model = new Model();
    Rectangle r = new Rectangle("R");
    Ellipse e = new Ellipse("C");
    model.addShape(r);
    model.addShape(e);
    assertEquals("R", model.getShape("R").getName());
    assertEquals("C", model.getShape("C").getName());
    assertEquals(2, model.getShapes().size());
    assertEquals("R", model.getShapes().get(0).getName());
    assertEquals("C", model.getShapes().get(1).getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransitionInvalidName() {
    AnimationModel model = new Model();
    model.addShape(new Rectangle("R"));
    Transition t1 = new Transition(1, 10, 1, 2, 3, 4, 5, 6, 7, 0,8, 9, 10, 11, 12, 13, 14, 0);

    model.addTransition("C", t1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeBeginTime() {
    Transition t1 = new Transition(-1, 10, 1, 2, 3, 4, 5, 6, 7, 0, 8, 9, 10, 11, 12, 13, 14, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeDurationTransition() {
    Transition t1 = new Transition(5, 4, 1, 2, 3, 4, 5, 6, 7, 0, 8, 9, 10, 11, 12, 13, 14, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingTransition() {
    Rectangle r = new Rectangle("R");
    Transition t1 = new Transition(1, 10, 1, 2, 3, 4, 5, 6, 7, 0, 8, 9, 10, 11, 12, 13, 14, 0);
    Transition t2 = new Transition(6, 15, 1, 2, 3, 4, 5, 6, 7, 0, 8, 9, 10, 11, 12, 13, 14, 0);

    AnimationModel model = new Model();
    model.addShape(r);
    model.addTransition("R", t1);
    model.addTransition("R", t2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConsistentStartEndState() {
    Rectangle r = new Rectangle("R");
    Transition t1 = new Transition(1, 10, 1, 2, 3, 4, 5, 6, 7, 0, 8, 9, 10, 11, 12, 13, 14, 0);
    Transition t2 = new Transition(10, 15, 1, 2, 3, 4, 5, 6, 7, 0, 8, 9, 10, 11, 12, 13, 14, 0);

    AnimationModel model = new Model();
    model.addShape(r);
    model.addTransition("R", t1);
    model.addTransition("R", t2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoGapTransitionShape() {
    Rectangle r = new Rectangle("R");
    Transition t1 = new Transition(1, 10, 1, 2, 3, 4, 5, 6, 7, 0, 8, 9, 10, 11, 12, 13, 14, 0);
    Transition t2 = new Transition(15, 20, 1, 2, 3, 4, 5, 6, 7, 0, 8, 9, 10, 11, 12, 13, 14, 0);

    AnimationModel model = new Model();
    model.addShape(r);
    model.addTransition("R", t1);
    model.addTransition("R", t2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoMatchingShape() {
    AnimationModel model = new Model();
    model.addShape(new Rectangle("R"));
    Shape s = model.getShape("C");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    AnimationModel model = new Model();
    model.addShape(new Rectangle("R"));
    Shape s = model.getShape(null);
  }

  @Test(expected = IllegalStateException.class)
  public void testEmptyModelTick() {
    AnimationModel model = new Model();
    model.tick();
  }

  @Test
  public void testTickCanTickCurrentTickReset() {
    AnimationModel model = new Model();
    assertEquals(false, model.canTick());
    assertEquals(0, model.getCurrentTick());

    Rectangle r = new Rectangle("R");
    r.addTransition(new Transition(
        2, 10,
        1, 2, 3, 4, 5, 6, 7, 0,
        8, 9, 10, 11, 12, 13, 14, 0));
    model.addShape(r);

    assertEquals(true, model.canTick());
    assertEquals(new Position2D(0, 0), r.getPosition());
    assertEquals(new Size(0, 0), r.getSize());
    assertEquals(new Color(0, 0, 0), r.getColor());

    model.tick();
    model.tick();

    assertEquals(2, model.getCurrentTick());
    assertEquals(new Position2D(1, 2), r.getPosition());
    assertEquals(new Size(3, 4), r.getSize());
    assertEquals(new Color(5, 6, 7), r.getColor());

    for (int i = 0; i < 8; i++) {
      model.tick();
    }

    assertEquals(false, model.canTick());
    assertEquals(10, model.getCurrentTick());
    assertEquals(new Position2D(8, 9), r.getPosition());
    assertEquals(new Size(10, 11), r.getSize());
    assertEquals(new Color(12, 13, 14), r.getColor());

    model.reset();

    assertEquals(0, model.getCurrentTick());
    assertEquals(new Position2D(0, 0), r.getPosition());
    assertEquals(new Size(0, 0), r.getSize());
    assertEquals(new Color(0, 0, 0), r.getColor());
  }

  @Test
  public void testAddDeleteKeyFrame() {
    AnimationModel model = new Model();
    Rectangle r = new Rectangle("R");
    model.addShape(r);

    assertEquals(new Position2D(0, 0), r.getPosition());
    assertEquals(new Size(0, 0), r.getSize());
    assertEquals(new Color(0, 0, 0), r.getColor());

    model.addKeyFrame("R", 3, 3, 3, 3, 3, 3, 3, 3, 0);
    model.addKeyFrame("R", 10, 10, 10, 10, 10, 10, 10, 10, 0);

    model.tick();
    model.tick();
    model.tick();

    assertEquals(3, model.getCurrentTick());
    assertEquals(new Position2D(3, 3), r.getPosition());
    assertEquals(new Size(3, 3), r.getSize());
    assertEquals(new Color(3, 3, 3), r.getColor());

    while (model.canTick()) {
      model.tick();
    }

    assertEquals(10, model.getCurrentTick());
    assertEquals(new Position2D(10, 10), r.getPosition());
    assertEquals(new Size(10, 10), r.getSize());
    assertEquals(new Color(10, 10, 10), r.getColor());

    model.reset();
    model.addKeyFrame("R", 5, 0, 0, 0, 0, 0, 0, 0, 0);

    for (int i = 0; i < 5; i++) {
      model.tick();
    }

    assertEquals(5, model.getCurrentTick());
    assertEquals(new Position2D(0, 0), r.getPosition());
    assertEquals(new Size(0, 0), r.getSize());
    assertEquals(new Color(0, 0, 0), r.getColor());

    for (int i = 0; i < 5; i++) {
      model.tick();
    }

    assertEquals(10, model.getCurrentTick());
    assertEquals(new Position2D(10, 10), r.getPosition());
    assertEquals(new Size(10, 10), r.getSize());
    assertEquals(new Color(10, 10, 10), r.getColor());

    model.reset();
    model.deleteKeyFrame("R", 10);

    while (model.canTick()) {
      model.tick();
    }

    assertEquals(5, model.getCurrentTick());
    assertEquals(false, model.canTick());
    assertEquals(new Position2D(0, 0), r.getPosition());
    assertEquals(new Size(0, 0), r.getSize());
    assertEquals(new Color(0, 0, 0), r.getColor());

    model.reset();
    model.addKeyFrame("R", 10, 10, 10, 10, 10, 10, 10, 10, 0);
    model.deleteKeyFrame("R", 5);

    for (int i = 0; i < 5; i++) {
      model.tick();
    }

    assertEquals(5, model.getCurrentTick());
    assertEquals(true, model.canTick());
    assertEquals(new Position2D(5, 5), r.getPosition());
    assertEquals(new Size(5, 5), r.getSize());
    assertEquals(new Color(5, 5, 5), r.getColor());

    while (model.canTick()) {
      model.tick();
    }

    assertEquals(10, model.getCurrentTick());
    assertEquals(new Position2D(10, 10), r.getPosition());
    assertEquals(new Size(10, 10), r.getSize());
    assertEquals(new Color(10, 10, 10), r.getColor());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteInvalidKeyFrame() {
    AnimationModel model = new Model();
    model.addShape(new Rectangle("R"));
    model.addKeyFrame("R", 10, 1, 1, 1, 1,1, 1, 1, 0);
    model.deleteKeyFrame("R", 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidKeyFrameName() {
    AnimationModel model = new Model();
    model.addShape(new Rectangle("R"));
    model.addKeyFrame("C", 10, 1, 1, 1, 1,1, 1, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteInvalidKeyFrameName() {
    AnimationModel model = new Model();
    model.addShape(new Rectangle("R"));
    model.addKeyFrame("R", 10, 1, 1, 1, 1,1, 1, 1, 0);
    model.deleteKeyFrame("C", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeKeyFrame() {
    AnimationModel model = new Model();
    model.addShape(new Rectangle("R"));
    model.addKeyFrame("C", -1, 1, 1, 1, 1,1, 1, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeDuplicateName() {
    AnimationModel model = new Model();
    model.addShape(new Rectangle("R"));
    model.addShape(new Ellipse("R"));
  }
}
