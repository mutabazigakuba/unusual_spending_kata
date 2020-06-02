package spend.core;

import java.time.LocalDate;

public class DateLocal implements ILocalDate {
    
    public LocalDate getDate() {
        return LocalDate.now();
    }
}