package spend.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DetermineUnusualSpending implements IDetermineUnusualSpending 
{
    private List<Payment> userPayments;
    private ILocalDate _localDate;

    public DetermineUnusualSpending(ILocalDate localDate) {
        _localDate = localDate;
    }
    public List<HighSpending> Compute(List<Payment> payments) 
    {
        userPayments = payments;
        List<HighSpending> unusualSpendings = new ArrayList<>();

        LocalDate today = _localDate.getDate();
        Integer currentMonth = today.getMonthValue();
        Integer previousMonth = currentMonth == 1 ? 12 : currentMonth - 1;

        for (Category category : Category.values())
        {
            Integer previousTotalExpenditures = getMonthlyExpenditures(category, previousMonth);
            Integer currentTotalExpenditures = getMonthlyExpenditures(category, currentMonth);
            
            if (currentTotalExpenditures > ((1.5) * previousTotalExpenditures)) 
            {
                unusualSpendings.add(new HighSpending(previousTotalExpenditures + currentTotalExpenditures,
                        category));
            }
        }
        return unusualSpendings;
    }

    private Integer getMonthlyExpenditures(Category category, Integer month) 
    {
        List<Payment> categoryPayments = getSpecificCategoryPayments(category);
        Integer totalExpenditure = 0;

        for (Payment payments : categoryPayments) 
        {
            Integer _month = payments.Date.getMonthValue();
            if(_month == month)
            {
                totalExpenditure += payments.Price;
            }
        }
        return totalExpenditure;
    }

    private List<Payment> getSpecificCategoryPayments(Category category) 
    {
        List<Payment> categoryList = new ArrayList<>();

        for (Payment payments : userPayments) 
        {
           if(category == payments.Category)
           {
               categoryList.add(payments);
           }
        }
        return categoryList;
    }

}