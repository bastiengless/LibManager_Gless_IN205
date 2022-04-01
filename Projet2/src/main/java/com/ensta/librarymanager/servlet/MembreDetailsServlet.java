package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.services.EmpruntServiceImpl;
import com.ensta.librarymanager.services.MembreServiceImpl;

public class MembreDetailsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String action = req.getServletPath();
		if (action.equals("/membre_details")) {
			final MembreServiceImpl mInstance = MembreServiceImpl.getInstance();
			final EmpruntServiceImpl eInstance = EmpruntServiceImpl.getInstance();
			try {
				req.setAttribute("membre", mInstance.getById(Integer.parseInt(req.getParameter("id"))));
				req.setAttribute("emprunts", eInstance.getListCurrentByMembre(Integer.parseInt(req.getParameter("id"))));
			}
			catch (Exception e) {
				new ServletException("can not find the book");
			}
			req.getRequestDispatcher("/WEB-INF/View/membre_details.jsp").forward(req, res);
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final MembreServiceImpl mInstance = MembreServiceImpl.getInstance();
		final EmpruntServiceImpl eInstance = EmpruntServiceImpl.getInstance();
		if (req.getParameter("nom").equals("") || req.getParameter("prenom").equals("")) {
			throw new ServletException("Name is empty");
		}
		try {
			Membre umembre = mInstance.getById(Integer.parseInt(req.getParameter("id")));
			umembre.setNom(req.getParameter("nom"));
			umembre.setPrenom(req.getParameter("prenom"));
			umembre.setAdresse(req.getParameter("adresse"));
			umembre.setEmail(req.getParameter("email"));
			umembre.setTelephone(req.getParameter("telephone"));
			umembre.setAbonnement(Abonnement.valueOf(req.getParameter("abonnement")));

			mInstance.update(umembre);
			req.setAttribute("membre", mInstance.getById(umembre.getId()));
			req.setAttribute("emprunts", eInstance.getListCurrentByMembre(umembre.getId()));
			res.sendRedirect("/LibraryManager/membre_details?id=" + umembre.getId());
		} catch (NumberFormatException | ServiceException e) {
			e.printStackTrace();
		}
		
	}
}
