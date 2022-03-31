package com.ensta.librarymanager.services;

import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;

public class EmpruntServiceImpl implements EmpruntService {
	
	private static EmpruntServiceImpl instance;
	
	private EmpruntServiceImpl() {}
	
	public static EmpruntServiceImpl getInstance() {
		if (instance == null) {
			instance = new EmpruntServiceImpl();
		}
		return instance;
	}

	@Override
	public List<Emprunt> getList() throws ServiceException {
		EmpruntDaoImpl e = EmpruntDaoImpl.getInstance();
		try {
			List<Emprunt> l = e.getList();
			return l;
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service EmpruntServiceImpl.getList");
		}
	}

	@Override
	public List<Emprunt> getListCurrent() throws ServiceException {
		EmpruntDaoImpl e = EmpruntDaoImpl.getInstance();
		try {
			List<Emprunt> l = e.getListCurrent();
			return l;
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service EmpruntServiceImpl.getListCurrent");
		}
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
		EmpruntDaoImpl e = EmpruntDaoImpl.getInstance();
		try {
			List<Emprunt> l = e.getListCurrentByMembre(idMembre);
			return l;
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service EmpruntServiceImpl.getListCurrentByMembre");
		}
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
		EmpruntDaoImpl e = EmpruntDaoImpl.getInstance();
		try {
			List<Emprunt> l = e.getListCurrentByLivre(idLivre);
			return l;
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service EmpruntServiceImpl.getListCurrentByLivre");
		}
	}

	@Override
	public Emprunt getById(int id) throws ServiceException {
		EmpruntDaoImpl e = EmpruntDaoImpl.getInstance();
		try {
			Emprunt emprunt = e.getById(id);
			return emprunt;
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service EmpruntServiceImpl.getById");
		}
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
		EmpruntDaoImpl e = EmpruntDaoImpl.getInstance();
		try {
			e.create(idMembre, idLivre, dateEmprunt);
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service EmpruntServiceImpl.create");
		}
		
	}

	@Override
	public void returnBook(int id) throws ServiceException {
		EmpruntDaoImpl e = EmpruntDaoImpl.getInstance();
		try {
			Emprunt emprunt = e.getById(id);
			emprunt.setDateRetour(LocalDate.now());
			e.update(emprunt);
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service EmpruntServiceImpl.returnBook");
		}
		
	}

	@Override
	public int count() throws ServiceException {
		EmpruntDaoImpl e = EmpruntDaoImpl.getInstance();
		try {
			int c = e.count();
			return c;
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service EmpruntServiceImpl.count");
		}
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		EmpruntDaoImpl e = EmpruntDaoImpl.getInstance();
		try {
			List<Emprunt> lEmprunt = e.getListCurrentByLivre(idLivre);
			return (lEmprunt!=null);
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service EmpruntServiceImpl.isLivreDispo");
		}
	}

	@Override
	public boolean isEmpruntPossible(Membre membre) throws ServiceException {
		EmpruntDaoImpl e = EmpruntDaoImpl.getInstance();
		try {
			List<Emprunt> lEmprunt = e.getListCurrentByMembre(membre.getId());
			int nEmprunts = lEmprunt.size();
			return (membre.getAbonnement()==Abonnement.BASIC && nEmprunts<3)
			    || (membre.getAbonnement()==Abonnement.PREMIUM && nEmprunts<6)
				|| (membre.getAbonnement()==Abonnement.VIP && nEmprunts<21);
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service EmpruntServiceImpl.isEmpruntPossible");
		}
	}
	
	
}
