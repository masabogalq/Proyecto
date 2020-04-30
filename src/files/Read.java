package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
//Esta clase se encarga de leer 2 tipos de archivos
//un tipo de archivo que almacena los nombres de los archivos que se han creado
//otro tipo de archivo que almancena cada rejilla creada

public class Read {
	private String[] data;// en esta variable se almacena el texto leido
	private int x, y;
	public Read(String path) throws NumberFormatException, IOException {
		FileReader r = new FileReader(path);//abro el archivo para leerlo
		BufferedReader pr = new BufferedReader(r);//Con este objeto leo el archivo en un bufer en memoria
		x = Integer.parseInt(pr.readLine());
		y = Integer.parseInt(pr.readLine());
		data = new String[x];
		for(int i=0;i<x;i++)
			data[i] = pr.readLine()+"\n";
	}
	public String[] getData() {
		return data;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
