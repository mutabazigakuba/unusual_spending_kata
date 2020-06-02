package spend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import spend.core.*;


public class DetermineUnusualSpendingTest 
{
    DetermineUnusualSpending subject;
    LocalDate currentDate = LocalDate.of(2020, 06, 05);
    LocalDate previousDate = LocalDate.of(2020, 05, 04);
    ILocalDate datetime = new ILocalDate(){
        
        @Override
        public LocalDate getDate()
        {
            return currentDate;
        }
    };
  
    @Test
    public void zeroUnusualPaymentsByUser()
    {
        List<Payment> paymentList = Arrays.asList(
            new Payment(150, Category.TRAVEL, 1, currentDate),
            new Payment(200, Category.TRAVEL, 1, previousDate)
        );
        subject = new DetermineUnusualSpending(datetime);

        List<HighSpending> expectedList = new ArrayList<>();
        List<HighSpending> actualList = subject.Compute(paymentList);

        assertEquals(expectedList, actualList);
    }

    @Test
    public void oneUnusualSpendingByUser()
    {
        List<Payment> paymentList = Arrays.asList(
            new Payment(100, Category.TRAVEL, 1, currentDate),
            new Payment(50, Category.TRAVEL, 1, previousDate)
        );
        subject = new DetermineUnusualSpending(datetime);

        List<HighSpending> expectedList = Arrays.asList(new HighSpending(150, Category.TRAVEL));
        List<HighSpending> actualList = subject.Compute(paymentList);

        assertThat("all of same size", actualList.size(), is(expectedList.size()));
        assertEquals(1, actualList.size());
        assertEquals(Category.TRAVEL, actualList.get(0).Cateogory);
    }

    @Test
    public void moreThanOneUnusualSpendingByuser()
    {
        List<Payment> paymentList = Arrays.asList(
            new Payment(100, Category.TRAVEL, 1, currentDate),
            new Payment(50, Category.TRAVEL, 1, previousDate),
            new Payment(100, Category.GROCERIES, 1, currentDate),
            new Payment(70, Category.GROCERIES, 1, previousDate),
            new Payment(50, Category.ENTERNAINMENT, 1, currentDate)
            );
        subject = new DetermineUnusualSpending(datetime);
        List<HighSpending> unsusalSpendingList = Arrays.asList(
            new HighSpending(150, Category.TRAVEL),
            new HighSpending(170, Category.GROCERIES)
            );

        List<HighSpending> expectedList = unsusalSpendingList;
        List<HighSpending> actualList = subject.Compute(paymentList);

        assertThat("All are of the same size", expectedList.size(), is(actualList.size()));
        assertEquals(2, actualList.size());
        assertEquals(Category.TRAVEL, actualList.get(0).Cateogory);
    }

    @Test
    public void oneUnusualSpendingByUserInDifferentYear()
    {
        LocalDate currentDate = LocalDate.of(2020, 01, 05);
        LocalDate previousDate = LocalDate.of(2019, 12, 04);
        ILocalDate datetime = new ILocalDate(){
            
            @Override
            public LocalDate getDate()
            {
                return currentDate;
            }
        };
        
        List<Payment> paymentList = Arrays.asList(
            new Payment(100, Category.TRAVEL, 1, currentDate),
            new Payment(50, Category.TRAVEL, 1, previousDate)
        );
        subject = new DetermineUnusualSpending(datetime);

        List<HighSpending> expectedList = Arrays.asList(new HighSpending(150, Category.TRAVEL));
        List<HighSpending> actualList = subject.Compute(paymentList);

        assertThat("all of same size", actualList.size(), is(expectedList.size()));
        assertEquals(1, actualList.size());
    }
}