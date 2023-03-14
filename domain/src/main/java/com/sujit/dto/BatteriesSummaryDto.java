package com.sujit.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BatteriesSummaryDto {

    private List<String> batteriesName = new ArrayList<>();

    private Double totalWattCapacity;

    private Double averageWattCapacity;
}
