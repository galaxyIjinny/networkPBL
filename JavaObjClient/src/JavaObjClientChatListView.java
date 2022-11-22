
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
import java.awt.Container;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

//import JavaObjServer.UserService;

//import JavaObjClientView.ChatListAction;

import javax.swing.JToggleButton;
import javax.swing.JList;
//채팅방 목록
public class JavaObjClientChatListView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String UserName;
	private String IpAddr;
	private String PortNo;
	private String ChatRoom[];
	private String myChatRooms;
	private String myFriends;
	private static final int BUF_LEN = 128; // Windows 처럼 BUF_LEN 을 정의
	private Socket socket; // 연결소켓

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private JLabel chatroomLabel;
	// private JTextArea textArea;
	private JTextPane textArea;

	private JFrame frame;
	private FileDialog fd;
	
	private Vector myChatRoom;
	
	public JavaObjClientFriendListView FriendListview;

	/**
	 * Create the frame.
	 */
	public JavaObjClientChatListView(String username, JavaObjClientView mainview, String roomids) {
		setBounds(150, 150, 200, 200);
		setBackground(new Color(255, 255, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		setLayout(null);
		//frame.getContentPane().add(this);
		mainview.add(this);
		
		//Container con = frame.getContentPane();
		//con.add(this);
		
		chatroomLabel = new JLabel();
		chatroomLabel.setText(username);
		chatroomLabel.setBounds(150, 150, 50, 50);
		add(chatroomLabel);
		
		ChatMsg msg = new ChatMsg(UserName, "200", "clist");
		//msg.setChatroom("chat11");
		mainview.SendObject(msg);
		//채팅방 목록
		//myChatRoom = chatrooms;
		/*ChatMsg msg = new ChatMsg(UserName, "500", username);
		for (int i = 0; i < user_vc.size(); i++) {
			UserService user = (UserService) user_vc.elementAt(i);
			if (user.UserStatus == "O")
		}*/
		
		
		mainview.AppendText("User chatroom " + username);
		setVisible(true);

		
		
	}



}
