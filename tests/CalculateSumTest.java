import org.junit.Test;

import static org.junit.Assert.*;


public class CalculateSumTest {

    @Test
    public void testPaymentsSum() throws Exception {
        Person person = new Person();
        Expenses expenses = new Expenses(), expenses1 = new Expenses();
        int paymentsSum;
        expenses.setServiceAmount(100);
        person.expensesList.add(expenses);
        expenses1.setServiceAmount(200);
        person.expensesList.add(expenses1);
        paymentsSum = person.getPaymentsSum();
        assertEquals(paymentsSum, 300);
    }

}