package pacs;

import java.util.ArrayList;
import java.util.List;

import overlay.api.IOverlay;

public class OverlayManager implements IOverlayManager {
	private List<IOverlay> overlays = new ArrayList<>();
	
	/*
	 *  OSGi Declarative Service Bind method
	 */
	public void addOverlay(IOverlay ovl) {
		System.out.println("OverlayManager: Adding overlay " + ovl.toString() );
		overlays.add(ovl);
	}

	/* 
	 * OSGi Declarative Service Unbind method
	 */
	public void removeOverlay(IOverlay ovl) {
		System.out.println("OverlayManager: Removing overlay " + ovl.toString() );
		overlays.remove(ovl);
	}

	@Override
	public List<IOverlay> getOverlays() {
		return overlays;
	}
	
}
