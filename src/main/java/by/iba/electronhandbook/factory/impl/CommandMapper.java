package by.iba.electronhandbook.factory.impl;

import by.iba.electronhandbook.command.Command;
import by.iba.electronhandbook.command.impl.*;
import by.iba.electronhandbook.factory.CommandFactory;

import java.util.HashMap;
import java.util.Map;

public final class CommandMapper implements CommandFactory {
    final private static Map<String, Command> commands = new HashMap();

    static {
        commands.put("GETALL", new GetAllEntitiesCommand());
        commands.put("ADD", new AddEntityCommand());
        commands.put("DELETE", new DeleteEntityCommand());
        commands.put("UPDATE", new UpdateEntityCommand());
        commands.put("GETBYID",new GetEntityByIdCommand());
        commands.put("AUTHORIZATION", new AuthorizationCommand());
        commands.put("REGISTRATION", new RegistrationCommand());
        commands.put("ADMIN", new AdminCommand());
    }

    private static class Holder{
        private final static CommandMapper INSTANCE = new CommandMapper();
    }

    public static CommandMapper getInstance(){
        return Holder.INSTANCE;
    }

    public Command getCommand(String commandName) {
        if(commands.containsKey(commandName.toUpperCase()))
            return commands.get(commandName.toUpperCase());
        return null;
    }
}
