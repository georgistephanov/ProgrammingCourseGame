package game;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public final class StatusPanel extends JPanel {
	private static final StatusPanel instance = new StatusPanel();

	private Font arcadeFont = new JLabel().getFont().deriveFont(56f);

	private final JPanel infoPanel = new JPanel();
	private final CollectiblesJLabel levelLabel = new CollectiblesJLabel("Level");
	private final CollectiblesJLabel livesLabel = new CollectiblesJLabel("Lives");
	private final CollectiblesJLabel coinsLabel = new CollectiblesJLabel("Coins");

	/**
	 * Returns the singleton instance of the StatusPanel.
	 *
	 * @return  the status panel
	 */
	public static StatusPanel getInstance() {
		return instance;
	}

	private StatusPanel() {
		super();

		setBackground(Color.darkGray);
		setPreferredSize(new Dimension(1920, 60));
		setLayout(new BorderLayout());

		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("data/fonts/arcade.ttf"));
			arcadeFont = font.deriveFont(56f);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initLabel(levelLabel);
		add(levelLabel, BorderLayout.WEST);

		initLabel(livesLabel);
		initLabel(coinsLabel);
		initInfoPanel();
	}

	private void initLabel(JLabel label) {
		label.setBorder(BorderFactory.createMatteBorder(0, 20, 0, 20, Color.darkGray));
		label.setFont(arcadeFont);
		label.setMinimumSize(new Dimension(200, 200));
		label.setForeground(Color.WHITE);
	}

	private void initInfoPanel() {
		infoPanel.setBackground(new Color(0, 0, 0, 0));
		infoPanel.add(livesLabel);
		infoPanel.add(coinsLabel);

		add(infoPanel, BorderLayout.EAST);
	}

	public CollectiblesJLabel getLevelLabel() {
		return levelLabel;
	}

	public CollectiblesJLabel getLivesLabel() {
		return livesLabel;
	}

	public CollectiblesJLabel getCoinsLabel() {
		return coinsLabel;
	}

	/**
	 * Represents the labels in the status bar which indicate the amount collected of a certain collectible.
	 */
	public static class CollectiblesJLabel extends JLabel implements PropertyChangeListener {
		public static final String PROPERTY_NAME = "amount";
		private int amount = -1;

		CollectiblesJLabel(String string) {
			super(string);
			addPropertyChangeListener(PROPERTY_NAME, this);
			setOpaque(true);
			setBackground(Color.darkGray);
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getNewValue() instanceof Integer) {
				setAmount((int) evt.getNewValue());
			} else {
				throw new RuntimeException("Passed value should be an integer");
			}
		}

		/**
		 * Returns the amount collected which is displayed next to the text of the label
		 *
		 * @return  the amount collected so far
		 */
		public int getAmount() {
			return amount;
		}

		/**
		 * Sets the amount collected and updates the text of the label to display it
		 *
		 * @param amount  the amount collected so far
		 */
		private void setAmount(int amount) {
			this.amount = amount;
			setText(getText().split(" ")[0] + "   " + amount);
		}
	}
}
