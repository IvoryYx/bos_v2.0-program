package cn.itheima.bos.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itheima.bos.dao.base.FixedAreaRepository;
import cn.itheima.bos.domain.base.FixedArea;
import cn.itheima.bos.service.base.FixedAreaService;

@Service
@Transactional
public class FixedAreaServiceImpl implements FixedAreaService {

	
	// 注入DAO
		@Autowired
		private FixedAreaRepository fixedAreaRepository;

		@Override
		public void save(FixedArea fixedArea) {
			fixedAreaRepository.save(fixedArea);
		}

		@Override
		public Page<FixedArea> findPageData(Specification<FixedArea> specification, Pageable pageable) {
			// TODO Auto-generated method stub
			return fixedAreaRepository.findAll(specification, pageable);
		}

}
