package spend;

import java.util.ArrayList;
import java.util.List;

public class DetermineUnusualSpending implements IDetermineUnusualSpending 
{
    private List<Payment> userPayments;

    public List<HighSpending> Compute(List<Payment> payments) 
    {
        userPayments = payments;
        List<HighSpending> unusualSpendings = new ArrayList<>();

        /**
         * LocalDate today = LocalDate.now();
            Integer currentMonth = today.getMonthValue();
         */

        Integer currentMonth = userPayments.get(0).Date.getMonthValue();
        Integer previousMonth = currentMonth - 1;

        if(previousMonth < 0){
            previousMonth = 12;
        }

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