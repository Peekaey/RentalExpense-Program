

public class Property_19783996 {

    private int propertyID;             // ID Of The Property
    private String street;              // Street Where The Property Is
    private String suburb;              // Suburb Where The Property Is Located
    private String state;               // State The Property Is Located
    private int postcode;               // Postcode Of the Suburb
    private double rent;                // Weekly Rent Fee
    private double managementFee;       // Management Fee
    private int clientID;               // ID Of The Client Who Owns The Property

    //MUTATORS

    public void setpropertyID(int ID) {                         // Sets PropertyID
        propertyID = ID;
    }

    public void setstreet(String propertyStreet) {              // Sets Street Of Property
        street = propertyStreet;
    }

    public void setsuburn(String propertySuburb) {              // Sets Property Suburb
        suburb = propertySuburb;
    }

    public void setstate(String propertyState) {                // Sets State
        state = propertyState;
    }

    public void setpostcode(int propertyPostcode) {             // Sets Postcode
        postcode = propertyPostcode;
    }

    public void setrent(double propertyRent) {                  // Sets Rent
        rent = propertyRent;
    }

    public void setmanagementFee(double Fee) {                  // Sets Management Fee
        managementFee = Fee;
    }

    public void setclientID(int ID) {                           // Sets Client ID
        clientID = ID;
    }

    //ACCESSORS

    public int getpropertyID() {                                // Gets PropertyID
        return propertyID;
    }

    public String getstreet() {                                 // Gets Street
        return street;
    }

    public String getsuburb() {                                 // Gets Suburb
        return suburb;
    }

    public String getstate() {                                  // Gets State
        return state;
    }

    public int getpostcode() {                                  // Gets Post Code
        return postcode;
    }

    public double getrent() {                                   // Gets Rent
        return rent;
    }

    public double getmanagementFee() {                          // Gets Management Fee
        return managementFee;
    }

    public int getclientID() {                                  // Gets Client ID
        return clientID;
    }
}
