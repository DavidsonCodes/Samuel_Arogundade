package Project_week_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DeveloperDatabaseConn implements Developers {
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/developers_db", "root", "12345");
            Statement statement = connection.createStatement();

            String createTable = "create table if not exists users(id int, name Text, age int, country Text, track Text)";
            statement.execute(createTable);

//READING FROM PROJECT.TXT
            String filePath = "src/project_week_4/project.txt";

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



    //DOWNLOAD ALL USERS INFORMATION FROM DATABASE TO CONSOLE
            String selectAll = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(selectAll);

            System.out.println("Id., \tName., \tAge., \tCountry., \tTrack");
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

}
