
// ChatMsg.java 채팅 메시지 ObjectStream 용.
import java.io.Serializable;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class UserProfile extends JFrame {
	private JPanel contentPane;
	private JLabel userName;
	private JLabel profileImg;
	private JLabel stateMsg;
	
	private static final long serialVersionUID = 1L;
	private String username; 
	private String statemsg; 
	private String data;
	public ImageIcon profileimg;
	//public String chatroomId;
	//public Vector<String> chatuserlists;

	public UserProfile(String username, String statemsg, ImageIcon profileimg) {
		this.username = username;
		this.statemsg = statemsg;
		this.profileimg = profileimg;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userName = new JLabel(username);
		userName.setBounds(0, 28, 18, -28);
		
		ImageIcon userImg = profileimg;
		profileImg = new JLabel(userImg);
		stateMsg = new JLabel(statemsg);
		
		contentPane.add(userName);
		contentPane.add(profileImg);
		contentPane.add(stateMsg);
	}
	
	public String getstatemsg() {
		return statemsg;
	}

	public void setstatemsg(String statemsg) {
		this.statemsg = statemsg;
	}

	public String getUsernmae() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setprofileimg(ImageIcon profileimg) {
		this.profileimg = profileimg;
	}
//	public void setChatroomId(String chatroomId) {
//		this.chatroomId = chatroomId;
//	}
//	public void setChatuserlists(Vector chatuserlists) {
//		this.chatuserlists = chatuserlists;
//	}
}