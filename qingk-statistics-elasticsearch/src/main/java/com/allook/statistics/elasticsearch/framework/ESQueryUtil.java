/**
 * 
 */
package com.allook.statistics.elasticsearch.framework;

import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.google.common.collect.Lists;

/** 
 * @ClassName: ESQueryUtil 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年9月27日 上午10:00:52  
 */

public class ESQueryUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ESQueryUtil.class);
	
	public static <T> List<T> listData(Querybody querybody,ElasticsearchRepository repository){
		if (querybody == null) {
			return null;
		}
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		
		PageRequest pageRequest=null;
		
		Iterable<T> results =null;

		// 组装精确查询条件
		if (querybody.getTermQuery() != null) {
			Map<String, String> param = querybody.getTermQuery();
			param.forEach((k, v) -> {
				boolQueryBuilder.filter(QueryBuilders.termQuery(k, v));
			});
		}

		// 组装in查询条件
		if (querybody.getInQueryColumn() != null && querybody.getInQueryColumns() != null) {
			boolQueryBuilder
					.filter(QueryBuilders.termQuery(querybody.getInQueryColumn(), querybody.getInQueryColumns()));
		}

		// 组装not in查询条件
		if (querybody.getNotInQueryColumn() != null && querybody.getNotInQueryColumns() != null) {
			boolQueryBuilder
					.filter(QueryBuilders.termQuery(querybody.getNotInQueryColumn(), querybody.getNotInQueryColumns()));
		}

		// 组装range查询条件
		if (querybody.getRangeQueryColumn() != null && querybody.getRangeQueryFrom() != null
				&& querybody.getRangeQueryTo() != null) {
			boolQueryBuilder.filter(QueryBuilders.rangeQuery(querybody.getRangeQueryColumn())
					.from(querybody.getRangeQueryFrom()).to(querybody.getRangeQueryTo()));
		}

		// 组装分页查询条件
		if (querybody.getPageColumn() != null && querybody.getPageFrom() != null && querybody.getPageTo() != null
				&& querybody.getOrderRule() != null) {
			if("desc".equals(querybody.getOrderRule())) {
				pageRequest=new PageRequest(querybody.getPageFrom(),querybody.getPageTo(),Direction.DESC,querybody.getPageColumn());
			}else if("asc".equals(querybody.getOrderRule())) {
				pageRequest=new PageRequest(querybody.getPageFrom(),querybody.getPageTo(),Direction.ASC,querybody.getPageColumn());
			}
			
		}
		logger.debug("es查询语句:{}", boolQueryBuilder);
		
		if(pageRequest!=null) {
			results = repository.search(boolQueryBuilder,pageRequest);
		}else {
			results = repository.search(boolQueryBuilder);
		} 
		List<T> dataList = Lists.newArrayList();
		results.forEach(T -> dataList.add(T));
		return dataList;
	}

}
