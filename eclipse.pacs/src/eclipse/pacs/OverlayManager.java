package eclipse.pacs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

import eclipse.overlay.api.IOverlay;

public class OverlayManager implements IOverlayManager {
	private static final OverlayManager INSTANCE = new OverlayManager();
	
	private List<IOverlay> overlays = new ArrayList<>();
	
	
	public static OverlayManager getInstance()
	{
		return INSTANCE;
	}
	
	private OverlayManager()
	{
		init();
	}
	
	public void init() {
		 IExtensionPoint overlayExtensionPoint = Platform.getExtensionRegistry().getExtensionPoint( "eclipse.overlay.api.overlay" );
		 IExtension[] overlayExtensions = overlayExtensionPoint.getExtensions();
		 
		try {
			for (IExtension ovlExtension : overlayExtensions) {
				IOverlay ovl = (IOverlay)ovlExtension.getConfigurationElements()[0].createExecutableExtension("class");
				System.out.println("OverlayManager: Adding overlay " + ovl.toString() );
				overlays.add(ovl);
			}
		} catch (CoreException e) {
			throw new RuntimeException("Could not initialize OverlayManager ", e);
		}
	}

	@Override
	public List<IOverlay> getOverlays() {
		return overlays;
	}
	
}
