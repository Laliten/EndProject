package com.aigrow.service.impl;

import com.aigrow.dao.CompanyDao;
import com.aigrow.dao.MeterDao;
import com.aigrow.model.dto.CostEstimateDto;
import com.aigrow.model.dto.MeterDto;
import com.aigrow.model.dto.Page;
import com.aigrow.model.entity.Company;
import com.aigrow.model.entity.Meter;
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
    public List cost(int weight, String destination, Page page, String start) {
        List<CostEstimateDto> costEstimateDtos = new ArrayList<>();
        List<Meter> meters = new ArrayList<>();
        List<Company> companyList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("destination",destination);
        meters = meterDao.find("from Meter m where m.destination=:destination", map);
        Map<String,Object> map1 = new HashMap<>();
        companyList = companyDao.find("select c from Company c,Meter m where m.destination=:destination and m.company.id = c.id", map);
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
                costEstimateDto.setStart(start);
                costEstimateDtos.add(costEstimateDto);
            }
        }
        return costEstimateDtos;
    }

    /**
     * 获取对应公司名字的计价表
     *
     * @param companyName
     * @param page
     * @return
     */
    @Override
    public List<MeterDto> getCompanyMeter(String companyName, Page page) {
        String hql = "from Meter m where m.company.name=:companyName";
        Map<String, Object> map = new HashMap<>(0);
        map.put("companyName", companyName);
        List<Meter> meters = meterDao.find(hql, map, page.getNextPage(), page.getPageSize());

        if (meters != null){
            return this.e2d(meters);
        }
        return null;
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
