import java.awt.EventQueue;
import java.awt.HeadlessException;

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
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
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
	private Logger logger;
	private Logger logger_error;

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
	public FormPier() throws PierNotFoundException, PierOverflowException, 
	SecurityException, IOException, PierOccupiedPlaceException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() throws SecurityException, IOException, PierNotFoundException,
	PierOccupiedPlaceException, PierOverflowException {		
		logger = Logger.getLogger(FormPier.class.getName() + "1");
		logger_error = Logger.getLogger(FormPier.class.getName() + "2");
		try {
			FileHandler fh = null;
			FileHandler fh_error = null;
			fh = new FileHandler("C:\\temp\\file_info.txt");
			fh_error = new FileHandler("C:\\temp\\file_error.txt");
			logger.addHandler(fh);
			logger_error.addHandler(fh_error);
			logger.setUseParentHandlers(false);
			logger_error.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			fh_error.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
				try {
					if(textFieldIndex.getText() != "") {
						int a = Integer.parseInt(textFieldIndex.getText());
						ship = pier.getShip(list.getSelectedIndex(),Integer.parseInt(textFieldIndex.getText()));
						panelTake.clear();
						storageShip.put(storageIndex, ship);					
						if (ship instanceof DieselShip) {
							deck = pier.getDecks(list.getSelectedIndex(), Integer.parseInt(textFieldIndex.getText()));
							panelTake.drawShip(ship, deck);
							storageDecks.put(storageIndex, deck);
						} else {
							panelTake.drawShip(ship);
						}
						storageIndex++;
						panelTake.ship.setPosition(100, 50, panelPierWidth, panelPierHeight);
						panelPier.repaint();
						panelTake.repaint();
						logger.info("Удалён корабль по месту " + a);
					}
				} catch (PierNotFoundException ex) {
					logger_error.warning("Не найдено");
					JOptionPane.showMessageDialog(null, "Не найдено",
							"Exception", 0, null);
				} catch (Exception ex) {
					logger_error.warning("Неизвестная ошибка");
					JOptionPane.showMessageDialog(frame, "Неизвестная ошибка",
							"Exception", JOptionPane.ERROR_MESSAGE);
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
			private ShipConfig configFrame;

			public void actionPerformed(ActionEvent e) {
				configFrame = new ShipConfig();
				try {
					configFrame.frame.setVisible(true);
					configFrame.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					configFrame.initializeConfig(panelPier, pier, list);
				} catch (PierOverflowException ex) {
					logger.warning("Причал переполнен");
					JOptionPane.showMessageDialog(null, "Причал переполнен");
				}
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
				try {
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File("C:\\tmp\\file.txt"));
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовый файл", "txt");
					chooser.setFileFilter(filter);
					int result = chooser.showOpenDialog(null);
					if(result == JFileChooser.APPROVE_OPTION) {
						if(pier.loadData(chooser.getSelectedFile().getPath())) {
							JOptionPane.showMessageDialog(null, "Загрузили");
							logger.info("Загрузили");
							panelPier.setPier(pier.getPier(list.getSelectedIndex()));
							panelPier.repaint();
						} else {
							logger.info("Не удалось загрузить");
							JOptionPane.showMessageDialog(null, "Не удалось загрузить");
						}
					}
				} catch (PierOccupiedPlaceException ex) {
					logger_error.warning("Ошибка загрузки " + ex.getMessage());
					JOptionPane.showMessageDialog(frame, ex.getMessage(),
							"Exception", JOptionPane.ERROR_MESSAGE);
				} 
				catch (Exception ex) {
					logger_error.warning("Неизвестная ошибка при загрузке");
					JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при загрузке",
							"Exception", JOptionPane.ERROR_MESSAGE);
					
				} 
			}
		});
		menuFile.add(menuItemLoad);
		
		JMenuItem menuItemSave = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u043F\u0440\u0438\u0447\u0430\u043B");
		menuItemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						JFileChooser chooser = new JFileChooser();
						chooser.setCurrentDirectory(new File("C:\\tmp\\file.txt"));
						FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовый файл", "txt");
						chooser.setFileFilter(filter);
						int result = chooser.showSaveDialog(null);
						if(result == JFileChooser.APPROVE_OPTION) {
							pier.saveData(chooser.getSelectedFile().getPath());
								logger.info("Сохранение прошло успешно");
								JOptionPane.showMessageDialog(null, "Сохранение прошло успешно");
							
						}
					} 
					 catch (Exception e1) {						
						 logger.info("Неизвестная ошибка");
							JOptionPane.showMessageDialog(null, "Неизвестная ошибка");
					}
				}
				
			}
		);
		
		JMenuItem menuItemLoadLevel = new JMenuItem("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C \u0443\u0440\u043E\u0432\u0435\u043D\u044C");
		menuItemLoadLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File("C:\\tmp\\fileLevel.txt"));
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовый файл", "txt");
					chooser.setFileFilter(filter);
					int result = chooser.showOpenDialog(null);
					if(result == JFileChooser.APPROVE_OPTION) {
						if(pier.loadLevelData(chooser.getSelectedFile().getPath(), list.getSelectedIndex())) {
							logger.info("Загрузили");
							JOptionPane.showMessageDialog(null, "Загрузили");
							panelPier.setPier(pier.getPier(list.getSelectedIndex()));
							panelPier.repaint();
						} else {
							logger.info("Не удалось загрузить");
							JOptionPane.showMessageDialog(null, "Не удалось загрузить");
						}
					}
				}  catch (IOException ex) {
					logger_error.warning("Неизвестная ошибка при загрузке " + ex.getMessage());
					JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при загрузке" + ex.getMessage(),
							"Exception", JOptionPane.ERROR_MESSAGE);
				} catch (PierOccupiedPlaceException ex) {
					logger_error.warning("Ошибка загрузки" + ex.getMessage());
					JOptionPane.showMessageDialog(frame, ex.getMessage(),
							"Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menuFile.add(menuItemLoadLevel);
		menuFile.add(menuItemSave);
		
		JMenuItem menuItemSaveLevel = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u0443\u0440\u043E\u0432\u0435\u043D\u044C");
		menuItemSaveLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File("C:\\tmp\\fileLevel.txt"));
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовый файл", "txt");
					chooser.setFileFilter(filter);
					int result = chooser.showSaveDialog(null);
					if(result == JFileChooser.APPROVE_OPTION) {
						pier.saveLevelData(chooser.getSelectedFile().getPath(), list.getSelectedIndex());
							JOptionPane.showMessageDialog(null, "Сохранение прошло успешно");
					}
				} catch (Exception ex) {
					logger_error.warning("Неизвестная ошибка при сохранении");
					JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при сохранении",
							"Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menuFile.add(menuItemSaveLevel);
	}
}
