package Frame_Database_Application;

// Incomplete ID class for CE203 Assignment 1
// Date: 12/10/2020
// Author: f.Doctor
public class ID implements Comparable<ID>{

    // id attribute
    int id = 000000;

    // constructor should input an ID as a String or int and set it to the attribute id - to be modified
    public ID(int i)
    {
        setID(i);
    }


    // gets a stored ID
    public int getID() {
        return id;
    }


    // sets the input parameter to an ID this can be modified to input a string in which case you will need to convert
    // the parameter to an int
    public void setID(int inputID) {
        id = inputID;
    }


    @Override
    // method used for comparing ID objects based on stored ids, you need to complete the method
    public int compareTo(ID o) {
        if(id<o.getID()){
            return -1;}
        else if(id>o.getID()){
            return 1;}
        else return 0;

    }

    // outputs a string representation of the object
    public String toString()
    {
        return ("ID = "+id);
    }
}
