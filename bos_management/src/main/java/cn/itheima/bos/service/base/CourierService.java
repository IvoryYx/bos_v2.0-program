package cn.itheima.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itheima.bos.domain.base.Courier;

//快递员操作接口
public interface CourierService {
    
	//保存快递员
	public void save(Courier courier);
    
	// 分页查询
	public Page<Courier> findPageData(Specification<Courier> specification, Pageable pageable);

	public void delBatch(String[] idArray);

	public List<Courier> findNoAssociation();
  
}
