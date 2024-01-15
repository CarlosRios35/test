package com.between.challenge.app.controller.v1;

import com.between.challenge.commons.dto.PriceDTO;
import com.between.challenge.commons.exception.PricesException;
import com.between.challenge.core.service.IProductService;
import org.springframework.http.ResponseEntity;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping ("/v1")
@AllArgsConstructor
public class ProductRestController {

    private IProductService productService;

    @GetMapping(path = "/{country}/products/{product_id}/{company_id}")
    ResponseEntity<List<PriceDTO>> getPriceByProduct(
            @PathVariable("country") String country,
            @PathVariable("product_id") Integer productId,
            @PathVariable("company_id") Integer companyId,
            @PathParam("date") String date) throws PricesException, ParseException {

        return ResponseEntity.ok( productService.getPriceByProduct(country, productId, companyId, date) );
    }
}
