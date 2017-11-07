package by.iba.electronhandbook.factory;

import by.iba.electronhandbook.command.Command;

public interface CommandFactory {
    Command getCommand(String commandName);
}
