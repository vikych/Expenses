import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleMenu {
    public static Scanner sc = new Scanner(System.in);
    public static final int EXIT = 9;
    public static ArrayList<Person> personList = new ArrayList<>();

    public void line() {
        System.out.println("--------------------------------------");
    }

    public void printMenu() {
        System.out.println("               MENU                  |");
        System.out.println("1 - add a person to the list         |");
        System.out.println("2 - add an expense to the list       |");
        System.out.println("3 - view expenses from the list      |");
        System.out.println("4 - show transactions to be made     |");
        System.out.println("5 - delete an expense from the list  |");
        System.out.println("6 - search expense in the list       |");
        System.out.println("7 - save list to file                |");
        System.out.println("8 - load list from file              |");
        System.out.println("9 - exit                             |");
        line();
        System.out.print("Enter number of menu > ");
    }

    public void mainMenu(ExpensesList expenses) {
        System.out.println("------------Shopping list-------------");
        printMenu();
        int choice;
        do {
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    addPersonToList(expenses);
                    break;
                }
                case 2: {
                    expenses.createExpense();
                    break;
                }
                case 3: {
                    printPersonListWithExpenses(personList);
                    printTransaction(expenses);
                    break;
                }
                case 4: {
                    printTransaction(expenses);
                    break;
                }
                case 5: {
                    deletePersonMenu(expenses);
                    break;
                }
                case 6: {
                    searchMenu(expenses);
                    break;
                }
                case 7: {
                    expenses.writeToFile(personList);
                    break;
                }
                case 8: {
                    printExpensesListFromFile(expenses);
                    break;
                }
                case 9:{
                    break;
                }
                default: {
                    System.out.println("Error. Please try again");
                    line();
                    printMenu();
                }
            }
            if (choice >= 1 && choice < EXIT) {
                mainMenu(expenses);
                choice = EXIT;
            }
        } while (choice != EXIT);
    }

    public void addPersonToList(ExpensesList expenses) {

        Person person = expenses.createPerson();
        personList.add(person);
        line();
        System.out.println(person + "\nis added to the list!");
        line();
    }

    public void printPersonListWithExpenses(ArrayList<Person> personList) {
        Expenses expense;
        Person person;
        if (personList.isEmpty()) {
            line();
            System.out.println("Your list is empty");
        } else {
            line();
            if (personList.size() == 1)
                System.out.print("There is " + personList.size() + " person in your list:\n");
            else
                System.out.print("There are " + personList.size() + " person in your list:\n\n");

            for (int i = 0; i < personList.size(); i++) {
                if (personList.size() != 1) {
                    System.out.println((i + 1) + ") Person expense: \n");
                }
                person = personList.get(i);
                if (person.expensesList.isEmpty()) {
                    System.out.println(person + "\n");
                } else {
                    for (int j = 0; j < person.expensesList.size(); j++) {
                        expense = person.expensesList.get(j);
                        System.out.println(person + "\n" + expense + "\n");
                    }
                }
                ExpensesList.calculateSum(person);
            }
            line();
        }
    }

    public static boolean printPersonListOnly() {
        if (personList.isEmpty()) {
            System.out.println("Your list is empty");
            return false;
        } else {
            for (int i = 0; i < personList.size(); i++) {
                System.out.println("\n" + (i + 1) + " Person: " + personList.get(i).getPersonName() + "\n");
            }
            return true;
        }
    }

    public void printTransaction(ExpensesList expenses) {
        if (!personList.isEmpty()) {
            expenses.calculateTransaction(personList);
            System.out.println("--------------------------------------");
        }
        else throw new RuntimeException("List is empty!");
    }

    public void printExpensesListFromFile(ExpensesList expenses) {
        line();
        String textFromFile = expenses.readFromFile();
        System.out.println(textFromFile);
        line();
    }

    public void deletePersonMenu(ExpensesList expenses) {
        if (personList.isEmpty()) {
            System.out.println("Your list is empty");
        } else {
            line();
            System.out.println("What do you want to delete from the list?");
            System.out.println("1 - all persons");
            System.out.println("2 - only one person");
            line();
            System.out.print("Enter your choice > ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    expenses.deleteAllPersons(personList);
                    line();
                    System.out.println("Now your list is empty");
                    line();
                    break;
                }
                case 2: {
                    printPersonListOnly();
                    expenses.deletePerson(personList);
                    break;
                }
                default: {
                    line();
                    System.out.println("Error");
                    line();
                }
            }
        }
    }

    public void searchMenu(ExpensesList expenses) {
        if (personList.isEmpty()) {
            System.out.println("Your list is empty");
        } else {
            line();
            System.out.println("What do you want to search in the list?");
            System.out.println("1 - search expenses by amount");
            System.out.println("2 - search person expense");
            line();
            System.out.print("Enter your choice > ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    ArrayList<Person> newPersonList = expenses.searchByAmount(personList);
                    printPersonListWithExpenses(newPersonList);
                    break;
                }
                case 2: {
                    printPersonListOnly();
                    ArrayList<Person> newPersonList = expenses.searchByName(personList);
                    printPersonListWithExpenses(newPersonList);
                    break;
                }
                default: {
                    line();
                    System.out.println("Error");
                    line();
                }
            }
        }
    }
}


