package cn.itheima.bos.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Page<Standard> findPageData(Pageable pageable) {
		// TODO Auto-generated method stub
		return standardRepository.findAll(pageable);
	}

	@Override
	public List<Standard> findAll() {
		return standardRepository.findAll();
	}

}
