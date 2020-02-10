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
		
		btn_exit.setOnAction(new EventHandler<ActionEvent>() {//退出，进入到登录界面
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
				Main.CurStage.setTitle("仓库管理系统");
				Main.CurStage.show();
			}
			
			
		});
		
		btn_fresh.setOnAction(new EventHandler<ActionEvent>() {//刷新
			public void handle(ActionEvent event) {
				fresh();
			}
			
		});
		btn_jin.setOnAction(new EventHandler<ActionEvent>() {//刷新
			public void handle(ActionEvent event) {
				String addnumString = com_jinhuo.getEditor().getText();
				if(addnumString.length()>6) {//输入字符串过长
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"输入字符串过长！", "警告", JOptionPane.WARNING_MESSAGE);
					com_jinhuo.getEditor().clear();
	    			return;
				}
				if(!isNumeric(addnumString)){//输入字符串不都是数字
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"只能输入数字！", "警告", JOptionPane.WARNING_MESSAGE);
					com_jinhuo.getEditor().clear();
	    			return;
				}
				if(addnumString.isEmpty()){//还没有输入编号
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"信息输入不完整！", "警告", JOptionPane.WARNING_MESSAGE);
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
						if(addnumString.equals(jinhuors.getString(1))) {//找到
							
							String goodsNamesString  = jinhuors.getString(2);
							String caigounumsString  = jinhuors.getString(3);
							String proString = jinhuors.getString(4);
							SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String shenehtimeString  = df.format(new Date());
							
							//向已审核的进货单中插入一条记录
							LoginController.stmt.executeUpdate("insert into is_shenhedcaigoudan values ('" + addnumString+ "','"+ goodsNamesString+ "','" 
									+caigounumsString+ "','" + shenehtimeString+ "')"  );
							
							//进货，则将仓库内对应的货物库存数量加上入库数量
							int addnum = Integer.valueOf(caigounumsString);//进货的数量
							ResultSet huowu = LoginController.stmt.executeQuery("select * " + 
									" from goodsinfo "+ " where goodsID = '"  + addnumString + "'" );
							huowu.next();
							if(rowsInResultSet(huowu)==0) {//没有这个货物，则新增这个货品信息
								LoginController.stmt.executeUpdate("insert into goodsinfo  values ('" + addnumString+ "','"+ goodsNamesString+ "','"
										+ caigounumsString + "','"
										+proString+  "')"  );
								//删除该进货单的记录
								LoginController.stmt.executeUpdate("delete from  caigoudan where goodsID = '"  + addnumString + "'");
								JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					         			"进货审核成功！\n货品编号为"+addnumString, "提示", JOptionPane.INFORMATION_MESSAGE);
								fresh();
								return;
							}
							ResultSet huowu1 = LoginController.stmt.executeQuery("select * " + 
									" from goodsinfo "+ " where goodsID = '"  + addnumString + "'" );
							huowu1.next();
							int prenum = Integer.valueOf(huowu1.getString(3));//之前的库存量
							String aftString = Integer.toString(addnum+prenum);//新的库存量
							LoginController.stmt.executeUpdate("update  goodsinfo set storenum =  '" + aftString+ 
									"' where goodsID = '"  + addnumString + "'");//更新库存
							
							//再看是否缺货
							ResultSet quehuo = LoginController.stmt.executeQuery("select * " + 
									" from lacked "+ " where goodsID = '"  + addnumString + "'" );
							
							if(rowsInResultSet(quehuo)!=0) {//该商品缺货，用进货数量补上
								quehuo.next();
								int lacknum = Integer.valueOf(quehuo.getString(3));
								if(lacknum<=addnum+prenum)//缺货量可以补足
								{
									//更新库存
									String aftString1 = Integer.toString(addnum+prenum-lacknum);//新的库存量
									LoginController.stmt.executeUpdate("update  goodsinfo set storenum =  '" + aftString1+ 
											"' where goodsID = '"  + addnumString + "'");//更新库存
									
									//删除该缺货单
									LoginController.stmt.executeUpdate("delete from  lacked where goodsID = '"  + addnumString + "'");
									
									//已经处理该缺货单，在已处理的缺货单上加一条记录
									LoginController.stmt.executeUpdate("insert into chuli_lacked  values ('" + addnumString+ "','"+ goodsNamesString+ "','"
											+ proString + "','"
											+Integer.toString(lacknum)+ "','" + Integer.toString(addnum+prenum) + "','" + shenehtimeString +  "')"  );
									
									
								}
								else {
									//更新库存
									LoginController.stmt.executeUpdate("update  goodsinfo set storenum =  '0' "
											+ "where goodsID = '"  + addnumString + "'");//更新库存
									//缺货单缺货数量更新
									LoginController.stmt.executeUpdate("update  lacked set lacknum =  '"+ Integer.toString(lacknum-addnum-prenum)
											+ "' where goodsID = '"  + addnumString + "'");//更新缺货单
									//已经处理该缺货单，在已处理的缺货单上加一条记录
									LoginController.stmt.executeUpdate("insert into chuli_lacked  values ('" + addnumString+ "','"+ goodsNamesString+ "','"
											+ proString + "','"
											+Integer.toString(lacknum)+ "','" + Integer.toString(addnum+prenum) + "','" + shenehtimeString + "')"  );
									
								}
								
							}
							
							
							//删除该进货单的记录
							LoginController.stmt.executeUpdate("delete from  caigoudan where goodsID = '"  + addnumString + "'");
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				         			"进货审核成功！\n货品编号为"+addnumString, "提示", JOptionPane.INFORMATION_MESSAGE);
							com_jinhuo.getEditor().setText("");
						    System.out.println("删除成功！");
							fresh();
							return;
						}
						
						
					}
					/*SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String shenehtimeString  = df.format(new Date());
					
					//没找到
					ResultSet delrs = LoginController.stmt.executeQuery("SELECT * FROM caigoudan where goodsID = '" +addnumString +"'");
					delrs.next();
					
					//进货，库存增加
					LoginController.stmt.executeUpdate("insert into goodsinfo  values ('" + addnumString+ "','"+ delrs.getString(2)+ "','"
							+ delrs.getString(3) + "','"
							+delrs.getString(4)+  "')"  );
					
					//向已审核的进货单中插入一条记录
					LoginController.stmt.executeUpdate("insert into is_shenhedcaigoudan values ('" + addnumString+ "','"+ delrs.getString(2)+ "','" 
							+delrs.getString(3)+ "','" + shenehtimeString+ "')"  );
					//删除该进货单的记录
					LoginController.stmt.executeUpdate("delete from  caigoudan where goodsID = '"  + addnumString + "'");
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
		         			"进货审核成功！\n货品编号为"+addnumString, "提示", JOptionPane.INFORMATION_MESSAGE);*/
					//没找到
					
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"出货单中不存在该编号！", "警告", JOptionPane.WARNING_MESSAGE);
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
		
		btn_chu.setOnAction(new EventHandler<ActionEvent>() {//审核出货单
			public void handle(ActionEvent event) {
				String addnumString = com_chuhuo.getEditor().getText();
				if(addnumString.length()>6) {//输入字符串过长
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"输入字符串过长！", "警告", JOptionPane.WARNING_MESSAGE);
					com_chuhuo.getEditor().clear();
	    			return;
				}
				if(!isNumeric(addnumString)){//输入字符串不都是数字
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"只能输入数字！", "警告", JOptionPane.WARNING_MESSAGE);
					com_chuhuo.getEditor().clear();
	    			return;
				}
				if(addnumString.isEmpty()){//还没有输入编号
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"信息输入不完整！", "警告", JOptionPane.WARNING_MESSAGE);
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
						if(addnumString.equals(jinhuors.getString(1))) {//找到
							
							String goodsNamesString  = jinhuors.getString(2);//商品名称
							String chuhuonumsString  = jinhuors.getString(3);//商品出货量
							String proString = jinhuors.getString(4);//商品产地
							
							SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String shenehtimeString  = df.format(new Date());//审核时间,也是缺货登记时间
							
							
							ResultSet huowu = LoginController.stmt.executeQuery("select * " + 
									" from goodsinfo "+ " where goodsID = '"  + addnumString + "'" );
							huowu.next();
							int prenum = Integer.valueOf(huowu.getString(3));//之前的库存量
							int chuhuonum = Integer.valueOf(chuhuonumsString);//进货的数量
							
							if(prenum>=chuhuonum) {//库存足
								//更新库存
								LoginController.stmt.executeUpdate("update  goodsinfo set storenum =  ' "+ Integer.toString(prenum-chuhuonum)
										+ "' where goodsID = '"  + addnumString + "'");//更新库存
								//向已审核的出货单中插入一条记录
								LoginController.stmt.executeUpdate("insert into is_shenhedchuhuodan values ('" + addnumString+ "','"+ goodsNamesString+ "','" 
										+chuhuonumsString+ "','" + shenehtimeString + "','"+ "0"+ "')"  );
								
							}
							else {//库存不足，需要缺货登记
								//更新库存
								LoginController.stmt.executeUpdate("update  goodsinfo set storenum =  '0' "
										+ "where goodsID = '"  + addnumString + "'");//更新库存
								//向已审核的出货单中插入一条记录
								LoginController.stmt.executeUpdate("insert into is_shenhedchuhuodan values ('" + addnumString+ "','"+ goodsNamesString+ "','" 
										+chuhuonumsString+ "','" + shenehtimeString + "','"+ "1"+ "')"  );
								
								//缺货单上加一条记录
								LoginController.stmt.executeUpdate("insert into lacked  values ('" + addnumString+ "','"+ goodsNamesString+ "','"
										+ Integer.toString(chuhuonum-prenum) + "','"
										+ proString + "','"+   shenehtimeString + "')"  );
								
							}
							
							
							//删除该出货单
							LoginController.stmt.executeUpdate("delete from  chuhuodan where goodsID = '"  + addnumString + "'");
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				         			"出货审核成功！\n货品编号为"+addnumString, "提示", JOptionPane.INFORMATION_MESSAGE);
							com_chuhuo.getEditor().setText("");
						
							fresh();
							return;
						}
						
					}
					
					//没找到
					
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"出货单中不存在该编号！", "警告", JOptionPane.WARNING_MESSAGE);
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
			if(huoList != null)huoList.clear();//先将之前的列表清空
			if(jinhuoList != null)jinhuoList.clear();//先将之前的列表清空
			if(chuhuoList != null)chuhuoList.clear();//先将之前的列表清空
			if(lackList != null)lackList.clear();//先将之前的列表清空
			if(sjinhuoList != null)sjinhuoList.clear();//先将之前的列表清空
			if(schuhuoList != null)schuhuoList.clear();//先将之前的列表清空
			if(clackList != null)clackList.clear();//先将之前的列表清空
			
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
						incomeRS4.getString(5).contentEquals("0")?"否":"是"));
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
			System.out.println("已刷新！");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean isNumeric(String str){//判断输入的字符串是否全部是数字
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
    * @param rs ：查询结果
    * @return：返回rs这个集合中记录条数
    * @throws java.sql.SQLException
    */
   static public int rowsInResultSet(ResultSet rs) throws java.sql.SQLException{
       if(rs == null)
           return 0;
       rs.last();
       int row = rs.getRow();
       rs.beforeFirst();
       System.out.println("5号函数：返回了"+row+"条记录");
       return row;
   }
}
