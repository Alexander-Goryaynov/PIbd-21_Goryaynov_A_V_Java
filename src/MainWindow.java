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
import java.awt.Font;

public class MainWindow {

	private JFrame frame;
	private MyPanel panel;
	private ITransport ship;
	private JButton btnUp;
	private JButton btnRight;
	private JButton btnLeft;
	private JButton btnDown;
	private JButton btnCreateDieselShip;
	private JTextField textField;
	private JButton btnCreateShip;

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
		
		btnCreateDieselShip = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u0442\u0435\u043F\u043B\u043E\u0445\u043E\u0434");
		btnCreateDieselShip.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCreateDieselShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().equals("")) {
					createNewDieselShip();
					btnUp.setEnabled(true);
					btnRight.setEnabled(true);
					btnLeft.setEnabled(true);
					btnDown.setEnabled(true);
				}				
			}
		});
		btnCreateDieselShip.setBounds(10, 521, 123, 43);
		frame.getContentPane().add(btnCreateDieselShip);
		
		btnUp = new JButton("");
		btnUp.setEnabled(false);
		btnUp.setIcon(new ImageIcon("C:\\Eclipse\\workspace\\WindowsFormsShip\\icons\\cursorUp.png"));
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ship.moveShip(Direction.Up);
				panel.repaint();
			}
		});
		btnUp.setBounds(330, 514, 50, 50);
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
		btnRight.setBounds(390, 541, 50, 50);
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
		btnLeft.setBounds(270, 541, 50, 50);
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
		btnDown.setBounds(330, 567, 50, 50);
		frame.getContentPane().add(btnDown);
		
		JLabel label = new JLabel("\u041F\u0430\u043B\u0443\u0431:");
		label.setBounds(20, 575, 48, 14);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(65, 571, 96, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnCreateShip = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u043A\u043E\u0440\u0430\u0431\u043B\u044C");
		btnCreateShip.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCreateShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewShip();
				btnUp.setEnabled(true);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				btnDown.setEnabled(true);
			}
		});
		btnCreateShip.setBounds(143, 521, 117, 43);
		frame.getContentPane().add(btnCreateShip);
	}
	private void createNewShip() {
		Random rnd = new Random();
		ship = new Ship(rnd.nextInt(30)+20, rnd.nextInt(60)+140, 
                Color.gray, Color.blue);				
		panel = new MyPanel(ship);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 10, 900, 500);
		frame.getContentPane().add(panel);
		ship.setPosition(rnd.nextInt(50)+50, rnd.nextInt(50)+50, 
                panel.getWidth(), panel.getHeight());				
		panel.repaint();
	}
	private void createNewDieselShip() {
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
		ship = new DieselShip(rnd.nextInt(30)+20, rnd.nextInt(60)+140, numDeck, 
				Color.gray, Color.blue, Color.yellow, true, true);				
		panel = new MyPanel(ship);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 10, 900, 500);
		frame.getContentPane().add(panel);
		ship.setPosition(rnd.nextInt(50)+50, rnd.nextInt(50)+50, 
                panel.getWidth(), panel.getHeight());				
		panel.repaint();
	}
}
