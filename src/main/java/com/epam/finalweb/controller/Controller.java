package com.epam.finalweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.finalweb.command.AvailableCommand;
import com.epam.finalweb.command.Command;
import com.epam.finalweb.command.CommandProvider;

/**
 * Servlet implementation class AppController
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
	}

	public void init() {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		executeCommand(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		executeCommand(request, response);

	}

	

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		executeCommand(request, response);

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		executeCommand(request, response);

	}
	private void executeCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AvailableCommand commandName;

		commandName = AvailableCommand.valueOf((request.getParameter("commandName")).toUpperCase());
		
		Command command = CommandProvider.getInstance().getCommand(commandName);

		command.execute(request, response);

	}

}
