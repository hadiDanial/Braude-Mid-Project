package controllers;

import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class TaskScheduler
{
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

	public static void scheduleReportGeneration()
	{
		ReportsController reportController = ReportsController.getInstance();
		final Runnable reportGenerator = new Runnable()
		{
			
			@Override
			public void run()
			{
			}
		};
		final ScheduledFuture<?> reportsHandle = scheduler.scheduleAtFixedRate(reportGenerator, 0, 10, SECONDS);
	}
}
