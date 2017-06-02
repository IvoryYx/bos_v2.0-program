package cn.itheima.bos.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itheima.bos.dao.base.StandardRepository;
import cn.itheima.bos.domain.base.Standard;
import cn.itheima.bos.service.base.StandardService;

@Service 
@Transactional
public class StandardServiceImpl implements StandardService{
    
	// 注入DAO
	@Autowired
	private StandardRepository standardRepository;
	
	@Override
	public void save(Standard standard) {
		standardRepository.save(standard);
		
	}

}
