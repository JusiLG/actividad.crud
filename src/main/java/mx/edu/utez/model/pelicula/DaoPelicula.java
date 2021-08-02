package mx.edu.utez.model.pelicula;

import mx.edu.utez.service.ConnectionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPelicula {

    Connection con;
    CallableStatement cstm;
    ResultSet rs;

    /*Consulta de usuarios*/

    public List<BeanPelicula> findAll(){
        List<BeanPelicula> listPelicula = new ArrayList<>();
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("call  sp_findAll");
            rs = cstm.executeQuery();

            Connection con =ConnectionMySQL.getConnection();
            Statement cstm = con.createStatement();

            while (rs.next()){
                BeanPelicula pelicula = new BeanPelicula();

                pelicula.setId(rs.getInt("id"));
                pelicula.setNombre(rs.getString("nombre"));
                pelicula.setDescripcion(rs.getString("descripcion"));
                pelicula.setFecha_estreno(rs.getString("fecha_estreno"));
                pelicula.setRecaudacion(rs.getInt("recaudacion"));
                pelicula.setEstado(rs.getInt("estado"));


                listPelicula.add(pelicula);
            }
        }catch (SQLException e){
            System.out.println("Ha occurido un error: " +e.getMessage());
        }finally {
            ConnectionMySQL.closeConnection(con,cstm,rs);
        }
        return listPelicula;
    }

    public BeanPelicula findById(int id){
        BeanPelicula pelicula = null;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM info_pelis WHERE id = ?");
            cstm.setLong(1, id);
            rs = cstm.executeQuery();

            if (rs.next()){
                pelicula = new BeanPelicula();

                pelicula.setId(rs.getInt("idPelicula"));
                pelicula.setNombre(rs.getString("nombre"));
                pelicula.setDescripcion(rs.getString("descripcion"));
                pelicula.setFecha_estreno(rs.getString("fecha_estreno"));
                pelicula.setRecaudacion(rs.getInt("recaudacion"));
                pelicula.setEstado(rs.getInt("estado"));

            }
        }catch (SQLException e){
            System.out.println("Ha occurido un error: " +e.getMessage());
        }finally {
            ConnectionMySQL.closeConnection(con,cstm,rs);
        }
        return pelicula;
    }

    public boolean create(BeanPelicula peli){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_create(?,?,?,?,?,?)}");
            cstm.setInt(1, peli.getId());
            cstm.setString(2,peli.getNombre());
            cstm.setString(3,peli.getDescripcion());
            cstm.setString(4,peli.getFecha_estreno());
            cstm.setInt(5,peli.getRecaudacion());
            cstm.setInt(6,peli.getEstado());

            cstm.execute();
            flag = true;
        }catch(SQLException e){
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnection(con,cstm);
        }
        return flag;

    }

    public boolean update(BeanPelicula peli) {
        boolean flag = false;
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_update(?,?,?,?,?,?)}");
            cstm.setInt(1,peli.getId());
            cstm.setString(2,peli.getNombre());
            cstm.setString(3,peli.getDescripcion());
            cstm.setString(4,peli.getFecha_estreno());
            cstm.setInt(5,peli.getRecaudacion());
            cstm.setInt(6,peli.getEstado());


            cstm.execute();
            flag = true;
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnection(con, cstm);
        }
        return flag;

    }

    public boolean delete(int id){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_delete2(?)}");
            cstm.setLong(1, id);

            flag = cstm.execute();
        }catch(SQLException e){
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnection(con, cstm);
        }
        return flag;
    }

    /*public static void main(String[] args) {
        BeanPelicula beanPelicula = new BeanPelicula();
        DaoPelicula daoPelicula = new DaoPelicula();
        // Listando usuarios
        List<BeanPelicula> listPelicula = new ArrayList<>();
        listPelicula = daoPelicula.findAll();
        for (int i = 0; i < listPelicula.size(); i++) {
            System.out.println(listPelicula.get(i).getNombre());
        }
    }*/

}
