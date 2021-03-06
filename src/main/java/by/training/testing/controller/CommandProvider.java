package by.training.testing.controller;

import by.training.testing.controller.command.Command;
import by.training.testing.controller.command.impl.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;

final class CommandProvider {

    private static final Logger LOGGER = LogManager.getLogger(CommandProvider.class);

    private final HashMap<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.GO_TO_SIGNUP, new GoToSignUpPageCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestCommand());
        repository.put(CommandName.SIGNUP, new SignUpCommand());
        repository.put(CommandName.GO_TO_MAIN, new GoToMainCommand());
        repository.put(CommandName.SIGNIN, new SignInCommand());
        repository.put(CommandName.SIGNOUT, new SignOutCommand());
        repository.put(CommandName.GO_TO_ADD, new GoToAddCommand());
        repository.put(CommandName.ADDENTITY, new AddEntityCommand());
        repository.put(CommandName.DELETE, new DeleteCommand());
        repository.put(CommandName.GO_TO_EDIT, new GoToEditCommand());
        repository.put(CommandName.EDITENTITY, new EditEntityCommand());
        repository.put(CommandName.GO_TO_TESTS, new GoToTestsCommand());
        repository.put(CommandName.GO_TO_QUESTIONS, new GoToQuestionsCommand());
        repository.put(CommandName.NEXT_QUESTION, new GoToNextQuestionCommand());
        repository.put(CommandName.GO_TO_DELETE_USERS, new GoToDeleteUsersCommand());
        repository.put(CommandName.GO_TO_RESULTS, new GoToResultsCommand());
        repository.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguageCommand());
        repository.put(CommandName.PAGINATION_REDIRECT, new PaginationRedirectCommand());
    }

    Command getCommand(String name) {
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }
        catch (IllegalArgumentException | NullPointerException e) {
            LOGGER.debug(name.toUpperCase() + " ", e);
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return  command;
    }
}
