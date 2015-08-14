package finalProject.client;

import finalProject.client.WeatherPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
//hello
public class Genoa4_20 implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		System.out.println("Start");
		// Login Window Variables
		final LoginPanel lp = new LoginPanel();
		
		// UILayout
		final UILayout ui = new UILayout();
		
		// ===================================================================
		// Login Window

		// ===================================================================
		// Menu Window
		lp.getMainPanel().setMainPanelVisible(false);

		// ===================================================================
		// Log Module Details

		// ===================================================================
		// Weather & Sunset Time
		WeatherPanel wp = new WeatherPanel();
		SunsetPanel sp = new SunsetPanel();

		// ===================================================================
		// Footer
		FooterPanel fp = new FooterPanel();
		
		// Attach five widgets to a DockLayoutPanel, one in each direction. Lay
		// them out in 'em' units.
		
		ui.setLoginPanel(lp);
		ui.setMainPanel(lp.getMainPanel());
//		ui.setModuleLogPanel(lp.getMenuPanel().getModuleLogPanel());
		ui.setWeatherPanel(wp);
		ui.setSunsetPanel(sp);
		ui.setFooter(fp);

		RootLayoutPanel rp = RootLayoutPanel.get();
		rp.addStyleName("translate");
		rp.add(ui.getUILayout());
	}

}
