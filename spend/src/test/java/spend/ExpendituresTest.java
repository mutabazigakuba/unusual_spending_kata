package spend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExpendituresTest 
{
    @InjectMocks
    Expenditures subject;

    LocalDate today = LocalDate.now();
    Integer currentMonth = today.getMonthValue();
    Integer previousMonth = currentMonth-1;

    @Test
    public void shouldRetrunZeroPaymentsFromUser()
    {
        IPayments iPayments = mock(IPayments.class);
        when(iPayments.getPayments(1)).thenReturn(new ArrayList<>());
        subject = new Expenditures(iPayments);

        List<Payments> expectedList = new ArrayList<>();
        List<Payments> actualList = subject.getPayments(1);

        assertEquals(expectedList, actualList);
        verify(iPayments).getPayments(1);
    }

    @Test
    public void shouldReturnPaymentsFromUser()
    {
        IPayments iPayments = mock(IPayments.class);
        List<Payments> list = Arrays.asList(
            new Payments(150, Category.TRAVEL, 1, new Date(2020, currentMonth, 12)),
            new Payments(150, Category.TRAVEL, 1, new Date(2020, previousMonth, 12))
        );
        when(iPayments.getPayments(1)).thenReturn(list);
        subject = new Expenditures(iPayments);

        List<Payments> expectedList = list;
        List<Payments> actualList = subject.getPayments(1);

        assertEquals(expectedList, actualList);
        verify(iPayments, atLeast(1)).getPayments(1);
    }
}