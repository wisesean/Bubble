package com.gxlu.client.bubble.printbubble;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ch.swingfx.twinkle.NotificationBuilder;
import ch.swingfx.twinkle.event.NotificationEvent;
import ch.swingfx.twinkle.event.NotificationEventAdapter;
import ch.swingfx.twinkle.window.Positions;

import com.gxlu.client.res.string.StrConstants;
import com.gxlu.client.update.AutoUpdateClient;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.awt.SystemColor;

public class BubbleUi {
	public static int model;
	public static JFrame jf;
	public static JLabel jlb_board;
	public static JList jlt_board;
	public static JPanel panel;
	public static JTextField jtf_board;
	
	private FileOpera fo = new FileOpera();
	SysEnvironment se = new SysEnvironment();

	public static DefaultListModel d_message_lm;
	private static Image image_t = Toolkit.getDefaultToolkit().getImage(BubbleUi.class.getResource(Util.image_t_path));
	private static Image image = Toolkit.getDefaultToolkit().getImage(BubbleUi.class.getResource(Util.image_t_path));
	static TrayIcon trayIcon = new TrayIcon(image, StrConstants.b_ui_title);
	static SystemTray tray = null;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JMenuItem menuItem_1;
	private JMenu menu_1;
	private JMenuItem menuItem_2;
	private JPanel panel_2;
	private JPanel panel_3;
	private JTextArea textArea;
	private JPanel panel_1;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JMenu menu_2;
	private JMenuItem menuItem_3;
	private JMenuItem menuItem_4;
	private JLabel jlb_status_model;
	private JLabel jlb_status_message;
	private JLabel lblNewLabel_3;
	private JMenuItem menuItem_5;
	private JMenu menu_3;
	private JMenuItem menuItem_6;
	private JMenuItem menuItem_7;
	
	public BubbleUi() {
		d_message_lm = new DefaultListModel();
		try {
			Util.InitList();
		} catch (Exception e) {
			throw new RuntimeException(StrConstants.b_exception_init_failer);
		}
		if (SystemTray.isSupported()) {
			this.tray();
		}	
		jf = new JFrame(StrConstants.b_ui_title_main);
		jf.getContentPane().setBackground(new Color(255, 127, 80));
		jf.setForeground(UIManager.getColor("Button.background"));
		jf.setBackground(UIManager.getColor("Button.background"));
		jf.setIconImage(image);
		jf.setSize(new Dimension(800, 500));
		jf.setResizable(true);
		if(true) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension frameSize = jf.getSize();
			if (frameSize.width > screenSize.width) {
				frameSize.width = screenSize.width;
			}
			if (frameSize.height > screenSize.height) {
				frameSize.height = screenSize.height;
			}
			jf.setLocation((screenSize.width - frameSize.width) / 2,
					(screenSize.height - frameSize.height) / 2);
		}
		jf.setDefaultCloseOperation(3);

		JPanel jp_board = new JPanel();
		jp_board.setForeground(UIManager.getColor("CheckBox.interiorBackground"));
		jp_board.setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		jf.getContentPane().add(jp_board, BorderLayout.CENTER);
		jp_board.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		jp_board.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 250, 205));
		Border bo1 = BorderFactory.createBevelBorder(2);
		Border border1 = BorderFactory.createTitledBorder(bo1, StrConstants.b_ui_border_newmessage);
		panel_3.setBorder(border1);
		
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_3, BorderLayout.NORTH);
		
				jlb_board = new JLabel("\u65B0\u7684\u9700\u5904\u7406\u6D88\u606F\uFF1A");
				panel_3.add(jlb_board);
				jlb_board.setHorizontalAlignment(SwingConstants.CENTER);
				
				jtf_board = new JTextField();
				jtf_board.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						jlb_status_message.setText("\u5F53\u524D\u72B6\u6001\uFF1A\u672A\u6536\u5230\u65B0\u6D88\u606F");
						textArea.setText(jtf_board.getText());
					}
				});
				if(d_message_lm.size() > 0) {
					String last_message =String.valueOf(d_message_lm.lastElement());
					jtf_board.setText(last_message.substring(0, last_message.indexOf(" ")));
				}
				panel_3.add(jtf_board);
				jtf_board.setColumns(20);
		
		panel_2 = new JPanel();
		
		panel_2.setBackground(new Color(240, 248, 255));
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		Border bo2 = BorderFactory.createBevelBorder(2);
		Border border2 = BorderFactory.createTitledBorder(bo2, StrConstants.b_ui_border_messagelog);
		panel_4.setBorder(border2);
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		panel_4.add(textArea);
		textArea.setBackground(SystemColor.textHighlightText);
		
		lblNewLabel = new JLabel("    ");
		panel_4.add(lblNewLabel, BorderLayout.EAST);
		
		panel_5 = new JPanel();
		Border border3 = BorderFactory.createTitledBorder(bo2, StrConstants.b_ui_border_messagelist);
		panel_5.setBorder(border3);
		panel_2.add(panel_5, BorderLayout.EAST);
		
				jlt_board = new JList();
				jlt_board.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						String log;
						if(jlt_board.getSelectedIndex() >= 0) {
							log = (String) d_message_lm.get(jlt_board.getSelectedIndex());
							textArea.setText(log.substring(0, log.indexOf(" ")));
							System.out.println("selected!");
						}
					}
				});
				panel_5.setLayout(new BorderLayout(0, 0));
				jlt_board.setVisibleRowCount(18);
				jlt_board.setBackground(new Color(173, 216, 230));
				jlt_board.setModel(d_message_lm);
				JScrollPane js1 = new JScrollPane(jlt_board);
				panel_5.add(js1);
				js1.setVisible(true);
				js1.setWheelScrollingEnabled(true);
				js1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		jp_board.add(panel_1, BorderLayout.SOUTH);
		
		jlb_status_model = new JLabel("\u5F53\u524D\u6A21\u5F0F\uFF1A\u6B63\u5E38\u6A21\u5F0F");
		jlb_status_model.setForeground(new Color(255, 105, 180));
		panel_1.add(jlb_status_model);
		
		lblNewLabel_3 = new JLabel("        ");
		panel_1.add(lblNewLabel_3);
		
		jlb_status_message = new JLabel("\u5F53\u524D\u72B6\u6001\uFF1A\u672A\u6536\u5230\u65B0\u6D88\u606F");
		jlb_status_message.setForeground(new Color(255, 105, 180));
		panel_1.add(jlb_status_message);
		
		menuBar = new JMenuBar();
		jf.setJMenuBar(menuBar);
		
		menu_1 = new JMenu("\u6587\u4EF6");
		menuBar.add(menu_1);
		
		menuItem_2 = new JMenuItem("\u9000\u51FA");
		menuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(1);
			}
		});
		menu_1.add(menuItem_2);
		
		menu = new JMenu("\u6D88\u606F\u7BA1\u7406");
		menuBar.add(menu);
		
		menu_2 = new JMenu("\u8BBE\u7F6E");
		menu.add(menu_2);
		
		menuItem_4 = new JMenuItem("\u9759\u9ED8\u6A21\u5F0F");
		menuItem_4.setSelectedIcon(new ImageIcon(BubbleUi.class.getResource(Util.image_path)));
		menuItem_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BubbleUi.model = Util.SILENT_MODEL;
				jlb_status_model.setText(StrConstants.b_ui_model_silent);
			}
		});
		menu_2.add(menuItem_4);
		
		menuItem_3 = new JMenuItem("\u6B63\u5E38\u6A21\u5F0F");
		menuItem_3.setSelectedIcon(new ImageIcon(BubbleUi.class.getResource(Util.image_path)));
		menuItem_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BubbleUi.model = Util.NORMAL_MODEL;
				jlb_status_model.setText(StrConstants.b_ui_model_normal);
			}
		});
		menu_2.add(menuItem_3);
		
		menuItem_1 = new JMenuItem("\u5BFC\u5165\u6D88\u606F\u65E5\u5FD7");
		menuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter(StrConstants.b_ui_file_filter,"mdd");
				JFileChooser fileChooser = Util.getFileChooser(filter,JFileChooser.FILES_AND_DIRECTORIES);
				if (fileChooser.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
					String content = fo.getContent(fileChooser.getSelectedFile());
					try {
						fo.setContent(new File(se.getTelantfile()), content ,FileOpera.CONTENT_REVERT);
					} catch (IOException e1) {
						//TODO IO exception
						e1.printStackTrace();
					}
					Util.InitList();
				}
			}
		});
		menu.add(menuItem_1);
		
		menuItem = new JMenuItem("\u6E05\u7A7A\u6D88\u606F\u65E5\u5FD7");
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int response = JOptionPane.showConfirmDialog(jf, StrConstants.b_ui_dialog_waring_comfirm, StrConstants.b_ui_dialog_waring_comfirm_title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(response == JOptionPane.OK_OPTION) {
					try {
						fo.setContent(new File(se.getTelantfile()),"",false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d_message_lm.clear();
					jlt_board.setModel(d_message_lm);
					jtf_board.setText("");
					System.out.println("empty!");
				}
			}
		});
		
		menuItem_5 = new JMenuItem("\u5BFC\u51FA\u6D88\u606F\u65E5\u5FD7");
		menuItem_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter(StrConstants.b_ui_file_filter,"mdd");
				JFileChooser fileChooser = Util.getFileChooser(filter,JFileChooser.FILES_ONLY);
				if (fileChooser.showSaveDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
					String content = fo.getContent(new File(se.getTelantfile()));
					String path = fileChooser.getSelectedFile().getAbsolutePath();
					if(!Util.getExName(path).equals("mdd")) {
						path = path + ".mdd";
					}
					File savefile = new File(path);
					try {
						fo.setContent(savefile, content ,FileOpera.CONTENT_REVERT);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		menu.add(menuItem_5);
		menu.add(menuItem);
		
		menu_3 = new JMenu("\u5BA2\u6237\u7AEF\u4FE1\u606F");
		menuBar.add(menu_3);
		
		menuItem_6 = new JMenuItem("\u68C0\u67E5\u65B0\u7248\u672C");
		menuItem_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AutoUpdateClient client = new AutoUpdateClient();
			    client.update();
			}
		});
		menu_3.add(menuItem_6);
		
		menuItem_7 = new JMenuItem("\u5E2E\u52A9");
		menu_3.add(menuItem_7);
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowIconified(WindowEvent e) {
				try {
					tray.add(trayIcon);
				} catch (AWTException e1) {
					throw new RuntimeException(StrConstants.b_ui_minisize_t);
				}
				jf.setVisible(false);
				jf.dispose();
			}
		});
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(jf);
		} catch (Exception exe) {
			exe.printStackTrace();
		}
		jf.setVisible(true);
		new NotificationBuilder().withStyle(NoticeBubble.getDefaultStyle())
		.withTitle("\u65B0\u5361\u5355\u6D88\u606F")
		.withMessage("this is a test message")
		.withIcon(NoticeBubble.b_icon) 
		.withDisplayTime(NoticeBubble.b_disp)
		.withPosition(Positions.NORTH_EAST)
		.withListener(new NotificationEventAdapter() {
			public void closed(NotificationEvent event) {
				System.out.println("closed notification with UUID " + event.getId());
			}

			public void clicked(NotificationEvent event) {
				System.out.println("clicked notification with UUID " + event.getId());
			}
		})
		.showNotification();
		try {
			tray.add(trayIcon);
			Thread.sleep(50);
			jf.dispose();
		} catch (Exception e1) {
			throw new RuntimeException(e1.getMessage());
		}
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("gxlu-cq1");
		Connection connection;
		Channel channel;
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.exchangeDeclare(StrConstants.b_back_exchange_name, "fanout");

			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, StrConstants.b_back_exchange_name, "");

			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName, true, consumer);
			while (true) {
				QueueingConsumer.Delivery delivery = consumer
						.nextDelivery();
				String message = new String(delivery.getBody());
				jtf_board.setText(message);
				System.out.println(" [x] Received '" + message.replace(" ", "") + "'");
				message = message + "      " + Util.getTime();
				d_message_lm.addElement(message);
				jlt_board.setModel(d_message_lm);
				fo.setContent(new File(se.getTelantfile()), message,FileOpera.CONTENT_ADD);
				if(BubbleUi.model != Util.SILENT_MODEL) {
//					if(se.getOsname().contains("Windows")) {
//						String cmd ="mshta vbscript:CreateObject(\"Wscript.Shell\").popup(\""+message+"\",3,\""+StrConstants.b_ui_title_main+"\",64)(window.close)";
//						Runtime.getRuntime().exec(cmd);
//					}else if(se.getOsname().contains("Linux")) {
//						Runtime.getRuntime().exec(new String[]{"notify-send", message});
//					}
					new NotificationBuilder().withStyle(NoticeBubble.getDefaultStyle())
							.withTitle("\u65B0\u5361\u5355\u6D88\u606F")
							.withMessage(message)
							.withIcon(NoticeBubble.b_icon) 
							.withDisplayTime(NoticeBubble.b_disp)
							.withPosition(Positions.NORTH_EAST)
							.withListener(new NotificationEventAdapter() {
								public void closed(NotificationEvent event) {
									System.out.println("closed notification with UUID " + event.getId());
								}

								public void clicked(NotificationEvent event) {
									System.out.println("clicked notification with UUID " + event.getId());
								}
							})
							.showNotification();
				}
				jlb_status_message.setText(StrConstants.b_ui_status_newmessage);			
			}
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.CreateErrorDialog(jf, "Can not connect to the host", StrConstants.b_ui_dialog_waring_error_title);
		}
	}

	void tray() {
		tray = SystemTray.getSystemTray();
		ImageIcon icon = new ImageIcon(image_t);
		PopupMenu pop = new PopupMenu();
		MenuItem show = new MenuItem(StrConstants.b_ui_back_openbubble);
		MenuItem exit = new MenuItem(StrConstants.b_ui_back_exitbubble);
		pop.add(show);
		pop.add(exit);
		trayIcon = new TrayIcon(icon.getImage(), StrConstants.b_ui_title_main, pop);
		trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					tray.remove(trayIcon);
					jf.setExtendedState(JFrame.NORMAL);
					jf.setVisible(true);
					jf.toFront();
				}
			}
		});
		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setExtendedState(JFrame.NORMAL);
				jf.setVisible(true);
				jf.toFront();
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
	}
}