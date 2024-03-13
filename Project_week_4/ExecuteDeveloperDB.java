package Project_week_4;


public class ExecuteDeveloperDB extends DeveloperDatabaseConn{
    public static void main(String[] args) {

        ExecuteDeveloperDB executeDeveloperDB = new ExecuteDeveloperDB();
        executeDeveloperDB.loadDevelopers();
    }
}
