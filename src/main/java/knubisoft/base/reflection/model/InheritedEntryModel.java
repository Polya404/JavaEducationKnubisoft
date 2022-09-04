package knubisoft.base.reflection.model;

import lombok.SneakyThrows;

public class InheritedEntryModel extends EntryModel {

    public InheritedEntryModel(String tableName) {
        super(tableName);
    }

    public InheritedEntryModel(String tableName, String schemaName) {
        super(tableName, schemaName);
    }

    public InheritedEntryModel(String tableName, String schemaName, String version) {
        super(tableName, schemaName, version);
    }

    @Override
    @Deprecated
    public EntryModel builder() {
        return super.builder();
    }
}
