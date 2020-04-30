package files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//dado un path y un contenido se escribe un archivo

public class Write {
	public Write(String path,String contenido, boolean existe) throws IOException {
		FileWriter w = new FileWriter(path);//abro el archivo para escribir, si no existe, se crea automaticamente
		PrintWriter p = new PrintWriter(w);//este objeto escribe en el archivo abierto/creado
		p.print(contenido);//escribo el contenido
		w.close();//y cierro el archivo
	}
}
