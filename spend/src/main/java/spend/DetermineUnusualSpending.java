package spend;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetermineUnusualSpending implements IDetermineUnusualSpending 
{
    private List<Payments> userPayments;

    public List<HighSpending> Compute(List<Payments> payments) 
    {
        userPayments = payments;
        List<HighSpending> unusualSpendings = new ArrayList<>();

        /** 
         * List<Payments> categories = payments.stream().distinct().collect(Collectors.toList());
         * Long numberOfCategories = payments.stream().distinct().count();
         */

        Integer numberOfCategories = Category.values().length;

        for (int i = 0; i < numberOfCategories; i++) 
        {
            Category category = Category.values()[i];

            LocalDate today = LocalDate.now();
            Integer currentMonth = today.getMonthValue();

            Integer previousTotalExpenditures = getMonthlyExpenditures(category, (currentMonth - 1));
            Integer currentTotalExpenditures = getMonthlyExpenditures(category, currentMonth);
            
            if (currentTotalExpenditures > ((1.5) * previousTotalExpenditures)) 
            {
                unusualSpendings.add(new HighSpending(previousTotalExpenditures + currentTotalExpenditures,
                        payments.get(i).Category));
            }
        }
        return unusualSpendings;
    }

    private Integer getMonthlyExpenditures(Category category, Integer month) 
    {
        List<Payments> categoryPayments = getSpecificCategoryPayments(category);
        Integer totalExpenditure = 0;

        for (Payments payments : categoryPayments) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(payments.Date);
            Integer _month = cal.get(Calendar.MONTH);
            if(_month == month)
            {
                totalExpenditure += payments.Price;
            }
        }
        return totalExpenditure;
    }

    private List<Payments> getSpecificCategoryPayments(Category category) 
    {
        List<Payments> categoryList = new ArrayList<>();

        for (Payments payments : userPayments) {
           if(category == payments.Category)
           {
               categoryList.add(payments);
           }
        }
        return categoryList;
    }

}