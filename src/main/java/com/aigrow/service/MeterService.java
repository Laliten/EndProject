package com.aigrow.service;

import com.aigrow.model.dto.CostEstimateDto;
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
    List<CostEstimateDto> cost(int weight, String destination, Page page, String start);

    /**
     * 获取对应公司名字的计价表
     * @param companyName
     * @param page
     * @return
     */
    List<MeterDto> getCompanyMeter(String companyName, Page page);

    /**
     * 添加对应公司的计价单
     * @param meterDto
     * @return
     */
    int add(MeterDto meterDto);

    /**
     * 修改对应公司的计价单
     * @param meterDto
     * @return
     */
    int update(MeterDto meterDto);

    /**
     * 删除一条价格
     * @param meterId
     * @return
     */
    void singleDelete(int meterId);

    /**
     * 批量删除
     * @param meterIds
     * @return
     */
    void batchDelete(String meterIds);
}
