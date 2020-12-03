import java.sql.*;
import java.text.DecimalFormat;

public class GenerateTestSet {

    public static void main(String[] args) {

        Connection con;
        String tablename1 = "Sequence_Data";
        String tablename2 = "Sulphide_bond";

        DecimalFormat df = new DecimalFormat("#.##");
        SulphideFeatureExtraction sf = new SulphideFeatureExtraction();

        String family;

        try {

            con = DriverManager.getConnection("jdbc:ucanaccess://D:\\Graduation Materials\\Research\\SulphideDb.accdb");
            String table = "select * from " + tablename1;
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery(table);

            while (rs.next()) {

                String ProteinSequence = rs.getString(3);
                family = rs.getString(2);

                sf.getTypes(ProteinSequence);

                if (sf.avgp != 0 && sf.avga != 0 && sf.avgq != 0) {

                    //System.out.println(sf.avga + "\t" + sf.avgp + "\t" + sf.avgq);
                    String variable = "insert into " + tablename2 + " values ( '" + family + "' , " + df.format(sf.avgp) + "," + df.format(sf.avga) + " , " + df.format(sf.avgq) + " )";

                    PreparedStatement ps = con.prepareStatement(variable);
                    ps.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
