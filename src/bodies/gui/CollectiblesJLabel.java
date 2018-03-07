package bodies.gui;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

/**
 * Represents the labels in the status bar which indicate the amount collected of a certain collectible.
 */
public class CollectiblesJLabel extends JLabel implements PropertyChangeListener {
	public static final String PROPERTY_NAME = "amount";
	private int amount = -1;

	private static Font arcadeFont;

	static {
		arcadeFont = new JLabel().getFont().deriveFont(56f);

		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("data/fonts/arcade.ttf"));
			arcadeFont = font.deriveFont(56f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	CollectiblesJLabel(String string) {
		super(string);

		setBorder(BorderFactory.createMatteBorder(0, 20, 0, 20, Color.darkGray));
		setOpaque(true);
		setBackground(Color.darkGray);
		setForeground(Color.WHITE);
		setFont(arcadeFont);

		addPropertyChangeListener(PROPERTY_NAME, this);
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