package eclipse.overlay.arrow;

import eclipse.overlay.api.IOverlay;

public class Arrow implements IOverlay {

	@Override
	public void paint() {
		System.out.println("Arrow: Painting " + toString() );
	}
	
	@Override
	public String toString()
	{
		return "Arrow[" + hashCode() + "]";
	}
	
}
