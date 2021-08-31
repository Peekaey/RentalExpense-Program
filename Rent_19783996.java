

public class Rent_19783996 {

    private int propertyID;                 //Property ID For The Rent Collection
    private double rentAmount;              // Amount For Rent Collected
    private String date;                    // Date Of Rent Collected
    
    // MUTATORS

    public void setpropertyID(int ID) {         // Sets Property ID
        propertyID = ID;
    }

    public void setrentAmount(double rent) {    // Sets Rent Amount
        rentAmount = rent;
    }

    public void setdate(String rentDate) {      // Sets Date
        date = rentDate;
    }

    // ACCESSORS

    public int getpropertyID() {                // Gets PropertyID
        return propertyID;
    }

    public double getrentAmount() {             // Gets Rent Amount
        return rentAmount;
    }

    public String getdate() {                   // Gets Date
        return date;
    }

}
