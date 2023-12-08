package com.coffee;

import com.Package.Package;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Scanner;

public class Coffee extends Package {
    private String coffeeName;
    private String packageType;
    private String manufacturer;
    private String sort;
    private String type;
    private double coffeeWeight;
    private double totalWeight;
    private double totalPrice;
    private double priceFor100G;
    private double coffeePrice;
    private double grade;
    private int term;
    private static final int DECIMAL_PLACES = 2;

    public Coffee() {
        this.coffeeName = "";
        this.packageType = "";
        this.manufacturer = "";
        this.totalPrice = 0.00;
        this.sort = "";
        this.type = "";
        this.coffeeWeight = 0.00;
        this.totalWeight = 0.00;
        this.term = 0;
    }
    private double formatDouble(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(DECIMAL_PLACES, RoundingMode.DOWN);
        return bd.doubleValue();
    }
    public void setCoffeeName(String coffeeName) { this.coffeeName = coffeeName; }
    public void setPackageType(String packageType) { this.packageType = packageType; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public void setSort(String sort) { this.sort = sort; }
    public void setType(String type) { this.type = type; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setCoffeeWeight(double coffeeWeight) { this.coffeeWeight = coffeeWeight; }
    public void setTotalWeight(double totalWeight) { this.totalWeight = totalWeight; }
    public void setGrade(double grade) { this.grade = grade; }
    public void setTerm(int term) { this.term = term; }
    public void setTotalWeight() { totalWeight = coffeeWeight + getPackageWeight();  }
    protected String getValueFromLine(String line, int start) {
        if (line.length() > start) {
            return line.substring(start);
        }
        return "";
    }
    public String getCoffeeName() { return coffeeName; }
    public String getPackageType() { return packageType; }
    public String getSort() { return sort; }
    public String getType() { return type; }
    public String getManufacturer() { return manufacturer; }
    public double getCoffeeWeight() { return coffeeWeight; }
    public double getTotalWeight() { return totalWeight; }
    public double getPriceFor100G() { return priceFor100G; }
    public double getCoffeePrice() { return coffeePrice; }
    public double getTotalPrice() { return totalPrice; }
    public double getGrade() { return grade; }
    public int getTerm() { return term; }
    public void calcTotalWeight() {
        double oneHundredPackWeight = 0;
        double oneGramPrice = 0;
        boolean correctType = false;
        if(Objects.equals(packageType, "Can")) { oneHundredPackWeight = 30; oneGramPrice = 1.5; correctType = true; }
        else if(Objects.equals(packageType, "Jar")) { oneHundredPackWeight = 24; oneGramPrice = 0.8; correctType = true; }
        else if(Objects.equals(packageType, "Pack")) { oneHundredPackWeight = 4; oneGramPrice = 0.05; correctType = true; }
        if(correctType) {
            setPackageWeight(oneHundredPackWeight, getCoffeeWeight());
            setPackagePrice(oneGramPrice);
            setTotalWeight();
        }
    }
    public void calcGrade() {
        int aroma = -1, flavor = -1, density = -1, acidity = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\tQuality parameters: ");
        while(aroma < 0 || aroma > 10) {
            System.out.print("Enter coffee aroma(from 1 to 10): ");
            aroma = scanner.nextInt();
        }
        while(flavor < 0 || flavor > 10) {
            System.out.print("Enter coffee flavor(from 1 to 10): ");
            flavor = scanner.nextInt();
        }
        while(density < 0 || density > 10) {
            System.out.print("Enter coffee density(from 1 to 10): ");
            density = scanner.nextInt();
        }
        while(acidity < 0 || acidity > 10) {
            System.out.print("Enter coffee acidity(from 1 to 10): ");
            acidity = scanner.nextInt();
        }
        this.grade = (double) (aroma + flavor + density + acidity) / 4;
    }
    public void calcTotalPrice() {
        calcTotalWeight();
        coffeePrice = getPriceFor100G() / 100 * getCoffeeWeight();
        totalPrice = getCoffeePrice() + getPackagePrice();
    }
    public void setInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\tEnter info about coffee: ");
        System.out.print("Enter coffee name: ");
        this.coffeeName = scanner.nextLine();
        System.out.print("Enter manufacturer: ");
        this.manufacturer = scanner.nextLine();
        while(!Objects.equals(getSort(), "Arabica") && !Objects.equals(getSort(), "Robusta") && !Objects.equals(getSort(), "Sublimated")) {
            System.out.print("Enter sort(Arabica, Robusta, Sublimated): ");
            this.sort = scanner.nextLine();
        }
        while(!Objects.equals(getType(), "Grains") && !Objects.equals(getType(), "Soluble") && !Objects.equals(getType(), "Powdery")) {
            System.out.print("Enter type(Grains, Soluble, Powdery): ");
            this.type = scanner.nextLine();
        }
        System.out.print("Enter coffee Price(for 100 Gram): ");
        this.priceFor100G = Double.parseDouble(scanner.next());
        scanner.nextLine();
        System.out.print("Enter coffee weight(grams): ");
        this.coffeeWeight = Double.parseDouble(scanner.next());
        scanner.nextLine();
        while(!Objects.equals(getPackageType(), "Can") && !Objects.equals(getPackageType(), "Jar") && !Objects.equals(getPackageType(), "Pack")) {
            System.out.print("Enter type of package(Can, Jar, Pack): ");
            this.packageType = scanner.nextLine();
        }
        System.out.print("Enter coffee term(month): ");
        this.term = scanner.nextInt();
        calcTotalPrice();
        calcGrade();
    }
    public void setInfo(Coffee obj) {
        this.coffeeName = obj.coffeeName;
        this.manufacturer = obj.manufacturer;
        this.sort = obj.sort;
        this.type = obj.type;
        this.totalPrice = obj.totalPrice;
        this.packageType = obj.packageType;
        this.totalWeight = obj.totalWeight;
        this.coffeeWeight = obj.coffeeWeight;
        this.setPackageWeight(obj.getPackageWeight());
        this.term = obj.term;
        this.grade = obj.grade;
    }
    public void showInfo() {
        System.out.println("Coffee name: " + getCoffeeName());
        System.out.println("Manufacturer: " + getManufacturer());
        System.out.println("Sort: " + getSort());
        System.out.println("Type: " + getType());
        System.out.println("Price: " + formatDouble(getTotalPrice()) + " UAH");
        System.out.println("Package type: " + getPackageType());
        if(getTotalWeight() > 1000) {
            double totalWeightKilograms = formatDouble(getTotalWeight() / 1000);
            System.out.println("Weight: " + totalWeightKilograms + " kilograms");
        }
        else {
            System.out.println("Weight: " + formatDouble(getTotalWeight()) + " grams");
        }
        if(getCoffeeWeight() > 1000) {
            double coffeeWeightKilograms = formatDouble(getCoffeeWeight() / 1000);
            System.out.println("Coffee Weight: " + coffeeWeightKilograms + " kilograms");
        }
        else {
            System.out.println("Coffee Weight: " + formatDouble(getCoffeeWeight()) + " grams");
        }
        if(getPackageWeight() > 1000) {
            double packageWeightKilograms = formatDouble(getPackageWeight() / 1000);
            System.out.println("Package Weight: " + packageWeightKilograms + " kilograms");
        }
        else {
            System.out.println("Package Weight: " + formatDouble(getPackageWeight()) + " grams");
        }
        System.out.println("Term: " + getTerm() + " month");
        System.out.println("Grade: " + getGrade());
    }
    public String toWrite() {
        return "Coffee name: " + getCoffeeName() + "\nManufacturer: " + getManufacturer() + "\nSort: " + getSort() + "\nType: " + getType() +
                "\nPrice: " + getTotalPrice() + " UAH" + "\nPackage type: " + getPackageType() + "\nWeight: " + getTotalWeight() + " grams" + "\nCoffee Weight: " +
                getCoffeeWeight() + " grams" + "\nPackage Weight: " + getPackageWeight() + " grams" + "\nTerm: " + getTerm() + " month" + "\nGrade: " + getGrade() + "\n";
    }
}
