package com.epam.finalweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.PropertyConfigurator;

import com.epam.finalweb.command.AvailableCommand;
import com.epam.finalweb.command.Command;
import com.epam.finalweb.command.CommandProvider;
import com.epam.finalweb.exception.DaoException;
import com.epam.finalweb.util.JDBCConnection;

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

		PropertyConfigurator.configure("src//main//resources//log4jconfig.properties");
		try {
			JDBCConnection.init();
		} catch (DaoException e) {

		}
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AvailableCommand commandName;
		
		commandName=AvailableCommand.valueOf((request.getParameter("commandName")).toUpperCase());
		
		Command command = CommandProvider.getInstance().getCommand(commandName);

		command.execute(request,response);
		

	}

}
