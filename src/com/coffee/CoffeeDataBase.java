package com.coffee;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CoffeeDataBase extends Coffee {
    List<Coffee> coffeeList;
    public CoffeeDataBase() {
        this.coffeeList = new ArrayList<>();
    }
    public void addCoffee() throws IOException {
        Coffee coffee = new Coffee();
        coffee.setInfo();
        addToFile(coffee);
        coffeeList.add(coffee);
    }
    public Coffee getCoffee(double weight, String coffeeName) {
        for (Coffee coffee : coffeeList) {
            if (coffee.getTotalWeight() == weight && coffee.getCoffeeName().equals(coffeeName)) {
                return coffee;
            }
        }
        return null;
    }
    public void deleteCoffee(double weight, String coffeeName) {
        List<Coffee> coffeesToRemove = new ArrayList<>();
        for (Coffee coffee : coffeeList) {
            if (coffee.getTotalWeight() == weight && coffee.getCoffeeName().equals(coffeeName)) {
                coffeesToRemove.add(coffee);
            }
        }
        coffeeList.removeAll(coffeesToRemove);
        updateFile();
        System.out.println("Removed coffees: " + coffeesToRemove.size());
    }
    public void showAllCoffee() {
        System.out.println("\tList of coffee:");
        System.out.println("\n-----------------------\n");
        for (Coffee coffee : coffeeList) {
            coffee.showInfo();
            System.out.println("\n-----------------------\n");
        }
    }
    public void showAllCoffeeSorted() {
        List<Coffee> sortedList = new ArrayList<>(coffeeList);
        sortedList.sort(Comparator.comparingDouble(coffee -> coffee.getTotalPrice() / coffee.getTotalWeight()));
        System.out.println("\tList of coffee (sorted by price to weight ratio):");
        System.out.println("\n-----------------------\n");
        for (Coffee coffee : sortedList) {
            coffee.showInfo();
            System.out.println("\n-----------------------\n");
        }
    }
    public void showCoffeeByQuality(double minQuality, double maxQuality) {
        System.out.println("\tList of coffee with quality parameters between " + minQuality + " and " + maxQuality + ":");
        boolean success = false;
        System.out.println("\n-----------------------\n");
        for (Coffee coffee : coffeeList) {
            if(coffee.getGrade() >= minQuality && coffee.getGrade() <= maxQuality) {
                coffee.showInfo();
                System.out.println("\n-----------------------\n");
                success = true;
            }
        }
        if(!success) {
            System.out.println("No coffee with grade in that range");
            System.out.println("\n-----------------------\n");
        }
    }
    public void addToFile(Coffee coffee) throws IOException {
        String fileName = "CoffeeDataBase.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.write(coffee.toWrite() + "\n");
        writer.close();
    }
    public void readFromFile() throws IOException {
        String fileName = "CoffeeDataBase.txt";
        List<String> lines = Files.readAllLines(Path.of(fileName));
        coffeeList.clear();
        int linesSize = lines.size();
        for (int i = 0; i < linesSize; i += 12) {
            if (i + 9 < linesSize) {
                Coffee coffee = new Coffee();
                coffee.setCoffeeName(getValueFromLine(lines.get(i), 13));
                coffee.setManufacturer(getValueFromLine(lines.get(i + 1), 14));
                coffee.setSort(getValueFromLine(lines.get(i + 2), 6));
                coffee.setType(getValueFromLine(lines.get(i + 3), 6));
                coffee.setTotalPrice(getDoubleValueFromLine(lines.get(i + 4), 7, " UAH"));
                coffee.setPackageType(getValueFromLine(lines.get(i + 5), 14));
                coffee.setTotalWeight(getDoubleValueFromLine(lines.get(i + 6), 8, " grams"));
                coffee.setCoffeeWeight(getDoubleValueFromLine(lines.get(i + 7), 15, " grams"));
                coffee.setPackageWeight(getDoubleValueFromLine(lines.get(i + 8), 16, " grams"));
                coffee.setTerm(getIntValueFromLine(lines.get(i + 9), 6, " month"));
                coffee.setGrade(getDoubleValueFromLine(lines.get(i + 10), 7));
                coffeeList.add(coffee);
            }
        }
    }
    private void updateFile() {
        String fileName = "CoffeeDataBase.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (Coffee coffee : coffeeList) {
                writer.write(coffee.toWrite() + "\n");
            }
            writer.close();
            System.out.println("File updated successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file: " + e.getMessage());
        }
    }

    protected double getDoubleValueFromLine(String line, int start, String delimiter) {
        int endIndex = line.indexOf(delimiter);
        if (endIndex != -1 && line.length() > start && endIndex > start) {
            return Double.parseDouble(line.substring(start, endIndex));
        }
        return 0.0;
    }
    protected double getDoubleValueFromLine(String line, int start) {
        return Double.parseDouble(line.substring(start));
    }
    protected int getIntValueFromLine(String line, int start, String delimiter) {
        int endIndex = line.indexOf(delimiter);
        if (endIndex != -1 && line.length() > start && endIndex > start) {
            return Integer.parseInt(line.substring(start, endIndex));
        }
        return 0;
    }
    protected int getIntValueFromLine(String line, int start) {
        return Integer.parseInt(line.substring(start));
    }
}
