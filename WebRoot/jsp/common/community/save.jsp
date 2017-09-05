<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../_frag/include/include.inc.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<h2 class="contentTitle">部门管理</h2>


<div class="pageContent">

	<form enctype="multipart/form-data" method="post" 
		action="community!saveOrUpdate.action?callbackType=closeCurrent"
		class="pageForm required-validate"
		onsubmit="return iframeCallback(this, navTabAjaxDone);"">
		<div class="pageFormContent nowrap" layoutH="97">
			<input value="9999" type="hidden" name="community.id"/>
			<dl>
				<dt>创建部门名称：</dt>
				<dd>
					<input type="text"
						name="community.community_name" maxlength="20" class="required" />
					<span class="info">请输入部门名称</span>
				</dd>
			</dl>
			<dl>
				<dt>部门简介：</dt>
				<dd>
					<textarea rows="3" cols="70" name="community.community_introduction" class="required" > </textarea> <span
						class="info">请输入部门简介"</span>
				</dd>
			</dl>
			<dl>
				<dt>部门图片上传：</dt>
				<dd>
					<input type="file" name="file" />
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>

</div>


<script type="text/javascript">
	function customvalidXxx(element) {
		if ($(element).val() == "xxx")
			return false;
		return true;
	}
</script>
