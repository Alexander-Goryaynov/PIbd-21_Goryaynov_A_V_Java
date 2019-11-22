import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

@SuppressWarnings("serial")
public class TransHandConfigPanel extends TransferHandler {
	private ITransport ship;
	public Transferable createTransferable(JComponent c) {
		JTextField textField = (JTextField)c;
	    return new StringSelection(textField.getText());
	}
	public int getSourceActions(JComponent c) {
	    return COPY_OR_MOVE;
	}
	public boolean canImport(TransferSupport supp) {
		return supp.isDataFlavorSupported(DataFlavor.stringFlavor);
	}
	public ITransport getShip() {
		return ship;
	}
	public boolean importData(TransferSupport supp) {
		if(!supp.isDrop()) {
			return false;
		}	
		/* import destination */
		ConfigPanel panelShip = (ConfigPanel)supp.getComponent();
		String data;
		try {
			data = (String)supp.getTransferable().getTransferData(
					DataFlavor.stringFlavor);
			switch(data) {
				case "Прямоугольные":
					if(ship instanceof DieselShip) {
						panelShip.clearDecks();
						panelShip.setDecks(new StandardDecks());
					}
					break;
				case "Закругленные":
					if(ship instanceof DieselShip) {
						panelShip.clearDecks();
						panelShip.setDecks(new RoundedDecks());
					}
					break;
				case "Трапеция":
					if(ship instanceof DieselShip) {
						panelShip.clearDecks();
						panelShip.setDecks(new TrapezeDecks());
					}
					break;
				case "Корабль":
					panelShip.clear();
					ship = new Ship(50, 200, Color.gray, Color.blue);
					ship.setPosition(panelShip.getX() - 50, panelShip.getY() + 20,
							panelShip.getWidth(), panelShip.getHeight());
					panelShip.setShip(ship);
					break;
				case "Теплоход":
					panelShip.clear();
					ship = new DieselShip(50, 200, Decks.Three, Color.gray, 
							Color.blue, Color.yellow, true, true);
					ship.setPosition(panelShip.getX() - 50, panelShip.getY() + 20, 
							panelShip.getWidth(), panelShip.getHeight());
					panelShip.setShip(ship);
					break;
			}
		}catch(Exception e) {
			return false;
		}
		panelShip.repaint();
		panelShip.repaint();
		return true;
	}
}
