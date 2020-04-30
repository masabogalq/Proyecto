package main;

import java.util.Scanner;

public class Principal {

	Scanner s = new Scanner(System.in);
	Conexiones operador;
	
	public Principal() {
		operador = new Conexiones();//este objeto va a realizar todas las operaciones de datos
		System.out.println("Bienvenido al prototipo de este programa");
		System.out.println("Como no tengo interfaz grafica aun, funciono con comandos de texto");
		System.out.println("Escriba como aparece el comando que quiera realizar");
		Proyectos();
	}
	public void Proyectos() {//este se encarga de las operaciones de proyectos
		System.out.println("[CrearProyecto: CP] [AbrirProyecto: AP] [BorrarProyecto: BP]");
		String comando = s.next();
		if(comando.compareTo("CP")==0) {
			operador.mostrarRejillas();
		}else if(comando.compareTo("AP")==0) {
			
			operador.mostrarRejillas();
			Rejillas();
		}else if(comando.compareTo("BP")==0) {
			System.out.println("Ingrese el nombre del proyecto a borrar");
			operador.borrarProyecto(s.next());
		}
		else
			Proyectos();
	}
	
	private void Rejillas() {//este pedazo se encarga de las operaciones dentro de los proyectos
		System.out.println("[CrearDibujo: CD] [AbrirDibujo: AD] [BorrarDibujo: BD]");
		String comando = s.next();
		if(comando.compareTo("CD")==0) {
			System.out.println("Ingrese el nombre del dibujo");
			String nombre = s.next();
			int x, y;
			System.out.println("Ingrese las dimensiones del dibujo en pixeles asi [Ancho Alto]");
			x=s.nextInt();
			y=s.nextInt();
			operador.crearRejilla(nombre, x, y);
		}
	}
	public static void main(String args[]) {
		new Principal();
	}
}
