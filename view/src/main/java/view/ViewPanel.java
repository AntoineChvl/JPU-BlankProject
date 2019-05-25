package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.entity.mobileelements.Player;

import entity.Entity;
import entity.Map;

/**
 * The Class ViewPanel.
 *
 * @author Jean-Aymeric Diet
 */
@SuppressWarnings("unused")
class ViewPanel extends JPanel implements Observer {

	/** The view frame. */
	private ViewFrame viewFrame;
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -998294702363713521L;

	public ViewPanel() {
	}

	/**
	 * Instantiates a new view panel.
	 *
	 * @param viewFrame the view frame
	 */
	public ViewPanel(final ViewFrame viewFrame) {
		this.setViewFrame(viewFrame);
		viewFrame.getModel().getObservable().addObserver(this);
		// viewFrame.getModel().getMap();
	}

	/**
	 * Gets the view frame.
	 *
	 * @return the view frame
	 */
	@SuppressWarnings("unused")
	private ViewFrame getViewFrame() {
		return this.viewFrame;
	}

	/**
	 * Sets the view frame.
	 *
	 * @param viewFrame the new view frame
	 */
	private void setViewFrame(final ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(final Observable arg0, final Object arg1) {
		this.repaint();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */

	static int counter = 200; // Counter until the end of the game

	@Override
	protected void paintComponent(final Graphics graphics) {

		final double scale = 3.0;
		final int imageSize = 16;
		final int width = this.getWidth();
		final int height = this.getHeight();
		Map map = this.viewFrame.getModel().getMap();
		Entity[][] loadMap = null;
		Player player = null;

		if (map != null) {

			loadMap = map.getArrayMap();
			player = this.viewFrame.getModel().getMap().getPlayer();
			int playerPosX = this.viewFrame.getModel().getMap().getPlayer().getPositionX();
			int playerPosY = this.viewFrame.getModel().getMap().getPlayer().getPositionY();
			Font font = new Font("Arial", Font.BOLD, 20);
			graphics.setFont(font);

			if (counter != 0 && counter != -100) {

				graphics.clearRect(0, 0, width, height);

				graphics.translate((int) (-playerPosX * imageSize * scale + width / 2),
						(int) (-playerPosY * imageSize * scale + height / 2));

				((Graphics2D) graphics).scale(scale, scale);

				for (int x = 0; x < map.getWidthMap(); x++) {

					for (int y = 0; y < map.getHeightMap(); y++) {
						graphics.drawImage(loadMap[x][y].getSprite().getImage(), x * imageSize, y * imageSize, this);
					}
					if (player.getIsWin() && player.getNumberOfWin() == 1) {

						player.incrementNumberOfWin();
						JOptionPane deathMessage = new JOptionPane();
						JOptionPane.showMessageDialog(null, "You just won !", "Congrats !",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			} else {
				// If the remaining time is equal to 0
				graphics.clearRect(0, 0, width, height);
				graphics.drawString("BAD TIMING !", width / 2 - 75, height / 2);
				counter = -100;
				/*
				 * NOW WE HAVE TO PERFORM AN ACTION WHEN THE PLAYER LOOSES (+ RESTART THE TIMER
				 * = set it two 200 when the player switch between the maps)
				 */
			}

			((Graphics2D) graphics).scale(1 / scale, 1 / scale);

			graphics.translate((int) (+playerPosX * 16 * scale - width / 2),
					(int) (+playerPosY * 16 * scale - height / 2));
			graphics.setColor(Color.white);
			graphics.fillRect(width - 210, 0, 220, 45);
			graphics.setColor(Color.BLUE);
			graphics.drawString("Remaining time : " + counter, width - 200, 20);
			graphics.drawString(String.valueOf("Diamond Counter : " + player.getDiamondsCounter()), width - 200, 40);

		}
	}

	public void startTimer() { // This is a timer
		ViewPanel drawTimer = new ViewPanel();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				// If the counter is equal to -100 it means that the game has stopped
				if (counter != -100) {
					counter--;// increments the counter
				}
			}
		};
		Timer timer = new Timer("MyTimer"); // create a new timer
		timer.scheduleAtFixedRate(timerTask, 1000, 1000); // each seconds we perform the run method
	}
}
