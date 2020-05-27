package spend;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetermineUnusualSpending implements IDetermineUnusualSpending 
{
    private List<Payments> userPayments;

    public List<HighSpending> Compute(List<Payments> payments, Integer id) 
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
        List<Payments> list = getCategoryPayments(category);
        Integer totalExpenditure = 0;

        for (int i = 0; i < list.size(); i++) 
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime((Date) (list.get(i).Date));
            Integer _month = cal.get(Calendar.MONTH);
            if (_month == month) 
            {
                totalExpenditure += list.get(i).Price;
            }
        }
        return totalExpenditure;
    }

    private List<Payments> getCategoryPayments(Category category) 
    {
        List<Payments> categoryList = new ArrayList<>();
        for (int i = 0; i < userPayments.size(); i++) 
        {
            if (category == userPayments.get(i).Category) 
            {
                categoryList.add(userPayments.get(i));
            }
        }
        return categoryList;
    }

}