
import java.util.*;

 class Nodo {

	String nombre;
	int bloque;

    public Nodo(String nombre, int bloque)
    {
    	this.nombre=nombre;
    	this.bloque=bloque;
    }

    public String getName(){
      return nombre;
    }

    public int getBloque()
    {
      return bloque;
    }

    public void setName(String cad)
    {
      nombre = cad;
    }
}
