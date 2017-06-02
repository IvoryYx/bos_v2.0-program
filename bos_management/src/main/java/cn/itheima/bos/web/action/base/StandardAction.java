package cn.itheima.bos.web.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itheima.bos.domain.base.Standard;
import cn.itheima.bos.service.base.StandardService;

@ParentPackage("json-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class StandardAction extends ActionSupport implements
		ModelDriven<Standard> {
     
	//模型驱动
	private Standard standard = new Standard();
	@Override
	public Standard getModel() {
		// TODO Auto-generated method stub
		return standard;
	}
	
	// 注入Service对象
	@Autowired
	private StandardService standardService;
	
	// 添加操作
	@Action(value = "standard_save", 
			results = { @Result(name = "success",type="redirect", location = "./pages/base/standard.html") })
    public String save(){
    	System.out.println("添加收派标准");
    	standardService.save(standard);
    	return SUCCESS;
    }

}