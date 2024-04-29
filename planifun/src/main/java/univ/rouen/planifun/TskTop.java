package univ.rouen.planifun;

import java.io.File;
import java.io.IOException;
import univ.rouen.planifun.app.analyzer.Analyze;
import univ.rouen.planifun.app.builder.xml.read.ReadingXML;

public class TskTop {

    // ENTERED POINT 

    public static void main(String[] args) {
        if (args.length != 1 && args.length != 2) {
            System.out.println("Erreur de syntaxe : path ou path number");
        } else {
            Integer number = null;
            if (args.length == 2) {
                try {
                    number = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Erreur de syntaxe : Un nombre est demandé");
                    return;
                }
                
            }
            try {
                String filePath = args[0];
                File file = new File(filePath);
                if (file.exists()) {
                    ReadingXML reader = new ReadingXML(file);
                    reader.readFileXML();
                    Analyze analyze = number == null ? 
                        new Analyze(reader.getSetTaskInFile()) 
                        : new Analyze(reader.getSetTaskInFile(), number.intValue());
                    analyze.analyze();
                    analyze.getAllTheMostUrgentTask();
                } else {
                    System.out.println("Le fichier n'existe pas : " + filePath);
                }
            } catch (IOException e) {
                System.out.println("Erreur de lecture du fichier");
            }
            
        }
    }
    
}
