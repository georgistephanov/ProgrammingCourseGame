package bodies.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class StatusJButton extends JButton {

	StatusJButton(ImageIcon [] icons) {
		super(icons[0]);

		setRolloverIcon(icons[1]);
		setPressedIcon(icons[2]);
		setBorder(new EmptyBorder(0, 20, 0 , 10));
		setBackground(Color.darkGray);
		setContentAreaFilled(false);
		setOpaque(true);
	}

	void changeIcons(ImageIcon [] newIcons) {
		setIcon(newIcons[0]);
		setRolloverIcon(newIcons[1]);
		setPressedIcon(newIcons[2]);
	}
}
