
import java.io.Serializable;

public class concreate extends Employee implements Serializable {
    public concreate() {
        super();
    }

    public concreate(String l_name, double sal, int e_id, String pass, String type) {
        super(l_name, sal, e_id, pass, type);
    }

    public concreate(String uname, int id, double sal, String date1, String new_name, String pass, String type) {
        super(uname, id, sal, date1, new_name, pass, type);
    }

    @Override
    public double getPay() {
        // TODO Auto-generated method stub
        return 0;
    }

}
