package estructuras;
//Esta lista se utilizará en especial para almacenamiento de datos de la aplicación
//La idea de esta Lista es que se puedan agregar objetos de cualquier tipo, por eso se hace genérica
//Se trata de una lista de tamaño dinámico, unidimensional, sin repeticiones de elementos


public class Lista <T extends Comparable> implements Comparable{
	int N;//tamaño del arreglo
	int c;//Contador de elementos
	int p;//indice, variable usada para recorrer el arreglo y marcar posiciones de interes
	String nombre;
	
	T [] a;
	public Lista() {//constructor por defecto
		a = (T[])new Comparable[N=100];
		c=0;
	}
	public Lista(int n) {//constructor con un tamaño específico
		a = (T[])new Comparable[N=n];
	}
	public boolean empty() {//devuelve true cuando la lista está vacia
		return c==0;
	}
	public boolean full() {//devuelve true cuando el arreglo está lleno
		return c==N;
	}
	public boolean search(T d) {//busca un elemento en específico dado un dato ingresado
		boolean f = false;//variable de retorno
		boolean s = false;//variable de apoyo
			p=0;//inicializo el indice en la primera posición del arreglo
			while(p<c && s==false)//cuido de no pasarme de la cantidad de elementos almacenados
				if(d.compareTo(a[p])<=0) {//si de acuerdo al criterio de comparación siguiente o igual al que busco
					s=true;//me detengo
					f= d.compareTo(a[p])==0;//y reviso si es el elemento q busco, cambio la variable de retorno de acuerdo a esa respuesta
				}
				else// de lo contrario
					p++;//sigo avanzando
		return f;
	}
	public boolean insert(T d) {//esta es una función de insertar sin repetición, es decir que el dato que da el criterio de comparación entre elementos debe ser único para cada elemento
		boolean i = false;//variable de retorno, falso si ya está el elemento en la lista y no lo puedo agregar (al menos de acuerdo al criterio de comparación de los elemetos: id, nombre, etc)
		if(!search(d)) {//si no encuentro el elemento (el elemento ingresado no es una repetición)
			i=true;//digo q si lo voy a insertar
			if(c==N)//si el número de elementos almacenados es el máximo permitido por el arreglo
				resize();//cambio el tamaño del arreglo
			for(int j=c;j>p;j--)//empezando desde la última posición, y hasta llegar al elemento siguiente al indice
				a[j]=a[j-1];//muevo cada elemento a su posición siguiente
			a[p]=d;//inserto el dato
			c++;//y aumento el número de elementos almacenados
		}
		else//si encuentro el elemento
			System.out.println("Lista llena");//ps digo q no lo puedo ingresar
		return i;// y se lo indico al método que llama a insertar
	}
	public void resize() {//aumenta el tamaño del arreglo cuando sea necesario
		T[] r = (T[]) new Comparable[N];//creo un arreglo de respaldo
		for(int i=0;i<N;i++)
			r[i]=a[i];//copio los elementos del arreglo en el arreglo de respaldo
		N = N+100;//aumento el tamaño de la lista, como es para los datos que se guardan del programa, me parece que el tama
		a = (T[]) new Comparable[N];//reinicializo el arreglo principal con su nuevo tamaño con el que crece no debe ser tan grande
		for(int i=0;i<N-100;i++)//como N aumento 100, debo hacer la operación N-100 veces
			a[i]=r[i];//y lo relleno de nuevo con los valores respaldados
	}
	public boolean delete (T n) {//dado un indice, se elimina un elemento, retorna true si lo elimina
		boolean d = false;//variable de retorno
		if(empty())//si está vacio
			System.out.println("Lista vacia");//digo q está vacio, de una retorno así q tambn se lo digo al método q llama q no eliminé
		else if(search(n)) {//de lo contrario, busco el elemento, y si lo encuentro queda almacenada su posición en la variable p de la clase
			d = true;//aviso al método que llama que si borré algo
			for(int i=p;i<c;i++)//empiezo desde el elemento que me dieron hasta el último elemento almacenado
				a[i]=a[i+1];//y muevo cada elemento siguiente a la posición anterior
			c--;//reduzco el número de elementos almacenados
		}//si no encontré el elemento de una vez le digo al método que llama q no eliminé nada
		return d;
	}
	public T consultar(int i) {//me devuelve el dato q se encuentra en una posicion específica
		T data = a[i];
		return data;
	}
	public int getC() {
		return c;
	}
	public String toString() {
		String texto = c+" \n";
		for(int i=0;i<c;i++) {
			texto+=a[i].toString()+"\n";
		}
		return texto;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	@Override
	public int compareTo(Object o) {
		Lista aComparar = (Lista) o;
		return this.nombre.compareTo(aComparar.nombre);
	}
}