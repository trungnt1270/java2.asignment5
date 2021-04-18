package FileProcessingApplication;

import java.util.Scanner;

public class RunApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FileManagement fm = new FileManagement();
        System.out.println("========== File Processing ==========");
        while (true) {
            System.out.println("1. Find person info");
            System.out.println("2. Copy Text to new file");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice: ");
            int choice;
            while (true){
                try {
                    choice = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e){
                    System.out.println("Error: " + e.getMessage());
                    System.out.print("Enter your choice: ");
                }
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("---------- Person info ----------");
                    System.out.print("Enter Path: ");
                    String path = sc.nextLine();
                    double money;
                    while (true) {
                        try {
                            System.out.print("Enter Money: ");
                            money = Double.parseDouble(sc.nextLine());
                            break;
                        } catch (NumberFormatException e){
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    System.out.println("--------------- Result --------------");
                    System.out.println("Name\t\t\t\tAddress\t\t\t\tMoney");
                    var personList = fm.getPerson(path, money);
                    for (var person : personList) {
                        System.out.println(person.toString());
                    }
                    System.out.println("\nMax: " + personList.get(personList.size() - 1).getName());
                    System.out.println("Min: " + personList.get(0).getName());
                    System.out.println("\nWhat to do next ?\n");
                }
                case 2 -> {
                    System.out.println("--------------- Copy Text ---------------");
                    System.out.print("Enter source: ");
                    String source = sc.nextLine();
                    System.out.print("Enter new file name: ");
                    String destination = sc.nextLine();
                    if (fm.copyWordOneTimes(source, destination)) {
                        System.out.println("Copy done...");
                    } else {
                        System.out.println("Copy false!");
                    }
                }
                case 3 -> System.exit(0);
                default -> {
                    System.out.println("Invalid choice!");
                    System.out.println("\nPlease choose a valid choice!\n");
                }

            }
        }
    }
}
