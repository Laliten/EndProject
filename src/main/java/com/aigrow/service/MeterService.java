package com.aigrow.service;

import com.aigrow.model.dto.Page;
import com.aigrow.model.entity.Package;

import java.util.List;

public interface MeterService {
    /**
     * 计算每个公司的运费
     * @param weight
     * @param destination
     * @param page
     * @return
     */
    List cost(int weight, String destination,Page page);
}
