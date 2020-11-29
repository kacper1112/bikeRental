package org.kacper.repo;

import java.sql.Connection;

public interface IDBConnection {
    Connection connect();
}
