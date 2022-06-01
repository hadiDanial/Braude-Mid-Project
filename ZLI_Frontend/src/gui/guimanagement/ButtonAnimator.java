package gui.guimanagement;

import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ButtonAnimator
{
	private static final double BUTTON_SCALE_PERCENT = 1.025;
	private static String HOVER_COLOR = "-fx-background-color: #469899;";
	private static String NORMAL_COLOR = "-fx-background-color: #05595B;";
	private static String DISABLED_COLOR = "-fx-background-color: #033436;";
	private static String STYLE = "-fx-background-radius: 10;";
	private static ButtonHandler animationHandler = new ButtonHandler(HOVER_COLOR, NORMAL_COLOR, STYLE);

	/**
	 * Handles mouse events for the grid buttons.
	 */
	private static class ButtonHandler implements EventHandler<MouseEvent>
	{
		private String hoverColor;
		private String normalColor;
		public ButtonHandler(String hoverColor, String normalColor, String style)
		{
			super();
			this.hoverColor = hoverColor + style;
			this.normalColor = normalColor + style;
		}
		@Override
		public void handle(MouseEvent event)
		{
			Button btn = (Button) event.getSource();

			// Animations.
			if (event.getEventType() == MouseEvent.MOUSE_ENTERED)
				scaleUp(btn, hoverColor);
			if (event.getEventType() == MouseEvent.MOUSE_EXITED)
				scaleDown(btn, normalColor);

		}
	}

	public static void addButtonAnimations(String style, String normalColor, String hoverColor, String disabledColor,
			Button... buttons)
	{
		ButtonHandler handler;
		if(normalColor.equals(NORMAL_COLOR) && hoverColor.equals(HOVER_COLOR))
		{
			handler = animationHandler;
		}
		else 
		{
			handler = new ButtonHandler(hoverColor, normalColor, style);
		}
		for (Button button : buttons)
		{
			// Animations
			button.setOnMouseEntered(handler);
			button.setOnMouseExited(handler);
			// Disabled color
			button.disabledProperty().addListener(new ChangeListener<Boolean>()
			{
				
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
				{
					if (newValue)
					{
						button.setStyle(normalColor + style);
					} else
					{
						button.setStyle(disabledColor + style);
					}
				}
			});
			// Focused color (when using tab to change input controls)
			button.focusedProperty().addListener(new ChangeListener<Boolean>()
			{
				
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
				{
					if (newValue)
					{
						scaleUp(button, hoverColor);
					} else
					{
						scaleDown(button, normalColor);
					}
				}
			});
		}
	}

	public static void addButtonAnimations(Button... buttons)
	{
		addButtonAnimations(STYLE, NORMAL_COLOR, HOVER_COLOR, DISABLED_COLOR, buttons);
	}

	private static void scaleUp(Button btn, String style)
	{
		ScaleTransition scaleUp = new ScaleTransition();
		btn.setStyle(style);
		scaleUp.setDuration(Duration.millis(100));
		scaleUp.setFromX(1);
		scaleUp.setFromY(1);
		scaleUp.setToX(BUTTON_SCALE_PERCENT);
		scaleUp.setToY(BUTTON_SCALE_PERCENT);
		scaleUp.setNode(btn);
		scaleUp.play();
	}

	private static void scaleDown(Button btn, String style)
	{

		ScaleTransition scaleDown = new ScaleTransition();
		btn.setStyle(style);
		scaleDown.setDuration(Duration.millis(100));
		scaleDown.setFromX(BUTTON_SCALE_PERCENT);
		scaleDown.setFromY(BUTTON_SCALE_PERCENT);
		scaleDown.setToX(1);
		scaleDown.setToY(1);
		scaleDown.setNode(btn);
		scaleDown.play();

	}
}
