import java.util.ArrayList;

public class Person {

    public ArrayList<Expenses> expensesList = new ArrayList<>();

    private String personName;

    public Person() {
        this.personName = "name";
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void addExpense(Expenses expenses) {
        expensesList.add(expenses);
    }

    public int getPaymentsSum() {
        int sum = 0;
        for (Expenses expenses : expensesList) {
            sum += expenses.getServiceAmount();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Person name: " + '"' + this.getPersonName() + '"';
    }
}
