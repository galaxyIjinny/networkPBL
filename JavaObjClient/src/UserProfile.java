
// ChatMsg.java 채팅 메시지 ObjectStream 용.
import java.io.Serializable;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class UserProfile extends JPanel {
	private JPanel contentPane;
	
	private JLabel userName;
	private JLabel profileImg;
	private JLabel stateMsg;
	
	private String username; 
	private String statemsg; 
	private String data;
	public ImageIcon profileimg;
	//public String chatroomid;
	//public Vector<String> chatuserlists;

	public UserProfile(String username) {
		this.username = username;
		this.statemsg = statemsg;
		this.profileimg = profileimg;
		
		setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
//		//setContentPane(contentPane);

		userName = new JLabel(username);
		userName.setBounds(66, 10, 80, 21);
		contentPane.add(userName);
		
		ImageIcon userImg = profileimg;
		profileImg = new JLabel(userImg);
		profileImg.setBounds(5, 53, 58, -48);
		contentPane.add(profileImg);
		
		stateMsg = new JLabel(statemsg);
		stateMsg.setBounds(66, 30, 126, 21);
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