package com.aigrow.service.impl;

import com.aigrow.service.SearchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YangDeJian
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SearchServiceImpl implements SearchService {

}
