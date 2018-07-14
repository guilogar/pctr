import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.concurrent.*;

public class resImagenParGru implements Runnable {
    private static int tareas = Runtime.getRuntime().availableProcessors();
    private static int[][] matriz;
    private int linf, lsup;
    private BufferedImage imagen;
    
    public resImagenParGru(int linf, int lsup, BufferedImage imagen) {
        this.linf = linf;
        this.lsup = lsup;
        this.imagen = imagen;
    }
    
    public void run() {
        for (int i = this.linf; i <= this.lsup; i++) {
            for(int j = 0; j < imagen.getWidth(); ++j) {
                Color c = new Color(imagen.getRGB(i, j));
                int gris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                imagen.setRGB(i, j, (new Color(gris, gris, gris)).getRGB());
            }
        }
    }
    
    public static int[][] cargar(String fichero) throws IOException{
        
        BufferedImage imagen = ImageIO.read(new File(fichero));
        
        int[][] matriz = new int[imagen.getHeight()][imagen.getWidth()];
        
        for(int i = 0 ; i < imagen.getHeight() ; ++i)
        {
            for(int j = 0 ; j < imagen.getWidth() ; ++j)
            {
                Color c = new Color(imagen.getRGB(j, i));
                matriz[i][j] = ((c.getRed() + c.getGreen() + c.getBlue()) / 3);
            }
        }
        
        return matriz;
    }
    
    public static void guardar(int[][] matriz, String fichero) throws IOException{
        
        BufferedImage imagen = new BufferedImage(matriz[0].length, matriz.length, BufferedImage.TYPE_4BYTE_ABGR);
        
        for(int i = 0 ; i < matriz.length ; ++i)
        {
            for(int j = 0 ; j < matriz[0].length ; ++j)
            {
                Color c = new Color(matriz[j][i], matriz[j][i], matriz[j][i]);
                imagen.setRGB(i, j, c.getRGB());
            }
        }
        
        ImageIO.write(imagen, "png", new File(fichero));
    }
    
    // Metodo que utiliza el grano grueso.
    public static void ConvertirAGris(String ficheroOriginal, String ficheroResultado) throws IOException, InterruptedException {
        
        ExecutorService pool = Executors.newCachedThreadPool();
        BufferedImage imagen = ImageIO.read(new File(ficheroOriginal));
        int linf = 0;
        int lsup = 0;
        
        for (int i = 0; i < tareas && i < imagen.getHeight(); i++) {
            lsup = linf + (imagen.getHeight() / tareas);
            if(imagen.getHeight() >= tareas) {
                lsup -= 1;
            }
            pool.execute(new resImagenParGru(linf, lsup, imagen));
            linf = lsup + 1;
        }
        if(imagen.getHeight() > tareas && imagen.getHeight() % tareas != 0) {
            lsup = linf + (imagen.getHeight() % tareas) - 1;
            pool.execute(new resImagenParGru(linf, lsup, imagen));
        }
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
        
        ImageIO.write(imagen, "png", new File(ficheroResultado));
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        
        System.out.println("Convirtiendo a escala de grises");
        ConvertirAGris("uca.png", "uca_gris.png");
        
        /*
         *System.out.println("Cargando fichero en matriz");
         *matriz = cargar("uca_gris.png");
         *
         *System.out.println("Guardando matriz en fichero");
         *guardar(matriz, "uca_gris_procesada.png");
         */
    }
}
