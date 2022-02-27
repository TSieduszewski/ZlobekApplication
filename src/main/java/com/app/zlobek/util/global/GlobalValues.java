package com.app.zlobek.util.global;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GlobalValues {

    public static final int singleMealPrice = 10;
    public static final int tuition = 690;
    public static final LocalDate actualMonth = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-01")));
    public static final LocalDate previousMonth = LocalDate.parse(LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-01")));
    public static final String extraFee = "Błędnie zgłoszono obecność/nieobecność dziecka. Naliczono opłatę. Sprawdź w płatnościach. Jeśli uważasz, że to błąd, skontaktuj się z dyrekcją";
    public static final String tuitionInfo = "Rozpoczął się nowy miesiąc. Naliczono czesne w wysokości";
}
