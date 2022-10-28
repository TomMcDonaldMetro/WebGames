package com.webgames.controller;

import java.util.logging.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.webgames.model.TicTacToe;


@WebListener
public class SessionListener implements HttpSessionListener{

	public SessionListener() {
	}

	// TODO not set up correctly.
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute("game", new TicTacToe());
		Logger.getAnonymousLogger().info("Session created");
	}

	public void sessionDestroyed(HttpSessionEvent se) {

	}
}
