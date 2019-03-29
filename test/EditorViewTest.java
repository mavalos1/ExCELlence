
import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.PopUpOptionPanel;
import org.junit.Test;

import javax.swing.JButton;
import java.awt.event.ActionListener;

import static org.junit.Assert.assertEquals;

public class EditorViewTest {
  @Test
  public void testPlaybackControlListener() {
    AnimationModel model = new Model();
    AnimationView view = new EditorView();
    AnimationController controller = new Controller(model, view, 1);

    Rectangle r = new Rectangle("R");
    model.addShape(r);
    controller.addKeyFrame("R", 1, 1, 1, 1, 1, 1, 1, 1);
    controller.addKeyFrame("R", 10, 10, 10, 10, 10, 10, 10, 10);

    JButton testButton = new JButton();
    testButton.addActionListener((ActionListener) controller);
    testButton.setActionCommand("loop");

    assertEquals(true, controller.getLoop());
    testButton.doClick();
    assertEquals(false, controller.getLoop());

    testButton.setActionCommand("restart");

    assertEquals(true, controller.getPlaying());
    testButton.doClick();
    assertEquals(false, controller.getPlaying());

    testButton.setActionCommand("start/pause");

    assertEquals(false, controller.getPlaying());
    testButton.doClick();
    assertEquals(true, controller.getPlaying());

    testButton.setActionCommand("speed");

    assertEquals(1, controller.getSpeed());
    view.setSpeed(100);
    testButton.doClick();
    assertEquals(100, controller.getSpeed());

    PopUpOptionPanel pR = new PopUpOptionPanel("Add Rectangle");
    pR.addActionListener((ActionListener) controller);
    pR.name = "R1";

    assertEquals(1, model.getShapes().size());
    pR.doClick();
    assertEquals(2, model.getShapes().size());
    assertEquals("R1", model.getShape("R1").getName());
    assertEquals(true, model.getShape("R1") instanceof Rectangle);

    PopUpOptionPanel pRs = new PopUpOptionPanel("Remove Shape");
    pRs.addActionListener((ActionListener) controller);
    pRs.name = "R1";

    assertEquals(2, model.getShapes().size());
    pRs.doClick();
    assertEquals(1, model.getShapes().size());

    PopUpOptionPanel pC = new PopUpOptionPanel("Add Ellipse");
    pC.addActionListener((ActionListener) controller);
    pC.name = "C1";

    assertEquals(1, model.getShapes().size());
    pC.doClick();
    assertEquals(2, model.getShapes().size());
    assertEquals("C1", model.getShape("C1").getName());
    assertEquals(true, model.getShape("C1") instanceof Ellipse);
  }
}
