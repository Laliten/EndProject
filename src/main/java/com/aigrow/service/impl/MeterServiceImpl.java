package com.aigrow.service.impl;

import com.aigrow.dao.CompanyDao;
import com.aigrow.dao.MeterDao;
import com.aigrow.model.dto.CostEstimateDto;
import com.aigrow.model.dto.Page;
import com.aigrow.model.entity.Company;
import com.aigrow.model.entity.Meter;
import com.aigrow.model.entity.Package;
import com.aigrow.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MeterServiceImpl implements MeterService{
    @Autowired
    private MeterDao meterDao;
    @Autowired
    private CompanyDao companyDao;
    @Override
    public List cost(int weight, String destination, Page page) {
        List<CostEstimateDto> costEstimateDtos = new ArrayList<>();
        List<Meter> meters = new ArrayList<>();
        List<Company> companyList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("destination",destination);
        meters = meterDao.findByHQL("from Meter m where m.destination=:destination",map);
        Map<String,Object> map1 = new HashMap<>();
        companyList  = companyDao.findBySQL("select tcompany.* from tcompany,tmeter where destination='"+destination+"' and tcompany.`code`=tmeter.company_code",map1);
        if (meters!=null) {
            for (int i = 0; i < meters.size(); i++) {
                int cost;
                CostEstimateDto costEstimateDto = new CostEstimateDto();
                Meter meter = new Meter();
                Company company = new Company();
                meter = meters.get(i);
                company = companyList.get(i);
                cost = meter.getFirstWeightPrice()+(weight-1)*(meter.getNextWeightPrice());
                costEstimateDto.setId(meter.getId());
                costEstimateDto.setCost(cost);
                costEstimateDto.setDestination(destination);
                costEstimateDto.setName(company.getName());
                costEstimateDto.setTrustDegree(company.getTrustDegree());
                costEstimateDtos.add(costEstimateDto);
            }
        }
        return costEstimateDtos;
    }
}
