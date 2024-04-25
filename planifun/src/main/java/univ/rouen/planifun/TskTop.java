package univ.rouen.planifun;

import java.io.File;
import java.io.IOException;
import univ.rouen.planifun.app.analyzer.Analyze;
import univ.rouen.planifun.app.builder.xml.read.ReadingXML;

public class TskTop {
    public static void main(String[] args) {
        try {
            ReadingXML reader = new ReadingXML(new File("C:\\Users\\Izaalala\\Documents\\save_example.xml"));
            reader.readFileXML();
            System.out.println(reader.getSetTaskInFile().getAllTask().size());
            Analyze analyze = new Analyze(reader.getSetTaskInFile());
            analyze.analyze();
            analyze.getAllTheMostUrgentTask();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*if (args.length != 1) {
            System.out.println("Erreur de syntaxe");
        } else {
            try {
                ReadingXML reader = new ReadingXML(new File(args[0]));
                Analyze analyze = new Analyze(reader.getSetTaskInFile());
                analyze.getAllTheMostUrgentTask();
            } catch (IOException e) {
                System.out.println("Erreur de lecture");
            }

            
        }*/
    }
}
