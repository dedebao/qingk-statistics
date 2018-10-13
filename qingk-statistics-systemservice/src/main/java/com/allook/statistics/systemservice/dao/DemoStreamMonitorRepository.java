package com.allook.statistics.systemservice.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


import com.allook.statistics.systemservice.enity.DemoStreamMonitor;





/**
 * 
 * @ClassName: StreamMonitorInfoRepository
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年8月14日 下午3:39:15
 */
@Repository
public interface DemoStreamMonitorRepository extends ElasticsearchRepository<DemoStreamMonitor, String> {

}
