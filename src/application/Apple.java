package application;

public class Apple {

	public Apple() {
		this.setCoordinates(new Coordinates());
	}
	private Coordinates coordinates;

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
