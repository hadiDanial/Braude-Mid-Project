package gui.guimanagement;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Stack;

import client.ClientProperties;
import controllers.ClientController;
import controllers.UserController;
import enums.UserRole;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
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
	private static ScrollPane scrollPane;
	private static HBox header;
	private static Stack<HistoryState> history;
	private static Stage loadingWindow;
	private static MenuButton userDropDown;
	private static Button shoppingCartButton;
	private static HashSet<Pane> panesToResize;
	private static AnchorPane scrollPaneAnchor;
	private static Button homeButton;
	private static MenuItem myOrders;

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
		panesToResize = new HashSet<Pane>();
		scrollPaneAnchor = new AnchorPane();
		mainWindow.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			@Override
			public void handle(WindowEvent event)
			{
				ClientController.getInstance().closeConnection();
			}
		});

		loadMainContainer();
		loadNewScene(GUIPages.LOGIN, false);
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
		GUIPages pageToLoad = GUIPages.MAIN_CONTAINER;
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
			homeButton = mainViewController.getHomeBtn();
			scrollPane = mainViewController.getScrollPane();
			container = mainViewController.getContent();
			myOrders = mainViewController.getMyOrdersItem();
			container.setAlignment(Pos.TOP_CENTER);
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
		if(!history.isEmpty() && pageToLoad.equals(history.peek().getPage()))
			return null;
		FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(pageToLoad.getFxmlFile()));
		GUIController guiController = null;
		try
		{
			if (pageToLoad != GUIPages.MAIN_CONTAINER)
			{
				Pane loadedContent = loader.load();
				container.getChildren().clear();
				container.getChildren().add(loadedContent);
				guiController = loader.getController();
				setupGUIController(guiController, loadedContent);
//				setContentWidth(loadedContent, true, true);
//				setContentHeight(loadedContent, true, true);
//				scrollPane.setStyle("-fx-min-height: 0%;");
				scrollPane.setContent(null);
				scrollPane.setVisible(false);
				panesToResize.clear();
				if (saveToHistory)
				{
					HistoryState state = new HistoryState(guiController, false, pageToLoad);
					history.push(state);
				}
				resizeAllContent();
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
			container.setAlignment(Pos.CENTER);
			scrollPane.setContent(container);
//			scrollPane.setStyle("-fx-min-height: 100%;");
			resizeAllContent();
			scrollPane.setVisible(true);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return guiController;
	}

	/**
	 * Loads a page without replacing any existing content.
	 * 
	 * @param pageToLoad Page to load
	 * @param parent     The Pane that the newly loaded scenes will be added to.
	 * @return The GUIController of the loaded scene.
	 */
	public static GUIController loadAdditiveSceneFromParent(GUIPages pageToLoad, Pane parent)
	{
		FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(pageToLoad.getFxmlFile()));
		GUIController guiController = null;
		Parent toAdd;
		try
		{
			toAdd = loader.load();
			guiController = loader.getController();
			setupGUIController(guiController, toAdd);
			double width = mainWindow.getWidth();
			if(parent != null)
			{
				panesToResize.add(parent);
				parent.getChildren().add(toAdd);
				parent.setPrefWidth(width);
				
				if (parent instanceof VBox)
				{
					((VBox) parent).setAlignment(Pos.CENTER);
					((VBox) parent).setSpacing(20);
				}
				parent.setMaxWidth(scrollPane.getPrefViewportWidth());
//				scrollPaneAnchor.getChildren().clear();
//				scrollPaneAnchor.getChildren().add(parent);
			}
			panesToResize.remove(scrollPaneAnchor);
			AnchorPane.setLeftAnchor(scrollPaneAnchor, 0.0);
			AnchorPane.setRightAnchor(scrollPaneAnchor, 0.0);
			panesToResize.add(scrollPaneAnchor);
//			scrollPane.setContent(scrollPaneAnchor);
//			scrollPane.setStyle("-fx-min-height: " + getHeightPercentWithoutHeader() + "%;");
//			scrollPane.setVisible(true);
			resizeAllContent();

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return guiController;
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
			if(history.isEmpty()) return null;
			HistoryState prev = history.pop();
			HistoryState state = history.peek();
			if (prev.isAdditivelyLoaded())
			{
				container.getChildren().remove(prev.getGuiController().getRoot());
			}
			else
			{
				container.getChildren().clear();
			}

			container.getChildren().add(state.getGuiController().getRoot());
			state.getGuiController().initialize(null, null);
			return state.getGuiController();
		} catch (EmptyStackException e)
		{
			System.out.println("Error in history!");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Loads the given page as a Modal Window, blocking other open windows until it
	 * is closed.
	 * 
	 * @param pageToLoad Page to load.
	 */
	public static GUIController loadModalWindow(GUIPages pageToLoad, Object data)
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
			if (data != null)
				guiController.setData(data);
			Scene scene = new Scene(pane);
			modalWindow.setScene(scene);
			modalWindow.show();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return guiController;
	}

	/**
	 * Opens the settings page.
	 */
	public static void openSettingsPage()
	{
		loadModalWindow(GUIPages.SETTINGS, null);
	}

	public static void openLoadingWindow()
	{
		loadModalWindow(GUIPages.LOADING, null);
	}

//	public static void closeLoadingWindow()
//	{
//		if (loadingWindow != null)
//		{
//			loadingWindow.close();
//			loadingWindow = null;
//		}
//	}

	/**
	 * Set whether the user drop down and the shopping cart buttons will be visible.
	 */
	public static void setHeaderButtonVisibility(boolean showUserDropDown, boolean isUser)
	{
		userDropDown.setVisible(showUserDropDown);
		shoppingCartButton.setVisible(isUser);
		myOrders.setVisible(isUser);
	}

	
	/** 
	 * @param messageString
	 */
	public static void displayErrorMessage(String messageString)
	{
		Platform.runLater(new Runnable()
		{

			@Override
			public void run()
			{
				loadModalWindow(GUIPages.ERROR, messageString);
			}
		});
	}

	public static void clearHistory()
	{
		history.clear();
	}
	
	
	
	
	
	//////////////////////
	//  RESIZE CONTENT  //
	//////////////////////
	
	
	/**
	 * Dynamically resize content.
	 */
	private static void resizeAllContent()
	{
		double windowHeight = mainWindow.getHeight();
		double windowWidth = mainWindow.getWidth();
		mainViewPane.setPrefWidth(windowWidth);
		mainViewPane.setPrefHeight(windowHeight);
		mainViewPane.setMaxWidth(windowWidth);
		mainViewPane.setMaxHeight(windowHeight);
		setContentWidth(mainViewPane, true, true);
		setContentWidth(container, true, true);
		setContentHeight(mainViewPane, true, true);
		setContentHeight(container, false, false);
		if (scrollPane.getContent() == null)
			scrollPane.setStyle("-fx-min-height: 0%;");
		else
		{
			scrollPane.setStyle("-fx-min-height: 100%;");
			double h = windowHeight - header.getHeight();
			scrollPane.setPrefViewportHeight(h);
			scrollPane.setMinViewportHeight(h);
		}
		scrollPane.setMinWidth(windowWidth);
		scrollPane.setMaxWidth(windowWidth);
		scrollPane.setPrefWidth(windowWidth);
		scrollPane.setPrefViewportWidth(windowWidth);
		scrollPaneAnchor.setMaxWidth(scrollPane.getPrefViewportWidth());
		scrollPaneAnchor.setMinWidth(scrollPane.getPrefViewportWidth() - 20);
		scrollPaneAnchor.setPrefWidth(scrollPane.getPrefViewportWidth() - 20);
		ObservableList<Node> children = container.getChildren();
		resizeChildren(children, true);
		for (Pane p : panesToResize)
		{
			children = p.getChildren();
			resizeChildren(children, false);
		}
	}

	/**
	 * Resize a list of nodes.
	 * 
	 * @param children     List of nodes to resize.
	 * @param resizeHeight
	 */
	private static void resizeChildren(ObservableList<Node> children, boolean resizeHeight)
	{
		for (Node child : children)
		{
			setContentWidth((Pane) child, false, true);
			if (resizeHeight)
				setContentHeight((Pane) child, false, true);
		}
	}

	/**
	 * @return Height of the window minus the header and container as a percentage
	 *         of the window height.
	 */
	private static double getHeightPercentWithoutHeader()
	{
		double height = mainWindow.getHeight();
		height = (height - header.getHeight()) / height;
		System.out.println(height);
		return height;
	}

	/**
	 * Calculate the height of the window minus the header, with an optional percentage.
	 * @param setMaxSize
	 * @param parentHeight
	 * @param headerHeight
	 * @return
	 */
	private static double calculateHeight(boolean setMaxSize, double parentHeight, double headerHeight)
	{
		double height = parentHeight * (setMaxSize ? 1 : ClientProperties.getDefaultPercentageOfParent())
				- headerHeight;
		return height;
	}

	/**
	 * Dynamically set the pane's height
	 * 
	 * @param loadedContent  Pane that needs to be resized
	 * @param setMaxSize     Use the maximum size or leave a margin?
	 * @param useWindowWidth Use the window height or the view pane height?
	 */
	public static void setContentHeight(Pane loadedContent, boolean setMaxSize, boolean useWindowSize)
	{
		double parentHeight = (useWindowSize ? mainWindow.getHeight() : mainViewPane.getHeight());
		double headerHeight = header.getHeight();
		double height = calculateHeight(setMaxSize, parentHeight, headerHeight);
		loadedContent.setMaxHeight(height);
		loadedContent.setPrefHeight(height);
		loadedContent.setMinHeight(height * 0.5);
		double offset = (parentHeight - height - headerHeight) / 2;
		AnchorPane.setTopAnchor(loadedContent, offset);
		AnchorPane.setBottomAnchor(loadedContent, offset);
	}

	/**
	 * Dynamically set the pane's width
	 * 
	 * @param loadedContent Pane that needs to be resized
	 * @param setMaxSize    Use the maximum size or leave a margin?
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
	 * 
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
	 * @return double
	 */
	public static double getStageHeight()
	{
		return mainWindow.getHeight();
	}

	
	/** 
	 * @param listener
	 */
	public static void addHeightListener(ChangeListener<? super Number> listener)
	{
		mainWindow.heightProperty().addListener(listener);
	}

	/**
	 * Load the home page for each user based on their role
	 */
	public static void openHomePage()
	{
		try
		{
			UserRole userRole = UserController.getInstance().getLoggedInUser().getRole();
			history.clear();
			container.getChildren().clear();
			homeButton.setVisible(true);
			userDropDown.setText(UserController.getInstance().getLoggedInUser().getFullName());
			switch(userRole)
			{
			case Customer:
			{
				loadNewScene(GUIPages.CATALOG_PAGE, true);
				setHeaderButtonVisibility(true, true);
				break;
			}
			case BranchEmployee:
			{
				loadNewScene(GUIPages.BRANCH_EMPLOYEE_PORTAL, true);
				setHeaderButtonVisibility(true, false);				
				break;
			}
			case BranchManager:
			{
				loadNewScene(GUIPages.BRANCH_MANAGER_PORTAL, true);
				setHeaderButtonVisibility(true, false);				
				break;
			}
			case CEO:
			{
				loadNewScene(GUIPages.CEO_PORTAL, true);
				setHeaderButtonVisibility(true, false);				
				break;
			}
			case ChainEmployee:
			{
				loadNewScene(GUIPages.CHAIN_EMPLOYEE_PORTAL, true);
				setHeaderButtonVisibility(true, false);				
				break;
			}
			case CustomerServiceEmployee:
			{
				loadNewScene(GUIPages.CS_EMPLOYEE_PORTAL, true);
				setHeaderButtonVisibility(true, false);
				break;
			}
			case CustomerServiceSpecialist:
			{
				loadNewScene(GUIPages.SPECIALIST_EMPLOYEE_PORTAL, true);
				setHeaderButtonVisibility(true, false);				
				break;
			}
			case DeliveryPerson:
			{
				loadNewScene(GUIPages.DELIVERY_OPERATOR_PORTAL, true);
				setHeaderButtonVisibility(true, false);				
				break;
			}
			default:
				displayErrorMessage("Invalid user role!");
				break;
			}
		} catch (NullPointerException e)
		{
			System.out.println("No user");
			displayErrorMessage("User Error!");
		}
		
	}

	public static void openMyOrdersPage()
	{
		if(UserController.getInstance().getLoggedInUser().getRole() == UserRole.Customer)
		{
			loadNewScene(GUIPages.CUSTOMER_ORDERS, true);
		}
	}
}
