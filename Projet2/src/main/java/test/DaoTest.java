package test;

import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;

public class DaoTest {
	public static void main(String[] args) {
		EmpruntDaoImpl e = EmpruntDaoImpl.getInstance();
		LivreDaoImpl l = LivreDaoImpl.getInstance();
		try {
			Emprunt e1 = e.getById(5);
			System.out.println(e1.toString());
			Livre l1 = l.getById(2);
			System.out.println(l1.toString());
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
