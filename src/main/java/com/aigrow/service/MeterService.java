package com.aigrow.service;

import com.aigrow.model.dto.MeterDto;
import com.aigrow.model.dto.Page;
import java.util.List;

/**
 * @author JDB
 */
public interface MeterService {
    /**
     * 计算每个公司的运费
     * @param weight
     * @param destination
     * @param page
     * @param start
     * @return
     */
    List cost(int weight, String destination,Page page, String start);

    /**
     * 获取对应公司名字的计价表
     * @param companyName
     * @param page
     * @return
     */
    List<MeterDto> getCompanyMeter(String companyName, Page page);
}
