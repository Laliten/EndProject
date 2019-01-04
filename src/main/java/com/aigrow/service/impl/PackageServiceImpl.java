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
        return null;
    }
}
