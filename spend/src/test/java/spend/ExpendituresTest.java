package spend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;

public class ExpendituresTest 
{
    @InjectMocks
    Expenditures subject;

    @Test
    public void shouldRetrunZeroPaymentsFromUser()
    {
        IPayments iPayments = mock(IPayments.class);
        when(iPayments.getPayments(1)).thenReturn(new ArrayList<>());
        subject = new Expenditures(iPayments);

        List<Payments> expectedList = new ArrayList<>();
        List<Payments> actualList = subject.getPayments(1);

        assertEquals(expectedList, actualList);
    }

    @Test
    public void shouldReturnPaymentsFromUser()
    {
        IPayments iPayments = mock(IPayments.class);
        List<Payments> list = new ArrayList<>();
        list.add(new Payments(150, "Travel", 1, new Date(2020, 05, 12)));
        list.add(new Payments(150, "Travel", 1, new Date(2020, 05, 12)));
        when(iPayments.getPayments(1)).thenReturn(list);
        subject = new Expenditures(iPayments);

        List<Payments> expectedList = list;
        List<Payments> actualList = subject.getPayments(1);

        assertEquals(expectedList, actualList);
    }
}