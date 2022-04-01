package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.services.EmpruntServiceImpl;
import com.ensta.librarymanager.services.LivreServiceImpl;
import com.ensta.librarymanager.services.MembreServiceImpl;

public class EmpruntAddServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String action = req.getServletPath();
		if (action.equals("/emprunt_add")) {
			final LivreServiceImpl lInstance = LivreServiceImpl.getInstance();
			final MembreServiceImpl mInstance = MembreServiceImpl.getInstance();
			try {
				req.setAttribute("avBook", lInstance.getListDispo());
				req.setAttribute("avMember", mInstance.getListMembreEmpruntPossible());
			}
			catch (Exception e) {
				System.out.println("Errror in EmpruntAddServlet --------------");
				e.printStackTrace();
			}
			req.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp").forward(req, res);
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final EmpruntServiceImpl eInstance = EmpruntServiceImpl.getInstance();
		
		final LivreServiceImpl lInstance = LivreServiceImpl.getInstance();
		final MembreServiceImpl mInstance = MembreServiceImpl.getInstance();
		try {
			eInstance.create(Integer.parseInt(req.getParameter("idMembre")), Integer.parseInt(req.getParameter("idLivre")), LocalDate.now());
			System.out.println(lInstance.getById(Integer.parseInt(req.getParameter("idLivre"))) + " : " + mInstance.getById(Integer.parseInt(req.getParameter("idMembre"))));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			req.setAttribute("avBook", lInstance.getListDispo());
			req.setAttribute("avMember", mInstance.getListMembreEmpruntPossible());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		res.sendRedirect("/LibraryManager/emprunt_list");
	}
}
