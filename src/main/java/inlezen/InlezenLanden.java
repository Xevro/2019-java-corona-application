package inlezen;

import data.Datalaag;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import logica.Land;

/**
 *
 * @author louisdhont
 */
public class InlezenLanden {

    private static final String BESTAND = "src/main/java/gegevens/daily-cases-covid-19.csv";
    private static final ArrayList<String> LANDNAMEN = new ArrayList<>();
    private static final ArrayList<String> LANDCODES = new ArrayList<>();
    Datalaag dataLaag;

    public void landGegevensInlezen() throws SQLException, FileNotFoundException {
        dataLaag = new Datalaag("dhontlouis");
        Scanner scanLand = null;
        Scanner scanCode = null;
        try {
            scanLand = new Scanner(new File(BESTAND));
            scanCode = new Scanner(new File(BESTAND));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Bestand: " + BESTAND + " kon niet worden gevonden", "CSV error", JOptionPane.ERROR_MESSAGE);
            throw new FileNotFoundException("Kon het opgegeven bestand niet vinden");
        }

        // Opslaan van eerste lijn met kolomnamen
        String lijstLandNaam = scanLand.nextLine().split(",")[0];
        String lijstnaamCode = scanCode.nextLine().split(",")[0];

        while (scanLand.hasNextLine()) {
            LANDNAMEN.add(scanLand.nextLine().split(",")[0]);
            LANDCODES.add(scanCode.nextLine().split(",")[1]);
        }

        for (int i = 0; i < LANDNAMEN.size(); i++) {
            if (i == (LANDNAMEN.size() - 1)) {
                Land landen = new Land(LANDNAMEN.get(i), LANDCODES.get(i));
                dataLaag.invoegenLandInfo(landen.getLandCode(), landen.getLandNaam());
            }
            if (i + 1 != LANDNAMEN.size()) {
                if (!LANDNAMEN.get(i).equals(LANDNAMEN.get(i + 1))) {
                    Land landen = new Land(LANDNAMEN.get(i), LANDCODES.get(i));
                    dataLaag.invoegenLandInfo(landen.getLandCode(), landen.getLandNaam());
                }
            } else {
                break;
            }
        }
    }
}
