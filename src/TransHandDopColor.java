import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

@SuppressWarnings("serial")
public class TransHandDopColor extends TransferHandler {
	private ConfigPanel panel;

	public int getSourceActions(JComponent c) {
		return COPY_OR_MOVE;
	}

	// create Transferable object keeping info about textField
	public Transferable createTransferable(JComponent c) {
		JTextField textField = (JTextField) c;
		return new StringSelection(textField.getText());
	}

	// check transferred type
	public boolean canImport(TransferSupport supp) {
		return supp.isDataFlavorSupported(DataFlavor.stringFlavor);
	}

	public void setPanel(ConfigPanel panel) {
		this.panel = panel;
	}

	public boolean importData(TransferSupport supp) {
		if (!supp.isDrop())
			return false;
		DieselShip ship = (DieselShip) panel.getShip();
		String data;
		try {
			if (ship != null) {
				data = (String) supp.getTransferable().getTransferData(DataFlavor.stringFlavor);
				switch(data) {
				case "Красный":
					ship.setDopColor(Color.red);
					break;
				case "Желтый":
					ship.setDopColor(Color.yellow);
					break;
				case "Зеленый":
					ship.setDopColor(Color.green);
					break;
				case "Синий":
					ship.setDopColor(Color.blue);
					break;
				case "Белый":
					ship.setDopColor(Color.white);
					break;
				case "Черный":
					ship.setDopColor(Color.black);
					break;
				}
			}
		} catch (Exception e) {
			return false;
		}
		panel.repaint();
		return true;
	}
}
