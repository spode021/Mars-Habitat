package finalProject.client;

import com.google.gwt.event.shared.SimpleEventBus;

public class ModuleView {

	public ModuleView (final SimpleEventBus bus) {
		
		bus.addHandler(ModuleEvent.TYPE, new ModuleEventHandler() {
			public void onEvent(ModuleEvent event){
				
			}
			
		});
	}
}
