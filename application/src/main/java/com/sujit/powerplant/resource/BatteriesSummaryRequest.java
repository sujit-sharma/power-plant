package com.sujit.powerplant.resource;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class BatteriesSummaryRequest {

    private Pageable page ;

    private List<BatterySummaryRequest> batteries;


}
