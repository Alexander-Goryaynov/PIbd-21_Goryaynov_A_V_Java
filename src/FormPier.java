import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Hashtable;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FormPier {
	
	private final int panelPierWidth = 870;
	private final int panelPierHeight = 560;
	private final int countLevels = 5;
	private MultiLevelPier pier;
	private Hashtable<Integer, ITransport> storageShip;
	private Hashtable<Integer, IDecks> storageDecks;
	private int storageIndex = 0;
	private ITransport ship;
	private IDecks deck;
	private JFrame frame;
	private JTextField textFieldIndex;
	private PierPanel panelPier;
	private JButton btnTake;
	private TakePanel panelTake;
	private JList<String> list;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1042, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		pier = new MultiLevelPier(countLevels, panelPierWidth, panelPierHeight);
		storageShip = new Hashtable<>();
		storageDecks = new Hashtable<>();
		
		panelPier = new PierPanel(pier.getPier(0));
		panelPier.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPier.setBounds(10, 37, 767, 534);
		frame.getContentPane().add(panelPier);
		
		
		String[] levels = new String[countLevels];
		for(int i = 0; i < countLevels; i++) {
			levels[i] = "Уровень" + (i + 1);
		}
		list = new JList(levels);
		list.setSelectedIndex(0);
		list.setBounds(800, 37, 214, 186);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = list.getSelectedIndex();
				panelPier.setPier(pier.getPier(index));
				panelPier.repaint();
			}
		});
		frame.getContentPane().add(list);
		
		JLabel label = new JLabel("\u0417\u0430\u0431\u0440\u0430\u0442\u044C \u043A\u043E\u0440\u0430\u0431\u043B\u044C:");
		label.setBounds(827, 301, 122, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u041C\u0435\u0441\u0442\u043E:");
		label_1.setBounds(837, 326, 48, 14);
		frame.getContentPane().add(label_1);
		
		textFieldIndex = new JTextField();
		textFieldIndex.setBounds(891, 326, 58, 20);
		frame.getContentPane().add(textFieldIndex);
		textFieldIndex.setColumns(10);
		
		btnTake = new JButton("\u0417\u0430\u0431\u0440\u0430\u0442\u044C");
		btnTake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldIndex.getText() != "") {
					ship = pier.getShip(list.getSelectedIndex(),Integer.parseInt(textFieldIndex.getText()));
					if (ship != null) {
						panelTake.clear();
						storageShip.put(storageIndex, ship);
						deck = pier.getDecks(list.getSelectedIndex(), Integer.parseInt(textFieldIndex.getText()));
						if (deck != null) {
							panelTake.drawShip(ship, deck);
							storageDecks.put(storageIndex, deck);
						} else {
							panelTake.drawShip(ship);
						}
						storageIndex++;
						panelTake.ship.setPosition(100, 50, panelPierWidth, panelPierHeight);
						panelPier.repaint();
						panelTake.repaint();
					}
				}
			}
		});
		btnTake.setBounds(830, 355, 119, 23);
		frame.getContentPane().add(btnTake);
		
		panelTake = new TakePanel();
		panelTake.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTake.setBounds(799, 438, 215, 118);
		frame.getContentPane().add(panelTake);
		
		JButton btnShowCollection = new JButton("\u041A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u044F");
		btnShowCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CollectionInfo info = new CollectionInfo();
				info.showCollection(storageShip, storageDecks);
				info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				info.setVisible(true);
			}
		});
		btnShowCollection.setBounds(837, 389, 119, 23);
		frame.getContentPane().add(btnShowCollection);
		
		JButton btnAddShip = new JButton("\u0417\u0430\u043A\u0430\u0437\u0430\u0442\u044C \u043A\u043E\u0440\u0430\u0431\u043B\u044C");
		btnAddShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShipConfig configFrame = new ShipConfig();
				configFrame.frame.setVisible(true);
				configFrame.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				configFrame.initializeConfig(panelPier, pier, list);
			}
		});
		btnAddShip.setBounds(827, 244, 147, 44);
		frame.getContentPane().add(btnAddShip);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(8, 0, 1006, 26);
		frame.getContentPane().add(menuBar);
		
		JMenu menuFile = new JMenu("\u0424\u0430\u0439\u043B");
		menuBar.add(menuFile);
		
		JMenuItem menuItemLoad = new JMenuItem("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C \u043F\u0440\u0438\u0447\u0430\u043B");
		menuItemLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:\\tmp\\file.txt"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовый файл", "txt");
				chooser.setFileFilter(filter);
				int result = chooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					if(pier.loadData(chooser.getSelectedFile().getPath())) {
						JOptionPane.showMessageDialog(null, "Загрузили");
						panelPier.setPier(pier.getPier(list.getSelectedIndex()));
						panelPier.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "Не удалось загрузить");
					}
				}
			}
		});
		menuFile.add(menuItemLoad);
		
		JMenuItem menuItemSave = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u043F\u0440\u0438\u0447\u0430\u043B");
		menuItemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:\\tmp\\file.txt"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовый файл", "txt");
				chooser.setFileFilter(filter);
				int result = chooser.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					if (pier.saveData(chooser.getSelectedFile().getPath())) {
						JOptionPane.showMessageDialog(null, "Сохранение прошло успешно");
					}else {
						JOptionPane.showMessageDialog(null, "Не сохранилось");
					}
				}
			}
		});
		
		JMenuItem menuItemLoadLevel = new JMenuItem("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C \u0443\u0440\u043E\u0432\u0435\u043D\u044C");
		menuItemLoadLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:\\tmp\\fileLevel.txt"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовый файл", "txt");
				chooser.setFileFilter(filter);
				int result = chooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					if(pier.loadLevelData(chooser.getSelectedFile().getPath(), list.getSelectedIndex())) {
						JOptionPane.showMessageDialog(null, "Загрузили");
						panelPier.setPier(pier.getPier(list.getSelectedIndex()));
						panelPier.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "Не удалось загрузить");
					}
				}
			}
		});
		menuFile.add(menuItemLoadLevel);
		menuFile.add(menuItemSave);
		
		JMenuItem menuItemSaveLevel = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u0443\u0440\u043E\u0432\u0435\u043D\u044C");
		menuItemSaveLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:\\tmp\\fileLevel.txt"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовый файл", "txt");
				chooser.setFileFilter(filter);
				int result = chooser.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					if (pier.saveLevelData(chooser.getSelectedFile().getPath(), list.getSelectedIndex())) {
						JOptionPane.showMessageDialog(null, "Сохранение прошло успешно");
					}else {
						JOptionPane.showMessageDialog(null, "Не сохранилось");
					}
				}
			}
		});
		menuFile.add(menuItemSaveLevel);
	}
}
