import java.text.DecimalFormat;

public class Salaried extends Employee // retrieves data from employee class
{

    double payfare; // payfare

    Salaried(String usrname, double sal, int id, double d) { // 3-parameter constructor
        super(usrname, sal, id); // call base class method
        // payfare=Double.parseDouble(sa);
        payfare = d;
    }

    Salaried(String str, int eid, double salary, String current_date, String str1) // 5-parameter constructor
    {
        super();
    }

    public Salaried() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public double getPay() {
        double day_sal = payfare / 24;
        DecimalFormat dt = new DecimalFormat("###.##"); // convert number to two decimal place
        System.out.println("salary in salaried class is: " + dt.format(day_sal));
        return day_sal; // return value
    }

}
