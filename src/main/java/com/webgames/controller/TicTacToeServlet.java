package com.webgames.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webgames.model.TicTacToe;

/**
 * Servlet implementation class TicTacToeServlet
 */
@WebServlet("/tictactoe")
public class TicTacToeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicTacToeServlet() {
        super();
    }

    /**
     * Handles the user responses from the TicTacToe.jsp page.
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. get data
    	String locString = request.getParameter("loc");
		String newGame = request.getParameter("newGame");
    	// 2. validate
		if(newGame != null) {
			request.getSession().setAttribute("game", new TicTacToe());
		}else if(locString != null && locString.matches("[0-8]")){
	    	//  3. do it
			int loc = Integer.parseInt(locString);
			((TicTacToe) request.getSession(true).getAttribute("game")).placeMark(loc);
		}
    	// 4. store info
    	// 5. forward control
    	
    	request.getRequestDispatcher("/html/ticTacToe.jsp").forward(request, response);
		
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
		doGet(request, response);
	}

}
