package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKeyCommand implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String tableName, databaseName, objectKey, objectValue;

    public UpdateKeyCommand(ExecutionEnvironment environment, String databaseName, String tableName, String objectKey, String objectValue) {
        this.environment = environment;
        this.tableName = tableName;
        this.databaseName = databaseName;
        this.objectKey = objectKey;
        this.objectValue = objectValue;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        Optional<Database> database = this.environment.getDatabase(this.databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error(String.format("Database \"%s\" doesn't exist.", databaseName));
        }
        try {
            database.get().write(this.tableName, this.objectKey, this.objectValue);
            return DatabaseCommandResult.success(String.format("Key \"%s\" was updated with value \"%s\"", objectKey, objectValue));
        } 
        catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}