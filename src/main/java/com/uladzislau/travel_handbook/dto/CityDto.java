package com.uladzislau.travel_handbook.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uladzislau.travel_handbook.constant.ErrorConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityDto {

    @NotNull(message = ErrorConstant.NULLABLE_CITY_NAME)
    @Length(min = 2, max = 70, message = ErrorConstant.CITY_NAME_LENGTH)
    private String name;
}
