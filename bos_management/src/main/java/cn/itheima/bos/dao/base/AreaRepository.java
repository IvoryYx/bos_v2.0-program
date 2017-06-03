package cn.itheima.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.itheima.bos.domain.base.Area;

public interface AreaRepository extends JpaRepository<Area,String>,JpaSpecificationExecutor<Area> {
 
}
