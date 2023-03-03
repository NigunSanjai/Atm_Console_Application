import com.sun.source.tree.Tree;

import java.util.*;
class Bank_Admin{
    String admin_username="NIGUN SANJAI";
    String admin_pass = "123456";

    int balance = 0;
    ArrayList<String> user_user_ids = new ArrayList<>();
    ArrayList<String> user_passwords = new ArrayList<>();
    HashMap<String,Integer> denominations = new HashMap<>();
    HashMap<String,Double> user_default_balance = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    public void enter_user(String name, String pass, Double bal){
        user_user_ids.add(name);
        user_passwords.add(pass);
        user_default_balance.put(name,bal);
        System.out.println();
        System.out.println("Thank You");
    }
    public void load_amount(){
        denominations.put("2000",0);
        denominations.put("500",0);
        denominations.put("200",0);
        denominations.put("100",0);
        System.out.println();
        System.out.println("Enter the No of 2000's");
        int two = sc.nextInt();
        balance+=(2000*two);
        denominations.put("2000",denominations.get("2000")+two);
        System.out.println("Enter the No of 500's");
        int five = sc.nextInt();
        balance+=(500*five);
        denominations.put("500",denominations.get("500")+five);
        System.out.println("Enter the No of 200's");
        int twoh= sc.nextInt();
        balance+=(200*twoh);
        denominations.put("200",denominations.get("200")+twoh);
        System.out.println("Enter the No of 100's");
        int one = sc.nextInt();
        balance+=(100*one);
        denominations.put("100",denominations.get("100")+one);
        System.out.println("Thank you!");
        System.out.println();
    }
    public void check_balance(){
        System.out.println();
        System.out.println("The ATM Balance is : " + String.valueOf(balance));
        System.out.println("The Denomination count is");
        System.out.println("No of 2000's : "+denominations.get("2000"));
        System.out.println("No of 500's : "+denominations.get("500"));
        System.out.println("No of 200's : "+denominations.get("200"));
        System.out.println("No of 100's : "+denominations.get("100"));
        System.out.println();
        System.out.println("Thank you!");
        System.out.println();
    }


}
class Bank_Users{

    ArrayList<ArrayList<String>> Transaction_History = new ArrayList<>();
    String user_name = "";
    String user_pass = "";
    double user_balance = 0;
    Bank_Users(String name, String pass , Double def_bal){
        user_name=name;
        user_pass=pass;
        user_balance=def_bal;
    }
    public void balance_check(){
        System.out.println();
        System.out.println("YOUR CURRENT BALANCE IS : " + user_balance);
    }
    public void pin_change(String pin){
        System.out.println();
        user_pass=pin;
        System.out.println("PIN CHANGED SUCESSFULLY");
    }
    public void withdrawl(Bank_Admin admin,int amount){
        boolean withdrawl = true;
        System.out.println();
        if(amount>user_balance) System.out.println("INSUFFICIENT USER BALANCE");
        else{
                int global_amount = amount;
                int count2000 = 0, count500 = 0, count200 = 0, count100 = 0;
                count2000 = Math.min(admin.denominations.get("2000"),amount / 2000);
                amount -= count2000*2000;
                count500 = Math.min(admin.denominations.get("500"),amount / 500);
                amount -= count500*500;
                count200 = Math.min(admin.denominations.get("200"),amount / 200);
                amount -= count200*200;
                count100 = Math.min(admin.denominations.get("100"),amount / 100);
                amount -= count100*100;
                if (amount != 0) {
                    withdrawl =  false;
                }
                if(withdrawl){
                    System.out.println();
                    System.out.println("Kindly collect the cash");
                    admin.denominations.put("2000",admin.denominations.get("2000")-count2000);
                    admin.denominations.put("500",admin.denominations.get("500")-count500);
                    admin.denominations.put("200",admin.denominations.get("200")-count200);
                    admin.denominations.put("100",admin.denominations.get("100")-count100);
                    user_balance-=global_amount;
                    admin.balance-=global_amount;
                    Transaction_History.add(new ArrayList<>(Arrays.asList(user_name,"DEBIT",String.valueOf(global_amount))));
                }
                else{
                    System.out.println();
                    System.out.println("Enter as per the denominations");
                }
        }
    }
    public void deposit(Bank_Admin admin){
        Scanner sc = new Scanner(System.in);
        int amount = 0;
        System.out.println();
        System.out.println("Enter the denominations you wish to deposit");
        System.out.println("Enter the No of 2000's");
        int two = sc.nextInt();
        admin.balance+=(2000*two);
        user_balance+=(2000*two);
        amount+=(2000*two);
        admin.denominations.put("2000",admin.denominations.get("2000")+two);
        System.out.println("Enter the No of 500's");
        int five = sc.nextInt();
        admin.balance+=(500*five);
        user_balance+=(500*five);
        amount+=(500*five);
        admin.denominations.put("500",admin.denominations.get("500")+five);
        System.out.println("Enter the No of 200's");
        int twoh= sc.nextInt();
        admin.balance+=(200*twoh);
        user_balance+=(200*twoh);
        amount+=(200*twoh);
        admin.denominations.put("200",admin.denominations.get("200")+twoh);
        System.out.println("Enter the No of 100's");
        int one = sc.nextInt();
        admin.balance+=(100*one);
        user_balance+=(100*one);
        amount+=(100*one);
        admin.denominations.put("100",admin.denominations.get("100")+one);
        System.out.println("Thank you!");
        System.out.println();
        Transaction_History.add(new ArrayList<>(Arrays.asList(user_name,"CREDIT",String.valueOf(amount))));

    }

}
class Bank_Atm {
    HashMap<String, Bank_Users> valid_bank_users=new HashMap<>();
    HashMap<String, Bank_Admin> valid_bank_admin=new HashMap<>();
}
public class Atm{
    static Bank_Atm machine  = new Bank_Atm();
    public static void main(String[] args) {
        boolean global_exit=true;
        boolean user_avaliable=false;
        Bank_Admin bank_admin = new Bank_Admin();
        while(global_exit){
            Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out.println("----------ENTER YOUR INPUT IN UPPERCASE---------");
            System.out.println("ENTER YOUR ROLE : ADMIN/USER");
            String current_user = sc.nextLine();

            if(current_user.equals("ADMIN")){
                System.out.println();
                System.out.println("ENTER YOUR USER NAME");
                String username= sc.nextLine();
                System.out.println("ENTER YOUR PASSWORD");
                String pass = sc.nextLine();
                if(!username.equals(bank_admin.admin_username) || !pass.equals(bank_admin.admin_pass)){
                    System.out.println();
                    System.out.println("Invalid Authentication Details");
                }
                else{
                    if(!machine.valid_bank_admin.containsKey(username)) machine.valid_bank_admin.put(username,bank_admin);
                    while(true){
                        System.out.println();
                        System.out.println("ENTER THE METHOD YOU PREFER : ");
                        System.out.println("1.CREATE USER");
                        System.out.println("2.LOAD AMOUNT");
                        System.out.println("3.CHECK BALANCE");
                        System.out.println("4.GO TO MAIN MENU");
                        System.out.println("5.EXIT");
                        int process = 0;
                        try {
                            process = sc.nextInt();
                        }
                        catch (Exception e){

                            System.out.println("Invalid Choice Check Again");
                        }
                        sc.nextLine();
                        if (process==1){
                            user_avaliable=true;
                            System.out.println();
                            System.out.println("ENTER USER NAME");
                            String user_name= sc.nextLine();
                            while(user_name.isEmpty()){
                                System.out.println("USER NAME CANNOT BE BLANK PROVIDE A USER NAME");
                                user_name= sc.nextLine();
                            }
                            System.out.println("ENTER USER PASSWORD");
                            String user_pass = sc.nextLine();
                            while(user_pass.isEmpty()){
                                System.out.println("USER NAME CANNOT BE BLANK PROVIDE A USER NAME");
                                username= sc.nextLine();
                            }
                            System.out.println("ENTER USER BALANCE");
                            double def_bal = sc.nextDouble();
                            machine.valid_bank_users.put(user_name,new Bank_Users(user_name,user_pass,def_bal));
                        }
                        else if(process==2){
                            machine.valid_bank_admin.get(username).load_amount();
                        }
                        else if (process==3) {
                            machine.valid_bank_admin.get(username).check_balance();
                        }
                        else if (process==4) {
                            break;
                        }
                        else if(process==5){
                            global_exit=false;
                            break;
                        }
                    }
                }
                }
            else if (current_user.equals("USER")) {
                System.out.println();
                if (!user_avaliable) System.out.println("No USER in Record");
                else {
                    System.out.println();
                    System.out.println("ENTER YOUR ID");
                    String id = sc.nextLine();
                    System.out.println("ENTER YOUR PASSWORD");
                    String pass = sc.nextLine();
                    if (!machine.valid_bank_users.containsKey(id) || !pass.equals(machine.valid_bank_users.get(id).user_pass))
                        System.out.println("INVALID AUTHENTICATION DETAILS");
                    else {
                        System.out.println();
                        System.out.println("Thank you for choosing our services");
                        while (true) {
                            System.out.println();
                            System.out.println("ENTER THE METHOD YOU PREFER : ");
                            System.out.println("1.BALANCE CHECK");
                            System.out.println("2.PIN CHANGE");
                            System.out.println("3.WITHDRAWL");
                            System.out.println("4.DEPOSIT");
                            System.out.println("5.TRANSFER");
                            System.out.println("6.MINI STATEMENT");
                            System.out.println("7.GO TO MAIN MENU");
                            System.out.println("8.EXIT");
                            int process = 0;
                            try {
                                process = sc.nextInt();
                            }
                            catch (Exception e){
                                System.out.println();
                                System.out.println("Invalid Choice Check Again");
                            }
                            sc.nextLine();
                            if (process == 1) machine.valid_bank_users.get(id).balance_check();
                            else if (process == 2) {
                                System.out.println();
                                System.out.println("Enter the pin you wish to change");
                                String change_pin = sc.nextLine();
                                machine.valid_bank_users.get(id).pin_change(change_pin);
                            } else if (process == 3) {
                                System.out.println();
                                System.out.println("Enter the amount in denominations of 2000,500,200,100");
                                int amount = sc.nextInt();
                                sc.nextLine();
                                if (machine.valid_bank_admin.get("NIGUN SANJAI").balance < amount)
                                    System.out.println("INSUFFICIENT ATM BALANCE");
                                else {
                                    machine.valid_bank_users.get(id).withdrawl(machine.valid_bank_admin.get("NIGUN SANJAI"), amount);
                                }
                            } else if (process == 4) {
                                machine.valid_bank_users.get(id).deposit(machine.valid_bank_admin.get("NIGUN SANJAI"));
                            } else if (process == 5) {
                                System.out.println();
                                System.out.println("Enter the user to which you wish to transfer");
                                String transfer_user = sc.nextLine();
                                if (!machine.valid_bank_users.containsKey(transfer_user))
                                    System.out.println("Invalid User");
                                else {
                                    System.out.println();
                                    System.out.println("Enter the amount you wish to transfer");
                                    int amount = sc.nextInt();
                                    if (machine.valid_bank_users.get(id).user_balance < amount)
                                        System.out.println("INSUFFICIENT BALANCE");
                                    else {
                                        machine.valid_bank_users.get(id).user_balance-=amount;
                                        machine.valid_bank_users.get(transfer_user).user_balance+=amount;
                                        System.out.println();
                                        System.out.println("SUCESSFULLY TRANSFERRED");
                                        machine.valid_bank_users.get(id).Transaction_History.add(new ArrayList<>(Arrays.asList(machine.valid_bank_users.get(id).user_name,"TRANSFERRED AMOUNT OF",String.valueOf(amount),"TO",machine.valid_bank_users.get(transfer_user).user_name)));
                                        machine.valid_bank_users.get(transfer_user).Transaction_History.add(new ArrayList<>(Arrays.asList(machine.valid_bank_users.get(transfer_user).user_name,"RECEIVED AMOUNT OF",String.valueOf(amount),"FROM",machine.valid_bank_users.get(id).user_name)));
                                    }
                                }
                            }
                            else if (process==6) {
                                int index = 0;
                                if(machine.valid_bank_users.get(id).Transaction_History.size()==0)
                                    System.out.println("No transactions occured");
                                else if(machine.valid_bank_users.get(id).Transaction_History.size()<5){
                                    System.out.println();
                                    System.out.println(id +" "+ "TRANSACTION HISTORY");
                                    System.out.println();
                                    for(int i=0;i<machine.valid_bank_users.get(id).Transaction_History.size();i++){
                                        String result = "";
                                        for(int j=0;j<machine.valid_bank_users.get(id).Transaction_History.get(i).size();j++){
                                            result+=machine.valid_bank_users.get(id).Transaction_History.get(i).get(j);
                                            result+=" ";
                                        }
                                        System.out.println(result);

                                        System.out.println();
                                    }
                                    System.out.println("Thank you for Reaching us");
                                }
                                else{
                                    System.out.println(id + " TRANSACTION HISTORY");
                                    for(int i=machine.valid_bank_users.get(id).Transaction_History.size()-1;i>machine.valid_bank_users.get(id).Transaction_History.size()-5;i--){
                                        String result = "";
                                        for(int j=0;j<machine.valid_bank_users.get(id).Transaction_History.get(i).size();j++){
                                            result+=machine.valid_bank_users.get(id).Transaction_History.get(i).get(j);
                                            result+=" ";
                                        }
                                        System.out.println(result);

                                        System.out.println();
                                    }
                                    System.out.println("Thank you for Reaching us");
                                    }
                                }

                            else if (process == 7) {
                                break;
                            }
                            else if(process==8){
                                global_exit=false;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
