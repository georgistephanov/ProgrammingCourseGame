package bodies.gui;

import javax.swing.*;
import java.awt.*;

public final class StatusPanel extends JPanel {

	private final JPanel infoPanel = new JPanel();
	private final JPanel buttonsPanel = new JPanel();
	private final CollectiblesJLabel levelLabel = new CollectiblesJLabel("Level");
	private final CollectiblesJLabel livesLabel = new CollectiblesJLabel("Lives");
	private final CollectiblesJLabel coinsLabel = new CollectiblesJLabel("Coins");

	private final ImageIcon [] startButtonIcons = {
			new ImageIcon("data/buttons/start.png"),
			new ImageIcon("data/buttons/start_rollover.png"),
			new ImageIcon("data/buttons/start_pressed.png")
	};

	private final ImageIcon [] pauseButtonIcons = {
		new ImageIcon("data/buttons/pause.png"),
		new ImageIcon("data/buttons/pause_rollover.png"),
		new ImageIcon("data/buttons/pause_pressed.png")
	};

	private final ImageIcon [] resetGameIcons = {
			new ImageIcon("data/buttons/reset.png"),
			new ImageIcon("data/buttons/reset_rollover.png"),
			new ImageIcon("data/buttons/reset_pressed.png")
	};

	private boolean isPauseIcon = true;
	private StatusJButton pauseButton;
	private StatusJButton resetButton;

	private static final StatusPanel instance = new StatusPanel();

	/**
	 * Returns the singleton instance of the StatusPanel.
	 *
	 * @return  the status panel
	 */
	public static StatusPanel getInstance() {
		return instance;
	}

	/**
	 * Initialises this object, sets its properties and adds the labels showing the current level, coins and lives on it.
	 */
	private StatusPanel() {
		super();

		setBackground(Color.darkGray);
		setPreferredSize(new Dimension(1920, 60));
		setLayout(new BorderLayout());

		// Add the level label to the status panel
		add(levelLabel);

		// Initialise the buttons panel
		initButtonPanel();
		// Initialise the info panel
		initInfoPanel();
	}

	/**
	 * Clears everything from the status panel and displays the winning message.
	 */
	public void displayWinningMessage() {
		infoPanel.remove(livesLabel);
		infoPanel.remove(coinsLabel);
		remove(levelLabel);

		for (int i = 0; i < 7; i++) {
			JLabel label = new CollectiblesJLabel("WINNER!");
			infoPanel.add(label);
		}

		repaint();
	}

	private void initInfoPanel() {
		infoPanel.setBackground(new Color(0, 0, 0, 0));
		infoPanel.add(livesLabel);
		infoPanel.add(coinsLabel);

		add(infoPanel, BorderLayout.EAST);
	}

	private void initButtonPanel() {
		pauseButton = new StatusJButton(pauseButtonIcons);
		resetButton = new StatusJButton(resetGameIcons);

		buttonsPanel.setBackground(new Color(0, 0, 0, 0));
		buttonsPanel.add(pauseButton);
		buttonsPanel.add(resetButton);

		add(buttonsPanel, BorderLayout.WEST);
	}

	/**
	 * Displays the pause button icons.
	 */
	public void displayPauseButton() {
		if (!isPauseIcon) {
			toggleStartPauseButtons();
		}
	}

	/**
	 * Displays the start button icons.
	 */
	public void displayStartButton() {
		if (isPauseIcon) {
			toggleStartPauseButtons();
		}
	}

	/**
	 * Toggles between the pause button icons and the start button icons.
	 */
	private void toggleStartPauseButtons() {
		if (isPauseIcon) {
			pauseButton.changeIcons(startButtonIcons);
		} else {
			pauseButton.changeIcons(pauseButtonIcons);
		}

		isPauseIcon = !isPauseIcon;
	}

	/**
	 * Gets the label which displays the current level number.
	 *
	 * @return  the label for the current level
	 */
	public CollectiblesJLabel getLevelLabel() {
		return levelLabel;
	}

	/**
	 * Gets the label which displays the amount of lives left.
	 *
	 * @return  the label for the lives
	 */
	public CollectiblesJLabel getLivesLabel() {
		return livesLabel;
	}

	/**
	 * Gets the label which displays the amount of coins collected.
	 *
	 * @return  the label for the coins
	 */
	public CollectiblesJLabel getCoinsLabel() {
		return coinsLabel;
	}
}
