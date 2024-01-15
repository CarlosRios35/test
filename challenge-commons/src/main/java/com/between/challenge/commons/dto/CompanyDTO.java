package com.between.challenge.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    private Integer id;
    private String nameDescription;
    private Date createAt;
    private Date updateAt;
}
