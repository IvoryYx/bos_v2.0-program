package cn.itheima.bos.web.action.base;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itheima.bos.domain.base.TakeTime;
import cn.itheima.bos.service.base.TakeTimeService;
import cn.itheima.bos.web.action.common.BaseAction;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/")
@ParentPackage("json-default")
@Controller
@Scope("prototype")
public class TakeTimeAction extends BaseAction<TakeTime>{
  
	@Autowired
	private TakeTimeService takeTimeService;

	@Action(value = "taketime_findAll", 
			results = { @Result(name = "success", type = "json") })
	public String findAll() {
		// 调用业务层，查询所有收派时间
		List<TakeTime> takeTimes = takeTimeService.findAll();
		// 压入值栈返回
		ActionContext.getContext().getValueStack().push(takeTimes);
		return SUCCESS;
	}
}
