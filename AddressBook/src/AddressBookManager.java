import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBookManager {
    private enum Action {
        ADD_PERSON,
        FIND_PERSON,
        DISPLAY_ALL,
        EXIT
    }

    private enum FilterOption {
        FIRST_NAME,
        lAST_NAME
    }

    private AddressBook dataFile;
    private Scanner in;
    private List<Person> people;

    public AddressBookManager(AddressBook dataFile) {
        in = new Scanner(System.in);
        this.dataFile = dataFile;
        try {
            people = dataFile.loadAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public AddressBookManager(File file) {
        this(new AddressBook(file));
    }

   

	private List<Person> findPerson(String searchString, FilterOption filter) {
        switch (filter) {
            case FIRST_NAME: return people.stream().filter(person -> person.getlastname().equals(searchString)).collect(Collectors.toList());
            case lAST_NAME: return people.stream().filter(person -> person.getFirstName().equals(searchString)).collect(Collectors.toList());
            default:
                System.out.println("Invalid filter option");
                return new ArrayList<>();
        }
    }

    public void run() {
        while(true) {
            Action action = showMainMenuAndGetSelection();
            switch (action) {
                case ADD_PERSON:
                    Person person = getPersonInformation();
                    dataFile.save(person);
                    people.add(person);
                    break;
                case FIND_PERSON:
                    FilterOption selectedFilter = showFindPersonFilterOptionsAndGetSelection();
                    System.out.print("Enter name: ");
                    String searchString = in.nextLine();
                    List<Person> filteredPeople = findPerson(searchString, selectedFilter);
                    if (filteredPeople.size() == 0) {
                        System.out.println("No matches");
                    } else {
                        for (Person p : filteredPeople)
                            System.out.println(p);
                    }
                    break;
                case DISPLAY_ALL:
                    System.out.println(this.people);
                    System.out.println();
                    break;
                case EXIT:
                    System.out.println("Exiting Program");
                    System.exit(0);
                    break;
            }
        }
    }

    private Action showMainMenuAndGetSelection() {
        System.out.println("1. Add person");
        System.out.println("2. Find person");
        System.out.println("3. Show all contacts");
        System.out.println("4. Close program");
        String choice;
        do {
            choice = in.nextLine();
            switch (choice) {
                case "1": return Action.ADD_PERSON;
                case "2": return Action.FIND_PERSON;
                case "3": return Action.DISPLAY_ALL;
                case "4": return Action.EXIT;
                default: System.out.println("Enter a number from 1 to 4");
            }
        } while (!choice.equals("4"));
        return null; //should never reach here
    }

    private Person getPersonInformation() {
        System.out.println("Enter first name: ");
        String firstName = in.nextLine();
        System.out.println("Enter lastname: ");
        String surname = in.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = in.nextLine();
        System.out.println("Enter email: ");
        String email = in.nextLine();
        System.out.println("Enter addres: ");
        String address = in.nextLine();
        return new Person(firstName, surname, phoneNumber, email, address);
    }

    private FilterOption showFindPersonFilterOptionsAndGetSelection() {
        System.out.println("1. Find with name");
        System.out.println("2. Find with surname");
        System.out.println();
        String choice;
        do {
            choice = in.nextLine();
            switch (choice) {
                case "1":  return FilterOption.FIRST_NAME;
                case "2": return FilterOption.lAST_NAME;
                default: System.out.print("Choose 1 or 2");
            }
        } while (!choice.equals("1") && !choice.equals("2"));
        return null; //should never reach here
    }
}