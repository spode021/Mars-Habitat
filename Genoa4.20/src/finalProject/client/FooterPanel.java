package finalProject.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FooterPanel extends HorizontalPanel {
	
	final HorizontalPanel panel = new HorizontalPanel();
	final HorizontalPanel leftPanel = new HorizontalPanel();
	final HorizontalPanel timePanel = new HorizontalPanel();
	int speed = 1000;
	
	@SuppressWarnings("deprecation")
	public FooterPanel() {
		Label footer = new Label("Copyright © 2015 Spring Software Engineering, Genoa: Jackson, Phil, Andrew, Penghuan");
		footer.setStyleName("copyright");
		
		panel.addStyleName("panel");
		panel.setSize("100%", "100%");
		
		leftPanel.addStyleName("leftFooter");
		leftPanel.setSize("100%", "100%");
		leftPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		leftPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		
		timePanel.addStyleName("timePanel");
		//timePanel.setSize("100%", "100%");
		//timePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		//timePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		
		// Timer for clock
		final Timer t = new Timer() {
			Label timeLabel = new Label("Time: ");
			Label time = new Label();
			int sec = 0, min = 0, hour = 0, day = 0;
			
			@Override
			public void run() {
				sec++;
				if (sec == 60) {
					min++;
					sec = 0;
				}
				if (min == 60) {
					hour++;
					min = 0;
				}
				if (hour == 24) {
					day++;
					hour = 0;
				}
				if (day == 10) {
					time.setText("10:0:0:0");
					Window.alert("	     10 Day Alert\nRecalibrate lift rover milometer.");
					day = 0;
				}
				
				// 10 day alert simulation
				timeLabel.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						sec = 0; min = 0; hour = 0; day = 10;	
					}
				});
				
				time.setText("" + day + ":" + hour + ":" + min + ":" + sec);
				time.addStyleName("time");
				timePanel.add(timeLabel);
				timePanel.add(time);
			}
		};
		
		t.scheduleRepeating(1000);
		
		leftPanel.add(footer);
		
		panel.add(leftPanel);
		panel.add(timePanel);
		
		RootPanel.get().add(panel);
	}
	
	public HorizontalPanel getPanel() {
		return panel;
	}
}
