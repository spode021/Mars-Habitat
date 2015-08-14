package finalProject.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainPanel {
	
	private VerticalPanel mainPanel = new VerticalPanel();
	
	private HorizontalPanel menuPanel = new HorizontalPanel();
	private HorizontalPanel showPanel = new HorizontalPanel();
	
	private final Button logModuleDetails = new Button("Log Module Details");
	private final Button findOptimalPath = new Button("Find Optimal Path");
	private final Button configureHabitat = new Button("Configure Habitat");
	private final Button logout = new Button("Logout");
	
	private OptimalPathPanel optimalPathPanel = new OptimalPathPanel();
	private ModuleLogPanel moduleLogPanel = new ModuleLogPanel(optimalPathPanel);
	private ConfigHabitatPanel configHabitatPanel = new ConfigHabitatPanel();

	
	//used for logout
	private VerticalPanel loginPanel;
	
	public MainPanel(){
		logModuleDetails.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
//				menuPanel.setVisible(false);
//				showPanel.add(moduleLogPanel);
				showPanel.clear();
				showPanel.add(moduleLogPanel.getPanel());
				showPanel.setVisible(true);
//				moduleLogPanel.setVisible(true);
			}
		});

		findOptimalPath.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showPanel.clear();
				showPanel.add(optimalPathPanel.getPanel());
				showPanel.setVisible(true);
			}

		});

		configureHabitat.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				showPanel.clear();
				showPanel.add(configHabitatPanel.getPanel());

			}
		});
		
		logout.addClickHandler(new ClickHandler(){
		
			public void onClick(ClickEvent event){
				//TODO----------------------------
				loginPanel.setVisible(true);
//				Window.Location.reload();
				setMainPanelVisible(false);
//				setModuleLogPanelVisible(false);
			}
		});
		
		
		menuPanel.add(logModuleDetails);
		menuPanel.add(findOptimalPath);
		menuPanel.add(configureHabitat);
		menuPanel.add(logout);
		
		mainPanel.add(menuPanel);
		mainPanel.add(showPanel);

	}

	public VerticalPanel getMainPanel() {
		// TODO Auto-generated method stub 
		return mainPanel;
	}
		
	public void setMenuPanel(HorizontalPanel menuPanel){
		this.menuPanel = menuPanel;
	}
	
	public ModuleLogPanel getModuleLogPanel(){
		return moduleLogPanel;
	}
		
	public OptimalPathPanel getOptimalPathPanel(){
		return optimalPathPanel;		
	}
	
	public void setMainPanelVisible(boolean b){
		showPanel.setVisible(b);
		menuPanel.setVisible(b);
	}
	
	//used for logout button
	public void setLoginPanel(VerticalPanel lp){
		loginPanel = lp;
	}
}
