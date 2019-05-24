package ѧ���ɼ�����ϵͳ;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


//�û���¼����
class LoginFrame {
	private JFrame login;
	private JTextField uid;
	private JPasswordField psw;

	public LoginFrame() {
		login = new JFrame("ѧ���ɼ�����ϵͳV0.0.1");
		login.setSize(400, 400);
		login.setResizable(false);
		login.setLayout(null);
		

		JLabel uidl = new JLabel("�û���:");
		uidl.setBounds(40, 140, 50, 50);
		uid = new JTextField();
		uid.setBounds(100, 150, 200, 30);
		JLabel pswl = new JLabel("����:");
		pswl.setBounds(55, 190, 50, 50);
		psw = new JPasswordField();
		psw.setBounds(100, 200, 200, 30);
		

		JButton b = new JButton("��¼");
		b.setBounds(150, 250, 100, 30);
		
		


		JLabel logo =  new JLabel("ѧ���ɼ�����ϵͳ");
		logo.setBounds(40, 20, 400, 100);
		logo.setFont((new Font("΢���ź�", 1, 40)));
		
		//copyright
		JLabel copyright = new JLabel("<html>Copyright C 2019<a href='#'> 219#305</a>. All Rights Reserved.</html>");  
		copyright.setBounds(60, 350, 400, 15);
		copyright.addMouseListener(new MouseAdapter() {  
  
            public void mouseClicked(MouseEvent e) {  
                try {  
                    Runtime.getRuntime().exec("cmd.exe /c start " + "http://www.gov.cn");  
                } catch (Exception ex) {  
                    ex.printStackTrace();  
                }  
            }  
        }); 
		
		login.add(uid);
		login.add(uidl);
		login.add(psw);
		login.add(pswl);
		login.add(b);
		login.add(logo);
		login.add(copyright);
		login.setVisible(true);
		ActionListener listener = new LoginListener();
		b.addActionListener(listener);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	//��¼��ť������
	private class LoginListener implements ActionListener{

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String buttonName = e.getActionCommand();
			if (buttonName.equals("��¼")) {
				if(uid.getText().isEmpty() || psw.getText().isEmpty()) {
					JOptionPane.showMessageDialog(login,"�û���������Ϊ�գ�");
				}
				else {
					Connection con = DBUtils.getConnection();
					Statement sql = null;
				 	ResultSet res = null;
				 	try {
						sql=con.createStatement();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            try {
						res=sql.executeQuery("select * from admin");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            try {
						while(res.next()) {
							String aid = res.getString("aid");
							String apsw = res.getString("apsw");
							if (aid.equals(uid.getText()) && apsw.equals(psw.getText())) {
								//�����֤ͨ��
								new ContentFrame();
								login.dispose();
							}else {
								//�����֤��ͨ��
								JOptionPane.showMessageDialog(login,"�û������������");
							}
							break;//��ѭ��ֻ��ִ��һ��
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		          
		            try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            
				}
			}
		}	
	}	
}