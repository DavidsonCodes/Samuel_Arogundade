package Project_week_4_Enhanaced;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class NewDeveloperDatabaseConn extends NewDeleteUserData implements NewDevelopers {
    /*
   Connect to database : developers_db
   Create Table within the database called - users
   Post several users into the table from project.txt
   Query to select all users information and show them on tne console
    */



    @Override
    public ResultSet loadDevelopers() {

//CREATING CONNECTION
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/developersEnhanced_db", "root", "12345");
            Statement statement = connection.createStatement();

            String createTable = "create table if not exists users(id int, name Text, age int, country Text, track Text)";
            statement.execute(createTable);

//READING FROM PROJECT.TXT
            String filePath = "src/Project_week_4_Enhanaced/project.txt";

            try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
                String line;
                int count = 1;
                while ((line = reader.readLine())!=null){

                    String[] data = line.split(", ");
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    String country = data[3];
                    String track = data[4];
                    String uploadData = String.format("insert into users(id, name, age, country, track) values(%d, '%s', %d,  '%s', '%s')", id, name, age, country, track);
                    statement.execute(uploadData);


                    count++;
                }
            }catch (IOException e){
                e.printStackTrace();
            }


            statement.close();


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        return null;
    }

//DOWNLOAD ALL USERS INFORMATION FROM DATABASE TO CONSOLE
    public void DownloadData(){
        Connection connection  = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/developers_db", "root", "12345");
            Statement statement = connection.createStatement();

            //DOWNLOAD ALL USERS INFORMATION FROM DATABASE TO CONSOLE
            String selectAll = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(selectAll);

            System.out.println("Id, \tName, \tPhone No., \temail");
            while( resultSet.next() ){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String country = resultSet.getString(4);
                String track = resultSet.getString(5);
                System.out.println(id + "\t" + name + "\t" + age + "\t" + country + "\t" + track);
            }
            resultSet.close();
            statement.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

//DELETE USERS INFORMATION FROM DATABASE IN ROWS

    @Override
    public void deleteUserData(){
        Connection connection  = null;
        Scanner scanner = new Scanner(System.in);


        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/developersEnhanced_db", "root", "12345");
            Statement statement = connection.createStatement();

            System.out.println("Enter roll number to delete");
            int rollNumber = Integer.parseInt(scanner.nextLine());

            String query = "delete from users where id=" + rollNumber;
            int row = statement.executeUpdate(query);

            if(row>0){
                System.out.println("Alert!.. Row deleted successfully");
            }
            statement.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

    }


}

