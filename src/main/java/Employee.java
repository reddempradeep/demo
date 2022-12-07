
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

//------------- create Employee class --------

public abstract class Employee {

    // ---------------- declare all variable used in prog-----------

    static String login_name;
    double base_salary;
    int emp_id;
    Date date;
    String data;
    static int nextId = -00001;
    double salary;
    int id1;
    String date2, lname, n_name, password;
    static String temp_name;
    String s_type;
    double pay;

    // ------------- default constructor --------------
    public abstract double getPay();

    public Employee() {
        // TODO Auto-generated constructor stub
    }

    // ------------- constructor of employee class with 3 parameter

    Employee(String l_name, double sal, int e_id, String pass, String type) {
        // --------------assigning values ------------
        login_name = l_name;
        base_salary = sal;
        emp_id = e_id;
        password = pass;
        s_type = type;
    }

    Employee(String l_name, double sal, int e_id) {
        login_name = l_name;
        salary = sal;
        emp_id = e_id;

    }
    // ------ constructor 5 parameter ----------

    public Employee(String uname, int id, double sal, String date1, String new_name, String pass, String type) {

        n_name = new_name;
        lname = uname;
        salary = sal;
        this.id1 = id;
        date2 = date1;
        password = pass;
        s_type = type;
    }

    public void set_s_type(String t) {
        s_type = t;
    }

    public String get_s_type() {
        return s_type;
    }

    public void setsalary(double base_salary) {
        salary = base_salary;
        System.out.print("set in employee" + salary);

    }

    public double getsalary() {
        System.out.println("get method :" + salary);
        return salary;
    }

    public String password() {
        return password;
    }

    // -------- to assign next id ----------

    public void setnextId() {
        nextId++;
    }

    public void id(int id) {
        nextId = id;
    }

    // --------return employee id---------
    public int get_emp_id() {
        return id1;
    }

    // --------return id-----------
    public int getid() {
        return nextId;
    }

    public void setname(String name) {
        n_name = name;
    }

    public String getname1() {
        return n_name;
    }

    // return employee name-------------
    public String getname() {
        return lname;
    }

    // --------- return employee data--------
    public String details() {

        return lname + "   -     " + id1 + "  -   " + salary + "   -    " + date2 + "   -   " + n_name;
    }

    // ------ returning data----------
    public Date setdate() {
        return date;
    }

    // -----to string method---------

    public String toString() {
        return lname + "," + id1 + "," + salary + "," + date2 + "," + n_name + "," + password + "," + s_type + ",";
    }

    public void setpay(double final_Salary) {
        // TODO Auto-generated method stub
        pay = final_Salary;

    }

    public double getpay() {
        return pay;
    }
}

