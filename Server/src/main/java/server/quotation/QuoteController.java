package server.quotation;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.Period;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api")
public class QuoteController {

    public static final int INCLUSIVE_END_DATE = 1;

    @Resource
    private QuotationService quotationService;

    @PostMapping("/quotation")
    public QuotationResponseBody quotation(@RequestHeader("contentType") String contentType,
                          @RequestHeader("authorization") String authorization,
                            @RequestBody QuotationRequestBody request) throws Exception {

        if (!authorization.equals("jwtToken")) {
            throw new Exception();
        }

        LocalDate localStart = LocalDate.parse(request.getStartDate());
        LocalDate localEnd = LocalDate.parse(request.getEndDate());

        int daysInTrip = Period.between(localStart, localEnd).getDays() + INCLUSIVE_END_DATE;

        double rate = quotationService.estimateQuote(daysInTrip, request.getAges());

        return QuotationResponseBody.builder()
                .total(rate)
                .currencyId(request.getCurrencyId())
                .quotationId(1)
                .build();
    }
}
