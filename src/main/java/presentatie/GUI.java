package presentatie;

import data.Datalaag;
import inlezen.InlezenCovidGevallen;
import inlezen.InlezenLanden;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Cijfers;
import logica.Datum;
import logica.Helper;
import logica.Land;

/**
 *
 * @author louisdhont
 */

public class GUI extends javax.swing.JFrame {

    Datalaag dataLaag;
    InlezenLanden inlezenLanden = new InlezenLanden();
    InlezenCovidGevallen InlezenCovidGevallen = new InlezenCovidGevallen();
    Land landen = new Land();

    public GUI() throws SQLException, FileNotFoundException {
        initComponents();
        dataLaag = new Datalaag("dhontlouis");
        dataLaag.wisDataTabellen();
        inlezenLanden.landGegevensInlezen();
        InlezenCovidGevallen.covidGevallenInlezen();

        List<Land> landData = dataLaag.geefLanden();

        for (Land land : landData) {
            cbxLandenGrafiek.addItem(land.getLandNaam());
            cbxLandCodeGegevens.addItem(land.getLandCode());
        }
        cbxLandCodeGegevens.setSelectedIndex(18); // standaard ingesteld als België
        cbxLandenGrafiek.setSelectedIndex(18);
        cbxMaandGrafiek.setSelectedItem("March");
        ckbCijfersTonen.setSelected(true);
        jPanel2.toonCijfers(true);
        jSpinnerPuntDikte.setValue(5);

        lblGevallenWereldWijd.setText("Total registered corona cases worldwide: " + Helper.getalFormatten(dataLaag.geefAantalGevallenWereldwijd()));
        lblAantalLanden.setText("Data available from " + dataLaag.geefAantalLanden() + " countries");
        lblTitelGrafiek.setText("Graph with corona values from " + cbxLandenGrafiek.getSelectedItem().toString() + " in the month " + cbxMaandGrafiek.getSelectedItem().toString());
        lblAantalGevallenInMaand.setText("Total value of corona cases in the month " + cbxMaandGrafiek.getSelectedItem() + ":");
        lblAantalRecords.setText("Number of records available in the database: " + Helper.getalFormatten(dataLaag.geefAantalRecordsBeschikbaar()));
        haalGegevensOpGrafiek();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new presentatie.JPanel();
        jPanelGegevens = new javax.swing.JPanel();
        cbxLandCodeGegevens = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbxDatum = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        lblGegevensTitel = new javax.swing.JLabel();
        btnToonGegevens = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaGegevens = new javax.swing.JTextArea();
        jPanelGrafiekData = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxLandenGrafiek = new javax.swing.JComboBox<>();
        btnToonGrafiek = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblGevallenWereldWijd = new javax.swing.JLabel();
        lblAantalLanden = new javax.swing.JLabel();
        cbxMaandGrafiek = new javax.swing.JComboBox<>();
        btnResetFilters = new javax.swing.JButton();
        lblAantalGevallenInMaand = new javax.swing.JLabel();
        ckbCijfersTonen = new javax.swing.JCheckBox();
        lblAantalRecords = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSpinnerPuntDikte = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        lblTitelGrafiek = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jPanelGegevens.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cbxLandCodeGegevens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLandCodeGegevensActionPerformed(evt);
            }
        });

        jLabel2.setText("Choose a landcode:");

        jLabel3.setText("Pick a date");

        lblGegevensTitel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblGegevensTitel.setText("Data from");

        btnToonGegevens.setText("Show data");
        btnToonGegevens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToonGegevensActionPerformed(evt);
            }
        });

        txaGegevens.setEditable(false);
        txaGegevens.setColumns(20);
        txaGegevens.setRows(5);
        jScrollPane1.setViewportView(txaGegevens);

        javax.swing.GroupLayout jPanelGegevensLayout = new javax.swing.GroupLayout(jPanelGegevens);
        jPanelGegevens.setLayout(jPanelGegevensLayout);
        jPanelGegevensLayout.setHorizontalGroup(
            jPanelGegevensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGegevensLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelGegevensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelGegevensLayout.createSequentialGroup()
                        .addComponent(lblGegevensTitel, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnToonGegevens, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelGegevensLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelGegevensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(202, 202, 202)
                        .addGroup(jPanelGegevensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxLandCodeGegevens, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxDatum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );
        jPanelGegevensLayout.setVerticalGroup(
            jPanelGegevensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGegevensLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelGegevensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxLandCodeGegevens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanelGegevensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(38, 38, 38)
                .addGroup(jPanelGegevensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblGegevensTitel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnToonGegevens))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelGrafiekData.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Choose a country");

        cbxLandenGrafiek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLandenGrafiekActionPerformed(evt);
            }
        });

        btnToonGrafiek.setText("Show graph");
        btnToonGrafiek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToonGrafiekActionPerformed(evt);
            }
        });

        jLabel4.setText("Choose a month:");

        lblGevallenWereldWijd.setText("Totaal aantal gevallen wereldwijd:");

        lblAantalLanden.setText("Aantal landen waarover data verzameld is:");

        btnResetFilters.setText("Reset filters");
        btnResetFilters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFiltersActionPerformed(evt);
            }
        });

        lblAantalGevallenInMaand.setText("Total value of corona cases in the month:");

        ckbCijfersTonen.setText("Show values in graph");
        ckbCijfersTonen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbCijfersTonenActionPerformed(evt);
            }
        });

        lblAantalRecords.setText("Aantal records beschikbaar in de databank:");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel7.setText("regular data");

        jSpinnerPuntDikte.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerPuntDikteStateChanged(evt);
            }
        });

        jLabel8.setText("Point thickness:");

        javax.swing.GroupLayout jPanelGrafiekDataLayout = new javax.swing.GroupLayout(jPanelGrafiekData);
        jPanelGrafiekData.setLayout(jPanelGrafiekDataLayout);
        jPanelGrafiekDataLayout.setHorizontalGroup(
            jPanelGrafiekDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGrafiekDataLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanelGrafiekDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAantalLanden, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAantalRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGevallenWereldWijd, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGrafiekDataLayout.createSequentialGroup()
                .addGroup(jPanelGrafiekDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelGrafiekDataLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanelGrafiekDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGrafiekDataLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinnerPuntDikte, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ckbCijfersTonen, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelGrafiekDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxLandenGrafiek, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelGrafiekDataLayout.createSequentialGroup()
                                .addComponent(btnResetFilters, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnToonGrafiek, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbxMaandGrafiek, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelGrafiekDataLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblAantalGevallenInMaand, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(29, 29, 29))
        );
        jPanelGrafiekDataLayout.setVerticalGroup(
            jPanelGrafiekDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGrafiekDataLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelGrafiekDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxLandenGrafiek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelGrafiekDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGrafiekDataLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(ckbCijfersTonen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelGrafiekDataLayout.createSequentialGroup()
                        .addComponent(cbxMaandGrafiek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(jPanelGrafiekDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnToonGrafiek)
                            .addComponent(btnResetFilters)
                            .addComponent(jSpinnerPuntDikte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(lblAantalGevallenInMaand)
                .addGap(17, 17, 17)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGevallenWereldWijd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAantalLanden)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAantalRecords)
                .addGap(21, 21, 21))
        );

        lblTitelGrafiek.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblTitelGrafiek.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitelGrafiek.setText("Titel Grafiek");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel5.setText("Graph data");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel6.setText("Data of a country");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitelGrafiek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanelGegevens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanelGrafiekData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(36, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitelGrafiek)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelGegevens, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelGrafiekData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxLandCodeGegevensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLandCodeGegevensActionPerformed
        try {
            String selectieLand = cbxLandCodeGegevens.getSelectedItem().toString();
            List<Datum> datumData = dataLaag.geefDatums(selectieLand);
            cbxDatum.removeAllItems();
            for (Datum datum : datumData) {
                cbxDatum.addItem(datum.getDatum());
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Kon de landen en datum gegevens niet ophalen");
        }
    }//GEN-LAST:event_cbxLandCodeGegevensActionPerformed

    private void cbxLandenGrafiekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLandenGrafiekActionPerformed
        try {
            List<Datum> Maanden = dataLaag.geefBeschikbareMaanden(cbxLandenGrafiek.getSelectedItem().toString());
            cbxMaandGrafiek.removeAllItems();
            for (Datum maand : Maanden) {
                cbxMaandGrafiek.addItem(maand.getDatum());
            }
            lblTitelGrafiek.setText("Graph with corona values from " + cbxLandenGrafiek.getSelectedItem().toString() + " in the month " + cbxMaandGrafiek.getSelectedItem().toString());
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Kon de beschkbare maanden niet ophalen");
        }
    }//GEN-LAST:event_cbxLandenGrafiekActionPerformed

    private void btnToonGegevensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToonGegevensActionPerformed
        String gegevens = "";
        String selectieLand = cbxLandCodeGegevens.getSelectedItem().toString();
        try {
            String landNaam = dataLaag.geefLandNaam(selectieLand);
            lblGegevensTitel.setText("Data from " + landNaam);
            gegevens += "Number of registered cases " + cbxDatum.getSelectedItem() + ": " + Helper.getalFormatten(dataLaag.geefAantalGevallenOpDatum(cbxDatum.getSelectedItem().toString(), selectieLand)) + " cases\n";
            gegevens += "Date of first case: " + dataLaag.geefDatumEersteGeval(cbxLandCodeGegevens.getSelectedItem().toString()) + "\n";
            gegevens += "Max number of registered cases in " + landNaam + " on "
                    + dataLaag.geefDatumMaximumAantal(selectieLand) + ": " + Helper.getalFormatten(dataLaag.geefMaximumAantal(selectieLand)) + " cases\n";
            gegevens += "Totaal number of registered cases in " + landNaam + ": " + Helper.getalFormatten(dataLaag.geefTotaalAantalInLand(selectieLand)) + "\n";
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Kon de gegevens niet ophalen");
        }
        txaGegevens.setText(gegevens);
    }//GEN-LAST:event_btnToonGegevensActionPerformed

    private void btnToonGrafiekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToonGrafiekActionPerformed
        lblAantalGevallenInMaand.setText("Totaal number of registered cases in the month " + cbxMaandGrafiek.getSelectedItem() + ": " + Helper.getalFormatten(haalGegevensOpGrafiek()));
        lblTitelGrafiek.setText("Graph with corona values from " + cbxLandenGrafiek.getSelectedItem() + " in the months " + cbxMaandGrafiek.getSelectedItem());
    }//GEN-LAST:event_btnToonGrafiekActionPerformed

    private void btnResetFiltersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFiltersActionPerformed
        txaGegevens.setText("");
        cbxLandCodeGegevens.setSelectedIndex(8);
        cbxLandenGrafiek.setSelectedIndex(18);
        cbxMaandGrafiek.setSelectedItem("March");
        lblGegevensTitel.setText("Data from");
        jSpinnerPuntDikte.setValue(5);
        ckbCijfersTonen.setSelected(true);
        jPanel2.toonCijfers(ckbCijfersTonen.isSelected());
        haalGegevensOpGrafiek();
        btnToonGrafiek.doClick();
    }//GEN-LAST:event_btnResetFiltersActionPerformed

    private void ckbCijfersTonenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbCijfersTonenActionPerformed
        jPanel2.toonCijfers(ckbCijfersTonen.isSelected());
    }//GEN-LAST:event_ckbCijfersTonenActionPerformed

    private void jSpinnerPuntDikteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerPuntDikteStateChanged
        if ((int) jSpinnerPuntDikte.getValue() >= 10) {
            jSpinnerPuntDikte.setValue(10);
        } else if ((int) jSpinnerPuntDikte.getValue() < 0) {
            jSpinnerPuntDikte.setValue(0);
        }
        jPanel2.setPuntDikte((int) jSpinnerPuntDikte.getValue());
    }//GEN-LAST:event_jSpinnerPuntDikteStateChanged

    private int haalGegevensOpGrafiek() {
        int totaalAantal = 0;
        try {
            List<Cijfers> cijfers = dataLaag.geefAantallenInMaand(cbxMaandGrafiek.getSelectedItem().toString(), cbxLandenGrafiek.getSelectedItem().toString());
            jPanel2.setCijferGUI(cijfers);
            for (Cijfers cijfer : cijfers) {
                totaalAantal += cijfer.getAantal();
            }
            List<Datum> datums = dataLaag.geefDagenInMaand(cbxMaandGrafiek.getSelectedItem().toString(), cbxLandenGrafiek.getSelectedItem().toString());
            jPanel2.setDatumGUI(datums);
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Kon de grafiek data niet ophalen");
        }
        return totaalAantal;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new GUI().setVisible(true);
                } catch (SQLException | FileNotFoundException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResetFilters;
    private javax.swing.JButton btnToonGegevens;
    private javax.swing.JButton btnToonGrafiek;
    private javax.swing.JComboBox<String> cbxDatum;
    private javax.swing.JComboBox<String> cbxLandCodeGegevens;
    private javax.swing.JComboBox<String> cbxLandenGrafiek;
    private javax.swing.JComboBox<String> cbxMaandGrafiek;
    private javax.swing.JCheckBox ckbCijfersTonen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private presentatie.JPanel jPanel2;
    private javax.swing.JPanel jPanelGegevens;
    private javax.swing.JPanel jPanelGrafiekData;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerPuntDikte;
    private javax.swing.JLabel lblAantalGevallenInMaand;
    private javax.swing.JLabel lblAantalLanden;
    private javax.swing.JLabel lblAantalRecords;
    private javax.swing.JLabel lblGegevensTitel;
    private javax.swing.JLabel lblGevallenWereldWijd;
    private javax.swing.JLabel lblTitelGrafiek;
    private javax.swing.JTextArea txaGegevens;
    // End of variables declaration//GEN-END:variables
}