package spend;

import java.util.List;

public class UnusualSpending
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
        List<HighSpending> listOfHighSpendings = DetermineUnusualSpending.Compute(userPayments);
        Email.SendEmail(listOfHighSpendings);
    }
    
}
