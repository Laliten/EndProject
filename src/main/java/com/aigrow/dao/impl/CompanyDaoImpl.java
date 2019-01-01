package com.aigrow.dao.impl;

import com.aigrow.dao.BaseDao;
import com.aigrow.dao.CompanyDao;
import com.aigrow.model.entity.Company;
import com.aigrow.model.entity.Meter;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author JDB
 */
@Repository
public class CompanyDaoImpl extends BaseDaoImpl<Company> implements CompanyDao{

}
