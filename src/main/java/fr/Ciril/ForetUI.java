package ma.annotation;

import javax.swing.*;
import java.awt.*;
import static ma.annotation.Foret.Etat;

public class ForetUI {

    private final Foret foret;
    private JFrame frame;
    private JPanel gridPanel;

    public ForetUI(Foret foret) {
        this.foret = foret;
    }

    public void display() {
        frame = new JFrame("Grille 10x10 - Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(createMainPanel());
        frame.setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(createGridPanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);
        return mainPanel;
    }

    private JPanel createGridPanel() {
        gridPanel = new JPanel(new GridLayout(10, 10, 5, 5));
        gridPanel.setBackground(Color.LIGHT_GRAY);

        for (var ligne : foret.getGrilles()) {
            for (var grille : ligne) {
                gridPanel.add(createLabel(grille));
            }
        }
        return gridPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton etapeSuivant = new JButton("Étape suivante");
        etapeSuivant.setBackground(Color.BLUE);
        etapeSuivant.setForeground(Color.BLACK);
        etapeSuivant.setFont(new Font("Arial", Font.BOLD, 16));
        etapeSuivant.setFocusPainted(false);
        etapeSuivant.setPreferredSize(new Dimension(150, 50));

        etapeSuivant.addActionListener(e -> onNextStep());
        buttonPanel.add(etapeSuivant);
        return buttonPanel;
    }

    private JLabel createLabel(Foret.Grille grille) {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(getColorForEtat(grille.getEtat()));
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        label.putClientProperty("grille", grille);
        return label;
    }

    private Color getColorForEtat(Etat etat) {
        return switch (etat) {
            case EnFeu -> Color.RED;
            case JamaisBrulee -> Color.GREEN;
            default -> Color.DARK_GRAY;
        };
    }

    private void onNextStep() {
        boolean feuContinue = foret.simulerEtape();
        gridPanel.removeAll();

        for (var ligne : foret.getGrilles()) {
            for (var grille : ligne) {
                gridPanel.add(createLabel(grille));
            }
        }

        gridPanel.revalidate();
        gridPanel.repaint();

        if (!feuContinue) {
            JOptionPane.showMessageDialog(frame, "Simulation terminée : plus de feu !");
        }
    }
}
