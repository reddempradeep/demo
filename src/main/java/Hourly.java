
import java.text.DecimalFormat;
import java.util.Scanner;

public class Hourly extends Employee {
    double pyrate = 10;
    double s;

    Hourly(String uname, int id, double sal, double sa) {
        super(uname, sal, id);
        s = sa;
    }

    Hourly(String str, int eid, double salary, String current_date, String str1, String type) {
        super();

    }

    public Hourly() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public double getPay() {
        Scanner sc = new Scanner(System.in);
        // System.out.println("Enter number of hours: "); // ask for hours
        // double d=Double.parseDouble(s);

        double total = pyrate * s;
        DecimalFormat df = new DecimalFormat("###.##"); // convert number to two decimal place
        System.out.println("total salary is in hourly class is:" + df.format(total));
        return total; // return value

    }

}
