package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.services.EmpruntServiceImpl;
import com.ensta.librarymanager.services.LivreServiceImpl;
import com.ensta.librarymanager.services.MembreServiceImpl;


public class DashboardServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String action = req.getServletPath();
		if (action.equals("/dashboard")) {
			final MembreServiceImpl mInstance = MembreServiceImpl.getInstance();
			final LivreServiceImpl lInstance = LivreServiceImpl.getInstance();
			final EmpruntServiceImpl eInstance = EmpruntServiceImpl.getInstance();
			
			try {
				req.setAttribute("nMembres", mInstance.count());
				req.setAttribute("nLivres", lInstance.count());
				req.setAttribute("nEmprunts", eInstance.count());
				req.setAttribute("currBorrow", eInstance.getListCurrent());
			}
			catch (final Exception e) {
				e.printStackTrace();
			}
			final RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
			dispatcher.forward(req, res);
		}
	}
}
