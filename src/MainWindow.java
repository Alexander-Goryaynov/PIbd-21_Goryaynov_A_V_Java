import java.awt.EventQueue;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainWindow {

	private JFrame frame;
	private MyPanel panel;
	private Ship ship;
	private JButton btnUp;
	private JButton btnRight;
	private JButton btnLeft;
	private JButton btnDown;
	private JButton btnCreate;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u0422\u0435\u043F\u043B\u043E\u0445\u043E\u0434");
		frame.setBounds(200, 50, 950, 667);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnCreate = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().equals("")) {
					createNewShip();
					btnUp.setEnabled(true);
					btnRight.setEnabled(true);
					btnLeft.setEnabled(true);
					btnDown.setEnabled(true);
				}				
			}
		});
		btnCreate.setBounds(10, 521, 119, 43);
		frame.getContentPane().add(btnCreate);
		
		btnUp = new JButton("");
		btnUp.setEnabled(false);
		btnUp.setIcon(new ImageIcon("C:\\Eclipse\\workspace\\WindowsFormsShip\\icons\\cursorUp.png"));
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ship.moveShip(Direction.Up);
				panel.repaint();
			}
		});
		btnUp.setBounds(257, 514, 50, 50);
		frame.getContentPane().add(btnUp);
		
		btnRight = new JButton("");
		btnRight.setEnabled(false);
		btnRight.setIcon(new ImageIcon("C:\\Eclipse\\workspace\\WindowsFormsShip\\icons\\cursorRight.png"));
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ship.moveShip(Direction.Right);
				panel.repaint();
			}
		});
		btnRight.setBounds(317, 541, 50, 50);
		frame.getContentPane().add(btnRight);
		
		btnLeft = new JButton("");
		btnLeft.setEnabled(false);
		btnLeft.setIcon(new ImageIcon("C:\\Eclipse\\workspace\\WindowsFormsShip\\icons\\cursorLeft.png"));
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ship.moveShip(Direction.Left);
				panel.repaint();
			}
		});
		btnLeft.setBounds(197, 541, 50, 50);
		frame.getContentPane().add(btnLeft);
		
		btnDown = new JButton("");
		btnDown.setEnabled(false);
		btnDown.setIcon(new ImageIcon("C:\\Eclipse\\workspace\\WindowsFormsShip\\icons\\cursorDown.png"));
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ship.moveShip(Direction.Down);
				panel.repaint();
			}
		});
		btnDown.setBounds(257, 567, 50, 50);
		frame.getContentPane().add(btnDown);
		
		JLabel label = new JLabel("\u041F\u0430\u043B\u0443\u0431:");
		label.setBounds(20, 575, 48, 14);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(65, 571, 96, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
	private void createNewShip() {
		Decks numDeck;
		int numDecks = Integer.parseInt(textField.getText());
		switch (numDecks) {
		case 1:
			numDeck = Decks.One;
			break;
		case 2:
			numDeck = Decks.Two;
			break;
		case 3:
			numDeck = Decks.Three;
			break;
		default:
			numDeck = Decks.Three;
			break;
		}
		Random rnd = new Random();
		ship = new Ship(rnd.nextInt(30)+20, rnd.nextInt(60)+140, 
                Color.gray, Color.blue, numDeck, true, true);				
		panel = new MyPanel(ship);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 10, 900, 500);
		frame.getContentPane().add(panel);
		ship.setPosition(rnd.nextInt(50)+50, rnd.nextInt(50)+50, 
                panel.getWidth(), panel.getHeight());				
		panel.repaint();
	}
}
