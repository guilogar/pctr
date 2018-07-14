import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class utilsFile {
    
    public static void writeInFile(String fold, String filename, String content) {
        
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            if(fold != null) {
                boolean success = (new File(fold)).mkdirs();
                if (success || (new File(fold).exists() && new File(fold).isDirectory())) {
                    filename = fold + "/" + filename;
                }
            }
            fw = new FileWriter(filename, true);
            bw = new BufferedWriter(fw);
            bw.write(content);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                    
                if (fw != null)
                    fw.close();
                    
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    // Método para copiar un archivo a una ubicacion deseada.
    @SuppressWarnings("empty-statement")
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        
        if(!destFile.exists()) {
            destFile.createNewFile();
        }
        
        FileChannel origen = null;
        FileChannel destino = null;
        try {
            origen = new FileInputStream(sourceFile).getChannel();
            destino = new FileOutputStream(destFile).getChannel();

            long count = 0;
            long size = origen.size();              
            while((count += destino.transferFrom(origen, count, size-count))<size);
        }
        finally {
            if(origen != null) {
                origen.close();
            }
            if(destino != null) {
                destino.close();
            }
        }
    }
    
    private static void logs(String nombre_archivo, String operacion) {
        
        try {
            FileWriter archivo = null;
            
            if (utilsFile.newFile(nombre_archivo).exists() == false){
                archivo = new FileWriter(utilsFile.newFile(nombre_archivo), false);
            }
            
            archivo = new FileWriter(utilsFile.newFile(nombre_archivo), true);
            Calendar fechaActual = Calendar.getInstance();
            
            archivo.write((String.valueOf(fechaActual.get(Calendar.YEAR))
                    +"/"+String.valueOf(fechaActual.get(Calendar.MONTH)+1)
                    +"/"+String.valueOf(fechaActual.get(Calendar.DAY_OF_MONTH))
                    +"; "+String.valueOf(fechaActual.get(Calendar.HOUR_OF_DAY))
                    +":"+String.valueOf(fechaActual.get(Calendar.MINUTE))
                    +":"+String.valueOf(fechaActual.get(Calendar.SECOND))
                    +":"+String.valueOf(fechaActual.get(Calendar.MILLISECOND)))
                    +"; "+operacion+"\r\n\r\n");
            archivo.close();
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(utilsFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static File newFile(String nombre) throws URISyntaxException {
        return new File(utilsFile.ruta(nombre));
    }
    
    public static String ruta(String nombre) throws URISyntaxException {
        // Se utiliza para crear ficheros en la misma carpeta donde se encuentra
        // el codigo, no donde se llama al programa.
        
        // Para que funcione, se nesecita una clase llamada Main, claro esta.
        
        // Su principal utilidad, es en los paquetes .jar que se pueden ejecutar
        // desde cualquier directorio.
        try {
            Class<?> main = Class.forName("Main");
            return new File(main.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent() + "/" + nombre;
        } catch( ClassNotFoundException e ) { }
        return "./" + nombre;
    }
    
}

