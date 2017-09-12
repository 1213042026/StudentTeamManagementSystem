package me.cmnt.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import me.cmnt.model.CheckIn;
import me.cmnt.service.BaseServiceI;
import me.cmnt.util.Util;

@ParentPackage("basePackage")
@Action(value = "checkIn")
@Namespace("/")
@InterceptorRefs(value = {
		@InterceptorRef(value = "fileUpload", params = {
				"maximumSize", "209715200",
				"allowedExtensions", ".xlsx, xls"
		}),
		@InterceptorRef(value = "defaultStack")
})
@Results({
		@Result(name = "list_check_in", location = "/jsp/common/checkin/checkin.jsp"),
		@Result(name = "doUpdate", location = "/jsp/common/checkin/update.jsp"),
		@Result(name = "add", location = "/jsp/common/checkin/add.jsp"),
})
public class CheckInAction extends BaseAction {
	@Autowired
	private BaseServiceI checkInService;
	
	private CheckIn checkIn;
	private List<CheckIn> checkInList;
	private String community_id;
	private String uid;
	private File file;
	private String fileFileName;
	private String fileContentType;
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCommunity_id() {
		return community_id;
	}

	public List<CheckIn> getCheckInList() {
		return checkInList;
	}

	public void setCheckInList(List<CheckIn> checkInList) {
		this.checkInList = checkInList;
	}

	public void setCommunity_id(String community_id) {
		this.community_id = community_id;
	}

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

	public String selectCheckInByCommunityId() {
		if (community_id != null && !community_id.isEmpty()) {
			checkIn = new CheckIn();
			checkIn.setCommunity_id(community_id);
			checkInList = queryByEntType(2);
		}
		return "list_check_in";
	}
	
	public String getCheckIdByUid() {
		checkIn = new CheckIn();
		checkIn.setId(Integer.valueOf(uid));
		List<CheckIn> listObj = queryByEntType(1);
		if (listObj == null && listObj.isEmpty()) {
			return ajaxForwardError("没有该考勤信息！");
		}
		checkIn = listObj.get(0);
		return "doUpdate";
	}
	
	public String update() {
		checkInService.update(checkIn);
		return ajaxForwardSuccess("更新成功！");
	}
	
	public String delete() {
		try {
			checkIn = new CheckIn();
			checkIn.setId(Integer.valueOf(uid));
			checkInService.delete(checkIn);
			return ajaxForwardSuccess("删除成功！");
		} catch (Exception e) {
			return ajaxForwardError("删除失败！");
		}
	}
	
	public String add() {
		return "add";
	}
	
	public String save() {
		if (community_id == null) {
			return ajaxForwardError("导入失败！");
		}
		
		if (file != null) {
			InputStream is = null;
			HSSFWorkbook hssfWorkbook = null;
			try {
				is = new FileInputStream(file);
				hssfWorkbook = new HSSFWorkbook(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			CheckIn checkIn = null;
			
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						checkIn = new CheckIn();
						HSSFCell userId = hssfRow.getCell(0);
						HSSFCell username = hssfRow.getCell(1);
						HSSFCell duration = hssfRow.getCell(2);
						HSSFCell recorder = hssfRow.getCell(3);
						HSSFCell recordTime = hssfRow.getCell(4);
					
						checkIn.setUser_id(getValue(userId));
						checkIn.setUsername(getValue(username));
						checkIn.setTime(getValue(duration));
						checkIn.setRecorder(getValue(recorder));
						checkIn.setRecord_time(getValue(recordTime));
						checkIn.setCommunity_id(community_id);
						checkInService.save(checkIn);
					}
				}
			}
			
			return ajaxForwardSuccess("导入成功！");
		}
		return ajaxForwardError(getText("上传表格失败，请重试！"));
	}
	
	private String getValue(HSSFCell hssfCell) {

        if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}
