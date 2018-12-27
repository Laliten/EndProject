package com.aigrow.service.impl;

import com.aigrow.dao.CompanyDao;
import com.aigrow.dao.MeterDao;
import com.aigrow.model.dto.CostEstimateDto;
import com.aigrow.model.dto.MeterDto;
import com.aigrow.model.dto.Page;
import com.aigrow.model.entity.Company;
import com.aigrow.model.entity.Meter;
import com.aigrow.model.entity.Package;
import com.aigrow.service.MeterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JDB
 */
@Service
@Transactional(rollbackFor = Exception.class)
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

    private MeterDto e2d(Meter meter){
        MeterDto meterDto = new MeterDto();
        if (meter != null) {
            BeanUtils.copyProperties(meter, meterDto);
            if (meter.getCompany() != null) {
                meterDto.setCompanyName(meter.getCompany().getName());
                meterDto.setCompanyCode(meter.getCompany().getCode());
            }
        }
        return meterDto;
    }

    private List<MeterDto> e2d(List<Meter> meters){
        List<MeterDto> meterDtos = new ArrayList<>();
        if (meters != null && meters.size() != 0){
            for (Meter m : meters){
                meterDtos.add(this.e2d(m));
            }
        }
        return meterDtos;
    }
}
