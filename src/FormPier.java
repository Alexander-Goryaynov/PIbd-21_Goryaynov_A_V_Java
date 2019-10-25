import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class FormPier {
	
	private final int panelPierWidth = 870;
	private final int panelPierHeight = 560;
	private Pier<ITransport, IDecks> pier;
	private ITransport ship;
	private IDecks deck;
	private JFrame frame;
	private JTextField textFieldIndex;
	private PierPanel panelPier;
	private JButton btnParkShip;
	private JButton btnParkDieselShip;
	private JButton btnTake;
	private TakePanel panelTake;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPier window = new FormPier();
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
	public FormPier() {
		initialize();
		initializePierPanel();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1130, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		pier = new Pier<ITransport, IDecks>(20, panelPierWidth, panelPierWidth);
		
		btnParkShip = new JButton("\u041F\u043E\u0441\u0442\u0430\u0432\u0438\u0442\u044C \u043A\u043E\u0440\u0430\u0431\u043B\u044C");
		btnParkShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(frame, "Цвет корабля", Color.blue);
				if (newColor != null) {
					ship = new Ship(100, 1000, newColor, Color.blue);
					int place = pier.plus(ship);
					panelPier.repaint();
				}
			}
		});
		btnParkShip.setBounds(901, 27, 166, 55);
		frame.getContentPane().add(btnParkShip);
		
		btnParkDieselShip = new JButton("\u041F\u043E\u0441\u0442\u0430\u0432\u0438\u0442\u044C \u0442\u0435\u043F\u043B\u043E\u0445\u043E\u0434");
		btnParkDieselShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color mainColor = JColorChooser.showDialog(frame, "Основной цвет теплохода", Color.blue);
				if (mainColor != null) {
					Color dopColor = JColorChooser.showDialog(frame, "Дополнительный цвет теплохода", Color.blue);
					if (dopColor != null) {
						ship = new DieselShip(100, 1000, Decks.One, mainColor, dopColor, Color.yellow, true, true);
						Random rnd = new Random();
						switch (rnd.nextInt(3)) {
							case 0:
								deck = new StandardDecks();
								break;
							case 1:
								deck = new RoundedDecks();
								break;
							case 2:
								deck = new TrapezeDecks();
								break;
						}
						int place = pier.plus(ship, deck);
						panelPier.repaint();
					}					
				}
			}
		});
		btnParkDieselShip.setBounds(905, 93, 162, 55);
		frame.getContentPane().add(btnParkDieselShip);
		
		JLabel label = new JLabel("\u0417\u0430\u0431\u0440\u0430\u0442\u044C \u043A\u043E\u0440\u0430\u0431\u043B\u044C:");
		label.setBounds(915, 199, 122, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u041C\u0435\u0441\u0442\u043E:");
		label_1.setBounds(912, 224, 48, 14);
		frame.getContentPane().add(label_1);
		
		textFieldIndex = new JTextField();
		textFieldIndex.setBounds(970, 221, 58, 20);
		frame.getContentPane().add(textFieldIndex);
		textFieldIndex.setColumns(10);
		
		btnTake = new JButton("\u0417\u0430\u0431\u0440\u0430\u0442\u044C");
		btnTake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldIndex.getText() != "") {
					ship = pier.minus(Integer.parseInt(textFieldIndex.getText()));
					if (ship != null) {
						panelTake.clear();
						deck = pier.minusDecks(Integer.parseInt(textFieldIndex.getText()));
						if (deck != null) {
							panelTake.drawShip(ship, deck);
						} else {
							panelTake.drawShip(ship);
						}
						panelTake.ship.setPosition(100, 100, panelPierWidth, panelPierHeight);
						panelPier.repaint();
						panelTake.repaint();
					}
				}
			}
		});
		btnTake.setBounds(909, 249, 119, 23);
		frame.getContentPane().add(btnTake);
		
		panelTake = new TakePanel();
		panelTake.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTake.setBounds(889, 288, 215, 186);
		frame.getContentPane().add(panelTake);
	}
	public void initializePierPanel(){
		panelPier = new PierPanel(pier);
		panelPier.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPier.setBounds(10, 11, panelPierWidth, panelPierHeight);
		frame.getContentPane().add(panelPier);
	}
}
