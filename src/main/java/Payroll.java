
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Payroll extends Application {
    public static Button login;
    public static Button submit;
    public static TextField name;
    public static TextField user_name;
    public static TextField sal_type;
    public static TextField salary;
    public static PasswordField re_pass;
    public static PasswordField pass;
    public static Button b1;
    public static Button b2;
    public static Button b3;
    public static Button b4;
    public static Button b5;
    public static Button b6;
    public static RadioButton hr;
    public static RadioButton sal;
    public static TextArea textArea;
    public static TextArea t;
    public static TextField t11;
    static int y1 = 0;
    public static TextField t12;
    static double salary2;
    static Alert alert;
    static int y;

    Label message = new Label();
    int counter = 0;

    static int eid;
    static double final_Salary;
    // static int current_emp_id=0;
    static String data;
    static boolean r = false;
    static boolean s = false;
    static String current_date;
    static int rs;
    static int c = 0;
    static String current_emp_name;
    static int current_emp_id = 0;
    static ArrayList<Employee> emp = new ArrayList<>();
    static ArrayList<String> boss = new ArrayList<>();

    static ArrayList<Employee> remove_emp_list = new ArrayList<>();

    static Employee emp1 = new concreate();
    static String tokens[];
    static int bossid = 0;
    static int no = 0;
    static String s_type = "";
    static String str, str1;
    protected static Object saLary;
    String name1;
    int id_no;
    public static String boss_name = "";
    public static String boss_password = "";
    public static StringBuffer hexString1;
    public static StringBuffer hexString;
    public static int currEmpId= -1;

    public static void main(String[] args) {

        launch(args);
    }

    public static int getpassword(String op, String rp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(op.getBytes());
        int yas = 0;
        byte byteData[] = md.digest();

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            // if(hex.length()==1) hexString.append('0');
            hexString.append(hex);

        }
        MessageDigest md1 = MessageDigest.getInstance("SHA-256");
        md1.update(rp.getBytes());

        byte byteData1[] = md.digest();

        hexString1 = new StringBuffer();
        for (int j = 0; j < byteData.length; j++) {
            String hex1 = Integer.toHexString(0xff & byteData[j]);
            // if(hex1.length()==1) hexString.append('0');
            hexString1.append(hex1);

        }
        System.out.println("hax value of 1:" + hexString.toString());
        System.out.println("hax value of 1:" + hexString1.toString());
        System.out.println("hax value of 1:" + hexString.length());
        System.out.println("hax value of 1:" + hexString.length());

        if (hexString.toString().equals(hexString1.toString())) {
            System.out.println("here");

            yas = 1;

        }
        System.out.println("yas value: " + yas);
        return yas;
    }

    public static void logout(Stage primaryStage, ArrayList<Employee> emp) {
        current_emp_id = emp1.getid(); // take current employee id
        // File file=new File("employee.txt");
        writeToFile(emp);

        // ===============serialization ======================
        ArrayList<Employee> clone_se = new ArrayList();
        clone_se.addAll(emp);

        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream("employee_ser.txt");
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(fo);
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        try {
            oo.writeObject(clone_se); // create object file of emp1
            // oo.close();
            // fo.close();
            System.out.println("done serialization");
        } catch (Exception e) {
            System.out.println("error in serialization");

        }

        // =================== Dserialization of an object ==============
        concreate cd = null;
        FileInputStream f = null;
        try {
            f = new FileInputStream("employee_ser.txt");
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ObjectInputStream out = null;
        try {
            out = new ObjectInputStream(f);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            clone_se = (ArrayList<Employee>) out.readObject();
            // cd=(concreate)out.readObject();
            System.out.println("Object has been deserialized");

        } catch (Exception e) {
            System.out.println("error in deserialization");
        }

    }

    public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        // TODO Auto-generated method stub
        alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();

    }

    public void login_design(Stage primaryStage, ArrayList<Employee> emp) {
        primaryStage.setTitle("JavaFx Assignment 6");
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(pane, 300, 275);
        Text sceneTitle = new Text("Login ");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        pane.add(sceneTitle, 0, 0, 2, 1);
        Label label1 = new Label("Enter user name:");
        pane.add(label1, 0, 1);
        name = new TextField();
        pane.add(name, 1, 1);
        Label label2 = new Label("Password ");
        pane.add(label2, 0, 2);
        pass = new PasswordField();
        pane.add(pass, 1, 2);
        login = new Button("Login");
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().add(login);
        pane.add(hbox, 1, 4);
        primaryStage.setScene(scene);
        primaryStage.show();
        login.setOnAction(new EventHandler<ActionEvent>() {

                              @Override
                              public void handle(ActionEvent t) {

                                  String filename = "employee.txt";
                                  emp.clear();
                                  String strLine = "";
                                  FileInputStream fstream = null;
                                  try {
                                      fstream = new FileInputStream(filename);
                                  } catch (FileNotFoundException e) {
                                      // TODO Auto-generated catch block
                                      e.printStackTrace();
                                  }
                                  BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                                  try {
                                      while ((strLine = br.readLine()) != null) // read data until end of file
                                      {
                                          tokens = strLine.split(","); // spilt data when it find ,
                                          emp.add(new concreate(tokens[0], Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2]),
                                                  tokens[3], tokens[4], tokens[5], tokens[6]));

                                      }
                                      Employee.nextId = Integer.parseInt(tokens[1]);
                                      System.out.println("token numbers: " + Employee.nextId);
                                  } catch (Exception e) {

                                      e.printStackTrace();
                                  }

                                  for (Employee e : emp) {
                                      System.out.println("employee list:" + emp);
                                  }
                                  String name;
                                  String pass;
                                  name = Payroll.name.getText();
                                  pass = Payroll.pass.getText();
                                  String line1;

                                  FileInputStream fstream1 = null;
                                  try {
                                      fstream1 = new FileInputStream(filename);
                                  } catch (FileNotFoundException e) {
                                      // TODO Auto-generated catch block
                                      e.printStackTrace();
                                  }
                                  BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));
                                  try {
                                      line1 = br1.readLine();
                                      tokens = line1.split(",");
                                      boss_name = tokens[0];
                                      boss_password = tokens[5];
                                      System.out.println("boss name" + boss_name);
                                  } catch (IOException e1) {
                                      e1.printStackTrace();
                                  }

                                  System.out.println("boss entry" + boss);
                                  if (name.equals(boss_name) && pass.equals(boss_password)) {

                                      boss_menu(primaryStage, emp);

                                  } else {
                                      current_emp_name = Payroll.name.getText();
                                      boolean flag = false;
                                      for(Employee e:emp) {
                                          if(current_emp_name ==  e.getname()) {
                                              flag = true;
                                          }
                                      }
					/*if(!flag) {
						showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "error", "Invalid user login attempt");
						login_design(primaryStage, emp);
						return;
					}*/

                                      // employee module design
                                      primaryStage.setTitle("JavaFX Assignment 6");
                                      GridPane pane = new GridPane();
                                      pane.setAlignment(Pos.CENTER);
                                      pane.setHgap(10);
                                      pane.setVgap(10);
                                      pane.setPadding(new Insets(25, 25, 25, 25));
                                      Scene scene = new Scene(pane, 300, 275);
                                      Text sceneTitle = new Text("Employee Options ");
                                      sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                                      pane.add(sceneTitle, 0, 0, 2, 1);
                                      Button b = new Button("Details");
                                      Button b1 = new Button("Quit");
                                      b5 = new Button("Logout");
                                      b.setMaxWidth(Double.MAX_VALUE);
                                      b1.setMaxWidth(Double.MAX_VALUE);
                                      b5.setMaxWidth(Double.MAX_VALUE);
                                      VBox hbox = new VBox(6);
                                      hbox.setAlignment(Pos.BOTTOM_RIGHT);
                                      hbox.getChildren().addAll(b, b1, b5);
                                      pane.add(hbox, 1, 4);
                                      primaryStage.setScene(scene);
                                      primaryStage.show();
                                      b.setOnAction(new EventHandler<ActionEvent>() {

                                          @Override
                                          public void handle(ActionEvent t) {
                                              System.out.println("Details View : " + current_emp_name);
                                              StringBuilder sb = new StringBuilder();
                                              sb.append("you details: \n" + "LoginName - Id  - salary - date - userName\n");
                                              for (Employee e : emp) {
                                                  if (e.getname().equals(current_emp_name)) {
                                                      sb.append(e.details()+"\n");
                                                      currEmpId = e.get_emp_id();
                                                      break;
                                                  }
                                              }
                                              Label ls = new Label((sb.toString()));
                                              pane.add(ls, 10, 4);

                                          }
                                      });
                                      b1.setOnAction(new EventHandler<ActionEvent>() {

                                          @Override
                                          // must done with id
                                          public void handle(ActionEvent t) {
                                              int i = 0;
                                              for (Employee e : emp) {
                                                  if(e.getname().equals(current_emp_name)) {
                                                      i++;
                                                      emp.remove(i);
                                                      showAlert(Alert.AlertType.CONFIRMATION, pane.getScene().getWindow(), "submit confirmation",
                                                              "Quit done succesfully...");
                                                      writeToFile(emp);
                                                      login_design(primaryStage, emp);
                                                  }
                                              }
                                              System.out.println("your goal is:" + emp);
                                          }
                                      });

                                      b5.setOnAction(new EventHandler<ActionEvent>() {

                                          @Override
                                          public void handle(ActionEvent event) {
                                              logout(primaryStage, emp);
                                              login_design(primaryStage, emp);
                                          }

                                      });

                                  }

                              }
                          }

        );

    }

    public void changedata_design(Stage primaryStage, ArrayList<Employee> emp) {

        primaryStage.setTitle("JavaFX Assignment 6");
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(pane, 300, 275);
        Text sceneTitle = new Text("change data  Options ");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        pane.add(sceneTitle, 0, 0, 2, 1);
        b5 = new Button("change name");
        b6 = new Button("Change  salary");
        b5.setMaxWidth(Double.MAX_VALUE);
        b6.setMaxWidth(Double.MAX_VALUE);
        VBox hbox = new VBox(10);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().addAll(b5, b6);
        pane.add(hbox, 1, 4);
        primaryStage.setScene(scene);
        primaryStage.show();
        b5.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("inside button");
                primaryStage.setTitle("JavaFX Assignment 6");
                GridPane pane = new GridPane();
                pane.setAlignment(Pos.CENTER);
                pane.setHgap(10);
                pane.setVgap(10);
                pane.setPadding(new Insets(25, 25, 25, 25));
                Scene scene = new Scene(pane, 500, 500);
                Text sceneTitle = new Text("change name  Options ");
                sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                pane.add(sceneTitle, 0, 0, 2, 1);
                Label l1 = new Label("Enter old Employee Name:");
                pane.add(l1, 0, 1);

                Label l2 = new Label("Enter new Employee Name: ");
                pane.add(l2, 0, 2);
                TextField name1 = new TextField();
                pane.add(name1, 1, 1);
                TextField user_name1 = new TextField();
                pane.add(user_name1, 1, 2);
                Button b = new Button("Submit");
                pane.setHgap(10);
                Button b1 = new Button("Back");
                pane.setHgap(10);
                pane.add(b, 1, 3);
                pane.add(b1, 1, 5);
                VBox hbox = new VBox(10);
                hbox.setAlignment(Pos.BOTTOM_RIGHT);
                // hbox.getChildren().addAll(l1,l2,name1,user_name1,b);
                pane.add(hbox, 1, 4);
                primaryStage.setScene(scene);
                primaryStage.show();
                b.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        // Optional<ButtonType> result = alert.showAndWait();
                        for (int i = 0; i < emp.size(); i++) {
                            for (Employee e : emp) {
                                System.out.println("get name" + e.getname1());
                                System.out.println("entry box" + name1.getText());

                                if (e.getname1().equals(name1.getText())) // check old name is in arraylist or not
                                {
                                    e.setname(user_name1.getText()); // set new name as employee name

                                    boss_menu(primaryStage, emp);
                                }
                            }

                        }

                        writeToFile(emp);
                    }
                });
                b1.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {

                        boss_menu(primaryStage, emp);
                    }
                });

            }
        });
        b6.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setTitle("JavaFX Assignment 6");
                GridPane pane = new GridPane();
                pane.setAlignment(Pos.CENTER);
                pane.setHgap(10);
                pane.setVgap(10);
                pane.setPadding(new Insets(25, 25, 25, 25));
                Scene scene = new Scene(pane, 500, 500);
                Text sceneTitle = new Text("change salary  Options ");
                sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                pane.add(sceneTitle, 0, 0, 2, 1);
                Label l1 = new Label("New Name:");
                pane.add(l1, 0, 1);

                Label l2 = new Label("New salary: ");
                pane.add(l2, 0, 2);
                TextField name1 = new TextField();
                pane.add(name1, 1, 1);
                TextField salary = new TextField();
                pane.add(salary, 1, 2);
                Button b = new Button("OK");
                pane.setHgap(10);
                Button b1 = new Button("RETURN");
                pane.setHgap(10);
                pane.add(b, 1, 3);
                pane.add(b1, 1, 5);
                VBox hbox = new VBox(10);
                hbox.setAlignment(Pos.BOTTOM_RIGHT);
                // hbox.getChildren().addAll(l1,l2,name1,user_name1,b);
                pane.add(hbox, 1, 4);
                primaryStage.setScene(scene);
                primaryStage.show();
                b.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {

                        for (int i = 0; i < emp.size(); i++) {
                            for (Employee e : emp) {
                                System.out.println("get name" + e.getname1());
                                System.out.println("entry box" + name1.getText());

                                if (e.getname1().equals(name1.getText())) // check old name is in arraylist or not
                                {
                                    double d = Double.parseDouble(salary.getText());
                                    e.setsalary(d); // set new name as employee name
                                    boss_menu(primaryStage, emp);

                                }
                            }
                        }

                        writeToFile(emp);
                    }
                });
                b1.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {

                        boss_menu(primaryStage, emp);
                    }
                });

            }
        });
    }

    public void boss_menu(Stage primaryStage, ArrayList<Employee> emp) {

        primaryStage.setTitle("JavaFX Assignment 6");
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(pane, 300, 275);
        Text sceneTitle = new Text("BOSS SCREEN ");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        pane.add(sceneTitle, 0, 0, 2, 1);
        textArea = new TextArea();

        b1 = new Button("Add New Employee");
        b6 = new Button("List Employees");
        b2 = new Button("Update Info");
        b3 = new Button("Do Payroll");
        b4 = new Button("Fire");
        b5 = new Button("Logout");
        b1.setMaxWidth(Double.MAX_VALUE);
        b2.setMaxWidth(Double.MAX_VALUE);
        b3.setMaxWidth(Double.MAX_VALUE);
        b4.setMaxWidth(Double.MAX_VALUE);
        b5.setMaxWidth(Double.MAX_VALUE);
        b6.setMaxWidth(Double.MAX_VALUE);
        VBox hbox = new VBox(10);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().addAll(b1,b6, b2, b3, b4, b5, textArea);
        pane.add(hbox, 1, 4);
        primaryStage.setScene(scene);
        primaryStage.show();
        String text = "";
        StringBuilder fieldContent = new StringBuilder("");
        for (Employee e : emp) {
            System.out.println("Employee is :" + e);
            fieldContent.append(e);
        }
        textArea.setText(fieldContent.toString() + "/n");
        b1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                reg_design(primaryStage);

            }
        });

        b2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                changedata_design(primaryStage, emp);

            }
        });
        b6.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setTitle("JavaFX Assignment 6");
                GridPane pane = new GridPane();
                pane.setAlignment(Pos.CENTER);
                pane.setHgap(10);
                pane.setVgap(10);
                pane.setPadding(new Insets(25, 25, 25, 25));
                Scene scene = new Scene(pane, 300, 275);
                Text sceneTitle = new Text("List of Employees : ");
                sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                pane.add(sceneTitle, 0, 0, 2, 1);
                Button b1 = new Button("RETURN");
                HBox hbox = new HBox(5);
                hbox.setAlignment(Pos.BOTTOM_RIGHT);
                hbox.getChildren().addAll( b1);
                pane.add(hbox, 1, 8);

                primaryStage.setScene(scene);
                primaryStage.show();
                StringBuilder sb = new StringBuilder();
                sb.append("you details: \n" + "LoginName - Id  - salary - date - userName\n");
                int i = 1;
                for(Employee e : emp)
                {
                    sb.append(i+".  " + e.details());
                    sb.append("\n");
                    i++;
                }

                Label ls = new Label((sb.toString()));
                pane.add(ls, 5, 4);
                b1.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        boss_menu(primaryStage, emp);
                    }
                });

            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setTitle("JavaFX Assignment 6");
                GridPane pane = new GridPane();
                pane.setAlignment(Pos.CENTER);
                pane.setHgap(10);
                pane.setVgap(10);
                pane.setPadding(new Insets(25, 25, 25, 25));
                Scene scene = new Scene(pane, 800, 200);
                Text sceneTitle = new Text("Do Pyroll ");
                sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                pane.add(sceneTitle, 0, 0, 2, 1);
                Label label1 = new Label("NAME:");
                label1.setMaxWidth(Double.MAX_VALUE);
                Label label2 = new Label("hour/salary: ");
                label2.setMaxWidth(Double.MAX_VALUE);
                pane.add(label1, 0, 1);
                t11 = new TextField();
                pane.add(t11, 1, 1);

                pane.add(label2, 0, 2);
                t12 = new TextField();
                pane.add(t12, 1, 2);
                Button b = new Button("SUBMIT");
                Button b1 = new Button("RETURN");
                b.setMaxWidth(Double.MAX_VALUE);
                TextArea t = new TextArea();
                HBox hbox = new HBox(5);
                hbox.setAlignment(Pos.BOTTOM_RIGHT);
                hbox.getChildren().addAll(b, b1, t);
                pane.add(hbox, 1, 8);

                primaryStage.setScene(scene);
                primaryStage.show();

                b.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {

                        for (Employee e : emp) {

                            if (e.getname1().equals(t11.getText()) && e.s_type.equals("hourly")) {

                                System.out.println("hr");
                                double d = Double.parseDouble(t12.getText());
                                Hourly h = new Hourly(e.getname(), e.get_emp_id(), e.getsalary(), d);
                                final_Salary = h.getPay();
                                String text = "";
                                StringBuilder fieldContent = new StringBuilder("");

                                fieldContent.append(e.getname() + " " + e.get_emp_id() + " " + final_Salary);

                                t.setText(fieldContent.toString() + "/n");
                            }

                            else if (e.getname1().equals(t11.getText()) && e.s_type.equals("")) {
                                System.out.println("hr");
                                double d = Double.parseDouble(t12.getText());
                                Salaried s = new Salaried(e.getname(), e.getsalary(), e.get_emp_id(), d);
                                final_Salary = s.getPay();
                                String text = "";
                                StringBuilder fieldContent = new StringBuilder("");

                                fieldContent.append(e.getname() + " " + e.get_emp_id() + final_Salary);

                                t.setText(fieldContent.toString() + "/n");
                            }
                        }
                        System.out.println("Payment done succesfully...");
                        showAlert(Alert.AlertType.CONFIRMATION, pane.getScene().getWindow(), "submit confirmation",
                                "Payment done succesfully...");
                        boss_menu(primaryStage, emp);

                    }
                });
                b1.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        boss_menu(primaryStage, emp);
                    }
                });
            }
        });

        b4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                primaryStage.setTitle("JavaFX Assignment 6");
                GridPane pane = new GridPane();
                pane.setAlignment(Pos.CENTER);
                pane.setHgap(10);
                pane.setVgap(10);
                pane.setPadding(new Insets(25, 25, 25, 25));
                Scene scene = new Scene(pane, 300, 275);
                Text sceneTitle = new Text("Employee fire option: ");
                sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                pane.add(sceneTitle, 0, 0, 2, 1);
                Label l1 = new Label("Enter emp id to fire:");
                pane.add(l1, 0, 1);
                TextField t1 = new TextField();
                pane.add(t1, 1, 1);
                Button fb = new Button("OK");
                Button fc = new Button("RETURN");
                // pane.add(fb,2,2);
                TextArea fi = new TextArea();
                VBox hbox = new VBox(10);
                hbox.setAlignment(Pos.BOTTOM_RIGHT);
                hbox.getChildren().addAll(fb, fi, fc);
                pane.add(hbox, 1, 4);
                primaryStage.setScene(scene);
                primaryStage.show();

                String text = "";
                StringBuilder fieldContent = new StringBuilder("");
                for (Employee e : remove_emp_list) {
                    // Concatinate each loop
                    fieldContent.append(e);
                }
                fi.setText(fieldContent.toString());
                fb.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        int y = Integer.parseInt(t1.getText());
                        int index = 0 ;
                        for (Employee e : emp) {
                            if (e.get_emp_id() == y) // check id is in arraylist or not
                            {
                                //remove_emp_list.add((emp.remove(e.get_emp_id() + 1)));
//								emp.remove(e.get_emp_id() + 1);
								/*String text = "";
								StringBuilder fieldContent1 = new StringBuilder("");
								for (Employee e1 : remove_emp_list) {
									fieldContent1.append(e1);
								}
								fi.setText(fieldContent1.toString());

*/
                                if(index == 0) {
                                    showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "error", "You can not Fire Boss");
                                }else {
                                    emp.remove(index);
                                    System.out.println("Employee Fired succesfully...");
                                    showAlert(Alert.AlertType.CONFIRMATION, pane.getScene().getWindow(), "submit confirmation",
                                            "Employee Fired succesfully...");
                                    boss_menu(primaryStage, emp);
                                }



                            }
                            index++;
                        }

                    }
                });

                fc.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {

                        boss_menu(primaryStage, emp);
                    }
                });
            }
        });

        b5.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                logout(primaryStage, emp);
                login_design(primaryStage, emp);
            }

        });

    }

    private static void add1(Employee emp12) {
        emp.add(emp12);
        System.out.println("Adding employee to the list ");
        System.out.println("add " + emp);// add all data into arraylist of employee

    }

    public void reg_design(Stage primaryStage) {
        File file = new File("employee.txt");
        primaryStage.setTitle("JavaFX Assignment 6");
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);

        pane.setHgap(40);
        pane.setVgap(40);
        pane.setPadding(new Insets(50, 50, 50, 50));
        Scene scene = new Scene(pane, 400, 600);

        Text sceneTitle = new Text("REGISTRATION");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        pane.add(sceneTitle, 0, 0, 2, 1);

        Label label1 = new Label("Name:");
        pane.add(label1, 0, 1);

        Label label2 = new Label("UserName");
        pane.add(label2, 0, 2);

        Label label3 = new Label("Password");
        pane.add(label3, 0, 3);

        Label label4 = new Label("Re-enter Password ");
        pane.add(label4, 0, 4);

        Label label5 = new Label("Enter HOURS/SALARIED:  ");
        pane.add(label5, 0, 7);
        hr = new RadioButton("Houly");
        sal = new RadioButton("Salaried");
        ToggleGroup question1 = new ToggleGroup();
        hr.setToggleGroup(question1);
        sal.setToggleGroup(question1);
        hr.setUserData("hourly");
        sal.setUserData("salaried");
        question1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (question1.getSelectedToggle() != null) {
                    System.out.println(question1.getSelectedToggle().getUserData().toString());
                }
            }
        });

        System.out.println("Salary type:" + s_type);
        pane.add(hr, 0, 5);
        pane.add(sal, 0, 6);

        name = new TextField();
        pane.add(name, 1, 1);
        user_name = new TextField();
        pane.add(user_name, 1, 2);

        pass = new PasswordField();
        pane.add(pass, 1, 3);

        re_pass = new PasswordField();
        pane.add(re_pass, 1, 4);
        System.out.println(sal.isSelected());
        salary = new TextField();
        pane.add(salary, 1, 7);

        submit = new Button("OK");

        HBox hbox = new HBox(20);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().add(submit);
        pane.add(hbox, 1, 9);
        primaryStage.setScene(scene);
        primaryStage.show();
        submit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                int r = 0;
                emp1.setnextId();
                eid = Employee.nextId;
                // current_emp_id=eid;
                System.out.println("eid  in reg" + eid);
                emp1.id(eid);
                emp1.set_s_type(question1.getSelectedToggle().getUserData().toString());
                String str = Payroll.user_name.getText();
                String str1 = Payroll.name.getText();
                String salary1 = Payroll.salary.getText();
                salary2 = Double.parseDouble(salary1);
                String pass = Payroll.pass.getText();
                String re_pass = Payroll.re_pass.getText();
                try {
                    r = getpassword(pass, re_pass);

                } catch (NoSuchAlgorithmException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if (pass.equals(re_pass) && str != "" && str1 != "" && pass != "" && re_pass != "" && r == 1) {
                    System.out.println("inside id pass");
                    message.setText("password not match with Conirm password");
                    message.setTextFill(Color.rgb(21, 117, 84));
                    emp1 = new concreate(str, eid, salary2, current_date, str1, pass,
                            question1.getSelectedToggle().getUserData().toString()); // call Employee cons of 5
                    // parameter
                    Employee emp_ser = new concreate();
                    emp_ser = new concreate(str, eid, salary2, current_date, str1, pass,
                            question1.getSelectedToggle().getUserData().toString());

                    add1(emp1);
                    if (counter == 0) {

                        boss.add(str);
                        boss.add(pass);
                        System.out.println("boss list" + boss);
                        counter++;
                    }
                    writeToFile(emp);
                    System.out.println("c:" + c);
                    if (c == 0) {
                        showAlert(Alert.AlertType.CONFIRMATION, pane.getScene().getWindow(), "submit confirmation",
                                "Login using username and password");
                        c++;
                        System.out.println("inside c:" + c);
                        login_design(primaryStage, emp);
                    } else {
                        showAlert(Alert.AlertType.CONFIRMATION, pane.getScene().getWindow(), "submit confirmation",
                                "Login using username and password");

                        boss_menu(primaryStage, emp);
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "error", "mismatch entered details");
                    return;
                }
            }

        });
        // assing employee id to current employee id

    }

    @Override
    public void start(Stage primaryStage) {
        //
        final int eid1;
        String name;
        String filename = "employee.txt";
        double salary;
        String bname = null;
        // --------get current date usinf date formate type--------------
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        current_date = dtf.format(localDate);

        // ------------try to open file---------
        try {
            File file = new File(filename);
            // -------- check file is already there or not--------------
            if (file.exists()) {
                //
                login_design(primaryStage, emp);

            }

            else {

                reg_design(primaryStage);

            }

        } catch (Exception e) {

        }

    }

    public static void writeToFile(ArrayList<Employee> emp) {
        BufferedWriter bw = null;
        try {
            File fout = new File("employee.txt");
            FileOutputStream fos = new FileOutputStream(fout);
            bw = new BufferedWriter(new OutputStreamWriter(fos));

            for (Employee e : emp) {
                bw.write(e.toString());
                bw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Exception Occured while rending the data to the databse File");
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                System.out.println("Exception occured while closing Buffered Writer()");
            }
        }

	/*
		try {
			FileOutputStream fos = new FileOutputStream("employee.txt");
			ObjectOutputStream oos  = new ObjectOutputStream(fos);
			for(Employee e: emp) {
				oos.writeObject(e.toString());
			}

		}catch(Exception e) {
			System.out.println("Exception occured while writing in to the file");
		}*/


    }
}
