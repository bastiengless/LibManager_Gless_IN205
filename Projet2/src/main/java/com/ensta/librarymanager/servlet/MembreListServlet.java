package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.services.MembreServiceImpl;

public class MembreListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String action = req.getServletPath();
		
		if (action.equals("/membre_list")) {
			final MembreServiceImpl mInstance = MembreServiceImpl.getInstance();
			try {
				req.setAttribute("membreList", mInstance.getList());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/WEB-INF/View/membre_list.jsp").forward(req, res);
			
		}
	}
}
