//Table Filed Name |BLog
	var TITLE = "title";//
    var BLOGID = "blogId";//
	var CREATEBY = "createBy";//
	var DELEFLAG = "deleFlag";//
	var UPDATEBY = "updateBy";//
	var DATAID = "dataId";//
	var CATEGORYID = "categoryId";//
	var BLOGDESC = "blogDesc";//
//Table Filed Name |BLogData
	var DATAID = "dataId";//
	var COPYFROM = "copyFrom";//
	var CONTENT = "content";//
	
//TableName
	var table_BLOG="#dg";
	
//选取 网格数据
	//用词
	var COMFIRM_BLOG_SEND_SUCCESS="发布成功！继续编辑吗？退出请按取消";
	var COMFIRM_BLOG_EDIT_SUCCESS="修改成功！继续编辑吗？退出请按取消";
	var COMFIRM_BLOG_DELE_SUCCESS="删除成功！";

//
	var blog_function={
			createNew:function(){
				var permisison ={};
				
				//@param  $arrParam |要取得的jQuery 对象数组
				//@param  names     |封装为json    的 名字
				//@return result    |返回 json     数据 
				permisison.getParamToJson = function($arrParam,names){
					var json = {};
					for (var int = 0; int < $arrParam.length; int++) {
						var array_element = $arrParam[int];
						json[names[int]]= array_element;
					}
					return json;
				};
				permisison.do_blog_edit_add=function(id){
					//获取 参数 封装json
					var $arrParam = [];
					var names     = []; 
					//标题
					$arrParam.push($("input[name='"+TITLE+"']").val());
					names.push(TITLE);
					//分类
					
					var json   =  permisison.getParamToJson($arrParam,names);
					json['content']=UE.getEditor('editor').getContent();
					var val = $('#settt').combogrid('getValue');
					json[CATEGORYID]=val;
					
					if(id !=null){
						json[BLOGID]=id;
					}
					//发送请求 返回结果
					var string = JSON.stringify(json);
					var result =  global_AjaxGetView($A_BLOG_EDIT_ADD,{"data":string},'POST');
					
					global_windowShowResultComfirm(result,COMFIRM_BLOG_EDIT_SUCCESS);
				};
				
				//删除
				permisison.do_blog_edit_delete=function(idsMap){
					var json ={};
					for (var int = 0; int < idsMap.length; int++) {
						var id = idsMap[int].blogId;
						console.log("删除时传入的id值为:"+id);
						json[id] =id;
					}
					var result =  global_AjaxGetView($A_BLOG_EDIT_DELETE,{"data":JSON.stringify(json)},'POST');
					
					global_windowShowResultComfirm(result,COMFIRM_BLOG_DELE_SUCCESS);
				};
				return permisison;
			}
	};

//立即发布
function do_blog_edit_add(){
	var id = $("input[name='blogId']").val();
	var permisison =  blog_function.createNew();
	if(id!=null&&id!=''&&typeof(id)!='undefined'){
		permisison.do_blog_edit_add(id);
	}else{
		permisison.do_blog_edit_add(null);
	}
}
//返回博客列表
function re_to_blog_edit_list(){
	window.location.href=$A_BLOG_EDIT_QUERY_LIST;
}
//数据表格操作
//删除 数据
function do_blog_edit_delete(){
	var permisison =  blog_function.createNew();
	//获取 选中id
	var idsMap = global_getDatagridSelections($(table_BLOG));
	permisison.do_blog_edit_delete(idsMap);
}

//跳转到 重新编辑页面
function to_blog_edit_update(){

    var blogId = global_getDatagridSelected($(table_BLOG)).blogId;
    //addTab("编辑博客",$A_BLOG_EDIT_UPDATE+"/"+blogId);
    window.location.href=$A_BLOG_EDIT_UPDATE+"/"+blogId;
}
