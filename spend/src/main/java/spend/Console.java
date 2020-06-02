package spend;

import java.util.List;
import java.util.Scanner;

public class Console 
{
    private List<Payment> Payments;
    public void Start() 
    {
        getPaymentFromCommandLine();
    }
    
    private void getPaymentFromCommandLine()
    {
        System.out.println("Enter payment category");
        Scanner scanner = new Scanner(System.in);
        String category = scanner.nextLine();
        System.out.println("Enter payment price");
        String price = scanner.nextLine();
        System.out.println("You entered this "+category+ " and this "+price);
        scanner.close();

    }

   
}