public class Car{

    // class attributes
    private String id = "";
    private int mileage = 0;
    private int mpg = 0;
    private double cost = 0.0;
    private double salesPrice = 0.0;
    private boolean isSold = false;
    private double priceSold = 0.0;
    private double profit = 0.0;

    // no arguments constructor
    public Car(){}

    /**
     * Constructor with attributes for each individual car
     * @param id
     * @param mileage
     * @param mpg
     * @param cost
     * @param salesPrice
     */
    public Car(String id, int mileage, int mpg, double cost, double salesPrice){
        this.id = id;
        this.mileage = mileage;
        this.mpg = mpg;
        this.cost = cost;
        this.salesPrice = salesPrice;
        
    }

    
    // getters  or accessors for all the attributes
    public String getID(){
        return this.id;
    }

    public int getMileage(){
        return this.mileage;
    }

    public int getMPG(){
        return this.mpg;
    }

    public double getCost(){
        return this.cost;
    }

    public double getSalesPrice(){
        return this.salesPrice;
    }

    public double getPriceSold(){
        return this.priceSold;
    }

    // get string return for the boolean value
    public String getIsSold(){
        if(this.isSold){
            return "Yes";
        }
        else{
            return "No";
        }
    }
    
    public double getProfit(){
        return this.profit;
    }

    // to string method to represent the car
    public String toString(){
        return "Car: " + this.id + ", Mileage: " + this.mileage + ", MPG: " + this.mpg + ", Sold: " + getIsSold() + ", Cost: " + this.cost + ", Selling price: " + this.salesPrice + ", Sold For " + this.priceSold + ", Profit: " + this.profit;
    }

    // setters or mutators
    public void setID(String id){
        this.id = id;
    }

    public void setMileage(int m){
        this.mileage = m;
    }

    public void setMPG(int mpg){
        this.mpg = mpg;
    }

    public void setCost(double cost){
        this.cost = cost; 
    }

    public void setSalesPrice(double sales){
        this.salesPrice = sales;
    }

    public void sellCar(double priceSold){
        this.priceSold = priceSold;
        this.isSold = true;
        this.profit = this.priceSold - this.cost;
    }


  // comparison methods
  /**
   * Compare miles per gallon for both cars
   * @Car instance
   * @return -1 if current car has lesser miles per gallon, 1 if other car has better and 0 if they are equal
   */
    public int compareMPG(Car otherCar){
        if(this.mpg < otherCar.mpg){
            return -1;
        }
        else if(this.mpg == otherCar.mpg){
            return 0;
        }
        else{
            return 1;
        }   
    }

   /**
   * Compare mileage p for both cars
   * @Car instance
   * @return -1 if current car has lesser mileage, 1 if other car has lesser and 0 if they are equal
   */
    public int compareMiles(Car otherCar){
        if(this.mileage < otherCar.mileage){
            return -1;
        }
        else if(this.mileage == otherCar.mileage){
            return 0;
        }
        else{
            return 1;
        }   
    }

    /**
    * Compare sales price for both cars
    * @Car instance
    * @return -1 if current car has lower sales price, 1 if other car has  lower and 0 if they are equal
    */
    public int comparePrice(Car otherCar){
        if(this.salesPrice < otherCar.salesPrice){
            return -1;
        }
        else if(this.salesPrice == otherCar.salesPrice){
            return 0;
        }
        else{
            return 1;
        }   
    }

}
