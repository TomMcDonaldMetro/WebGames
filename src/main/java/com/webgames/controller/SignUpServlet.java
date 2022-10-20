package com.webgames.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webgames.dao.DBConnection;
import com.webgames.model.User;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/html/signUp.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Get data
		String action = request.getParameter("action");
		String email = request.getParameter("email");
		String first = request.getParameter("fname");
		String last = request.getParameter("lname");
		String password = request.getParameter("password");
		// 2. Validate / Convert
		if(password.equals(request.getParameter("repeat_password"))) {
			// fill bean
			User user = new User();
			user.setEmail(email);
			user.setFirstName(first);
			user.setLastName(last);
			user.setPassword(password);
			// 3. "do it"
			DBConnection.getInstance().addUser(user);
			
		}
		
		// 4 store data
			// store a list of problems for validation?
		// 5. forward control
		//TODO set up XML for forwarding control
		request.getRequestDispatcher("html/home.jsp").forward(request, response);
	}
}
