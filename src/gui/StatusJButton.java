package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Represents buttons in the status panel.
 */
class StatusJButton extends JButton {

	/**
	 * Creates this object and sets its UI properties, as well as its corresponding icons for the default, rollover and
	 * pressed states.
	 *
	 * @param icons  an array of three icons with indexes:
	 *               <ul>
	 *               	<li><b>0</b> - default state icon</li>
	 *               	<li><b>1</b> - rollover state icon</li>
	 *               	<li><b>2</b> - pressed state icon</li>
	 *               </ul>
	 */
	StatusJButton(ImageIcon [] icons) {
		super(icons[0]);

		setRolloverIcon(icons[1]);
		setPressedIcon(icons[2]);
		setBorder(new EmptyBorder(0, 20, 0 , 10));
		setBackground(Color.darkGray);
		setContentAreaFilled(false);
		setOpaque(true);
	}

	/**
	 * Changes the icon of the button.
	 *
	 * @param newIcons  an array of three icons with indexes:
	 *                  <ul>
	 *               		<li><b>0</b> - default state icon</li>
	 *               		<li><b>1</b> - rollover state icon</li>
	 *               		<li><b>2</b> - pressed state icon</li>
	 *               	</ul>
	 */
	void changeIcons(ImageIcon [] newIcons) {
		setIcon(newIcons[0]);
		setRolloverIcon(newIcons[1]);
		setPressedIcon(newIcons[2]);
	}
}
