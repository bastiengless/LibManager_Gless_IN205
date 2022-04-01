package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.services.LivreServiceImpl;
import com.ensta.librarymanager.services.MembreServiceImpl;

public class LivreAddServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String action = req.getServletPath();
		if (action.equals("/livre_add")) {
			final LivreServiceImpl lInstance = LivreServiceImpl.getInstance();
			final MembreServiceImpl mInstance = MembreServiceImpl.getInstance();
			try {
				req.setAttribute("currBook", lInstance.getListDispo());
				req.setAttribute("currMember", mInstance.getListMembreEmpruntPossible());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/WEB-INF/View/livre_add.jsp").forward(req, res);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final LivreServiceImpl lInstance = LivreServiceImpl.getInstance();
		int id = -1;
		if (req.getParameter("titre") == null || req.getParameter("titre")=="") {
			throw new ServletException("Title is empty");
		}
		try {
			
			id = lInstance.create(req.getParameter("titre"), req.getParameter("auteur"), req.getParameter("isbn"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		final MembreServiceImpl mInstance = MembreServiceImpl.getInstance();
		try {
			req.setAttribute("currBook", lInstance.getListDispo());
			req.setAttribute("currMember", mInstance.getListMembreEmpruntPossible());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		res.sendRedirect("/LibraryManager/livre_details?id=" + id);
	}
}
