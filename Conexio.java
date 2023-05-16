import java.sql.*;

public class Conexio {
    public static Connection conectar(){
        String bdd = "jdbc:mysql://10.2.59.145:3306/eleccions2019?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "perepi";
        String password = "pastanaga";
        try{
            Connection conn= DriverManager.getConnection(bdd,user,password);
            return conn;
        } catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public static void desconectar(Connection con){
        try {
            if (con != null){
                con.close();
            }
        }catch (SQLException e) {
            System.out.println(e);
        }
    }
}
