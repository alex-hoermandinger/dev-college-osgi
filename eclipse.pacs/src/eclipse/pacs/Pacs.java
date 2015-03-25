package eclipse.pacs;

import java.util.List;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import eclipse.overlay.api.IOverlay;

public class Pacs implements IApplication {

	/*
	 * OSGi Application start method
	 */
	@Override
	public Object start(IApplicationContext cxt) throws Exception {
		System.out.println("EclipsePacs: Starting");
		
		IOverlayManager ovlManager = getOverlayManager();
		
		List<IOverlay> overlays = ovlManager.getOverlays();
		paintOverlays(overlays);
		
		waitForTermination();
		
		return IApplication.EXIT_OK;
	}
	
	// get OverlayManager service instance
	private IOverlayManager getOverlayManager() {
		return OverlayManager.getInstance();
	}
	
	private void paintOverlays( List<IOverlay> overlays )
	{
		System.out.println("EclipsePacs: Painting all overlays -- start");
		for ( IOverlay ovl : overlays )
		{
			ovl.paint();
		}
		System.out.println("EclipsePacs: Painting all overlays -- finish");
	}
	
	@Override
	public void stop() {
		System.out.println("Stopping EclipsePacs");
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
