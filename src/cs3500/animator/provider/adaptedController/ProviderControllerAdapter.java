package cs3500.animator.provider.adaptedController;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.shapes.Shape2D;
import cs3500.animator.provider.adaptedModel.ProviderModelAdapter;
import cs3500.animator.provider.view.EditorView;
import cs3500.animator.provider.view.ExCELenceAnimatorModel;
import cs3500.animator.provider.view.IActionableView;
import cs3500.animator.provider.view.IShape;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Our adaptation of our controller to work with the provider's view.
 */
public class ProviderControllerAdapter implements ActionListener {
	private static final String DECLARE_SHAPE_ACTION = "Declare Shape";
	private static final String PLAY_PAUSE_ACTION = "Play Pause";
	private static final String SELECT_SHAPE_ACTION = "Select Shape";
	private static final String DELETE_SHAPE_ACTION = "Delete Shape";
	private static final String SELECT_KEYTICK_ACTION = "Select Keytick";
	private static final String ADD_KEYFRAME_ACTION = "Add Keyframe";
	private static final String DELETE_KEYFRAME_ACTION = "Delete Keyframe";
	private static final String EDIT_KEYFRAME_ACTION = "Edit Keyframe";

	private AnimationController controller;
	private ExCELenceAnimatorModel model;
	private EditorView view;

	/**
	 * Contructs an adapted controller.
	 * @param controller our controller
	 */
	public ProviderControllerAdapter(AnimationController controller) {
		this.controller = controller;
	}

	/**
	 * Contructs an adapted controller.
	 * @param model our model
	 * @param view our view
	 */
	public ProviderControllerAdapter(ExCELenceAnimatorModel model, EditorView view) {
		this.model = model;
		this.view = view;
		view.setListener(this);

		refreshKeyFrameList();
		refreshShapeList();
	}

	/**
	 * Listener for actions.
	 * @param e the event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case DECLARE_SHAPE_ACTION:
				String nName = view.pullNewShapeName();
				Shape2D.ShapeType sT = view.pullNewShapeType();

				model.declareShape(nName, sT);
				refreshShapeList();
				break;
			case SELECT_SHAPE_ACTION:
				refreshKeyFrameList();
				break;
			case DELETE_SHAPE_ACTION:
				String dName = view.getSelectedShapeName();

				model.removeShape(dName);
				refreshShapeList();
				break;
			case ADD_KEYFRAME_ACTION:
				String aName = view.getSelectedShapeName();
				int kAdd = view.getNewKeyframeTick();

				model.addKeyframe(aName, kAdd);
				refreshKeyFrameList();
				break;
			case DELETE_KEYFRAME_ACTION:
				String kName = view.getSelectedShapeName();
				int kDel = view.getSelectedKeytick();

				model.removeKeyframe(kName, kDel);
				refreshKeyFrameList();
				break;
			case EDIT_KEYFRAME_ACTION:
				String iName = view.getSelectedShapeName();
				int iK = view.getSelectedKeytick();
				IShape iS = view.getShapeValue();

				model.addKeyframe(iName, iK, iS);
				break;
		}
	}

	/**
	 * Shows the view.
	 */
	public void go() {
		view.showView();
	}

	/**
	 * Refreshes shapes list.
	 */
	private void refreshShapeList() {
		view.setShapeNames(model.getShapes());
	}

	/**
	 * Refreshes key frames list.
	 */
	private void refreshKeyFrameList() {
		view.setKeyTicks(model.getKeyticksForShape(view.getSelectedShapeName()));
	}
}
