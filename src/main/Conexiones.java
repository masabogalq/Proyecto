package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import files.Read;
import rejilla.Coordenada;
import estructuras.Bidimensional;
import estructuras.Lista;

public class Conexiones {//se encarga de las operaciones de datos
	public Lista[] Proyectos;//lista los proyectos existentes
	String dirPrincipal;//Dirección de la carpeta principal de archivos
	String dirProyecto;//Dirección de un proyecto específico, variable de apoyo
	int j;//posición en lista de un proyecto específico, es el mismo de dirProyecto
	int c;//Número de proyectos existentes
	int N;//Tamaño de la lista de proyectos
	public Conexiones() {
		Proyectos = new Lista[N=100];//crea la lista de proyectos
		String desktopPath = System.getProperty("user.home") + "/Desktop";//Encuentra la dirección del escritorio
		File dirProyecto = new File(desktopPath+"/ProyectoFinal");//Ubica la dirección de la carpeta principal en el escritorio
		dirPrincipal = dirProyecto.getPath();//almacena esa dirección
		recuperarDirectorios();
	}
	public void recuperarDirectorios() {
		
		File dirProyecto = new File(dirPrincipal);//dado el directorio principal
		if(!dirProyecto.exists())//si no existe el directorio
			if(dirProyecto.mkdirs())//lo crea
				System.out.println("Directorio creado en "+dirProyecto.getPath().replace("\\", "/"));
			else//avisa si tiene problemas para crearlo
				System.out.println("No se puede Crear el directorio base");
		else {//si ya existe el directorio
			String[] nombresProyectos = dirProyecto.list();//obtengo la lista de proyectos (nombres)
			if(nombresProyectos.length>N)//si hay mas proyectos que espacios de memoria asignados 
				N=nombresProyectos.length;//doy suficiente espacio para almacenar los proyectos existentes
			c=nombresProyectos.length;//aviso de la cantidad de proyectos almacenados
			for(int i=0;i<nombresProyectos.length;i++) {//y lleno el arreglo
				Proyectos[i] = new Lista();//cada elemento de estos proyectos es una lista
				Proyectos[i].setNombre(nombresProyectos[i]);//tambien le digo su nombre a dicha lista
			}
		}
	}
	
	public void mostrarRejillas() {
		File f = new File(dirPrincipal);
		String[] nombres = f.list();
		if(nombres.length>0)
			System.out.println("Estos son los proyectos creados");
		for(int i=0;i<nombres.length;i++)
			System.out.println(nombres[i]);//imprimo los nombres de todos los proyectos existentes
		System.out.println("Ingrese el nombre del proyecto");
		Scanner s = new Scanner(System.in);
		String nombre = s.next();
		j=-1;
		File dir;
		for(int i=0;i<Proyectos.length;i++)
			if(Proyectos[i].getNombre().compareTo(nombre)==0)
				j=i;
		if(j!=-1) {//si encuentro el nombre del proyecto ingresado por el usuario
			System.out.println("el proyecto contiene las siguientes imagenes");
			dir= new File(dirPrincipal+"/"+nombre);
			String[] rejillas = dir.list();//listo los nombres de los dibujos allí incluidos
			dirProyecto = dirPrincipal+"/"+nombre;
			if(rejillas!=null)
				for(int i=0;i<rejillas.length;i++)
					System.out.println(rejillas[i].substring(0, (rejillas[i].length()-4)));//Y los imprimo para conocimiento del usuario
			else
				System.out.println("Este proyecto esta vacio");//si está vacio lo hago saber
		}else {
			System.out.println("Nombre invalido");//si el nombre no está lo hago saber
			mostrarRejillas();//y doy de nuevo la oportunidad para abrir un proyecto
		}
	}
	
	public void crearRejilla(String nombre, int x, int y) {//dados un nombre y un tamaño
		Bidimensional nueva = new Bidimensional(nombre);//creo el arreglo q contiene los colores del sibujo
		nueva.iniciar(x, y);//la inicializo
		Proyectos[j].insert(nueva);//y la ingreso al proyecto abierto
	}
	public Bidimensional abrirRejilla(String nombre) throws NumberFormatException, IOException {
		File f = new File(dirProyecto+"/"+nombre);
		Read r = new Read(dirProyecto+"/"+nombre);//hago un objeto para leer el dibujo guardado en el directorio del proyecto abierto
		Bidimensional rejilla;
		if(f.exists()) {//si el archivo existe
			rejilla = new Bidimensional(nombre);//creo el elemento co su nombre
			rejilla.iniciar(r.getX(), r.getY());//le doy el tamaño
			rejilla.colorear(llenarCoordenadas(r.getX(),r.getY(),r.getData()));//y lo lleno con sus colores guardados
		}
		else
			rejilla = null;//si no existe el archivo
		return rejilla;
	}
	
	private Lista llenarCoordenadas(int x, int y, String[] data) {//con esto lleno un elemento en memoria con sus valores del archivo guardado si existe
		Lista<Coordenada> colores = new Lista<Coordenada>();
		StringTokenizer tokens;
		for(int i=0;i<x;i++) {
			tokens = new StringTokenizer(data[i], " ");
			for(int j=0;j<y;j++) {
				colores.insert(new Coordenada(i,j,Integer.parseInt(tokens.nextToken())));
			}
		}
		return colores;//retorna un arreglo con todos los colores para ser rellenados
	}
	
	public boolean empty() {
		File f = new File(dirPrincipal);
		String[] carpetas = f.list();
		return carpetas==null;
	}
	
	public boolean crearProyecto(String nombre) {
		boolean existe=false;
		for(int i=0;i<Proyectos.length;i++) {//primero busco si ya existe el proyecto
			if(nombre.compareTo(Proyectos[i].getNombre())==0)
				existe = true;
		}
		if(!existe) {//si no existe procedo a crearlo
			File f = new File(dirPrincipal+"/"+nombre);
			if(f.mkdirs())//creo la carpeta
				System.out.println("Proyecto creado");
			else
				System.out.println("No se pudo crear este proyecto");
			if(c==N)//si es q ya llene el arreglo de listas/proyectos
				resize();
			Proyectos[c]=new Lista();//ahora agrego
			c++;//aumento el numero de proyectos
		}
		return existe;//y aviso si existe o no el proyecto previamente
	}
	
	public boolean borrarProyecto(String nombre) {
		boolean r = false;
		File f = new File(dirPrincipal+"/"+nombre);
		if(f.delete()) {//si existe el archivo y lo borro
			System.out.println("Proyecto Borrado");
			return true;
		}
		return r;
	}
	public void resize() {
		N = N+100;
		Lista[] temp = new Lista[N];
		for(int i=0;i<N-100;i++)
			temp[i]=Proyectos[i];
		Proyectos = temp;			
	}
}
