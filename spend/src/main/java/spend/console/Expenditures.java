package spend.console;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import spend.core.*;

public class Expenditures implements IPayments 
{
    private List<Payment> consolePayments;
    Scanner scanner = new Scanner(System.in);
    Category selectedCategory;
    Integer expenditure;

    public List<Payment> getPayments(Integer id) 
    {
        Start();
        return consolePayments;
    }

    public void Start()
    {
        List<Payment> payments = new ArrayList<>();
        int numberOfPayments = 1;
        for (int i = 0; i < numberOfPayments; i++) 
        {
            getCategoryFromConsole();
            getExpenditureFromConsole();
            System.out.println("Add another payment? Type Y or N");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            switch (answer) 
            {
                case "Y":
                    numberOfPayments++;
                    break;
                case "N":
                    numberOfPayments = 0;
                    break;
                default:
                    System.out.println("Unknown answer");
                    numberOfPayments = 0;
                    scanner.close();
                    break;
            }
            payments.add(new Payment(expenditure, selectedCategory, 1, LocalDate.now()));
        }
        consolePayments = payments;
        for (Payment payment : consolePayments) 
        {
            System.out.println("You have spent "+payment.Price+ " on "+payment.Category);
        }
    }

    private void getCategoryFromConsole()
    {
        System.out.println("Select payment category");
        int i = 1;
        for (Category category : Category.values()) 
        {
            System.out.println(i + " "+category);
            i++;
        }

        Integer category = scanner.nextInt();  
        switch (category) 
        {
            case 1:
                selectedCategory = Category.TRAVEL;        
                break;
            case 2:
                selectedCategory = Category.GROCERIES;
                break;
            case 3:
                selectedCategory = Category.ENTERNAINMENT;
                break;
            default:
                System.out.println("unknown category!");
                break;
        }
    }

    private void getExpenditureFromConsole()
    {
        System.out.println("Enter category expenditure");
        expenditure = scanner.nextInt(); 
    }
}