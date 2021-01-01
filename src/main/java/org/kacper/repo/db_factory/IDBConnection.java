package org.kacper.repo.db_factory;

import java.sql.Connection;

public interface IDBConnection {
    Connection connect();
}
