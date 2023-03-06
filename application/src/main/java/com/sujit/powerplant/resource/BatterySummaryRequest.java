package com.sujit.powerplant.resource;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BatterySummaryRequest {

    private String name;

    private Integer postCode;

    private String wattCapacity;

}
