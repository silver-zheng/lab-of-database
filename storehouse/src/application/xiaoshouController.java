package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class xiaoshouController {
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
	private TextField text14;
	@FXML
	private TextField text24;
	@FXML
	private TextField text34;
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
	private TableView<LackTable> ChuhuoTable;
	@FXML
	private TableColumn<LackTable,String>col_ID;
	@FXML
	private TableColumn<LackTable,String> col_name;
	@FXML
	private TableColumn<LackTable,String> col_num;
	@FXML
	private TableColumn <LackTable,String>col_pro;
	@FXML
	private TableColumn <LackTable,String>col_to;
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
	ObservableList<LackTable> chuhuoList;
	ObservableList<LackTable> lackList;
	private ArrayList<String> addFull = new ArrayList<String>();
	private ArrayList<String> delFull = new ArrayList<String>();
	
	
	
	public void initialize() {
		text11.setPromptText("��Ʒ����");
		text21.setPromptText("��Ʒ����");
		text31.setPromptText("��Ʒ����");
		text12.setPromptText("��������");
		text22.setPromptText("��������");
		text32.setPromptText("��������");
		text13.setPromptText("������");
		text23.setPromptText("������");
		text33.setPromptText("������");
		text14.setPromptText("������");
		text24.setPromptText("������");
		text34.setPromptText("������");
		
		col_ID.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_offname"));
		col_name.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_docnum"));
		col_num.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_docname"));
		col_pro.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_regtype2"));
		col_to.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_regcount"));
		chuhuoList = FXCollections.observableArrayList();
		
		lack_ID.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_offname"));
		lack_name.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_docnum"));
		lack_num.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_docname"));
		lack_pro.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_regtype2"));
		lack_time.setCellValueFactory(new PropertyValueFactory<LackTable,String>("col_regcount"));
		lackList =  FXCollections.observableArrayList();
		
		initcombobox();
		fresh();
		
		com_add.getSelectionModel().selectedIndexProperty().addListener(e->show1());//
		com_del.getSelectionModel().selectedIndexProperty().addListener(e->show2());//
		com_upd.getSelectionModel().selectedIndexProperty().addListener(e->show3());//
		
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
		btn_add.setOnAction(new EventHandler<ActionEvent>() {//����
			public void handle(ActionEvent event) {
				String addnumString = com_add.getEditor().getText();
				if(addnumString.length()>6) {//�����ַ�������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"�����ַ���������", "����", JOptionPane.WARNING_MESSAGE);
					com_add.getEditor().clear();
	    			return;
				}
				if(!isNumeric(addnumString)){//�����ַ�������������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"ֻ���������֣�", "����", JOptionPane.WARNING_MESSAGE);
					com_add.getEditor().clear();
	    			return;
				}
				if(addnumString.isEmpty()){//��û��������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"��Ϣ���벻������", "����", JOptionPane.WARNING_MESSAGE);
					com_add.getEditor().clear();
	    			return;
				}
				
				int needzero = 6 - addnumString.length();
				String addString = "";
				for(int i = 0;i < needzero;i++) {
					addString = addString + "0";
				}
				addnumString = addString + addnumString;
				
				//���ڳ����������ң����ǲ����Ѿ��ڳ���������
				try {
					ResultSet jinhuors = LoginController.stmt.executeQuery("select * " + 
							" from chuhuodan ");
					while(jinhuors.next()) {
						if(addnumString.equals(jinhuors.getString(1))) {//�ҵ�
							
							if(text11.getText().isEmpty()|| text12.getText().isEmpty()||text13.getText().isEmpty()||text14.getText().isEmpty()) {
								JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				              			"��Ϣ���벻������", "����", JOptionPane.WARNING_MESSAGE);
								com_add.getEditor().clear();
				    			return;
							}
							int addnum = Integer.valueOf(text12.getText());
							int prenum = Integer.valueOf(jinhuors.getString(3));
							String aftString = Integer.toString(addnum+prenum);
							LoginController.stmt.executeUpdate("update  chuhuodan set chuhuonum =  '" + aftString+ 
									"' where goodsID = '"  + addnumString + "'");
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				         			"���������ӳɹ���\n��Ʒ���Ϊ"+addnumString, "��ʾ", JOptionPane.INFORMATION_MESSAGE);
							com_add.getEditor().setText("");
							text11.setText("");
							text12.setText("");
							text13.setText("");
							text14.setText("");
							text11.setPromptText("��Ʒ����");
							text21.setPromptText("��Ʒ����");
							text31.setPromptText("��Ʒ����");
							text12.setPromptText("��������");
							text22.setPromptText("��������");
							text32.setPromptText("��������");
							text13.setPromptText("������");
							text23.setPromptText("������");
							text33.setPromptText("������");
							text14.setPromptText("������");
							text24.setPromptText("������");
							text34.setPromptText("������");
							fresh();
							return;
						}
						
					}
					
					//û�ҵ�
					if(text11.getText().isEmpty()|| text12.getText().isEmpty()||text13.getText().isEmpty()||text14.getText().isEmpty()) {
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
		              			"��Ϣ���벻������", "����", JOptionPane.WARNING_MESSAGE);
						com_add.getEditor().clear();
		    			return;
					}
					LoginController.stmt.executeUpdate("insert into chuhuodan values ('" + addnumString+ "','"+ text11.getText()+ "','" + text12.getText()+
							"','" + text13.getText()+ "','" + text14.getText()+ "')"  );
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
		         			"���������ӳɹ���\n��Ʒ���Ϊ"+addnumString, "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					fresh();
					com_add.getEditor().setText("");
					text11.setText("");
					text12.setText("");
					text13.setText("");
					text14.setText("");
					text11.setPromptText("��Ʒ����");
					text21.setPromptText("��Ʒ����");
					text31.setPromptText("��Ʒ����");
					text12.setPromptText("��������");
					text22.setPromptText("��������");
					text32.setPromptText("��������");
					text13.setPromptText("������");
					text23.setPromptText("������");
					text33.setPromptText("������");
					text14.setPromptText("������");
					text24.setPromptText("������");
					text34.setPromptText("������");
					return;
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		});
		btn_del.setOnAction(new EventHandler<ActionEvent>() {//ɾ��
			public void handle(ActionEvent event) {
				String addnumString = com_del.getEditor().getText();
				if(addnumString.length()>6) {//�����ַ�������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"�����ַ���������", "����", JOptionPane.WARNING_MESSAGE);
					com_del.getEditor().clear();
	    			return;
				}
				if(!isNumeric(addnumString)){//�����ַ�������������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"ֻ���������֣�", "����", JOptionPane.WARNING_MESSAGE);
					com_del.getEditor().clear();
	    			return;
				}
				
				if(addnumString.isEmpty()){//��û��������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"��Ϣ���벻������", "����", JOptionPane.WARNING_MESSAGE);
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
				//���ڳ����������ң����ǲ����Ѿ��ڳ���������
				try {
					ResultSet jinhuors = LoginController.stmt.executeQuery("select * " + 
							" from chuhuodan ");
					while(jinhuors.next()) {
						if(addnumString.equals(jinhuors.getString(1))) {//�ҵ�
							
							if(text21.getText().isEmpty()|| text22.getText().isEmpty()||text23.getText().isEmpty()||text24.getText().isEmpty()) {
								JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				              			"��Ϣ���벻������", "����", JOptionPane.WARNING_MESSAGE);
								com_del.getEditor().clear();
				    			return;
							}
							LoginController.stmt.executeUpdate("delete from  chuhuodan where goodsID = '"  + addnumString + "'");
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				         			"������ɾ���ɹ���\n��Ʒ���Ϊ"+addnumString, "��ʾ", JOptionPane.INFORMATION_MESSAGE);
							
							com_del.getEditor().setText("");
							text21.setText("");
							text22.setText("");
							text23.setText("");
							text24.setText("");
							text11.setPromptText("��Ʒ����");
							text21.setPromptText("��Ʒ����");
							text31.setPromptText("��Ʒ����");
							text12.setPromptText("��������");
							text22.setPromptText("��������");
							text32.setPromptText("��������");
							text13.setPromptText("������");
							text23.setPromptText("������");
							text33.setPromptText("������");
							fresh();
							return;
						}
						
					}
					
					//û�ҵ�
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"�������в����ڸñ�ţ�", "����", JOptionPane.WARNING_MESSAGE);
					
					fresh();
					com_del.getEditor().setText("");
					text21.setText("");
					text22.setText("");
					text23.setText("");
					text24.setText("");
					text11.setPromptText("��Ʒ����");
					text21.setPromptText("��Ʒ����");
					text31.setPromptText("��Ʒ����");
					text12.setPromptText("��������");
					text22.setPromptText("��������");
					text32.setPromptText("��������");
					text13.setPromptText("������");
					text23.setPromptText("������");
					text33.setPromptText("������");
					System.out.println(addnumString);
					return;
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				fresh();
			}
			
		});
		
		btn_upd.setOnAction(new EventHandler<ActionEvent>() {//�޸�
			public void handle(ActionEvent event) {
				String addnumString = com_upd.getEditor().getText();
				if(addnumString.length()>6) {//�����ַ�������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"�����ַ���������", "����", JOptionPane.WARNING_MESSAGE);
					com_upd.getEditor().clear();
	    			return;
				}
				if(!isNumeric(addnumString)){//�����ַ�������������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"ֻ���������֣�", "����", JOptionPane.WARNING_MESSAGE);
					com_upd.getEditor().clear();
	    			return;
				}
				if(addnumString.isEmpty()){//��û��������
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"��Ϣ���벻������", "����", JOptionPane.WARNING_MESSAGE);
					com_upd.getEditor().clear();
	    			return;
				}
				int needzero = 6 - addnumString.length();
				String addString = "";
				for(int i = 0;i < needzero;i++) {
					addString = addString + "0";
				}
				addnumString = addString + addnumString;
				
				//���ڽ����������ң����ǲ����Ѿ��ڽ���������
				try {
					ResultSet jinhuors = LoginController.stmt.executeQuery("select * " + 
							" from chuhuodan ");
					while(jinhuors.next()) {
						if(addnumString.equals(jinhuors.getString(1))) {//�ҵ�
							
							if(text31.getText().isEmpty()|| text32.getText().isEmpty()||text33.getText().isEmpty()||text34.getText().isEmpty()) {
								JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				              			"��Ϣ���벻������", "����", JOptionPane.WARNING_MESSAGE);
								com_add.getEditor().clear();
				    			return;
							}
							String nameString = text31.getText();
							String numString = text32.getText();
							String proString = text33.getText();
							String toString = text34.getText();
							
							
							LoginController.stmt.executeUpdate("update  chuhuodan set goodsname =  '" + nameString+ 
									"',chuhuonum = '" +numString+
									"',producted = '"+ proString+
									"',transto = '"+ toString+
									"' where goodsID = '"  + addnumString + "'");
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
				         			"�������޸ĳɹ���\n��Ʒ���Ϊ"+addnumString, "��ʾ", JOptionPane.INFORMATION_MESSAGE);
							com_upd.getEditor().setText("");
							text31.setText("");
							text32.setText("");
							text33.setText("");
							text34.setText("");
							text11.setPromptText("��Ʒ����");
							text21.setPromptText("��Ʒ����");
							text31.setPromptText("��Ʒ����");
							text12.setPromptText("��������");
							text22.setPromptText("��������");
							text32.setPromptText("��������");
							text13.setPromptText("������");
							text23.setPromptText("������");
							text33.setPromptText("������");
							fresh();
							return;
						}
						
					}
					
					//û�ҵ�
					
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
	              			"�������в����ڸñ�ţ�", "����", JOptionPane.WARNING_MESSAGE);
					fresh();
					com_add.getEditor().setText("");
					text31.setText("");
					text32.setText("");
					text33.setText("");
					text34.setText("");
					text11.setPromptText("��Ʒ����");
					text21.setPromptText("��Ʒ����");
					text31.setPromptText("��Ʒ����");
					text12.setPromptText("��������");
					text22.setPromptText("��������");
					text32.setPromptText("��������");
					text13.setPromptText("������");
					text23.setPromptText("������");
					text33.setPromptText("������");
					return;
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				fresh();
			}
			
		});
		
	}
	public void fresh() {
		try {
			if(chuhuoList != null)chuhuoList.clear();//�Ƚ�֮ǰ���б����
			if(lackList != null)lackList.clear();//�Ƚ�֮ǰ���б����
			ResultSet patientRS = LoginController.stmt.executeQuery("select * " + 
					" from chuhuodan ");
			while(patientRS.next()) {
				System.out.println(patientRS.getString(1)+ " " + patientRS.getString(2)+" " + patientRS.getString(3)+" " + patientRS.getString(4));
				chuhuoList.add(new LackTable(patientRS.getString(1),patientRS.getString(2),patientRS.getString(3),patientRS.getString(4),patientRS.getString(5)));
			}
			ChuhuoTable.setItems(chuhuoList);
			System.out.println(chuhuoList);
			
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
			delrs = LoginController.stmt.executeQuery("SELECT * FROM chuhuodan");
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
			System.out.println("��ˢ�£�");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void show1( ) {
		String addnumString = com_add.getEditor().getText();
		if(addnumString.length()>6) {//�����ַ�������
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
          			"�����ַ���������", "����", JOptionPane.WARNING_MESSAGE);
			com_add.getEditor().clear();
			return;
		}
		if(!isNumeric(addnumString)){//�����ַ�������������
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
          			"�����ַ��趼Ϊ���֣�", "����", JOptionPane.WARNING_MESSAGE);
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
				if(addnumString.equals(jinhuors.getString(1))) {//�ҵ�
					text11.setText(jinhuors.getString(2));
					
					text13.setText(jinhuors.getString(4));
					
					
					return;
				}
			}
			
			//û�ҵ�
			text11.setPromptText("��Ʒ����");
			text21.setPromptText("��Ʒ����");
			text31.setPromptText("��Ʒ����");
			text12.setPromptText("��������");
			text22.setPromptText("��������");
			text32.setPromptText("��������");
			text13.setPromptText("������");
			text23.setPromptText("������");
			text33.setPromptText("������");
			text14.setPromptText("������");
			text24.setPromptText("������");
			text34.setPromptText("������");
			
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void show2() {
		String addnumString = com_del.getEditor().getText();
		if(addnumString.length()>6) {//�����ַ�������
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
          			"�����ַ���������", "����", JOptionPane.WARNING_MESSAGE);
			com_del.getEditor().clear();
			return;
		}
		if(!isNumeric(addnumString)){//�����ַ�������������
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
          			"ֻ���������֣�", "����", JOptionPane.WARNING_MESSAGE);
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
					" from chuhuodan ");
			while(jinhuors.next()) {
				if(addnumString.equals(jinhuors.getString(1))) {//�ҵ�
					text21.setText(jinhuors.getString(2));
					text22.setText(jinhuors.getString(3));
					text23.setText(jinhuors.getString(4));
					text24.setText(jinhuors.getString(5));
					
					return;
				}
			}
			
			//û�ҵ�
			text11.setPromptText("��Ʒ����");
			text21.setPromptText("��Ʒ����");
			text31.setPromptText("��Ʒ����");
			text12.setPromptText("��������");
			text22.setPromptText("��������");
			text32.setPromptText("��������");
			text13.setPromptText("������");
			text23.setPromptText("������");
			text33.setPromptText("������");
			text14.setPromptText("������");
			text24.setPromptText("������");
			text34.setPromptText("������");
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void show3() {
		String addnumString = com_upd.getEditor().getText();
		if(addnumString.length()>6) {//�����ַ�������
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
          			"�����ַ���������", "����", JOptionPane.WARNING_MESSAGE);
			com_upd.getEditor().clear();
			return;
		}
		if(!isNumeric(addnumString)){//�����ַ�������������
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
          			"�����ַ��趼Ϊ���֣�", "����", JOptionPane.WARNING_MESSAGE);
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
					" from chuhuodan ");
			while(jinhuors.next()) {
				if(addnumString.equals(jinhuors.getString(1))) {//�ҵ�
					text31.setText(jinhuors.getString(2));
					text32.setText(jinhuors.getString(3));
					text33.setText(jinhuors.getString(4));
					text34.setText(jinhuors.getString(5));
					return;
				}
			}
			
			//û�ҵ�
			text11.setPromptText("��Ʒ����");
			text21.setPromptText("��Ʒ����");
			text31.setPromptText("��Ʒ����");
			text12.setPromptText("��������");
			text22.setPromptText("��������");
			text32.setPromptText("��������");
			text13.setPromptText("������");
			text23.setPromptText("������");
			text33.setPromptText("������");
			text14.setPromptText("������");
			text24.setPromptText("������");
			text34.setPromptText("������");
			
			
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
}
