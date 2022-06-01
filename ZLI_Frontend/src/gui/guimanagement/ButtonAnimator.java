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
	private static String hoverColor = "-fx-background-color: #469899;";
	private static String normalColor = "-fx-background-color: #05595B;";
	private static String disabledColor = "-fx-background-color: #033436;";
	private static ButtonHandler animationHandler = new ButtonHandler();

	/**
	 * Handles mouse events for the grid buttons.
	 */
	private static class ButtonHandler implements EventHandler<MouseEvent>
	{
		@Override
		public void handle(MouseEvent event)
		{
			Button btn = (Button) event.getSource();

			// Animations.
			if (event.getEventType() == MouseEvent.MOUSE_ENTERED)
				scaleUp(btn);
			if (event.getEventType() == MouseEvent.MOUSE_EXITED)			
				scaleDown(btn);

		}
	}

	public static void addButtonAnimations(Button... buttons)
	{
		for (Button button : buttons)
		{
			button.disabledProperty().addListener(new ChangeListener<Boolean>()
			{

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
				{
					if (newValue)
					{
						button.setStyle(normalColor);
					} else
					{
						button.setStyle(disabledColor);
					}
				}
			});
			button.setOnMouseEntered(animationHandler);
			button.setOnMouseExited(animationHandler);
			button.focusedProperty().addListener(new ChangeListener<Boolean>()
			{

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
				{
					if (newValue)
					{
						scaleUp(button);
					} else
					{
						scaleDown(button);
					}
				}
			});
		}
	}

	private static void scaleUp(Button btn)
	{
			ScaleTransition scaleUp = new ScaleTransition();
			btn.setStyle(hoverColor);
			scaleUp.setDuration(Duration.millis(100));
			scaleUp.setFromX(1);
			scaleUp.setFromY(1);
			scaleUp.setToX(BUTTON_SCALE_PERCENT);
			scaleUp.setToY(BUTTON_SCALE_PERCENT);
			scaleUp.setNode(btn);
			scaleUp.play();
	}

	private static void scaleDown(Button btn)
	{
		
			ScaleTransition scaleDown = new ScaleTransition();
			btn.setStyle(normalColor);
			scaleDown.setDuration(Duration.millis(100));
			scaleDown.setFromX(BUTTON_SCALE_PERCENT);
			scaleDown.setFromY(BUTTON_SCALE_PERCENT);
			scaleDown.setToX(1);
			scaleDown.setToY(1);
			scaleDown.setNode(btn);
			scaleDown.play();
		
	}
}
