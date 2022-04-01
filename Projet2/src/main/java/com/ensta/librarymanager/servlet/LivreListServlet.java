package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.services.LivreServiceImpl;

public class LivreListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String action = req.getServletPath();
		
		if (action.equals("/livre_list")) {
			final LivreServiceImpl lInstance = LivreServiceImpl.getInstance();
			try {
				req.setAttribute("bookList", lInstance.getList());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/WEB-INF/View/livre_list.jsp").forward(req, res);
			
		}
	}
}
