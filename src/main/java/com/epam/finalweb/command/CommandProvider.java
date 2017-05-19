package com.epam.finalweb.command;

import java.util.EnumMap;

import com.epam.finalweb.command.Command;
import com.epam.finalweb.command.impl.LoginCommand;
import com.epam.finalweb.command.impl.LogoutCommand;
import com.epam.finalweb.command.impl.RegisterCommand;
import com.epam.finalweb.command.impl.SearchCommand;



public class CommandProvider {

	
	
	private EnumMap<AvailableCommand,Command> commands=
			new EnumMap<AvailableCommand,Command>(AvailableCommand.class);
	private CommandProvider(){
		
		commands.put(AvailableCommand.LOGIN,new LoginCommand());
		commands.put(AvailableCommand.LOGOUT,new LogoutCommand());
		commands.put(AvailableCommand.SEARCH,new SearchCommand());
		commands.put(AvailableCommand.REGISTER,new RegisterCommand());
	}
	
	private static class CommandProviderHolder{
		
		private static final CommandProvider INSTANCE=new CommandProvider(); 
		
	}
	
	public static CommandProvider getInstance(){
		
		return CommandProviderHolder.INSTANCE;
		
	}
	
	
	
	public Command getCommand(AvailableCommand command){
		return commands.get(command);
		
	}
	
	
}
