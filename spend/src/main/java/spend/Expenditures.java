package spend;

import java.util.ArrayList;
import java.util.List;

public class Expenditures implements IPayments
{
    private IPayments IPayments;

    public Expenditures(IPayments iPayments)
    {
        IPayments = iPayments;
    }

    public List<Payments> getPayments(Integer id)
    {
        if (IPayments.getPayments(1).isEmpty())
        {
            return new ArrayList<>();
        }
        return IPayments.getPayments(1);
    }
}