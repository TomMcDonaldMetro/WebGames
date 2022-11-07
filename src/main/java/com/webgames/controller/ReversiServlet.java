package com.webgames.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webgames.model.Reversi;


/**
 * Servlet implementation class ReversiServlet
 */
@WebServlet("/reversi")
public class ReversiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReversiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. get data
		String locStr = request.getParameter("loc");
		String newGame = request.getParameter("quit");

		// 2. validate
		if (newGame != null) {
			request.getSession().setAttribute("reversi", new Reversi());
		} else if (locStr != null && locStr.matches("([0-9]|[1-9][0-9])")) {
			int loc = Integer.parseInt(locStr);
			// 3. do it
			((Reversi) request.getSession(true).getAttribute("reversi")).placeMark(loc);
		}

		// forward control
		request.getRequestDispatcher("/html/reversi.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
