<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../_frag/include/include.inc.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<h2 class="contentTitle">考勤管理</h2>


<div class="pageContent">

	<form method="post" action="checkIn!update.action?callbackType=closeCurrent"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<input value="${checkIn.id}" type="hidden" name="checkIn.id"/>
			<input value="${checkIn.community_id}" type="hidden" name="checkIn.community_id"/>
			<input value="${checkIn.user_id}" type="hidden" name="checkIn.user_id"/>
			<input value="${checkIn.record_time}" type="hidden" name="checkIn.record_time"/>
			<dl>
				<dt>用户名:</dt>
				<dd>
					<input value="${checkIn.username}" type="text" name="checkIn.username" maxlength="20" class="required" />
					<span class="info">请输入用户名</span>
				</dd>
			</dl>
			<dl>
				<dt>考勤时长（h）：</dt>
				<dd>
					<input value="${checkIn.time}" type="text" name="checkIn.time" maxlength="20" class="required" />
					<span class="info">请输入考勤时长</span>
				</dd>
			</dl>
			<dl>
				<dt>记录人：</dt>
				<dd>
					<textarea class="editor" name="checkIn.recorder" rows="6"
						cols="100">${checkIn.recorder}</textarea>
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
