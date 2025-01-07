package fr.Ciril;

import java.util.ArrayList;
import java.util.List;

public class Foret {

    private int nombreLigne;
    private int nombreColonne;
    private double probabilite;
    private List<List<Grille>> grilles;

    public int getNombreLigne() {
        return nombreLigne;
    }

    public void setNombreLigne(int nombreLigne) {
        this.nombreLigne = nombreLigne;
    }

    public int getNombreColonne() {
        return nombreColonne;
    }

    public void setNombreColonne(int nombreColonne) {
        this.nombreColonne = nombreColonne;
    }

    public double getProbabilite() {
        return probabilite;
    }

    public void setProbabilite(double probabilite) {
        this.probabilite = probabilite;
    }

    public List<List<Grille>> getGrilles() {
        return grilles;
    }

    public void setGrilles(List<List<Grille>> grilles) {
        this.grilles = grilles;
    }

    public List<Grille> getCasesAdjacentes(Grille grille) {
        List<Grille> adjacentes = new ArrayList<>();
        int x = grille.getLigneIndex();
        int y = grille.getColonneIndex();

        if (x > 0) adjacentes.add(grilles.get(x - 1).get(y));
        if (x < nombreLigne - 1) adjacentes.add(grilles.get(x + 1).get(y));
        if (y > 0) adjacentes.add(grilles.get(x).get(y - 1));
        if (y < nombreColonne - 1) adjacentes.add(grilles.get(x).get(y + 1));

        return adjacentes;
    }

    public boolean simulerEtape() {
        List<Grille> nouvellesCasesEnFeu = new ArrayList<>();

        for (var ligne : grilles) {
            for (var grille : ligne) {
                if (grille.getEtat() == Etat.EnFeu) {
                    grille.setEtat(Etat.RemplieDeCendre);
                    for (Grille adjacente : getCasesAdjacentes(grille)) {
                        if (adjacente.getEtat() == Etat.JamaisBrulee && Math.random() < probabilite) {
                            nouvellesCasesEnFeu.add(adjacente);
                        }
                    }
                }
            }
        }

        for (Grille grille : nouvellesCasesEnFeu) {
            grille.setEtat(Etat.EnFeu);
        }
        return !nouvellesCasesEnFeu.isEmpty();
    }

    private Grille getGrille(int ligneIndex, int colonneIndex) {
        return grilles.get(ligneIndex).get(colonneIndex);
    }

    public static class Grille {
        private int ligneIndex;
        private int colonneIndex;
        private Etat etat;

        public Grille(int ligneIndex, int colonneIndex, Etat etat) {
            this.ligneIndex = ligneIndex;
            this.colonneIndex = colonneIndex;
            this.etat = etat;
        }

        public int getLigneIndex() {
            return ligneIndex;
        }

        public void setLigneIndex(int ligneIndex) {
            this.ligneIndex = ligneIndex;
        }

        public int getColonneIndex() {
            return colonneIndex;
        }

        public void setColonneIndex(int colonneIndex) {
            this.colonneIndex = colonneIndex;
        }

        public Etat getEtat() {
            return etat;
        }

        public void setEtat(Etat etat) {
            this.etat = etat;
        }
    }

    enum Etat {
        JamaisBrulee, EnFeu, RemplieDeCendre
    }
}