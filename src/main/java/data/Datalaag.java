package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import logica.Cijfers;
import logica.Datum;
import logica.Land;

/**
 *
 * @author louisdhont
 */
public class Datalaag {

    private String dbName;
    private final String login = "root";
    private final String pass = "Azerty123";
    private Connection con;

    public String getDbName() {
        return dbName;
    }

    public Datalaag(String dbName) throws SQLException {
        this.dbName = dbName;
        makeConnection();
    }

    private void makeConnection() throws SQLException {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3307/" + dbName + "?serverTimezone=UTC&allowMultiQueries=true", login, pass);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kan geen verbinding maken met de databank.", "Databank connectie error", JOptionPane.ERROR_MESSAGE);
            throw new SQLException("Kon geen verbinding maken met de databank.");
        }
    }

    public void wisDataTabellen() throws SQLException {
        try {
            Statement stmt = this.con.createStatement();
            stmt.execute("DELETE FROM Covid19BevestigdeGevallen; DELETE FROM Landen;");
        } catch (SQLException ex) {
            throw new SQLException("Kon de tabellen niet leegmaken in de databank.");
        }
    }

    public void invoegenLandInfo(String landcode, String landnaam) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = this.con.prepareStatement("INSERT INTO Landen (LandCode, LandNaam) VALUES(?, ?)");
            stmt.setString(1, landcode);
            stmt.setString(2, landnaam);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet in de databank toevoegen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void invoegenCovidGevallen(String landcode, String datum, int aantal) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = this.con.prepareStatement("INSERT INTO Covid19BevestigdeGevallen (LandCode, Datum, Aantal) VALUES(?, ?, ?)");
            stmt.setString(1, landcode);
            stmt.setString(2, datum);
            stmt.setInt(3, aantal);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet in de databank toevoegen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public List<Land> geefLanden() throws SQLException {
        Statement stmt = null;
        List<Land> landenLijst = null;
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery("SELECT * FROM Landen ORDER BY LandNaam;");
            landenLijst = new ArrayList<>();
            while (rs.next()) {
                String landNaam = rs.getString("LandNaam");
                String landCode = rs.getString("LandCode");
                Land land = new Land(landNaam, landCode);
                landenLijst.add(land);
            }
        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return landenLijst;
    }

    public List<Datum> geefDatums(String landcode) throws SQLException {
        Statement stmt = null;
        List<Datum> datumLijst = null;
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT Datum FROM Covid19BevestigdeGevallen WHERE LandCode = \'" + landcode + "\';");
            datumLijst = new ArrayList<>();
            while (rs.next()) {
                String CovidGevaldatum = rs.getString("Datum");
                Datum datum = new Datum(CovidGevaldatum);
                datumLijst.add(datum);
            }

        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return datumLijst;
    }

    public List<Datum> geefBeschikbareMaanden(String landnaam) throws SQLException {
        Statement stmt = null;
        List<Datum> datumLijst = null;
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT(MONTHNAME(Datum)) AS Datum FROM Covid19BevestigdeGevallen WHERE (SELECT LandCode FROM Landen WHERE LandNaam = \"" + landnaam + "\") = LandCode ORDER BY Datum;");
            datumLijst = new ArrayList<>();
            while (rs.next()) {
                String CovidGevaldatum = rs.getString("Datum");
                Datum datum = new Datum(CovidGevaldatum);
                datumLijst.add(datum);
            }

        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return datumLijst;
    }

    public List<Datum> geefDagenInMaand(String maand, String landnaam) throws SQLException {
        Statement stmt = null;
        List<Datum> datumLijst = null;
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT Datum FROM Covid19BevestigdeGevallen WHERE (SELECT LandCode FROM Landen WHERE LandNaam = \"" + landnaam + "\") = LandCode && Monthname(Datum) = \"" + maand + "\";");
            datumLijst = new ArrayList<>();
            while (rs.next()) {
                String CovidGevaldatum = rs.getString("Datum");
                Datum datum = new Datum(CovidGevaldatum);
                datumLijst.add(datum);
            }
        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return datumLijst;
    }

    public List<Cijfers> geefAantallenInMaand(String maand, String landnaam) throws SQLException {
        Statement stmt = null;
        List<Cijfers> cijferlijst = null;
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT Aantal FROM Covid19BevestigdeGevallen covid JOIN Landen land ON covid.LandCode = land.LandCode WHERE land.LandNaam = \"" + landnaam + "\" && monthname(Datum) = \"" + maand + "\";");
            cijferlijst = new ArrayList<>();
            while (rs.next()) {
                int aantal = rs.getInt("Aantal");
                Cijfers cijfers = new Cijfers(aantal);
                cijferlijst.add(cijfers);
            }
        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return cijferlijst;
    }

    public int geefAantalGevallenOpDatum(String datum, String landcode) throws SQLException {
        Statement stmt = null;
        int aantal = 0;
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT Aantal FROM Covid19BevestigdeGevallen WHERE Datum = \'" + datum + "\' && LandCode = \'" + landcode + "\';");
            while (rs.next()) {
                aantal = rs.getInt("Aantal");
            }

        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return aantal;
    }

    public String geefDatumEersteGeval(String landcode) throws SQLException {
        Statement stmt = null;
        String datumEersteGeval = "";
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT Datum FROM `Covid19BevestigdeGevallen` WHERE Aantal >= 1 && LandCode = \'" + landcode + "\' LIMIT 1;");
            while (rs.next()) {
                datumEersteGeval = rs.getString("Datum");
            }

        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return datumEersteGeval;
    }

    public int geefAantalGevallenWereldwijd() throws SQLException {
        Statement stmt = null;
        int AantalGevallen = 0;
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT SUM(Aantal) AS Totaal FROM Covid19BevestigdeGevallen WHERE LandCode = 'OWID_WRL';");
            while (rs.next()) {
                AantalGevallen = rs.getInt("Totaal");
            }

        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return AantalGevallen;
    }

    public int geefAantalLanden() throws SQLException {
        Statement stmt = null;
        int AantalLanden = 0;
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS AantalLanden FROM Landen;");
            while (rs.next()) {
                AantalLanden = rs.getInt("AantalLanden");
            }

        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return AantalLanden;
    }

    public String geefLandNaam(String landcode) throws SQLException {
        Statement stmt = null;
        String landNaam = "";
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT LandNaam FROM Landen WHERE LandCode = \"" + landcode + "\";");
            while (rs.next()) {
                landNaam = rs.getString("LandNaam");
            }

        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return landNaam;
    }

    public int geefMaximumAantal(String landcode) throws SQLException {
        Statement stmt = null;
        int maxAantal = 0;
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT MAX(Aantal) AS maxAantal FROM Covid19BevestigdeGevallen WHERE LandCode = \"" + landcode + "\";");
            while (rs.next()) {
                maxAantal = rs.getInt("maxAantal");
            }

        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return maxAantal;
    }

    public String geefDatumMaximumAantal(String landcode) throws SQLException {
        Statement stmt = null;
        String datum = "";
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT Datum, (SELECT MAX(Aantal) FROM Covid19BevestigdeGevallen WHERE LandCode = \"" + landcode + "\")"
                    + "AS MaxAantal FROM Covid19BevestigdeGevallen WHERE LandCode= \"" + landcode + "\" limit 1;");
            while (rs.next()) {
                datum = rs.getString("Datum");
            }

        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return datum;
    }

    public int geefTotaalAantalInLand(String landcode) throws SQLException {
        Statement stmt = null;
        int totaalAantal = 0;
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT SUM(Aantal) AS totaalAantal FROM Covid19BevestigdeGevallen WHERE LandCode = \"" + landcode + "\";");
            while (rs.next()) {
                totaalAantal = rs.getInt("totaalAantal");
            }

        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return totaalAantal;
    }

    public int geefAantalRecordsBeschikbaar() throws SQLException {
        Statement stmt = null;
        int totaalAantal = 0;
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS Totaal FROM Covid19BevestigdeGevallen;");
            while (rs.next()) {
                totaalAantal = rs.getInt("Totaal");
            }

        } catch (SQLException ex) {
            throw new SQLException("Kon de data niet uit de databank ophalen. \n" + ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return totaalAantal;
    }
}
