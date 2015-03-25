package pacs;

import java.util.List;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import overlay.api.IOverlay;

public class Pacs implements IApplication {

	/*
	 * OSGi Application start method
	 */
	@Override
	public Object start(IApplicationContext cxt) throws Exception {
		System.out.println("Pacs: Starting");
		
		IOverlayManager ovlManager = getOverlayManager();
		
		List<IOverlay> overlays = ovlManager.getOverlays();
		paintOverlays(overlays);
		
		waitForTermination();
		
		return IApplication.EXIT_OK;
	}
	
	// get OverlayManager service instance
	private IOverlayManager getOverlayManager() {
		BundleContext bundleContext = Activator.getContext();
		ServiceReference<IOverlayManager> ovlManagerServiceRef = bundleContext.getServiceReference(IOverlayManager.class);
		if (ovlManagerServiceRef == null) {
			throw new RuntimeException( "No IOverlayManager implementation found");
		}
		return bundleContext.getService(ovlManagerServiceRef);
	}
	
	private void paintOverlays( List<IOverlay> overlays )
	{
		System.out.println("Pacs: Painting all overlays -- start");
		for ( IOverlay ovl : overlays )
		{
			ovl.paint();
		}
		System.out.println("Pacs: Painting all overlays -- finish");
	}
	
	@Override
	public void stop() {
		System.out.println("Stopping PACS");
	}
	
	private synchronized void waitForTermination()
    {
        try
        {
            wait();
        }
        catch ( InterruptedException e )
        {
        }
    }
	
	
}
