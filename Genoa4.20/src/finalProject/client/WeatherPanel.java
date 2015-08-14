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

public class WeatherPanel {

	private final static VerticalPanel wp = new VerticalPanel();

	public WeatherPanel() {
		// ===================================================================
		// Weather
		String url = "http://api.wunderground.com/api/50b68c5ec9345027/conditions/q/55812.json";
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
		JSONValue jTry = jA.get("current_observation");

		JSONObject jB = (JSONObject) JSONParser.parseLenient(jTry.toString());
		JSONValue temp_c = jB.get("temp_c");
		JSONValue temp_f = jB.get("temp_f");
		JSONValue visibility = jB.get("visibility_km");
		JSONValue pressure_mb = jB.get("pressure_mb");
		JSONValue wind_mph = jB.get("wind_mph");

		String sTemp_f = temp_f.toString();
		String sTemp_c = temp_c.toString();
		String sVisibility = visibility.toString().substring(1,
				visibility.toString().length() - 1);
		String sPressure_mb = pressure_mb.toString().substring(1,
				pressure_mb.toString().length() - 1);
		String sWind_mph = wind_mph.toString();

		Image wundergroound_logo = new Image(
				"images/wundergroundLogo_4c_horz.png");
		wundergroound_logo.setPixelSize(180, 80);
		wp.add(wundergroound_logo);
		wp.add(new Label("Temperature in F:"));
		wp.add(new Label(sTemp_f));
		wp.add(new Label("Temperature in C:"));
		wp.add(new Label(sTemp_c)); // TO VIEW
		wp.add(new Label("Visibility in km:"));
		wp.add(new Label(sVisibility)); // TO VIEW
		wp.add(new Label("Pressure in mb:"));
		wp.add(new Label(sPressure_mb));
		wp.add(new Label("Wind in mph:"));
		wp.add(new Label(sWind_mph));
	}

	public static VerticalPanel getWeatherPanel() {
		return wp;
	}
}
