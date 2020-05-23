import java.util.Scanner;
public class SimpleAddressBook {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter the First name");
    String firstName=sc.nextLine();
    System.out.println("Enter the last name");
    String lastName=sc.nextLine();
    System.out.println("Enter the  address");
    String address=sc.nextLine();
    System.out.println("Enter the  city");
    String city=sc.nextLine();
    System.out.println("Enter the  state");
    String state=sc.nextLine();
    System.out.println("Enter the  zip");
    int zip=sc.nextInt();
    System.out.println("Enter the  phone number");
    int phoneNumber=sc.nextInt();
    }

}
