package com.sujit.usecase.validation;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constraints {


    public static final String BATTERY_NAME = "Battery name";
    public static final String POSTCODE = "post code";
    public static final String WATT_CAPACITY = "watt capacity";

    public static final String POST_CODE_SHOULD_BE_POSITIVE_INTEGER = "post code should be non negative value";
    public static final String BATTERY_NAME_SHOULD_NOT_BE_EMPTY = "Battery name should not be empty";
    public static final String WATT_CAPACITY_SHOULD_BE_GREATER_THEN_ZERO = "watt capacity should be greater then 0 and less then 5000";
    public static final String WATT_CAPACITY_SHOULD_BE_LESS_THEN_5000 = "watt capacity should be greater then 0 and less then 5000";

}