package inlezen;

import data.Datalaag;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import logica.Cijfers;
import logica.Datum;
import logica.Land;

/**
 *
 * @author louisdhont
 */
public class InlezenCovidGevallen {

    private static final String BESTAND = "src/main/java/gegevens/daily-cases-covid-19.csv";
    private static final ArrayList<String> LANDDATUM = new ArrayList<>();
    private static final ArrayList<String> LANDCODES = new ArrayList<>();
    private static final ArrayList<Integer> LANDAANTAL = new ArrayList<>();
    Datalaag dataLaag;
    Cijfers cijfers;
    Datum datum;
    Land land;

    public void covidGevallenInlezen() throws SQLException, FileNotFoundException {
        dataLaag = new Datalaag("dhontlouis");
        Scanner scanCode = null;
        Scanner scanDatum = null;
        Scanner scanAantal = null;
        try {
            scanDatum = new Scanner(new File(BESTAND));
            scanCode = new Scanner(new File(BESTAND));
            scanAantal = new Scanner(new File(BESTAND));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Bestand: " + BESTAND + " kon niet worden gevonden", "CSV error", JOptionPane.ERROR_MESSAGE);
            throw new FileNotFoundException("Kon het opgegeven bestand niet vinden");
        }

        // Opslaan van eerste lijn met kolomnaam
        String lijstLandCode = scanCode.nextLine().split("\"")[0];
        String lijstLandDaum = scanDatum.nextLine().split(",")[0];
        String lijstIndexAantal = scanAantal.nextLine().split(",")[0];

        while (scanDatum.hasNextLine()) {
            LANDDATUM.add(scanDatum.nextLine().split("\"")[1]);
            LANDCODES.add(scanCode.nextLine().split(",")[1]);
            LANDAANTAL.add(Integer.parseInt(scanAantal.nextLine().split(",")[4]));
        }

        for (int i = 0; i < LANDDATUM.size(); i++) {
            if (i + 1 != LANDDATUM.size()) {
                cijfers = new Cijfers(LANDAANTAL.get(i));
                datum = new Datum(LANDDATUM.get(i));
                land = new Land(LANDCODES.get(i));
                dataLaag.invoegenCovidGevallen(land.getLandCode(), datum.omzettenDatum(datum.getDatum()), cijfers.getAantal());
            } else {
                break;
            }
        }
    }
}
