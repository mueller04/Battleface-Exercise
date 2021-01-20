package server.quotation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuotationResponseBody {

    private double total;
    private String currencyId;
    private int quotationId;
}
