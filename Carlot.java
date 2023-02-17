import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class CarLot {

    private ArrayList<Car> inventory = new ArrayList<>();

    public ArrayList<Car> getInventory() {
        return this.inventory;
    }

    public void setInventory(ArrayList<Car> inventory) {
        this.inventory = inventory;
    }

    // Accessors
    // Add the following accessor methods:
    // Car findCarByIdentifier(String identifier). Find the car with the specified
    // identifer in the inventory. Return null if the Car is not found

    public Car findCarByIdentifier(String identifier) {
        Car idCar = new Car();
        for (Car car : this.inventory) {
            String carID = car.getID().trim();
            String checkID = identifier.trim();
            if (carID.equals(checkID)) {
                idCar = car;
                break;
            } else {
                idCar = null;
            }
        }
        return idCar;
    }

    // ArrayList<Car> getCarsInOrderOfEntry(). Return an ArrayList of all Cars in
    // the inventory, ordered by their entry into the inventory. This method should
    // return a copy of the inventory, not the inventory itself
    public ArrayList<Car> getCarsInOrderOfEntry() {
        ArrayList<Car> entries = new ArrayList<>(this.inventory);
        return entries;
    }

    // ArrayList<Car> getCardsSortedByMPG(). Return an ArrayList of all Cars in the
    // inventory, sorted by MPG. This method should not sort the inventory, but
    // should instead make a copy of the inventory and sort the copy
    public ArrayList<Car> getCarsSortedByMPG() {
        ArrayList<Car> sortedByMPG = new ArrayList<>(this.inventory);
        selectionSort(sortedByMPG);
        return sortedByMPG;
    }


    /**
     * sort the list by lowest to highest MPG
     */
    private static void selectionSort(ArrayList<Car> sortedByMPG){
        int size = sortedByMPG.size();
        for (int i = 0; i < size - 1; i++) {
            // Find the minimum MPG among the cars
            Car lowestMPG = sortedByMPG.get(i);
            int currentMinIndex = i;

            for (int j = i + 1; j < size; j++) {
                Car two = sortedByMPG.get(j);
                int res = lowestMPG.compareMPG(two);
                if (res == 1) {
                    lowestMPG = two;
                    currentMinIndex = j;
                }
            }

            if (currentMinIndex != i) {
                Car otherCar = sortedByMPG.get(currentMinIndex);
                sortedByMPG.set(currentMinIndex, sortedByMPG.get(i));
                sortedByMPG.set(i, otherCar);
            }
        }
    }

    // Car getCarWithBestMPG(). Return the Car in the inventory with the highest MPG
    public Car getCarWithBestMPG(){
        ArrayList<Car> sorted = getCarsSortedByMPG();
        return sorted.get(sorted.size()-1);
    }

    // Car getCarWithHighestMileage(). Return the Car in the inventory with the highest mileage
    public Car getCarWithHighestMileage(){
        ArrayList<Car> checkMileage = this.inventory;
        Car highestMileage = new Car();
        int highest = 0;
        for(Car car: checkMileage) {
            if(car.getMileage() > highest){
                highest = car.getMileage();
                highestMileage = car;
            } 
        }
        return highestMileage;
    }

    // double getAverageMpg(). Return the average MPG of all Cars in the inventory
    public double getAverageMpg(){
        double average = 0.0;
        int sum = 0;
        ArrayList<Car> carList = this.inventory;
        int size = carList.size();
        for(Car car: carList){
            sum+= car.getMPG();
        }
        average = sum / (double) size;
        return average;
    }

    // double getTotalProfit(). Return the total profit of all cars in the inventory that have been sold
    public double getTotalProfit(){
        double totalProfit = 0.0;
        for(Car car: this.inventory){
            if(car.getIsSold() == "Yes"){
                totalProfit += car.getProfit();
            }
        }
        return totalProfit;
    }

    // Mutators
    // Add the following mutator methods:
    // void addCar(String id, int mileage, int mpg, double cost, double salesPrice).  Add a new Car with the specified id, mileage, mpg, cost, and salesPrice to the inventory

    public void addCar(String id, int mileage, int mpg, double cost, double salesPrice){
        Car newCar = new Car(id, mileage, mpg, cost, salesPrice);
        this.inventory.add(newCar);
    }

    // void sellCar(String identifier, double priceSold ) throws IllegalArgumentException. Sell the Car identified by identirier for the priceSold. If the Car does not exist in the identifier, throw an IllegalArgument Exception with an appropriate error message


    public void sellCar(String identifier, double priceSold) throws IllegalArgumentException {
       
            Car sold = findCarByIdentifier(identifier);
            if(sold != null){
                sold.sellCar(priceSold);
            }
            else{
                throw new IllegalArgumentException("Couldn't find the car " + identifier);
            }   
         }
         
         // Add the method saveToDisk() in the CarLot class.  When executed, this method should save all of the inventory data to a .txt file named "carlot.txt"
         public void saveToDisk() throws IOException {

            File csvFile = new File("carlot.txt");
            if(csvFile.exists()){
                System.out.println("File already exists");
                System.exit(0);
            }
            System.out.println("Writing file ...");
            PrintWriter printer = new PrintWriter(csvFile);
            ArrayList<Car> cars = this.inventory;
            for(Car car : cars){
                String row = car.getID() + "," + car.getMileage() + "," + car.getMPG() + "," + car.getCost() + "," + car.getSalesPrice();
                printer.println(row);
            }
            
            printer.close();
            System.out.println("File written.");
        }

        // Add the method loadFromDisk() to the CarLot class. When executed, this method should load all of the data from the file "carlot.txt"
        public ArrayList<Car> loadFromDisk(){

            this.inventory.clear();
            try {
    
                File file = new File("carlot.txt");
                Scanner input = new Scanner(file);
                System.out.println("Reading file ....");
    
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    String[] lineArray = line.split(",", 5);
                    String id = lineArray[0];
                    int mileage = Integer.parseInt(lineArray[1]);
                    int mpg = Integer.parseInt(lineArray[2]);
                    double cost = Double.parseDouble(lineArray[3]);
                    double salesPrice = Double.parseDouble(lineArray[4]);
                    Car car = new Car(id, mileage, mpg, cost, salesPrice);
                    this.inventory.add(car);
                }
                input.close();
    
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Finished reading ...");
            System.out.println("Inventory loaded from disk: " + this.inventory.toString());

            return this.inventory;
        }
         
         
       
