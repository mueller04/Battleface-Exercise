package server.quotation;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Service
public class QuotationService {

    public static final int FIXED_RATE = 3;

    public double estimateQuote(double daysInTrip, List<Integer> ages) {

        double rate = ages
                .stream()
                .mapToDouble(Integer::doubleValue)
                .reduce(.0, (double subtotal, double age) -> subtotal + estimateQuotePerTraveler(daysInTrip, age));

        return rate;
    }

    private double estimateQuotePerTraveler(double daysInTrip, double age) {
        double ageLoad = 0;

        if (age >= 18) {
            ageLoad += 0.6;
        }
        //admitted shortcut, I saved myself typing but I would trigger only one assignment with age > 30 && age <=40
        if (age > 30) {
            ageLoad += 0.1;
        }
        if (age > 40) {
            ageLoad += 0.1;
        }
        if (age > 50) {
            ageLoad += 0.1;
        }
        if (age > 60 && age <= 70) {
            ageLoad += 0.1;
        }
        BigDecimal bd = BigDecimal.valueOf(FIXED_RATE * ageLoad * daysInTrip);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
