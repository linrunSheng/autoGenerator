<%@page language="java" pageEncoding="UTF-8"%>

<%@ include file="/views/common/taglibs.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<title>自助服务平台 - </title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/common/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/common/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/showdialog.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/window-form-table.css" />
<script type="text/javascript"
	src="${ctx}/resources/js/common/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common/common.showdialog.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common/jquery.serializejson.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/manage/common.js"></script>
<script type="text/javascript"
    src="${ctx}/resources/js/${controllerAttribute.viewPath}.js"></script>
<script type="text/javascript">
    var rootUrl = "${ctx}";
</script>
</head>
	<body>
	<div class="easyui-panel" title="">   
        <table id="table" style="height: 600px;">
			<thead>
				<tr>
					<#list columns as column>
						<#if column.columnAttributes.gridShowable?string('true','false')=="true">
					<th data-options="field:'${column.columnName }',width:105,align:'center'">${column.columnDesc }</th>
						<#else>
					<th data-options="field:'${column.columnName }',width:105,align:'center',hidden:true">${column.columnDesc }</th>
						</#if>
					</#list>
					<th data-options="field:'operation',width:94,align:'center',formatter:formatOperation">操作</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar" style="height:auto;padding:0">
			<div style="padding:2px;">
				<form id="searchForm">
					<table style="width: 100%;height: auto;" border="0">
						<#-- 定义每行显示字段数-->
						<#assign col=4 />
						<#list queryColumns as column>
						<#-- 取3的模，即除于3的倍数余数为0的生成行的开头<tr> -->
						<#if column_index%col==0><tr></#if>
							<td align="right">${column.columnDesc}：</td>
							<td align="left" width="166">
								<input id="${column.columnName}" name="${column.columnName}" style="height:18px;width: 148px;" class="easyui-validatebox textbox">
							</td>
						    <#-- 如果最后一个不是刚好3列，则要补充完剩下的列 ,最后两列加入查询条件-->
						<#if column_index == (queryColumns?size-1) && column_index%col!=(col-1)>
						    <#assign end=(col-(queryColumns?size%col)-1) />
						    <td colspan="${end*2}">&nbsp;</td>
						    <td align="right" colspan="2" style="padding-right: 51px;"><a href="javascript:void(0)"
									class="easyui-linkbutton" icon="icon-search"
									onclick="${modelName?uncap_first}.initTable()" style="width: 60px;">查询</a>
						    </td>
						</tr>
						</#if>
						<#-- 取3的模，即除于3的倍数余数为2（0到2刚好3列）的生成行的结尾</tr> -->
						<#if column_index%col==(col-1)></tr></#if>
						<#-- 最后一个字段刚好填满，则单独加一行 ,最后两列加入查询条件 -->
						<#if column_index == (queryColumns?size-1) && column_index%col==(col-1)>
						</tr>
						    <td colspan="${(col-1)*2}">&nbsp;</td>
						    <td align="right" colspan="2" style="padding-right: 51px;"><a href="javascript:void(0)"
									class="easyui-linkbutton" icon="icon-search"
									onclick="${modelName?uncap_first}.initTable()" style="width: 60px;">查询</a>
						    </td>
						</tr>
						</#if>
						</#list>
					</table>
				</form>
			</div>
			<div style="border-top:1px solid #dddddd;padding:2px;">
				<a href="javascript:void(0)" class="easyui-linkbutton" 
				data-options="iconCls:'icon-add',plain:true" onclick="${modelName?uncap_first}.showEdit(false)">新增</a>
			</div>
		</div>
		<div id="edit-window" title="详情" class="easyui-window"
			data-options="modal:true,closed:true"
			style="width:500px;height:530px;padding:5px">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'" style="padding:0;border:0">
					<form id="editForm" data-options="novalidate:true">
						<input type="hidden" id='wid' name='wid'/>
						<table class="window-form-table">
							<#list columns as column>
								<#if column.columnName!="wid">
							<tr>
								<td>${column.columnDesc }:</td>
								<td><input id="${column.columnName }" name="${column.columnName }"
									class="textbox easyui-validatebox"
									style="width:220px;height:19px;" /> 
									<#if column.columnAttributes.nullable?string('true','false')=="false">
										<font color="red">(*)</font>
									</#if>
								</td>
							</tr>
								</#if>
							</#list>
						</table>
					</form>
				</div>
				<div data-options="region:'south',border:false"
							style="text-align: center; padding: 5px;">
					<a id="save" href="javascript:void(0)" onclick="${modelName?uncap_first}.save()"
						class="easyui-linkbutton" icon="icon-save">保存</a><a
						href="javascript:void(0)"
						onclick="$('#edit-window').window('close')"
						class="easyui-linkbutton" icon="icon-cancel">关闭</a>
				</div>
			</div>
		</div>
	</div> 
</body>
