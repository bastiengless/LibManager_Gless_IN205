package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class EmpruntDaoImpl implements EmpruntDao {
	
	private static EmpruntDaoImpl instance;
	
	private EmpruntDaoImpl() {}
	
	public static EmpruntDaoImpl getInstance() {
		if (instance == null) {
			instance = new EmpruntDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Emprunt> getList() throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(
				  "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\r\n"
				+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\r\n"
				+ "dateRetour\r\n"
				+ "FROM emprunt AS e\r\n"
				+ "INNER JOIN membre ON membre.id = e.idMembre\r\n"
				+ "INNER JOIN livre ON livre.id = e.idLivre\r\n"
				+ "ORDER BY dateRetour DESC;");
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Emprunt> l = new ArrayList<Emprunt>();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			int idMembre = rs.getInt("idMembre");
			int idLivre = rs.getInt("idLivre");
			LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
			LocalDate dateRetour = rs.getDate("dateRetour").toLocalDate();
			l.add(new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour));
		}
		conn.close();
		return l;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<Emprunt> getListCurrent() throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(
				  "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\r\n"
				  + "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\r\n"
				  + "dateRetour\r\n"
				  + "FROM emprunt AS e\r\n"
				  + "INNER JOIN membre ON membre.id = e.idMembre\r\n"
				  + "INNER JOIN livre ON livre.id = e.idLivre\r\n"
				  + "WHERE dateRetour IS NULL;");
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Emprunt> l = new ArrayList<Emprunt>();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			int idMembre = rs.getInt("idMembre");
			int idLivre = rs.getInt("idLivre");
			LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
			LocalDate dateRetour = rs.getDate("dateRetour").toLocalDate();
			l.add(new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour));
		}
		conn.close();
		return l;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(
				  "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\r\n"
				  + "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\r\n"
				  + "dateRetour\r\n"
				  + "FROM emprunt AS e\r\n"
				  + "INNER JOIN membre ON membre.id = e.idMembre\r\n"
				  + "INNER JOIN livre ON livre.id = e.idLivre\r\n"
				  + "WHERE dateRetour IS NULL AND membre.id = ?;");
		
		ResultSet rs = pstmt.executeQuery();
		
		pstmt.setInt(1, idMembre);
		
		List<Emprunt> l = new ArrayList<Emprunt>();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			int idLivre = rs.getInt("idLivre");
			LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
			LocalDate dateRetour = rs.getDate("dateRetour").toLocalDate();
			l.add(new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour));
		}
		conn.close();
		return l;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(
				  "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\r\n"
				  + "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\r\n"
				  + "dateRetour\r\n"
				  + "FROM emprunt AS e\r\n"
				  + "INNER JOIN membre ON membre.id = e.idMembre\r\n"
				  + "INNER JOIN livre ON livre.id = e.idLivre\r\n"
				  + "WHERE dateRetour IS NULL AND livre.id = ?;");
		
		ResultSet rs = pstmt.executeQuery();
		
		pstmt.setInt(1, idLivre);
		
		List<Emprunt> l = new ArrayList<Emprunt>();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			int idMembre = rs.getInt("idMembre");
			LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
			LocalDate dateRetour = rs.getDate("dateRetour").toLocalDate();
			l.add(new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour));
		}
		conn.close();
		return l;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public Emprunt getById(int id) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(
				"SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email,\r\n"
				+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\r\n"
				+ "dateRetour\r\n"
				+ "FROM emprunt AS e\r\n"
				+ "INNER JOIN membre ON membre.id = e.idMembre\r\n"
				+ "INNER JOIN livre ON livre.id = e.idLivre\r\n"
				+ "WHERE e.id = ?;");
		
		pstmt.setInt(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		
		if (rs.next()) {
			int idMembre = rs.getInt("idMembre");
			int idLivre = rs.getInt("idLivre");
			LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
			if (rs.getDate("dateRetour")!=null) {
				LocalDate dateRetour = rs.getDate("dateRetour").toLocalDate();
				return new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
			} else {
				return new Emprunt(id, idMembre, idLivre, dateEmprunt, null);
			}
		}
		else { return new Emprunt(); }
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(
				"INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour)\r\n"
				+ "VALUES (?, ?, ?, ?);");
		
		pstmt.setInt(1,  idMembre);
		pstmt.setInt(2,  idLivre);
		pstmt.setDate(3,  java.sql.Date.valueOf(dateEmprunt));
		pstmt.setDate(4,  null);
		
		conn.close();		
		pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		
	}

	@Override
	public void update(Emprunt emprunt) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(
				"UPDATE emprunt\r\n"
				+ "SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ?\r\n"
				+ "WHERE id = ?;");
		
		pstmt.setInt(1,  emprunt.getIdMembre());
		pstmt.setInt(2,  emprunt.getIdLivre());
		pstmt.setDate(3,  java.sql.Date.valueOf(emprunt.getDateEmprunt()));
		pstmt.setDate(4,  java.sql.Date.valueOf(emprunt.getDateRetour()));
		pstmt.setInt(5, emprunt.getId());
		
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
		
		PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(id) AS count FROM emprunt;");
		
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
