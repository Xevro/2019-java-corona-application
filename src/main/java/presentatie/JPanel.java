package presentatie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import logica.Cijfers;
import logica.Datum;

/**
 *
 * @author louisdhont
 */
public class JPanel extends javax.swing.JPanel {

    Graphics graph;
    Graphics2D g2d;

    private final Color LIJNKLEUR = new Color(0, 0, 0, 35);
    private final Color LIJNKLEURGRAPH = new Color(0, 0, 0);
    private final Color PUNTKLEUR = new Color(235, 64, 52);
    private int puntDikte = 5;
    private final int WAARDE = 25;
    private final int WAARDEPOS = 50;
    private final int WAARDESCALE = 75;
    private final ArrayList<Integer> cijfersLijst = new ArrayList<>();
    private final ArrayList<String> datumsLijst = new ArrayList<>();
    private int cijferLijstgrote = 0;
    private boolean toonCijfers;

    public JPanel() {
        initComponents();
    }

    public void setCijferGUI(List<Cijfers> cijfersList) {
        if (!cijfersLijst.isEmpty()) {
            cijfersLijst.clear();
        }
        for (Cijfers cijfer : cijfersList) {
            cijfersLijst.add(cijfer.getAantal());
        }
        this.repaint();
    }

    public void setDatumGUI(List<Datum> datumsList) {
        if (!datumsLijst.isEmpty()) {
            datumsLijst.clear();
        }
        for (Datum datum : datumsList) {
            datumsLijst.add(datum.getDatum().substring(5).replace("-", "/"));
        }
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!this.cijfersLijst.isEmpty() || !this.datumsLijst.isEmpty()) {
            super.paintComponent(g);
            graph = (Graphics) g;
            g2d = (Graphics2D) g;
            cijferLijstgrote = cijfersLijst.size();
            //Tekenen van achtergrond canvas
            graph.setColor(Color.WHITE);
            graph.fillRect(WAARDEPOS, WAARDE, getWidth() - WAARDESCALE, getHeight() - (WAARDESCALE));
            graph.setFont(new Font("Verdana", Font.PLAIN, 11));

            tekenenXYLijnen();
            tekenenChart();
        }
    }

    private void tekenenChart() {
        List<Point> puntenLocatie = new ArrayList<>();
        double xSchaal = ((double) getWidth() - WAARDESCALE) / (cijferLijstgrote - 1);
        double ySchaal = ((double) getHeight() - WAARDESCALE) / (geefGrootsteAantalInLijst() - geefKleinsteAantalInLijst());

        for (int i = 0; i < cijferLijstgrote; i++) {
            puntenLocatie.add(new Point((int) (i * xSchaal + WAARDEPOS), (int) ((geefGrootsteAantalInLijst() - cijfersLijst.get(i)) * ySchaal + WAARDE)));
        }
        for (int i = 0; i < puntenLocatie.size() - 1; i++) {
            graph.setColor(LIJNKLEURGRAPH);
            graph.drawLine(puntenLocatie.get(i).x, puntenLocatie.get(i).y - 1, puntenLocatie.get(i + 1).x, puntenLocatie.get(i + 1).y - 1);
        }

        for (int i = 0; i < puntenLocatie.size(); i++) {
            if (toonCijfers) {
                graph.setColor(Color.BLACK);
                graph.drawString(String.valueOf(cijfersLijst.get(i)), puntenLocatie.get(i).x - WAARDE + 21, puntenLocatie.get(i).y + cijferLijstgrote - 35);
            }
            graph.setColor(PUNTKLEUR);
            graph.fillOval(puntenLocatie.get(i).x - puntDikte / 2, puntenLocatie.get(i).y - 1 - puntDikte / 2, puntDikte, puntDikte);
        }
    }

    private void tekenenXYLijnen() {
        Stroke stippelLijn = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
        Stroke basisLijn = new BasicStroke();

        // Lijnen verticaal op X-as
        for (int i = 0; i < cijferLijstgrote; i++) {
            if (cijferLijstgrote > 1) {
                int x = Math.round(i * (getWidth() - WAARDE - WAARDEPOS) / (cijferLijstgrote - 1) + WAARDEPOS);
                int y = Math.round(getHeight() - WAARDEPOS);

                g2d.setStroke(stippelLijn);
                graph.setColor(LIJNKLEUR);
                graph.drawLine(x, y - 5, x, WAARDE);
                graph.setColor(Color.BLACK);
                graph.drawString(String.valueOf(datumsLijst.get(i)), x - WAARDE, y + cijferLijstgrote + 10);
                g2d.setStroke(basisLijn);
                g2d.drawLine(x, y, x, y - 5); // kleine zwarte strepen onderaan X-as
            }
        }
        // Lijnen verticaal op Y-as
        for (int i = 0; i < cijferLijstgrote + 1; i++) {
            int y = Math.round(getHeight() - ((i * (getHeight() - WAARDEPOS - WAARDE)) / cijferLijstgrote + WAARDEPOS));
            g2d.setStroke(stippelLijn);
            graph.setColor(LIJNKLEUR);
            graph.drawLine(WAARDEPOS + puntDikte, y, getWidth() - WAARDE, y);
            graph.setColor(Color.BLACK);
            String labelY = ((int) ((geefKleinsteAantalInLijst() + (geefGrootsteAantalInLijst() - geefKleinsteAantalInLijst()) * ((i * 1.0) / cijferLijstgrote)))) + "";
            graph.drawString(labelY, 0, y);
            graph.drawLine(WAARDEPOS, y, WAARDEPOS + 5, y);
            g2d.setStroke(basisLijn);
        }
    }

    private int geefKleinsteAantalInLijst() {
        int minAantal = Integer.MAX_VALUE;
        for (Integer aantal : cijfersLijst) {
            minAantal = Math.min(minAantal, aantal);
        }
        return minAantal;
    }

    private double geefGrootsteAantalInLijst() {
        int maxAantal = Integer.MIN_VALUE;
        for (Integer aantal : cijfersLijst) {
            maxAantal = Math.max(maxAantal, aantal);
        }
        return maxAantal;
    }

    public void toonCijfers(boolean status) {
        toonCijfers = status;
        repaint();
    }

    public void setPuntDikte(int puntdikte) {
        this.puntDikte = puntdikte;
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 746, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 559, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
