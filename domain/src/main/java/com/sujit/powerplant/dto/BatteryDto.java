package com.sujit.powerplant.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BatteryDto implements Serializable {

    private static final long serialVersionUID = -12456875L;

    private String batteryId;

    private String name;

    private Integer postCode;

    private String wattCapacity;

}
