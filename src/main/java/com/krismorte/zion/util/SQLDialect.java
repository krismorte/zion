/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.util;

import com.krismorte.zion.view.util.Biblioteca;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class SQLDialect {

    public static String[] COMMANDS_SYNTAX_MSSQL = {"ADD", "EXTERNAL", "PROCEDURE", "FETCH", "PUBLIC", "ALTER", "FILE", "RAISERROR", "FILLFACTOR", "READ", "FOR", "READTEXT", "AS", "FOREIGN", "RECONFIGURE", "ASC", "FREETEXT", "REFERENCES", "AUTHORIZATION", "FREETEXTTABLE", "REPLICATION", "BACKUP", "FROM", "RESTORE", "BEGIN", "FULL", "RESTRICT", "RETURN", "BREAK", "GOTO", "REVERT", "BROWSE", "GRANT", "REVOKE", "BULK", "GROUP", "BY", "HAVING", "ROLLBACK", "CASCADE", "HOLDLOCK", "ROWCOUNT", "CASE", "IDENTITY", "ROWGUIDCOL", "CHECK", "IDENTITY_INSERT", "RULE", "CHECKPOINT", "IDENTITYCOL", "SAVE", "CLOSE", "IF", "SCHEMA", "CLUSTERED", "SECURITYAUDIT", "INDEX", "SELECT", "COLLATE", "COLUMN", "INSERT", "COMMIT", "INTERSECT", "COMPUTE", "INTO", "CONSTRAINT", "SET", "CONTAINS", "SETUSER", "CONTAINSTABLE", "KEY", "SHUTDOWN", "CONTINUE", "KILL", "STATISTICS", "CREATE", "LINENO", "TABLE", "CURRENT", "LOAD", "TABLESAMPLE", "CURRENT_DATE", "MERGE", "TEXTSIZE", "CURRENT_TIME", "NATIONAL", "THEN", "NOCHECK", "NONCLUSTERED", "TOP", "CURSOR", "TRAN", "DATABASE", "TRANSACTION", "DBCC", "TRIGGER", "DEALLOCATE", "OF", "TRUNCATE", "DECLARE", "OFF", "TRY_CONVERT", "DEFAULT", "OFFSETS", "TSEQUAL", "DELETE", "ON", "UNION", "DENY", "OPEN", "UNIQUE", "DESC", "OPENDATASOURCE", "DISK", "OPENQUERY", "UPDATE", "DISTINCT", "OPENROWSET", "UPDATETEXT", "DISTRIBUTED", "OPENXML", "USE", "DOUBLE", "OPTION", "DROP", "VALUES", "DUMP", "ORDER", "VARYING", "ELSE", "VIEW", "END", "OVER", "WAITFOR", "ERRLVL", "PERCENT", "WHEN", "ESCAPE", "WHERE", "EXCEPT", "PLAN", "WHILE", "EXEC", "PRECISION", "WITH", "EXECUTE", "PRIMARY", "PRINT", "WRITETEXT", "EXIT", "PROC"};
    public static String[] OPERATIONS_SYNTAX_MSSQL = {"ALL", "AND", "ANY", "BETWEEN", "RIGHT", "IN", "INNER", "IS", "JOIN", "LIKE", "SOME", "LEFT", "CROSS", "NOT", "NULL", "UNPIVOT", "OR", "OUTER", "PIVOT", "EXISTS"};
    public static String[] FUNCTIONS_SYNTAX_MSSQL = {"COALESCE", "SESSION_USER", "CONVERT", "SYSTEM_USER", "CURRENT_TIMESTAMP", "CURRENT_USER", "NULLIF", "USER","SERVERNAME","VERSION","DB_NAME"};

    public static List<Biblioteca> getDialectMSSQL() {
        List<Biblioteca> lst = new ArrayList<Biblioteca>();
        lst.add(new Biblioteca(SQLDialect.COMMANDS_SYNTAX_MSSQL, Color.blue));
        lst.add(new Biblioteca(SQLDialect.OPERATIONS_SYNTAX_MSSQL, Color.gray));
        lst.add(new Biblioteca(SQLDialect.FUNCTIONS_SYNTAX_MSSQL, Color.red));
        return lst;
    }

}
