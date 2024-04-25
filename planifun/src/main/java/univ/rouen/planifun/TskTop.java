package univ.rouen.planifun;

import java.io.File;
import java.io.IOException;
import univ.rouen.planifun.app.analyzer.Analyze;
import univ.rouen.planifun.app.builder.xml.read.ReadingXML;

public class TskTop {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Erreur de syntaxe");
        } else {
            try {
                
                String filePath = args[0];
                File file = new File(filePath);
                
                if (file.exists()) {
                    ReadingXML reader = new ReadingXML(file);
                    reader.readFileXML();
                    Analyze analyze = new Analyze(reader.getSetTaskInFile());
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
