package gui.guimanagement;

class HistoryState
{
	private GUIController guiController;
	private boolean additivelyLoaded;
	private GUIPages page;

	public HistoryState(GUIController guiController, boolean additivelyLoaded, GUIPages page)
	{
		super();
		this.guiController = guiController;
		this.additivelyLoaded = additivelyLoaded;
		this.page = page;
	}

	public GUIController getGuiController()
	{
		return guiController;
	}
	
	public void setGuiController(GUIController guiController)
	{
		this.guiController = guiController;
	}
	
	public boolean isAdditivelyLoaded()
	{
		return additivelyLoaded;
	}
	
	public void setAdditivelyLoaded(boolean wasAdditivelyLoaded)
	{
		this.additivelyLoaded = wasAdditivelyLoaded;
	}

	public GUIPages getPage()
	{
		return page;
	}

	public void setPage(GUIPages page)
	{
		this.page = page;
	}
}
