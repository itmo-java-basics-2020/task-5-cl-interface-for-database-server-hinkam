package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.console.DatabaseCommands;
import ru.andrey.kvstorage.exception.DatabaseException;
import java.util.Arrays;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {

    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null) {
            return DatabaseCommandResult.error("Wrong command name.");
        }

        String[] args = commandText.split(" ");
        if (args.length < 1) {
            return DatabaseCommandResult.error("Wrong command");
        }
        try {
            return DatabaseCommands.valueOf(args[0]).getCommand(this.env, Arrays.copyOfRange(args, 1, args.length)).execute();
        } catch (DatabaseException | IllegalArgumentException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}
