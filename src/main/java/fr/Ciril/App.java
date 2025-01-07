package ma.annotation;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ma.annotation.Foret.Grille;
import static ma.annotation.Foret.Etat;

public class App {

    public static Foret foret;

    static {
        initialiseContext();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ForetUI foretUI = new ForetUI(foret);
            foretUI.display();
        });
    }

    public static void initialiseContext() {
        try (InputStream inputStream = App.class.getClassLoader().getResourceAsStream("input.txt")) {
            if (inputStream == null) {
                System.out.println("Le fichier input n'existe pas");
                return;
            }
            foret = new Foret();
            try (Scanner scanner = new Scanner(inputStream)) {
                foret.setNombreLigne(scanner.nextInt());
                foret.setNombreColonne(scanner.nextInt());
                foret.setProbabilite(scanner.nextDouble());
                List<String> grilleAvecFeu = new ArrayList<>();
                while (scanner.hasNext()) {
                    grilleAvecFeu.add(scanner.nextLine());
                }

                List<List<Foret.Grille>> grilles = new ArrayList<>();
                for (int i = 0; i < foret.getNombreLigne(); i++) {
                    List<Grille> grilleLigne = new ArrayList<>();
                    for (int j = 0; j < foret.getNombreColonne(); j++) {
                        grilleLigne.add(new Foret.Grille(i, j, grilleAvecFeu.contains(i + " " + j) ? Etat.EnFeu : Etat.JamaisBrulee));
                    }
                    grilles.add(grilleLigne);
                }
                foret.setGrilles(grilles);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}