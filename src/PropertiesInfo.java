import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class PropertiesInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;

	public static void main(String[] args) {
		try {
			PropertiesInfo dialog = new PropertiesInfo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PropertiesInfo() {
		setTitle("\u0421\u0432\u043E\u0439\u0441\u0442\u0432\u0430");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		textArea = new JTextArea();
		textArea.setBounds(10, 11, 414, 229);
		contentPanel.add(textArea);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}
	
	public void showProperties(String input) {
		textArea.setText(input);
	}
}
