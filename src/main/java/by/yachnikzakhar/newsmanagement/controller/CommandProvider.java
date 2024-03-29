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
        commands.put(CommandName.GO_TO_CHANGE_NEWS, new GoToChangeNewsCommand());
        commands.put(CommandName.DELETE_NEWS, new BlockNewsCommand());
        commands.put(CommandName.LOGOUT, new LogoutCommand());
        commands.put(CommandName.GO_TO_ADMINISTRATION, new GoToAdministration());
        commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(CommandName.ADD_NEWS, new AddNewsCommand());
        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
        commands.put(CommandName.BLOCK_USER, new BlockUserCommand());
        commands.put(CommandName.ADD_USER_ADMIN_ROLE, new AddUserAdminRoleCommand());
        commands.put(CommandName.REMOVE_USER_ADMIN_ROLE, new RemoveUserAdminRoleCommand());
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
