import java.sql.*;
import java.util.*;

public class TimelisteDb {
    private Connection connection;

    public TimelisteDb(Connection connection) {
        this.connection = connection;
    }

    public void printTimelister() throws SQLException {
        Statement stm = connection.createStatement();
        String sprng = "select tliste.timelistenr as nr, "
                    + "tliste.status as s, tliste.beskrivelse as b "
                    + "from tliste";
        ResultSet tmlstr = stm.executeQuery(sprng);
        String s = "";

        while(tmlstr.next()){
            String tnr = tmlstr.getString("nr");
            String stts = tmlstr.getString("s");
            String bskr = tmlstr.getString("b");

            s = s + tnr + " - " + stts + " - " + bskr + "\n";
        }
        System.out.println(s);
    }

    public void printTimelistelinjer(int timelisteNr) throws SQLException {
        Statement stm = connection.createStatement();
        String sprng = "select timelisteNr as tnr, linjenr as nr, "
                    + "timeantall as ta,beskrivelse as b, "
                    + "kumulativt_timeantall as kt from tlistelinje";
        ResultSet tmlstr = stm.executeQuery(sprng);
        String s = "";

        while(tmlstr.next()){
            int timelnr = tmlstr.getInt("tnr");
            if(timelnr == timelisteNr){
                String lnr = tmlstr.getString("nr");
                String tmn = tmlstr.getString("ta");
                String bskr = tmlstr.getString("b");
                String kmt = tmlstr.getString("kt");

                s = s + lnr + " - " + tmn + " - " + bskr + " - " + kmt + "\n";
            }
        }
        System.out.println(s);
    }

    public double medianTimeantall(int timelisteNr) throws SQLException {
        Statement stm = connection.createStatement();
        String sprng = "select timelisteNr as tnr, "
                    + "timeantall as ta "
                    + "from tlistelinje order by tnr desc";
        ResultSet tmlstr = stm.executeQuery(sprng);
        ArrayList<Integer> liste = new ArrayList<Integer>();

        while(tmlstr.next()){
            int timelnr = tmlstr.getInt("tnr");
            if(timelnr == timelisteNr){
                liste.add(tmlstr.getInt("ta"));
            }
        }

        double retur = median(liste);
        return retur;
    }

    public void settInnTimelistelinje(int timelisteNr, int antallTimer, String beskrivelse) throws SQLException {
        /*Statement stm = connection.createStatement();
        String sprng1 = "select timelistenr tnr, linjenr as lnr "
        + "from tlistelinje order by tnr desc";
        ResultSet res1 = stm.executeQuery(sprng1);
        int nummer = res1.getInt("lnr");*/



        String sprng1 = "select timelistenr, linjenr as lnr "
        + "from tlistelinje where timelistenr = ? order by lnr desc limit 1";
        try{
            PreparedStatement stm1 = connection.prepareStatement(sprng1);
            stm1.setInt(1, timelisteNr);
            ResultSet res1 = stm1.executeQuery();
            res1.next();
            int nummer = res1.getInt("lnr") + 1;

            String sprng2 = "insert into tlistelinje"
                            + "(timelistenr, linjenr, timeantall, beskrivelse)"
                            + "values (?, ?, ?, ?)";
            try {
                PreparedStatement stm2 = connection.prepareStatement(sprng2);
                stm2.setInt(1, timelisteNr);
                if(nummer > 0){stm2.setInt(2, nummer);}
                stm2.setInt(3, antallTimer);
                stm2.setString(4, beskrivelse);
                ResultSet res2 = stm2.executeQuery();

            } catch(SQLException e) {
                // Noe gikk galt!

            }
        }catch(SQLException e) {
            // Noe gikk galt!
            e.printStackTrace();
        }
    }

    public void regnUtKumulativtTimeantall(int timelisteNr) throws SQLException {
        /*Statement stm = connection.createStatement();
        String sprng = "select timelisteNr as tnr, linjenr as lnr, "
                    + "timeantall as ta, "
                    + "kumulativt_timeantall as kt from tlistelinje "
                    + "where tnr = ? order by lnr asc";
        ResultSet tmlstr = stm.executeQuery(sprng);
        String s = "";
        */
        String sprng1 = "select timelistenr, linjenr as lnr, "
                    + "timeantall as ta, "
                    + "kumulativt_timeantall as kt from tlistelinje "
                    + "where timelistenr = ? order by lnr asc";
        try {
            PreparedStatement stm1 = connection.prepareStatement(sprng1);
            stm1.setInt(1, timelisteNr);
            ResultSet res1 = stm1.executeQuery();
            int tilSammen = 0;
            while(res1.next()){
                int linjenret = res1.getInt("lnr");
                tilSammen = tilSammen + res1.getInt("ta");
                String sprng2 = "update tlistelinje "
                + "set kumulativt_timeantall = ? where linjenr = ?";
                try {
                    PreparedStatement stm2 = connection.prepareStatement(sprng2);
                    stm2.setInt(1, tilSammen);
                    stm2.setInt(2, linjenret);
                    ResultSet res2 = stm2.executeQuery();
                    System.out.println("Hei");
                } catch(SQLException e) {
                    // Noe gikk galt!

                }
            }

        } catch(SQLException e) {
            // Noe gikk galt!
            
        }
    }


    /**
     * Hjelpemetode som regner ut medianverdien i en SORTERT liste. Kan slettes om du ikke har bruk for den.
     * @param list Tar inn en sortert liste av integers (f.eks. ArrayList, LinkedList osv)
     * @return medianverdien til denne listen
     */
    private double median(List<Integer> list) {
        int length = list.size();
        if (length % 2 == 0) {
            return (list.get(length / 2) + list.get(length / 2 - 1)) / 2.0;
        } else {
            return list.get((length - 1) / 2);
        }
    }
}
