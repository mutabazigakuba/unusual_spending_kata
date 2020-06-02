package spend;

import java.util.Date;

public class Payment 
{
    public Integer Price;
    public Category Category;
    public Integer Id;
    public Date Date;

    public Payment(Integer price, Category category, Integer id, Date date){
        Price = price;
        Category = category;
        Id = id;
        Date = date;
    }
}
