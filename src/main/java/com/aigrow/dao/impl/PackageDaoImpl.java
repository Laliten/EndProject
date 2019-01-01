package com.aigrow.dao.impl;

import com.aigrow.dao.PackageDao;
import com.aigrow.model.entity.Package;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dingquanbo
 */
@Repository
public class PackageDaoImpl extends BaseDaoImpl<Package> implements PackageDao{

}
