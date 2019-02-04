package com.sge.erp.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sge.erp.model.Project;
import com.sge.erp.model.Task;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.persistence.ManagerTask;

public class CompleteProjectsExcel {

    public void create() throws ClassNotFoundException, SQLException {

        final Logger LOGGER = Logger.getLogger("mx.com.hash.newexcel.ExcelOOXML");

        ManagerProjects mp = new ManagerProjects(); //Instanciamos el ManagerProjects
        ManagerTask mt = new ManagerTask(); //Instanciamos el ManagerTask
        ArrayList<Project> projects = mp.getProjects(); //Cogemos el ArrayList de proyectos.
        ArrayList<Project> p100 = new ArrayList<>(); //Instanciamos el ArrayList donde guardaremos los proyectos que esten completados

        for (Project p : projects) {
            ArrayList<Task> tasks = mt.getProjectTask(p.getId_project()); //Cargamos el ArrayList de las tareas de cada proyecto
            int taskComplete = 0; //Instanciamos las tareas completadas y las iniciamos a 0
            double projectComplete = 0; //Instanciamos una variable para saber si el proyecto esta completado y la iniciamos a 0

            if (tasks.size() != 0) {

                for (Task t : tasks) {
                    if (t.getState().equalsIgnoreCase("completada")) {
                        taskComplete++;
                    }
                }
                projectComplete = ((100 * taskComplete) / tasks.size()) * 0.01;

            }

            if(projectComplete == 1) {
                p100.add(p);
            }
        }

        String[] titles = {"ID", "NIF CLIENTE", "NOMBRE", "DESCRIPCION", "FECHA ENTREGA"}; // Titulos de las columnas

        File file = new File("CompleteProjects.xlsx"); // Creamos el fichero

        file.delete(); // Si existe un fichero anterior, se borra

        Workbook workbook = new XSSFWorkbook(); // Creamos el libro de trabajo

        Sheet page = workbook.createSheet("Proyectos Completados"); // Creamos la pagina para el informe

        Row fila = null; // Instanciamos las filas

        CellStyle header = workbook.createCellStyle(); // Instanciamos un estilo para la cabecera

        header.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex()); //Color de fondo
        header.setFillPattern(FillPatternType.SOLID_FOREGROUND); //Tipo de color de fondo (no se porque hay que ponerlo pero lo pide xD)
        header.setAlignment(HorizontalAlignment.CENTER_SELECTION); //Alineacion del texto horizontal
        header.setBorderRight(BorderStyle.SLANTED_DASH_DOT); //Estilo de borde derecho
        header.setVerticalAlignment(VerticalAlignment.CENTER); //Alineacion del texto vertical
        header.setFillBackgroundColor(IndexedColors.WHITE.getIndex()); // BETA: intento de poner el color de la letra en blanco
        header.setDataFormat((short) 30); //BETA: intento de poner el tama�o de letra mas grande

        CellRangeAddress region = CellRangeAddress.valueOf("A" + 1 + ":E" // Creamos una region para poder combinar celdas entre la region seleccionada A1:B3
                + 3);
        page.addMergedRegion(region); // Creamos la celda combinada

        fila = page.createRow(0);  //Creamos la fila para poder meter textos en las celdas
        Cell cabecera = fila.createCell(0); //Creamos la celda para meter el titulo en el
        cabecera.setCellStyle(header); //Establecemos el estilo de la celda
        cabecera.setCellValue("INFORME"); //Establecemos el texto que contendra la celda

        fila = page.createRow(3);

        CellStyle sTitles = workbook.createCellStyle(); // Estilo de celda para la barra de titulos

        sTitles.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex()); //Color de fondo de la barra de titulos
        sTitles.setFillPattern(FillPatternType.SOLID_FOREGROUND); //Tipo de color de fondo (no se porque hay que ponerlo pero lo pide xD)
        sTitles.setBorderBottom(BorderStyle.THICK); //Tipo de borde inferior, en este caso es un borde negro gordo
        sTitles.setBorderTop(BorderStyle.THICK); //Tipo de borde superior, en este caso es un borde negro gordo
        sTitles.setBorderRight(BorderStyle.THICK); //Tipo de borde derecho, en este caso es un borde negro gordo
        sTitles.setAlignment(HorizontalAlignment.CENTER_SELECTION); //Alineacion del texto horizontal

        // Creamos el encabezado
        for (int i = 0; i < titles.length; i++) { //Iniciamos el for en 0 y de longitud del array de strings
            // Creamos una celda en esa fila, en la posicion
            // indicada por el contador del ciclo
            Cell celda = fila.createCell(i);

            celda.setCellStyle(sTitles); //Establecemos el estilo de la celda al que hemos definido anteriormente a para los titulos
            celda.setCellValue(titles[i]); //Establecemos el contenido de las celdas con los valores del array de strings
        }

        int r = 4; //Iniciamos el contador de la fila (r), a 4 para poder empezar a intorducir los datos desde ahi.
        int c = 0; //Iniciamos el contador de la columna (c), a 0 para que sea desde la primera celda.

        CellStyle texts = workbook.createCellStyle(); //Creamos un nuevo estilo para las celdas que contienen los datos

        texts.setBorderBottom(BorderStyle.DOUBLE); //Tipo de borde inferior, en este caso dobles barras
        texts.setBorderRight(BorderStyle.DOUBLE); //Tipo de borde derecho, en este caso dobles barras
        texts.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex()); //Establecemos el color de fondo de la celda
        texts.setFillPattern(FillPatternType.SOLID_FOREGROUND); //Tipo de color de fondo (no se porque hay que ponerlo pero lo pide xD)

        for(Project p : p100) {
            fila = page.createRow(r); //Creamos la fila para poder meter textos en las celdas
            for(int j = 0; j < 5; j++) { //Iniciamos el for para poder recorrer las celdas e introducir los datos
                Cell celda = fila.createCell(j); //Creamos la celda para meter los valores dentro
                celda.setCellStyle(texts); //Establecemos el estilo para las celdas.
                if(j==0) {
                    celda.setCellValue(p.getId_project()); //Si el contador es 0, introducimos el primer valor, en este caso el DNI
                }else if(j==1) {
                    celda.setCellValue(p.getNif_client()); //Si el contador es 1, introducimos el primer valor, en este caso el Nombre
                }else if(j==2) {
                    celda.setCellValue(p.getName()); //Si el contador es 2, introducimos el primer valor, en este caso el Apellido
                }else if(j==3) {
                    celda.setCellValue(p.getDescription()); //Si el contador es 3, introducimos el primer valor, en este caso el Trabajo
                }else if(j==4) {
                    celda.setCellValue(p.getDeliver_date());
                }
                c++; //Por cada vez que introduce un dato, vamos incrementando el valor de la columna
            }
            c=0; //Igualamos el valor de la columna a 0 para que vuelva a empezar de nuevo desde la primera columna
            r++; //Sumamos uno al contador de las filas para poder cambiar de fila, en este caso bajamos de fila
        }

        // Ahora guardaremos el archivo
        try {
            // Creamos el flujo de salida de datos,
            // apuntando al archivo donde queremos
            // almacenar el libro de Excel
            FileOutputStream salida = new FileOutputStream(file);

            // Almacenamos el libro de
            // Excel via ese
            // flujo de datos
            workbook.write(salida);

            // Cerramos el libro para concluir operaciones
            workbook.close();

            //Mensaje en la consola de que el archivo se ha creado correcto (se puede suprimir)
            LOGGER.log(Level.INFO, "Archivo creado existosamente en {0}", file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            //Mensaje de error de que no se ha podido crear el archivo, no deberia darlo, ya que cada ve que lo ejecutamos borramos el archivo anterior.)
            LOGGER.log(Level.SEVERE, "Archivo no localizable en sistema de archivos");
        } catch (IOException ex) {
            //Mensaje de error de entrada o salida
            LOGGER.log(Level.SEVERE, "Error de entrada/salida");
        }

    }

}
