package gui.guimanagement;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class GUIController implements Initializable
{
	protected Scene scene;
	protected Stage stage;
	protected Parent root;
	protected Object data;
	
	
	/** 
	 * @param scene
	 */
	public void setScene(Scene scene)
	{
		this.scene = scene;
	}

	
	/** 
	 * @param settings
	 */
	public void setStage(Stage settings)
	{
		this.stage = settings;
	}
	
	
	/** 
	 * @param root
	 */
	public void setRoot(Parent root)
	{
		this.root = root;
	}
	
	
	/** 
	 * @return Parent
	 */
	public Parent getRoot()
	{
		return root;
	}

	
	/** 
	 * @return Object
	 */
	public Object getData()
	{
		return data;
	}

	
	/** 
	 * @param data
	 */
	public void setData(Object data)
	{
		this.data = data;
	}
}
