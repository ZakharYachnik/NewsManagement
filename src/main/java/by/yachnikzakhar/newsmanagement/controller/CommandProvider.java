package by.yachnikzakhar.newsmanagement.controller;

import by.yachnikzakhar.newsmanagement.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private static CommandProvider provider;

    private final Map<CommandName, Command> commands = new HashMap<>();

    private CommandProvider() {
        commands.put(CommandName.SIGN_IN, new SignInCommand());
        commands.put(CommandName.GO_TO_REGISTRATION, new GoToRegistrationCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        commands.put(CommandName.GO_TO_SIGN_IN, new GoToSignInCommand());
        commands.put(CommandName.GO_TO_ADD_NEWS, new GoToAddNewsCommand());
        commands.put(CommandName.SHOW_NEWS, new ShowNewsCommand());
        commands.put(CommandName.CHANGE_NEWS, new ChangeNewsCommand());
        commands.put(CommandName.DELETE_NEWS, new DeleteNewsCommand());
        commands.put(CommandName.LOGOUT, new LogoutCommand());
        commands.put(CommandName.GO_TO_ADMINISTRATION, new GoToAdministration());
        commands.put(CommandName.GO_TO_MAIN_PAGE_AFTER_SIGN_IN, new GoToMainPageAfterSignIn());
    }

    public static CommandProvider getInstance() {
        if (provider == null) {
            provider = new CommandProvider();
        }
        return provider;
    }

    public Command getCommand(String name) {
        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        return commands.get(commandName);
    }
}
