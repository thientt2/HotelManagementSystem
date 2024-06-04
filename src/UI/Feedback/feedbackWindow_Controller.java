package UI.Feedback;

import UI.MainWindow_Controller;

public class feedbackWindow_Controller {
	
	private MainWindow_Controller mainWindowController;

    // Phương thức để thiết lập tham chiếu của main window controller từ bên ngoài
    public void setMainWindowController(MainWindow_Controller controller) {
        this.mainWindowController = controller;
    }
    
    public MainWindow_Controller getMainWindowController() {
		return mainWindowController;
	}
}
