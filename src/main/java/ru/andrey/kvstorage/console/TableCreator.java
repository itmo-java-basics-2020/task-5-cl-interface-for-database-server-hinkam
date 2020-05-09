package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class TableCreator implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String databaseName; 
    private String tableName;

    TableCreator(ExecutionEnvironment environment, String databaseName, String tableName) {
        this.environment = environment;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        Optional<Database> database = this.environment.getDatabase(this.databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error(String.format("Database \"%s\" doesn't exist.", databaseName));
        }
        try {
            database.get().createTableIfNotExists(this.tableName);
            return DatabaseCommandResult.success(String.format("Table \"%s\" was created in database \"%s\".", tableName, databaseName));
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}