

public class Client_19783996 {

    private int clientID;           //ID Of The Client
    private String name;            // Name Of The Client
    private String street;          // Street That Client Lives On
    private String suburb;          // Suburb The Client Lives In
    private String state;           // State The Client Lives In
    private int postcode;           // PostCode Of The Suburb

    //MUTATORS

    public void setclientID(int ID) {                   // Sets ClientID
        clientID = ID;
    }

    public void setName(String Clientname) {            // Sets Client Name
        name = Clientname;
    }

    public void setstreet(String clientStreet) {        // Sets Client Street
        street = clientStreet;
    }

    public void setsuburb(String clientSuburb) {        // Sets Suburb
        suburb = clientSuburb;
    }

    public void setstate(String clientState) {          // Sets State
        state = clientState;
    }

    public void setpostcode(int clientPostcode) {       // Sets Postcode
        postcode = clientPostcode;
    }

    //ACCESSORS

    public int getclientID() {                          // Gets ClientID
        return clientID;
    }

    public String getname() {                           // Gets Name
        return name;
    }

    public String getstreet() {                         // Gets Street
        return street;
    }

    public String getsuburb() {                         // Gets Suburb
        return suburb;
    }

    public String getstate() {                          // Gets State
        return state;
    }

    public int getpostcode() {                          // Gets Postcode
        return postcode;
    }
}
