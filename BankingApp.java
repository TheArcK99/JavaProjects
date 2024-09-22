import java.util.*;

class BankDetails{
    private String acctno;
    private String name;
    private String acct_type;
    private double balance;
    Scanner se = new Scanner(System.in);

    public void openAccount(){
        System.out.println("Please enter the account number you wish to use: ");
        acctno = se.next();
        System.out.println("Please enter the account type you wish to use: ");
        acct_type = se.next();
        System.out.println("Please enter your name: ");
        name = se.next();
        System.out.println("Please enter the initial deposit amount: ");
        balance = se.nextDouble();
    }

    public void showAccount(){
        System.out.println("Account Holde Name: "+name);
        System.out.println("Account Number: "+acctno);
        System.out.println("Account Type: "+acct_type);
        System.out.println("Balance: "+balance);
    }

    public void deposit(){
        double amt;
        System.out.println("Enter the amount you wish to deposit: ");
        amt = se.nextDouble();
        balance += amt;
    }

    public void withdrawal(){
        System.out.println("Enter the amount you wish to withdraw: ");
        double amt = se.nextDouble();
        if(balance >= amt){
            balance -= amt;
            System.out.println("Balance after withdrawal: "+balance);
        }else{
            System.out.println("Your balance is less than "+amt+ "-Transaction failed!");
        }
    }

    public boolean search(String a){
        if(acctno.equals(a)){
            showAccount();
            return true;
        }
        return false;
    }

}

public class BankingApp {
    public static void main(String arg[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("How many number of costumers do you want to input? ");
        int n = sc.nextInt();
        BankDetails C[] = new BankDetails[n];
        for(int i = 0; i < C.length; i++){
            C[i] = new BankDetails();
            C[i].openAccount();
        }

        int ch;
        do {
            System.out.println("\n***Banking System Application***");
            System.out.println("1.Display all acount data \n2.Search by Account Number\n3.Deposit the amount\n4.Withdraw the amount \n5.Exit");
            System.out.println("Enter your choice: ");
            ch = sc.nextInt();

            switch(ch){
                case 1:
                    for(int i = 0; i <C.length; i++){
                        C[i].showAccount();
                    }
                    break;
                case 2:
                    System.out.println("Enter the account number you wish to search: ");
                    String ab = sc.next();
                    boolean found = false;
                    for(int i = 0; i < C.length; i++){
                        found = C[i].search(ab);
                        if(found){
                            break;
                        }
                    }
                    if(!found){
                        System.out.println("Search failed. Account does not exist.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the account number you wish to search: ");
                    String abc = sc.next();
                    found = false;
                    for(int i = 0; i < C.length; i++){
                        found = C[i].search(abc);
                        if(found){
                            C[i].deposit();
                            break;
                        }
                    }
                    if(!found){
                        System.out.println("Search failed. Account does not exist.");
                    }
                    break;
                case 4:
                    System.out.println("Enter the account number you wish to search: ");
                    String abcd = sc.next();
                    found = false;
                    for(int i = 0; i < C.length; i++){
                        found = C[i].search(abcd);
                        if(found){
                            C[i].withdrawal();
                            break;
                        }
                    }
                    if(!found){
                        System.out.println("Search failed. Account does not exist.");
                    }
                    break;
                case 5:
                    System.out.println("See you soon!");
                    break;

            }

        }
        while(ch!=5);
    }
}


