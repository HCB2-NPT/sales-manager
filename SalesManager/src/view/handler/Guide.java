package view.handler;

import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;

public class Guide {
	 	@FXML
	    private JFXTextArea textarea;

	    @FXML
	    void About_Click() {
	    	textarea.setText("Thông tin liên lạc của nhóm \r\n");
	    }

	    @FXML
	    void Account_Click() {
	    	textarea.setText("Quản lý tài khoản của công ty \r\n \r\n"
	    			+ "THÊM MỚI \r\n"
	    			+ "Nhập tên người, nhập tên tài khoản, chọn phân quyền cho tài khoản\r\n"
	    			+ "Click '+' \r\n"
	    			+ "Lưu ý : tài khoản vừa được tạo sẽ có ID - Password giống nhau ( có thể tuỳ chỉnh lại sau )\r\n \r\n"
	    			+ "CHỈNH SỬA \r\n"
	    			+ "Người quản lý thao tác trực tiếp trên bảng \r\n"
	    			+ "Lưu thay đổi bằng cách nhấn 'SAVE' \r\n"
	    			+ "Quay lại như lúc ban đầu thì nhấn 'REFRESH'");
	    }

	    @FXML
	    void Backup_Click() {
	    	textarea.setText("Tạo bản sao lưu cơ sở dữ liệu hiện tại");
	    }

	    @FXML
	    void Bill_Click() {
	    	textarea.setText("Kiểm tra hoá đơn đã bán của công ty \r\n \r\n"
	    			+ "TÌM HOÁ ĐƠN THEO NGÀY \r\n"
	    			+ "Nhân viên chọn ngày cần tìm \r\n"
	    			+ "Bảng sẽ hiển thị danh sách hoá đơn đã tạo trong ngày\r\n"
	    			+ "Quay trở lại toàn bộ danh sách hoá đơn thì nhấn 'Refresh'\r\n \r\n"
	    			+ "Xoá HOÁ ĐƠN\r\n"
	    			+ "Nhấn biểu tượng thùng rác ở dòng hoá đơn muốn xoá\r\n"
	    			+ "Nhấn 'Save' để lưu lại hành động");
	    }

	    @FXML
	    void Category_Click() {
	    	
	    }

	    @FXML
	    void Contacts_Click() {

	    }

	    @FXML
	    void Customer_Click() {

	    }

	    @FXML
	    void Debt_Click() {

	    }

	    @FXML
	    void Details_Click() {

	    }

	    @FXML
	    void Dram_Click() {

	    }

	    @FXML
	    void Import_Click() {

	    }

	    @FXML
	    void Items_Click() {

	    }

	    @FXML
	    void Log_Click() {

	    }

	    @FXML
	    void Parts_Click() {

	    }

	    @FXML
	    void Payment_Click() {

	    }

	    @FXML
	    void ReportImport_Click() {

	    }

	    @FXML
	    void ReportIncome_Click() {

	    }

	    @FXML
	    void ReportSell_Click() {

	    }

	    @FXML
	    void Restore_Click() {

	    }

	    @FXML
	    void Sell_Click() {

	    }

	    @FXML
	    void Storage_Click() {

	    }

	    @FXML
	    void initialize() {
	        assert textarea != null : "fx:id=\"textarea\" was not injected: check your FXML file 'Guide.fxml'.";

	    }
}
