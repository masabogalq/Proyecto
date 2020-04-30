package estructuras;
import rejilla.*;

public class Bidimensional implements Comparable{
	//como se trata de un programa de edición de imagenes en 2 dimensiones es necesario tener un arreglo bidimensional
	//además de manejar el tamaño y los datos que se incluyen en cada rejilla
	//esta clase es la que almacena los dibujos, cada objeto de esta clase es una rejilla, un dibujo
	
	public int[][] arr;
	public int x,y;
	public String nombre;
	
	public Bidimensional(String nombre) {
		this.nombre=nombre;
	}
	
	public void iniciar (int x, int y) {//constructor por defecto de la clase, necesito el tamaño del arreglo que es el de la rejilla
		this.x = x;
		this.y=y;
		arr = new int[x][y];//inicializo el arreglo, la variable x se refiere a filas, la y a columnas
		for(int i=0;i<x;i++)//recorro el arreglo por filas
			for(int j=0;j<y;j++)//recorro el arreglo por columnas dentro de cada fila
				arr[i][j] = 0; //el 0 significa q no hay un color en esta casilla, esta vacia por defecto
	}
	public void colorear(Lista coordenadas) {//recibe una lista de coordenadas
		int tam = coordenadas.getC();//primero obtengo la cantidad de los cuadros a colorear
		for(int i=0;i<tam;i++) {//los recorro
			Coordenada dato = (Coordenada) coordenadas.consultar(i);//creo una referencia a cada objeto de la lista
			arr[dato.getX()][dato.getY()] = dato.getID();//y asigno el color a cada casilla de la matriz usada para almacenar los colores con los datos de la referencia
		}
	}
	
	public String toString() {
		String texto=x+"\n"+y+"\n";//primero digo las dimensiones de la rejilla q estoy editando para guardarla
		for(int i=0;i<x;i++) {//recorro por filas
			for(int j=0;j<y;j++)//recorro por columnas dentro de las filas
				texto+=arr[i][j]+" ";//y almaceno la informacion del color en cada casilla
			texto+="\n";//salto de linea para separar cada fila
		}
		return texto;
	}
	@Override
	public int compareTo(Object o) {
		Bidimensional aComparar = (Bidimensional) o;
		return this.nombre.compareTo(aComparar.nombre);
	}
}
