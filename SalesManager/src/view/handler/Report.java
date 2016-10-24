package view.handler;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import com.jfoenix.controls.JFXDatePicker;

import application.AppSession;
import dao.HibernateUtil;
import helper.AppUtil;
import helper.MessageBox;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Report {
	private static Logger logger = Logger.getLogger(Report.class);
	
	public enum ReportType {
		In, Out, Income
	}
	
	static FXMLLoader _formLoader = null;
    static Stage _currentStage = null;
    static ReportType rp = null;
    public static void callReport(ReportType e){
    	if (_currentStage == null){
			_formLoader = AppUtil.callFXMLLoader("../view/Report.fxml");
			if (_formLoader != null){
				_currentStage = AppUtil.callForm(_formLoader, null);
				if (_currentStage != null){
					_currentStage.centerOnScreen();
					_currentStage.setResizable(false);
					_currentStage.setAlwaysOnTop(true);
					_currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						public void handle(WindowEvent event) {
							callQuit();
						}
					});
					rp = e;
					_currentStage.showAndWait();
			    }
			}
    	}
    }
    
    static void callQuit(){
    	if (_currentStage != null){
    		_currentStage.close();
    		_currentStage = null;
    	}
    }
    
    static HashMap<String, Object> parameters;
    public static void callReport(String file, HashMap<String, Object> params){
    	if (params == null)
    		params = new HashMap<>();
    	parameters = params;
    	Session session = null;
    	try{
	    	session = HibernateUtil.getSessionFactory().openSession();
			session.doWork(new Work() {
				@Override
				public void execute(Connection conn) throws SQLException {
					try {
						String s = AppSession._resourceProvider.getResource(file).getPath();
						JasperReport jr = JasperCompileManager.compileReport(s);
						parameters.put("LocalImageDir", new File(s).getParent() + "\\");
						parameters.put("User", String.format("%1$s (Id-%2$s)", AppSession._currentUser.getName(), AppSession._currentUser.getAccountId()));
						JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);
						JasperViewer.viewReport(jp, false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally {
    		session.close();
		}
    }
    
    @FXML
    private JFXDatePicker _from;

    @FXML
    private JFXDatePicker _to;

    @FXML
    void cancel() {
    	callQuit();
    }

    @FXML
    void ok() {
    	logger.debug("Wait for query to database...");
    	Main.callMsg("Wait...");
    	callQuit();
    	HashMap<String, Object> params = new HashMap<>();
    	params.put("From", _from.getValue());
    	params.put("To", _to.getValue());
    	switch (rp) {
    	case In:
    		callReport("../view/report/ImportsReport.jrxml", params);
			break;
    	case Out:
    		callReport("../view/report/SellsReport.jrxml", params);
			break;
    	case Income:
    		callReport("../view/report/IncomeReport.jrxml", params);
			break;
		default:
			break;
		}
    	logger.info("Create a report '" + rp.name() + "' from " + _from.getValue().toString() + " to " + _to.getValue().toString());
    	Main.callMsg("Now you have a report.");
    }

    @FXML
    void initialize() {
        assert _from != null : "fx:id=\"_from\" was not injected: check your FXML file 'Report.fxml'.";
        assert _to != null : "fx:id=\"_to\" was not injected: check your FXML file 'Report.fxml'.";

        _from.setValue(LocalDate.now());
        _to.setValue(LocalDate.now());
    }
}
