package com.krismorte.zion.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class DefaultOperationTest {

    String userTest = "user_test";

    public String getSingleResult(Connection connection,String command) throws Exception{
        Statement statement = connection.createStatement();
        statement.execute(command);
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        return resultSet.getString(1);
    }

}
