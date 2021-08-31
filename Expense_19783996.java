


public class Expense_19783996 {

    private int propertyID;                     // Property ID Of The Expense
    private String expenseDescription;          // Description Of The Expense
    private double expenseAmount;               // Cost Of The Expense
    private String date;                        // Date Of The Expense Incurred

    //MUTATORS

    public void setpropertyID(int ID) {                         // Sets The PropertyID
        propertyID = ID;
    }

    public void setExpenseDescription(String Description) {     // Sets Expense Description
        expenseDescription = Description;
    }

    public void setexpenseAmount(double expense) {              // Sets Expense Amount
        expenseAmount = expense;
    }

    public void setdate(String expenseDate) {                   // Sets Date Of Expense
        date = expenseDate;
    }

    //ACCESSORS

    public int getpropertyID() {                                // Gets Property ID
        return propertyID;
    }

    public String getexpenseDescription() {                     // Gets Expense Description
        return expenseDescription;
    }

    public double getexpenseAmount() {                          // Gets Expense Amount
        return expenseAmount;
    }

    public String getdate() {                                   // Gets Date
        return date;
    }
    
}
