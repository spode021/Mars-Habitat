package finalProject.client;

import java.util.ArrayList;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;

public class ModuleLogPanel extends HorizontalPanel {

	private int delInt = -1;
	static VerticalPanel logPanel = new VerticalPanel();
	static VerticalPanel detailPanel = new VerticalPanel();
	static VerticalPanel detailButtonPanel = new VerticalPanel();
	static HorizontalPanel mainPanel = new HorizontalPanel();
	Storage moduleStore = null;
	String singleModule = "";
	final ListBox modList = new ListBox();
	ArrayList<Module> moduleList = new ArrayList<Module>();
	OptimalPathPanel optimalPath = null;
//	MiniConfig miniConfig1 = new MiniConfig("miniConfig1.json");
//	MiniConfig miniConfig2 = new MiniConfig("miniConfig2.json");
	final Button mini1 = new Button("Load Mini Config1");
	final Button mini2 = new Button("Load Mini Config2");
	int numOfMiniAlert = 0;

	public ModuleLogPanel(OptimalPathPanel optPath) {

		// make text boxes to enter module codes and coordinates as well as
		// labels for all inputs
		final TextBox code = new TextBox();
		final TextBox xcoord = new TextBox();
		final TextBox ycoord = new TextBox();
		final Label codeLabel = new Label("Module Code Number");
		final Label xLabel = new Label("X Coordinate");
		final Label yLabel = new Label("Y Coordinate");
		final Label statLabel = new Label("Status");
		final Label orientLabel = new Label("Orientation");
		final Button submit = new Button("Submit");
		final Button delete = new Button("Delete");
		final Label moduleDetailsLabel = new Label("Module Details");
		optimalPath = optPath;

		// create sound controller
		final SoundController soundController = new SoundController();
		final Sound sound = soundController.createSound(
				Sound.MIME_TYPE_AUDIO_WAV_PCM, "sound/modlogpass.wav");

		moduleStore = Storage.getLocalStorageIfSupported();

		// ===========================================================
		// module Listbox to dispaly module details

		modList.setVisibleItemCount(5);
		modList.setSize("200px", "200px");
		modList.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				delInt = modList.getSelectedIndex();
				delete.setEnabled(true);
			}

		});

		// create dropdown menus for status and orientation
		final ListBox statusBox = new ListBox();
		statusBox.addItem("USABLE");
		statusBox.addItem("UNUSABLE");
		statusBox.addItem("NEEDS REPAIR");
		statusBox.setVisibleItemCount(1);

		final ListBox oriBox = new ListBox();
		oriBox.addItem("0");
		oriBox.addItem("1");
		oriBox.addItem("2");
		oriBox.setVisibleItemCount(1);

		// create a button to submit module data
		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (code.getText() != "" && xcoord.getText() != ""
						&& ycoord.getText() != "") {
					Integer optionIndex = statusBox.getSelectedIndex();
					String statusValue = statusBox.getValue(optionIndex);

					optionIndex = oriBox.getSelectedIndex();
					String orientationValue = oriBox.getValue(optionIndex);

					Module mod = new Module(Integer.parseInt(code.getText()),
							Double.parseDouble(xcoord.getText()), Double
									.parseDouble(ycoord.getText()),
							statusValue, Integer.parseInt(orientationValue));

					String singleModule = mod.toString();

					if (mod.getType() != ModType.UNKNOWN) {
						moduleStore.setItem("module" + mod.getX().toString()
								+ mod.getY().toString(), singleModule);

						updateModuleDetails();

						code.setText("");
						xcoord.setText("");
						ycoord.setText("");
						sound.play();

						optimalPath.updateCanvas(moduleList);
					}
				}
			}
		});

		delete.setEnabled(false);
		delete.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if (moduleStore != null) {
					modList.removeItem(delInt);
					moduleStore.removeItem(moduleStore.key(delInt + 1));
					updateModuleDetails();
				}
				delete.setEnabled(false);
			}

		});

		// ============================================================
		// Create button to load current local store modules
		Button loadModules = new Button("Load Local Modules");
		loadModules.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				updateModuleDetails();
				optimalPath.updateCanvas(moduleList);
			}
		});

		// Add a button for mini config 1
		mini1.setEnabled(false);
		mini1.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				// System.out.println(miniConfig.getMiniConfigList());
	//			optimalPath.updateCanvas(miniConfig1.getMiniConfigList());
			}

		});

		// Add a button for mini config 2
		mini2.setEnabled(false);
		mini2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				// System.out.println(miniConfig.getMiniConfigList());
	//			optimalPath.updateCanvas(miniConfig2.getMiniConfigList());
			}

		});
		// ============================================================
		// Create button to load current gps modules

		final ListBox gpsBox = new ListBox();
		gpsBox.addItem("1");
		gpsBox.addItem("2");
		gpsBox.addItem("3");
		gpsBox.addItem("4");
		gpsBox.addItem("5");
		gpsBox.addItem("6");
		gpsBox.addItem("7");
		gpsBox.addItem("8");
		gpsBox.addItem("9");
		gpsBox.addItem("10");
		gpsBox.setVisibleItemCount(1);

		Button gpsModules = new Button("Load GPS Modules");
		gpsModules.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				Integer gpsIndex = gpsBox.getSelectedIndex();
				String gpsValue = gpsBox.getValue(gpsIndex);

				updateGPSModuleDetails(gpsValue);

				optimalPath.updateCanvas(moduleList);

			}
		});

		// create a panel for all to go in and add to rootpanel

		logPanel.add(codeLabel);
		logPanel.add(code);
		logPanel.add(xLabel);
		logPanel.add(xcoord);
		logPanel.add(yLabel);
		logPanel.add(ycoord);
		logPanel.add(statLabel);
		logPanel.add(statusBox);
		logPanel.add(orientLabel);
		logPanel.add(oriBox);
		logPanel.add(submit);
		logPanel.add(delete);

		// detail Panel to display module details that have been logged
		detailPanel.add(moduleDetailsLabel);
		detailPanel.add(modList);

		// create button panel for interaction with module detail panel
		detailButtonPanel.add(loadModules);
		detailButtonPanel.add(mini1);
		detailButtonPanel.add(mini2);
		detailButtonPanel.add(gpsModules);
		detailButtonPanel.add(gpsBox);

		// create main panel to store all other panels in
		mainPanel.add(logPanel);
		mainPanel.add(detailPanel);
		mainPanel.add(detailButtonPanel);
	}

	public HorizontalPanel getPanel() {
		return mainPanel;
	}

	public void setVisible(boolean b) {
		mainPanel.setVisible(b);
	}

	public void updateModuleDetails() {

		if (moduleStore != null) {
			moduleList.clear();

			// remove configuration to rebuild it with more modules
			moduleStore.removeItem("config1");

			// Clear modList to re-print module details
			modList.clear();
			String module = "[";

			// build Array structure
			for (int i = 0; i < moduleStore.getLength(); i++) {
				String key = moduleStore.key(i);
				String value = moduleStore.getItem(key);
				if (i != moduleStore.getLength() - 1)
					// not last module
					module += "{" + value + "},";
				else
					// last module
					module += "{" + value + "}";
			}

			module += "]";
			moduleStore = Storage.getLocalStorageIfSupported();

			if (moduleStore != null) {
				moduleStore.setItem("config1", module);
			}

			String sConfigOne = moduleStore.getItem("config1");
			JSONArray jA = (JSONArray) JSONParser.parseLenient(sConfigOne);
			JSONNumber jN;
			JSONString jS;
			Double codeNumber;
			String status;
			Double xCoordinate;
			Double yCoordinate;
			Double orientation;

			int numPLAIN = 0;
			int numDORM = 0;
			int numPOWER = 0;
			int numCONTROL = 0;
			int numCANTEEN = 0;
			int numSAN = 0;
			int numFOOD = 0;
			int numGYM = 0;
			int numAIRLOCK = 0;
			int numMED = 0;
			for (int i1 = 0; i1 < jA.size(); i1++) {

				JSONObject jO = (JSONObject) jA.get(i1);

				jN = (JSONNumber) jO.get("code");
				codeNumber = jN.doubleValue();

				jS = (JSONString) jO.get("status");
				status = jS.stringValue();

				jN = (JSONNumber) jO.get("turns");
				orientation = jN.doubleValue();

				jN = (JSONNumber) jO.get("X");
				xCoordinate = jN.doubleValue();

				jN = (JSONNumber) jO.get("Y");
				yCoordinate = jN.doubleValue();

				Module mod1 = new Module(codeNumber.intValue(), xCoordinate,
						yCoordinate, status, orientation.intValue());

				moduleList.add(mod1);

				modList.insertItem("module (" + mod1.getX() + "," + mod1.getY()
						+ "): " + mod1.getType().toString() + "\n",
						modList.getItemCount());
				optimalPath.updateCanvas(moduleList);

				if (mod1.getType().toString() == "PLAIN")
					numPLAIN++;
				else if (mod1.getType().toString() == "DORM")
					numDORM++;
				else if (mod1.getType().toString() == "SAN")
					numSAN++;
				else if (mod1.getType().toString() == "FOOD")
					numFOOD++;
				else if (mod1.getType().toString() == "GYM")
					numGYM++;
				else if (mod1.getType().toString() == "CANTEEN")
					numCANTEEN++;
				else if (mod1.getType().toString() == "POWER")
					numPOWER++;
				else if (mod1.getType().toString() == "CONTROL")
					numCONTROL++;
				else if (mod1.getType().toString() == "AIRLOCK")
					numAIRLOCK++;
				else if (mod1.getType().toString() == "MED")
					numMED++;

				if (numPLAIN > 2 && numDORM > 0 && numSAN > 0 && numFOOD > 0
						&& numCANTEEN > 0 && numPOWER > 0 && numCONTROL > 0
						&& numAIRLOCK > 0) {
					mini1.setEnabled(true);
					mini2.setEnabled(true);
					numOfMiniAlert++;
					if (numOfMiniAlert == 1)
						Window.alert("Minimum Configuartion Achieved");
				} else {
					mini1.setEnabled(false);
					mini2.setEnabled(false);
				}
			}
		}
	}

	// getting JSON data from gps
	public void updateGPS(String rt) {
		if (moduleStore != null) {

			moduleList.clear();

			// remove configuration to rebuild it with more modules
			moduleStore.removeItem("config1");

			// Clear modList to re-print module details
			modList.clear();
			String module = "[";

			// build Array structure
			for (int i = 0; i < moduleStore.getLength(); i++) {

				String key = moduleStore.key(i);
				String value = moduleStore.getItem(key);
				if (i != moduleStore.getLength() - 1)
					// not last module
					module += "{" + value + "},";
				else
					// last module
					module += "{" + value + "}";
			}

			module += "]";
			moduleStore = Storage.getLocalStorageIfSupported();

			if (moduleStore != null) {

				moduleStore.setItem("config1", module);
			}

			String sAll = rt;
			JSONArray jA = (JSONArray) JSONParser.parseLenient(sAll);
			JSONNumber jN;
			JSONString jS;
			Double codeNumber;
			String status;
			Double xCoordinate;
			Double yCoordinate;
			Double orientation;

			for (int i1 = 0; i1 < jA.size(); i1++) {

				JSONObject jO = (JSONObject) jA.get(i1);

				jN = (JSONNumber) jO.get("code");
				codeNumber = jN.doubleValue();

				jS = (JSONString) jO.get("status");
				status = jS.stringValue();

				jN = (JSONNumber) jO.get("turns");
				orientation = jN.doubleValue();

				jN = (JSONNumber) jO.get("X");
				xCoordinate = jN.doubleValue();

				jN = (JSONNumber) jO.get("Y");
				yCoordinate = jN.doubleValue();

				Module mod1 = new Module(codeNumber.intValue(), xCoordinate,
						yCoordinate, status, orientation.intValue());

				moduleList.add(mod1);

				modList.insertItem("module (" + mod1.getX() + "," + mod1.getY()
						+ "): " + mod1.getType().toString() + "\n",
						modList.getItemCount());
				optimalPath.updateCanvas(moduleList);
			}
			String sConfigOne = moduleStore.getItem("config1");
			jA = (JSONArray) JSONParser.parseLenient(sConfigOne);

/*			for (int i1 = 0; i1 < jA.size(); i1++) {

				JSONObject jO = (JSONObject) jA.get(i1);

				jN = (JSONNumber) jO.get("code");
				codeNumber = jN.doubleValue();

				jS = (JSONString) jO.get("status");
				status = jS.stringValue();

				jN = (JSONNumber) jO.get("turns");
				orientation = jN.doubleValue();

				jN = (JSONNumber) jO.get("X");
				xCoordinate = jN.doubleValue();

				jN = (JSONNumber) jO.get("Y");
				yCoordinate = jN.doubleValue();

				Module mod1 = new Module(codeNumber.intValue(), xCoordinate,
						yCoordinate, status, orientation.intValue());

				moduleList.add(mod1);

				modList.insertItem("module (" + mod1.getX() + "," + mod1.getY()
						+ "): " + mod1.getType().toString() + "\n",
						modList.getItemCount());
				optimalPath.updateCanvas(moduleList);
			}
		*/
		}
	}

	public void updateGPSModuleDetails(String testCase) {
		String proxy = "http://d.umn.edu/~houst087/war/Mars/Proxy.php?url=";
		String url = proxy + "http://www.d.umn.edu/~abrooks/SomeTests.php?q="
				+ testCase;

		// Send request to server and catch any errors.
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
			Request request = builder.sendRequest(null, new RequestCallback() {

				public void onError(Request request, Throwable exception) {
					Window.alert("onError: Couldn't retrieve JSON");
				}

				public void onResponseReceived(Request request,
						Response response) {
					if (200 == response.getStatusCode()) {
						String rt = response.getText();
						updateGPS(rt); // METHOD CALL TO DO SOMETHING WITH
										// RESPONSE TEXT
					} else {
						Window.alert("Couldn't retrieve JSON ("
								+ response.getStatusText() + ")");
					}
				}
			});
		} catch (RequestException e) {
			Window.alert("RequestException: Couldn't retrieve JSON");
		}
	}

}
