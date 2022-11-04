package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.CONEXION;
import Modelo.USUARIO;

public class daoUSUARIO {
	CONEXION cx = null;

	public daoUSUARIO() {
		cx = new CONEXION();
	}

	public boolean insertaUsuario(USUARIO user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO USUARIO VALUES(null,?,?,?)");
			ps.setString(1, user.getUser());
			ps.setString(2, user.getNombre());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<USUARIO> fetchUsuarios() {
		ArrayList<USUARIO> lista = new ArrayList<USUARIO>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM USUARIO");
			rs = ps.executeQuery();
			while (rs.next()) {
				USUARIO u = new USUARIO();
				u.setId(rs.getInt("Id"));
				u.setUser(rs.getString("User"));
				u.setPassword(rs.getString("Password"));
				u.setNombre(rs.getString("Nombre"));
				lista.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
