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

	
	/** 
	 * @return GUIController
	 */
	public GUIController getGuiController()
	{
		return guiController;
	}
	
	
	/** 
	 * @param guiController
	 */
	public void setGuiController(GUIController guiController)
	{
		this.guiController = guiController;
	}
	
	
	/** 
	 * @return boolean
	 */
	public boolean isAdditivelyLoaded()
	{
		return additivelyLoaded;
	}
	
	
	/** 
	 * @param wasAdditivelyLoaded
	 */
	public void setAdditivelyLoaded(boolean wasAdditivelyLoaded)
	{
		this.additivelyLoaded = wasAdditivelyLoaded;
	}

	
	/** 
	 * @return GUIPages
	 */
	public GUIPages getPage()
	{
		return page;
	}

	
	/** 
	 * @param page
	 */
	public void setPage(GUIPages page)
	{
		this.page = page;
	}
}
