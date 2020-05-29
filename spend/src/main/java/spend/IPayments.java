package spend;

import java.util.List;

public interface IPayments 
{
    List<Payment> getPayments(Integer id);
}