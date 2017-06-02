package cn.itcast.bos.web.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.itcast.bos.domain.base.Standard;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

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
	@Action(value = "standard_save", 
			results = { @Result(name = "success", location = "/pages/base/standard.html") })
    public String save(){
    	System.out.println("添加收派标准");
    	return SUCCESS;
    }

}