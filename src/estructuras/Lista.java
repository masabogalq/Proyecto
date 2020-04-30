package estructuras;
//Esta lista se utilizar� en especial para almacenamiento de datos de la aplicaci�n
//La idea de esta Lista es que se puedan agregar objetos de cualquier tipo, por eso se hace gen�rica
//Se trata de una lista de tama�o din�mico, unidimensional, sin repeticiones de elementos


public class Lista <T extends Comparable> implements Comparable{
	int N;//tama�o del arreglo
	int c;//Contador de elementos
	int p;//indice, variable usada para recorrer el arreglo y marcar posiciones de interes
	String nombre;
	
	T [] a;
	public Lista() {//constructor por defecto
		a = (T[])new Comparable[N=100];
		c=0;
	}
	public Lista(int n) {//constructor con un tama�o espec�fico
		a = (T[])new Comparable[N=n];
	}
	public boolean empty() {//devuelve true cuando la lista est� vacia
		return c==0;
	}
	public boolean full() {//devuelve true cuando el arreglo est� lleno
		return c==N;
	}
	public boolean search(T d) {//busca un elemento en espec�fico dado un dato ingresado
		boolean f = false;//variable de retorno
		boolean s = false;//variable de apoyo
			p=0;//inicializo el indice en la primera posici�n del arreglo
			while(p<c && s==false)//cuido de no pasarme de la cantidad de elementos almacenados
				if(d.compareTo(a[p])<=0) {//si de acuerdo al criterio de comparaci�n siguiente o igual al que busco
					s=true;//me detengo
					f= d.compareTo(a[p])==0;//y reviso si es el elemento q busco, cambio la variable de retorno de acuerdo a esa respuesta
				}
				else// de lo contrario
					p++;//sigo avanzando
		return f;
	}
	public boolean insert(T d) {//esta es una funci�n de insertar sin repetici�n, es decir que el dato que da el criterio de comparaci�n entre elementos debe ser �nico para cada elemento
		boolean i = false;//variable de retorno, falso si ya est� el elemento en la lista y no lo puedo agregar (al menos de acuerdo al criterio de comparaci�n de los elemetos: id, nombre, etc)
		if(!search(d)) {//si no encuentro el elemento (el elemento ingresado no es una repetici�n)
			i=true;//digo q si lo voy a insertar
			if(c==N)//si el n�mero de elementos almacenados es el m�ximo permitido por el arreglo
				resize();//cambio el tama�o del arreglo
			for(int j=c;j>p;j--)//empezando desde la �ltima posici�n, y hasta llegar al elemento siguiente al indice
				a[j]=a[j-1];//muevo cada elemento a su posici�n siguiente
			a[p]=d;//inserto el dato
			c++;//y aumento el n�mero de elementos almacenados
		}
		else//si encuentro el elemento
			System.out.println("Lista llena");//ps digo q no lo puedo ingresar
		return i;// y se lo indico al m�todo que llama a insertar
	}
	public void resize() {//aumenta el tama�o del arreglo cuando sea necesario
		T[] r = (T[]) new Comparable[N];//creo un arreglo de respaldo
		for(int i=0;i<N;i++)
			r[i]=a[i];//copio los elementos del arreglo en el arreglo de respaldo
		N = N+100;//aumento el tama�o de la lista, como es para los datos que se guardan del programa, me parece que el tama
		a = (T[]) new Comparable[N];//reinicializo el arreglo principal con su nuevo tama�o con el que crece no debe ser tan grande
		for(int i=0;i<N-100;i++)//como N aumento 100, debo hacer la operaci�n N-100 veces
			a[i]=r[i];//y lo relleno de nuevo con los valores respaldados
	}
	public boolean delete (T n) {//dado un indice, se elimina un elemento, retorna true si lo elimina
		boolean d = false;//variable de retorno
		if(empty())//si est� vacio
			System.out.println("Lista vacia");//digo q est� vacio, de una retorno as� q tambn se lo digo al m�todo q llama q no elimin�
		else if(search(n)) {//de lo contrario, busco el elemento, y si lo encuentro queda almacenada su posici�n en la variable p de la clase
			d = true;//aviso al m�todo que llama que si borr� algo
			for(int i=p;i<c;i++)//empiezo desde el elemento que me dieron hasta el �ltimo elemento almacenado
				a[i]=a[i+1];//y muevo cada elemento siguiente a la posici�n anterior
			c--;//reduzco el n�mero de elementos almacenados
		}//si no encontr� el elemento de una vez le digo al m�todo que llama q no elimin� nada
		return d;
	}
	public T consultar(int i) {//me devuelve el dato q se encuentra en una posicion espec�fica
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