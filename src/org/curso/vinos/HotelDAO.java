package org.curso.vinos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    public List<Hotel> findAll() {
        List<Hotel> list = new ArrayList<Hotel>();
        Connection conn = null;
    	String sql = "SELECT * FROM hotel ORDER BY nombre";
        try {
            conn = ConnectionHelper.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(conn);
		}
        return list;
    }

    
    public List<Hotel> findByName(String nombre) {
        List<Hotel> lista = new ArrayList<Hotel>();
        Connection conn = null;
    	String sql = "SELECT * FROM hotel " +
			"WHERE UPPER(nombre) LIKE ? " +	
			"ORDER BY nombre";
        try {
            conn = ConnectionHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nombre.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(conn);
		}
        return lista;
    }
    
    public Hotel findById(int id) {
    	String sql = "SELECT * FROM hotel WHERE id = ?";
    	Hotel hotel = null;
        Connection conn = null;
        try {
            conn = ConnectionHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	hotel = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(conn);
		}
        return hotel;
    }

    public Hotel save(Hotel hotel) 	{
		return hotel.getId() > 0 ? update(hotel) : create(hotel);
	}    
    
    public Hotel create(Hotel hotel) {
        Connection conn = null;
        PreparedStatement ps = null;
        final String qry = "INSERT INTO hotel (nombre, precio, ubicacion, pais, " 
        		+ "estrellas, descripcion, imagen) " 
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = ConnectionHelper.getConnection();            
			ps = conn.prepareStatement(qry , new String[] { "ID" });
            ps.setString(1, hotel.getNombre());
            ps.setDouble(2, hotel.getPrecio());
            ps.setString(3, hotel.getUbicacion());
            ps.setString(4, hotel.getPais());
            ps.setInt(5, hotel.getEstrellas());
            ps.setString(6, hotel.getDescripcion());
            ps.setString(7, hotel.getImagen());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            // Actualizar el id del objeto que se devuelve. Esto es importante 
            // ya que este valor debe ser devuelto al cliente.
            int id = rs.getInt(1);
            hotel.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(conn);
		}
        return hotel;
    }

    public Hotel update(Hotel hotel) {
        Connection conn = null;
        try {
            conn = ConnectionHelper.getConnection();
            final String qry = "UPDATE hotel SET nombre=?, precio=?, ubicacion=?, pais=?, " +
            			"estrellas=?, descripcion=?, imagen=? WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(qry );
            ps.setString(1, hotel.getNombre());
            ps.setDouble(2, hotel.getPrecio());
            ps.setString(3, hotel.getUbicacion());
            ps.setString(4, hotel.getPais());
            ps.setInt(5, hotel.getEstrellas());
            ps.setString(6, hotel.getDescripcion());
            ps.setString(7, hotel.getImagen());
            ps.setInt(8, hotel.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(conn);
		}
        return hotel;
    }

    public boolean remove(int id) {
        Connection conn = null;
        try {
            conn = ConnectionHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM hotel WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(conn);
		}
    }

    protected Hotel processRow(ResultSet rs) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getInt("id"));
        hotel.setNombre(rs.getString("nombre"));
        hotel.setPrecio(rs.getDouble("precio"));
        hotel.setUbicacion(rs.getString("ubicacion"));
        hotel.setPais(rs.getString("pais"));
        hotel.setEstrellas(rs.getInt("estrellas"));
        hotel.setDescripcion(rs.getString("descripcion"));
        hotel.setImagen(rs.getString("imagen"));
        return hotel;
    }
    
}
