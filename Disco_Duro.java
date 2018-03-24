import java.util.StringTokenizer;

public class Disco_Duro
{
	public int capacidad;//no se disminuye
	public int bloques_libres;//se disminuye
	public String[] disco;//disco duro
	public int[] ultimos_bloques_archivo;

	public Disco_Duro()
	{
		capacidad = 100;
		bloques_libres = 100;
		/*ultimos_bloques_archivo = new int[10];
		for(int i = 0; i < 10; i++)
			ultimos_bloques_archivo[i] = -1;
		*/
		disco = new String[100];
		for(int i = 0; i < disco.length; i++)
			disco[i] = "";
	}

	public void Imprimir_Capacidad()
	{
		System.out.println("La capacidad del disco duro es de " + capacidad + " TB.");
	}

	public void Imprimir_Capacidad_Libre()
	{
		System.out.println("El disco duro actualmente tiene " + bloques_libres + " TB disponibles.");
	}

	public void Actualizar(String str_datos)
	{
		StringTokenizer st = new StringTokenizer(str_datos);
		int num_bloques = st.countTokens();

		int[] copia_bloques = new int[10];
		for(int i = 0; i < copia_bloques.length; i++)
			copia_bloques[i] = ultimos_bloques_archivo[i];

		//Borramos el viejo archivo.
		for(int i = 0; i < ultimos_bloques_archivo.length; i++)
		{
			if(ultimos_bloques_archivo[i] != -1)
				disco[ ultimos_bloques_archivo[i] ] = "";
		}

		//Copiamos el antiguo arreglo de bloques.
		for(int i = 0; i < ultimos_bloques_archivo.length; i++)
		{
			copia_bloques[i] = ultimos_bloques_archivo[i];
		}

		int contador = 0;
		for(int i = 0; i < capacidad; i++)
		{
			if(disco[i].isEmpty())
			{
				if((st.hasMoreTokens()) && (contador < num_bloques))
				{
					disco[i] = st.nextToken(" ");
					ultimos_bloques_archivo[contador] = (i + 1);
					contador += 1;
				}
				else
				{
					break;
				}
			}
			else
			{
				System.out.println(disco[i]);
			}
		}

		Limpiar_Ultimos_Bloques_Archivo();

		//Falta agregar los métodos de la FAT.
	}

	public boolean Guardar_Bloques(String str_datos)
	{
		StringTokenizer st = new StringTokenizer(str_datos);
		ultimos_bloques_archivo = new int[st.countTokens()];
		int num_bloques = st.countTokens();
		int posiciones[] = new int[num_bloques];
		int contador = 0;

		//System.out.println("La operaci-o-n se puede llevar a cabo.");
		//System.out.println("Se necesitar-a-n " + st.countTokens() + " GB en el disco duro.");

		//Preguntamos si hay bloques libres, y si los que hay son suficientes.
		if( (bloques_libres > 0) && (bloques_libres >= num_bloques) )
		{
			//System.out.println("La operaci-o-n se puede llevar a cabo.");
			Limpiar_Ultimos_Bloques_Archivo();
			for(int i = 0; i < capacidad; i++)
			{
				if(disco[i].isEmpty())
				{
					//El bloque está libre.
					if((st.hasMoreTokens()) && (contador < num_bloques))
					{
						disco[i] = st.nextToken(" ");
						posiciones[contador] = i;
						ultimos_bloques_archivo[contador] = (i);
						contador += 1;
					}
					else
					{
						break;
					}
				}
				else
				{
					System.out.println(disco[i]);
				}
			}

			/*
			System.out.println("Las siguientes posiciones del disco duro se han ocupado.");
			for(int i = 0; i < num_bloques; i++)
			{
				System.out.println("N-u-mero de bloque: " + (posiciones[i] + 1) + ": " + disco[posiciones[i]]);
			}
			*/


			return true;
		}
		else
		{
			//System.out.println("No se puede llevar a cabo el almacenamiento.");

			return false;
		}
	}

	public boolean Liberar_Bloque(int posicion)
	{
		//La posición está dentro del intervalo.
		if( (posicion > -1) && (posicion < 100) )
		{
			//posicion -= 1;
			if(!disco[posicion].isEmpty())
			{
				disco[posicion] = "";
				bloques_libres += 1;
			}
		}

		return false;
	}

	public String Leer_Bloque(int posicion)
	{
		if( (posicion > 0) && (posicion <= 100) )
		{
			posicion -= 1;
			return disco[posicion];
		}
		else
		{
			return "";
		}
	}

	public String Leer_Bloques(int[] posiciones, int num_posiciones)
	{
		for(int i = 0; i < num_posiciones; i++)
		{
			if( (posiciones[i] <= 0)  && (posiciones[i] > 100) )
			{
				return "";
			}
		}

		//Todas las posiciones son válidas.
		String simulacion_archivo = "";
		for(int i = 0; i < num_posiciones; i++)
		{
			//posiciones[i] -= 1;
			simulacion_archivo += disco[ posiciones[i] ];
			simulacion_archivo += " ";
		}

		return simulacion_archivo;
	}

	public String Leer_Bloques(int[] posiciones)
	{
		int num_posiciones = posiciones.length;

		for(int i = 0; i < num_posiciones; i++)
		{
			if( (posiciones[i] <= 0)  && (posiciones[i] > 100) )
			{
				return "Error system: some index are not valid!";
			}
		}

		//Todas las posiciones son válidas.
		Limpiar_Ultimos_Bloques_Archivo();

		String simulacion_archivo = "";
		for(int i = 0; i < num_posiciones; i++)
		{
			posiciones[i] -= 1;
			simulacion_archivo += disco[ posiciones[i] ];
			simulacion_archivo += " ";

			//Almacenamos los bloques del archivo que se está recuperando.
			ultimos_bloques_archivo[i] = posiciones[i];
		}

		return simulacion_archivo;
	}

	public String[] Mostrar_Disco_Duro()
	{
		return disco;
	}

	public void Limpiar_Ultimos_Bloques_Archivo()
	{
		for(int i = 0; i < ultimos_bloques_archivo.length; i++)
			ultimos_bloques_archivo[i] = -1;
	}

	public int[] Obtener_Ultimos_Bloques_Archivo()
	{
		return ultimos_bloques_archivo;
	}

	public void Imprimir_Ultimos_Bloques_Archivo()
	{
		for(int i = 0; i < ultimos_bloques_archivo.length; i++)
		{
			if(ultimos_bloques_archivo[i] != -1)
				System.out.print(ultimos_bloques_archivo[i] + " ");
		}
	}
}
