package com.ensta.librarymanager.services;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;

public class LivreServiceImpl implements LivreService {

	private static LivreServiceImpl instance;
	
	private LivreServiceImpl() {}
	
	public static LivreServiceImpl getInstance() {
		if (instance == null) {
			instance = new LivreServiceImpl();
		}
		return instance;
	}
	
	@Override
	public List<Livre> getList() throws ServiceException {
		LivreDaoImpl l = LivreDaoImpl.getInstance();
		try {
			List<Livre> list = l.getList();
			return list;
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service LivreServiceImpl.getList");
		}
	}

	@Override
	public List<Livre> getListDispo() throws ServiceException {
		LivreDaoImpl l = LivreDaoImpl.getInstance();
		EmpruntServiceImpl e = EmpruntServiceImpl.getInstance();
		try {
			List<Livre> list = l.getList();
			List<Livre> dispos = new ArrayList<Livre>();
			for (int i=0; i<list.size(); i++) {
				Livre livre = list.get(i);
				if (e.isLivreDispo(livre.getId())) dispos.add(livre);
			}
			return dispos;
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service LivreServiceImpl.getListDispo");
		}
	}

	@Override
	public Livre getById(int id) throws ServiceException {
		LivreDaoImpl l = LivreDaoImpl.getInstance();
		try {
			Livre livre = l.getById(id);
			return livre;
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service LivreServiceImpl.getById");
		}
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws ServiceException {
		LivreDaoImpl l = LivreDaoImpl.getInstance();
		try {
			if (titre==null || titre=="") throw new ServiceException("Erreur : titre vide interdit");
			return l.create(titre, auteur, isbn);
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service LivreServiceImpl.create");
		}
	}

	@Override
	public void update(Livre livre) throws ServiceException {
		LivreDaoImpl l = LivreDaoImpl.getInstance();
		try {
			if (livre.getTitre()==null || livre.getTitre()=="") throw new ServiceException("Erreur : titre vide interdit");
			l.update(livre);
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service LivreServiceImpl.update");
		}
		
	}

	@Override
	public void delete(int id) throws ServiceException {
		LivreDaoImpl l = LivreDaoImpl.getInstance();
		try {
			l.delete(id);
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service LivreServiceImpl.delete");
		}
		
	}

	@Override
	public int count() throws ServiceException {
		LivreDaoImpl l = LivreDaoImpl.getInstance();
		try {
			int c = l.count();
			return c;
			
		} catch (DaoException e1) {
			e1.printStackTrace();
			throw new ServiceException("Erreur au niveau du service LivreServiceImpl.count");
		}
	}

}
