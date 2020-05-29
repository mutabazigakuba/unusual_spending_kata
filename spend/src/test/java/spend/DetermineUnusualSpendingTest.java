package spend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;


public class DetermineUnusualSpendingTest 
{
    DetermineUnusualSpending subject;

    LocalDate today = LocalDate.now();
    Integer currentMonth = today.getMonthValue();
    Integer previousMonth = currentMonth-1;
    
    @Test
    public void zeroUnusualPaymentsByUser()
    {
        List<Payment> paymentList = Arrays.asList(
            new Payment(150, Category.TRAVEL, 1, LocalDate.of(2020, currentMonth, 22)),
            new Payment(200, Category.TRAVEL, 1, LocalDate.of(2020, previousMonth, 22))
        );
        subject = new DetermineUnusualSpending();

        List<HighSpending> expectedList = new ArrayList<>();
        List<HighSpending> actualList = subject.Compute(paymentList);

        assertEquals(expectedList, actualList);
    }

    @Test
    public void oneUnusualSpendingByUser()
    {
        List<Payment> list = Arrays.asList(
            new Payment(100, Category.TRAVEL, 1, LocalDate.of(2020, currentMonth, 22)),
            new Payment(50, Category.TRAVEL, 1, LocalDate.of(2020, previousMonth, 22)),
            new Payment(20, Category.ENTERNAINMENT, 1, LocalDate.of(2020, previousMonth, 3))
        );
        subject = new DetermineUnusualSpending();

        List<HighSpending> expectedList = Arrays.asList(new HighSpending(150, Category.TRAVEL));
        List<HighSpending> actualList = subject.Compute(list);

        assertThat("all of same size", actualList.size(), is(expectedList.size()));
        assertEquals(1, actualList.size());
        assertEquals(Category.TRAVEL, actualList.get(0).Cateogory);
    }

    @Test
    public void moreThanOneUnusualSpendingByuser()
    {
        List<Payment> list = Arrays.asList(
            new Payment(100, Category.TRAVEL, 1, LocalDate.of(2020, currentMonth, 22)),
            new Payment(50, Category.TRAVEL, 1, LocalDate.of(2020, previousMonth, 22)),
            new Payment(100, Category.GROCERIES, 1, LocalDate.of(2020, currentMonth, 22)),
            new Payment(70, Category.GROCERIES, 1, LocalDate.of(2020, previousMonth, 22)),
            new Payment(50, Category.ENTERNAINMENT, 1, LocalDate.of(2020, currentMonth, 22))
            );
        subject = new DetermineUnusualSpending();
        List<HighSpending> unsusalSpendingList = Arrays.asList(
            new HighSpending(150, Category.TRAVEL),
            new HighSpending(170, Category.GROCERIES)
            );

        List<HighSpending> expectedList = unsusalSpendingList;
        List<HighSpending> actualList = subject.Compute(list);

        assertThat("All are of the same size", expectedList.size(), is(actualList.size()));
        assertEquals(2, actualList.size());
        assertEquals(Category.TRAVEL, actualList.get(0).Cateogory);
    }
}