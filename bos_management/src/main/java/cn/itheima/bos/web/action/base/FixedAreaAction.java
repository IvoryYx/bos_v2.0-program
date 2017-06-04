package cn.itheima.bos.web.action.base;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
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

import cn.itheima.bos.domain.base.FixedArea;
import cn.itheima.bos.service.base.FixedAreaService;
import cn.itheima.bos.web.action.common.BaseAction;

import com.opensymphony.xwork2.ActionContext;

//定区管理
@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class FixedAreaAction extends BaseAction<FixedArea>{
	
	// 注入Service
	@Autowired
	private FixedAreaService fixedAreaService;

	// 保存定区
	@Action(value = "fixedArea_save", results = { @Result(name = "success", type = "redirect", location = "./pages/base/fixed_area.html") })
	public String save() {
		// 调用业务层，保存定区
		fixedAreaService.save(model);
		return SUCCESS;
	}
    
	// 分页查询
		@Action(value = "fixedArea_pageQuery", results = { @Result(name = "success", type = "json") })
		public String pageQuery() {
			// 构造Pageable
			Pageable pageable = new PageRequest(page - 1, rows);
			// 构造条件查询对象
			Specification<FixedArea> specification = new Specification<FixedArea>() {
				@Override
				public Predicate toPredicate(Root<FixedArea> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> list = new ArrayList<Predicate>();
					// 构造查询条件
					if (StringUtils.isNotBlank(model.getId())) {
						// 根据 定区编号查询 等值
						Predicate p1 = cb.equal(root.get("id").as(String.class),
								model.getId());
						list.add(p1);
					}
					if (StringUtils.isNotBlank(model.getCompany())) {
						// 根据公司查询 模糊
						Predicate p2 = cb.like(
								root.get("company").as(String.class),
								"%" + model.getCompany() + "%");
						list.add(p2);
					}

					return cb.and(list.toArray(new Predicate[0]));
				}
			};
			// 调用业务层，查询数据
			Page<FixedArea> pageData = fixedAreaService.findPageData(specification,
					pageable);

			// 压入值栈
			pushPageDataToValueStack(pageData);

			return SUCCESS;
		}

}
