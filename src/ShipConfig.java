import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class ShipConfig {

	public JFrame frame;
	private JTextField textFieldShip;
	private JTextField textFieldDieselShip;
	private JTextField textFieldRed;
	private JTextField textFieldYellow;
	private JTextField textFieldGreen;
	private JTextField textFieldBlue;
	private JTextField textFieldWhite;
	private JTextField textFieldBlack;
	private JTextField textFieldRectangle;
	private JTextField textFieldRounded;
	private JTextField textFieldTrapeze;
	private ConfigPanel panelShip;
	private PierPanel currentPierPanel;
	private MultiLevelPier currentPier;
	private JList<String> currentList;
	private JButton btnAdd;
	private Logger logger_error;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShipConfig window = new ShipConfig();
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
	public ShipConfig() {
		initialize();
	}
	
	public void initializeConfig(PierPanel currentPierPanel, MultiLevelPier currentPier,
			JList<String> currentList) {
		this.currentPierPanel = currentPierPanel;
		this.currentPier = currentPier;
		this.currentList = currentList;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		logger_error = Logger.getLogger(FormPier.class.getName() + "2");
		try {			
			FileHandler fh_error = null;			
			fh_error = new FileHandler("C:\\temp\\file_error.txt");
			logger_error.addHandler(fh_error);
			logger_error.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fh_error.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setTitle("\u041A\u043E\u043D\u0444\u0438\u0433\u0443\u0440\u0430\u0442\u043E\u0440 \u043A\u043E\u0440\u0430\u0431\u043B\u044F");
		frame.setBounds(100, 100, 594, 318);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelShip = new ConfigPanel();
		panelShip.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelShip.setBounds(138, 21, 188, 108);
		TransHandConfigPanel transHandConfig = new TransHandConfigPanel();
		panelShip.setTransferHandler(transHandConfig);
		frame.getContentPane().add(panelShip);
		
		JLabel label = new JLabel("\u0422\u0438\u043F");
		label.setBounds(58, 21, 50, 14);
		frame.getContentPane().add(label);
		
		textFieldShip = new JTextField();
		textFieldShip.setSelectionEnd(7);
		textFieldShip.setFocusable(false);
		textFieldShip.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldShip.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldShip.setEditable(false);
		textFieldShip.setText("\u041A\u043E\u0440\u0430\u0431\u043B\u044C");
		textFieldShip.setDragEnabled(true);
		textFieldShip.setBounds(23, 46, 85, 34);
		frame.getContentPane().add(textFieldShip);
		textFieldShip.setColumns(10);
		
		textFieldDieselShip = new JTextField();
		textFieldDieselShip.setFocusable(false);
		textFieldDieselShip.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDieselShip.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldDieselShip.setText("\u0422\u0435\u043F\u043B\u043E\u0445\u043E\u0434");
		textFieldDieselShip.setEditable(false);
		textFieldDieselShip.setDragEnabled(true);
		textFieldDieselShip.setBounds(23, 91, 85, 38);
		frame.getContentPane().add(textFieldDieselShip);
		textFieldDieselShip.setColumns(10);
		
		textFieldRed = new JTextField();
		textFieldRed.setEditable(false);
		textFieldRed.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldRed.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldRed.setBackground(Color.RED);
		textFieldRed.setText("\u041A\u0440\u0430\u0441\u043D\u044B\u0439");
		textFieldRed.setFocusable(false);
		textFieldRed.setDragEnabled(true);
		textFieldRed.setAutoscrolls(false);
		textFieldRed.setBounds(350, 44, 96, 20);
		frame.getContentPane().add(textFieldRed);
		textFieldRed.setColumns(10);
		
		textFieldYellow = new JTextField();
		textFieldYellow.setText("\u0416\u0435\u043B\u0442\u044B\u0439");
		textFieldYellow.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldYellow.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldYellow.setFocusable(false);
		textFieldYellow.setEditable(false);
		textFieldYellow.setDragEnabled(true);
		textFieldYellow.setBackground(Color.YELLOW);
		textFieldYellow.setBounds(456, 46, 96, 20);
		frame.getContentPane().add(textFieldYellow);
		textFieldYellow.setColumns(10);
		
		textFieldGreen = new JTextField();
		textFieldGreen.setText("\u0417\u0435\u043B\u0435\u043D\u044B\u0439");
		textFieldGreen.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldGreen.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldGreen.setFocusable(false);
		textFieldGreen.setEditable(false);
		textFieldGreen.setDragEnabled(true);
		textFieldGreen.setBackground(Color.GREEN);
		textFieldGreen.setBounds(350, 77, 96, 20);
		frame.getContentPane().add(textFieldGreen);
		textFieldGreen.setColumns(10);
		
		textFieldBlue = new JTextField();
		textFieldBlue.setForeground(Color.WHITE);
		textFieldBlue.setText("\u0421\u0438\u043D\u0438\u0439");
		textFieldBlue.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldBlue.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldBlue.setFocusable(false);
		textFieldBlue.setEditable(false);
		textFieldBlue.setDragEnabled(true);
		textFieldBlue.setBackground(Color.BLUE);
		textFieldBlue.setBounds(456, 77, 96, 20);
		frame.getContentPane().add(textFieldBlue);
		textFieldBlue.setColumns(10);
		
		textFieldWhite = new JTextField();
		textFieldWhite.setText("\u0411\u0435\u043B\u044B\u0439");
		textFieldWhite.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldWhite.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldWhite.setFocusable(false);
		textFieldWhite.setEditable(false);
		textFieldWhite.setDragEnabled(true);
		textFieldWhite.setBackground(Color.WHITE);
		textFieldWhite.setBounds(350, 109, 96, 20);
		frame.getContentPane().add(textFieldWhite);
		textFieldWhite.setColumns(10);
		
		textFieldBlack = new JTextField();
		textFieldBlack.setForeground(Color.WHITE);
		textFieldBlack.setText("\u0427\u0435\u0440\u043D\u044B\u0439");
		textFieldBlack.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldBlack.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldBlack.setFocusable(false);
		textFieldBlack.setEditable(false);
		textFieldBlack.setDragEnabled(true);
		textFieldBlack.setBackground(Color.BLACK);
		textFieldBlack.setBounds(456, 108, 96, 20);
		frame.getContentPane().add(textFieldBlack);
		textFieldBlack.setColumns(10);
		
		JLabel label_1 = new JLabel("\u0426\u0432\u0435\u0442");
		label_1.setBounds(437, 21, 48, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u0424\u043E\u0440\u043C\u0430 \u043F\u0430\u043B\u0443\u0431");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(23, 141, 85, 14);
		frame.getContentPane().add(label_2);
		
		textFieldRectangle = new JTextField();
		textFieldRectangle.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldRectangle.setText("\u041F\u0440\u044F\u043C\u043E\u0443\u0433\u043E\u043B\u044C\u043D\u044B\u0435");
		textFieldRectangle.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldRectangle.setFocusable(false);
		textFieldRectangle.setEditable(false);
		textFieldRectangle.setDragEnabled(true);
		textFieldRectangle.setBounds(10, 166, 109, 20);
		frame.getContentPane().add(textFieldRectangle);
		textFieldRectangle.setColumns(10);
		
		textFieldRounded = new JTextField();
		textFieldRounded.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldRounded.setText("\u0417\u0430\u043A\u0440\u0443\u0433\u043B\u0435\u043D\u043D\u044B\u0435");
		textFieldRounded.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldRounded.setFocusable(false);
		textFieldRounded.setEditable(false);
		textFieldRounded.setDragEnabled(true);
		textFieldRounded.setBounds(10, 197, 109, 20);
		frame.getContentPane().add(textFieldRounded);
		textFieldRounded.setColumns(10);
		
		textFieldTrapeze = new JTextField();
		textFieldTrapeze.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldTrapeze.setText("\u0422\u0440\u0430\u043F\u0435\u0446\u0438\u044F");
		textFieldTrapeze.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTrapeze.setFocusable(false);
		textFieldTrapeze.setEditable(false);
		textFieldTrapeze.setDragEnabled(true);
		textFieldTrapeze.setBounds(10, 228, 109, 20);
		frame.getContentPane().add(textFieldTrapeze);
		textFieldTrapeze.setColumns(10);
		
		JLabel labelMainColor = new JLabel("\u041E\u0421\u041D\u041E\u0412\u041D\u041E\u0419 \u0426\u0412\u0415\u0422");
		labelMainColor.setHorizontalAlignment(SwingConstants.CENTER);
		labelMainColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelMainColor.setBounds(148, 153, 166, 46);
		TransHandMainColor transHandMain = new TransHandMainColor();
		transHandMain.setPanel(panelShip);
		labelMainColor.setTransferHandler(transHandMain);
		frame.getContentPane().add(labelMainColor);
		
		JLabel labelDopColor = new JLabel("\u0414\u041E\u041F\u041E\u041B\u041D\u0418\u0422\u0415\u041B\u042C\u041D\u042B\u0419 \u0426\u0412\u0415\u0422");
		labelDopColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelDopColor.setHorizontalAlignment(SwingConstants.CENTER);
		labelDopColor.setBounds(148, 215, 166, 46);
		TransHandDopColor transHandDop = new TransHandDopColor();
		transHandDop.setPanel(panelShip);
		FlowLayout fl_panelShip = new FlowLayout(FlowLayout.CENTER, 5, 5);
		fl_panelShip.setAlignOnBaseline(true);
		panelShip.setLayout(fl_panelShip);
		labelDopColor.setTransferHandler(transHandDop);
		frame.getContentPane().add(labelDopColor);
		
		btnAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if((panelShip.getShip() != null) && (panelShip.getDecks() != null)) {
						currentPier.getPier(currentList.getSelectedIndex()).plus(panelShip.getShip(), panelShip.getDecks());
						currentPierPanel.repaint();
					}else if((panelShip.getShip() != null) && (panelShip.getDecks() == null)) {
						currentPier.getPier(currentList.getSelectedIndex()).plus(panelShip.getShip());
						currentPierPanel.repaint();
					}
					frame.dispose();
				} catch (PierOverflowException ex) {
					logger_error.warning("Причал переполнен");
					JOptionPane.showMessageDialog(frame, "Причал переполнен",
							"Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdd.setBounds(379, 153, 137, 46);
		frame.getContentPane().add(btnAdd);
		
		JButton btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(379, 215, 137, 46);
		frame.getContentPane().add(btnCancel);
	}
}
