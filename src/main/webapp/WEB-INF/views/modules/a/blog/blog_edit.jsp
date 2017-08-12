<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../default/commom.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
<head >
    <title>博客编辑</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="${statics }/Uedit/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${statics }/Uedit/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${statics }/Uedit/lang/zh-cn/zh-cn.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="${statics }/awesome/css/font-awesome.css">
	<script type="text/javascript" src="${statics }/easyUI/jquery.easyui.min.js"></script>
    <style type="text/css">
        div{
            width:100%;
        }
    </style>
</head>
<script type="text/javascript" >

	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor');
	//写入操作
	try{
		setTimeout('fff()', 1000);
	}catch (Error){
		setTimeout('fff()', 3000);
	}
	function fff(){
		var value=$("#edit_content").html();
		ue.setContent(value,true);
		
		$('#settt').combogrid('setValue',1);
	}
	
</script>
<div >
	<input type="hidden" name="blogId" value="${blog.blogId }">
	<table>
			<tr>
				<td><h3>输入标题:</h3></td>
				<td><input type="text" name="title" value="${blog.title }"/></td>
			</tr>
			<tr>
				<td>选择博客类别:</td>
				<td>
<select id="settt" class="easyui-combogrid" style="width:250px" data-options="
			panelWidth: 500,
			idField: 'categoryId',
			textField: 'categoryName',
			url: '${headpath }/a/blog/category/query?categoryId=${blog.categoryId }',
			method: 'get',
			pagination:'true',
			columns: [[
				{field:'categoryId',title:'ID',width:80},
				{field:'categoryName',title:'分类名',width:120}
			]],
			fitColumns: true
		">
</select>
				</td>
				
				<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="do_blog_edit_add()">立即发布</a></td>
					
				<c:choose>
					<c:when test="${blog eq null }">
					<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-hourglass-half'" >存为草稿</a></td>
					</c:when>
					<c:otherwise>
					<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'fa fa-hourglass-half'" onclick="re_to_blog_edit_list()">返回列表</a></td>
					</c:otherwise>
				</c:choose>
			</tr>
	</table>
    <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
</div>
<div style="display: none" id="edit_content">${blogData.content }</div>
</html>
