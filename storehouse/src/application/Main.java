package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Main extends Application {
	public static Stage CurStage;
	
	private static Scene scene_login = null;
	private static Scene scene_shenhe = null;
	private static Scene scene_caigou = null;
	private static Scene scene_xiaoshou = null;
	public static String Login_ID = null;//登录人员的ID
	public static String Login_name = null;//登录人员的名字
	public static String Login_class = null;//登录人员的种类
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
			
			scene_login = new Scene(FXMLLoader.load(getClass().getResource("/Login.fxml")));
			
           
			CurStage = primaryStage;
			primaryStage.setTitle("仓库管理系统");
			primaryStage.setScene(new Scene(root));
			//primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
