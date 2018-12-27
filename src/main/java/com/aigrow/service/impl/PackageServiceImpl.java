package com.aigrow.service.impl;

import com.aigrow.dao.CompanyDao;
import com.aigrow.dao.PackageDao;
import com.aigrow.model.dto.PackageDto;
import com.aigrow.model.entity.Company;
import com.aigrow.model.entity.Package;
import com.aigrow.service.PackageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PackageServiceImpl implements PackageService{
    @Autowired
    private PackageDao packageDao;

    @Autowired
    private CompanyDao companyDao;
    @Override
    public List getHistory(int id) {
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();
        map.put("id",id);
        List<PackageDto> packageDtoList = new ArrayList<>();
        List<Package> packageList = packageDao.findBySQL("select tpackage.* from tpackage,tuser where tpackage.user_id=tuser.id and tuser.id=:id",map);
        map1.put("id",id);
        List<Company> companyList = companyDao.findBySQL("select tcompany.* from tcompany,tuser,twayBill where twayBill.sender=tuser.id and tcompany.code=twayBill.company_code and tuser.id=?",map1);
        for (int i=0;i<packageList.size();i++){
            PackageDto packageDto = new PackageDto();
            Package p = new Package();
            p = packageList.get(i);
            BeanUtils.copyProperties(p,packageDto);
            packageDto.setCompany_name(companyList.get(i).getName());
            packageDtoList.add(packageDto);
        }
        return packageDtoList;
    }
}
