package ma.annotation;

import junit.framework.TestCase;

import java.io.InputStream;
import java.util.List;

public class AppTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        App.foret = null;
    }

    public void testInitialiseContext_ValidInput() {
        InputStream inputStream = App.class.getClassLoader().getResourceAsStream("input.txt");
        assertNotNull(inputStream);

        App.initialiseContext();

        assertNotNull(App.foret);
        assertEquals(10, App.foret.getNombreLigne());
        assertEquals(10, App.foret.getNombreColonne());
        assertEquals(0.4, App.foret.getProbabilite(), 0.001);

        List<List<Foret.Grille>> grilles = App.foret.getGrilles();
        assertEquals(Foret.Etat.EnFeu, grilles.get(0).get(1).getEtat());
        assertEquals(Foret.Etat.EnFeu, grilles.get(0).get(6).getEtat());
        assertEquals(Foret.Etat.EnFeu, grilles.get(1).get(8).getEtat());
        assertEquals(Foret.Etat.JamaisBrulee, grilles.get(0).get(0).getEtat());
    }
}
