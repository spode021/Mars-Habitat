package finalProject.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Image;

public class ConfigHabitatPanel {

	static VerticalPanel configButtonPanel = new VerticalPanel();

	public ConfigHabitatPanel() {
		Button mini1 = new Button("Mini Config 1");
		Button mini2 = new Button("Mini Config 2");
		Button full1 = new Button("Full Config 1");
		Button full2 = new Button("Full Config 2");
		Button manual = new Button("Manually Drag");

		final AbsolutePanel picPanel = new AbsolutePanel();
		picPanel.setPixelSize(4000, 3000);
		final Image configImage = new Image();

		mini1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// configImage = new Image("images/miniConfig1.png");
				picPanel.clear();
				picPanel.add(new Image("images/miniConfig1.png"));
			}

		});

		mini2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				picPanel.clear();
				picPanel.add(new Image("images/miniConfig2.png"));
			}

		});

		full1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				picPanel.clear();
				picPanel.add(new Image("images/fullConfig1.png"));

			}

		});

		full2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				picPanel.clear();
				picPanel.add(new Image("images/fullConfig2.png"));

			}

		});

		manual.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				picPanel.clear();

//				PickupDragController dragController = new PickupDragController(
//						picPanel, true);

				List<String> aList = new ArrayList<String>(Arrays.asList(
						"Airlock", "Canteen", "Control", "Dormitory", "Food",
						"Gym", "Medical", "Plain", "Power","Sanitation" ));
				
				List<Image> otherList = new ArrayList<Image>();
				
				for(int i =0; i<10; i++){
					String str = aList.get(i);
					otherList.add(new Image("images/moduleImages/" + str + ".jpg"));
					otherList.get(i).setPixelSize(45, 45);
		//			dragController.makeDraggable(otherList.get(i));
					picPanel.add(otherList.get(i));
				}

			}

		});

		configButtonPanel.add(mini1);
		configButtonPanel.add(mini2);
		configButtonPanel.add(full1);
		configButtonPanel.add(full2);
		configButtonPanel.add(manual);
		configButtonPanel.add(picPanel);

	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		configButtonPanel.setVisible(b);

	}

	public Widget getPanel() {
		// TODO Auto-generated method stub
		return configButtonPanel;
	}
}
