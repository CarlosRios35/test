package com.between.challenge.core.service;

import com.between.challenge.commons.dto.PriceDTO;
import com.between.challenge.commons.exception.PricesException;

import java.util.List;

public interface IProductService {

    List<PriceDTO> getPriceByProduct(String country, Integer productId, Integer companyId, String date) throws PricesException;
}
