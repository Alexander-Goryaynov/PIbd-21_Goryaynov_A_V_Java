import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Test {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setText("\u0422\u0435\u043F\u043B\u043E\u0445\u043E\u0434");
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFocusable(false);
		textField.setEditable(false);
		textField.setDragEnabled(true);
		textField.setBackground(Color.GRAY);
		textField.setBounds(99, 97, 96, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		System.out.println(textField.getText());
	}

}
