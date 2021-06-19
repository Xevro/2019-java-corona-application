package logica;

/**
 *
 * @author louisdhont
 */
public class Land {

    String landNaam;
    String landCode;

    public Land() {
        this.landNaam = "Belgium";
        this.landCode = "BEL";
    }

    public Land(String landCode) {
        this.landCode = landCode;
    }

    public Land(String landNaam, String landCode) {
        this.landNaam = landNaam;
        this.landCode = landCode;
    }

    public String getLandNaam() {
        return landNaam;
    }

    public void setLandNaam(String landNaam) {
        if (landNaam == null) {
            throw new IllegalArgumentException("Land naam mag niet leeg zijn.");
        }
        this.landNaam = landNaam;
    }

    public String getLandCode() {
        return landCode;
    }

    public void setLandCode(String landCode) {
        if (landCode == null) {
            throw new IllegalArgumentException("Land code mag niet leeg zijn.");
        }
        this.landCode = landCode;
    }

    @Override
    public String toString() {
        return landNaam + ", " + landCode;
    }   
}