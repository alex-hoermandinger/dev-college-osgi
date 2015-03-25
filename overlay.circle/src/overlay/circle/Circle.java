package overlay.circle;

import overlay.api.IOverlay;

public class Circle implements IOverlay {

	@Override
	public void paint() {
		System.out.println("Circle: Painting " + toString());
	}
	
	@Override
	public String toString()
	{
		return "Circle[" + hashCode() + "]";
	}
	
}
