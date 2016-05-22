import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class ExpensesList {
    public static Scanner sc = new Scanner(System.in);
    public static String FILENAME = "ExpensesListFile.txt";


    public Person createPerson() {
        Person person = new Person();

        System.out.print("Enter person name > ");
        String personName;
        while ((personName = sc.nextLine()).isEmpty()) {
        }
        person.setPersonName(personName);
        return person;
    }

    public void createExpense() {
        Expenses expense = new Expenses();
        Person person;
        if (ConsoleMenu.printPersonListOnly()) {
            System.out.print("Enter whom you want to add expense: ");
            String personName;
            while ((personName = sc.nextLine()).isEmpty()) {
            }
            boolean found = false;
            for (int i = 0; i < ConsoleMenu.personList.size(); i++) {
                person = ConsoleMenu.personList.get(i);
                if (person.getPersonName().equals(personName)) {
                    System.out.println("Enter expense for " + person.getPersonName() + ":");
                    System.out.print("Enter service > ");
                    String service;
                    while ((service = sc.nextLine()).isEmpty()) {
                    }
                    expense.setService(service);

                    System.out.print("Enter amount of service > ");
                    int amount = sc.nextInt();
                    expense.setServiceAmount(amount);

                    person.addExpense(expense);
                    found = true;
                }
            }
            if (!found) throw new RuntimeException("Not founded person with that name");
        }
    }

    public static void calculateSum(Person personToCalculate) {
        if (!ConsoleMenu.personList.isEmpty()) {
            System.out.println("The sum of all payments made by " + personToCalculate.getPersonName() + ": " +
                    personToCalculate.getPaymentsSum() + "\n");
        } else
            System.out.println("List with persons is empty!");
    }


    public void calculateTransaction(ArrayList<Person> personList) {
        ArrayList<Person> personsToReceive = new ArrayList<>();
        ArrayList<Person> personsToGive = new ArrayList<>();
        int average = calculateAverage(personList);

        for (Person person : personList) {
            if (person.getPaymentsSum() > average) {
                personsToReceive.add(person);
            } else if (person.getPaymentsSum() < average) {
                personsToGive.add(person);
            }
        }

        int owe = 0;
        for (Person person : personsToReceive) {
            owe += (person.getPaymentsSum() - average);
        }

        if (owe > 0) {
            for (Person personToReceive : personsToGive) {
                int owes = average - personToReceive.getPaymentsSum();
                int singleOwes = owes / personsToReceive.size();
                for (Person personToGive : personsToReceive) {
                    System.out.println(personToReceive.getPersonName() + " should send " + singleOwes + "$ to " + personToGive.getPersonName());
                }
            }
        }
        else
            System.out.println("Everyone have average sum of payments");
    }

    public int calculateAverage(ArrayList<Person> personList) {
        Person person;
        int totalSum = 0, countOfPersons = 0, averagePayments;

        for (Person personFromList : personList) {
            person = personFromList;
            totalSum += person.getPaymentsSum();
            countOfPersons++;
        }
        averagePayments = totalSum / countOfPersons;

        System.out.print("\nTotal sum of all persons payments: " + totalSum + '\n');
        System.out.println("Average to be paid by mate: " + averagePayments);
        System.out.println("--------------------------------------");

        return averagePayments;
    }

    public void deleteAllPersons(ArrayList<Person> personList) {
        personList.removeAll(personList);
    }

    public void deletePerson(ArrayList<Person> personList) {
        Person person;
        System.out.print("Enter whom you want to delete from list: ");
        String deletePerson;
        while ((deletePerson = sc.nextLine()).isEmpty()) {
        }
        boolean found = false;
        for (int i = 0; i < personList.size(); i++) {
            person = personList.get(i);
            if (person.getPersonName().equals(deletePerson)) {
                personList.remove(i);
                System.out.println(person + "\nwas deleted from your list");
                found = true;
            }
        }
        if (!found) {
            System.out.println("That person isn't in the list");
        }
    }

    public ArrayList<Person> searchByName(ArrayList<Person> personList) {
        Person person;
        ArrayList<Person> newPersonList = new ArrayList<>();
        System.out.print("Enter whom do you want to search: ");
        String personName;
        while ((personName = sc.nextLine()).isEmpty()) {
        }
        boolean found = false;
        for (Person personFromList : personList) {
            person = personFromList;
            if (person.getPersonName().equals(personName)) {
                newPersonList.add(person);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Not founded person with that name");
        }
        return newPersonList;
    }

    public ArrayList<Person> searchByAmount(ArrayList<Person> personList) {
        Person person;
        ArrayList<Person> newPersonList = new ArrayList<>();
        System.out.print("Enter what payments sum do you want to search: ");
        int paymentsSumToSearch;
        paymentsSumToSearch = sc.nextInt();
        boolean found = false;
        for (Person personFromList : personList) {
            person = personFromList;
            if (person.getPaymentsSum() == paymentsSumToSearch) {
                newPersonList.add(person);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Not founded person with that payments sum");
        }
        return newPersonList;
    }
    public void writeToFile(ArrayList<Person> personList) {
        File expensesListFile = new File(FILENAME);
        Person person;
        Expenses expense;
        try {
            if (!expensesListFile.exists()) {
                expensesListFile.createNewFile();
            }
            PrintWriter out = new PrintWriter(expensesListFile.getAbsoluteFile());
            try {
                for (int i = 0; i < personList.size(); i++) {
                    person = personList.get(i);
                    for (int j = 0; j < person.expensesList.size(); j++) {
                        expense = person.expensesList.get(j);
                        out.print('\n');
                        out.print((i + 1) + ") Info about expense: \n" + person + "\n" + expense + "\n");
                    }
                }
            } finally {
                System.out.println("Your list is added to the file!");
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFromFile() {
        StringBuilder sb = new StringBuilder();
        File expensesListFile = new File(FILENAME);
        fileExists();
        try {
            BufferedReader in = new BufferedReader(new FileReader(expensesListFile.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void fileExists() {
        File expensesListFile = new File(FILENAME);
        try {
            if (!expensesListFile.exists()) {
                expensesListFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
