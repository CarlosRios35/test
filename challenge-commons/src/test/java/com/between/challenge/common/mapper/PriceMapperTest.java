package com.between.challenge.common.mapper;

import com.between.challenge.commons.dto.CompanyDTO;
import com.between.challenge.commons.dto.PriceDTO;
import com.between.challenge.commons.mapper.PriceMapper;
import com.between.challenge.domain.model.Company;
import com.between.challenge.domain.model.Prices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PriceMapperTest {

    @InjectMocks
    private PriceMapper priceMapper;

    @Test
    public void validateNullElements(){
        List<PriceDTO> lPriceDTO = priceMapper.priceToPiceDTO(new ArrayList<>(), "2020-12-31-23.59.59");
        Assertions.assertNotNull(lPriceDTO);
        Assertions.assertTrue(lPriceDTO.isEmpty());
    }

    @Test
    public void validateOneElement(){
        Prices prices = new Prices();
        prices.setPrice(BigDecimal.TEN);
        prices.setProductId(1);
        Company company = new Company();
        company.setNameDescription("SARA");
        company.setId(1);
        prices.setCompany(company);
        prices.setStartDate(new Timestamp(System.currentTimeMillis()));
        prices.setEndDate(new Timestamp(System.currentTimeMillis()));

        List<Prices> lPrices = new ArrayList<>();
        lPrices.add(prices);

        List<PriceDTO> lPriceDTO = priceMapper.priceToPiceDTO(lPrices, "2020-12-31-23.59.59");

        Assertions.assertNotNull(lPriceDTO);
        Assertions.assertEquals(prices.getPrice(), lPriceDTO.get(0).getPrice());
        Assertions.assertEquals(prices.getCompany().getId(), lPriceDTO.get(0).getCompanyDTO().getId());
        Assertions.assertEquals(prices.getCompany().getNameDescription(), lPriceDTO.get(0).getCompanyDTO().getNameDescription());
        Assertions.assertEquals(prices.getProductId(), lPriceDTO.get(0).getProductId());
        Assertions.assertEquals(new BigDecimal("12.10"), lPriceDTO.get(0).getFinalPrice());
        Assertions.assertNotNull(lPriceDTO.get(0).getStartDate());
        Assertions.assertNotNull(lPriceDTO.get(0).getFinalDate());
    }

    @Test
    public void validateMoreOneElement(){
        Prices prices = new Prices();
        prices.setPrice(BigDecimal.TEN);
        prices.setProductId(1);
        Company company = new Company();
        company.setNameDescription("SARA");
        company.setId(1);
        prices.setCompany(company);
        prices.setPriority(0);
        prices.setStartDate(new Timestamp(System.currentTimeMillis()));
        prices.setEndDate(new Timestamp(System.currentTimeMillis()));

        Prices prices2 = new Prices();
        prices2.setPrice(BigDecimal.ONE);
        prices2.setProductId(1);
        Company company1 = new Company();
        company1.setNameDescription("SARA");
        company1.setId(1);
        prices.setCompany(company1);
        prices2.setPriority(1);
        prices2.setStartDate(new Timestamp(System.currentTimeMillis()));
        prices2.setEndDate(new Timestamp(System.currentTimeMillis()));

        List<Prices> lPrices = new ArrayList<>();
        lPrices.add(prices);
        lPrices.add(prices2);

        List<PriceDTO> lPriceDTO = priceMapper.priceToPiceDTO(lPrices, "2020-12-31-23.59.59");

        Assertions.assertNotNull(lPriceDTO);
        Assertions.assertEquals(BigDecimal.ONE, lPriceDTO.get(0).getPrice()); //espero el de mayor prioridad.
        Assertions.assertEquals(prices.getCompany().getNameDescription(), lPriceDTO.get(0).getCompanyDTO().getNameDescription());
        Assertions.assertEquals(prices.getProductId(), lPriceDTO.get(0).getProductId());
        Assertions.assertEquals(new BigDecimal("1.21"), lPriceDTO.get(0).getFinalPrice());
        Assertions.assertNotNull(lPriceDTO.get(0).getStartDate());
        Assertions.assertNotNull(lPriceDTO.get(0).getFinalDate());
    }
}
