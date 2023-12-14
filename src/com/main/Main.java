package com.main;

import com.coffee.Coffee;
import com.coffee.CoffeeDataBase;
import com.truck.Truck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CoffeeDataBase coffeeDataBase = new CoffeeDataBase();
        Scanner scanner = new Scanner(System.in);
        List<Truck> truckList = new ArrayList<>();

        CommandProcessor commandProcessor = new CommandProcessor(scanner, coffeeDataBase, truckList);
        try {
            commandProcessor.processCommands();
        } catch (IOException e) {
            System.out.println("An error occurred while processing commands: " + e.getMessage());
        }
    }
}

interface Command {
    void execute() throws IOException;
    default void clearConsole() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }
}

class WorkWithTrucksCommand implements Command {
    private final Scanner scanner;
    private final CoffeeDataBase coffeeDataBase;
    private final List<Truck> truckList;

    public WorkWithTrucksCommand(Scanner scanner, CoffeeDataBase coffeeDataBase, List<Truck> truckList) {
        this.scanner = scanner;
        this.coffeeDataBase = coffeeDataBase;
        this.truckList = truckList;
    }

    @Override
    public void execute() throws IOException {
        coffeeDataBase.readFromFile();
        int choice = -1, nextChoice;

        while (choice != 0) {
            System.out.println("\nMenu:");
            System.out.println("1. Add new truck");
            System.out.println("2. Add baggage to truck");
            System.out.println("3. Show info about truck");
            System.out.println("4. Show info about all trucks");
            System.out.println("5. Show coffee in truck by quality");
            System.out.println("6. Read data from file");
            System.out.println("0. Return");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Truck newTruck = new Truck();
                    newTruck.setInfo();
                    truckList.add(newTruck);
                    newTruck.writeTruckInfoToFile();
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Choose a truck: ");
                    String selectedTruckName = scanner.nextLine();
                    boolean truckFound = false;
                    for (Truck truck : truckList) {
                        if (truck.getTruckName().equalsIgnoreCase(selectedTruckName)) {
                            truck.addCoffee(coffeeDataBase);
                            truckFound = true;
                            break;
                        }
                    }
                    if (!truckFound) {
                        System.out.println("Invalid truck selection.");
                    }
                    scanner.nextLine();
                    break;
                case 3:
                    for (int i = 0; i < 50; ++i) System.out.println();
                    System.out.print("Choose a truck: ");
                    selectedTruckName = scanner.nextLine();
                    truckFound = false;
                    for (Truck truck : truckList) {
                        if (Objects.equals(truck.getTruckName(), selectedTruckName)) {
                            truck.showAllPackedCoffee();
                            truckFound = true;
                            break;
                        }
                    }
                    if (!truckFound) {
                        System.out.println("Invalid truck selection.");
                    }
                    scanner.nextLine();
                    break;
                case 4:
                    for (int i = 0; i < 50; ++i) System.out.println();
                    for (Truck truck : truckList) {
                        truck.showAllPackedCoffee();
                    }
                    scanner.nextLine();
                    break;
                case 5:
                    for (int i = 0; i < 50; ++i) System.out.println();
                    String truckName;
                    System.out.print("Truck name: ");
                    truckName = scanner.nextLine();
                    for (Truck truck : truckList) {
                        if(Objects.equals(truck.getTruckName(), truckName)) {
                            double minQuality = 1, maxQuality = 0;
                            while (maxQuality < minQuality) {
                                System.out.print("Enter min quality parameter: ");
                                minQuality = Double.parseDouble(scanner.next());
                                scanner.nextLine();
                                System.out.print("Enter max quality parameter: ");
                                maxQuality = Double.parseDouble(scanner.next());
                                scanner.nextLine();
                            }
                            for (int i = 0; i < 50; ++i) System.out.println();
                            truck.showCoffeeByQualityInTruck(minQuality, maxQuality);
                            scanner.nextLine();
                        }
                    }
                    break;
                case 6:
                    System.out.print("Enter the truck name to load info from file: ");
                    String truckFileName = scanner.nextLine();
                    boolean fileExists = Files.exists(Paths.get(truckFileName + "Info.txt"));
                    if (fileExists) {
                        Truck truck = new Truck();
                        truck.readTruckInfoFromFile(truckFileName + "Info.txt");
                        truckList.add(truck);
                        System.out.println("Truck info loaded successfully.");
                    } else {
                        System.out.println("File does not exist.");
                    }
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
                    break;
            }
        }
        System.out.println("Goodbye");
    }
}

class WorkWithCoffeeCommand implements Command {
    private final Scanner scanner;
    private final CoffeeDataBase coffeeDataBase;

    public WorkWithCoffeeCommand(Scanner scanner, CoffeeDataBase coffeeDataBase) {
        this.scanner = scanner;
        this.coffeeDataBase = coffeeDataBase;
    }

    @Override
    public void execute() throws IOException {
        int nextChoice = -1;

        while (nextChoice != 0) {
            clearConsole();
            System.out.println("\nCoffee Menu:");
            System.out.println("1. Add new coffee");
            System.out.println("2. Show coffee list");
            System.out.println("3. Delete coffee");
            System.out.println("0. Return");
            System.out.print("Enter your choice: ");
            nextChoice = scanner.nextInt();
            scanner.nextLine();

            switch (nextChoice) {
                case 1:
                    clearConsole();
                    coffeeDataBase.addCoffee();
                    break;
                case 2:
                    int subChoice = -1;
                    do {
                        clearConsole();
                        System.out.println("\nShow Coffee Menu:");
                        System.out.println("1. Show All coffee list");
                        System.out.println("2. Show coffee by price to weight ratio");
                        System.out.println("3. Show coffee by quality");
                        System.out.println("4. Return");
                        System.out.print("Enter your choice: ");
                        subChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch (subChoice) {
                            case 1:
                                clearConsole();
                                coffeeDataBase.showAllCoffee();
                                scanner.nextLine();
                                break;
                            case 2:
                                clearConsole();
                                coffeeDataBase.showAllCoffeeSorted();
                                scanner.nextLine();
                                break;
                            case 3:
                                clearConsole();
                                double minQuality = 1, maxQuality = 0;
                                while (maxQuality < minQuality) {
                                    System.out.print("Enter min quality parameter: ");
                                    minQuality = Double.parseDouble(scanner.next());
                                    scanner.nextLine();
                                    System.out.print("Enter max quality parameter: ");
                                    maxQuality = Double.parseDouble(scanner.next());
                                    scanner.nextLine();
                                }
                                clearConsole();
                                coffeeDataBase.showCoffeeByQuality(minQuality, maxQuality);
                                scanner.nextLine();
                                break;
                        }
                    } while (subChoice != 4);
                    break;
                case 3:
                    clearConsole();
                    String name;
                    double weight;
                    System.out.print("Enter coffee name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter weight: ");
                    weight = Double.parseDouble(scanner.next());
                    scanner.nextLine();
                    coffeeDataBase.deleteCoffee(weight, name);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
                    break;
            }
            clearConsole();
        }
    }
}

class CommandProcessor {
    private final Command workWithTrucksCommand;
    private final Command workWithCoffeeCommand;
    private final Scanner scanner;
    private final CoffeeDataBase coffeeDataBase;
    private final List<Truck> truckList;

    public CommandProcessor(Scanner scanner, CoffeeDataBase coffeeDataBase, List<Truck> truckList) {
        this.scanner = scanner;
        this.coffeeDataBase = coffeeDataBase;
        this.truckList = truckList;
        workWithTrucksCommand = new WorkWithTrucksCommand(scanner, coffeeDataBase, truckList);
        workWithCoffeeCommand = new WorkWithCoffeeCommand(scanner, coffeeDataBase);
    }

    public void processCommands() throws IOException {
        int choice = -1;

        while (choice != 0) {
            clearConsole();
            System.out.println("\nMenu:");
            System.out.println("1. Work with Trucks");
            System.out.println("2. Work with Coffee");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    workWithTrucksCommand.execute();
                    break;
                case 2:
                    workWithCoffeeCommand.execute();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
                    break;
            }
            clearConsole();
        }
        System.out.print("Goodbye");
    }

    private void clearConsole() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }
}
