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

import java.io.Serializable;
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
    public List<CostEstimateDto> cost(int weight, String destination, Page page, String start) {
        List<CostEstimateDto> costEstimateDtos = new ArrayList<>();
        List<Meter> meterList;
        List<Company> companyList;

        Map<String,Object> map = new HashMap<>(0);
        map.put("destination",destination);

        meterList = meterDao.find("from Meter m where m.destination=:destination", map);

        companyList = companyDao.find("select c from Company c,Meter m where m.destination=:destination and m.company.id = c.id", map);

        if (meterList!=null) {
            for (int i = 0, cost = 0; i < meterList.size(); i++) {

                CostEstimateDto costEstimateDto = new CostEstimateDto();
                Meter meter = meterList.get(i);
                Company company = companyList.get(i);

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

    /**
     * 添加对应公司的计价单
     * @param meterDto
     * @return
     */
    @Override
    public int add(MeterDto meterDto) {
        Meter meter = this.d2e(meterDto);
        Serializable num = meterDao.save(meter);
        if (num == null) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 修改对应公司的计价单
     *
     * @param meterDto
     * @return
     */
    @Override
    public int update(MeterDto meterDto) {
        Meter meter = this.d2e(meterDto);
        meterDao.update(meter);
        return 1;
    }

    /**
     * 删除一条价格
     * @param meterId
     * @return
     */
    @Override
    public void singleDelete(int meterId) {
        if(meterId==0){
            return;
        }
        Meter meter = meterDao.get(Meter.class,meterId);
        meterDao.delete(meter);
    }

    /**
     * 批量删除
     * @param meterIds
     * @return
     */
    @Override
    public void batchDelete(String meterIds) {
        if (meterIds == null){
            return;
        }
        meterDao.batchDelete(meterIds);
    }

    /**
     * 将meterDto对象转换为meter实体类对象
     * @param meterDto
     * @return
     */
    private Meter d2e(MeterDto meterDto){
        Meter meter = new Meter();
        Map<String, Object> map = new HashMap<>(0);
        map.put("companyCode",meterDto.getCompanyCode());
        map.put("companyName",meterDto.getCompanyName());
        if (meterDto != null){
            BeanUtils.copyProperties(meterDto, meter);
            if(meterDto.getCompanyCode()!=null){
                meter.setCompany(companyDao.get("from Company c where c.code=:companyCode",map));
            }
            if(meterDto.getCompanyName()!=null){
                meter.setCompany(companyDao.get("from Company c where c.name=:companyName",map));
            }
        }
        return meter;
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
