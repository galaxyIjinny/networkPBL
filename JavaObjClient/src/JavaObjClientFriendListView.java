
// JavaObjClientView.java ObjecStram 기반 Client
//실질적인 채팅 창
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
import java.util.Vector;

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

//import JavaObjClientMain.Myaction;

import javax.swing.JToggleButton;
import javax.swing.JList;

public class JavaObjClientFriendListView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel FriendPane;
	private String UserName;
	private String IpAddr;
	private String PortNo;
	private String myChatRooms;
	private String myFriends;
	private Vector<String> myChatRoom;
	private static final int BUF_LEN = 128; // Windows 처럼 BUF_LEN 을 정의
	private Socket socket; // 연결소켓

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private JLabel friendUser;
	// private JTextArea textArea;
	private JTextPane textArea;
	private JTextPane myTextArea;

	private Frame frame;
	private FileDialog fd;

	public UserProfile userpro;
	public JavaObjClientView mainclient;
	public JavaObjClientChatListView ChatListview;
	//public JavaObjClientNotice noticeview;
	
	/**
	 * Create the frame.
	 */
	public JavaObjClientFriendListView(String username, JavaObjClientView mainview, String friendlist) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		ChatMsg obcm = new ChatMsg(UserName, "200", "flist");
		mainview.SendObject(obcm);
		
		String[] getfriend = friendlist.split(" ");
		for (int i = 0; i < getfriend.length; i++) {
			//friendpane[i] = new JPanel();
			//add(userpro(getfriend[i]));
		}
		//setContentPane(contentPane);
		mainview.add(this);
		
		mainview.AppendText("User " + username + " friend lists");
		
		
		setVisible(true);
		
	}
	
}
