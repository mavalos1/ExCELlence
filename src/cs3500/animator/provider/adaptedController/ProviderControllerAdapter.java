package cs3500.animator.provider.adaptedController;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.shapes.Shape2D;
import cs3500.animator.provider.adaptedModel.ProviderModelAdapter;
import cs3500.animator.provider.view.EditorView;
import cs3500.animator.provider.view.ExCELenceAnimatorModel;
import cs3500.animator.provider.view.IShape;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Our adaptation of our controller to work with the provider's view.
 */
public class ProviderControllerAdapter extends Controller implements AnimationController, ActionListener {
	private static final String DECLARE_SHAPE_ACTION = "Declare Shape";
	private static final String PLAY_PAUSE_ACTION = "Play Pause";
	private static final String SELECT_SHAPE_ACTION = "Select Shape";
	private static final String DELETE_SHAPE_ACTION = "Delete Shape";
	private static final String SELECT_KEYTICK_ACTION = "Select Keytick";
	private static final String ADD_KEYFRAME_ACTION = "Add Keyframe";
	private static final String DELETE_KEYFRAME_ACTION = "Delete Keyframe";
	private static final String EDIT_KEYFRAME_ACTION = "Edit Keyframe";

	private int speed = 1;
	private ExCELenceAnimatorModel adaptedModel;
	private EditorView view;

	/**
	 * Contructs an adapted controller.
	 */
	public ProviderControllerAdapter(String type, int speed, String outFile) {
		super(type, speed, outFile);
		adaptedModel = new ProviderModelAdapter(this.model);
		view = new EditorView(adaptedModel);
		this.speed = speed;

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

				adaptedModel.declareShape(nName, sT);
				refreshShapeList();
				break;
			case SELECT_SHAPE_ACTION:
				refreshKeyFrameList();
				break;
			case DELETE_SHAPE_ACTION:
				String dName = view.getSelectedShapeName();

				adaptedModel.removeShape(dName);
				refreshShapeList();
				refreshKeyFrameList();
				break;
			case SELECT_KEYTICK_ACTION:
				IShape nS = adaptedModel.getShapeAtTick(
					view.getSelectedShapeName(), view.getSelectedKeytick()
				);

				view.setShapeEditorValue(nS);
				break;
			case ADD_KEYFRAME_ACTION:
				String aName = view.getSelectedShapeName();
				int kAdd = view.getNewKeyframeTick();

				adaptedModel.addKeyframe(aName, kAdd);
				refreshKeyFrameList();
				break;
			case DELETE_KEYFRAME_ACTION:
				String kName = view.getSelectedShapeName();
				int kDel = view.getSelectedKeytick();

				adaptedModel.removeKeyframe(kName, kDel);
				refreshKeyFrameList();
				break;
			case EDIT_KEYFRAME_ACTION:
				String iName = view.getSelectedShapeName();
				int iK = view.getSelectedKeytick();
				IShape iS = view.getShapeValue();

				adaptedModel.addKeyframe(iName, iK, iS);
				break;
		}
	}

	/**
	 * Shows the view.
	 */
	public void start() {
		adaptedModel = new ProviderModelAdapter(this.model);
		view = new EditorView(adaptedModel);
		view.setListener(this);

		view.setSpeed(speed);

		view.showView();
	}

	/**
	 * Refreshes shapes list.
	 */
	private void refreshShapeList() {
		view.setShapeNames(adaptedModel.getShapes());
	}

	/**
	 * Refreshes key frames list.
	 */
	private void refreshKeyFrameList() {
		view.setKeyTicks(adaptedModel.getKeyticksForShape(view.getSelectedShapeName()));
	}

	@Override
	public void setBounds(int x, int y, int w, int h) {
		adaptedModel.setCanvasOffsetX(x);
		adaptedModel.setCanvasOffsetY(y);
		adaptedModel.setCanvasWidth(w);
		adaptedModel.setCanvasHeight(h);
	}

	@Override
	public void setSpeed(int speed) {
		view.setSpeed(speed);
	}
}
