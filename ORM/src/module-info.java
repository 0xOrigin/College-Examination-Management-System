module ORM {
    requires java.sql;
    requires java.desktop;
    requires AppDataReader;
    exports ORM;
    exports ORM.Utilities;
}