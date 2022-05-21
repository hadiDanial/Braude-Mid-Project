package gui.guimanagement;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

import client.ClientProperties;
import controllers.ClientController;
import gui.client.main.MainView;
import javafx.application.Platform;
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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SceneManager
{
	private static Stage mainWindow;
	private static Scene currentScene;
	private static Pane root, mainViewPane;
	private static VBox container;
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
		container = new VBox();
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
		mainWindow.setMinWidth(800);
		mainWindow.setMinHeight(600);
		
		// Dynamically resize content
		ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> resizeAllContent();
		mainWindow.widthProperty().addListener(stageSizeListener);
		mainWindow.heightProperty().addListener(stageSizeListener);
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
			container.setAlignment(Pos.BASELINE_LEFT);
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
				container.getChildren().clear();
				container.getChildren().add(loadedContent);
				guiController = loader.getController();
				setupGUIController(guiController, loadedContent);
				setContentWidth(loadedContent, true, true);
				setContentHeight(loadedContent, true, true);

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
			container.getChildren().add(toAdd);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return guiController;
	}

	
	/**
	 * Dynamically resize content.
	 */
	private static void resizeAllContent()
	{
		mainViewPane.setPrefWidth(mainWindow.getWidth());
		mainViewPane.setPrefHeight(mainWindow.getHeight());
		mainViewPane.setMaxWidth(mainWindow.getWidth());
		mainViewPane.setMaxHeight(mainWindow.getHeight());
		setContentWidth(mainViewPane, true, true);		
		setContentWidth(container, true, true);
		setContentHeight(mainViewPane, true, true);		
		setContentHeight(container, false, false);
		ObservableList<Node> children = container.getChildren();
		for(Node child : children)
		{
			setContentWidth((Pane)child, false, true);
			setContentHeight((Pane) child, false, true);					
		}
	}
	

	/**
	 * Dynamically set the pane's height
	 * @param loadedContent Pane that needs to be resized
	 * @param setMaxSize Use the maximum size or leave a margin?
	 * @param useWindowWidth Use the window height or the view pane height?
	 */
	public static void setContentHeight(Pane loadedContent, boolean setMaxSize, boolean useWindowSize) 
	{
		double parentHeight = (useWindowSize ? mainWindow.getHeight() : mainViewPane.getHeight());
		double headerHeight =  header.getHeight();
		double height = parentHeight * (setMaxSize ? 1 : ClientProperties.getDefaultPercentageOfParent()) - headerHeight;
		loadedContent.setMaxHeight(height);
		loadedContent.setPrefHeight(height);
		loadedContent.setMinHeight(height * 0.5);
		double offset = (parentHeight - height - headerHeight) / 2;
		AnchorPane.setTopAnchor(loadedContent, offset);
		AnchorPane.setBottomAnchor(loadedContent, offset);
	}
	
	/**
	 * Dynamically set the pane's width
	 * @param loadedContent Pane that needs to be resized
	 * @param setMaxSize Use the maximum size or leave a margin?
	 * @param useWindowSize Use the window width or the view pane width?
	 */
	public static void setContentWidth(Pane loadedContent, boolean setMaxSize, boolean useWindowSize)
	{
		double parentWidth = (useWindowSize ? mainWindow.getWidth() : mainViewPane.getWidth());
		double width = parentWidth * (setMaxSize ? 1 : ClientProperties.getDefaultPercentageOfParent());
		loadedContent.setPrefWidth(width);
		loadedContent.setMinWidth(width * 0.5);
		double offset = (parentWidth - width) / 2;
		AnchorPane.setLeftAnchor(loadedContent, offset);
		AnchorPane.setRightAnchor(loadedContent, offset);
	}
	
	/**
	 * Setup the GUIController root, scene, and stage.
	 * @param guiController
	 * @param root
	 */
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
				container.getChildren().remove(state.getGuiController().getRoot());
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

	/**
	 * Reload a history state.
	 */
	private static void reloadScene(HistoryState state)
	{
		container.getChildren().clear();
		container.getChildren().add(state.getGuiController().getRoot());
	}

	/**
	 * Loads the given page as a Modal Window, blocking other open windows until it
	 * is closed.
	 * 
	 * @param pageToLoad Page to load.
	 */
	public static Stage loadModalWindow(GUIPages pageToLoad, Object data)
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
			if(data != null)
				guiController.setData(data);
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
		loadModalWindow(GUIPages.Settings, null);
	}

	public static void openLoadingWindow()
	{
		loadingWindow = loadModalWindow(GUIPages.Loading, null);
	}

	public static void closeLoadingWindow()
	{
		if (loadingWindow != null)
		{
			loadingWindow.close();
			loadingWindow = null;
		}
	}
	
	/**
	 * Set whether the user drop down and the shopping cart buttons will be visible.
	 */
	public static void setHeaderButtonVisibility(boolean showUserDropDown, boolean showShoppingCartButton)
	{
		userDropDown.setVisible(showUserDropDown);
		shoppingCartButton.setVisible(showShoppingCartButton);
		userDropDown.setDisable(!showUserDropDown);
		shoppingCartButton.setDisable(!showShoppingCartButton);
	}

	public static void displayErrorMessage(String messageString)
	{
		Platform.runLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				loadModalWindow(GUIPages.Error, messageString);
			}
		});
	}

	public static void clearHistory()
	{
		history.clear();
	}
}
