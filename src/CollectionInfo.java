import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class CollectionInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CollectionInfo dialog = new CollectionInfo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CollectionInfo() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textArea = new JTextArea();
			textArea.setBounds(10, 11, 414, 229);
			contentPanel.add(textArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	
	public void showCollection(Hashtable<Integer, ITransport> h1, Hashtable<Integer, IDecks> h2) {
		String result = "";
		for (int i = 0; i < h1.size(); i++) {
			result = result + h1.get(i) + "\n" + h2.get(i) + "\n";
		}
		textArea.setText(result);
	}

}
