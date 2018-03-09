package gui;

import city.cs.engine.World;
import levels.LevelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class StatusPanel extends JPanel {

	private final JPanel infoPanel = new JPanel();
	private final JPanel buttonsPanel = new JPanel();
	private final AmountJLabel levelLabel = new AmountJLabel("Level");
	private final AmountJLabel livesLabel = new AmountJLabel("Lives");
	private final AmountJLabel coinsLabel = new AmountJLabel("Coins");

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

	private static StatusPanel instance;

	/**
	 * Returns the singleton instance of the StatusPanel.
	 *
	 * @return  the status panel
	 */
	public static StatusPanel getInstance(World world) {
		if (instance == null) {
			instance = new StatusPanel(world);
		}

		return instance;
	}

	/**
	 * Initialises this object, sets its properties and adds the labels showing the current level, coins and lives on it.
	 */
	private StatusPanel(World world) {
		super();

		setBackground(Color.darkGray);
		setPreferredSize(new Dimension(1920, 60));
		setLayout(new BorderLayout());

		// Add the level label to the status panel
		add(levelLabel);

		// Initialise the buttons panel
		initButtonPanel(world);
		// Initialise the info panel
		initInfoPanel();
	}

	/**
	 * Clears everything from the status panel and displays the winning message.
	 */
	public void displayWinningMessage() {
		remove(levelLabel);
		remove(buttonsPanel);
		infoPanel.remove(coinsLabel);
		infoPanel.remove(livesLabel);

		for (int i = 0; i < 7; i++) {
			JLabel label = new AmountJLabel("WINNER!");
			infoPanel.add(label);
		}

		revalidate();
		repaint();
		System.out.println("You win!");
	}

	private void initInfoPanel() {
		infoPanel.setBackground(new Color(0, 0, 0, 0));
		infoPanel.add(livesLabel);
		infoPanel.add(coinsLabel);

		add(infoPanel, BorderLayout.EAST);
	}

	private void initButtonPanel(World world) {
		pauseButton = new StatusJButton(pauseButtonIcons);
		pauseButton.addActionListener((ActionEvent e) -> {
			if (isPauseIcon) {
				world.stop();
			} else {
				world.start();
			}

			toggleStartPauseButtons();
		});

		resetButton = new StatusJButton(resetGameIcons);
		resetButton.addActionListener((ActionEvent e) -> LevelManager.getInstance().restartGame());

		buttonsPanel.setBackground(new Color(0, 0, 0, 0));
		buttonsPanel.add(pauseButton);
		buttonsPanel.add(resetButton);

		add(buttonsPanel, BorderLayout.WEST);
	}

	/**
	 * Triggers the on click action of the pause button.
	 */
	public void clickPauseButton() {
		pauseButton.doClick();
	}

	/**
	 * Triggers the on click action of the restart button.
	 */
	public void clickRestartButton() {
		resetButton.doClick();
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
	 * Checks if any of the status panel buttons has focus.
	 *
	 * @return  true if any of the status panel buttons has focus
	 */
	public boolean buttonHasFocus() {
		return pauseButton.hasFocus() || resetButton.hasFocus();
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
	public AmountJLabel getLevelLabel() {
		return levelLabel;
	}

	/**
	 * Gets the label which displays the amount of lives left.
	 *
	 * @return  the label for the lives
	 */
	public AmountJLabel getLivesLabel() {
		return livesLabel;
	}

	/**
	 * Gets the label which displays the amount of coins collected.
	 *
	 * @return  the label for the coins
	 */
	public AmountJLabel getCoinsLabel() {
		return coinsLabel;
	}
}
