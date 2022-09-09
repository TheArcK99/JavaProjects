import java.util.*;

public class VotingSys{
	public static void main(String args[]){
		Scanner inp = new Scanner(System.in);
		System.out.print("Welcome to the online voting system. How many accounts do you want to create: ");
		int n = inp.nextInt();

		VoterAcc E[] = new VoterAcc[n];

		for(int i = 0; i < E.length; i++){
			E[i] = new VoterAcc();
			E[i].makeVoter();
		}

		Canidate a = new Canidate("Donald Trump",70,"Republican");
		Canidate b = new Canidate("Joe Biden",78,"Democrat");
		Canidate c = new Canidate("Ron DeSantis",57,"Republican");
		Canidate d = new Canidate("Beto O' Rourke",60,"Democrat");

		int ch;
		do {
			System.out.println("Here is a list of all your options. Please type in the number of the action you would like to complete");
			System.out.println("1.Login and Vote");
			System.out.println("2.Change Vote");
			System.out.println("3.Reset Password");
			System.out.println("4.Change Account Data");
			System.out.println("5.Exit");
			ch = inp.nextInt();

			switch(ch){
				case 1:
					System.out.print("Please enter your username: ");
					String use = inp.next();
					System.out.print("Please enter your password: ");
					String pas = inp.next();
					boolean foundU = false;
					boolean foundP = false;
                    for(int i = 0; i < E.length; i++){
                        foundU = E[i].searchUser(use);
                        foundP = E[i].searchPass(pas);
                        if(foundU && foundP){
                        	System.out.println("Here is a list of all the canidates. Type the number of the canidate you wish to vote for.");
							System.out.println("1." + a.getName());
							System.out.println("2." + b.getName());
							System.out.println("3." + c.getName());
							System.out.println("4." + d.getName());
							int can = inp.nextInt();
							if(can == 1){E[i].vote(a);}
							else if(can == 2){E[i].vote(b);}
							else if(can == 3){E[i].vote(c);}
							else if(can == 4){E[i].vote(d);}
                            break;
                        }
                    }
                    if(!foundU || !foundP){
                        System.out.println("Login Failed! Please try again!");
                    }
                   	break;

                case 2:
                	System.out.print("Please enter your username: ");
					String use1 = inp.next();
					System.out.print("Please enter your password: ");
					String pas1 = inp.next();
					foundU = false;
					foundP = false;
                    for(int i = 0; i < E.length; i++){
                        foundU = E[i].searchUser(use1);
                        foundP = E[i].searchPass(pas1);
                        if(foundU && foundP){
                        	System.out.println("Here is a list of all the canidates. Type the number of the canidate you wish to change your vote for.");
							System.out.println("1." + a.getName());
							System.out.println("2." + b.getName());
							System.out.println("3." + c.getName());
							System.out.println("4." + d.getName());
							int can = inp.nextInt();
							E[i].removeVote();
							if(can == 1){E[i].vote(a);}
							else if(can == 2){E[i].vote(b);}
							else if(can == 3){E[i].vote(c);}
							else if(can == 4){E[i].vote(d);}
                            break;
                        }
                    }
                    if(!foundU || !foundP){
                        System.out.println("Login Failed! Please try again!");
                    }
                   	break;

                  case 3:
                  	System.out.print("Please enter your username: ");
					String use2 = inp.next();
					System.out.print("Please enter your password: ");
					String pas2 = inp.next();
					foundU = false;
					foundP = false;
                    for(int i = 0; i < E.length; i++){
                        foundU = E[i].searchUser(use2);
                        foundP = E[i].searchPass(pas2);
                        if(foundU && foundP){
                        	System.out.println("Please enter the new password you wish to use.");
                        	String pasnew = inp.nextLine();
							E[i].changePass(pasnew);
                            break;
                        }
                    }
                    if(!foundU || !foundP){
                        System.out.println("Login Failed! Please try again!");
                    }
                   	break;

                  case 4:
                  	System.out.print("Please enter your username: ");
					String use3 = inp.next();
					System.out.print("Please enter your password: ");
					String pas3 = inp.next();
					foundU = false;
					foundP = false;
                    for(int i = 0; i < E.length; i++){
                        foundU = E[i].searchUser(use3);
                        foundP = E[i].searchPass(pas3);
                        if(foundU && foundP){
                        	System.out.println("Please enter your name.");
                        	String namenew = inp.nextLine();
                        	System.out.println("Please enter your age.");
                        	int agenew = inp.nextInt();
                        	System.out.println("Please enter your username.");
                        	String usernew = inp.nextLine();
							E[i].changeDeets(namenew, agenew, usernew);
                            break;
                        }
                    }
                    if(!foundU || !foundP){
                        System.out.println("Login Failed! Please try again!");
                    }
                   	break;

                  case 5:
                    System.out.println(a.getName()+" from the "+a.getParty()+"s has "+a.getCount()+" vote(s)!");
                    System.out.println(b.getName()+" from the "+b.getParty()+"s has "+b.getCount()+" vote(s)!");
                    System.out.println(c.getName()+" from the "+c.getParty()+"s has "+c.getCount()+" vote(s)!");
                    System.out.println(d.getName()+" from the "+d.getParty()+"s has "+d.getCount()+" vote(s)!");
                  	System.out.println("Thanks for participating in our democracy! See you soon!");
                    break;


			}
		}
		while(ch!=5);
	}
}

class Canidate{

	private String name;
	private int age;
	private String party;
	private long count;

	public Canidate(String s, int a, String p){
		name = s;
		age = a;
		party = p;
		count = 0;
	}

	public void addVote(){
		count++;
	}

	public void removeVote(){
		count--;
	}

	public String getName(){
		return name;
	}

	public String getParty(){
		return party;
	}

	public int getAge(){
		return age;
	}

	public long getCount(){
		return count;
	}

}

class VoterAcc{

	private String name;
	private int age;
	private String user;
	private String pass;
	Scanner inpu = new Scanner(System.in);
	private Canidate votehis;

	public void makeVoter(){
		System.out.println("Time to create an account. Please enter the following details.");
		System.out.println("Please enter your name: ");
		name = inpu.next();
		System.out.println("Please enter your age: ");
		age = inpu.nextInt();
		if(age < 18){
			System.out.println("YOUR UNDERAGE!");
			System.exit(0);
		}
		System.out.println("Please enter a username: ");
		user = inpu.next();
		System.out.println("Please enter a password: ");
		pass = inpu.next();
	}

	public String getUser(){
		return user;
	}

	public String getPass(){
		return pass;
	}

	public void changePass(String s){
		pass = s;
	}

	public void changeDeets(String s, int i, String s2){
		name = s;
		age = i;
		user = s2;
	}

	public void vote(Canidate a){
		a.addVote();
		votehis = a;
	}

	public void removeVote(){
		votehis.removeVote();
		votehis = null;
	}

	public boolean searchUser(String a){
        if(user.equals(a)){
            return true;
        }
        return false;
    }

    public boolean searchPass(String a){
        if(pass.equals(a)){
            return true;
        }
        return false;
    }
}