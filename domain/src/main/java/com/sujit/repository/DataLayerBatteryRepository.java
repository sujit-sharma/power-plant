package com.sujit.repository;

import com.sujit.entity.BatteryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DataLayerBatteryRepository
    extends PagingAndSortingRepository<BatteryEntity, String>,
        QueryByExampleExecutor<BatteryEntity> {

}
