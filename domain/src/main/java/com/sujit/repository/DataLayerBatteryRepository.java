package com.sujit.repository;

import com.sujit.entity.BatteryEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DataLayerBatteryRepository
    extends PagingAndSortingRepository<BatteryEntity, String>,
        QueryByExampleExecutor<BatteryEntity> {


//  Page<BatteryEntity> saveAll(List<BatteryEntity> list, Pageable pageable);

//    List<BatteryEntity> saveAll(List<BatteryEntity> entities);
}
