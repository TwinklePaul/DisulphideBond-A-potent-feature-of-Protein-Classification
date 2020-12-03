import java.sql.*;
import java.text.DecimalFormat;

public class SulphideBasedClassifier {

    public static void main(String[] args) {

        Connection con;
        String tablename1 = "sequence_data";
        String tablename2 = "Knowledge_Sulphide_Bond";
        String tablename3 = "ClassifierSet";

        DecimalFormat df = new DecimalFormat("#.##");
        SulphideFeatureExtraction sf = new SulphideFeatureExtraction();

        int count = 0;
        String familyset = "";

        try {
            con = DriverManager.getConnection("jdbc:ucanaccess://D:\\Graduation Materials\\Research\\SulphideDb.accdb");

            String query = "select * from " + tablename1;
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery(query);

            while (rs.next()) {

                String ProteinSequence = rs.getString(3);
                sf.getTypes(ProteinSequence);

                String predictionQuery = "select  family_name from  " + tablename2 + " where " + sf.avgp + ">= min_parallel and " + sf.avgp + "<= max_parallel and " + sf.avga + ">= min_alternate and " + sf.avga + "<= max_alternate and " + sf.avgq + ">= min_quadcore and " + sf.avgq + "<= max_quadcore ";
                Statement st = con.createStatement();
                ResultSet predict = st.executeQuery(predictionQuery);

                count = 0;
                familyset = "";

                while (predict.next()) {
                    count++;
                    familyset = familyset + predict.getString(1) + ", ";
                }

                //System.out.println(count+"\t"+familyset);
                String generateData = "insert into " + tablename3 + " values ( '" + rs.getString(1) + "' , '" + rs.getString(2) + "' , " + count + ", '" + familyset + "' )";
                PreparedStatement ps = con.prepareStatement(generateData);
                ps.executeUpdate();
            }

        } catch (SQLException E) {
            E.printStackTrace();
        }
    }
}
