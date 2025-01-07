package fr.Ciril;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class ForetTest extends TestCase {

    private Foret foret;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        foret = new Foret();
        foret.setNombreLigne(3);
        foret.setNombreColonne(3);
        foret.setProbabilite(0.5);

        List<List<Foret.Grille>> grilles = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Foret.Grille> ligne = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                ligne.add(new Foret.Grille(i, j, Foret.Etat.JamaisBrulee));
            }
            grilles.add(ligne);
        }

        grilles.get(1).get(1).setEtat(Foret.Etat.EnFeu);
        foret.setGrilles(grilles);
    }

    public void testInitialisation() {
        assertEquals(3, foret.getNombreLigne());
        assertEquals(3, foret.getNombreColonne());

        List<List<Foret.Grille>> grilles = foret.getGrilles();
        assertEquals(Foret.Etat.EnFeu, grilles.get(1).get(1).getEtat());
        assertEquals(Foret.Etat.JamaisBrulee, grilles.get(0).get(0).getEtat());
    }

    public void testGetCasesAdjacentes() {
        Foret.Grille caseCentrale = foret.getGrilles().get(1).get(1);
        List<Foret.Grille> adjacentes = foret.getCasesAdjacentes(caseCentrale);

        assertNotNull(adjacentes);
        assertEquals(4, adjacentes.size());

        boolean haut = adjacentes.contains(foret.getGrilles().get(0).get(1));
        boolean bas = adjacentes.contains(foret.getGrilles().get(2).get(1));
        boolean gauche = adjacentes.contains(foret.getGrilles().get(1).get(0));
        boolean droite = adjacentes.contains(foret.getGrilles().get(1).get(2));

        assertTrue(haut);
        assertTrue(bas);
        assertTrue(gauche);
        assertTrue(droite);
    }
}
