package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKeyCommand implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String databaseName; 
    private String tableName;
    private String objectKey;

    public ReadKeyCommand(ExecutionEnvironment environment, String databaseName, String tableName, String objectKey) {
        this.environment = environment;
        this.tableName = tableName;
        this.databaseName = databaseName;
        this.objectKey = objectKey;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        Optional<Database> database = this.environment.getDatabase(this.databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error(String.format("Database \"%s\" doesn't exist", databaseName));
        }
        try {
            return DatabaseCommandResult.success(database.get().read(this.tableName, this.objectKey));
        } 
        catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}