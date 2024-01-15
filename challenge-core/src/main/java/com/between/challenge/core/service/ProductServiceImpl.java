package com.between.challenge.core.service;

import com.between.challenge.commons.constants.Constants;
import com.between.challenge.commons.dto.PriceDTO;
import com.between.challenge.commons.exception.PricesException;
import com.between.challenge.commons.mapper.PriceMapper;
import com.between.challenge.domain.model.Prices;
import com.between.challenge.domain.repository.PricesRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final PricesRepository pricesRepository;

    private PriceMapper priceMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public List<PriceDTO> getPriceByProduct(String country, Integer productId, Integer companyId, String date) throws PricesException {
        try {
            LOGGER.info("[ProductServiceImpl]:getPriceByProduct - consulta producto por precio - INIT");

            requestValidator(productId, companyId, date);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");

            Date fecha = formato.parse(date);

            List<Prices> lPrices = pricesRepository.findAllByDate(companyId, productId, fecha);

            return priceMapper.priceToPiceDTO(lPrices, date);
        }catch (PricesException ex){
            LOGGER.error("[ProductServiceImpl]:getPriceByProduct - consulta producto por precio - ERROR");
            throw ex;
        }catch (Exception ex){
            LOGGER.error("[ProductServiceImpl]:getPriceByProduct - consulta producto por precio - ERROR");
        }
        return null;
    }

    private void requestValidator(Integer productId, Integer companyId, String date) throws PricesException {
        if(productId == null || companyId == null || date == null || productId < 0 || companyId < 0)
            throw new PricesException(HttpStatus.BAD_GATEWAY.value(), Constants.nullMsgError, HttpStatus.BAD_REQUEST);
    }
}
