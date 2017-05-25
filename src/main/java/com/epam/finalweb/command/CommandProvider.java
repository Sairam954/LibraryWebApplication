package com.epam.finalweb.command;

import java.util.EnumMap;

import com.epam.finalweb.command.Command;
import com.epam.finalweb.command.impl.AddBookCommand;
import com.epam.finalweb.command.impl.AllBookAdminCommand;
import com.epam.finalweb.command.impl.AllBookCommand;
import com.epam.finalweb.command.impl.CreateBookCommand;
import com.epam.finalweb.command.impl.DeleteBookCommand;
import com.epam.finalweb.command.impl.LoginCommand;
import com.epam.finalweb.command.impl.LogoutCommand;
import com.epam.finalweb.command.impl.RegisterCommand;
import com.epam.finalweb.command.impl.RemoveBookCommand;
import com.epam.finalweb.command.impl.SearchCommand;
import com.epam.finalweb.command.impl.UpdateBookCommand;
import com.epam.finalweb.command.impl.UserBookCommand;

public class CommandProvider {

	private EnumMap<AvailableCommand, Command> commands = new EnumMap<AvailableCommand, Command>(
			AvailableCommand.class);

	private CommandProvider() {

		commands.put(AvailableCommand.LOGIN, new LoginCommand());
		commands.put(AvailableCommand.LOGOUT, new LogoutCommand());
		commands.put(AvailableCommand.SEARCH, new SearchCommand());
		commands.put(AvailableCommand.REGISTER, new RegisterCommand());
		commands.put(AvailableCommand.ALLBOOK, new AllBookCommand());
		commands.put(AvailableCommand.ADDBOOK, new AddBookCommand());
		commands.put(AvailableCommand.REMOVEBOOK, new RemoveBookCommand());
		commands.put(AvailableCommand.USERBOOK, new UserBookCommand());
		commands.put(AvailableCommand.CREATEBOOK, new CreateBookCommand());
		commands.put(AvailableCommand.ALLBOOKADMIN, new AllBookAdminCommand());
		commands.put(AvailableCommand.DELETEBOOK, new DeleteBookCommand());
		commands.put(AvailableCommand.UPDATEBOOK, new UpdateBookCommand());
		
	}

	private static class CommandProviderHolder {

		private static final CommandProvider INSTANCE = new CommandProvider();

	}

	public static CommandProvider getInstance() {

		return CommandProviderHolder.INSTANCE;

	}

	public Command getCommand(AvailableCommand command) {
		return commands.get(command);

	}

}
