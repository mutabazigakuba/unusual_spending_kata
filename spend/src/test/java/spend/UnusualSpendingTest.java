package spend;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import static org.hamcrest.CoreMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class UnusualSpendingTest 
{
    @InjectMocks
    UnusualSpending subject;

    @Test
    public void allInterfacesShouldBeCalled()
    {
        IPayments iPayments = mock(IPayments.class);
        when(iPayments.getPayments(1)).thenReturn(new ArrayList<>());
        IDetermineUnusualSpending determineUnusualSpending = mock(IDetermineUnusualSpending.class);
        when(determineUnusualSpending.Compute(new ArrayList<>(), 1)).thenReturn(new ArrayList<>());
        IEmail iEmail = mock(IEmail.class);
        subject = new UnusualSpending(iPayments, determineUnusualSpending, iEmail);

        subject.triggerEmail(1);

        verify(iPayments).getPayments(1);
        verify(determineUnusualSpending).Compute(new ArrayList<Payments>(), 1);
        verify(iEmail).SendEmail(new ArrayList<HighSpending>());
    }

    @Test
    public void zeroUnusualPaymentsByUser()
    {
        IPayments iPayments = mock(IPayments.class);
        List<Payments> list = new ArrayList<>();
        list.add(new Payments(150, "Travel", 1, new Date(2020, 05, 22)));
        list.add(new Payments(200, "Travel", 1, new Date(2020, 04, 22)));
        when(iPayments.getPayments(1)).thenReturn(list);
        IDetermineUnusualSpending determineUnusualSpending = mock(IDetermineUnusualSpending.class);
        IEmail iEmail = mock(IEmail.class);
        subject = new UnusualSpending(iPayments, determineUnusualSpending, iEmail);

        List<HighSpending> actualList = subject.Compute(list, 1);

        assertEquals(new ArrayList<>(), actualList);
        verify(iPayments).getPayments(1);
    }

    @Test
    public void oneUnusualSpendingByUser()
    {
        IPayments iPayments = mock(IPayments.class);
        List<Payments> list = new ArrayList<>();
        list.add(new Payments(100, "Travel", 1, new Date(2020, 05, 22)));
        list.add(new Payments(50, "Travel", 1, new Date(2020, 04, 22)));
        list.add(new Payments(20, "Enternaiment", 1, new Date(2020, 4, 3)));
        when(iPayments.getPayments(1)).thenReturn(list);
        IDetermineUnusualSpending determineUnusualSpending = mock(IDetermineUnusualSpending.class);
        IEmail iEmail = mock(IEmail.class);
        subject = new UnusualSpending(iPayments, determineUnusualSpending, iEmail);

        List<HighSpending> expectedList = Arrays.asList(new HighSpending(150, "Travel"));
        List<HighSpending> actualList = subject.Compute(list, 1);

        assertThat("all of same size", actualList.size(), is(expectedList.size()));
        verify(iPayments).getPayments(1);
    }

    @Test
    public void moreThanOneUnusualSpendingByuser()
    {
        IPayments iPayments = mock(IPayments.class);
        List<Payments> list = new ArrayList<>();
        list.add(new Payments(100, "Travel", 1, new Date(2020, 05, 22)));
        list.add(new Payments(50, "Travel", 1, new Date(2020, 04, 22)));
        list.add(new Payments(100, "Groceries", 1, new Date(2020, 05, 22)));
        list.add(new Payments(50, "Groceries", 1, new Date(2020, 04, 22)));
        list.add(new Payments(50, "Enternaiment", 1, new Date(2020, 04, 22)));
        when(iPayments.getPayments(1)).thenReturn(list);
        IDetermineUnusualSpending determineUnusualSpending = mock(IDetermineUnusualSpending.class);
        IEmail iEmail = mock(IEmail.class);
        subject = new UnusualSpending(iPayments, determineUnusualSpending, iEmail);
        List<HighSpending> unsusalSpendingList = new ArrayList<>();
        unsusalSpendingList.add(new HighSpending(150, "Travel"));
        unsusalSpendingList.add(new HighSpending(150, "Groceries"));

        List<HighSpending> expectedList = unsusalSpendingList;
        List<HighSpending> actualList = subject.Compute(list, 1);

        assertThat("All of same size", expectedList.size(), is(actualList.size()));
        verify(iPayments).getPayments(1);
    }
}