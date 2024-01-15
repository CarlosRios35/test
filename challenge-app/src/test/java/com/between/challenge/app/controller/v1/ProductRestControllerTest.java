package com.between.challenge.app.controller.v1;

import com.between.challenge.app.Application;
import com.between.challenge.commons.dto.PriceDTO;
import com.between.challenge.core.service.IProductService;
import com.between.challenge.domain.repository.PricesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IProductService productService;

    @Autowired
    private PricesRepository pricesRepository;

    @Autowired
    private ObjectMapper objectMapper;
    private String result1= "petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)";
    private String result2= "petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)";
    private String result3= "petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)";
    private String result4= "petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)";
    private String result5= "petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)";

    @Test
    void getPriceByProductTest1() throws Exception {

        String content = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/{country}/products/{product_id}/{company_id}","es",35455,1)
                .queryParam("date", "2020-06-14-10.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse().getContentAsString();

        String result = buildString(content);

        Assertions.assertEquals(result1, result);
    }

    @Test
    void getPriceByProductTest2() throws Exception {

        String content = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/{country}/products/{product_id}/{company_id}","es",35455,1)
                .queryParam("date", "2020-06-14-16.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse().getContentAsString();

        String result = buildString(content);

        Assertions.assertEquals(result2, result);
    }

    @Test
    void getPriceByProductTest3() throws Exception {

        String content = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/{country}/products/{product_id}/{company_id}","es",35455,1)
                .queryParam("date", "2020-06-14-21.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse().getContentAsString();

        String result = buildString(content);

        Assertions.assertEquals(result3, result);
    }

    @Test
    void getPriceByProductTest4() throws Exception {

        String content = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/{country}/products/{product_id}/{company_id}","es",35455,1)
                .queryParam("date", "2020-06-15-10.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse().getContentAsString();

        String result = buildString(content);

        Assertions.assertEquals(result4, result);
    }

    @Test
    void getPriceByProductTest5() throws Exception {

        String content = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/{country}/products/{product_id}/{company_id}","es",35455,1)
                .queryParam("date", "2020-06-16-21.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse().getContentAsString();

        String result = buildString(content);

        Assertions.assertEquals(result5, result);
    }

    private String buildString(String content ) throws JsonProcessingException {
        List<PriceDTO> lPriceDTO = objectMapper.readValue(content, new TypeReference<List<PriceDTO>>() {});
        PriceDTO priceDTO = lPriceDTO.get(0);

        StringBuilder stringBuffer = new StringBuilder();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime dateTime = LocalDateTime.parse(priceDTO.getDateConsulting(), formatter);

        String day = dateTime.format(DateTimeFormatter.ofPattern("dd"));
        String hour = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));

        stringBuffer.append("petición a las ")
        .append(hour)
        .append(" del día ")
        .append(day)
        .append(" del producto ")
        .append(priceDTO.getProductId())
        .append(" para la brand ");
        if(priceDTO.getCompanyDTO() != null) {
            stringBuffer.append(priceDTO.getCompanyDTO().getId())
           .append(" (")
           .append(priceDTO.getCompanyDTO().getNameDescription())
           .append(")");
        }

        return  stringBuffer.toString();
    }
}
