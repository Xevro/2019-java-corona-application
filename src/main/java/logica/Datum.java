package logica;

/**
 *
 * @author louisdhont
 */
public class Datum {

    private String datum;

    public Datum(String datum) {
        this.datum = datum;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        if (datum == null) {
            throw new IllegalArgumentException("Datum mag niet leeg zijn.");
        }
        this.datum = datum;
    }

    public String omzettenDatum(String datum) {
        String maandGetal = "";
        String dag = datum.substring(4, 6).replace(",", "");
        
        if(Integer.valueOf(dag) > 31 || Integer.valueOf(dag) <= 0) {
            throw new NumberFormatException("Opgegeven datum is niet geldig, dag is groter/kleiner dan toegestane waarde");
        }
        
        if (datum.contains("Jan")) {
            maandGetal = "01";
        } else if (datum.contains("Feb")) {
            maandGetal = "02";
        } else if (datum.contains("Mar")) {
            maandGetal = "03";
        } else if (datum.contains("Apr")) {
            maandGetal = "04";
        } else if (datum.contains("Mei")) {
            maandGetal = "05";
        } else if (datum.contains("Jun")) {
            maandGetal = "06";
        } else if (datum.contains("Jul")) {
            maandGetal = "07";
        } else if (datum.contains("Aug")) {
            maandGetal = "08";
        } else if (datum.contains("Sep")) {
            maandGetal = "09";
        } else if (datum.contains("Okt")) {
            maandGetal = "10";
        } else if (datum.contains("Nov")) {
            maandGetal = "11";
        } else if (datum.contains("Dec")) {
            maandGetal = "12";
        } else {
            throw new IllegalArgumentException("Opgegeven datum is niet geldig, gelieve een geldige datum in te geven met formaat: MMM DD, YYYY (Jan 13, 2020)");
        }
        return datum.substring(7).trim() + "/" + maandGetal + "/" + dag;
    }
}
