package com.ensta.librarymanager.services;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Membre;

public class MembreServiceImpl implements MembreService {

	private static MembreServiceImpl instance;

	private MembreServiceImpl() {}

	public static MembreServiceImpl getInstance() {
		if (instance==null) {
			instance  = new MembreServiceImpl();
		}
		return instance;
	}

	@Override
	public List<Membre> getList() throws ServiceException {
		MembreDaoImpl m = MembreDaoImpl.getInstance();
		try {
			List<Membre> list = m.getList();
			return list;
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service MembreServiceImpl.getList");
		}
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
		MembreDaoImpl m = MembreDaoImpl.getInstance();
		EmpruntServiceImpl e = EmpruntServiceImpl.getInstance();
		try {
			List<Membre> list = m.getList();
			List<Membre> possibles = new ArrayList<Membre>();
			for (int i=0; i<list.size(); i++) {
				Membre membre = list.get(i);
				if (e.isEmpruntPossible(membre)) possibles.add(membre);
			}

			return possibles;
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service MembreServiceImpl.getListMembreEmpruntPossible");
		}
	}

	@Override
	public Membre getById(int id) throws ServiceException {
		MembreDaoImpl m = MembreDaoImpl.getInstance();
		try {
			return m.getById(id);
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service MembreServiceImpl.getById");
		}
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone)
			throws ServiceException {
		MembreDaoImpl m = MembreDaoImpl.getInstance();
		try {
			if (nom==null || nom=="" || prenom==null || prenom=="") throw new ServiceException("Erreur : nom ou prenom vide interdits");
			Membre membre = new Membre(0, nom.toUpperCase(), prenom, adresse, email, telephone, Abonnement.BASIC);
			return m.create(membre);
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service MembreServiceImpl.create");
		}
	}

	@Override
	public void update(Membre membre) throws ServiceException {
		MembreDaoImpl m = MembreDaoImpl.getInstance();
		try {
			if (membre.getNom()==null || membre.getNom()=="" || membre.getPrenom()==null || membre.getPrenom()=="") throw new ServiceException("Erreur : nom ou prenom vide interdits");
			membre.setNom(membre.getNom().toUpperCase());
			m.update(membre);
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service MembreServiceImpl.update");
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		MembreDaoImpl m = MembreDaoImpl.getInstance();
		try {
			m.delete(id);
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service MembreServiceImpl.delete");
		}
	}

	@Override
	public int count() throws ServiceException {
		MembreDaoImpl m = MembreDaoImpl.getInstance();
		try {
			int c = m.count();
			return c;
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service MembreServiceImpl.count");
		}
	}

}
