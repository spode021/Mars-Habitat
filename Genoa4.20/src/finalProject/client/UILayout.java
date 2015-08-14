package finalProject.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UILayout {

	private final DockLayoutPanel dockPanel = new DockLayoutPanel(Unit.EM);
	private final ScrollPanel centralArea = new ScrollPanel();
	private final VerticalPanel leftArea = new VerticalPanel();
	private final VerticalPanel rightArea = new VerticalPanel();
	private final VerticalPanel footerArea = new VerticalPanel();

	public UILayout() {
		centralArea.setSize("100%", "100%");
//		centralArea.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//		centralArea.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		centralArea.getElement().getStyle()
				.setProperty("border", "black solid 2px");
		centralArea.setStyleName ("myDecoratedPanelStyle");
		

		leftArea.setSize("100%", "100%");
		leftArea.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		leftArea.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		leftArea.getElement().getStyle()
				.setProperty("border", "black solid 2px");
		

		rightArea.setSize("100%", "100%");
		rightArea.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		rightArea.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		rightArea.getElement().getStyle()
				.setProperty("border", "black solid 2px");
		
		footerArea.setSize("100%", "100%");
		footerArea.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		footerArea.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		footerArea.getElement().getStyle()
				.setProperty("border", "black solid 2px");
		
		HTML header = new HTML("");
		//HTML footer = new HTML("Copyright &#xA9; 2015 Spring Software Engineering, Genoa: Jackson, Phil, Andrew, Penghuan");
		//footer.getElement().getStyle().setProperty("border", "black solid 2px");
		//footer.getElement().getStyle().setBackgroundImage("images/headerbackground.jpg");
		
		dockPanel.addNorth(header, 8);
		dockPanel.addSouth(footerArea, 3);
		dockPanel.addEast(rightArea, 15);
		dockPanel.addWest(leftArea, 18);
		dockPanel.add(centralArea);
	}
	
	public DockLayoutPanel getUILayout(){
		return dockPanel;
	}
	
	public void setModuleLogPanel(ModuleLogPanel mlp){
		centralArea.add(mlp.getPanel());
	}

	public void setLoginPanel(LoginPanel lp){
		leftArea.add(lp.getLoginPanel());
	}
	
	public void setWeatherPanel(WeatherPanel wp){
		rightArea.add(wp.getWeatherPanel());
	}
	
	public void setSunsetPanel(SunsetPanel sp){
		rightArea.add(sp.getSunsetPanel());
	}
	
	public ScrollPanel getCentralArea(){
		return centralArea;
	}

	public void setMainPanel(MainPanel mp){
		centralArea.add(mp.getMainPanel());
	}
	
	public void setFooter(FooterPanel fp){
		footerArea.add(fp.getPanel());
	}
}
