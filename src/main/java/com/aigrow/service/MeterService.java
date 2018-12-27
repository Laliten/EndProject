package com.aigrow.service;

import com.aigrow.model.dto.Json;
import com.aigrow.model.dto.MeterDto;
import com.aigrow.model.dto.Page;

import java.util.List;

/**
 * @author YangDeJian
 */
public interface MeterService {
    /**
     * 获取申通的计价表
     * @param companyName
     * @param page
     * @return
     */
    List<MeterDto> getCompanyTable(String companyName, Page page);
}
