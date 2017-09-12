<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_frag/include/include.inc.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="delete" href="checkIn!delete.action?uid={eId}&callbackType=closeCurrent" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="checkIn!getCheckIdByUid.action?uid={eId}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80">序号</th>
				<th width="120">用户名</th>
				<th width="80">考勤时长（h）</th>
				<th width="80">记录时间</th>
				<th width="80">记录人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="checkIn" items="${checkInList}">
				<tr target="eId" rel="${checkIn.id}">
					<td>
						${checkIn.id}
					</td>
					<td>
						${checkIn.username}
					</td>
					<td>
						${checkIn.time}
					</td>
					<td>
						${checkIn.record_time}
					</td>
					<td>
						${checkIn.recorder}
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
