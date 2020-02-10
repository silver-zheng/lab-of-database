package application;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class caigouController {
	@FXML
	private ComboBox<String> com_add,com_del,com_upd;
	@FXML
	private TextField text11;
	@FXML
	private TextField text12;
	@FXML
	private TextField text13;
	@FXML
	private TextField text21;
	@FXML
	private TextField text22;
	@FXML
	private TextField text23;
	@FXML
	private TextField text31;
	@FXML
	private TextField text32;
	@FXML
	private TextField text33;
	@FXML
	private Button btn_fresh;
	@FXML
	private Button btn_exit;
	@FXML
	private Button btn_add;
	@FXML
	private Button btn_del;
	@FXML
	private Button btn_upd;
	@FXML
	private TableView<JinhuoTable> JinhuoTable;
	@FXML
	private TableColumn<JinhuoTable,String>col_ID;
	@FXML
	private TableColumn<JinhuoTable,String> col_name;
	@FXML
	private TableColumn<JinhuoTable,String> col_num;
	@FXML
	private TableColumn <JinhuoTable,String>col_pro;
	
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
	ObservableList<JinhuoTable> jinhuoList;
	ObservableList<LackTable> lackList;
	private ArrayList<String> addFull = new ArrayList<String>();
	private ArrayList<String> delFull = new ArrayList<String>();
	
	
	public void initialize() {
		text11.setPromptText("货品名称");
		text21.setPromptText("货品名称");
		text31.setPromptText("货品名称");
		text12.setPromptText("进货数量");
		text22.setPromptText("进货数量");
		text32.setPromptText("进货数量");
		text13.setPromptText("生产地");
		text23.setPromptText("生产地");
		text33.setPromptText("生产地");
		
		col_ID.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_regid"));
		col_name.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_patname"));
		col_num.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_regtime"));
		col_pro.setCellValueFactory(new PropertyValueFactory<JinhuoTable,String>("col_regtype"));
		jinhuoList = FXCollections.observableArrayList();
		
		lack_ID.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_offname"));
		lack_name.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_docnum"));
		lack_num.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_docname"));
		lack_pro.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_regtype2"));
		lack_time.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_regcount"));
		lackList =  FXCollections.observableArrayList();
		fresh();
		initcombobox();
		
		com_add.getSelectionModel().selectedIndexProperty().addListener(e->show1());//
		com_del.getSelectionModel().selectedIndexProperty().addListener(e->show2());//
		com_upd.getSelectionModel().selectedIndexProperty().addListener(e->show3());//
		
		// TODO Auto-generated method stub
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
		
		btn_add.setOnAction(new EventHandler<ActionEvent>() {//增加
			public void handle(ActionEvent event) {
				
				String addnumString = com_add.getEditor().getText();
				if(addnumString.length()>6) {//输入字符串过长
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"输入字符串过长！", "警告", JOptionPane.WARNING_MESSAGE);
					com_add.getEditor().clear();
	    			return;
				}
				if(!isNumeric(addnumString)){//输入字符串不都是数字
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"只能输入数字！", "警告", JOptionPane.WARNING_MESSAGE);
					com_add.getEditor().clear();
	    			return;
				}
				if(addnumString.isEmpty()){//还没有输入编号
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"信息输入不完整！", "警告", JOptionPane.WARNING_MESSAGE);
					com_add.getEditor().clear();
	    			return;
				}
				
				int needzero = 6 - addnumString.length();
				String addString = "";
				for(int i = 0;i < needzero;i++) {
					addString = addString + "0";
				}
				addnumString = addString + addnumString;
				
				//先在进货单里面找，看是不是已经在进货单里面
				try {
					ResultSet jinhuors = LoginController.stmt.executeQuery("select * " + 
							" from caigoudan ");
					while(jinhuors.next()) {
						if(addnumString.equals(jinhuors.getString(1))) {//找到
							
							if(text11.getText().isEmpty()|| text12.getText().isEmpty()||text13.getText().isEmpty()) {
								JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				              			"信息输入不完整！", "警告", JOptionPane.WARNING_MESSAGE);
								com_add.getEditor().clear();
				    			return;
							}
							int addnum = Integer.valueOf(text12.getText());
							int prenum = Integer.valueOf(jinhuors.getString(3));
							String aftString = Integer.toString(addnum+prenum);
							LoginController.stmt.executeUpdate("update  caigoudan set caigounum =  '" + aftString+ 
									"' where goodsID = '"  + addnumString + "'");
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				         			"进货单增加成功！\n货品编号为"+addnumString, "提示", JOptionPane.INFORMATION_MESSAGE);
							com_add.getEditor().setText("");
							text11.setText("");
							text12.setText("");
							text13.setText("");
							text11.setPromptText("货品名称");
							text21.setPromptText("货品名称");
							text31.setPromptText("货品名称");
							text12.setPromptText("进货数量");
							text22.setPromptText("进货数量");
							text32.setPromptText("进货数量");
							text13.setPromptText("生产地");
							text23.setPromptText("生产地");
							text33.setPromptText("生产地");
							fresh();
							return;
						}
						
					}
					
					//没找到
					if(text11.getText().isEmpty()|| text12.getText().isEmpty()||text13.getText().isEmpty()) {
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
		              			"信息输入不完整！", "警告", JOptionPane.WARNING_MESSAGE);
						com_add.getEditor().clear();
		    			return;
					}
					LoginController.stmt.executeUpdate("insert into caigoudan values ('" + addnumString+ "','"+ text11.getText()+ "','" + text12.getText()+
							"','" + text13.getText()+ "')"  );
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
		         			"进货单增加成功！\n货品编号为"+addnumString, "提示", JOptionPane.INFORMATION_MESSAGE);
					fresh();
					com_add.getEditor().setText("");
					text11.setText("");
					text12.setText("");
					text13.setText("");
					text11.setPromptText("货品名称");
					text21.setPromptText("货品名称");
					text31.setPromptText("货品名称");
					text12.setPromptText("进货数量");
					text22.setPromptText("进货数量");
					text32.setPromptText("进货数量");
					text13.setPromptText("生产地");
					text23.setPromptText("生产地");
					text33.setPromptText("生产地");
					return;
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
					
				
				fresh();
			}
			
		});
		btn_del.setOnAction(new EventHandler<ActionEvent>() {//删除
			public void handle(ActionEvent event) {
				String addnumString = com_del.getEditor().getText();
				if(addnumString.length()>6) {//输入字符串过长
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"输入字符串过长！", "警告", JOptionPane.WARNING_MESSAGE);
					com_del.getEditor().clear();
	    			return;
				}
				if(!isNumeric(addnumString)){//输入字符串不都是数字
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"只能输入数字！", "警告", JOptionPane.WARNING_MESSAGE);
					com_del.getEditor().clear();
	    			return;
				}
				
				if(addnumString.isEmpty()){//还没有输入编号
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"信息输入不完整！", "警告", JOptionPane.WARNING_MESSAGE);
					com_del.getEditor().clear();
	    			return;
				}
				int needzero = 6 - addnumString.length();
				String addString = "";
				for(int i = 0;i < needzero;i++) {
					addString = addString + "0";
				}
				addnumString = addString + addnumString;
				System.out.println(addnumString);
				//先在进货单里面找，看是不是已经在进货单里面
				try {
					ResultSet jinhuors = LoginController.stmt.executeQuery("select * " + 
							" from caigoudan ");
					while(jinhuors.next()) {
						if(addnumString.equals(jinhuors.getString(1))) {//找到
							
							if(text21.getText().isEmpty()|| text22.getText().isEmpty()||text23.getText().isEmpty()) {
								JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				              			"信息输入不完整！", "警告", JOptionPane.WARNING_MESSAGE);
								com_del.getEditor().clear();
				    			return;
							}
							LoginController.stmt.executeUpdate("delete from  caigoudan where goodsID = '"  + addnumString + "'");
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				         			"进货单删除成功！\n货品编号为"+addnumString, "提示", JOptionPane.INFORMATION_MESSAGE);
							
							com_del.getEditor().setText("");
							text21.setText("");
							text22.setText("");
							text23.setText("");
							text11.setPromptText("货品名称");
							text21.setPromptText("货品名称");
							text31.setPromptText("货品名称");
							text12.setPromptText("进货数量");
							text22.setPromptText("进货数量");
							text32.setPromptText("进货数量");
							text13.setPromptText("生产地");
							text23.setPromptText("生产地");
							text33.setPromptText("生产地");
							fresh();
							return;
						}
						
					}
					
					//没找到
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"进货单中不存在该编号！", "警告", JOptionPane.WARNING_MESSAGE);
					
					fresh();
					com_del.getEditor().setText("");
					text21.setText("");
					text22.setText("");
					text23.setText("");
					text11.setPromptText("货品名称");
					text21.setPromptText("货品名称");
					text31.setPromptText("货品名称");
					text12.setPromptText("进货数量");
					text22.setPromptText("进货数量");
					text32.setPromptText("进货数量");
					text13.setPromptText("生产地");
					text23.setPromptText("生产地");
					text33.setPromptText("生产地");
					return;
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				fresh();
			}
			
		});
		
		btn_upd.setOnAction(new EventHandler<ActionEvent>() {//修改
			public void handle(ActionEvent event) {
				
				String addnumString = com_upd.getEditor().getText();
				if(addnumString.length()>6) {//输入字符串过长
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"输入字符串过长！", "警告", JOptionPane.WARNING_MESSAGE);
					com_upd.getEditor().clear();
	    			return;
				}
				if(!isNumeric(addnumString)){//输入字符串不都是数字
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"只能输入数字！", "警告", JOptionPane.WARNING_MESSAGE);
					com_upd.getEditor().clear();
	    			return;
				}
				if(addnumString.isEmpty()){//还没有输入编号
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"信息输入不完整！", "警告", JOptionPane.WARNING_MESSAGE);
					com_upd.getEditor().clear();
	    			return;
				}
				int needzero = 6 - addnumString.length();
				String addString = "";
				for(int i = 0;i < needzero;i++) {
					addString = addString + "0";
				}
				addnumString = addString + addnumString;
				
				//先在进货单里面找，看是不是已经在进货单里面
				try {
					ResultSet jinhuors = LoginController.stmt.executeQuery("select * " + 
							" from caigoudan ");
					while(jinhuors.next()) {
						if(addnumString.equals(jinhuors.getString(1))) {//找到
							
							if(text31.getText().isEmpty()|| text32.getText().isEmpty()||text33.getText().isEmpty()) {
								JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				              			"信息输入不完整！", "警告", JOptionPane.WARNING_MESSAGE);
								com_add.getEditor().clear();
				    			return;
							}
							String nameString = text31.getText();
							String numString = text32.getText();
							String proString = text33.getText();
							
							
							LoginController.stmt.executeUpdate("update  caigoudan set goodsname =  '" + nameString+ 
									"',caigounum = '" +numString+
									"',producted = '"+ proString+
									"' where goodsID = '"  + addnumString + "'");
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				         			"进货单修改成功！\n货品编号为"+addnumString, "提示", JOptionPane.INFORMATION_MESSAGE);
							com_upd.getEditor().setText("");
							text31.setText("");
							text32.setText("");
							text33.setText("");
							text11.setPromptText("货品名称");
							text21.setPromptText("货品名称");
							text31.setPromptText("货品名称");
							text12.setPromptText("进货数量");
							text22.setPromptText("进货数量");
							text32.setPromptText("进货数量");
							text13.setPromptText("生产地");
							text23.setPromptText("生产地");
							text33.setPromptText("生产地");
							fresh();
							return;
						}
						
					}
					
					//没找到
					
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"进货单中不存在该编号！", "警告", JOptionPane.WARNING_MESSAGE);
					fresh();
					com_add.getEditor().setText("");
					text31.setText("");
					text32.setText("");
					text33.setText("");
					text11.setPromptText("货品名称");
					text21.setPromptText("货品名称");
					text31.setPromptText("货品名称");
					text12.setPromptText("进货数量");
					text22.setPromptText("进货数量");
					text32.setPromptText("进货数量");
					text13.setPromptText("生产地");
					text23.setPromptText("生产地");
					text33.setPromptText("生产地");
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
			
			if(jinhuoList != null)jinhuoList.clear();//先将之前的列表清空
			if(lackList != null)lackList.clear();//先将之前的列表清空
			ResultSet patientRS = LoginController.stmt.executeQuery("select * " + 
					" from caigoudan ");
			while(patientRS.next()) {
				System.out.println(patientRS.getString(1)+ " " + patientRS.getString(2)+" " + patientRS.getString(3)+" " + patientRS.getString(4));
				jinhuoList.add(new JinhuoTable(patientRS.getString(1),patientRS.getString(2),patientRS.getString(3),patientRS.getString(4)));
			}
			JinhuoTable.setItems(jinhuoList);
			System.out.println(jinhuoList);
			
			ResultSet incomeRS = LoginController.stmt.executeQuery("select * " + 
					" from lacked ");
			while(incomeRS.next()) {
				System.out.println(incomeRS.getString(1)+ " " + incomeRS.getString(2)+" " + incomeRS.getString(3)+" " + incomeRS.getString(4)+" " + incomeRS.getString(5));
				lackList.add(new LackTable(incomeRS.getString(1),incomeRS.getString(2),incomeRS.getString(3),
						incomeRS.getString(4),
						incomeRS.getString(5)));
			}
			
			LackTable.setItems(lackList);
			System.out.println(lackList);
			initcombobox();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void initcombobox() {
		try {
			ResultSet deprs,delrs;
			if(addFull!=null) addFull.clear();
			if(delFull!=null) delFull.clear();
			deprs = LoginController.stmt.executeQuery("SELECT * FROM goodsinfo");
			while(deprs.next()){
				addFull.add(deprs.getString(1));
				
			}
			delrs = LoginController.stmt.executeQuery("SELECT * FROM caigoudan");
			while(delrs.next()){
				delFull.add(delrs.getString(1));
				
			}
			
			com_add.getItems().removeAll();
			com_del.getItems().removeAll();
			com_upd.getItems().removeAll();
			com_add.getItems().setAll(addFull);
			com_del.getItems().setAll(delFull);
			com_upd.getItems().setAll(delFull);
			if(!(com_add.getEditor().getText().isEmpty()))com_add.getEditor().clear();
			if(!(com_del.getEditor().getText().isEmpty()))com_del.getEditor().clear();
			if(!(com_upd.getEditor().getText().isEmpty()))com_upd.getEditor().clear();
			//com_add.getEditor().setText("");
			//com_del.getEditor().setText("");
			//com_upd.getEditor().setText("");
			System.out.println("已刷新！");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void show1( ) {
		String addnumString = com_add.getEditor().getText();
		if(addnumString.length()>6) {//输入字符串过长
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
          			"输入字符串过长！", "警告", JOptionPane.WARNING_MESSAGE);
			com_add.getEditor().clear();
			return;
		}
		if(!isNumeric(addnumString)){//输入字符串不都是数字
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
          			"输入字符需都为数字！", "警告", JOptionPane.WARNING_MESSAGE);
			com_add.getEditor().clear();
			return;
		}
		//String fmtString = String.format("%06s",addnumString);
		
		int needzero = 6 - addnumString.length();
		String addString = "";
		for(int i = 0;i < needzero;i++) {
			addString = addString + "0";
		}
		addnumString = addString + addnumString;
		try {
			ResultSet jinhuors = LoginController.stmt.executeQuery("select * " + 
					" from goodsinfo ");
			while(jinhuors.next()) {
				if(addnumString.equals(jinhuors.getString(1))) {//找到
					text11.setText(jinhuors.getString(2));
					
					text13.setText(jinhuors.getString(4));
					
					return;
				}
			}
			
			//没找到
			text11.setPromptText("货品名称");
			text21.setPromptText("货品名称");
			text31.setPromptText("货品名称");
			text12.setPromptText("进货数量");
			text22.setPromptText("进货数量");
			text32.setPromptText("进货数量");
			text13.setPromptText("生产地");
			text23.setPromptText("生产地");
			text33.setPromptText("生产地");
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void show2() {
		String addnumString = com_del.getEditor().getText();
		if(addnumString.length()>6) {//输入字符串过长
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
          			"输入字符串过长！", "警告", JOptionPane.WARNING_MESSAGE);
			com_del.getEditor().clear();
			return;
		}
		if(!isNumeric(addnumString)){//输入字符串不都是数字
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
          			"只能输入数字！", "警告", JOptionPane.WARNING_MESSAGE);
			com_del.getEditor().clear();
			return;
		}
		//String fmtString = String.format("%06s",addnumString);
		
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
					text21.setText(jinhuors.getString(2));
					text22.setText(jinhuors.getString(3));
					text23.setText(jinhuors.getString(4));
					
					return;
				}
			}
			
			//没找到
			text11.setPromptText("货品名称");
			text21.setPromptText("货品名称");
			text31.setPromptText("货品名称");
			text12.setPromptText("进货数量");
			text22.setPromptText("进货数量");
			text32.setPromptText("进货数量");
			text13.setPromptText("生产地");
			text23.setPromptText("生产地");
			text33.setPromptText("生产地");
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void show3() {
		String addnumString = com_upd.getEditor().getText();
		if(addnumString.length()>6) {//输入字符串过长
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
          			"输入字符串过长！", "警告", JOptionPane.WARNING_MESSAGE);
			com_upd.getEditor().clear();
			return;
		}
		if(!isNumeric(addnumString)){//输入字符串不都是数字
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
          			"输入字符需都为数字！", "警告", JOptionPane.WARNING_MESSAGE);
			com_upd.getEditor().clear();
			return;
			
		}
		//String fmtString = String.format("%06s",addnumString);
		
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
					text31.setText(jinhuors.getString(2));
					text32.setText(jinhuors.getString(3));
					text33.setText(jinhuors.getString(4));
					
					return;
				}
			}
			
			//没找到
			text11.setPromptText("货品名称");
			text21.setPromptText("货品名称");
			text31.setPromptText("货品名称");
			text12.setPromptText("进货数量");
			text22.setPromptText("进货数量");
			text32.setPromptText("进货数量");
			text13.setPromptText("生产地");
			text23.setPromptText("生产地");
			text33.setPromptText("生产地");
			
			
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
}
