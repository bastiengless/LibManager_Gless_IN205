package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class MembreDaoImpl implements MembreDao {
	
	private static MembreDaoImpl instance;
	
	private MembreDaoImpl() {}
	
	public static MembreDaoImpl getInstance() {
		if (instance == null) {
			instance = new MembreDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Membre> getList() throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(
				  "SELECT id, nom, prenom, adresse, email, telephone, abonnement\r\n"
				+ "FROM membre\r\n"
				+ "ORDER BY nom, prenom;");
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Membre> l = new ArrayList<Membre>();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String adresse = rs.getString("adresse");
			String email = rs.getString("email");
			String telephone = rs.getString("telephone");
			Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
			l.add(new Membre(id, nom, prenom, adresse, email, telephone, abonnement));
		}
		conn.close();
		return l;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public Membre getById(int id) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(
				"SELECT id, nom, prenom, adresse, email, telephone, abonnement\r\n"
				+ "FROM membre\r\n"
				+ "WHERE id = ?;");
		
		pstmt.setInt(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		
		if (rs.next()) {
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String adresse = rs.getString("adresse");
			String email = rs.getString("email");
			String telephone = rs.getString("telephone");
			Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
			return new Membre(id, nom, prenom, adresse, email, telephone, abonnement);
		}
		else { return new Membre(); }
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public int create(Membre membre) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(
				"INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?);");
		
		pstmt.setString(1,  membre.getNom());
		pstmt.setString(2,  membre.getPrenom());
		pstmt.setString(3,  membre.getAdresse());
		pstmt.setString(4,  membre.getEmail());
		pstmt.setString(5,  membre.getTelephone());
		pstmt.setString(6,  membre.getAbonnement().toString());
		
		conn.close();		
		return pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void update(Membre membre) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(
				  "UPDATE membre\r\n"
				+ "SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ?\r\n"
				+ "WHERE id = ?;");
		
		pstmt.setString(1,  membre.getNom());
		pstmt.setString(2,  membre.getPrenom());
		pstmt.setString(3,  membre.getAdresse());
		pstmt.setString(4,  membre.getEmail());
		pstmt.setString(5,  membre.getTelephone());
		pstmt.setString(6,  membre.getAbonnement().toString());
		pstmt.setInt(7, membre.getId());
		
		conn.close();
		pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		
	}

	@Override
	public void delete(int id) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("DELETE FROM membre WHERE id = ?;");
		
		pstmt.setInt(1,  id);
		
		conn.close();
		pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		
	}

	@Override
	public int count() throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(id) AS count FROM membre;");
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			int c = rs.getInt("COUNT(id)");
			conn.close();
			return c;
		}
		else { return 0; }
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

}
