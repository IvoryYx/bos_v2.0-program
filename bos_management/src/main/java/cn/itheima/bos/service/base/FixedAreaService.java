package cn.itheima.bos.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itheima.bos.domain.base.FixedArea;

public interface FixedAreaService {

	public void save(FixedArea model);

	public Page<FixedArea> findPageData(Specification<FixedArea> specification, Pageable pageable);

}
