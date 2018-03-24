import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
    import java.io.File;


 public class Ventana extends JFrame{

private File_Allocation_Table FAT;
private Disco_Duro mi_disco;
private Directorio1 dir;

private JButton BotonGuardar;
  /*
Componentes del panel del disco duro
   */
	private JPanel panel1;
	private JButton botones[] = new JButton[100];
	private JScrollPane scrolldisco;
/*
Componentes del panel de la tabla Fat
 */
  private JPanel panelFat;
	private JButton botonesFat[] = new JButton[100];
	private JScrollPane scrollFat;

  /*
  Componentes del panel de la tabla del direcctorio
   */
  private JPanel panelDir;
  private JButton botonesDir[] = new JButton[100];
  private JScrollPane scrollDir;

    /*
    componentes de radio button
     */

  private JRadioButton rbtn1=new JRadioButton("Crear Archivo",true);
  private JRadioButton rbtn2=new JRadioButton("Eliminar Archivo",false);
  private JRadioButton rbtn3 = new JRadioButton("Leer Archivo",false);
  private JRadioButton rbtn4 = new JRadioButton("Modificar Archivo",false);
  private ButtonGroup grupo1 = new ButtonGroup();
  /*
  Editor de texto
   */

  private JTextArea areadetexto = new JTextArea();
  private JLabel etiquetatexto = new JLabel("Edicion del Archivo");
  private JLabel etiquetaDiscoDuro= new JLabel("Disco Duro");
  private JLabel etiquetatextoFat = new JLabel("Tabla FAT");
  private JLabel etiquetatextoDir = new JLabel("Directorio");
  private JLabel etiquetatextoNameArchivo = new JLabel("Nombre Archivo");

  /*
Tabla
   */
  private DefaultTableModel modeloDeMiJTable;
  private JTable miJTable;
  private JScrollPane scrollMostrarArchivos;



  private JTextField nombrearchivo;

//Colores
  //private Color colores[] = new Color[20];
  //colores[0] = new Color(255,175,175);
  //Color colorRosa=new Color(255, 175, 175);
  Color[] colores = {
new Color(128,0,0)
,new Color(165,42,42)
,new Color(238,232,170)
,new Color(255,69,0)
,new Color(220,20,60)
,new Color(205,92,92)
,new Color(218,165,32)
,new Color(128,128,0)
,new Color(154,205,50)
,new Color(0,100,0)
,new Color(102,205,170)
,new Color(0,255,255)
,new Color(95,158,160)
,new Color(65,105,225)
,new Color(123,104,238)
,new Color(186,85,211)
,new Color(238,130,238)
,new Color(255,0,255)
,new Color(250,235,215)
,new Color(210,105,30)
,new Color(255,228,181)
,new Color(255,140,0)};


	public Ventana()
	{
		setLayout(null);
    this.getContentPane().setBackground(Color.orange);
		InicializarComponentes();
    FAT = new File_Allocation_Table();
    mi_disco = new Disco_Duro();
    dir= new Directorio1();
    dir.Crear();
    FAT.inicializar();
    MostrarFat(FAT.mostrar());//para mostrar fat
    MostrarDisco(mi_disco.Mostrar_Disco_Duro());//para mostrar disco duro

	}

	private void InicializarComponentes()
	{

    /*
    Creacion del panel de disco duro
     */
  		panel1 = new JPanel();
  		panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
  		JScrollPane scrollPane = new JScrollPane(panel1);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setBounds(50, 50, 105, 200);
      add(scrollPane);
      int coordenaday=0;
      for (int i=0;i<100 ;i++ ) {
			     botones[i] = new JButton();
			     botones[i].setBounds(0,coordenaday,100,30);
        	 panel1.add(botones[i]);
        	 coordenaday=coordenaday+30;
      }

      /*
creacion del panel de la tabla fat
       */
       panelFat = new JPanel();
       panelFat.setLayout(new BoxLayout(panelFat,BoxLayout.Y_AXIS));
       JScrollPane scrollFat = new JScrollPane(panelFat);
       scrollFat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       scrollFat.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       scrollFat.setBounds(200, 50, 105, 200);
       add(scrollFat);
      coordenaday=0;
       for (int i=0;i<100 ;i++ ) {
            botonesFat[i] = new JButton();
            botonesFat[i].setBounds(0,coordenaday,100,30);
            panelFat.add(botonesFat[i]);
            coordenaday=coordenaday+30;
         }
      /*   for(int i=0;i<10;i++)
         {
            botonesFat[i].setBackground(Color.blue);
         }*/
         /*
creacion del panel de direcctorio
          */
         panelDir = new JPanel();
         panelDir.setLayout(new BoxLayout(panelDir,BoxLayout.Y_AXIS));
         JScrollPane scrollDir = new JScrollPane(panelDir);
         scrollDir.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         scrollDir.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         scrollDir.setBounds(350, 50, 135, 200);
         add(scrollDir);
         coordenaday=0;
         for (int i=0;i<100 ;i++ ) {
              botonesDir[i] = new JButton("");
              botonesDir[i].setBounds(0,coordenaday,100,30);
              panelDir.add(botonesDir[i]);
              coordenaday=coordenaday+30;
          }
/*          for(int i=0;i<10;i++)
          {
              botonesDir[i].setBackground(Color.yellow);
              //botonesDir[i].setText("cambio");
          }*/

          /*
radio button
           */

           rbtn1.setBounds(500,50,150,30);
           rbtn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
           add(rbtn1);
           grupo1.add(rbtn1);
           rbtn2.setBounds(500,80,150,30);
           rbtn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
           add(rbtn2);
           grupo1.add(rbtn2);
           rbtn3.setBounds(500,110,150,30);
           rbtn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
           add(rbtn3);
           grupo1.add(rbtn3);
           rbtn4.setBounds(500,140,150,30);
           rbtn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
           add(rbtn4);
           grupo1.add(rbtn4);

           rbtn1.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
               areadetexto.setEditable(true);
               nombrearchivo.setEditable(true);
               areadetexto.setText("");
               nombrearchivo.setText("");
               BotonGuardar.setText("Crear");
               areadetexto.setCursor(new Cursor(Cursor.TEXT_CURSOR));
              }
          });

          rbtn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              areadetexto.setEditable(false);
              nombrearchivo.setEditable(true);
              areadetexto.setText("");
              nombrearchivo.setText("");
              BotonGuardar.setText("Eliminar");
              areadetexto.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
             }
         });

         rbtn3.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
             areadetexto.setEditable(false);
             nombrearchivo.setEditable(true);
             areadetexto.setText("");
             nombrearchivo.setText("");
             BotonGuardar.setText("Buscar");
             areadetexto.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
        });

        rbtn4.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            areadetexto.setEditable(true);
            nombrearchivo.setEditable(false);
            BotonGuardar.setText("Guardar");
            areadetexto.setCursor(new Cursor(Cursor.TEXT_CURSOR));
           }
       });

           /*
ARea de texto
            */

            etiquetatexto.setBounds(500,230,150,20);
            add(etiquetatexto);
            //areadetexto.setBounds(500,250,400,100);
            JScrollPane scrollAreaTexto = new JScrollPane(areadetexto);
            scrollAreaTexto.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollAreaTexto.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollAreaTexto.setBounds(500,250,400,100);
            add(scrollAreaTexto);
            //add(areadetexto);

            /*
Etiquetas;
             */
            etiquetaDiscoDuro.setBounds(50,30,100,20);
            add(etiquetaDiscoDuro);
            etiquetatextoFat.setBounds(220,30,100,20);
            add(etiquetatextoFat);
            etiquetatextoDir.setBounds(350,30,100,20);
            add(etiquetatextoDir);
            etiquetatextoNameArchivo.setBounds(500,180,150,20);
            add(etiquetatextoNameArchivo);

            nombrearchivo=new JTextField();
            nombrearchivo.setBounds(500,210,300,20);
            add(nombrearchivo);

            /*
Boton Guardar
             */
            BotonGuardar = new JButton("Crear");
            BotonGuardar.setBounds(800,210,100,20);
            BotonGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            add(BotonGuardar);
            BotonGuardar.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                if (rbtn1.isSelected()) {
                  crearArchivo();
                }
                else if(rbtn2.isSelected())
                {
                  EliminarArchivo();
                }
                else if(rbtn3.isSelected())
                {
                  MostrarArchivo();
                }
                else if(rbtn4.isSelected())
                {
                  Modificar();
                }

               }
           });

            /*
Tabla
             */
             modeloDeMiJTable = new DefaultTableModel(new Object[][] {}, new String [] {}){
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
};
             	miJTable = new JTable(modeloDeMiJTable);
             	modeloDeMiJTable.addColumn("Archivos");
             	miJTable.setPreferredScrollableViewportSize(new Dimension(500,200));
             	scrollMostrarArchivos = new JScrollPane(miJTable);
             	scrollMostrarArchivos.setBounds(50,300,400,150);
             	add(scrollMostrarArchivos);

	}

private void Modificar()
{
  if(!areadetexto.getText().equals(""))
  {
  if(!nombrearchivo.getText().equals(""))
  {
    //se hace copia de seguridad
      int posi = dir.BuscarArchivo(nombrearchivo.getText()+".txt");
      if(posi != -1)
      {
      int PosMostar[]=FAT.abrir(posi);
      String ContenidoArchivo=mi_disco.Leer_Bloques(PosMostar,PosMostar.length);//cadena antigua
      //se elimina cadena antigua
        int PosEliminar[]=FAT.abrir(posi);
        FAT.borrar(PosEliminar);
        for(int i=0;i<PosEliminar.length;i++)
        {
          mi_disco.Liberar_Bloque(PosEliminar[i]);
        }
        dir.EliminarArchivo(nombrearchivo.getText()+".txt");
      //se agrega nueva cadena
      if(mi_disco.Guardar_Bloques(areadetexto.getText()))
      {
        MostrarDisco(mi_disco.Mostrar_Disco_Duro());
        int bloques_disco[] = mi_disco.Obtener_Ultimos_Bloques_Archivo();
        FAT.agregar(bloques_disco);
        MostrarFat(FAT.mostrar());
        dir.Fichero(nombrearchivo.getText(),areadetexto.getText(),bloques_disco[0]);
        MostrarDir(dir.Vista());
        PintarColores(dir.RetornarPosiciones());
        MostrarDirectorio();
      }
      else{
        Mensaje("No existe suficiente espacio en disco duro","Error al Modificar Archivo");
        //Se agrega la vieja cadena
        mi_disco.Guardar_Bloques(areadetexto.getText());
        MostrarDisco(mi_disco.Mostrar_Disco_Duro());
        int bloques_disco[] = mi_disco.Obtener_Ultimos_Bloques_Archivo();
        FAT.agregar(bloques_disco);
        MostrarFat(FAT.mostrar());
        dir.Fichero(nombrearchivo.getText(),areadetexto.getText(),bloques_disco[0]);
        MostrarDir(dir.Vista());
        PintarColores(dir.RetornarPosiciones());
        MostrarDirectorio();
      }
      }
      else {
        JOptionPane.showMessageDialog(null, "El archivo No existe","Error al Modificar Archivo", JOptionPane.ERROR_MESSAGE);
        nombrearchivo.setText("");
        areadetexto.setText("");
      }

  }
  else {
    JOptionPane.showMessageDialog(null, "No existe Archivo","Error al modificar", JOptionPane.ERROR_MESSAGE);
  }
}
else {
  Mensaje("Ingrese informacion para guardar en el Archivo","Error al crear Archivo");
}
}

private void crearArchivo()
{
  if(dir.BuscarArchivo(nombrearchivo.getText()+".txt")==-1)
  {
  if(!nombrearchivo.getText().equals(""))
  {
    if(!areadetexto.getText().equals(""))
    {
      if(mi_disco.Guardar_Bloques(areadetexto.getText()))
      {
        MostrarDisco(mi_disco.Mostrar_Disco_Duro());
        int bloques_disco[] = mi_disco.Obtener_Ultimos_Bloques_Archivo();
        for(int i=0;i<bloques_disco.length;i++)
          System.out.println("bloque"+bloques_disco[i]);
        FAT.agregar(bloques_disco);
        MostrarFat(FAT.mostrar());
        dir.Fichero(nombrearchivo.getText(),areadetexto.getText(),bloques_disco[0]);
        MostrarDir(dir.Vista());
        PintarColores(dir.RetornarPosiciones());
        MostrarDirectorio();
        nombrearchivo.setText("");
        areadetexto.setText("");
      }
      else
      Mensaje("No existe suficiente espacio en disco duro","Error al crear Archivo");
    }
    else {
      Mensaje("Ingrese informacion para guardar en el Archivo","Error al crear Archivo");
    }
  }
  else{
    Mensaje("Ingrese el Nombre del Archivo","Error al crear Archivo");
  }
  }
  else {
    Mensaje("El Archivo ya existe","Error al crear Archivo");
  }
}


private void EliminarArchivo()
{
  if(!nombrearchivo.getText().equals(""))
  {
    int posi = dir.BuscarArchivo(nombrearchivo.getText()+".txt");
    if(posi != -1)
    {
      int PosEliminar[]=FAT.abrir(posi);
      FAT.borrar(PosEliminar);
      MostrarFat(FAT.mostrar());
      for(int i=0;i<PosEliminar.length;i++)
      {
        mi_disco.Liberar_Bloque(PosEliminar[i]);
      }
      MostrarDisco(mi_disco.Mostrar_Disco_Duro());
      dir.EliminarArchivo(nombrearchivo.getText()+".txt");
      MostrarDir(dir.Vista());
      MostrarDirectorio();
      nombrearchivo.setText("");
      PintarColores(dir.RetornarPosiciones());
    }
    else {
      Mensaje("El archivo No existe","Error al Eliminar Archivo");
      nombrearchivo.setText("");
    }
  }
  else {
    Mensaje("Ingrese el Nombre del Archivo","Error al Eliminar Archivo");
  }
}


private void Mensaje(String men, String error)
{
  JOptionPane.showMessageDialog(null, men,error, JOptionPane.ERROR_MESSAGE);
}

private void MostrarArchivo()
{
  if(!nombrearchivo.getText().equals(""))
  {
    int posi = dir.BuscarArchivo(nombrearchivo.getText()+".txt");
    if(posi != -1)
    {
      int PosMostar[]=FAT.abrir(posi);
      String ContenidoArchivo=mi_disco.Leer_Bloques(PosMostar,PosMostar.length);
      System.out.println("Contenido Archivo "+ContenidoArchivo);
      areadetexto.setText(ContenidoArchivo);
    }
    else {
      JOptionPane.showMessageDialog(null, "El archivo No existe","Error al Mostar Archivo", JOptionPane.ERROR_MESSAGE);
      nombrearchivo.setText("");
    }
  }
  else {
    JOptionPane.showMessageDialog(null, "Ingrese el Nombre del Archivo","Error Buscar Archivo", JOptionPane.ERROR_MESSAGE);
  }
}

private void MostrarDirectorio(){
  String sDirectorio = "F:\\Linux\\java\\SistemasII\\Programa1\\Usuario\\";
  File f = new File(sDirectorio);

       for (int i = 0; i < miJTable.getRowCount(); i++) {
           modeloDeMiJTable.removeRow(i);
           i-=1;
       }


  if (f.exists()){
    File[] ficheros = f.listFiles();
    String []datos = new String[1];
    for (int x=0;x<ficheros.length;x++){
      //System.out.println(ficheros[x].getName());
       datos[0]=""+ficheros[x].getName();
      modeloDeMiJTable.addRow(datos);
    }
   }
  else {
    System.out.println("No existe el directorio");
  }
}

private void EliminarArchivos()
{
  String sDirectorio = "F:\\Linux\\java\\SistemasII\\Programa1\\Usuario\\";//
  File f = new File(sDirectorio);
  if (f.exists()){
    File[] ficheros = f.listFiles();
    for (int x=0;x<ficheros.length;x++){
       ficheros[x].delete();
       //fichero.delete()
    }
   }
  else {
    System.out.println("No existe el directorio");
  }

}

private void MostrarFat(String tablaFat[])
{
  for(int i=0;i<100;i++)
  {
    botonesFat[i].setText(""+i+" -"+tablaFat[i]);
  }
}

private void MostrarDisco(String Disco_Du[])
{
  for(int i=0;i<100;i++)
  {
    botones[i].setText(""+i+" -"+Disco_Du[i]);
  }
}

private void MostrarDir(String Direc[])
{
  for(int i=0;i<100;i++)
  {
    botonesDir[i].setText("");
  }
  for(int i=0;i<Direc.length;i++)
  {
    if(!Direc[i].equals(""))
     botonesDir[i].setText(""+Direc[i]);
  }
}

private void inicializarColores()
{
  for(int i=0;i<100;i++)
  {
    botones[i].setBackground(null);
    botonesDir[i].setBackground(null);
    botonesFat[i].setBackground(null);
  }
}

private void PintarColores(int pos[])
{
  inicializarColores();
  int h=0;
  for(int i=0;i<pos.length;i++)
  {
      if(h==21)
        h=0;
    botonesDir[i].setBackground(colores[h]);
    int posaux[]=FAT.abrir(pos[i]);
    for(int j=0;j<posaux.length;j++)
    {
      botones[posaux[j]].setBackground(colores[h]);
      botonesFat[posaux[j]].setBackground(colores[h]);
    }
    h++;

  }
}

	public static void main(String[] args){
		Ventana venta = new Ventana();
    venta.EliminarArchivos();
		venta.setSize(920,500);
		venta.setTitle("Simulacion Gestion de Archivos");
		venta.setVisible(true);
		venta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    venta.MostrarDirectorio();

	}

}
