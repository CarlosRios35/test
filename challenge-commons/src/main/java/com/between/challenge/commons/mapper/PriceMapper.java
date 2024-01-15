package com.between.challenge.commons.mapper;

import com.between.challenge.commons.dto.CompanyDTO;
import com.between.challenge.commons.dto.PriceDTO;
import com.between.challenge.domain.model.Prices;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class PriceMapper {

    public List<PriceDTO> priceToPiceDTO(List<Prices> lprice, String date) {

        if (lprice == null || lprice.isEmpty()) {
            return new ArrayList<>();
        }

        List<PriceDTO> lPriceDTO = new ArrayList<>();

        if(lprice.size() == 1) {
            lPriceDTO.add(mapPriceToPriceDTO(lprice.get(0), date));
            return lPriceDTO;
        } else {
            Prices pricePriority = lprice.get(0);

            for (Prices price : lprice) {
                if (price.getPriority() > pricePriority.getPriority()) {
                    pricePriority = price;
                }
            }
            lPriceDTO.add(mapPriceToPriceDTO(pricePriority, date));
            return lPriceDTO;
        }
    }

    private PriceDTO mapPriceToPriceDTO(Prices prices, String date) {
        PriceDTO priceDTO = new PriceDTO();

        if(prices != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");

            priceDTO.setDateConsulting(date);
            priceDTO.setPrice(prices.getPrice());
            priceDTO.setProductId(prices.getProductId());
            priceDTO.setStartDate(formato.format(prices.getStartDate()));
            priceDTO.setFinalDate(formato.format(prices.getEndDate()));
            priceDTO.setFinalPrice(prices.getPrice().multiply(new BigDecimal("1.21"))); // monto + iva.

            CompanyDTO companyDTO = new CompanyDTO();
            if (prices.getCompany() != null) {
                companyDTO.setId(prices.getCompany().getId());
                companyDTO.setNameDescription(prices.getCompany().getNameDescription());
                companyDTO.setCreateAt(prices.getCompany().getCreateAt());
                companyDTO.setUpdateAt(prices.getCompany().getUpdateAt());
                priceDTO.setCompanyDTO(companyDTO);
            }
        }

        return priceDTO;
    }
}
