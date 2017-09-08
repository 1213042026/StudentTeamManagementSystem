package me.cmnt.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import me.cmnt.model.CheckIn;
import me.cmnt.service.BaseServiceI;

@ParentPackage("basePackage")
@Action(value = "checkIn")
@Namespace("/")
@Results({
		@Result(name = "list_act", location = "/jsp/common/activity/activity.jsp"),
})
public class CheckInAction extends BaseAction {
	@Autowired
	private BaseServiceI checkInService;
	
	private CheckIn checkIn;
	
	public CheckIn getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(CheckIn checkIn) {
		this.checkIn = checkIn;
	}

	@Override
	public List queryByEntType(int entType) {
		List<CheckIn> listObj = new ArrayList<CheckIn>();
		try {
			if (checkIn == null) {
				checkIn = new CheckIn();
			}
			List<Object> listObjects = checkInService.query(checkIn, entType);
			for (Object ckn : listObjects) {
				if (ckn != null && ckn instanceof CheckIn) {
					listObj.add((CheckIn) ckn);
				}
			}
			return listObj;
		} catch (Exception e) {
			return null;
		}
	}

}
