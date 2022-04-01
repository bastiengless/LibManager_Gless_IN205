package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.services.LivreServiceImpl;
import com.ensta.librarymanager.services.MembreServiceImpl;

public class MembreAddServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String action = req.getServletPath();
		if (action.equals("/membre_add")) {
			final LivreServiceImpl lInstance = LivreServiceImpl.getInstance();
			final MembreServiceImpl mInstance = MembreServiceImpl.getInstance();
			try {
				req.setAttribute("currBook", lInstance.getListDispo());
				req.setAttribute("currMember", mInstance.getListMembreEmpruntPossible());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/WEB-INF/View/membre_add.jsp").forward(req, res);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final MembreServiceImpl mInstance = MembreServiceImpl.getInstance();
		int id = -1;
		if (req.getParameter("nom").equals("") || req.getParameter("prenom").equals("")) {
			throw new ServletException("Name is empty");
		}
		try {
			
			id = mInstance.create(req.getParameter("nom"), req.getParameter("prenom"), req.getParameter("adresse"), req.getParameter("email"), req.getParameter("telephone"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		final LivreServiceImpl lInstance = LivreServiceImpl.getInstance();
		try {
			req.setAttribute("currBook", lInstance.getListDispo());
			req.setAttribute("currMember", mInstance.getListMembreEmpruntPossible());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		res.sendRedirect("/LibraryManager/membre_details?id=" + id);
	}
}
