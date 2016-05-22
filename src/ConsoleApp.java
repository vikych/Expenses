
public class ConsoleApp {

    public static void main(String[] args) {
        ExpensesList expenses = new ExpensesList();
        ConsoleMenu menu = new ConsoleMenu();
        menu.mainMenu(expenses);
    }
}