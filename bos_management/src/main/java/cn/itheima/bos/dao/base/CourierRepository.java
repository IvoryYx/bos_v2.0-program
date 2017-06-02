package cn.itheima.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.itheima.bos.domain.base.Courier;

public interface CourierRepository extends JpaRepository<Courier, Integer>,
   JpaSpecificationExecutor<Courier> {

}
