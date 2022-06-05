package gui.orders.checkoutEnd;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.OrderController;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.GUIController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import utility.IResponse;

public class CheckOutEnd extends GUIController
{

    @FXML
    private CheckBox sendingToServerCheck;

    @FXML
    private Label sendingLabel;

    @FXML
    private Label reviewLabel;

    @FXML
    private Button catalogBtn;
    
    @FXML
    void onNextBtn(ActionEvent event) {
    	SceneManager.clearHistory();
    	SceneManager.loadNewScene(GUIPages.CATALOG_PAGE, true);
    }

    void setSaved()
    {
    	sendingLabel.setText("Order has been saved");
    	sendingToServerCheck.setSelected(true);
    	catalogBtn.setDisable(false);
    	reviewLabel.setVisible(true);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		catalogBtn.setDisable(true);
		reviewLabel.setVisible(false);
		OrderController.getInstance().sendOrderToServer(new IResponse<Boolean>()
		{
			
			@Override
			public void executeAfterResponse(Object message)
			{
				Boolean successful = (Boolean) message;
				if(successful)
				{
					Platform.runLater(new Runnable()
					{
						
						@Override
						public void run()
						{
							setSaved();
						}
					});
				}
				else
				{
					SceneManager.displayErrorMessage("Failed to save order...");
				}
			}
		});
		ButtonAnimator.addButtonAnimations(catalogBtn);
	}
}
