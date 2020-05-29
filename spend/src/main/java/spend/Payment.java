package spend;

import java.time.LocalDate;

public class Payment
{
    public Integer Price;
    public Category Category;
    public Integer Id;
    public LocalDate Date;

    public Payment(Integer price, Category category, Integer id, LocalDate localDate){
        Price = price;
        Category = category;
        Id = id;
        Date = localDate;
    }
}
