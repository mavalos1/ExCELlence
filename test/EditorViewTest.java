
import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.EditorView;
import org.junit.Test;

import java.awt.event.ActionListener;

import static org.junit.Assert.assertEquals;

public class EditorViewTest {
  @Test
  public void testSetListener() {
    AnimationModel model = new Model();
    AnimationView view = new EditorView();
    AnimationController controller = new Controller(model, view, 1);

    view.setListener((ActionListener) controller);
  }
}
