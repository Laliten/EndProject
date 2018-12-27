package com.aigrow.service.impl;

import com.aigrow.dao.MeterDao;
import com.aigrow.model.dto.Json;
import com.aigrow.model.dto.MeterDto;
import com.aigrow.model.dto.Page;
import com.aigrow.model.entity.Meter;
import com.aigrow.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.Oneway;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YangDeJian
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MeterServiceImpl implements MeterService {
    @Autowired
    private MeterDao meterDao;

    /**
     * 获取申通的计价表
     *
     * @param companyName
     * @param page
     * @return
     */
    @Override
    public List<MeterDto> getCompanyTable(String companyName, Page page) {
        String hql = "from Meter m where m.company.name=:name ";
        Map<String, Object> map = new HashMap<>();
        map.put("name", companyName);

        List<Meter> meters = meterDao.findByHQL(hql,map,page.getNextPage(),page.getPageSize());
        if (meters != null){
            return null;
        }
        return null;
    }

    private MeterDto e2d(Meter meter){

        return null;
    }

}
