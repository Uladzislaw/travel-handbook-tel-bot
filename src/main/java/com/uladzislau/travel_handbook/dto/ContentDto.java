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
public class ContentDto {

    @NotNull(message = ErrorConstant.NULLABLE_CONTENT_TEXT)
    @Length(min = 1, max = 1000, message = ErrorConstant.CONTENT_TEXT_LEN)
    private String text;
}
