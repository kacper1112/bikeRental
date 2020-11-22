package org.kacper.repo;

import java.sql.Connection;

public interface DBConnection {
    Connection connect();
}
