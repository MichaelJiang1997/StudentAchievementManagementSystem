package ѧ���ɼ�����ϵͳ;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShowAll {

	private JFrame allList;
	private JTable table;
	public ShowAll() {
		Statement sql;
	 	ResultSet res;
	 	Connection con;
		allList = new JFrame("��������");
		//�����������
		Vector<Vector<String>>tableValue = new Vector<>();
        con = DBUtils.getConnection();
        try {
            sql=con.createStatement();
            res=sql.executeQuery("select stu_info.sid,sname,sgender,stu_score.cid,cname,grade " + 
            					 "from stu_info,stu_score,stu_course " + 
            					 "where stu_info.sid = stu_score.sid "+
            					 "and stu_score.cid = stu_course.cid"+
            					 " order by stu_info.sid asc");
            while(res.next()) {
            	//����������
                Vector<String> rowValue = new Vector<>();
                rowValue.add(res.getString("sid"));
                rowValue.add(res.getString("sname"));
                rowValue.add(res.getString("sgender"));
                rowValue.add(res.getString("cid"));
                rowValue.add(res.getString("cname"));
                rowValue.add(res.getString("grade"));
                
                tableValue.add(rowValue);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //����ֶ���
		Vector<String> colName= new Vector<>();
		colName.add("ѧ��");
		colName.add("����");
		colName.add("�Ա�");
		colName.add("�γ̺�");
		colName.add("�γ���");
		colName.add("�ɼ�");	
		table = new JTable(tableValue,colName);
		table.setEnabled(false);
		JScrollPane scp = new JScrollPane(table);
		allList.getContentPane().add(scp,BorderLayout.CENTER);
		allList.setSize(800, 400);
		allList.setVisible(true);
		allList.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		
	}
	
	//����
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ShowAll();
		
	}

}
