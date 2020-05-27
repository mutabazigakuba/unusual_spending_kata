package spend;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UnusualSpendingTest 
{
    @InjectMocks
    UnusualSpending subject;

    @Test
    public void allInterfacesShouldBeCalled()
    {
        IPayments iPayments = mock(IPayments.class);
        IDetermineUnusualSpending determineUnusualSpending = mock(IDetermineUnusualSpending.class);
        IEmail iEmail = mock(IEmail.class);
        subject = new UnusualSpending(iPayments, determineUnusualSpending, iEmail);

        subject.triggerEmail(1);

        verify(iPayments).getPayments(1);
        verify(determineUnusualSpending).Compute(new ArrayList<Payments>());
        verify(iEmail).SendEmail(new ArrayList<HighSpending>());
    }

}