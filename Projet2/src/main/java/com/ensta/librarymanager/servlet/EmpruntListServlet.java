package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.services.EmpruntServiceImpl;

public class EmpruntListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final EmpruntServiceImpl eInstance = EmpruntServiceImpl.getInstance();
		String action = req.getServletPath();
		String show = "";
		if (req.getParameter("show") != null) {
			show = req.getParameter("show");
		}
		if (action.equals("/emprunt_list")) {
			try {
				if (show.equals("all"))
					req.setAttribute("allList", eInstance.getList());
				else
					req.setAttribute("allList", eInstance.getListCurrent());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		req.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp").forward(req, res);
		
	}
}
