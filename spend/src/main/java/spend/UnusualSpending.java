package spend;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class UnusualSpending implements IDetermineUnusualSpending
{
    private IPayments Payment;
    private IDetermineUnusualSpending DetermineUnusualSpending;
    private IEmail Email;
    
    public UnusualSpending(IPayments payments, IDetermineUnusualSpending determineUnusualSpending, IEmail email)
    {
        Payment = payments;
        DetermineUnusualSpending = determineUnusualSpending;
        Email= email;
    }

    public void triggerEmail(Integer id)
    { 
        List<Payments> userPayments = Payment.getPayments(id);
        List<HighSpending> listOfHighSpendings = DetermineUnusualSpending.Compute(userPayments, id);
        Email.SendEmail(listOfHighSpendings);
    }

    public List<HighSpending> Compute(List<Payments> payments, Integer id)
    {
        List<Payments> userPayments = Payment.getPayments(id);
        
        Integer previousTotalExpenditures = getMonthlyExpenditures(userPayments, 4);
        Integer currentTotalExpenditures = getMonthlyExpenditures(userPayments, 5);
        List<HighSpending> highSpendings = new ArrayList<>();

        if(currentTotalExpenditures > ((1.5)*previousTotalExpenditures))
        {
            highSpendings.add(new HighSpending(previousTotalExpenditures+currentTotalExpenditures, userPayments.get(0).Category));
        }
        return highSpendings;
    }

    private Integer getMonthlyExpenditures(List<Payments> list, Integer month)
    {
        Integer totalExpenditure = 0;
        for (int i = 0; i < list.size(); i++) 
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime((Date) (list.get(i).Date));
            Integer _month = cal.get(Calendar.MONTH);
            if(_month == month){
                totalExpenditure += list.get(i).Price;
            }
        }
        return totalExpenditure;
    }
}
