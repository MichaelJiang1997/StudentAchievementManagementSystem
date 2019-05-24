package 学生成绩管理系统;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//主界面
public class ContentFrame{
	private JFrame content;
	private JPanel panel;
	private JPanel panelDefault;
	private JPanel panelAdd;
	private JPanel panelDelete;
	private JPanel panelModifiy;
	private JPanel panelSearch;
	private JPanel panelPsw;
	private JPanel panelFlunk;
	
	public ContentFrame() {
		content = new JFrame("学生成绩管理系统V0.0.1");
		panel = new JPanel();
		panelDefault = new JPanel();
		panelAdd = new JPanel();
		panelDelete = new JPanel();
		panelModifiy = new JPanel();
		panelSearch = new JPanel();
		panelPsw = new JPanel();
		panelFlunk = new JPanel();
		
		JMenu op_menu = new JMenu("操作");
		JMenu cnt_menu = new JMenu("统计");
		JMenu setting_menu = new JMenu("设置");
		JMenu help_menu = new JMenu("帮助");
		
		JMenuBar menubar = new JMenuBar();
		JMenuItem add_item = new JMenuItem("添加");
		JMenuItem delete_item = new JMenuItem("删除");
		JMenuItem modifiy_item = new JMenuItem("修改");
		JMenuItem search_item = new JMenuItem("查找");
		
		JMenuItem list_item = new JMenuItem("全部数据");
		JMenuItem flunk_item = new JMenuItem("范围统计");
		
		JMenuItem psw_item = new JMenuItem("修改密码");
		
		JMenuItem about_item = new JMenuItem("关于");
		
		op_menu.add(add_item);
		op_menu.add(delete_item);
		op_menu.add(modifiy_item);
		op_menu.add(search_item);
		cnt_menu.add(list_item);
		cnt_menu.add(flunk_item);
		setting_menu.add(psw_item);
		help_menu.add(about_item);
		
		menubar.add(op_menu);
		menubar.add(cnt_menu);
		menubar.add(setting_menu);
		menubar.add(help_menu);
		
		CardLayout card = new CardLayout(); 
		panel.setLayout(card); 
		panel.add("default panel",panelDefault); 
        panel.add("add panel",panelAdd); 
        panel.add("delete panel",panelDelete); 
        panel.add("modifiy panel",panelModifiy); 
        panel.add("search panel",panelSearch); 
        panel.add("psw panel",panelPsw);
        panel.add("flunk panel",panelFlunk);
        
        //初始化各个panel
        initPanelAdd();
        initPanelDelete();
        initPanelModifiy();
		initPanelSearch();
		initPanelPsw();
		initPanelFlunk();
		
		//菜单事件监听器
		list_item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new ShowAll();
			}});
		
		flunk_item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				card.show(panel,"flunk panel"); 
			}});
		
		about_item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(content,"学生成绩管理系统V0.0.1\n作者:219#305");
			}});
		
		add_item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel,"add panel"); 
				
			}
			
		});
		
		delete_item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel,"delete panel"); 
				
			}
			
		});
		
		modifiy_item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel,"modifiy panel"); 
				
			}
			
		});

		search_item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel,"search panel"); 
				
			}
			
		});
		
		psw_item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel,"psw panel"); 
				
			}
			
		});
	
		content.add(panel);
		content.setJMenuBar(menubar);
		content.setSize(800, 500);
		content.setLayout(new FlowLayout());
		content.setVisible(true);
	}
	
	//初始化添加卡片界面
	void initPanelAdd() {
		Box baseBox,boxV1,boxV2; 
		JTextField sid = new JTextField(16);
		JTextField cid = new JTextField(16);
		JTextField grade = new JTextField(16);
		JButton addBut = new JButton("添加");
		boxV1=Box.createVerticalBox();
        boxV1.add(new JLabel("学生编号:"));
        boxV1.add(Box.createVerticalStrut(8));
        boxV1.add(new JLabel("课程编号:"));
        boxV1.add(Box.createVerticalStrut(8));
        boxV1.add(new JLabel("考试成绩:"));
        boxV2=Box.createVerticalBox();
        boxV2.add(sid);
        boxV2.add(Box.createVerticalStrut(8));
        boxV2.add(cid);
        boxV2.add(Box.createVerticalStrut(8));
        boxV2.add(grade);
        baseBox=Box.createHorizontalBox();
        baseBox.add(boxV1);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(boxV2);
        panelAdd.add(baseBox);
        panelAdd.add(addBut);
        panelAdd.setLayout(new FlowLayout());
        addBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection con = DBUtils.getConnection();
				try {
					Statement sql = con.createStatement();
					int r = sql.executeUpdate("insert into stu_score values("+sid.getText()+
							","+cid.getText()+","+grade.getText()+")");
					if(r==1) {
						JOptionPane.showMessageDialog(content,"添加成功！");
					}else {
						JOptionPane.showMessageDialog(content,"添加失败！");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(content,"添加失败！");
					e1.printStackTrace();
				}
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

        	
        });
	}
	
	//初始化删除卡片界面
	void initPanelDelete() {
			Box baseBox,boxV1,boxV2; 
			JTextField sid = new JTextField(16);
			JTextField cid = new JTextField(16);
			JButton addBut = new JButton("删除");
			boxV1=Box.createVerticalBox();
	        boxV1.add(new JLabel("学生编号:"));
	        boxV1.add(Box.createVerticalStrut(8));
	        boxV1.add(new JLabel("课程编号:"));
	        boxV1.add(Box.createVerticalStrut(8));
	        boxV2=Box.createVerticalBox();
	        boxV2.add(sid);
	        boxV2.add(Box.createVerticalStrut(8));
	        boxV2.add(cid);
	        boxV2.add(Box.createVerticalStrut(8));
	        baseBox=Box.createHorizontalBox();
	        baseBox.add(boxV1);
	        baseBox.add(Box.createHorizontalStrut(10));
	        baseBox.add(boxV2);
	        panelDelete.add(baseBox);
	        panelDelete.add(addBut);
	        panelDelete.setLayout(new FlowLayout());
	        addBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Connection con = DBUtils.getConnection();
					try {
						Statement sql = con.createStatement();
						int r = sql.executeUpdate("delete from stu_score where sid='"+sid.getText()+
								"' and cid='"+cid.getText()+"'");
						if(r==1) {
							JOptionPane.showMessageDialog(content,"删除成功！");
						}else {
							JOptionPane.showMessageDialog(content,"删除失败！");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(content,"删除失败！");
						e1.printStackTrace();
					}
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

	        	
	        });
		}
		
	//初始化修改密码卡片界面
	void initPanelPsw(){
			Box baseBox,boxV1,boxV2; 
			JTextField oldPsw = new JTextField(16);
			JTextField newPsw = new JTextField(16);
			JTextField uid = new JTextField(16);
			JButton addBut = new JButton("修改");
			boxV1=Box.createVerticalBox();
			boxV1.add(new JLabel("用户名:"));
		    boxV1.add(Box.createVerticalStrut(8));
	        boxV1.add(new JLabel("原密码:"));
	        boxV1.add(Box.createVerticalStrut(8));
	        boxV1.add(new JLabel("新密码:"));
	        boxV1.add(Box.createVerticalStrut(8));
	        boxV2=Box.createVerticalBox();
	        boxV2.add(uid);
	        boxV2.add(Box.createVerticalStrut(8));
	        boxV2.add(oldPsw);
	        boxV2.add(Box.createVerticalStrut(8));
	        boxV2.add(newPsw);
	        boxV2.add(Box.createVerticalStrut(8));
	        baseBox=Box.createHorizontalBox();
	        baseBox.add(boxV1);
	        baseBox.add(Box.createHorizontalStrut(10));
	        baseBox.add(boxV2);
	        panelPsw.add(baseBox);
	        panelPsw.add(addBut);
	        panelPsw.setLayout(new FlowLayout());
	        addBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Connection con = DBUtils.getConnection();
					 try {
						 Statement sql=con.createStatement();
						 ResultSet res=sql.executeQuery("select * from admin");
						 try {
								while(res.next()) {
									String aid = res.getString("aid");
									String apsw = res.getString("apsw");
									if (aid.equals(uid.getText()) && apsw.equals(oldPsw.getText())) {
										//验证通过执行修改密码
										try {
											int r = sql.executeUpdate("update admin set apsw='"+newPsw.getText()+
													"' where aid = '"+uid.getText()+"'");
											if(r==1) {
												JOptionPane.showMessageDialog(content,"修改成功！");
											}else {
												//
												JOptionPane.showMessageDialog(content,"修改失败！");
											}
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(content,"修改失败！");
											e1.printStackTrace();
										}
										break;
									}else {
										//验证未通过
										JOptionPane.showMessageDialog(content,"修改失败！");
									}
									
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
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

	        	
	        });
		}
	
	//初始化修改卡片界面
	void initPanelModifiy() {
		Box baseBox,boxV1,boxV2; 
		JTextField sid = new JTextField(16);
		JTextField cid = new JTextField(16);
		JTextField grade = new JTextField(16);
		JButton loadBut = new JButton("载入");
		JButton modBut = new JButton("修改");
		boxV1=Box.createVerticalBox();
        boxV1.add(new JLabel("学生编号:"));
        boxV1.add(Box.createVerticalStrut(8));
        boxV1.add(new JLabel("课程编号:"));
        boxV1.add(Box.createVerticalStrut(8));
        boxV1.add(new JLabel("考试成绩:"));
        boxV2=Box.createVerticalBox();
        boxV2.add(sid);
        boxV2.add(Box.createVerticalStrut(8));
        boxV2.add(cid);
        boxV2.add(Box.createVerticalStrut(8));
        boxV2.add(grade);
        baseBox=Box.createHorizontalBox();
        baseBox.add(boxV1);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(boxV2);
        panelModifiy.add(baseBox);
        panelModifiy.add(loadBut);
        panelModifiy.add(modBut);
        panelModifiy.setLayout(new FlowLayout());
        loadBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection con = DBUtils.getConnection();
				try {
					Statement sql = con.createStatement();
					ResultSet res=sql.executeQuery("select * from stu_score where sid = '"+sid.getText()+
							"' and cid='"+cid.getText()+"'");
					while(res.next()) {
						String sgrade = res.getString("grade");
						grade.setText(sgrade);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(content,"载入失败!");
					e1.printStackTrace();
				}
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

        	
        });
        
        modBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection con = DBUtils.getConnection();
				try {
					Statement sql = con.createStatement();
					int r = sql.executeUpdate("update  stu_score set grade='"+grade.getText()+
							"' where sid='"+sid.getText()+"' and cid='"+cid.getText()+"'");
					if(r==1) {
						JOptionPane.showMessageDialog(content,"修改成功！");
					}else {
						JOptionPane.showMessageDialog(content,"修改失败！");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(content,"修改失败！");
					e1.printStackTrace();
				}
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

        	
        });
	}
	
	//初始化查找卡片界面
	void initPanelSearch() {
		Box baseBox,boxV1,boxV2; 
		JTextField sid = new JTextField(16);
		JButton seaBut = new JButton("查找");
		boxV1=Box.createVerticalBox();
        boxV1.add(new JLabel("学生编号:"));
        boxV1.add(Box.createVerticalStrut(8));
        boxV2=Box.createVerticalBox();
        boxV2.add(sid);
        boxV2.add(Box.createVerticalStrut(4));
        baseBox=Box.createHorizontalBox();
        baseBox.add(boxV1);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(boxV2);
        panelSearch.add(baseBox);
        panelSearch.add(seaBut);
        panelSearch.setLayout(new FlowLayout());
        seaBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ShowSearch(sid.getText());
			}
        });
	}
	
	//初始化统计不及格卡片界面
	void initPanelFlunk() {
		Box baseBox,boxV1,boxV2; 
		JTextField cid = new JTextField(16);
		JTextField line = new JTextField(16);
		JButton seaBut = new JButton("统计");
		JRadioButton radio1 = new JRadioButton("大于基准");
		JRadioButton radio2 = new JRadioButton("小于基准");
		
		ButtonGroup btnGroup = new ButtonGroup();//radio互斥
		btnGroup.add(radio1);
		btnGroup.add(radio2);
		
		boxV1=Box.createVerticalBox();
        boxV1.add(new JLabel("按课程编号:"));
        boxV1.add(Box.createVerticalStrut(8));
        boxV1.add(new JLabel("基准分数线:"));
        boxV1.add(Box.createVerticalStrut(8));
       
        boxV2=Box.createVerticalBox();
        boxV2.add(cid);
        boxV2.add(Box.createVerticalStrut(4));
        boxV2.add(line);
        boxV2.add(Box.createVerticalStrut(4));
        baseBox=Box.createHorizontalBox();
        baseBox.add(boxV1);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(boxV2);
        baseBox.add(radio1);
        baseBox.add(radio2);
        radio1.doClick();
        panelFlunk.add(baseBox);
        panelFlunk.add(seaBut);
        panelFlunk.setLayout(new FlowLayout());
        seaBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(radio1.isSelected())
					new ShowCnt(cid.getText(),line.getText(),true);
				else
					new ShowCnt(cid.getText(),line.getText(),false);
			}
        });
	}
	
	//主界面测试
	public static void main(String args[]) {
		new ContentFrame();
	}
}