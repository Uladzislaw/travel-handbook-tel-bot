package com.uladzislau.travel_handbook.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstant {

    public static final String NULLABLE_CITY_NAME = "error.city.null.name";
    public static final String CITY_NAME_LENGTH = "error.city.name.len";

    public static final String NULLABLE_CONTENT_TEXT = "error.content.null.text";
    public static final String CONTENT_TEXT_LEN = "error.content.text.len";
}
