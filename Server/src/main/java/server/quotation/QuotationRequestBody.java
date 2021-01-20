package server.quotation;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class QuotationRequestBody {

    private List<Integer> ages;
    private String currencyId;
    private String startDate;
    private String endDate;
}
