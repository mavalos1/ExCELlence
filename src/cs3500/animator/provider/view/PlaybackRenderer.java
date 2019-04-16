package cs3500.animator.provider.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.Timer;

//This import was changed to accomodate for ReadOnlyExCELenceAnimatorModel location in provider.view
import cs3500.animator.provider.view.ReadOnlyExCELenceAnimatorModel;
import cs3500.animator.model.shapes.Shape2D;

/**
 * This class represents a visual view of an animation where users can control the playing ability
 * of an animation.
 */
public class PlaybackRenderer extends JPanel implements ActionListener, IPlaybackView {
	private int tickCounter = 0;
	private ReadOnlyExCELenceAnimatorModel model;
	private boolean loop;
	private Timer timer;

	/**
	 * Convenience constructor defaults to a tickrate of 1 tick per second.
	 *
	 * @param model the model of the animation
	 */
	public PlaybackRenderer(ReadOnlyExCELenceAnimatorModel model) {
		this(model, 1);
	}

	/**
	 * Constructs a playback view with the given animation model and speed.
	 *
	 * @param model the model of the animation
	 * @param delay the speed for the animation
	 */
	public PlaybackRenderer(ReadOnlyExCELenceAnimatorModel model, int delay) {
		super();
		this.model = model;
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(model.getCanvasWidth(), model.getCanvasHeight()));
		this.loop = false;
		timer = new Timer(delay, this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		for (IShape s : this.model.getShapesAtTick(this.tickCounter)) {
			if (s != null) {
				int x = s.getPosition().getXCoord().intValue() - this.model.getCanvasOffsetX();
				int y = s.getPosition().getYCoord().intValue() - this.model.getCanvasOffsetY();
				int w = s.getDimension().getWidth().intValue();
				int h = s.getDimension().getHeight().intValue();
				Color c = new Color(
					s.getColor().getRedValue(),
					s.getColor().getGreenValue(),
					s.getColor().getBlueValue());
				g2.setColor(c);
				if (s.getType().equals(Shape2D.ShapeType.RECTANGLE)) {
					g2.fillRect(x, y, w, h);
				} else {
					g2.fillOval(x, y, w, h);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(loop && isEndOfAnimation()) {
			restartAnimation();
		}
		repaint();
		this.tickCounter += 1;
	}

	@Override
	public void pauseAnimation() {
		this.timer.stop();
	}

	@Override
	public void playAnimation() {
		this.timer.start();
	}

	@Override
	public void restartAnimation() {
		this.tickCounter = 0;
		repaint();
	}

	@Override
	public void startAnimationFromTick(Integer tick) {
		this.tickCounter = tick;
		this.playAnimation();
	}

	@Override
	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	@Override
	public void showView() {
		//Since this is just a JPanel with some functionality within a visual view, it never needs to
		// call showView
	}

	@Override
	public void setModel(ReadOnlyExCELenceAnimatorModel model) {
		this.model = model;
	}

	@Override
	public void setSpeed(Integer ticksPerSecond) {
		if (ticksPerSecond < 1) {
			throw new IllegalArgumentException("ticks per second cannot be less than 1");
		} else {
			timer.setDelay(1000 / ticksPerSecond);
		}
	}

	private boolean isEndOfAnimation() {
		int end = Collections.max(model.getKeyticksForShape(model.getShapes().get(1)));
		return this.tickCounter > end;
	}

}