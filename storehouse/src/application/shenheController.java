package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class shenheController {
	@FXML
	private ComboBox<String> com_jinhuo,com_chuhuo;
	@FXML
	private Button btn_fresh;
	@FXML
	private Button btn_exit;
	@FXML
	private Button btn_jin;
	@FXML
	private Button btn_chu;
	
	@FXML
	private TableView<JinhuoTable> HuoTable;
	@FXML
	private TableColumn<JinhuoTable,String>Huo_ID;
	@FXML
	private TableColumn<JinhuoTable,String> Huo_name;
	@FXML
	private TableColumn<JinhuoTable,String> Huo_num;
	@FXML
	private TableColumn <JinhuoTable,String>Huo_pro;
	
	@FXML
	private TableView<JinhuoTable> JinhuoTable;
	@FXML
	private TableColumn<JinhuoTable,String>Jinhuo_ID;
	@FXML
	private TableColumn<JinhuoTable,String> Jinhuo_name;
	@FXML
	private TableColumn<JinhuoTable,String> Jinhuo_num;
	@FXML
	private TableColumn <JinhuoTable,String>Jinhuo_pro;
	
	@FXML
	private TableView<LackTable> ChuhuoTable;
	@FXML
	private TableColumn<LackTable,String>Chuhuo_ID;
	@FXML
	private TableColumn<LackTable,String> Chuhuo_name;
	@FXML
	private TableColumn<LackTable,String> Chuhuo_num;
	@FXML
	private TableColumn <LackTable,String>Chuhuo_pro;
	@FXML
	private TableColumn <LackTable,String>Chuhuo_to;
	
	@FXML
	private TableView<LackTable> LackTable;
	@FXML
	private TableColumn<LackTable,String>lack_ID;
	@FXML
	private TableColumn<LackTable,String> lack_name;
	@FXML
	private TableColumn<LackTable,String> lack_num;
	@FXML
	private TableColumn <LackTable,String>lack_pro;
	@FXML
	private TableColumn <LackTable,String>lack_time;
	
	@FXML
	private TableView<JinhuoTable> SjinhuoTable;
	@FXML
	private TableColumn<JinhuoTable,String>Sjinhuo_ID;
	@FXML
	private TableColumn<JinhuoTable,String> Sjinhuo_name;
	@FXML
	private TableColumn<JinhuoTable,String> Sjinhuo_num;
	@FXML
	private TableColumn <JinhuoTable,String>Sjinhuo_time;
	
	
	@FXML
	private TableView<LackTable> SchuhuoTable;
	@FXML
	private TableColumn<LackTable,String>Schuhuo_ID;
	@FXML
	private TableColumn<LackTable,String> Schuhuo_name;
	@FXML
	private TableColumn<LackTable,String> Schuhuo_num;
	@FXML
	private TableColumn <LackTable,String>Schuhuo_time;
	@FXML
	private TableColumn <LackTable,String>Schuhuo_islacked;
	
	@FXML
	private TableView<IncomeTable> ClackTable;
	@FXML
	private TableColumn<IncomeTable,String>Clack_ID;
	@FXML
	private TableColumn<IncomeTable,String> Clack_name;
	@FXML
	private TableColumn<IncomeTable,String> Clack_pro;
	@FXML
	private TableColumn <IncomeTable,String>Clack_chulinum;
	@FXML
	private TableColumn<IncomeTable,String> Clack_lacknum;
	@FXML
	private TableColumn<IncomeTable,String> Clack_chulitime;
	
	ObservableList<JinhuoTable> huoList;
	ObservableList<JinhuoTable> jinhuoList;
	ObservableList<LackTable> chuhuoList;
	ObservableList<LackTable> lackList;
	ObservableList<JinhuoTable> sjinhuoList;
	ObservableList<LackTable> schuhuoList;
	ObservableList<IncomeTable> clackList;
	
	private ArrayList<String> jinFull = new ArrayList<String>();
	private ArrayList<String> chuFull = new ArrayList<String>();
	
	public void initialize() {
		
		Huo_ID.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_regid"));
		Huo_name.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_patname"));
		Huo_num.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_regtime"));
		Huo_pro.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_regtype"));
		huoList = FXCollections.observableArrayList();
		
		Jinhuo_ID.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_regid"));
		Jinhuo_name.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_patname"));
		Jinhuo_num.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_regtime"));
		Jinhuo_pro.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_regtype"));
		jinhuoList = FXCollections.observableArrayList();
		
		Sjinhuo_ID.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_regid"));
		Sjinhuo_name.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_patname"));
		Sjinhuo_num.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_regtime"));
		Sjinhuo_time.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_regtype"));
		sjinhuoList = FXCollections.observableArrayList();
		
		
		
		
		lack_ID.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_offname"));
		lack_name.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_docnum"));
		lack_num.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_docname"));
		lack_pro.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_regtype2"));
		lack_time.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_regcount"));
		lackList =  FXCollections.observableArrayList();
		
		Chuhuo_ID.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_offname"));
		Chuhuo_name.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_docnum"));
		Chuhuo_num.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_docname"));
		Chuhuo_pro.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_regtype2"));
		Chuhuo_to.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_regcount"));
		chuhuoList =  FXCollections.observableArrayList();
		
		Schuhuo_ID.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_offname"));
		Schuhuo_name.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_docnum"));
		Schuhuo_num.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_docname"));
		Schuhuo_time.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_regtype2"));
		Schuhuo_islacked.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_regcount"));
		schuhuoList =  FXCollections.observableArrayList();
		
		Clack_ID.setCellValueFactory(new PropertyValueFactory<IncomeTable,String>("col_offname"));
		Clack_name.setCellValueFactory(new PropertyValueFactory<IncomeTable,String>("col_docnum"));
		Clack_pro.setCellValueFactory(new PropertyValueFactory<IncomeTable,String>("col_docname"));
		Clack_chulinum.setCellValueFactory(new PropertyValueFactory<IncomeTable,String>("col_regtype2"));
		Clack_lacknum.setCellValueFactory(new PropertyValueFactory<IncomeTable,String>("col_regcount"));
		Clack_chulitime.setCellValueFactory(new PropertyValueFactory<IncomeTable,String>("col_income"));
		clackList =  FXCollections.observableArrayList();
		
		fresh();
		initcombobox();
		
		btn_exit.setOnAction(new EventHandler<ActionEvent>() {//�˳������뵽��¼����
			public void handle(ActionEvent event) {
				//Main.setLoginUi();
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Scene scene = new Scene(root,600,350);
				Main.CurStage.setScene(new Scene(root));
				Main.CurStage.setResizable(false);
				Main.CurStage.setTitle("�ֿ����ϵͳ");
				Main.CurStage.show();
			}
			
			
		});
		
		btn_fresh.setOnAction(new EventHandler<ActionEvent>() {//ˢ��
			public void handle(ActionEvent event) {
				fresh();
			}
			
		});
		btn_jin.setOnAction(new EventHandler<ActionEvent>() {//ˢ��
			public void handle(ActionEvent event) {
				String addnumString = com_jinhuo.getEditor().getText();
				if(addnumString.length()>6) {//�����ַ�������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"�����ַ���������", "����", JOptionPane.WARNING_MESSAGE);
					com_jinhuo.getEditor().clear();
	    			return;
				}
				if(!isNumeric(addnumString)){//�����ַ�������������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"ֻ���������֣�", "����", JOptionPane.WARNING_MESSAGE);
					com_jinhuo.getEditor().clear();
	    			return;
				}
				if(addnumString.isEmpty()){//��û��������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"��Ϣ���벻������", "����", JOptionPane.WARNING_MESSAGE);
					com_jinhuo.getEditor().clear();
	    			return;
				}
				int needzero = 6 - addnumString.length();
				String addString = "";
				for(int i = 0;i < needzero;i++) {
					addString = addString + "0";
				}
				addnumString = addString + addnumString;
				try {
					ResultSet jinhuors = LoginController.stmt.executeQuery("select * " + 
							" from caigoudan ");
					while(jinhuors.next()) {
						if(addnumString.equals(jinhuors.getString(1))) {//�ҵ�
							
							String goodsNamesString  = jinhuors.getString(2);
							String caigounumsString  = jinhuors.getString(3);
							String proString = jinhuors.getString(4);
							SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String shenehtimeString  = df.format(new Date());
							
							//������˵Ľ������в���һ����¼
							LoginController.stmt.executeUpdate("insert into is_shenhedcaigoudan values ('" + addnumString+ "','"+ goodsNamesString+ "','" 
									+caigounumsString+ "','" + shenehtimeString+ "')"  );
							
							//�������򽫲ֿ��ڶ�Ӧ�Ļ��������������������
							int addnum = Integer.valueOf(caigounumsString);//����������
							ResultSet huowu = LoginController.stmt.executeQuery("select * " + 
									" from goodsinfo "+ " where goodsID = '"  + addnumString + "'" );
							huowu.next();
							if(rowsInResultSet(huowu)==0) {//û�������������������Ʒ��Ϣ
								LoginController.stmt.executeUpdate("insert into goodsinfo  values ('" + addnumString+ "','"+ goodsNamesString+ "','"
										+ caigounumsString + "','"
										+proString+  "')"  );
								//ɾ���ý������ļ�¼
								LoginController.stmt.executeUpdate("delete from  caigoudan where goodsID = '"  + addnumString + "'");
								JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					         			"������˳ɹ���\n��Ʒ���Ϊ"+addnumString, "��ʾ", JOptionPane.INFORMATION_MESSAGE);
								fresh();
								return;
							}
							ResultSet huowu1 = LoginController.stmt.executeQuery("select * " + 
									" from goodsinfo "+ " where goodsID = '"  + addnumString + "'" );
							huowu1.next();
							int prenum = Integer.valueOf(huowu1.getString(3));//֮ǰ�Ŀ����
							String aftString = Integer.toString(addnum+prenum);//�µĿ����
							LoginController.stmt.executeUpdate("update  goodsinfo set storenum =  '" + aftString+ 
									"' where goodsID = '"  + addnumString + "'");//���¿��
							
							//�ٿ��Ƿ�ȱ��
							ResultSet quehuo = LoginController.stmt.executeQuery("select * " + 
									" from lacked "+ " where goodsID = '"  + addnumString + "'" );
							
							if(rowsInResultSet(quehuo)!=0) {//����Ʒȱ�����ý�����������
								quehuo.next();
								int lacknum = Integer.valueOf(quehuo.getString(3));
								if(lacknum<=addnum+prenum)//ȱ�������Բ���
								{
									//���¿��
									String aftString1 = Integer.toString(addnum+prenum-lacknum);//�µĿ����
									LoginController.stmt.executeUpdate("update  goodsinfo set storenum =  '" + aftString1+ 
											"' where goodsID = '"  + addnumString + "'");//���¿��
									
									//ɾ����ȱ����
									LoginController.stmt.executeUpdate("delete from  lacked where goodsID = '"  + addnumString + "'");
									
									//�Ѿ������ȱ���������Ѵ����ȱ�����ϼ�һ����¼
									LoginController.stmt.executeUpdate("insert into chuli_lacked  values ('" + addnumString+ "','"+ goodsNamesString+ "','"
											+ proString + "','"
											+Integer.toString(lacknum)+ "','" + Integer.toString(addnum+prenum) + "','" + shenehtimeString +  "')"  );
									
									
								}
								else {
									//���¿��
									LoginController.stmt.executeUpdate("update  goodsinfo set storenum =  '0' "
											+ "where goodsID = '"  + addnumString + "'");//���¿��
									//ȱ����ȱ����������
									LoginController.stmt.executeUpdate("update  lacked set lacknum =  '"+ Integer.toString(lacknum-addnum-prenum)
											+ "' where goodsID = '"  + addnumString + "'");//����ȱ����
									//�Ѿ������ȱ���������Ѵ����ȱ�����ϼ�һ����¼
									LoginController.stmt.executeUpdate("insert into chuli_lacked  values ('" + addnumString+ "','"+ goodsNamesString+ "','"
											+ proString + "','"
											+Integer.toString(lacknum)+ "','" + Integer.toString(addnum+prenum) + "','" + shenehtimeString + "')"  );
									
								}
								
							}
							
							
							//ɾ���ý������ļ�¼
							LoginController.stmt.executeUpdate("delete from  caigoudan where goodsID = '"  + addnumString + "'");
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				         			"������˳ɹ���\n��Ʒ���Ϊ"+addnumString, "��ʾ", JOptionPane.INFORMATION_MESSAGE);
							com_jinhuo.getEditor().setText("");
						    System.out.println("ɾ���ɹ���");
							fresh();
							return;
						}
						
						
					}
					/*SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String shenehtimeString  = df.format(new Date());
					
					//û�ҵ�
					ResultSet delrs = LoginController.stmt.executeQuery("SELECT * FROM caigoudan where goodsID = '" +addnumString +"'");
					delrs.next();
					
					//�������������
					LoginController.stmt.executeUpdate("insert into goodsinfo  values ('" + addnumString+ "','"+ delrs.getString(2)+ "','"
							+ delrs.getString(3) + "','"
							+delrs.getString(4)+  "')"  );
					
					//������˵Ľ������в���һ����¼
					LoginController.stmt.executeUpdate("insert into is_shenhedcaigoudan values ('" + addnumString+ "','"+ delrs.getString(2)+ "','" 
							+delrs.getString(3)+ "','" + shenehtimeString+ "')"  );
					//ɾ���ý������ļ�¼
					LoginController.stmt.executeUpdate("delete from  caigoudan where goodsID = '"  + addnumString + "'");
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
		         			"������˳ɹ���\n��Ʒ���Ϊ"+addnumString, "��ʾ", JOptionPane.INFORMATION_MESSAGE);*/
					//û�ҵ�
					
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"�������в����ڸñ�ţ�", "����", JOptionPane.WARNING_MESSAGE);
					fresh();
					com_jinhuo.getEditor().setText("");
					
					return;
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				fresh();
			}
			
		});
		
		btn_chu.setOnAction(new EventHandler<ActionEvent>() {//��˳�����
			public void handle(ActionEvent event) {
				String addnumString = com_chuhuo.getEditor().getText();
				if(addnumString.length()>6) {//�����ַ�������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"�����ַ���������", "����", JOptionPane.WARNING_MESSAGE);
					com_chuhuo.getEditor().clear();
	    			return;
				}
				if(!isNumeric(addnumString)){//�����ַ�������������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"ֻ���������֣�", "����", JOptionPane.WARNING_MESSAGE);
					com_chuhuo.getEditor().clear();
	    			return;
				}
				if(addnumString.isEmpty()){//��û��������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"��Ϣ���벻������", "����", JOptionPane.WARNING_MESSAGE);
					com_chuhuo.getEditor().clear();
	    			return;
				}
				int needzero = 6 - addnumString.length();
				String addString = "";
				for(int i = 0;i < needzero;i++) {
					addString = addString + "0";
				}
				addnumString = addString + addnumString;
				try {
					ResultSet jinhuors = LoginController.stmt.executeQuery("select * " + 
							" from chuhuodan ");
					while(jinhuors.next()) {
						if(addnumString.equals(jinhuors.getString(1))) {//�ҵ�
							
							String goodsNamesString  = jinhuors.getString(2);//��Ʒ����
							String chuhuonumsString  = jinhuors.getString(3);//��Ʒ������
							String proString = jinhuors.getString(4);//��Ʒ����
							
							SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String shenehtimeString  = df.format(new Date());//���ʱ��,Ҳ��ȱ���Ǽ�ʱ��
							
							
							ResultSet huowu = LoginController.stmt.executeQuery("select * " + 
									" from goodsinfo "+ " where goodsID = '"  + addnumString + "'" );
							huowu.next();
							int prenum = Integer.valueOf(huowu.getString(3));//֮ǰ�Ŀ����
							int chuhuonum = Integer.valueOf(chuhuonumsString);//����������
							
							if(prenum>=chuhuonum) {//�����
								//���¿��
								LoginController.stmt.executeUpdate("update  goodsinfo set storenum =  ' "+ Integer.toString(prenum-chuhuonum)
										+ "' where goodsID = '"  + addnumString + "'");//���¿��
								//������˵ĳ������в���һ����¼
								LoginController.stmt.executeUpdate("insert into is_shenhedchuhuodan values ('" + addnumString+ "','"+ goodsNamesString+ "','" 
										+chuhuonumsString+ "','" + shenehtimeString + "','"+ "0"+ "')"  );
								
							}
							else {//��治�㣬��Ҫȱ���Ǽ�
								//���¿��
								LoginController.stmt.executeUpdate("update  goodsinfo set storenum =  '0' "
										+ "where goodsID = '"  + addnumString + "'");//���¿��
								//������˵ĳ������в���һ����¼
								LoginController.stmt.executeUpdate("insert into is_shenhedchuhuodan values ('" + addnumString+ "','"+ goodsNamesString+ "','" 
										+chuhuonumsString+ "','" + shenehtimeString + "','"+ "1"+ "')"  );
								
								//ȱ�����ϼ�һ����¼
								LoginController.stmt.executeUpdate("insert into lacked  values ('" + addnumString+ "','"+ goodsNamesString+ "','"
										+ Integer.toString(chuhuonum-prenum) + "','"
										+ proString + "','"+   shenehtimeString + "')"  );
								
							}
							
							
							//ɾ���ó�����
							LoginController.stmt.executeUpdate("delete from  chuhuodan where goodsID = '"  + addnumString + "'");
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				         			"������˳ɹ���\n��Ʒ���Ϊ"+addnumString, "��ʾ", JOptionPane.INFORMATION_MESSAGE);
							com_chuhuo.getEditor().setText("");
						
							fresh();
							return;
						}
						
					}
					
					//û�ҵ�
					
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"�������в����ڸñ�ţ�", "����", JOptionPane.WARNING_MESSAGE);
					fresh();
					com_chuhuo.getEditor().setText("");
					
					return;
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				fresh();
			}
			
		});
		
	}
	
	public void fresh () {
		try {
			if(huoList != null)huoList.clear();//�Ƚ�֮ǰ���б����
			if(jinhuoList != null)jinhuoList.clear();//�Ƚ�֮ǰ���б����
			if(chuhuoList != null)chuhuoList.clear();//�Ƚ�֮ǰ���б����
			if(lackList != null)lackList.clear();//�Ƚ�֮ǰ���б����
			if(sjinhuoList != null)sjinhuoList.clear();//�Ƚ�֮ǰ���б����
			if(schuhuoList != null)schuhuoList.clear();//�Ƚ�֮ǰ���б����
			if(clackList != null)clackList.clear();//�Ƚ�֮ǰ���б����
			
			ResultSet patientRS = LoginController.stmt.executeQuery("select * " + 
					" from goodsinfo ");
			while(patientRS.next()) {
				System.out.println(patientRS.getString(1)+ " " + patientRS.getString(2)+" " + patientRS.getString(3)+" " + patientRS.getString(4));
				huoList.add(new JinhuoTable(patientRS.getString(1),patientRS.getString(2),patientRS.getString(3),patientRS.getString(4)));
			}
			HuoTable.setItems(huoList);
			System.out.println(huoList);
			
			ResultSet incomeRS = LoginController.stmt.executeQuery("select * " + 
					" from caigoudan ");
			while(incomeRS.next()) {
				System.out.println(incomeRS.getString(1)+ " " + incomeRS.getString(2)+" " + incomeRS.getString(3)+" " + incomeRS.getString(4));
				jinhuoList.add(new JinhuoTable(incomeRS.getString(1),incomeRS.getString(2),incomeRS.getString(3),incomeRS.getString(4)));
			}
			
			JinhuoTable.setItems(jinhuoList);
			System.out.println(jinhuoList);
			
			ResultSet incomeRS3 = LoginController.stmt.executeQuery("select * " + 
					" from chuhuodan ");
			while(incomeRS3.next()) {
				System.out.println(incomeRS3.getString(1)+ " " + incomeRS3.getString(2)+" " + incomeRS3.getString(3)+" " + incomeRS3.getString(4)+" " + incomeRS3.getString(5));
				chuhuoList.add(new LackTable(incomeRS3.getString(1),incomeRS3.getString(2),incomeRS3.getString(3),
						incomeRS3.getString(4),
						incomeRS3.getString(5)));
			}
			
			ChuhuoTable.setItems(chuhuoList);
			System.out.println(chuhuoList);
			
			ResultSet incomeRS2 = LoginController.stmt.executeQuery("select * " + 
					" from lacked ");
			while(incomeRS2.next()) {
				System.out.println(incomeRS2.getString(1)+ " " + incomeRS2.getString(2)+" " + incomeRS2.getString(3)+" " + incomeRS2.getString(4)+" " + incomeRS2.getString(5));
				lackList.add(new LackTable(incomeRS2.getString(1),incomeRS2.getString(2),incomeRS2.getString(3),
						incomeRS2.getString(4),
						incomeRS2.getString(5)));
			}
			
			LackTable.setItems(lackList);
			System.out.println(lackList);
			
			ResultSet incomeRS1 = LoginController.stmt.executeQuery("select * " + 
					" from is_shenhedcaigoudan ");
			while(incomeRS1.next()) {
				System.out.println(incomeRS1.getString(1)+ " " + incomeRS1.getString(2)+" " + incomeRS1.getString(3)+" " + incomeRS1.getString(4));
				sjinhuoList.add(new JinhuoTable(incomeRS1.getString(1),incomeRS1.getString(2),incomeRS1.getString(3),incomeRS1.getString(4)));
			}
			
			SjinhuoTable.setItems(sjinhuoList);
			System.out.println(sjinhuoList);
			
			ResultSet incomeRS4 = LoginController.stmt.executeQuery("select * " + 
					" from is_shenhedchuhuodan ");
			while(incomeRS4.next()) {
				System.out.println(incomeRS4.getString(1)+ " " + incomeRS4.getString(2)+" " + incomeRS4.getString(3)+" " + incomeRS4.getString(4)+" " + incomeRS4.getString(5));
				schuhuoList.add(new LackTable(incomeRS4.getString(1),incomeRS4.getString(2),incomeRS4.getString(3),
						incomeRS4.getString(4),
						incomeRS4.getString(5).contentEquals("0")?"��":"��"));
			}
			
			SchuhuoTable.setItems(schuhuoList);
			System.out.println(schuhuoList);
			
			ResultSet incomeRS5 = LoginController.stmt.executeQuery("select * " + 
					" from chuli_lacked ");
			while(incomeRS5.next()) {
				System.out.println(incomeRS5.getString(1)+ " " + incomeRS5.getString(2)+" " + incomeRS5.getString(3)+" " + incomeRS5.getString(4)
				+" " + incomeRS5.getString(5)+" " + incomeRS5.getString(6));
				clackList.add(new IncomeTable(incomeRS5.getString(1),incomeRS5.getString(2),incomeRS5.getString(3),
						incomeRS5.getString(4),
						incomeRS5.getString(5),
						incomeRS5.getString(6)));
			}
			
			ClackTable.setItems(clackList);
			System.out.println(clackList);
			initcombobox();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void initcombobox() {
		try {
			ResultSet deprs,delrs;
			if(jinFull!=null) jinFull.clear();
			if(chuFull!=null) chuFull.clear();
			deprs = LoginController.stmt.executeQuery("SELECT * FROM caigoudan");
			while(deprs.next()){
				jinFull.add(deprs.getString(1));
				
			}
			delrs = LoginController.stmt.executeQuery("SELECT * FROM chuhuodan");
			while(delrs.next()){
				chuFull.add(delrs.getString(1));
				
			}
			
			com_jinhuo.getItems().removeAll();
			com_chuhuo.getItems().removeAll();
			com_jinhuo.getItems().setAll(jinFull);
			com_chuhuo.getItems().setAll(chuFull);
			
			if(!(com_jinhuo.getEditor().getText().isEmpty())) com_jinhuo.getEditor().clear();
			if(!(com_chuhuo.getEditor().getText().isEmpty())) com_chuhuo.getEditor().clear();
			
			//com_add.getEditor().setText("");
			//com_del.getEditor().setText("");
			//com_upd.getEditor().setText("");
			System.out.println("��ˢ�£�");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean isNumeric(String str){//�ж�������ַ����Ƿ�ȫ��������
		  for (int i = 0; i < str.length(); i++){
		  System.out.println(str.charAt(i));
		  if (!Character.isDigit(str.charAt(i))){
		    return false;
		  }
		 }
		 return true;
	}
    /**
    *
    * @param rs ����ѯ���
    * @return������rs��������м�¼����
    * @throws java.sql.SQLException
    */
   static public int rowsInResultSet(ResultSet rs) throws java.sql.SQLException{
       if(rs == null)
           return 0;
       rs.last();
       int row = rs.getRow();
       rs.beforeFirst();
       System.out.println("5�ź�����������"+row+"����¼");
       return row;
   }
}
