package mx.edu.utez.service;

import java.sql.*;

public class ConnectionMySQL {

    public static Connection getConnection()throws SQLException {
        String host = "127.0.0.1";
        String port = "3306";
        String database = "info_pelis";
        String useSSL = "false";
        String timezone = "UTC";
        String user = "root";
        String password = "";
        String url = String.format("jdbc:mysql://%s:%s/%s?userSSL=%s&serverTimezone=%s",host, port, database,useSSL,timezone);
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection( url,user,password);
    }
    public static void closeConnection(Connection con, CallableStatement cstm, ResultSet rs ){
        try{
            if(con != null){
                con.close();
            }
            if (cstm != null){
                cstm.close();
            }

            if (rs != null){
                rs.close();
            }
        }catch (SQLException e ){ }
    }

    public static void closeConnection(Connection con, CallableStatement cstm ){
        try{
            if(con != null){
                con.close();
            }
            if (cstm != null){
                cstm.close();
            }


        }catch (SQLException e ){ }
    }

}
