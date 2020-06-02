package spend.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import spend.core.IPayments;
import spend.core.Payment;

public class Expenditures implements IPayments {
    @Override
    public List<Payment> getPayments(Integer id) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select payment category");
        String category = scanner.nextLine();

        System.out.println("Enter payment price");
        String price = scanner.nextLine();

        System.out.println("You entered this "+category+ " and this "+price);
        scanner.close();

        return  new ArrayList<>();
    }
}