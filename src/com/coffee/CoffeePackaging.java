package com.coffee;
import com.coffee.Coffee;
import com.coffee.CoffeeDataBase;

public class CoffeePackaging extends CoffeeDataBase {
    private Coffee coffee;
    private int numberOfPackages;
    public void setCoffeeName(String name) { coffee.setCoffeeName(name);}
    public void setPackageType(String packageType) { coffee.setPackageType(packageType); }
    public void setManufacturer(String manufacturer) { coffee.setManufacturer(manufacturer); }
    public void setSort(String sort) { coffee.setSort(sort); }
    public void setType(String type) { coffee.setType(type); }
    public void setTotalPrice(double totalPrice) { coffee.setTotalPrice(totalPrice); }
    public void setCoffeeWeight(double coffeeWeight) { coffee.setCoffeeWeight(coffeeWeight); }
    public void setTotalWeight(double totalWeight) { coffee.setTotalWeight(totalWeight); }
    public void setGrade(double grade) { coffee.setGrade(grade); }
    public void setTerm(int term) { coffee.setTerm(term); }
    public void setPackageWeight(double packageWeight) { coffee.setPackageWeight(packageWeight); }
    public double getGrade() { return coffee.getGrade(); }
    public CoffeePackaging(Coffee coffee, int numberOfPackages) {
        this.coffee = coffee;
        this.numberOfPackages = numberOfPackages;
    }
    public CoffeePackaging() {
        this.coffee = new Coffee();
        this.numberOfPackages = 0;
    }
    public Coffee getCoffee() {
        return coffee;
    }

    public int getNumberOfPackages() {
        return numberOfPackages;
    }

    public void setNumberOfPackages(int numberOfPackages) {
        this.numberOfPackages = numberOfPackages;
    }
    public void setInfo(Coffee coffee, int numberOfPackages) {
        this.coffee.setInfo(coffee);
        this.numberOfPackages = numberOfPackages;
    }
    public void showInfo() {
        coffee.showInfo();
        System.out.println("Number of packages: " + numberOfPackages);
    }
    public String toWrite() {
        return "Coffee name: " + coffee.getCoffeeName() + "\nManufacturer: " + coffee.getManufacturer() + "\nSort: " + coffee.getSort() + "\nType: " +
                coffee.getType() + "\nPrice: " + coffee.getTotalPrice() + " UAH" + "\nPackage type: " + coffee.getPackageType() + "\nWeight: " +
                coffee.getTotalWeight() + " grams" + "\nCoffee Weight: " + coffee.getCoffeeWeight() + " grams" + "\nPackage Weight: " + coffee.getPackageWeight() +
                " grams" + "\nTerm: " + coffee.getTerm() + " month" + "\nGrade: " + coffee.getGrade() + "\nNumber of packages: " + getNumberOfPackages() + "\n";
    }

}
