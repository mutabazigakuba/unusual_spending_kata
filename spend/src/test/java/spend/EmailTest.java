package spend;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailTest {

    @InjectMocks
    Email subject;

    @Test
    public void zeroUnusualSpendingByUser()
    {
        UnusualSpending unusualSpending = mock(UnusualSpending.class);
        
    }
}