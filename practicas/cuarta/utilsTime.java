import java.lang.*;
import java.util.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class utilsTime {
    
    public static void writeInFile(String filename, String content) {
        
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            boolean success = (new File("info")).mkdirs();
            if (success || (new File("info").exists() && new File("info").isDirectory())) {
                filename = "info/" + filename;
            }
            fw = new FileWriter(filename, true);
            bw = new BufferedWriter(fw);
            bw.write(content);
            
            System.out.println("Done");
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
    
    
}
