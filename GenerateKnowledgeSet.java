import java.sql.*;

public class GenerateKnowledgeSet {

    public static void main(String[] args) {

        Connection con;
        String tablename1 = "Knowledge_Sulphide_Bond";
        String tablename2 = "Sulphide_bond";

        try {

            con = DriverManager.getConnection("jdbc:ucanaccess://D:\\Graduation Materials\\Research\\SulphideDb.accdb");
            String query = "SELECT FAMILYNAME, MIN(PARALLEL), MAX(PARALLEL), MIN(ALTERNATE), MAX(ALTERNATE) , MIN(QUAD_CORE), MAX(QUAD_CORE) FROM " + tablename2 + " GROUP BY FAMILYNAME";
            Statement st = con.createStatement();
            ResultSet knowledge = st.executeQuery(query);

            while (knowledge.next()) {
                //System.out.print("\t" + knowledge.getString(1));
                String variable = "insert into " + tablename1 + " values ( '" + knowledge.getString(1) + "' , " + knowledge.getString(2) + " , " + knowledge.getString(3) + "," + knowledge.getString(4) + "," + knowledge.getString(5) + "," + knowledge.getString(6) + "," + knowledge.getString(7) + " )";
                PreparedStatement ps = con.prepareStatement(variable);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}