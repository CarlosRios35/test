package com.between.challenge.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO {
    private CompanyDTO companyDTO;
    private Integer productId;
    private BigDecimal finalPrice;
    private BigDecimal price;
    private String startDate;
    private String finalDate;
    private String dateConsulting;
}
