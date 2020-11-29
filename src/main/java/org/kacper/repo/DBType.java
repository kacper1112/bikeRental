package org.kacper.repo;

import java.util.function.Supplier;

public enum DBType {
    POSTGRES(PostgresDB::new),
    H2(H2DB::new);
    
    private final Supplier<IDBConnection> constructor;
    
    DBType(Supplier<IDBConnection> constructor) {
        this.constructor = constructor;
    }
    
    public Supplier<IDBConnection> getConstructor() {
        return this.constructor;
    }
}
