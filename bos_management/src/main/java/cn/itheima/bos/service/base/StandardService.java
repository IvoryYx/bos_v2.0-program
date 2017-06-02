package cn.itheima.bos.service.base;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.itheima.bos.domain.base.Standard;

//业务层接口收派标准接口
public interface StandardService {
	
	
  public void save(Standard standard);
  
//分页查询
public Page<Standard> findPageData(Pageable pageable);

//查询所有收派标准
public List<Standard> findAll();
     
  
 }
