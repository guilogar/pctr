import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.concurrent.*;

public class resImagen {
    // Metodo secuencial
    public static void ConvertirAGris(String ficheroOriginal, String ficheroResultado) throws IOException, InterruptedException {
        BufferedImage imagen = ImageIO.read(new File(ficheroOriginal));
        
        for (int i = 0; i < imagen.getHeight(); i++)
        {
            for(int j = 0; j < imagen.getWidth(); j++)
            {
                Color c = new Color(imagen.getRGB(i, j));
                int gris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                imagen.setRGB(i, j, (new Color(gris, gris, gris)).getRGB());
            }
        }

        ImageIO.write(imagen, "png", new File(ficheroResultado));
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        
        System.out.println("Convirtiendo a escala de grises");
        double initTiempo = System.currentTimeMillis();
        ConvertirAGris("uca.png", "uca_gris.png");
        double tiempoTotal = (System.currentTimeMillis()-initTiempo);
        
        utilsFile.writeInFile("info", "resImagen.txt", ""+0+" "+0+"\n");
        utilsFile.writeInFile("info", "resImagen.txt", ""+1+" "+(tiempoTotal / tiempoTotal)+"\n");
        /*
         *System.out.println("Cargando fichero en matriz");
         *matriz = cargar("uca_gris.png");
         *
         *System.out.println("Guardando matriz en fichero");
         *guardar(matriz, "uca_gris_procesada.png");
         */
    }
}
