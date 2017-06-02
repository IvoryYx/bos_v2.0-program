package cn.itheima.bos.web.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import cn.itheima.bos.domain.base.Courier;
import cn.itheima.bos.domain.base.Standard;
import cn.itheima.bos.service.base.CourierService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class CourierAction extends ActionSupport implements ModelDriven<Courier>{

	// 模型驱动
	private Courier courier = new Courier();
		
	@Override
	public Courier getModel() {
		// TODO Auto-generated method stub
		return courier;
	}
	
	// 注入Service
	@Autowired
	private CourierService courierService;

	// 添加快递员方法
	@Action(value = "courier_save", 
			results = { @Result(name = "success",
			location = "./pages/base/courier.html", type = "redirect") })
	public String save() {
		courierService.save(courier);
		return SUCCESS;
	}
	
	// 属性驱动接收客户端分页参数
		private int page;
		private int rows;

		public void setPage(int page) {
			this.page = page;
		}

		public void setRows(int rows) {
			this.rows = rows;
		}

		// 分页查询方法
		@Action(value = "courier_pageQuery", results = { @Result(name = "success", type = "json") })
		public String pageQuery() {
			// 封装Pageable对象
			Pageable pageable = new PageRequest(page - 1, rows);
			// 封装条件查询对象 Specification
			Specification<Courier> specification = new Specification<Courier>() {
				@Override
				// Root 用于获取属性字段，CriteriaQuery可以用于简单条件查询，
				//CriteriaBuilder 用于构造复杂条件查询
				//这个方法返回null代表无条件查询
				//root用来获取条件表达式
				//CriteriaQuery,提供简单查询条件返回，提供where方法
				//CriteriaBuilder ，用来构造Predicate对象，提供复杂条件查询
				public Predicate toPredicate(Root<Courier> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> list = new ArrayList<Predicate>();

					// 简单单表查询（查询当前对象所对应的数据表），构造三个条件
					//StringUtils,快递员的工号
					if (StringUtils.isNotBlank(courier.getCourierNum())) {
						Predicate p1 = cb.equal(
								root.get("courierNum").as(String.class),
								courier.getCourierNum());
						list.add(p1);
					}
					if (StringUtils.isNotBlank(courier.getCompany())) {
						//模糊查询
						Predicate p2 = cb.like(
								root.get("company").as(String.class),
								"%" + courier.getCompany() + "%");
						list.add(p2);
					}
					if (StringUtils.isNotBlank(courier.getType())) {
						Predicate p3 = cb.equal(root.get("type").as(String.class),
								courier.getType());
						list.add(p3);
					}
					// 多表查询（查询的是当前对象所关联的对象对应的数据表）
					//收派标准进行多表查询
					//使用Courier(root)关联standard
					Join<Courier, Standard> standardJoin = root.join("standard",
							JoinType.INNER);
					if (courier.getStandard() != null
							&& StringUtils.isNotBlank(courier.getStandard()
									.getName())) {
						Predicate p4 = cb.like(
								standardJoin.get("name").as(String.class), "%"
										+ courier.getStandard().getName() + "%");
						list.add(p4);
					}
					return cb.and(list.toArray(new Predicate[0]));
					//关联四个条件，空数组起到泛型作用，构造起来动态条件
				}
			};

			// 调用业务层 ，返回 Page
			Page<Courier> pageData = courierService.findPageData(specification,
					pageable);
			// 将返回page对象 转换datagrid需要格式
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", pageData.getTotalElements());
			result.put("rows", pageData.getContent());
			// 将结果对象 压入值栈顶部
			ActionContext.getContext().getValueStack().push(result);

			return SUCCESS;
		}

}
