package com.krismorte.zion.model;

public enum SupportedDatabases {

    MSSQL("MSSQL"),MYSQL("MYSQL"),POSTGRES("POSTGRES"),ORACLE("ORACLE");

    private String name;
    SupportedDatabases(String name){
        this.name=name;
    }


    @Override
    public String toString() {
        return name ;
    }
}
