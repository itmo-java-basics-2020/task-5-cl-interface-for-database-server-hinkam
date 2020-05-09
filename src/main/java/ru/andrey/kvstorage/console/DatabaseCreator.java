package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class DatabaseCreator implements DatabaseCommand {

    private ExecutionEnvironment environment;
    private String databaseName;

    DatabaseCreator(ExecutionEnvironment environment, String databaseName) {
        this.environment = environment;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        this.environment.addDatabase(null);
        return DatabaseCommandResult.success(String.format("Database \"%s\" is created.", databaseName));
    }

}