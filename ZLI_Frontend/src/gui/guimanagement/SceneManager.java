package gui.guimanagement;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

import client.ClientProperties;
import controllers.ClientController;
import gui.client.ClientUI;
import gui.client.main.MainView;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SceneManager
{
	private static Stage mainWindow;
	private static Scene currentScene;
	private static Pane root, mainViewPane;
	private static StackPane container;
	private static VBox contentVBox;
	private static HBox header;
	private static Stack<HistoryState> history;
	private static Stage loadingWindow;
	private static MenuButton userDropDown;
	private static Button shoppingCartButton;

	/**
	 * Initialize the UI. This method should only be called <b>once!</b>
	 * 
	 * @param primaryStage
	 */
	public static void initUI(Stage primaryStage)
	{
		root = new AnchorPane();
		container = new StackPane();
		contentVBox = new VBox();
		mainWindow = primaryStage;
		history = new Stack<HistoryState>();

		mainWindow.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			@Override
			public void handle(WindowEvent event)
			{
				ClientController.getInstance().closeConnection();
			}
		});

		loadMainContainer();
		loadNewScene(GUIPages.Login, true);
//		loadAdditiveScene(GUIPages.Loading, true);
		mainWindow.setHeight(ClientProperties.getClientHeight());
		mainWindow.setWidth(ClientProperties.getClientWidth());
		ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> resizeAllContent();
		mainWindow.widthProperty().addListener(stageSizeListener);

//		mainWindow.heightProperty().addListener(stageSizeListener);
//		mainWindow.setResizable(false);
//		loadAdditiveScene(GUIPages.Orders, true);
//		loadAdditiveScene(GUIPages.UpdateOrder, true);
	}

	/**
	 * Loads the main container which has a scroll pane and an anchor pane for the
	 * content (ClientUI).
	 */
	private static void loadMainContainer()
	{
		GUIPages pageToLoad = GUIPages.MainContainer;
		FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(pageToLoad.getFxmlFile()));
		try
		{
			root = new AnchorPane();
			mainViewPane = loader.load();
			root.getChildren().add(mainViewPane);
			MainView mainViewController = loader.getController();
			header = mainViewController.getHeader();
			userDropDown = mainViewController.getUserDropDown();
			shoppingCartButton = mainViewController.getShoppingCartButton();
			container = mainViewController.getContent();
			container.getChildren().add(contentVBox);
			currentScene = new Scene(root);
			mainViewPane.setPrefWidth(ClientProperties.getClientWidth());
			mainViewPane.setPrefHeight(ClientProperties.getClientHeight());
			container.setPrefWidth(ClientProperties.getClientWidth());
			container.setPrefHeight(ClientProperties.getClientHeight());
			mainWindow.setTitle(pageToLoad.getPageTitle());
			mainWindow.setScene(currentScene);
			mainWindow.show();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Load a new page by replacing the current page.
	 * 
	 * @param pageToLoad    Page to load
	 * @param saveToHistory Should this page be saved in the history stack?
	 * @return The GUIController of the loaded scene.
	 */
	public static GUIController loadNewScene(GUIPages pageToLoad, boolean saveToHistory)
	{
		FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(pageToLoad.getFxmlFile()));
		GUIController guiController = null;
		try
		{
			if (pageToLoad != GUIPages.MainContainer)
			{
				Pane loadedContent = loader.load();
				contentVBox.getChildren().clear();
				contentVBox.getChildren().add(loadedContent);
				guiController = loader.getController();
				setupGUIController(guiController, loadedContent);
				setSceneWidth(loadedContent, false);

				if (saveToHistory)
				{
					HistoryState state = new HistoryState(guiController, false, pageToLoad);
					history.push(state);
				}
				mainWindow.setTitle(pageToLoad.getPageTitle());
				mainWindow.show();
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return guiController;
	}

	/**
	 * Loads a page without replacing any existing content.
	 * 
	 * @param pageToLoad    Page to load
	 * @param saveToHistory Should this page be saved in the history stack?
	 * @return The GUIController of the loaded scene.
	 */
	public static GUIController loadAdditiveScene(GUIPages pageToLoad, boolean saveToHistory)
	{
		FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(pageToLoad.getFxmlFile()));
		GUIController guiController = null;
		Parent toAdd;
		try
		{
			toAdd = loader.load();
			guiController = loader.getController();
			setupGUIController(guiController, toAdd);
			if (saveToHistory)
			{
				HistoryState state = new HistoryState(guiController, true, pageToLoad);
				history.push(state);
			}
			contentVBox.getChildren().add(toAdd);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return guiController;
	}

	private static void resizeAllContent()
	{
		// TODO: Doesn't work properly... fix later
		ObservableList<Node> content = contentVBox.getChildren();
		setSceneWidth(mainViewPane, true);		
		setSceneWidth(container, false);
		setSceneWidth(contentVBox, false);
		for (Node node : content)
		{
			System.out.println(node);
			setSceneWidth((Pane)node, false);
		}
		mainViewPane.setMinHeight(mainWindow.getHeight());
		mainViewPane.setPrefHeight(mainWindow.getHeight());
		contentVBox.setPrefHeight(mainWindow.getHeight());
		container.setMinHeight(Region.USE_PREF_SIZE);
		container.setPrefHeight(mainViewPane.getHeight() - header.getHeight());
	}

	/**
	 * @param loadedContent
	 * @param setMaxSize 
	 */
	public static void setSceneWidth(Pane loadedContent, boolean setMaxSize)
	{
		Pane parent = (Pane)loadedContent.getParent();
		double parentWidth = parent.getWidth();
		double width = parentWidth * (setMaxSize ? 1 : ClientProperties.getDefaultPercentageOfParent());
		loadedContent.setPrefWidth(width);
		loadedContent.setMinWidth(width * 0.5);
		double offset = (parentWidth - width) / 2;
//		System.out.println(left + " " + right);
		AnchorPane.setLeftAnchor(loadedContent, offset);
		AnchorPane.setRightAnchor(loadedContent, offset);
	}
	private static void setupGUIController(GUIController guiController, Parent root)
	{
		guiController.setRoot(root);
		guiController.setScene(currentScene);
		guiController.setStage(mainWindow);
	}

	/**
	 * Loads the previous page from the history stack.
	 * 
	 * @return The GUIController of the loaded scene.
	 */
	public static GUIController loadPreviousPage()
	{
		try
		{
			history.pop();
			HistoryState state = history.peek();
			if (state.isAdditivelyLoaded())
			{
				contentVBox.getChildren().remove(state.getGuiController().getRoot());
			} else
			{
				reloadScene(state);
			}
			return state.getGuiController();
		} catch (EmptyStackException e)
		{
			System.out.println("Error in history!");
			e.printStackTrace();
			return null;
		}
	}

	private static void reloadScene(HistoryState state)
	{
		contentVBox.getChildren().clear();
		contentVBox.getChildren().add(state.getGuiController().getRoot());
	}

	/**
	 * Loads the given page as a Modal Window, blocking other open windows until it
	 * is closed.
	 * 
	 * @param pageToLoad Page to load.
	 */
	public static Stage loadModalWindow(GUIPages pageToLoad)
	{
		Stage modalWindow = new Stage();
		GUIController guiController = null;
		modalWindow.initModality(Modality.APPLICATION_MODAL);
		modalWindow.setTitle(pageToLoad.getPageTitle());
		Pane pane;
		try
		{
			FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(pageToLoad.getFxmlFile()));
			pane = loader.load();
			guiController = loader.getController();
			guiController.setStage(modalWindow);
			Scene scene = new Scene(pane);
			modalWindow.setScene(scene);
			modalWindow.show();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return modalWindow;
	}

	/**
	 * Opens the settings page.
	 */
	public static void openSettingsPage()
	{
		loadModalWindow(GUIPages.Settings);
	}

	public static void openLoadingWindow()
	{
		loadingWindow = loadModalWindow(GUIPages.Loading);
	}

	public static void closeLoadingWindow()
	{
		if (loadingWindow != null)
		{
			loadingWindow.close();
			loadingWindow = null;
		}
	}
	
	public static void setHeaderButtonVisibility(boolean showUserDropDown, boolean showShoppingCartButton)
	{
		userDropDown.setVisible(showUserDropDown);
		shoppingCartButton.setVisible(showShoppingCartButton);
		userDropDown.setDisable(!showUserDropDown);
		shoppingCartButton.setDisable(!showShoppingCartButton);
	}
}
