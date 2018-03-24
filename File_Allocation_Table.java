import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class File_Allocation_Table
{
 private String[] FAT=new String[100];
 private final int tam=FAT.length;

public File_Allocation_Table()
{
  inicializar();
}

 public int capacidad()
 {
  int c=0;
  for(int i=0;i<tam;i++)
   if(FAT[i]=="FREE")
    c++;
  return c;
 }

 public void inicializar()
 {
   for(int i=0;i<100;i++)
    FAT[i]="FREE";

 }

 public String[] mostrar()
 {
   String cadena[] = new String[tam];
  for(int i=0;i<tam;i++)
    cadena[i]=FAT[i];
   return cadena;
 }

 public void agregar(int[] f)
 {
  if(f.length>capacidad())
   System.out.println("Tabla FAT llena");
  else
  {
   for(int i=0;i<f.length;i++)
   {
     System.out.println(""+f[i]);
    if(i==0&&f.length>1)
      FAT[f[i]]=Integer.toString(f[i+1]);
    else if(i!=(f.length-1))
     FAT[f[i]]=Integer.toString(f[i+1]);
    else
     FAT[f[i]]="EOF";
   }
  }
 }

 public void actualizar(int []V,int []N)  //Borra posiciones viejas y agrega posiciones nuevas (actualiza)
 {
  borrar(V);
  agregar(N);
 }

 public int[] abrir(int f) //Regresa bloques
 {
  int c=1,aux=f;
  while(FAT[aux]!="EOF")
  {
   c++; //
   aux=Integer.parseInt(FAT[aux]);
  }
  int[] B=new int[c];
  for(int i=0;i<c;i++)
  {
   B[i]=f; //
   if(FAT[f]!="EOF")
    f=Integer.parseInt(FAT[f]);
  }
  return B;
 }

 public void borrar(int[] f)
 {
  for(int i=0;i<f.length;i++)
   FAT[f[i]]="FREE";
 }

 public int primer_bloque(int[] f) //Regresa bloques
 {
  return f[0];
 }

}
