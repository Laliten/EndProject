package com.aigrow.service.impl;

import com.aigrow.controller.MeterController;
import com.aigrow.dao.CompanyDao;
import com.aigrow.dao.MeterDao;
import com.aigrow.model.dto.CostEstimateDto;
import com.aigrow.model.dto.MeterDto;
import com.aigrow.model.dto.Page;
import com.aigrow.model.dto.UserDto;
import com.aigrow.model.entity.Company;
import com.aigrow.model.entity.Meter;
import com.aigrow.service.MeterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

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
    public List<CostEstimateDto> cost(double weight, String destination, Page page, String start) {
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

                cost = (int) (meter.getFirstWeightPrice()+(weight-1)*(meter.getNextWeightPrice()));

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
     * 获取对应公司编码的计价表
     *
     * @param companyCode
     * @param page
     * @return
     */
    @Override
    public List<MeterDto> getCompanyMeter(String companyCode, Page page) {
        String hql = "from Meter m where m.company.code=:companyCode";
        Map<String, Object> map = new HashMap<>(0);
        map.put("companyCode", companyCode);
        List<Meter> meters = meterDao.find(hql, map, page.getNextPage(), page.getPageSize());

        if (meters != null){
            return this.e2d(meters);
        }
        return null;
    }

    /**
     * 获取计价表的总条数
     * @param companyCode
     * @return
     */
    @Override
    public long numOfMeters(String companyCode) {
        Map<String,Object> map = new HashMap<>(0);
        map.put("code", companyCode);
        String hql = "select count(*) from Meter m where m.company.code=:code";
        long numOfMeters = meterDao.count(hql,map);
        return numOfMeters;
    }

    /**
     * 根据搜索的内容对计价表进行搜索
     *
     * @param page
     * @param searchText
     * @return
     */
    @Override
    public List<MeterDto> searchMeters(Page page, String searchText) {
        Map<String,Object> map = new HashMap<>(0);
        map.put("searchText", searchText);

        String hql = "from Meter m where " +
                " m.destination=:searchText or " +
                " m.company.code=:searchText or " +
                " m.firstWeightPrice=:searchText or " +
                " m.nextWeightPrice=:searchText or " +
                " m.firstWeight=:searchText ";
        List<MeterDto> meterDtos = this.e2d(meterDao.find(hql, map, page.getNextPage(), page.getPageSize()));
        if (meterDtos == null || meterDtos.size()==0){
            meterDtos = new ArrayList<>(0);
        }
        return meterDtos;
    }

    /**
     * 添加对应公司的计价单
     * @param meterDto
     * @return
     */
    @Override
    public int add(MeterDto meterDto) {
        Meter meter = this.d2e(meterDto);
        Serializable num = meterDao.merge(meter);
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
     * @param meterDto
     * @return
     */
    @Override
    public MeterDto singleDelete(MeterDto meterDto) {
        if(meterDto==null){
            return null;
        }
        Meter meter = new Meter();
        BeanUtils.copyProperties(meterDto,meter);
        meterDao.delete(meter);
        return meterDto;
    }

    private static Logger logger = LoggerFactory.getLogger(MeterController.class);
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
        String[] idArr = meterIds.split(",");
        for(String id:idArr){
            if(id!=null){
                if(!id.equals("zhang")){
                    Meter meter = new Meter();
                    meter.setId(Integer.parseInt(id));
                    meterDao.delete(meter);
                }
            }
        }
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public MeterDto search(int id) {
        MeterDto meterDto = e2d(meterDao.get(Meter.class,id));
        return meterDto;
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
        if (meterDto != null){
            BeanUtils.copyProperties(meterDto, meter);
            if(meterDto.getCompanyCode()!=null){
                meter.setCompany(companyDao.get("from Company c where c.code=:companyCode",map));
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
