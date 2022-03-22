package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class LivreDaoImpl implements LivreDao {
	
	private static LivreDaoImpl instance;
	
	private LivreDaoImpl() {}
	
	public static LivreDaoImpl getInstance() {
		if (instance == null) {
			instance = new LivreDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Livre> getList() throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("SELECT id, titre, auteur, isbn FROM livre;");
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Livre> l = new ArrayList<Livre>();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String titre = rs.getString("titre");
			String auteur = rs.getString("auteur");
			String isbn = rs.getString("isbn");
			l.add(new Livre(id, titre, auteur, isbn));
		}
		conn.close();
		return l;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public Livre getById(int id) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("SELECT id, titre, auteur, isbn FROM livre WHERE id = ?;");
		
		pstmt.setInt(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		
		if (rs.next()) {
			String titre = rs.getString("titre");
			String auteur = rs.getString("auteur");
			String isbn = rs.getString("isbn");
			conn.close();
			return new Livre(id, titre, auteur, isbn);
		}
		else { return new Livre(); }
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);");
		
		pstmt.setString(1,  titre);
		pstmt.setString(2,  auteur);
		pstmt.setString(3,  isbn);
		
		conn.close();		
		return pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		
	}

	@Override
	public void update(Livre livre) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;");
		
		pstmt.setString(1, livre.getTitre());
		pstmt.setString(2, livre.getAuteur());
		pstmt.setString(3, livre.getIsbn());
		pstmt.setInt(4,  livre.getId());
		
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
		
		PreparedStatement pstmt = conn.prepareStatement("DELETE FROM livre WHERE id = ?;");
		
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
		
		PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(id) AS count FROM livre;");
		
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
