package com.allook.statistics.systemservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.allook.statistics.systemservice.enity.DemoAppInfo;

@Mapper
public interface DemoAppInfoMapper {

	@Select("select app_information_key,url from t_app_information where app_information_key=#{app_information_key}")
	DemoAppInfo getOneByAppKey(String app_information_key);

}
