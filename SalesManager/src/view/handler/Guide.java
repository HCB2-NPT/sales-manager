package view.handler;

import com.jfoenix.controls.JFXTextArea;

import helper.AppUtil;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Guide {
	static FXMLLoader _formLoader = null;
    static Stage _currentStage = null;
    public static void call(){
    	if (_currentStage == null){
			_formLoader = AppUtil.callFXMLLoader("../view/Guide.fxml");
			if (_formLoader != null){
				_currentStage = AppUtil.callForm(_formLoader, null);
				if (_currentStage != null){
					_currentStage.setResizable(false);
					_currentStage.setAlwaysOnTop(true);
					_currentStage.setX(0);
					_currentStage.setY(0);
					_currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						public void handle(WindowEvent event) {
							callQuit();
						}
					});
					_currentStage.show();
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
    	textarea.setText("Quản lý phân loại sản phẩm \r\n\r\n"
    			+ "THÊM MỚI PHÂN LOẠI \r\n"
    			+ "Quản lý nhập tên phân loại mới.\r\n"
    			+ "Nhấn '+' để thêm mới vào bảng danh sách phân loại.\r\n"
    			+ "Nhấn 'Save' để lưu lại thông tin.\r\n"
    			+ "Nhấn 'Refresh' để hoàn tác.");
    }


    @FXML
    void Customer_Click() {
    	textarea.setText("Quản lý khách hàng\r\n \r\n"
    			+ "THÊM KHÁCH HÀNG MỚI\r\n"
    			+ "Quản lý mở Title 'Add New User'\r\n"
    			+ "Nhập các thông tin của khách hàng rồi nhấn 'Save'\r\n\r\n"
    			+ "TÌM KIẾM KHÁCH HÀNG\r\n"
    			+ "Nhập tên khách hàng cần tìm, sau đó nhấn 'Search'\r\n"
    			+ "Bảng sẽ trả về danh sách khách hàng chứa giá trị cần tìm\r\n"
    			+ "Nhấn 'Refresh' để load lại bảng dữ liệu");
    }

    @FXML
    void Debt_Click() {
    	textarea.setText("Quản lý những hoá đơn nợ của công ty\r\n \r\n"
    			+ "TÌM HOÁ ĐƠN NỢ \r\n"
    			+ "Nhân viên nhập mã hoá đơn sau đó 'Enter' hoặc 'Search' \r\n"
    			+ "Bảng danh sách hoá đơn nợ sẽ hiện hoá đơn nếu tìm thấy\r\n\r\n"
    			+ "THANH TOÁN HOÁ ĐƠN NỢ"
    			+ "Nhân viên nhấn vào 'Check' hoá đơn nợ sẽ được hỏi lại lần nữa là có thanh toán hay không\r\n"
    			+ "Yes để hoàn tất thanh toán nợ , No để hoàn tác");
    }

    @FXML
    void Details_Click() {
    	textarea.setText("Điều chỉnh thông tin giới thiệu tool của cửa hàng\r\n \r\n"
    			+ "Nhập thông tin mới vào các trường.\r\n"
    			+ "Nhấn 'Save' để lưu lại thông tin. \r\n"
    			+ "Logout rồi đăng nhập lại để thấy sự thay đổi.");
    }

    @FXML
    void Dram_Click() {
    	textarea.setText("Quản lý đơn vị sản phẩm \r\n\r\n"
    			+ "THÊM MỚI ĐƠN VỊ \r\n"
    			+ "Quản lý nhập tên đơn vị mới.\r\n"
    			+ "Nhấn '+' để thêm mới vào bảng danh sách đơ vị.\r\n"
    			+ "Nhấn 'Save' để lưu lại thông tin.\r\n"
    			+ "Nhấn 'Refresh' để hoàn tác.\r\n"
    			+ "Lưu ý: thông tin mô tả có thể tuỳ chỉnh trực tiếp trên bảng.");
    }

    @FXML
    void Import_Click() {
    	textarea.setText("Chức năng nhập sản phẩm vào kho\r\n \r\n"
    			+ "NHẬP THÊM SẢN PHẨM\r\n"
    			+ "Nhập thông tin từng sản phẩm bằng cách điền các trường thuộc tính yêu cầu\r\n"
    			+ "Nhấn 'Add a import-detail' để thêm thiết bị vào đơn nhập hàng \r\n"
    			+ "Nhấn 'Save' để lưu thông tin phiếu nhập và tiến hành nhập thẳng vào kho \r\n"
    			+ "Lưu ý: 1.Với những sản phẩm chưa có sẵn trong kho, nhấn 'New Item' rồi tiến hành tạo mới sản phẩm trước khi nhập vào kho."
    			+ "		  2.Thay đổi số lượng trực tiếp trên bảng, xoá dòng bằng cách nhấn vào biểu tượng thùng rác trên bảng.");
    }

    @FXML
    void Items_Click() {
    	textarea.setText("Quản lý chi tiết sản phẩm của công ty\r\n\r\n"
    			+ "SỬA THÔNG TIN SẢN PHẨM\r\n"
    			+ "Quản lý chỉnh sửa thông tin trực tiếp trên bảng danh sách sản phẩm.\r\n"
    			+ "Nhấn 'Save All' để hoàn tất quá trình thay đổi\r\n \r\n"
    			+ "TÌM KIẾM SẢN PHẨM\r\n"
    			+ "Nhập tên sản phẩm cần tìm , sau đó nhấn 'Search' \r\n"
    			+ "Những sản phẩm có chứa giá trị cần tìm sẽ hiện ra trên bảng thông tin sản phẩm.\r\n"
    			+ "Nhấn 'Refresh' để load lại dữ liệu");
    }

    @FXML
    void Log_Click() {
    	textarea.setText("Mở log của chương trình");
    }

    @FXML
    void Parts_Click() {
    	textarea.setText("Quản lý loại phân quyền nhân viên \r\n\r\n"
    			+ "THÊM MỚI PHÂN QUYỀN \r\n"
    			+ "Quản lý nhập tên phân quyền mới.\r\n"
    			+ "Nhấn '+' để thêm mới vào bảng danh sách phân quyền.\r\n"
    			+ "Nhấn 'Save' để lưu lại thông tin.\r\n"
    			+ "Nhấn 'Refresh' để hoàn tác.\r\n"
    			+ "Lưu ý: thông tin mô tả có thể tuỳ chỉnh trực tiếp trên bảng.");
    }

    @FXML
    void Payment_Click() {
    	textarea.setText("Xem thông tin danh sách phiếu chi để nhập hàng\r\n \r\n"
    			+ "XEM DANH SÁCH PHIẾU CHI-TỔNG TIỀN NHẬP HÀNG \r\n"
    			+ "Chọn ngày muốn xem, bảng sẽ show ra danh sách hoá đơn nhập hàng trong ngày hôm đó \r\n"
    			+ "Nhấn 'Refresh' để load toàn bộ danh sách");
    }

    @FXML
    void ReportImport_Click() {
    	textarea.setText("Thống kê số lượng sản phẩm, tổng tiền nhập kho theo thời gian");
    }

    @FXML
    void ReportIncome_Click() {
    	textarea.setText("Thống kê doanh thu theo thời gian");
    }
    
    @FXML
    void Partner_Click() {
    	textarea.setText("Quản lý thông tin đối tác sản xuất \r\n\r\n"
    			+ "THÊM NSX MỚI \r\n"
    			+ "Nhập tên NSX mới \r\n"
    			+ "Nhấn '+' để lưu nsx vào bảng dang sách nsx \r\n"
    			+ "Nhấn 'Save' để lưu xuống csdl \r\n"
    			+ "Nhấn 'Refresh' để làm mới lại bảng danh sách \r\n"
    			+ "Lưu ý: Thông tin mô tả của nsx có thể điều chỉnh trực tiếp trên bảng.");
    }
    

    @FXML
    void ReportSell_Click() {
    	textarea.setText("Thống kê bán hàng theo thời gian");
    }

    @FXML
    void Restore_Click() {
    	textarea.setText("Phục hồi bản sao cơ sở dữ liệu đã tạo trước đó");
    }

    @FXML
    void Sell_Click() {
    	textarea.setText("Bán hàng cho khách \r\n \r\n"
    			+ "Nhân viên điền CMND của khách hàng \r\n"
    			+ "Chọn sản phẩm có trong cửa hàng\r\n"
    			+ "Nhấn 'Add Item' để thêm vào giỏ hàng\r\n"
    			+ "Có thể xoá sản phẩm khỏi giỏ hàng bằng biểu tượng kế bên"
    			+ "Hoá đơn có nợ hay không thì đánh vào 'Isdebt' và chọn ngày nợ nếu có"
    			+ "Nhấn 'Finish' để hoàn tất hoá đơn \r\n");
    }

    @FXML
    void Storage_Click() {
    	textarea.setText("Tra cứu số lượng và đơn giá của sản phẩm \r\n"
    			+ "Nhân viên nhập tên sản phẩm cần tìm"
    			+ "Bảng sẽ trả về danh sách có chứa giá trị cần tìm.");
    }

    @FXML
    void initialize() {
        assert textarea != null : "fx:id=\"textarea\" was not injected: check your FXML file 'Guide.fxml'.";

        textarea.setEditable(false);
    }
}
