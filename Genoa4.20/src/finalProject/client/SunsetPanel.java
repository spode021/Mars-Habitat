package finalProject.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SunsetPanel {

	private final static VerticalPanel sp = new VerticalPanel();
	
	public SunsetPanel() {
		// ===================================================================
		// Sunset
		String url = "http://api.wunderground.com/api/50b68c5ec9345027/astronomy/q/55812.json";
		url = URL.encode(url);
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
						update(rt); // METHOD CALL TO DO SOMETHING WITH RESPONSE
						// // TEXT
					} else {
						Window.alert("Couldn't retrieve JSON ("
								+ response.getStatusCode() + ")");
					}
				}
			});
		} catch (RequestException e) {
			Window.alert("RequestException: Couldn't retrieve JSON");
		}
	}

	@SuppressWarnings("deprecation")
	public void update(String rt) {		

		String sAll = rt;
		JSONObject jA = (JSONObject) JSONParser.parseLenient(sAll);
		JSONValue jTry = jA.get("sun_phase");
		JSONObject jB = (JSONObject) JSONParser.parseLenient(jTry.toString());
		JSONValue sunset = jB.get("sunset");

		String sunset_JSON = sunset.toString();
		
		JSONObject jsA = (JSONObject) JSONParser.parseLenient(sunset_JSON);
		JSONValue hour = jsA.get("hour");
		JSONValue min = jsA.get("minute");
		String hour_Str = hour.toString();
		String sub_hour = hour_Str.substring(1, hour_Str.length()-1);
		
		String min_Str = min.toString();
		String sub_min = min_Str.substring(1, min_Str.length()-1);
		
		Label time = new Label(sub_hour + ":" + sub_min);

		Image sunset_Img = new Image(
				"images/Sunset.jpg");
		sunset_Img.setPixelSize(180, 80);
		sp.add(sunset_Img);
		sp.add(new Label("Sunset Time:"));
		sp.add(time);

	}

	public static VerticalPanel getSunsetPanel() {
		return sp;
	}
}
