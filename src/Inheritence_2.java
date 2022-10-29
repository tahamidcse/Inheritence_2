//Daily.java


public class Daily extends Appointment
{
    public Daily(int year, int month, int day, String description)
    {
        super(year, month, day, description);
    }
    public boolean occursOn(int year, int month, int day)
    {
        if (year > getYear())
        {
            return true;
        }
        if (year == getYear())
        {
            if (month > getMonth())
            {
                return true;
            }
            if (month == getMonth())
            {
                if (day >= getDay())
                {
                    return true;
                }
            }
        }
        return false;
    }
}




//Monthly.java


public class Monthly extends Appointment
{
    public Monthly(int year, int month, int day, String description)
    {
        super(year, month, day, description);
    }
    public boolean occursOn(int year, int month, int day)
    {
        if (year < getYear())
        {
            return false;
        }
        if (month < getMonth() && year == getYear())
        {
            return false;
        }
        return day == getDay();
    }
}




//Onetime.java


public class Onetime extends Appointment
{
    public Onetime(int year, int month, int day, String description)
    {
        super(year, month, day, description);
    }
}






//Appointment.java


public class Appointment
{
    private String description;
    private int year;
    private int month;
    private int day;
    public Appointment(int year, int month, int day, String description)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.description = description;
    }
    public int getYear()
    {
        return year;
    }
    public int getMonth()
    {
        return month;
    }
    public int getDay()
    {
        return day;
    }
    public boolean occursOn(int year, int month, int day)
    {
        return (year == this.year) && (month == this.month) && (day == this.day);
    }

    public String toString()
    {
        return description;
    }
}




//Demo.java

import java.util.Scanner;
public class Demo
{
    public static void main(String[] args)
    {
        Appointment[] appointments = new Appointment[2];
        appointments[0] = new Daily(2016, 1, 1, "Clean Room.");
        appointments[1] = new Monthly(2016, 1, 31, "Visit Dentist.");
        System.out.println("Enter a date (year, month, day) to list "
                + "appointments: ");
        Scanner in = new Scanner(System.in);
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();
        for (int i = 0; i < appointments.length; i++)
        {
            if (appointments[i].occursOn(year, month, day))
            {
                System.out.println(appointments[i]);
            }
        }
    }
}


    /*EXPECTED OUTPUT:
        Enter a date (year, month, day) to list appointments:
        2016 1 1
        Clean Room.
        */



        //########################################## 2



        import java.io.BufferedReader;
        import java.io.InputStreamReader;

class Appointment{
    int total;    //total appointments
    String description[];    //description of appointments
    String date[];    //date of appointments

    Appointment(int MAX_APPOINTMENTS){
        /*
         * initializing appointment details
         */
        description = new String[MAX_APPOINTMENTS];
        date = new String[MAX_APPOINTMENTS];
        total = 0;
    }

    public int getTotal(){
        return total;
    }

    public String getDate(int indx){
        return date[indx];
    }

    public String getDescription(int indx){
        return description[indx];
    }

    public void addAnAppointment(String date, String description){
        /*
         * adding new appointment
         */
        this.date[total] = date;
        this.description[total] = description;
        total++;
    }
}

class Onetime extends Appointment{
    /*
     * onetime appointment handling
     */
    Onetime(int MAX_APPOINTMENTS){
        super(MAX_APPOINTMENTS);
    }

    public void occursOn(int year, int month, int day){
        /*
         * search and display onetime appointments
         */
        String date = year + "-" + month + "-" + day;
        for(int i = 0; i < total; i++){
            if(this.date[i].equals(date)){
                System.out.println("\tOnetime" + "\t\t" + this.description[i]);
            }
        }
    }
}

class Daily extends Appointment{
    /*
     * daily appointment handling
     */
    Daily(int MAX_APPOINTMENTS){
        super(MAX_APPOINTMENTS);
    }

    public void occursOn(int year, int month, int day){
        /*
         * search and display daily appointments
         */
        for(int i = 0; i < total; i++){
            if(this.date[i].substring(0, 4).equals(String.valueOf(year))){
                System.out.println("\tDaily" + "\t\t" + this.description[i]);
            }
        }
    }
}

class Monthly extends Appointment{
    /*
     * monthly appointment handling
     */
    Monthly(int MAX_APPOINTMENTS){
        super(MAX_APPOINTMENTS);
    }

    public void occursOn(int year, int month, int day){
        /*
         * search and display monthly appointments
         */
        for(int i = 0; i < total; i++){
            if(this.date[i].substring(0, 4).equals(String.valueOf(year)) && this.date[i].substring(8).equals(String.valueOf(day))){
                System.out.println("\tMonthly" + "\t\t" + this.description[i]);
            }
        }
    }
}

public class AppointmentBook{
    public static void main(String[] args){

        final int MAX_APPOINTMENTS = 10;    //maximum number of appointments

        Onetime onetime = new Onetime(MAX_APPOINTMENTS);    //object of onetime appointments
        Daily daily = new Daily(MAX_APPOINTMENTS);            //object of daily appointments
        Monthly monthly = new Monthly(MAX_APPOINTMENTS);    //object of monthly appointments

        int ch = -1;
        String date;
        String description;
        do{
            /*
             *
             * Loop until user give exit command
             *
             */
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                /*
                 * display main menu
                 */
                System.out.println("1. Add an appointment");
                System.out.println("2. Search for appointments");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                ch = Integer.parseInt(br.readLine());

                switch(ch){
                    case 1:
                        /*
                         * adding an appointment
                         */
                        date = "";
                        /*
                         * getting appointment details from user
                         */
                        System.out.print("\tEnter appointment year: ");
                        date += br.readLine() + "-";
                        System.out.print("\tEnter appointment month: ");
                        date += br.readLine() + "-";
                        System.out.print("\tEnter appointment day: ");
                        date += br.readLine();

                        System.out.print("\tEnter appointment description: ");
                        description = br.readLine();

                        /*
                         * getting appointment type
                         */
                        System.out.println("\tEnter appointment type: ");
                        System.out.println("\t\t1. Onetime\n\t\t2. Daily\n\t\t3. Monthly");
                        System.out.print("\t\tEnter your choice: ");
                        ch = Integer.parseInt(br.readLine());

                        switch(ch){
                            case 1:
                                /*
                                 * appointment type is onetime
                                 */
                                onetime.addAnAppointment(date, description);
                                break;
                            case 2:
                                /*
                                 * appointment type is daily
                                 */
                                daily.addAnAppointment(date, description);
                                break;
                            case 3:
                                /*
                                 * appointment type is monthly
                                 */
                                monthly.addAnAppointment(date, description);
                                break;
                            default:
                                /*
                                 * invalid choice given
                                 */
                                System.out.println("\t\tInvalid appointment type !!!");
                                break;
                        }
                        break;

                    case 2:
                        /*
                         * display appointments on particular day
                         */
                        int year, month, day;
                        /*
                         * getting date from user
                         */
                        System.out.print("\tEnter year: ");
                        year = Integer.parseInt(br.readLine());
                        System.out.print("\tEnter month: ");
                        month = Integer.parseInt(br.readLine());
                        System.out.print("\tEnter day: ");
                        day = Integer.parseInt(br.readLine());

                        /*

                         * displaying all appointments on that day
                         */
                        System.out.println("\n\tDetails of appointments on " + year + "-" + month + "-" + day + " are:");
                        System.out.println("\tType:\t\tDescription:");
                        onetime.occursOn(year, month, day);
                        daily.occursOn(year, month, day);
                        monthly.occursOn(year, month, day);
                        System.out.println("\n");
                        break;

                    case 0:
                        /*
                         * exit from loop
                         */
                        return;

                    default:
                        /*
                         * invalid choice given
                         */
                        System.out.println("\tInvalid choice !!!");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }while(ch != 0);
    }
}



/*EXPECTED OUTPUT

        1. Add an appointment
        2. Search for appointments
        O. Exit
        Enter your choice: 1
        Enter appointment year: 2019
        Enter appoinement month: 10
        Enter appoinement day: 11
        Enter appointment description: to consult doctor
        Enter appointment type:
        1. Onetime
        2. Daily
        3. Monthy
        Enter your choice: 1
        1. Add an appoinemenet
        2. Search tor appoinements
        O. Exit
        Enter your choice: 1
        Enter appointment year: 2019
        Enter appoinement month: 11
        Enter appoinement day: 10
        Enter appointment description: to do this

*/



//################################### 3

        import java.io.*;
         import java.util.*;

abstract class Appointment {

    private int y, m, d;
    private String desc;

    Appointment(int year, int month, int day, String description) {
        d = day;
        m = month;
        y = year;
        desc = description;
    }

    public String getDescription() {
        return desc;
    }

    public int getDay() {
        return d;
    }


    public int getMonth() {
        return m;
    }


    public int getYear() {
        return y;
    }

    public boolean occursOn(int year, int month, int day) {
        if(getDay() == day && getMonth() == month && getYear() == year){
            return true;
        }
        else {
            return false;
        }
    }

    public String toString() {
        return y +" " + m + " " + d + " " + desc;
    }
}
class Onetime extends Appointment {

    Onetime(int year, int month, int day, String description) {
        super(year, month, day, description);
    }
}
class Monthly extends Appointment {

    Monthly(int year, int month, int day, String description) {
        super(day, month, year, description);
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        if (day == getDay())
            return true;
        else
            return false;

    }
}
class Daily extends Appointment {

    Daily(int year, int month, int day, String description) {
        super(year, month, day, description);
    }

    @Override
    public boolean occursOn(int year, int month, int day)
    {
        if (year >= getYear() && month >= getMonth())
        {
            return true;
        }
        else{
            return false;}
    }
}
class AppointmentIndex {

    ArrayList<Appointment> apps;


    public AppointmentIndex() {
        apps = new ArrayList<Appointment>();
    }

    public void addApp(Appointment m) {

        apps.add(m);
    }

    //Conditionally show the matched appointment
    public void showOut(int year, int month, int day) {
        for (Appointment m : apps) {
            if (m.occursOn(year, month, day))
                System.out.println(m.toString());
        }
    }

    public static void save(String file, Appointment appointment) throws IOException {

        File fl = new File(file+".txt");
        FileWriter fw = new FileWriter(fl, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(appointment + " " + file );

        pw.close();
    }

    public static ArrayList<Appointment> load(String fileName) throws FileNotFoundException {

        File fl = new File(fileName);
        FileReader fr = new FileReader(fl);
        LineNumberReader lnr = new LineNumberReader(fr);
        Scanner in = null;
        String txt = "";
        Appointment loaded = null;
        ArrayList<Appointment> loadApp = null;


        try {

            in = new Scanner(fl);

            while(in.hasNextLine()){
                txt = in.nextLine();

                String[] data = txt.split(" ");
                int year = Integer.parseInt(data[0]);
                int month = Integer.parseInt(data[1]);
                int day = Integer.parseInt(data[2]);
                String desc = data[3];
                String type = data[4];

                switch(type){
                    case "OnetimeAppointment":
                        loaded = new Onetime(year, month, day, desc);
                        break;
                    case "MonthlyAppointment":
                        loaded = new Monthly(year, month, day, desc);
                        break;
                    case "DailyAppointment":
                        loaded = new Daily(year, month, day, desc);
                        break;
                }
                loadApp.add(loaded); //Where the issue occur
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return loadApp;
    }

}

public class Tester{

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        AppointmentIndex list = new AppointmentIndex();
        int y, m, d;
        String type;
        String des;
        Appointment app;
        String file;


        do{
            System.out.print("Select an option: A for add an appointment, C for checking, Q to quit, L to load an appointment book: ");
            String in = sc.nextLine();

            switch (in){
                case "Q":
                    System.exit(0);
                case "A":
                    System.out.print("Enter the type (O - Onetime, D - Daily, or M - Monthly): ");
                    type = sc.nextLine();
                    System.out.print("Enter a description: ");
                    des = sc.nextLine();
                    System.out.print("Enter the year: ");
                    y = sc.nextInt();
                    System.out.print("Enter the month: ");
                    m = sc.nextInt();
                    System.out.print("Enter the day: ");
                    d = sc.nextInt();


                    //Add the specific type of the appointment
                    switch (type){
                        case "O"://one time App
                            app = new Onetime(y, m, d, des);
                            file = "OnetimeAppointment";
                            break;
                        case "D"://Daily app
                            app = new Daily(y, m, d, des);
                            file = "DailyAppointment";
                            break;
                        case "M"://Monthly app
                            app = new Monthly(y, m, d, des);
                            file = "MonthlyAppointment";
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + type);
                    }
                    list.addApp(app);
                    try {
                        list.save(file, app);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "C":
                    int year, month, day;
                    System.out.print("Enter the year: ");
                    year = sc.nextInt();
                    System.out.print("Enter the month: ");
                    month = sc.nextInt();
                    System.out.print("Enter the day: ");
                    day = sc.nextInt();
                    list.showOut(year, month, day);
                    break;
                case "L":
                    System.out.println("Please enter the file you want to load: ");
                    String filename = sc.nextLine();
                    try {
                        list.load(filename);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("File succesfully loaded !! ");
                    break;

            }
        }
        while(true);
    }
}


/*EXPECTED OUTPUT
 Enter the type (O - Onetime, D - Daily, or M - Monthly): M
 Enter a description: MJUT
 Enter the year: 2562
 Enter the month: 3
 Enter the day: 63
 Select an option: A for add an appointment, C for checking, Q to quit, L to load an appointment book:L
 Enter the year: 2562
 Enter the month: 3
 Enter the day: 63
 Please enter the file you want to load:  OneTimeAppointment.txt
 File succesfully loaded !!
 **/
