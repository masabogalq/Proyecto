package rejilla;

//esta clase es de los cuadros que tiene una rejilla, en general almacena la posición de un cuadro y el color q este tiene
//se implementa esta clase para poder colorear cada cuadro de una rejilla de forma independiente o un conjunto de ellos
//esta clase se usa tanto en el paquete rejilla donde se llevan a cabo las operaciones de dibujo como en la de estructuras donde se almacenan

public class Coordenada implements Comparable{
	int x, y;
	int id;
	public Coordenada(int x, int y, int id) {
		this.x=x;
		this.y=y;
		this.id=id;
	}

	@Override
	public int compareTo(Object o) {
		
		return 0;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getID() {
		return id;
	}
}
