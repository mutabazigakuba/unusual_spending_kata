package spend;

import java.util.List;

public interface IDetermineUnusualSpending
{
    List<HighSpending> Compute(List<Payments> payments);
}