package finalProject.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class OptimalPathPanel extends HorizontalPanel {

	// variables
	static VerticalPanel buttonPanel = new VerticalPanel();
	static ScrollPanel canvasScrollPanel = new ScrollPanel();
	static VerticalPanel canvasPanel = new VerticalPanel();
	static VerticalPanel rulePanel = new VerticalPanel();
	static HorizontalPanel mainPanel = new HorizontalPanel();

	final Label roverXLabel = new Label("Rover X Coord:");
	final TextBox roverXTextBox = new TextBox();
	
	final Label roverYLabel = new Label("Rover Y Coord:");
	final TextBox roverYTextBox = new TextBox();
	
	// labels for the coordinates of the map
	final Label xCoord = new Label("X Coordinate:");
	final Label yCoord = new Label("Y Coordinate:");
	final Label xLabel = new Label();
	final Label yLabel = new Label();

	final HashMap<String, Module> moduleInfoHashMap = new HashMap<String, Module>();
	
	ArrayList<Module> moduleList = new ArrayList<Module>();

	ArrayList<Module> roverPathList = new ArrayList<Module>();
	
	Storage moduleStore = null;

	// parameters for canvas
	// the pixel space in between each grid line
	static final int gridWidth = 50;
	static final int canvasHeight = 50 * gridWidth;
	static final int canvasWidth = 100 * gridWidth;

	// parameters for canvas scroll panel
	static final int canvasScrollPanelHeight = 425;
	static final int canvasScrollPanelWidth = 600;

	public Sound sound;

	// length of canvas. It is implied that the canvas will be a square
	Canvas canvas;
	Context2d context;
	Grid mapGrid = new Grid(canvasWidth, canvasHeight);

	// create sound controller
	final SoundController soundController = new SoundController();

	// color of background
	final CssColor colorBrown = CssColor.make("brown");

	// color of grid lines
	final CssColor colorBlack = CssColor.make("black");

	//colors for optimal path drawing
	final CssColor colorBlue = CssColor.make("blue");
	final CssColor colorGreen = CssColor.make("green");
	// ||=================================================================
	// Image resources for map
	final Image airlock = new Image("images/mapImages/AirlockMap.jpg");
	final ImageElement airlockMap = ImageElement.as(airlock.getElement());

	final Image canteen = new Image("images/mapImages/CanteenMap.jpg");
	final ImageElement canteenMap = ImageElement.as(canteen.getElement());

	final Image control = new Image("images/mapImages/ControlMap.jpg");
	final ImageElement controlMap = ImageElement.as(control.getElement());

	final Image dormitory = new Image("images/mapImages/DormitoryMap.jpg");
	final ImageElement dormitoryMap = ImageElement.as(dormitory.getElement());

	final Image gym = new Image("images/mapImages/GymMap.jpg");
	final ImageElement gymMap = ImageElement.as(gym.getElement());

	final Image food = new Image("images/mapImages/FoodMap.jpg");
	final ImageElement foodMap = ImageElement.as(food.getElement());

	final Image medical = new Image("images/mapImages/MedicalMap.jpg");
	final ImageElement medicalMap = ImageElement.as(medical.getElement());

	final Image plain = new Image("images/mapImages/PlainMap.jpg");
	final ImageElement plainMap = ImageElement.as(plain.getElement());

	final Image power = new Image("images/mapImages/PowerMap.jpg");
	final ImageElement powerMap = ImageElement.as(power.getElement());

	final Image sanitation = new Image("images/mapImages/SanitationMap.jpg");
	final ImageElement sanitationMap = ImageElement.as(sanitation.getElement());

	final Image rover = new Image("images/mapImages/MarsRoverMap.jpg");
	final ImageElement roverMap = ImageElement.as(rover.getElement());
	// ========================================================================||

	TextArea ruleArea = new TextArea();
	String rule = "Rules: \n"
			+ "1.Sanitation not next to Canteen.\n"
			+ "2.Sanitation not next to Food & Water Storage.\n"
			+ "3.Airlock not next to Dormitory.\n"
			+ "4.At least one part of the habitat viewable from another part of the habitat.";

	public OptimalPathPanel() {

		moduleStore = Storage.getLocalStorageIfSupported();

		final Label title = new Label("Find Optimal Path");

		

		canvasScrollPanel.setSize(canvasScrollPanelWidth + "px",
				canvasScrollPanelHeight + "px");

		canvas = Canvas.createIfSupported();

		canvas.setSize(canvasWidth + "px", canvasHeight + "px");
		canvas.setCoordinateSpaceHeight(canvasHeight);
		canvas.setCoordinateSpaceWidth(canvasWidth);

		 //set size of rover X text box and rover Y text box
	    roverXTextBox.setSize(50 + "px", 10 + "px");
	    roverYTextBox.setSize(40 + "px", 10 + "px");
		
		context = canvas.getContext2d();

		// create a button to find optimal path
		final Button find = new Button("Find Path");
		find.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//if(roverXTextBox.getSelectedText().isEmpty() == false && roverYTextBox.getSelectedText().isEmpty() == false)
				//{
					sound = soundController.createSound(
						Sound.MIME_TYPE_AUDIO_WAV_PCM, "sound/oppath.wav");
					sound.play();
						
					Double roverX = (double)  Double.parseDouble(roverXTextBox.getText());
					Double roverY = (double) Double.parseDouble(roverYTextBox.getText());
						
					
					
					
					drawRoverPath(roverX, roverY);
				//}
			}	
		});
		
		drawCanvas();

		canvas.addMouseMoveHandler(new MouseMoveHandler() {

			@Override
			public void onMouseMove(MouseMoveEvent event) {
				Integer xPos = (event.getX() / gridWidth) + 1;
				Integer yPos = ((canvasHeight - event.getY()) / gridWidth) + 1;

				xLabel.setText(xPos.toString());
				yLabel.setText(yPos.toString());

			}
		});

		ruleArea.setText(rule);
		ruleArea.setPixelSize(200, 400);
		rulePanel.add(ruleArea);

		buttonPanel.add(title);
		buttonPanel.add(find);

		buttonPanel.add(roverXLabel);
	    buttonPanel.add(roverXTextBox);
	    buttonPanel.add(roverYLabel);
	    buttonPanel.add(roverYTextBox);

		buttonPanel.add(xCoord);
		buttonPanel.add(xLabel);
		buttonPanel.add(yCoord);
		buttonPanel.add(yLabel);

		canvasScrollPanel.add(canvas);
		canvasPanel.add(canvasScrollPanel);

		mainPanel.add(buttonPanel);
		mainPanel.add(canvasPanel);
		mainPanel.add(rulePanel);

		// RootPanel.get().add(plain);
		plain.setVisible(false);
		// RootPanel.get().add(airlock);
		airlock.setVisible(false);

	}

	// ============================================
	// draws canvas
	public void drawCanvas() {
		context.beginPath();

		// make background
		context.setFillStyle(colorBrown);
		context.fillRect(0, 0, canvasWidth, canvasHeight);

		// set color for grid
		context.setFillStyle(colorBlack);

		context.moveTo(0, 0);

		// draw outline of canvas
		context.setStrokeStyle(colorBlack);
		context.lineTo(0, canvasHeight);
		context.lineTo(canvasHeight, canvasWidth);
		context.lineTo(canvasHeight, 0);
		context.lineTo(0, 0);

		// draw grid lines
		drawGrid();

		context.stroke();
		context.closePath();
	}

	// =============================================
	// draws grid for map where modules can be placed
	public void drawGrid() {

		for (int i = gridWidth; i < canvasHeight; i += gridWidth) {
			context.moveTo(0, i);
			context.lineTo(canvasWidth, i);
		}

		for (int i = gridWidth; i < canvasWidth; i += gridWidth) {
			context.moveTo(i, 0);
			context.lineTo(i, canvasHeight);
		}

		context.stroke();
	}

	// ============================================
	// draw modules on map
	public void updateCanvas(ArrayList<Module> modList) {

		moduleList = modList;

		clearCanvas();

		drawCanvas();

		for (int i = 0; i < moduleList.size(); i++) {
			if (moduleList.get(i).getType() == ModType.AIRLOCK) {
				context.drawImage(airlockMap,
						(moduleList.get(i).getX() - 1) * 50, canvasHeight
								- (moduleList.get(i).getY() * 50));
				airlock.setVisible(true);
			} else if (moduleList.get(i).getType() == ModType.CANTEEN) {
				context.drawImage(canteenMap,
						(moduleList.get(i).getX() - 1) * 50, canvasHeight
								- (moduleList.get(i).getY() * 50));
				canteen.setVisible(true);
			} else if (moduleList.get(i).getType() == ModType.CONTROL) {
				context.drawImage(controlMap,
						(moduleList.get(i).getX() - 1) * 50, canvasHeight
								- (moduleList.get(i).getY() * 50));
				control.setVisible(true);
			} else if (moduleList.get(i).getType() == ModType.DORM) {
				context.drawImage(dormitoryMap,
						(moduleList.get(i).getX() - 1) * 50, canvasHeight
								- (moduleList.get(i).getY() * 50));
				dormitory.setVisible(true);
			} else if (moduleList.get(i).getType() == ModType.FOOD) {
				context.drawImage(foodMap, (moduleList.get(i).getX() - 1) * 50,
						canvasHeight - (moduleList.get(i).getY() * 50));
				food.setVisible(true);
			} else if (moduleList.get(i).getType() == ModType.GYM) {
				context.drawImage(gymMap, (moduleList.get(i).getX() - 1) * 50,
						canvasHeight - (moduleList.get(i).getY() * 50));
				gym.setVisible(true);
			} else if (moduleList.get(i).getType() == ModType.MED) {
				context.drawImage(medicalMap,
						(moduleList.get(i).getX() - 1) * 50, canvasHeight
								- (moduleList.get(i).getY() * 50));
				medical.setVisible(true);
			} else if (moduleList.get(i).getType() == ModType.PLAIN) {
				context.drawImage(plainMap,
						(moduleList.get(i).getX() - 1) * 50, canvasHeight
								- (moduleList.get(i).getY() * 50));
				plain.setVisible(true);
			} else if (moduleList.get(i).getType() == ModType.POWER) {
				context.drawImage(powerMap,
						(moduleList.get(i).getX() - 1) * 50, canvasHeight
								- (moduleList.get(i).getY() * 50));
				power.setVisible(true);
			} else if (moduleList.get(i).getType() == ModType.SAN) {
				context.drawImage(sanitationMap,
						(moduleList.get(i).getX() - 1) * 50, canvasHeight
								- (moduleList.get(i).getY() * 50));
				sanitation.setVisible(true);
			}

		}
	}
	
	//============================================
	//draws rover path to all modules
	public void drawRoverPath(Double roverXVal, Double roverYVal){
		
		if (moduleList.size() != 0){
			
			//clear path list to make new path.
			roverPathList.clear();
			
			clearCanvas();
		
			drawCanvas();
		
			updateCanvas(moduleList);
		
			//use a rover module to sort and draw path to all modules
			Module roverMod = new Module(500, roverXVal, roverYVal, 
				"USABLE", 0);
		
			//add rover to beginning
			roverPathList.add(roverMod);
		
			//add modules to roverPathList
			for(Module mod: moduleList)
				roverPathList.add(mod);
		
			
			Module moduleA = new Module();
			Module moduleB = new Module();
			Module temp = new Module();
			
			for(int i = 1; i < roverPathList.size(); i++)
			{
				moduleA = roverPathList.get(i);
				
				for(int j = i; j < roverPathList.size(); j++){
					moduleB = roverPathList.get(j);
					
					if(findModDistance(roverPathList.get(i - 1).getX(), 
							roverPathList.get(i - 1).getY(), roverPathList.get(i).getX(), 
							roverPathList.get(i).getY()) 
							> findModDistance(roverPathList.get(i - 1).getX(), 
									roverPathList.get(i - 1).getY(), roverPathList.get(j).getX(), 
									roverPathList.get(j).getY()))
					{
						temp = moduleB;
						moduleB = moduleA;
						moduleA = temp;
						
						roverPathList.set(j, moduleB);
						roverPathList.set(i, moduleA);
						
					}
					
				}
				
			}
			
			
			context.beginPath();
			context.setStrokeStyle(colorBlue);
			context.setFillStyle(colorGreen);
			context.moveTo((roverXVal * gridWidth) - gridWidth/2, canvasHeight - (roverYVal * gridWidth)  + gridWidth/2);
			
			context.drawImage(roverMap,
				(roverXVal - 1) * gridWidth, canvasHeight
					- (roverYVal * gridWidth));
			rover.setVisible(true);
			
			context.setFont("bold 20px sans-serif");
			
			//draw lines and module numbers
			Double totalDist = 0.0;
			//draw lines and module numbers
			for(int i = 0; i < roverPathList.size(); i++){
				if(i >= 1)
					context.fillText(i + "", ((roverPathList.get(i).getX() * gridWidth))  
							- gridWidth/2 + 5, (canvasHeight - 
									(roverPathList.get(i).getY())* gridWidth) 
									- gridWidth/2 + 5);
				
				context.lineTo(((roverPathList.get(i).getX() * gridWidth) - gridWidth/2), 
						canvasHeight - (roverPathList.get(i).getY()* gridWidth) + gridWidth/2);
				
				if ( i == roverPathList.size() - 1 )
					totalDist += findModDistance(roverPathList.get(i).getX(), roverPathList.get(i).getY(),
						roverPathList.get(0).getX(), roverPathList.get(0).getY());
				else
					totalDist += findModDistance(roverPathList.get(i).getX(), roverPathList.get(i).getY(),
						roverPathList.get(i+1).getX(), roverPathList.get(i+1).getY());
			}
			Window.alert("Total length of path (Sizeof moving task) = " + totalDist + " units");
			
			context.setFont("bold 20px sans-serif");
			
			
			context.stroke();
			context.closePath();
		}
		
	}
	
	public Double findModDistance(Double x1, Double y1, Double x2, Double y2){
		
		Double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		
		
		return distance;
	}
	
	// ============================================
	// Clears canvas to redraw on it
	public void clearCanvas() {

		context.clearRect(0, 0, canvasWidth, canvasHeight);

	}

	public HorizontalPanel getPanel() {
		return mainPanel;
	}

	public void setVisible(boolean b) {
		mainPanel.setVisible(b);
	}
}
