package spend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public void shouldRetrunPaymentsFromUser()
    {
        subject = new Expenditures();

        List<Payments> expectedList = new ArrayList<>();
        List<Payments> actualList = subject.getPayments(1);

        assertEquals(expectedList, actualList);
    }
}