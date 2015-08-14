package finalProject.client;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginPanel {

	private final VerticalPanel loginPanel = new VerticalPanel();
	private final HorizontalPanel userName = new HorizontalPanel();
	private final HorizontalPanel passWord = new HorizontalPanel();
	private final TextBox utb = new TextBox();
	private final PasswordTextBox ptb = new PasswordTextBox();
	private final Button loginButton = new Button("Login");
	private final Label ul = new Label("User Name: ");
	private final Label pl = new Label("Password  : ");
	final MainPanel mp = new MainPanel();
	
	@SuppressWarnings("deprecation")
	public LoginPanel() {
//		final Button loginButton = new Button("Login");
		// create sound controller
		final SoundController soundController = new SoundController();
		final Sound sound = soundController.createSound(
				Sound.MIME_TYPE_AUDIO_WAV_PCM, "sound/login.wav");

		loginButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String u = utb.getText();
				String p = ptb.getText();

				if (u.equals("hello") && p.equals("world")) {
					loginPanel.setVisible(false);
					mp.setMainPanelVisible(true);
					utb.setText("");
					ptb.setText("");
					// mp.setModuleLogPanelVisible(false);
					mp.setLoginPanel(loginPanel);
					sound.play();

				} else {
					Window.alert("Incorrect username or password");
				}
			}
		});

		// Add labels and text_boxes to panels
		userName.add(ul);
		userName.add(utb);
		passWord.add(pl);
		passWord.add(ptb);
		// Add everything needed to login panel
		loginPanel.add(userName);
		loginPanel.add(passWord);
		loginPanel.add(loginButton);
	}

	public VerticalPanel getLoginPanel(MainPanel mp) {
		// TODO Auto-generated method stub
		return loginPanel;
	}

	public VerticalPanel getLoginPanel() {
		// TODO Auto-generated method stub
		return loginPanel;
	}

	public MainPanel getMainPanel() {
		return mp;
	}

}
