package Project_week_4_Enhanaced;


import java.util.Scanner;

public class NewExecuteDeveloperDB extends NewDeveloperDatabaseConn {
    public static void main(String[] args) {


        NewExecuteDeveloperDB executeDeveloperDB = new NewExecuteDeveloperDB();

        Scanner scanner = new Scanner(System.in);
        String input;

        do{
            System.out.println();
            System.out.println("You are about to manipulate data to-from the DATABASE");
            System.out.println("Enter 1 -> Upload data and print to console");
            System.out.println("Enter 2 -> Download data and print to console");
            System.out.println("Enter 3 -> To Delete rows from Database");
            System.out.println("Enter 4 -> To terminate the program.");

            input = scanner.nextLine();

            switch (Integer.parseInt(input)){
                case 1:
                    System.out.println("loading..");
                    try{
                        Thread.sleep(3000);
                    }catch(InterruptedException e){
                        System.out.println(e.getMessage());
                    }

                    executeDeveloperDB.loadDevelopers();
                    break;

                case 2:
                    System.out.println("loading..");
                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        System.out.println(e.getMessage());
                    }

                    executeDeveloperDB.DownloadData();
                    break;

                case 3:
                    System.out.println("loading..");
                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        System.out.println(e.getMessage());
                    }

                    executeDeveloperDB.deleteUserData();
                    break;

                case 4:
                    System.out.println("you have successfully terminated the program");
                    System.exit(0);

            }
        }while(!(input.equals(String.valueOf(4))));
    }

}
