package com.between.challenge.common.dto;

import com.between.challenge.commons.dto.CompanyDTO;
import com.between.challenge.commons.dto.PriceDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class PriceDTOTest {

    @Test
    public void priceDTOValidTest(){
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setPrice(BigDecimal.ZERO);
        priceDTO.setFinalPrice(BigDecimal.ZERO);
        priceDTO.setProductId(1);
        priceDTO.setStartDate("2020-06-14-00.00.00");
        priceDTO.setFinalDate("2020-06-14-00.00.00");

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setNameDescription("SARA");
        companyDTO.setId(1);

        priceDTO.setCompanyDTO(companyDTO);
        Assertions.assertNotNull(priceDTO);
    }
}
