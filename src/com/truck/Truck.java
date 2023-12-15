package com.truck;

import Logs.EmailSender;
import Logs.MyLogger;
import com.coffee.Coffee;
import com.coffee.CoffeePackaging;
import com.coffee.CoffeeDataBase;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Truck extends CoffeePackaging {
    Scanner scanner;
    String truckName;
    double maxWeight;
    double currentWeight;
    private List<CoffeePackaging> coffeeList;

    public Truck() {
        this.scanner = new Scanner(System.in);
        this.truckName = "";
        this.maxWeight = 0.00;
        this.currentWeight = 0.00;
        this.coffeeList = new ArrayList<>();
    }
    public void setTruckName(String name) {
        this.truckName = name;
    }
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }
    public String getTruckName() {
        return truckName;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public boolean setCurrentWeight(double totalWeight, double packagesAmount) {
        if ((currentWeight + totalWeight * packagesAmount) <= getMaxWeight()) {
            currentWeight += totalWeight * packagesAmount;
            return true;
        }
        System.out.println(currentWeight + totalWeight * packagesAmount + " >= " + getMaxWeight());
        return false;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void getInfo() {
        System.out.println("Truck name: " + getTruckName());
        System.out.println("Max Weight: " + String.format("%.2f", getMaxWeight() / 1000) + " kilograms");
        System.out.println("Current Weight: " + String.format("%.2f", getCurrentWeight() / 1000) + " kilograms\n");
    }

    public void setInfo() {
        System.out.print("Truck name: ");
        truckName = scanner.nextLine();
        System.out.print("Max Weight(kilograms):");
        maxWeight = Double.parseDouble(scanner.next());
        maxWeight *= 1000;
        currentWeight = 0.00;
        System.out.print("Truck " + getTruckName() + " added");
    }

    public void addCoffee(CoffeeDataBase obj) throws IOException {
        obj.showAllCoffee();
        Coffee coffee = new Coffee();
        String name;
        double weight;
        int packagesAmount;

        System.out.print("Select Coffee that you want to add: \nEnter coffee name: ");
        name = scanner.nextLine();

        System.out.print("Enter weight(in grams): ");
        weight = Double.parseDouble(scanner.next());
        scanner.nextLine();

        System.out.print("Enter Amount of packages: ");
        String packagesInput = scanner.nextLine();
        packagesAmount = Integer.parseInt(packagesInput);
        coffee.setInfo(obj.getCoffee(weight, name));

        if (setCurrentWeight(coffee.getTotalWeight(), packagesAmount)) {
            System.out.println("Enough space");
        }

        CoffeePackaging cp = new CoffeePackaging();
        if (coffee.getTotalWeight() - getCurrentWeight() >= weight * packagesAmount) {
            cp.setInfo(coffee, packagesAmount);
            coffeeList.add(cp);
            writeTruckInfoToFile();
            MyLogger.logInfo("Adding coffee to the truck");
        } else {
            System.out.println("Too heavy baggage");
            //String subject = "кава не загружена в грузовик";
            //String body = "Вага кави не відпвідає нормам і тому вона не була загружена в грузовик";
            //EmailSender.sendEmail("pashakcnk@example.com", subject, body);
        }
    }

    public void showAllPackedCoffee() {
        getInfo();
        if (coffeeList.isEmpty()) {
            System.out.println("No coffee packed");
        } else {
            for (int i = 0; i < coffeeList.size(); i++) {
                Coffee coffee = coffeeList.get(i);
                System.out.println("\tCoffee Info: ");
                coffee.showInfo();
                System.out.println("-----------------------");
            }
        }
    }

    public void writeTruckInfoToFile() throws IOException {
        String fileName = truckName + "Info.txt";
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("Truck name: " + getTruckName());
        printWriter.println("Max Weight: " + getMaxWeight() + " grams");
        printWriter.println("Current Weight: " + getCurrentWeight() + " grams");
        printWriter.println("\tCoffee packed:");

        if (!coffeeList.isEmpty()) {
            for (CoffeePackaging c : coffeeList) {
                printWriter.write(c.toWrite() + "\n");
            }
        } else {
            printWriter.println("No coffee packed");
        }

        printWriter.close();
    }

    public void readTruckInfoFromFile(String truckFileName) throws IOException {
        FileReader fileReader = new FileReader(truckFileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String fileName = truckName + "Info.txt";
        String line;
        CoffeePackaging coffeePackaging = null;
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1) {
                String attribute = parts[0];
                String value = parts[1];

                switch (attribute.trim()) {
                    case "Truck name":
                        this.truckName = value;
                        break;
                    case "Max Weight":
                        this.maxWeight = Double.parseDouble(value.replaceAll("[^0-9.]", ""));
                        break;
                    case "Current Weight":
                        this.currentWeight = Double.parseDouble(value.replaceAll("[^0-9.]", ""));
                        break;
                    case "Coffee name":
                        if (coffeePackaging != null) {
                            coffeeList.add(coffeePackaging);
                        }
                        coffeePackaging = new CoffeePackaging();
                        coffeePackaging.setCoffeeName(value);
                        break;
                    case "Manufacturer":
                        coffeePackaging.setManufacturer(value);
                        break;
                    case "Sort":
                        coffeePackaging.setSort(value);
                        break;
                    case "Type":
                        coffeePackaging.setType(value);
                        break;
                    case "Price":
                        coffeePackaging.setTotalPrice(Double.parseDouble(value.replaceAll("[^0-9.]", "")));
                        break;
                    case "Package type":
                        coffeePackaging.setPackageType(value);
                        break;
                    case "Weight":
                        coffeePackaging.setTotalWeight(Double.parseDouble(value.replaceAll("[^0-9.]", "")));
                        break;
                    case "Coffee Weight":
                        coffeePackaging.setCoffeeWeight(Double.parseDouble(value.replaceAll("[^0-9.]", "")));
                        break;
                    case "Package Weight":
                        coffeePackaging.setPackageWeight(Double.parseDouble(value.replaceAll("[^0-9.]", "")));
                        break;
                    case "Term":
                        coffeePackaging.setTerm(Integer.parseInt(value.replaceAll("[^0-9.]", "")));
                        break;
                    case "Grade":
                        coffeePackaging.setGrade(Double.parseDouble(value.replaceAll("[^0-9.]", "")));
                        break;
                    case "Number of packages":
                        coffeePackaging.setNumberOfPackages(Integer.parseInt(value.replaceAll("[^0-9.]", "")));
                        break;
                    default:
                        break;
                }
            }
        }
        if (coffeePackaging != null) {
            coffeeList.add(coffeePackaging);
        }

        bufferedReader.close();
        fileReader.close();
    }
    public void showCoffeeByQualityInTruck(double minQuality, double maxQuality) {
        boolean success = false;
        System.out.println("\tList of coffee with quality parameters between " + minQuality + " and " + maxQuality + ":");
        System.out.println("\n-----------------------\n");

        for (CoffeePackaging coffee : coffeeList) {
            if (coffee.getGrade() >= minQuality && coffee.getGrade() <= maxQuality) {
                coffee.showInfo();
                System.out.println("\n-----------------------\n");
                success = true;
            }
        }

        if (!success) {
            System.out.println("No coffee with grade in that range");
            System.out.println("\n-----------------------\n");
        }
    }
}