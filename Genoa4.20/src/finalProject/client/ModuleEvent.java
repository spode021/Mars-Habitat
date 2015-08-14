package finalProject.client;

import com.google.gwt.event.shared.GwtEvent;


public class ModuleEvent extends GwtEvent<ModuleEventHandler>{

	public static Type<ModuleEventHandler> TYPE =
			new Type<ModuleEventHandler>();

	@Override
	public Type<ModuleEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ModuleEventHandler handler) {
		handler.onEvent(this);
	}
}
