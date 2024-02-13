import java.sql.*;
import java.util.ArrayList;

public class DBConnect {

    DBConnect() {

    }
    public ArrayList<Livre> peupler() {
        String url = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password = "";

        ArrayList<Livre> listLivre = null;
        try {
            ////block to connect to the database
            // this block creates the database and the table if they don't exist
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);//entré dans la base de donnée
            Statement statement = connection.createStatement();

            try {
                statement.execute("CREATE DATABASE DB_biblio");
            } catch (java.sql.SQLException create) {
                System.out.println("la base de donnée biblio existe déjà");
            }
            statement.execute("USE DB_biblio");
            try {
                statement.execute("CREATE TABLE Livres(ID int PRIMARY KEY AUTO_INCREMENT, Libelle VARCHAR(255), Auteur VARCHAR(255), Date VARCHAR(255), Code_barre VARCHAR(255), Disponibilite BOOLEAN)");
            } catch (java.sql.SQLSyntaxErrorException table_create) {
                System.out.println("La table existe déjà");
            }
            ////end of the block to connect



            ResultSet resultSet = statement.executeQuery("SELECT * FROM Livres");//fetching data from database

            listLivre = new ArrayList<Livre>();

            while (resultSet.next()) {
                Livre arrayLivre = new Livre();
                System.out.println("1");
                arrayLivre.setID(resultSet.getInt(1));
                arrayLivre.setLibelle(resultSet.getString(2));
                arrayLivre.setAuteur(resultSet.getString(3));
                arrayLivre.setDate(resultSet.getString(4));
                arrayLivre.setCode(resultSet.getString(5));
                arrayLivre.setDisponible(resultSet.getBoolean(6));
                listLivre.add(arrayLivre);
                System.out.println(listLivre.get(0));
            }
            System.out.println("data fetched");
            System.out.println("fin");
            connection.close();
            System.out.println("connection fermée");
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("retourne l'Arraylist à MyFrame");
        return listLivre;
    }
    public void ajouter(Livre l1){
        String url = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password = "";
        try{ // this block creates the database and the table if they don't exist

            ////block to connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println(connection);//entré dans la base de donnée
            Statement statement = connection.createStatement();

            try{
                statement.execute("CREATE DATABASE DB_biblio");
            }
            catch (java.sql.SQLException create){
                System.out.println("la base de donnée biblio existe déjà");
            }
            statement.execute("USE DB_biblio");
            try{
                statement.execute("CREATE TABLE Livres(ID int PRIMARY KEY AUTO_INCREMENT, Libelle VARCHAR(255), Auteur VARCHAR(255), Date VARCHAR(255), Code_barre VARCHAR(255), Disponibilite BOOLEAN)");
            }catch (java.sql.SQLSyntaxErrorException table_create){
                System.out.println("La table existe déjà");
            }
            ////end of the block to connect

            String query = "INSERT INTO `livres`(`Libelle`, `Auteur`, `Date`, `Code_barre`, `Disponibilite`) VALUES ( ? , ? , ? , ? , ?)";
            PreparedStatement myStmt = connection.prepareStatement(query);
            myStmt.setString(1,l1.getLibelle());
            myStmt.setString(2,l1.getAuteur());
            myStmt.setString(3,l1.getDate());
            myStmt.setString(4,l1.getCode());
            myStmt.setBoolean(5,l1.getDisponible());
            int res = myStmt.executeUpdate();

            System.out.println("fin");
            connection.close();
            System.out.println("connection fermée");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void modifier(Livre l1){
        String url = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password = "";
        try{ // this block creates the database and the table if they don't exist

            ////block to connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println(connection);//entré dans la base de donnée
            Statement statement = connection.createStatement();

            try{
                statement.execute("CREATE DATABASE DB_biblio");
            }
            catch (java.sql.SQLException create){
                System.out.println("la base de donnée biblio existe déjà");
            }
            statement.execute("USE DB_biblio");
            try{
                statement.execute("CREATE TABLE Livres(ID int PRIMARY KEY AUTO_INCREMENT, Libelle VARCHAR(255), Auteur VARCHAR(255), Date VARCHAR(255), Code_barre VARCHAR(255), Disponibilite BOOLEAN)");
            }catch (java.sql.SQLSyntaxErrorException table_create){
                System.out.println("La table existe déjà");
            }
            ////end of the block to connect
            System.out.println(l1.toString());

            String query = "UPDATE `livres` SET `Libelle`=?,`Auteur`=?,`Date`=?,`Code_barre`=?,`Disponibilite`=? WHERE ID=?";
            PreparedStatement myStmt = connection.prepareStatement(query);
            myStmt.setString(1,l1.getLibelle());
            myStmt.setString(2,l1.getAuteur());
            myStmt.setString(3,l1.getDate());
            myStmt.setString(4,l1.getCode());
            myStmt.setBoolean(5,l1.getDisponible());
            myStmt.setInt(6,l1.getID());
            int res = myStmt.executeUpdate();

            System.out.println("fin de modification"+res);
            connection.close();
            System.out.println("connection fermée");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    public void supprimer(int selectedID) {
        String url = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password = "";
        try { // this block creates the database and the table if they don't exist

            ////block to connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);//entré dans la base de donnée
            Statement statement = connection.createStatement();

            try {
                statement.execute("CREATE DATABASE DB_biblio");
            } catch (java.sql.SQLException create) {
                System.out.println("la base de donnée biblio existe déjà");
            }
            statement.execute("USE DB_biblio");
            try {
                statement.execute("CREATE TABLE Livres(ID int PRIMARY KEY AUTO_INCREMENT, Libelle VARCHAR(255), Auteur VARCHAR(255), Date VARCHAR(255), Code_barre VARCHAR(255), Disponibilite BOOLEAN)");
            } catch (java.sql.SQLSyntaxErrorException table_create) {
                System.out.println("La table existe déjà");
            }
            ////end of the block to connect
            System.out.println("selected Id= " + selectedID);

            String query = "DELETE FROM `livres` WHERE `ID`= ?";
            PreparedStatement myStmt = connection.prepareStatement(query);
            myStmt.setInt(1, selectedID);
            int res = myStmt.executeUpdate();

            System.out.println("fin de suppression de l'ID n-"+ selectedID +"res= "+ res);
            connection.close();
            System.out.println("connection fermée");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //function to display every row
    //function to add a row
    //function to modify a row
    //function to delete a row
}
