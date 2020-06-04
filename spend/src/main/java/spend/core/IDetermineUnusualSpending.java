package spend.core;

import java.util.List;

public interface IDetermineUnusualSpending
{
    List<HighSpending> Compute(List<Payment> payments);
}