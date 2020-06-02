package spend.console;

import spend.core.IPayments;
import spend.core.Payment;

public class Expenditures implements IPayments{
 
    
    public List<Payment> getPayments() {

        System.out.println("Enter payment category");
        Scanner scanner = new Scanner(System.in);
        String category = scanner.nextLine();
        System.out.println("Enter payment price");
        String price = scanner.nextLine();
        System.out.println("You entered this "+category+ " and this "+price);
        scanner.close();
        
        Payment payment = new Payment(price, category, id, localDate)

        return new List<Payment> { payment };
    }
}