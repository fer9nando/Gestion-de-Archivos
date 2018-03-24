import java.io.*;
import java.util.Scanner;

class Directorio1 {

	public Directorio1()
	{

	}

	private File directorios;
	private File archivo;
	Scanner leer= new Scanner(System.in);//F:\Linux\java\SistemasII\Programa1\Usuario  F:/Linux/java/SistemasII/Usuario/
	String aux= "F:\\Linux\\java\\SistemasII\\Programa1\\Usuario\\";
	int cont=0;
	int num=0;
	Nodo[] nodo= new Nodo[10];

//Crear directorio
	public void Crear()
	{
	 try{
			directorios = new File("Usuario");
			directorios.mkdir();

		}catch(Exception e){}
	}

//Crear archivo recibe de parametros cad= nombre del archivo
// recibe la cadena texto que tiene lo que se va a guardar
public void Fichero(String cad, String texto,int PrimerBloque)
{
	String cad1,d;
	BufferedWriter bw;

		try{
			Crear();
			cad1=cad + ".txt";
			String aux2= aux ;
			archivo = new File(aux2,cad1);
			archivo.createNewFile();
			bw = new BufferedWriter(new FileWriter(archivo));
      		bw.write(texto);
      		bw.close();

			nodo[cont]= new Nodo(cad1,PrimerBloque);
			cont++;
		}
		catch(Exception e){
			System.out.println(e);
		}
}
	//Mostrar Archivo y bloque
	public String[] Vista()
	{
		String cadenaDeNodos[] = new String[cont];
		int contaux=0;
		for(int i=0;i<cont;i++)
			{
				if(!nodo[i].getName().equals(""))
				{
					cadenaDeNodos[contaux++]= nodo[i].getName()+"  "+nodo[i].getBloque();
				}
			}
		String cadaux[] = new String[contaux];
			for(int i=0;i<cadaux.length;i++)
				cadaux[i]=cadenaDeNodos[i];
		return cadaux;
	}


	public int[] RetornarPosiciones()
	{
		int aux[] = new int[cont];
		int contaux=0;
		for(int i=0;i<cont;i++)
		{
			if(!nodo[i].getName().equals(""))
				aux[contaux++]=nodo[i].getBloque();
		}
		int posicion[] = new int[contaux];
		for(int i=0;i<posicion.length;i++)
			posicion[i]=aux[i];
		return posicion;
	}

	public int BuscarArchivo(String NombreArch)
	{
		int i=0;
		boolean encontrado=false;
		int posicion=-1;
		while(i<cont && encontrado==false)
		{
			if(nodo[i].getName().equals(NombreArch))
			{
				posicion=nodo[i].getBloque();
				encontrado=true;
			}
			i++;
		}
		return posicion;
	}

	public void EliminarArchivo(String NombreArch){
		boolean encontrado=false;
		int i=0;
		while(i<cont && encontrado==false)
		{
			if(nodo[i].getName().equals(NombreArch))
			{
				nodo[i].setName("");
				encontrado=true;
				String cad1=NombreArch;
				String aux2= aux + "Usuario";
				archivo = new File(aux2,cad1);
				archivo.delete();
			}
			i++;
		}
	}
	
}
