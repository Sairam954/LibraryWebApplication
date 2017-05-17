package com.epam.finalweb.command;

import java.util.EnumMap;

import com.epam.finalweb.command.Command;
import com.epam.finalweb.command.impl.LoginCommand;
import com.epam.finalweb.command.impl.LogoutCommand;
import com.epam.finalweb.command.impl.SearchCommand;


public class CommandProvider {

	private static CommandProvider instance;
	
	private EnumMap<AvailableCommand,Command> commands=
			new EnumMap<AvailableCommand,Command>(AvailableCommand.class);
	public CommandProvider(){
		
		commands.put(AvailableCommand.LOGIN,new LoginCommand());
		commands.put(AvailableCommand.LOGOUT,new LogoutCommand());
		commands.put(AvailableCommand.SEARCH,new SearchCommand());
	}
	public static CommandProvider getInstance(){
		
		 if(instance == null){
		        synchronized (CommandProvider.class) {
		            if(instance == null){
		                instance = new CommandProvider();
		            }
		        }
		    }
		    return instance;
		
	}
	
	
	
	public Command getCommand(AvailableCommand command){
		return commands.get(command);
		
	}
	
	
}
