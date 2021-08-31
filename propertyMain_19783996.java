
//Reads Several Text Files Into An Array, And Allows Users To Add Extra Data To Those Arrays And Write It Back On Top Of Existing Information 
//In The Text File. Program Is Also Capable Of Reading And Displaying Portfolio Reports Of The Data In The Array


import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;    
import java.util.Collections;
import java.util.List;


public class propertyMain_19783996 {
   
    static  Scanner keyboard = new Scanner(System.in);                                          // Imports The Scanner Class To Read Keyboard Inputs
    public static void main(String[] args) {

        propertyMain_19783996 main = new propertyMain_19783996();                              // Initialising This Class As An Object

        ArrayList<Property_19783996> Property = new ArrayList<Property_19783996>();            // Initialising Property Class File As An ArrayList
        ArrayList<Rent_19783996> Rent = new ArrayList<Rent_19783996>();                        // Initialising Rent Class File As An ArrayList
        ArrayList<Expense_19783996> Expense = new ArrayList<Expense_19783996>();               // Initialising Expense Class File As An ArrayList
        ArrayList<Client_19783996> Client = new ArrayList<Client_19783996>();                  // Initialising Client Class File As An ArrayList

        String propertyName = "properties.txt";                                                // PlaceHolder Name For The Property Text File
        String rentName = "rents.txt";                                                         // PlaceHolder Name For The Rent Text File
        String expenseName = "expenses.txt";                                                   // PlaceHolder Name For The Expense Text File
        String clientName = "clients.txt";                                                     // PlaceHolder Name For The Client Text File

        int answer = 10;                                                                       // Menu Input

        boolean unchangedsavestate = true;                                                     // Boolean For Whenever Array Has Been Changed Or Not

        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");              // Date Formatter
        LocalDateTime now = LocalDateTime.now();                                               // Calling Local Date Time

        // Tests To See If All Files Exist, If ! Asks User For Input And Will Then Override The File Name Of Each Type

        File propertycheck = new File(propertyName);
        while (!propertycheck.isFile()) {
            System.out.println("Property File Does Not Exist, Please Enter A New File Name");
            propertycheck = new File (keyboard.next() + ".txt");
        } 
        if (propertycheck.isFile()) {                                                          // If File Exists, Override Default File Name
            propertyName = propertycheck.toString();
        }

        File rentcheck = new File(rentName);
        while (!propertycheck.isFile()) {
            System.out.println("Rent File Does Not Exist, Please Enter A New File Name");
            rentcheck = new File (keyboard.next() + ".txt");
        } 
        if (rentcheck.isFile()) {                                                              // If File Exists, Override Default File Name
            rentName = rentcheck.toString();
        }

        File expensecheck = new File(expenseName);
        while (!expensecheck.isFile()) {
            System.out.println("Expense File Does Not Exist, Please Enter A New File Name");
            expensecheck = new File (keyboard.next() + ".txt");
        } 
        if (expensecheck.exists()) {                                                           // If File Exists, Override Default File Name
            expenseName = expensecheck.toString();
        }

        File clientcheck = new File(clientName);
        while (!clientcheck.isFile()) {
            System.out.println("Client File Does Not Exist, Please Enter A New File Name");
            clientcheck = new File (keyboard.next() + ".txt");
        } 
        if (clientcheck.exists()) {                                                            // If File Exists, Override Default File Name
            clientName = clientcheck.toString();
        }

        // Reads All Text Files Into An ARray

        main.readProperty(propertyName,Property);
        main.readRent(rentName, Rent);
        main.readExpense(expenseName, Expense);
        main.readClient(clientName, Client);

        // Menu Responses

        while (answer != 6) {

        answer = main.Menu(answer);  // Calls Menu And User Input
        
            if (answer == 1) {
            unchangedsavestate =  main.recordRentCollection(Rent, Property, Client,  now , dateformat, unchangedsavestate );       // If Selected, Records Rent Collection

            }

            if (answer == 2) {
            unchangedsavestate =   main.recordExpense(Expense, Rent, Property, Client, now , dateformat, unchangedsavestate);  // if Selected, Records Expense
            }

            if (answer == 3) {
                main.portfolioReport(Property, Rent, Client, Expense);                                                                      // If Select Displays Portfolio Report
            }

            if (answer == 4) {                                                                                                              // If Selected, Will Save Whats In The Array To The Text Files
                main.Save(rentName, expenseName, Rent, Expense);
            }

            if (answer == 5) {                                                                                                              // If Selected Exits The Program Depending Upon If Array is Changed Or Not

                if (unchangedsavestate == false) {
                    System.out.println("You Haven't Saved Your Data Yet , Would You Like To Return Back To The Menu? ");
                    System.out.println("Please Enter 1 To Return To The Main");
                    System.out.println("Please Enter 2 If You Would Still Like To Exit The Program");

                    int exitanswer;   

                    do {                                                                                                                    // Validates If File Is Between Appropriate Range, And An Integer
                        System.out.println("Please Enter An Option Between 1-2");
                        while (!keyboard.hasNextInt()) {
                            System.out.println("Invalid String Entered, Please Input A Number Instead");
                            keyboard.next();
                        }
                        exitanswer = keyboard.nextInt();
                    } while (exitanswer < 1 || exitanswer > 2);

                    if (exitanswer == 2) {                                                                                                  // If Selected And Array Was Change, Exits Program Without Saving Anything
                        answer = 6;
                    }
                }

                     if (unchangedsavestate == true) {                                                                                      // If Selected And Array Wasn't Changed, Exits Program
                        answer = 6;
                    }
                System.out.println("");
        }
    }
    }    

    // Menu

    public int Menu(int answer) {                                                                                                           // Prints Out Menu
        System.out.println("\t");
        System.out.println("Please Enter What You Would Like To Do:");
        System.out.println("Press 1 If You Would Like To Record Rent Collection:");
        System.out.println("Press 2 If You Would Like To Record Expense:");
        System.out.println("Press 3 If You Would Like To Generate Portfolio Report");
        System.out.println("Press 4 If You Would Like To Save");
        System.out.println("Press 5 If You Would Like To Exit The Program");

        // Validates Input

        do {                                                                                                                                
            System.out.println("Please Enter An Option Between 1-5");                       
        
            while (!keyboard.hasNextInt()) {
                System.out.println("Invalid String Entered, Please Input A Number Instead");
                keyboard.next(); 
            }
            answer = keyboard.nextInt();
        } while (answer < 1 || answer > 5);

        // Returns Input

        return answer;
    }


    /**
     * If Option 1 (Record Rent Collection) is Selected, Will Run This Method Which Allows The Search To Search For A Specific Property,
     * @param Rent                   // ArrayList Of Rent
     * @param Property               // ArrayList Of Property
     * @param Client                 // ArrayList Of Client
     * @param now                    // Name Of LocalDateTime Function
     * @param dateformat             // Name Of DateTime Function Formatter
     * @param unchangedsavestate     // Boolean For If ArrayList Was Changed
     * @return
     */

    public boolean recordRentCollection(ArrayList<Rent_19783996> Rent, ArrayList<Property_19783996> Property, ArrayList<Client_19783996> Client, LocalDateTime now, DateTimeFormatter dateformat, boolean unchangedsavestate) {

        keyboard.nextLine();    // Eating Empty Line From Scanner

        ArrayList<Integer> PropRentSearchIndex = new ArrayList<Integer>();                                    // Declares Array That The Search Results Will Be Stored

        System.out.println("Please Enter The Address Of The Property You Would Like To Record Rent For:");          
        String answeraddress = keyboard.nextLine();

        boolean addressexists = false;                                                                        //Boolean For If Property Exists Or Not
        int searchnumber = -1;                                                                                // Variable For Amount Of Property Matches                            

        for (int i = 0; i < Property.size(); i++) {                                                           // Searches For Address And If It Exists, Returns True And Allows Rest To Execute
            if (Property.get(i).getstreet().toLowerCase().contains(answeraddress.toLowerCase())) {
                 addressexists = true;
            } 
        }

        if (addressexists == true) {                                                                          // If Address Is True Will Execute Rest Of Method
        System.out.println("Displaying Search Results");
        System.out.println("\t");

        int indexsearch = 0;

        for (int i = 0; i < Property.size(); i++) {                                                           // Searches For Address, Adds To An ArrayList While Displaying
            if (Property.get(i).getstreet().toLowerCase().contains(answeraddress.toLowerCase())) {
                searchnumber++;
                System.out.println("Search Result: " + searchnumber + ": " + "Street: " + Property.get(i).getstreet() );
                System.out.println("\t");
                int index = i;                          
                PropRentSearchIndex.add(index);         // PropRentSearchIndex Stores Each Index Of Property And Allows For Easier Validation And Easier Selection
            
            } 
        }

        System.out.println("Please Select Your Property");
        int addressnumber = 0;

        do {                                                                                    // Validates Appropriate Property Pick As Well As If It Is An Integer
        System.out.println("Please Select From 0 - " + searchnumber);
            while (!keyboard.hasNextInt()) {
                System.out.println("Invalid String Entered, Please Input A Number Instead");
                keyboard.next();
            }
            addressnumber = keyboard.nextInt();
        } while (addressnumber < 0 || addressnumber > searchnumber);


        int streetindexvalue = PropRentSearchIndex.get(addressnumber) ;                        // Stores Property Index
    
        int clientNameIndex = 0;                                                               // Variable To Store Index Value Of Client Name For Chosen Property
        int propclientID = Property.get(streetindexvalue).getclientID() ;                      // Stores Index Value Of Client Name From Chosen Property By Using ClientID's As A Foreign Key
        for (int i = 0; i < Client.size(); i++) {
            if (Client.get(i).getclientID() == propclientID) {
                clientNameIndex = i;
            }
        }

        // Dispalying Chosen Address Information
        System.out.println("Street Address: " + Property.get(streetindexvalue).getstreet());
        System.out.println("Suburb: " + Property.get(streetindexvalue).getsuburb());
        System.out.println("State: " + Property.get(streetindexvalue).getstate());
        System.out.println("Postcode: " + Property.get(streetindexvalue).getpostcode());
        System.out.println("Weekly Rent Charged: " + Property.get(streetindexvalue).getrent());
        System.out.println("Property Owner Name: " + Client.get(clientNameIndex).getname());
        System.out.println("\t");

        //Asks User How Many Weeks Of Rent They Would Like To Record As Well as Validate That Input for Integers/Appropriate Value
        int rentweeks = 0;
        System.out.print("How Many Weeks Worth Of Rent Would You Like To Record?");
        System.out.println("\t");
 
        do {
            System.out.println("Please Enter Amount Of Weeks Greater Than 0");
             while (!keyboard.hasNextInt()) {
            System.out.println("Invalid String Entered, Please Input A Number Instead");
            keyboard.next();
        }
        rentweeks = keyboard.nextInt();
        } while (rentweeks <= 0);

    
       Double renttotal = Property.get(streetindexvalue).getrent() * rentweeks;

       // On Screen Summary Report
       System.out.println("\t");
       System.out.println("Rent Transaction For:");
       System.out.println(Property.get(streetindexvalue).getstreet());
       System.out.println("-------------------------------------");
       System.out.println("Monetary Collected: " + renttotal);
       System.out.println("Weeks Collected: " + rentweeks);
       System.out.println("Property Owner's Name: " + Client.get(clientNameIndex).getname());
       System.out.println("Current Date: " + dateformat.format(now));
       System.out.println("\t");

        // Writing To The Rent Array

      int propID = Property.get(indexsearch).getpropertyID();

        // Saves Rent Collection To ArrayList

        System.out.println("Saving Rent Record Collection To Memory.....");
        System.out.println("\t");
        
      for (int i = 0; i < 1; i++) {
                Rent_19783996 num = new Rent_19783996();        // Creates New Instance Of The Rent Class File

                num.setpropertyID(propID);
                num.setrentAmount(renttotal);
                num.setdate(dateformat.format(now));
                Rent.add(num);                                  // Adds New Instance Into Existing Instance Of ArrayList Class FIle
            }
            unchangedsavestate = false;                         // Returns False To Show That The User Has Ovewritten Data In The ArrayList
    }
        else if (addressexists == false) {                      // If Address Doesn't Exist Will Return Notify User And Return To Main Menu
            System.out.println("\t");
            System.out.println("Address Does Not Exist");
            System.out.println("Returning To The Main Menu.......");
            System.out.println("\t");
        }
        return unchangedsavestate;
    }

    /**
     *  If Option 2 (Record Expense) Is Selected, Will Allow User To Search For A Specific Property
     * @param Expense               // ArrayList Of Expenses
     * @param Rent                  // ArrayList Of Rent
     * @param Property              // ArrayList Of Property
     * @param Client                // ArrayList Of Client
     * @param now                   // Function Of LocalDateTime
     * @param dateformat            // LocalDateTime Formatter
     * @param unchangedsavestate    // Boolean To Determine If Array Has Been Altered Or Not
     * @return
     */
    public boolean recordExpense(ArrayList<Expense_19783996> Expense, ArrayList<Rent_19783996> Rent, ArrayList<Property_19783996> Property, ArrayList<Client_19783996> Client, LocalDateTime now, DateTimeFormatter dateformat, boolean unchangedsavestate) {

        keyboard.nextLine();    // Eating Empty Line From Scanner

        ArrayList<Integer> PropExpenseSearchIndex = new ArrayList<Integer>();                                                           //Initialises ArrayList Of Property Search Index

        System.out.println("Please Enter The Address Of The Property You Would Like To Record Expenses For:");                          
        String answeraddress = keyboard.nextLine();                                                                         

        boolean addressexists = false;                      // Boolean For If Address Exists Or Not
        int searchresult = -1;                              // Search Results Printed Out

        for (int i = 0; i < Property.size(); i++) {                                                           // Searches For Address And Returns True/False If It Exists
            if (Property.get(i).getstreet().toLowerCase().contains(answeraddress.toLowerCase())) {
                addressexists = true;
                    }
                }

                System.out.println("\t");
             
        if (addressexists == true) {                                                                          // If Address Exists, Executes Rest Of Method                                                                           
            
            System.out.println("Displaying Search Results");
            System.out.println("\t");

            for (int i = 0; i < Property.size(); i++) {
                if (Property.get(i).getstreet().toLowerCase().contains(answeraddress.toLowerCase())) {
                    searchresult++;
                    System.out.println("Search Result: " + searchresult + ": " + "Street: " + Property.get(i).getstreet() );
                    System.out.println("\t");
                    int index = i;
                    PropExpenseSearchIndex.add(index);                                                                              // Saves To Property Search Result Index
            }
        }
            System.out.println("Please Select Your Property");                                  
            int addressnumber = 0;
                         
            do {
                System.out.println("Please Select From 0 To " + searchresult);                                                     // Allows User To Input And Verify
                while (!keyboard.hasNextInt()) {
                    System.out.println("Invalid String Entered, Please Input A Number Instead");
                    keyboard.next();
                } 
                addressnumber = keyboard.nextInt();
            } while (addressnumber < 0 || addressnumber > searchresult);


            int streetindexvalue = PropExpenseSearchIndex.get(addressnumber);                                                       // Stores The Index Of The Chosen Property
            int clientNameIndex = 0;             

            int propclientID = Property.get(streetindexvalue).getclientID();                                                        // Matches Property With Client To Obtain Client Name Via Matching With Client ID's
            for (int i = 0; i < Client.size(); i ++) {
                if (Client.get(i).getclientID() == propclientID) {
                    clientNameIndex = i;
                }
            }

            //Displaying Chosen Address Information
            System.out.println("Street Address: " + Property.get(streetindexvalue).getstreet());
            System.out.println("Suburb: " + Property.get(streetindexvalue).getsuburb());
            System.out.println("State: " + Property.get(streetindexvalue).getstate());
            System.out.println("Postcode: " + Property.get(streetindexvalue).getpostcode());
            System.out.println("Weekly Rent Charged:" + Property.get(streetindexvalue).getrent());
            System.out.println("Property Owner name: " + Client.get(clientNameIndex).getname());
            System.out.println("\t");

            keyboard.nextLine();    // Eating Empty Line From Scanner

            System.out.println("Please Enter The Expense Description");                                                            // User Enters Expense Description
            String expensedesc = keyboard.nextLine();

            System.out.println("Please Enter The Expense Amount");                                                                 // User Enters Expense Total
            double expensetotal = 0;

            do {                                                                                                                   // Validates User Input For Total Expense
                System.out.println("Please Enter A Valid Number");
                while (!keyboard.hasNextDouble()) {
                    System.out.println("Invalid String Entered, Please Input A Number Instead");
                    keyboard.next();
                }
                expensetotal = keyboard.nextDouble();
            } while (expensetotal < 0);
            
            // On Screen Summary Report
                System.out.println("\t");
                System.out.println("Rent Transaction For:");
                System.out.println(Property.get(streetindexvalue).getstreet());
                System.out.println("-------------------------------------");
                System.out.println("Monetary Amount: " + expensetotal);
                System.out.println("Expense Description: " + expensedesc);
                System.out.println("Property Owner's Name: " + Client.get(clientNameIndex).getname());
                System.out.println("Current Date: " + dateformat.format(now));
                System.out.println("\t");

                System.out.println("Saving Property Expense  To Memory.....");
                System.out.println("\t");

                int propID = Property.get(streetindexvalue).getpropertyID();                                                     // Creates New Instance Of The Epxense Class File

                        Expense_19783996 expenses = new Expense_19783996();
        
                        expenses.setpropertyID(propID);
                        expenses.setExpenseDescription(expensedesc);
                        expenses.setexpenseAmount(expensetotal);
                        expenses.setdate(dateformat.format(now));
                        Expense.add(expenses);                                                                                   // Adds New Instance Into Existing Instance Of ArrayList Class File
            
                    unchangedsavestate = false;                                                                                  // Returns False To Show That The User Has Overwritten Data In The ArrayList

        } else if (addressexists == false) {                                                                                    // If Address Doesn't Exist Will Notify User And Return To Main Menu
            System.out.println("\t");
            System.out.println("Address Does Not Exist");
            System.out.println("Returning To The Main Menu.......");
            System.out.println("\t");
        }
        return unchangedsavestate;
    }

    /**
     * If Option (Portfolio Report) 3 Is Selected Exectures Method
     * @param Property      // ArrayList Of Property Class File
     * @param Rent          // ArrayList Of Rent Class File
     * @param Client        // ArrayList Of Client Class File
     * @param Expense       // ArrayList Of Expense Class File
     */
    public void portfolioReport(ArrayList<Property_19783996> Property, ArrayList<Rent_19783996> Rent, ArrayList<Client_19783996> Client, ArrayList<Expense_19783996> Expense) {
      
            //Extracting Dates

        DateTimeFormatter reportdate = DateTimeFormatter.ofPattern("EEEEE MMMM dd HH:mm:ss yyyy");      // Sets Format Of Date
        LocalDateTime reportnow = LocalDateTime.now();                                                  // Function For Local Date Time

        String address;                      // Variable For Storing Full Address

        double rentsum = 0;                 // Variable For Storing Sum From Rent Collected
        double rentsumtotal = 0;            // Variable For Storing Rolling Total Of Sum
        
        double expensesum = 0;              // Variable For Storing Sum From Expense Collected
        double expensesumtotal = 0;         // Variable For Storing Rolling Total Of Expense

        double feerate = 0;                 // Variable For Storing Management Fee Rate

        double fees = 0;                    // Variable For Storing Total Of Rent + Expense + Fee
        double feestotal = 0;               // Variable To Store Total Cost Of Fees

        double netincome = 0;               // Variable For Storing Net Income Per Property
        double netincometotal = 0;          // Variable For Storing Net Income Per Client

        System.out.println("\t");
        System.out.println("Please Select An Action From The Corresponding Options");
        System.out.println("Press 1 To Generate A Portfolio Report For a Specific User");
        System.out.println("Press 2 To Generate A Portfolio Report For All Clients");
        System.out.println("Press 3 To Generate A Portfolio Report For All Properties In a Specified Postcode");
        int userMenuInput = 0;

        do {                                                                                // Verify's Menu Option
            System.out.println("Please Enter An Option Between 1-3");
         while (!keyboard.hasNextInt()) {
            System.out.println("Invalid String Entered, Please Input A Number Instead");
            keyboard.next();
        }
        userMenuInput = keyboard.nextInt();
        } while (userMenuInput < 1 || userMenuInput > 3 );

            //Executes If Menu Option 1 Is Selected
        if (userMenuInput == 1) {
            keyboard.nextLine();    // Eating Empty Line From Scanner

            System.out.println("Please Enter A Specific User You Would Like To Display A Portfolio Report For:? ");
            String inputuser = keyboard.nextLine();

           boolean clientnameexists = false;                                                        // Boolean For If Client Exist

           for (int i = 0; i < Client.size(); i++) {                                                // Searches For Name And If Exists, Returns True
               if (Client.get(i).getname().toLowerCase().contains(inputuser.toLowerCase())) {
                   clientnameexists = true;
               }
           }
           // Executes If Name Exists
           if (clientnameexists == true) {

                System.out.println("Displaying Portfolio Report(s)");
                System.out.println("\t");

                for (int i = 0; i < Client.size(); i++) {                                                    // Searches For Matches Of Index
                    if (Client.get(i).getname().toLowerCase().contains(inputuser.toLowerCase())) {
                        System.out.println("\t");
                        System.out.println("\t");
                        System.out.println("PORTFOLIO REPORT");
                        System.out.println("Client: " + Client.get(i).getname() + "," + Client.get(i).getstreet() + " " + Client.get(i).getsuburb() + " " + Client.get(i).getstate() + " " + Client.get(i).getpostcode());
                        System.out.println("Report Generated: " + reportdate.format(reportnow) );
                        System.out.println("\t");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");

                        System.out.format("\n"+"%-55s"+"%-23s"+"%-23s"+ "%-23s"+ "%-23s"+ "%-23s", "Property ", "Rent  ", "Expenses ", "Fee Rate  ", "Fees  ", "Net  \n");
                        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------");
                        
                        int clienttopropertymatch = Client.get(i).getclientID();                             // Stores Client ID To Search Property/Expense/Rent File

                        for (int p = 0; p < Property.size(); p++) {    
                                                                   // Ping Match
                            if (Property.get(p).getclientID() == clienttopropertymatch) {                             // If Match

                                address = Property.get(p).getstreet() + " " + Property.get(p).getsuburb() + " " + Property.get(p).getstate() + " " +Property.get(p).getpostcode();          // Store Address
                                feerate = Property.get(p).getmanagementFee();                                                                                                               // Store Fee Rate

                                for (int r = 0; r < Rent.size(); r++) {                                                                         // Searches Rent File                                               
                                    if (Property.get(p).getpropertyID() == Rent.get(r).getpropertyID())  {
                                        rentsum = rentsum + Rent.get(r).getrentAmount();                                                        // When Matches Store Rent

                                    }
                                }
                                for (int e = 0; e < Expense.size(); e++) {                                                                      // Searches Expense File
                                    if (Property.get(p).getpropertyID() == Expense.get(e).getpropertyID()) {
                                        expensesum = expensesum + Expense.get(e).getexpenseAmount();                                            // When Match Store Expense

                                    }
                                }
                                //Does Some Calculations Behind The Scene To Calculate Total
                                rentsumtotal += rentsum;
                                expensesumtotal += expensesum;
                                fees =  rentsum * feerate;
                                feestotal += fees;
                                netincome = rentsum - expensesum - fees;
                                netincometotal = rentsumtotal - expensesumtotal - feestotal;

                                        // Displaying 2nd Part Of Report

                                System.out.format("\n"+"%-55s"+"%-23s"+"%-23s"+ "%-23s"+ "%-23s"+ "%-23s", address, rentsum ,expensesum, feerate, fees, netincome, "\n");
                                    }
                                        }
                                        System.out.format("\n"+"%-55s"+"%-23s"+"%-23s"+ "%-23s"+ "%-23s"+ "%-23s", "TOTAL", rentsumtotal, expensesumtotal, " ",  feestotal, netincometotal ,"\n");
                                        System.out.println("\t");

                                        // Resets Variables To Zero
                                        rentsum = 0;
                                        rentsumtotal = 0;
                                        expensesum = 0;
                                        expensesumtotal = 0;
                                        feerate = 0;
                                        fees = 0;
                                        feestotal = 0;
                                        netincome = 0;
                                        netincometotal = 0;
                                }
                    }
                }
                    // If Client Name Doesn't Exist, Notify User And Return To Main Menu
                else if (clientnameexists == false) {
                    System.out.println("Client Name Does Not Exist");
                    System.out.println("Returning To Main Menu.....");
                }
            }
                if (userMenuInput == 3) {                                                               // If Menu Option 3 Is Selected

                    System.out.println("Please Select A Postcode To Display Portfolio Reports For"); 
                        int postcode = keyboard.nextInt();

                        boolean postcodecheck = false;

                        for (int i = 0; i < Property.size(); i++) {                                     // Checks If Postcode Is In System, If Not Return To Main Menu
                            if (Property.get(i).getpostcode() == postcode) {
                                postcodecheck = true;
                            }
                        }
                        if (postcodecheck == false) {                                                   // If Postcode Doesn't Exist
                            System.out.println("\t");
                            System.out.println("Post Code Does Not Exist In The System....");
                            System.out.println("Returning To Main Menu....");
                        }
                        if (postcodecheck == true) {                                                    // Postcode Exists
                            for (int i = 0; i < Property.size(); i++) {
                                if (Property.get(i).getpostcode() == postcode) {
                                    
                                    for (int c = 0; c < Client.size(); c++) {
                                        if (Client.get(c).getclientID() == Property.get(i).getclientID()) {
                                            System.out.println("\t");
                                            System.out.println("\t");
                                            System.out.println("PORTFOLIO REPORT");
                                            System.out.println("Client: " + Client.get(c).getname() + "," + Client.get(c).getstreet() + " " + Client.get(c).getsuburb() + " " + Client.get(c).getstate() + " " + Client.get(c).getpostcode());
                                            System.out.println("Report Generated: " + reportdate.format(reportnow) );
                                            System.out.println("\t");
                                            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
                                            System.out.format("\n"+"%-55s"+"%-23s"+"%-23s"+ "%-23s"+ "%-23s"+ "%-23s", "Property ", "Rent  ", "Expenses ", "Fee Rate  ", "Fees  ", "Net  \n");
                                            System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------");

                                            for (int r = 0; r < Rent.size(); r++) {                                         // Searches Rent Array
                                                if (Rent.get(r).getpropertyID() == Property.get(i).getpropertyID()) {
                                                    rentsum = rentsum + Rent.get(r).getrentAmount();                        // If Match Store Rent Sum
                                                }
                                            }
                                            for (int e = 0; e < Expense.size(); e++) {                                      // Searches Expense File                              
                                                if (Expense.get(e).getpropertyID() == Property.get(i).getpropertyID()) {
                                                    expensesum = expensesum + Expense.get(e).getexpenseAmount();            // If Match Store Expense Sum
                                                }
                                            }
                                            address = Property.get(i).getstreet() + " " + Property.get(i).getsuburb() + " " + Property.get(i).getstate() + " " + Property.get(i).getpostcode();     // Stores Full Address

                                            //Does Calculations Behind Scence
                                            rentsumtotal += rentsum;
                                            expensesumtotal += expensesum;
                                            fees = rentsum * feerate;
                                            feestotal += fees;
                                            netincome = rentsum - expensesum - fees;
                                            netincometotal = rentsumtotal - expensesumtotal - feestotal;
                                            System.out.format("\n"+"%-55s"+"%-23s"+"%-23s"+ "%-23s"+ "%-23s"+ "%-23s",address, rentsum ,expensesum, feerate, fees, netincome, "\n");            // Prints Out Second Part Of Report
                                        }
                                    }
                                    System.out.format("\n"+"%-55s"+"%-23s"+"%-23s"+ "%-23s"+ "%-23s"+ "%-23s", "TOTAL", rentsumtotal, expensesumtotal, " ",  feestotal, netincometotal ,"\n");
                                    System.out.println("\t");
                                    // Wipes Variables Back To 0
                                    rentsum = 0;
                                    rentsumtotal = 0;
                                    expensesum = 0;
                                    expensesumtotal = 0;
                                    feerate = 0;
                                    fees = 0;
                                    feestotal = 0;
                                    netincome = 0;
                                    netincometotal = 0;
                                }
                            }
                        }
                    }
                        // Executes This Segment If 3 Is Selected
                    if (userMenuInput == 2) {
                        System.out.println("Displaying All Clients");
       
                        List<String> clientCopy = new ArrayList<String>();  // Creates A Copy Of The Last Names Of Clients To Sort
                        boolean duplicate = false;                          // Checks If A Duplicate Name Exists
                        String checker;                                     // Stores New Client Name
                        String checker2;                                    // Stores Backup Of Client Name
                        boolean existExpense = false;                       // If Expenses Exist For A Property

                        for (int i = 0; i < Client.size(); i++) {           // Runs Length Of Client Size, And Splits Last Name From First Name And Stores Last 5

                            String tosplit = Client.get(i).getname();       // Gets First Name
                            String[] split = tosplit.split(" ");
                            String lname = split[1];
                            clientCopy.add(lname);                          // Adds To The Client Copy Array
                        }
                        Collections.sort(clientCopy);                       // Sorts The Array In Alphabetical Order

                        checker2 = (String)clientCopy.get(10);              // Initialises A Name
                        
                        for (int i = 0; i < clientCopy.size(); i++) {       // Runs Length Of Client Copy Array Length

                            checker = (String)clientCopy.get(i);            // Gets Name

                            if (checker.equals(checker2)) {
                                duplicate = true;
                            }

                            if (duplicate == false) {                           // The New Name Last Name Doesn't Match Old Last Name, Will Run
                                checker2 = (String)clientCopy.get(i);           

                            for (int c = 0; c < Client.size(); c++) {                          
                                if (Client.get(c).getname().contains(clientCopy.get(i))) {          // I know that if the first name is a last name it will mess up :(

                                    System.out.println("\t");
                                    System.out.println("\t");
                                    System.out.println("PORTFOLIO REPORT");
                                    System.out.println("Client: " + Client.get(c).getname() + "," + Client.get(c).getstreet() + " " + Client.get(c).getsuburb() + " " + Client.get(c).getstate() + " " + Client.get(c).getpostcode());
                                    System.out.println("Report Generated: " + reportdate.format(reportnow) );
                                    System.out.println("\t");
                                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
                                    System.out.format("\n"+"%-55s"+"%-23s"+"%-23s"+ "%-23s"+ "%-23s"+ "%-23s", "Property ", "Rent  ", "Expenses ", "Fee Rate  ", "Fees  ", "Net  \n");
                                    System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------");
                                    existExpense = false;   

                                    for (int p = 0; p < Property.size(); p++) {                                         // Runs For The Length Of Property And Checks If Expense Exists                                     
                                        if  (Property.get(p).getclientID() == Client.get(c).getclientID()) {
                                            address = Property.get(p).getstreet() + " " + Property.get(p).getsuburb() + " " + Property.get(p).getstate() + " " +Property.get(p).getpostcode();      // Stores Full Addres
                                            feerate = Property.get(p).getmanagementFee();                                                                                                           // Stores Management Fee
                                        
                                            existExpense = true;   // Sets Expense To True

                                        for (int r = 0; r < Rent.size(); r++) {                                         // Runs For Length Of Rent File
                                            if (Property.get(p).getpropertyID() == Rent.get(r).getpropertyID())  {      // if Match
                                                rentsum = rentsum + Rent.get(r).getrentAmount();                        // Store Rent Sum
                                            }
                                        }

                                        for (int e = 0; e < Expense.size(); e++) {                                     // Runs For Length Of Rent File
                                            if (Property.get(p).getpropertyID() == Expense.get(e).getpropertyID()) {   // If Match
                                                expensesum = expensesum + Expense.get(e).getexpenseAmount();           // Store Rent Sum
                                            }
                                                }
                                                // Calculations Behind The Scene
                                                rentsumtotal += rentsum;
                                                expensesumtotal += expensesum;
                                                fees =  rentsum * feerate;
                                                feestotal += fees;
                                                netincome = rentsum - expensesum - fees;
                                                netincometotal = rentsumtotal - expensesumtotal - feestotal;

                                                // Will Only Execute The 2nd Half Of The Report If Expense Is Found

                                                if (existExpense == true) {
                                                System.out.format("\n"+"%-55s"+"%-23s"+"%-23s"+ "%-23s"+ "%-23s"+ "%-23s", address, rentsum ,expensesum, feerate, fees, netincome, "\n");
                                                }
                                                }
                                                }
                                                if (existExpense == true) {
                                                System.out.format("\n"+"%-55s"+"%-23s"+"%-23s"+ "%-23s"+ "%-23s"+ "%-23.2f", "TOTAL", rentsumtotal, expensesumtotal, " ",  feestotal, netincometotal ,"\n");
                                                }
                                                System.out.println("\t");

                                                //Resets To 0
                                                rentsum = 0;
                                                rentsumtotal = 0;
                                                expensesum = 0;
                                                expensesumtotal = 0;
                                                feerate = 0;
                                                fees = 0;
                                                feestotal = 0;
                                                netincome = 0;
                                                netincometotal = 0;
                                                if (existExpense == false) {                                // Will Print This Message If No Expenses Are Found
                                                    System.out.println("No Expenses Can Be Found");
                                                }
                                    }
                                }
                                }
                                if (duplicate == true) {                      // If Names Are Duplicated, Resets To False And Start Cycle Again
                                    duplicate = false;
                                    checker2 = (String)clientCopy.get(i);
                                }
                            }
                            }
                        }
    /**
     * Stores Contents Of Property File To Array
     * @param propertyName  // Stores Name Of File
     * @param Property      // ArrayList Of Property
     * @return
     */              

    public ArrayList<Property_19783996> readProperty(String propertyName, ArrayList<Property_19783996> Property) {

        int count = 0;  // Counter For Array Index

        try {
            File file = new File(propertyName);         // Creates File From File Name
            Scanner Scanner = new Scanner(file);        // Creates File Scanner From File

            while (Scanner.hasNext()) {

                String scan = Scanner.nextLine();
                String scanSplit[] = scan.split(",");

                int arrayadd = 0;

                Property.add(new Property_19783996());
                Property.get(count).setpropertyID(Integer.parseInt(scanSplit[arrayadd]));
                arrayadd++;
                Property.get(count).setstreet(scanSplit[arrayadd]);
                arrayadd++;
                Property.get(count).setsuburn(scanSplit[arrayadd]);
                arrayadd++;
                Property.get(count).setstate(scanSplit[arrayadd]);
                arrayadd++;
                Property.get(count).setpostcode(Integer.parseInt(scanSplit[arrayadd]));
                arrayadd++;
                Property.get(count).setrent(Double.parseDouble(scanSplit[arrayadd]));
                arrayadd++;
                Property.get(count).setmanagementFee(Double.parseDouble(scanSplit[arrayadd]));
                arrayadd++;
                Property.get(count).setclientID(Integer.parseInt(scanSplit[arrayadd]));
                count++;
            }
            Scanner.close();
    } catch (IOException error) {
        System.out.println("Error With Reading Property File");
    }
    return Property;        // Returns Arraylist
}

    /**
     * Reads Contents Of Rent File To ArrayList
     * @param rentName  // Stores Name Of File
     * @param Rent      // ArrayList For Rent
     * @return
     */
    public ArrayList<Rent_19783996> readRent(String rentName, ArrayList<Rent_19783996> Rent) {

        int count = 0;   // Counter For Array Index

        try {
            File file = new File(rentName);         // Creates File From File Name
            Scanner Scanner = new Scanner(file);        // Creates File Scanner From File

            while (Scanner.hasNext()) {

                String scan = Scanner.nextLine();
                String scanSplit[] = scan.split(",");

                int arrayadd = 0;

                Rent.add(new Rent_19783996());
                Rent.get(count).setpropertyID(Integer.parseInt(scanSplit[arrayadd]));
                arrayadd++;
                Rent.get(count).setrentAmount(Double.parseDouble(scanSplit[arrayadd]));
                arrayadd++;
                Rent.get(count).setdate(scanSplit[arrayadd]);
                count++;
            }
            Scanner.close();
            
    } catch (IOException error) {
        System.out.println("Error With Reading Rent File");
    }
        return Rent;
    }

    /**
     * Reads Contents Of Expense File To ArrayList
     * @param expenseName       // Store Name Of File
     * @param Expense           // ArrayList For Rent
     * @return
     */
    public ArrayList<Expense_19783996> readExpense(String expenseName, ArrayList<Expense_19783996> Expense) {

        int count = 0;   // Counter For Array Index

        try {
            File file = new File(expenseName);
            Scanner Scanner = new Scanner(file);        // Creates File Scanner From File

            while (Scanner.hasNext()) {

                String scan = Scanner.nextLine();
                String scanSplit[] = scan.split(",");

                int arrayadd = 0;

                Expense.add(new Expense_19783996());
                Expense.get(count).setpropertyID(Integer.parseInt(scanSplit[arrayadd]));
                arrayadd++;
                Expense.get(count).setExpenseDescription(scanSplit[arrayadd]);
                arrayadd++;
                Expense.get(count).setexpenseAmount(Double.parseDouble(scanSplit[arrayadd]));
                arrayadd++;
                Expense.get(count).setdate(scanSplit[arrayadd]);
                count++;
            }
            Scanner.close();
    } catch (IOException error) {
        System.out.println("Error With Reading Expense File");
    }
        return Expense;
    }

    /**
     * Reads Contents Of Expense File To ArrayList
     * @param clientName    // Store Name Of File
     * @param Client        // ArrayList For Client
     * @return
     */
    public ArrayList<Client_19783996> readClient(String clientName, ArrayList<Client_19783996> Client) {

        int count = 0;   // Counter For Array Index

        try {

            File file = new File(clientName);
            Scanner Scanner = new Scanner(file);        // Creates File Scanner From File

            while (Scanner.hasNext()) {

                String scan = Scanner.nextLine();
                String scanSplit[] = scan.split(",");

                int arrayadd = 0;

                Client.add(new Client_19783996());
                Client.get(count).setclientID(Integer.parseInt(scanSplit[arrayadd]));
                arrayadd++;
                Client.get(count).setName(scanSplit[arrayadd]);
                arrayadd++;
                Client.get(count).setstreet(scanSplit[arrayadd]);
                arrayadd++;
                Client.get(count).setsuburb(scanSplit[arrayadd]);
                arrayadd++;
                Client.get(count).setstate(scanSplit[arrayadd]);
                arrayadd++;
                Client.get(count).setpostcode(Integer.parseInt(scanSplit[arrayadd]));
                count++;
            }
            Scanner.close();
    } catch (IOException error) {
        System.out.println("Error With Reading Client File");
    }
        return Client;
    }

    /**
     * Saves Contents Of Arraylists Into Files
     * @param rentName      // Stores Name Of Rent File
     * @param expenseName   // Stores Name Of Expense File
     * @param Rent          // ArrayList For Rent
     * @param Expense       // ArrayList For Expense
     */

    public void Save(String rentName, String expenseName, ArrayList<Rent_19783996> Rent, ArrayList<Expense_19783996> Expense) {

        // Saves To Expense File
        try {
        PrintWriter expensesfile = new PrintWriter(expenseName);

        for (int i = 0; i < Expense.size(); i++) {

            expensesfile.print(Expense.get(i).getpropertyID());
            expensesfile.print(",");
            expensesfile.print(Expense.get(i).getexpenseDescription());
            expensesfile.print(",");
            expensesfile.print(Expense.get(i).getexpenseAmount());
            expensesfile.print(",");
            expensesfile.println(Expense.get(i).getdate());
        }
            expensesfile.close();
        } catch (IOException error) {
            System.out.println("Error with saving expensefile ");
        }

        // Saves To Rent File
        try {

        PrintWriter rentsfile = new PrintWriter(rentName);

        for (int i = 0; i < Rent.size(); i++) {

            rentsfile.print(Rent.get(i).getpropertyID());
            rentsfile.print(",");
            rentsfile.print(Rent.get(i).getrentAmount());
            rentsfile.print(",");
            rentsfile.println(Rent.get(i).getdate());
        }
            rentsfile.close();
        } catch (IOException error) {
            System.out.println("Error with saving rent ");
        }
    }
}