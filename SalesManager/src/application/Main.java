package application;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import dao.HibernateUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import view.handler.Login;

public class Main extends Application {
	private static final Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		HibernateUtil.tryConnectDatabase();
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws InterruptedException {
		logger.info("Start Application.");
		primaryStage.close();
		Login.callLogin(true);
	}
}
