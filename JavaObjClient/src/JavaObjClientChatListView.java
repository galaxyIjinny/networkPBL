
// JavaObjClientView.java ObjecStram ��� Client
//�������� ä�� â
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

//import JavaObjClientView.ChatListAction;

import javax.swing.JToggleButton;
import javax.swing.JList;
//ä�ù� ���
public class JavaObjClientChatListView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String UserName;
	private String IpAddr;
	private String PortNo;
	private String ChatRoom;
	
	private static final int BUF_LEN = 128; // Windows ó�� BUF_LEN �� ����
	private Socket socket; // �������
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private JLabel lblUserName;
	// private JTextArea textArea;
	private JTextPane textArea;
	private JList chatList;

	private Frame frame;
	private FileDialog fd;

	/**
	 * Create the frame.
	 */
	public JavaObjClientChatListView(String username, String ip_addr, String port_no) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 668);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(93, 76, 351, 518);
		contentPane.add(scrollPane);
		
		//ä�ù� ���
		chatList = new JList();
		chatList.setBounds(0, 0, 1, 1);
		chatList.setFont(new Font("����ü", Font.PLAIN, 14));
		scrollPane.add(chatList);
		

		lblUserName = new JLabel("Name");
		lblUserName.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblUserName.setBackground(Color.WHITE);
		lblUserName.setFont(new Font("����", Font.BOLD, 14));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(12, 20, 62, 40);
		contentPane.add(lblUserName);
		setVisible(true);

		AppendText("User " + username + " connecting " + ip_addr + " " + port_no);
		UserName = username;
		IpAddr = ip_addr;
		PortNo = port_no;
		lblUserName.setText(username);
		
		JButton btnNewButton = new JButton("�� ��");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatMsg msg = new ChatMsg(UserName, "400", "Bye");
				SendObject(msg);
				System.exit(0);
			}
		});
		btnNewButton.setBounds(12, 554, 69, 40);
		contentPane.add(btnNewButton);
		
		JButton btnProfileButton = new JButton("ģ��"); // ������ ��ư
		btnProfileButton.setFont(new Font("����", Font.PLAIN, 14));
		MainAction MAction = new MainAction();
		btnProfileButton.addActionListener(MAction);
		//�������� or �׼�
		/*btnProfileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatMsg msg = new ChatMsg(UserName, "900", username);
				SendObject(msg);
			}
		});*/
		btnProfileButton.setBounds(12, 97, 69, 40);
		contentPane.add(btnProfileButton);
		
		JButton btnChatListButton = new JButton("ä��"); // ä�ù� ��� ��ư
		btnChatListButton.setFont(new Font("����", Font.PLAIN, 14));
		//ChatListAction CLAction = new ChatListAction(); // �� ��ġ�� ä�ù� ����Ʈ�̹Ƿ� ����
		//btnChatListButton.addActionListener(CLAction);
		btnChatListButton.setBounds(12, 170, 69, 40);
		contentPane.add(btnChatListButton);

		try {
			//socket = new Socket(ip_addr, Integer.parseInt(port_no));

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

			//SendMessage("/login " + UserName);
			//ChatMsg obcm = new ChatMsg(UserName, "100", "Hello");
			//SendObject(obcm);
			
			//ListenNetwork net = new ListenNetwork();
			//net.start();

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppendText("connect error");
		}

	}

	// Server Message�� �����ؼ� ȭ�鿡 ǥ��
	class ListenNetwork extends Thread {
		public void run() {
			while (true) {
				try {
					Object obcm = null;
					String msg = null;
					ChatMsg cm;
					try {
						obcm = ois.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
					if (obcm == null)
						break;
					if (obcm instanceof ChatMsg) {
						cm = (ChatMsg) obcm;
						msg = String.format("[%s] %s", cm.getId(), cm.getData());
					} else
						continue;
					switch (cm.getCode()) {
					case "200": // chat message
						AppendText(msg);
						break;
					case "300": // Image ÷��
						AppendText("[" + cm.getId() + "]");
						AppendImage(cm.img);
						break;
					}
				} catch (IOException e) {
					AppendText("ois.readObject() error");
					try {
						ois.close();
						oos.close();
						socket.close();

						break;
					} catch (Exception ee) {
						break;
					} // catch�� ��
				} // �ٱ� catch����

			}
		}
	}

	ImageIcon icon1 = new ImageIcon("src/icon1.jpg");

	public void AppendIcon(ImageIcon icon) {
		int len = textArea.getDocument().getLength();
		// ������ �̵�
		textArea.setCaretPosition(len);
		textArea.insertIcon(icon);
	}

	// ȭ�鿡 ���
	public void AppendText(String msg) {
		// textArea.append(msg + "\n");
		//AppendIcon(icon1);
		msg = msg.trim(); // �յ� blank�� \n�� �����Ѵ�.
		int len = textArea.getDocument().getLength();
		// ������ �̵�
		textArea.setCaretPosition(len);
		textArea.replaceSelection(msg + "\n");
	}

	public void AppendImage(ImageIcon ori_icon) {
		int len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len); // place caret at the end (with no selection)
		Image ori_img = ori_icon.getImage();
		int width, height;
		double ratio;
		width = ori_icon.getIconWidth();
		height = ori_icon.getIconHeight();
		// Image�� �ʹ� ũ�� �ִ� ���� �Ǵ� ���� 200 �������� ��ҽ�Ų��.
		if (width > 200 || height > 200) {
			if (width > height) { // ���� ����
				ratio = (double) height / width;
				width = 200;
				height = (int) (width * ratio);
			} else { // ���� ����
				ratio = (double) width / height;
				height = 200;
				width = (int) (height * ratio);
			}
			Image new_img = ori_img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon new_icon = new ImageIcon(new_img);
			textArea.insertIcon(new_icon);
		} else
			textArea.insertIcon(ori_icon);
		len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len);
		textArea.replaceSelection("\n");
		// ImageViewAction viewaction = new ImageViewAction();
		// new_icon.addActionListener(viewaction); // ����Ŭ������ �׼� �����ʸ� ��ӹ��� Ŭ������
	}

	// Windows ó�� message ������ ������ �κ��� NULL �� ����� ���� �Լ�
	public byte[] MakePacket(String msg) {
		byte[] packet = new byte[BUF_LEN];
		byte[] bb = null;
		int i;
		for (i = 0; i < BUF_LEN; i++)
			packet[i] = 0;
		try {
			bb = msg.getBytes("euc-kr");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		for (i = 0; i < bb.length; i++)
			packet[i] = bb[i];
		return packet;
	}

	// Server���� network���� ����
	public void SendMessage(String msg) {
		try {
			ChatMsg obcm = new ChatMsg(UserName, "200", msg);
			oos.writeObject(obcm);
		} catch (IOException e) {
			// AppendText("dos.write() error");
			AppendText("oos.writeObject() error");
			try {
				ois.close();
				oos.close();
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(0);
			}
		}
	}

	public void SendObject(Object ob) { // ������ �޼����� ������ �޼ҵ�
		try {
			oos.writeObject(ob);
		} catch (IOException e) {
			// textArea.append("�޼��� �۽� ����!!\n");
			AppendText("SendObject Error");
		}
	}
	
	class MainAction implements ActionListener // ����Ŭ������ �׼� �̺�Ʈ ó�� Ŭ����. ä�� ���
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			JavaObjClientView view = new JavaObjClientView(UserName, IpAddr, PortNo);
			setVisible(false);
		}
	}
}
