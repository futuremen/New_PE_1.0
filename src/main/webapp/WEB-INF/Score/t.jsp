<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tags/fns.tld" %>


<html>

<head>
<title>员工信息</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<link type="text/css" rel="stylesheet"
	href="jqGrid/css/jquery-ui-1.7.1.custom.css" />

<link rel="stylesheet" href="jqGrid/css/ui.jqgrid.css" type="text/css" />



<script type="text/javascript" src="jqGrid/js/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="jqGrid/js/i18n/grid.locale-cn.js"></script>

<script type="text/javascript" src="jqGrid/js/jquery.jqGrid.min.js"></script>

<script type="text/javascript"
	src="jqGrid/js/jquery-ui-1.7.2.custom.min.js"></script>
	
	<link href="css/insert.css" rel="stylesheet">

<script type="text/javascript">

 var term_id = "${term_id}"
	 var x = ${class_id}
        $(function(){

            var timestamp = Date.parse(new Date());
        
            $("#gridTable").jqGrid({
                url:"viewClassScoreAjax?class_id="+x+"&term_id="+term_id+"&timestamp="+timestamp,
                datatype: 'json',
                height: 560,
                colNames:['term_id','student_id','score_id','序号',' 姓名 ', '学号 ', '肺活量 ','仰卧/引体','立定跳远 ','坐位体前屈','耐力跑 ', '身高 ','体重','短跑 ','在线测试','阳光长跑'],
                    colModel:[
                    {name:'term_id',index:'term_id', width:80,hidden:true},
                    {name:'student_id',index:'student_id', width:80,hidden:true},
                    {name:'score_id',index:'score_id', width:80,align:"center",hidden:true},
                    {name:'id',index:'id', width:80,align:"center"},
                    {name:'name',index:'name', width:80,align:"center"},
                    {name:'studentAccount', index:'studentAccount', width:80,align:"center",hidden : true},
                    {name:'lung',    index:'lung',width:120,  align:"right",align:"center"},
                    {name:'pullups_situps',  index:'pullups_situps', width:120,  align:"center"},
                    {name:'standingleap',   index:'standingleap', width:150, sortable:false,align:"center"},
                    {name:'sitreach',   index:'sitreach', width:150, sortable:false,align:"center"},
                    {name:'endurance',   index:'endurance', width:150, sortable:false,align:"center"},
                    {name:'height',   index:'height', width:150, sortable:false,align:"center"},
                    {name:'weight',   index:'weight', width:150, sortable:false,align:"center"},
                    {name:'dash',   index:'dash', width:150, sortable:false,align:"center"},
                    {name:'onlineScore',   index:'onlineScore', width:150, sortable:false,align:"center"},
                    {name:'sunScore',   index:'sunScore', width:150, sortable:false,align:"center"}
                ],
               autowidth:true,
                viewrecords:true,
                pgtext:'第{0}页 共{1}页',
                recordtext:'第 {0} - {1}条记录 ',
                rowNum:10,
                rowList:[10,20,30],
                jsonReader: {
                  /*  root:"dataRows",        // 数据行（默认为：rows）
                    page: "pages",    // 当前页
                    total: "totals",  // 总页数
                    records: "records",  // 总记录数  */
                    repeatitems : false     // 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
                },

                
                loadComplete:function() {
              	  var grid = $("#gridTable");
              	  var ids = grid.getDataIDs();
              	  for (var i = 0; i < ids.length; i++) {
              	   grid.setRowData ( ids[i], false, {height: 50} );
              	  }
                },

                prmNames : {
                   /*  search : "search",
                    rows:"pageModel.rows",
                    page:"pageModel.page", */
                    //sort:"page.orderBy",
                    //order:"page.order"
                },
                pager:"#gridPager",
                caption: "成绩信息 "
            });

            

              // 配置对话框  
            $("#consoleDlg").dialog({
                autoOpen: false,
                modal: true,    // 设置对话框为模态（modal）对话框
                resizable: true,
                width: 960,
                buttons: {  // 为对话框添加按钮
                    "取消": function() {$("#consoleDlg").dialog("close")},
                    "添加": addEmployee,
                    "保存": updateEmployee,
                    "删除成绩": loadDelData,
                    "确定": loadDelData
                }
            });
              
              
              
            $("#selTerm").dialog({
                autoOpen: false,
                modal: true,    // 设置对话框为模态（modal）对话框
                resizable: true,
                width: 960,
                buttons: {  // 为对话框添加按钮
                    "取消": function() {$("#selTerm").dialog("close")},
                  
                    "确定": loadTermData
                }
            });

              
              
        });

        
        
        
        var openDialog4Adding = function() {
            var consoleDlg = $("#consoleDlg");
            var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");
            consoleDlg.find("input").removeAttr("disabled").val("");
            dialogButtonPanel.find("button:not(:contains('取消'))").hide();
            dialogButtonPanel.find("button:contains('添加')").show();
            consoleDlg.dialog("option", "title", "添加信息 ").dialog("open");
        };
        var openDialog4Updating = function() {
            var consoleDlg = $("#consoleDlg");
            var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");

            consoleDlg.find("input").removeAttr("disabled");
            dialogButtonPanel.find("button:not(:contains('取消'))").hide();
            dialogButtonPanel.find("button:contains('保存')").show();
            dialogButtonPanel.find("button:contains('删除成绩')").show();
            consoleDlg.dialog("option", "title", "修改成绩");
            
            loadSelectedRowData();
           
        }
        var openDialog4Deleting = function() {
            var consoleDlg = $("#consoleDlg");
            var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");

            consoleDlg.find("input").attr("disabled", true);
            dialogButtonPanel.find("button:not(:contains('取消'))").hide();
            dialogButtonPanel.find("button:contains('删除')").show();
            consoleDlg.dialog("option", "title", "删除员工");

            loadDelData();
        }
        
        var selTerm = function() {
        	 
            var consoleDlg = $("#selTerm");
            var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");
         //   alert("consoleDlg");
         //   consoleDlg.find("input").attr("disabled", true);
            dialogButtonPanel.find("button:not(:contains('取消'))").hide();
            dialogButtonPanel.find("button:contains('确定')").show();
            consoleDlg.dialog("option", "title", "选择学期");
           
            consoleDlg.dialog("open"); 

        }
        
        
        
        
        
       var loadTermData = function(){
    	   
    	   
           var id = jQuery("#mySelect").val();
           
          
           
            term_id = id;
    	   
            $("#selTerm").dialog("close");
         
            
    	  
            $("#gridTable").jqGrid('setGridParam', {
                
                url : "viewClassScoreAjax?class_id="+x+"&term_id="+term_id,
                   datatype : "json"
                     }).trigger("reloadGrid"); 
    	   
       }
        
        


        
        var loadDelData = function(){
        	
        	
        	 var consoleDlg = $("#consoleDlg");
         	
             var count = $("#gridTable").jqGrid("getGridParam", "selrow");
             
            
             
             var score_id = $("#gridTable").getCell(count,"score_id");
             
             if (!score_id) {
                 alert("此学生无成绩信息");
                 return false;
             } else {
             	
                
                 
                  var params =
                	 {
                		  
                		  "score_id":score_id
                		  
                	 }
                 
                 
                 var actionUrl = "deleteOneStudentScoreAjax";
                 $.ajax({
                     url : actionUrl,
                     data : params,
                     dataType : "json",
                     cache : false,
                     error : function(textStatus, errorThrown) {
                         alert("系统ajax交互错误: " + textStatus);
                     },
                     success : function(data, textStatus) {
                        
                             
                             consoleDlg.dialog("close");
                              
                             $("#gridTable").jqGrid('setGridParam', {
                                   
                                     url : "viewClassScoreAjax?class_id="+x+"&term_id="+term_id,
                                        datatype : "json"
                                          }).trigger("reloadGrid"); 
                             
                             alert("删除成功!");
                        
                     }
                 });

                 }

        }

        
            var loadSelectedRowData = function() {
            	
            var consoleDlg = $("#consoleDlg");
        	
            var count = $("#gridTable").jqGrid("getGridParam", "selrow");
            
            var  student_id= $("#gridTable").getCell(count,"student_id");
            
            var score_id = $("#gridTable").getCell(count,"score_id");
            
            var term_id = $("#gridTable").getCell(count,"term_id");
            
            var id = $("#gridTable").getCell(count,"id");
           
            var name = $("#gridTable").getCell(count,"name");
            
            var studentAccount = $("#gridTable").getCell(count,"studentAccount");
            if (!student_id) {
                alert("请先选择需要编辑的行!");
                return false;
            } else {
            	
                var params = {
                     "student_id":student_id ,
                     "score_id":score_id ,
                     "id":id,
                     "term_id":term_id ,  
                     "name":name,
                     "studentAccount":studentAccount
                    
                }
                var acturl= "getOneStudentScoreAjax"
                $.ajax( {
                    url :acturl,
                    data : params,
                    dataType : "json",
                    cache : false,
                    error : function(textStatus, errorThrown) {
                        alert("load err: " + textStatus);                   
                    },
                    success : function(data, textStatus) {
                     var rowData = data
                    
                        // 如果读取结果成功，则将信息载入到对话框中
                        consoleDlg.find("#id").val(rowData.id);
                        consoleDlg.find("#term_id").val(rowData.term_id);                   
                        consoleDlg.find("#student_id").val(rowData.student_id);
                        consoleDlg.find("#score_id").val(rowData.score_id);
                        consoleDlg.find("#name").val(rowData.name);
                        consoleDlg.find("#studentAccount").val(rowData.studentAccount);  
                        consoleDlg.find("#lung").val(rowData.lung);  
                        consoleDlg.find("#pullups_situps").val(rowData.pullups_situps);  
                        consoleDlg.find("#standingleap").val(rowData.standingleap);  
                        consoleDlg.find("#sitreach").val(rowData.sitreach);  
                        consoleDlg.find("#endurance").val(rowData.endurance);  
                        consoleDlg.find("#height").val(rowData.height);  
                        consoleDlg.find("#weight").val(rowData.weight);  
                        consoleDlg.find("#dash").val(rowData.dash);  
                        consoleDlg.find("#onlineScore").val(rowData.onlineScore);  
                        consoleDlg.find("#sunScore").val(rowData.sunScore);
                        consoleDlg.dialog("open"); 
                    }
                }); 
            }
        };
        //数据更新
        function updateEmployee() {
            var consoleDlg = $("#consoleDlg");
            var id = $.trim(consoleDlg.find("#id").val());  
            var term_id = $.trim(consoleDlg.find("#term_id").val());                   
            var student_id = $.trim(consoleDlg.find("#student_id").val());
            var score_id = $.trim(consoleDlg.find("#score_id").val());
            var name = $.trim(consoleDlg.find("#name").val());
            var studentAccount = $.trim(consoleDlg.find("#studentAccount").val()); 
            var lung = $.trim(consoleDlg.find("#lung").val()); 
            var pullups_situps = $.trim(consoleDlg.find("#pullups_situps").val()); 
            var standingleap = $.trim(consoleDlg.find("#standingleap").val());
            var sitreach = $.trim(consoleDlg.find("#sitreach").val());
            var endurance = $.trim(consoleDlg.find("#endurance").val());  
            var height = $.trim(consoleDlg.find("#height").val());
            var weight = $.trim(consoleDlg.find("#weight").val()); 
            var dash = $.trim(consoleDlg.find("#dash").val());
            
            var onlineScore = $.trim(consoleDlg.find("#onlineScore").val());
            
            var sunScore = $.trim(consoleDlg.find("#sunScore").val());
            var params = {
            		"term_id":term_id,
            		"student_id":student_id,
            		"score_id":score_id,
            		"name":name,
            		"studentAccount":studentAccount,
            		"id":id,
            		"lung":lung,
            		"pullups_situps":pullups_situps,
            		"standingleap":standingleap,
            		"sitreach":sitreach,
            		"endurance":endurance,
            		"height":height,
            		"weight":weight,
            		"dash":dash,
            		"onlineScore":onlineScore,
            		"sunScore":sunScore
              
            }; 
            var actionUrl = "updateOrInsertScoreAjax";
            $.ajax( {
            	type: "POST",
                url : actionUrl,
                data : params,
                dataType : "json",
                cache : false,
                error : function(textStatus, errorThrown) {
                    alert("系统liu交互错误: " + textStatus);
                },
                success : function(data, textStatus) {
                   
                        var dataRow = {
                        		term_id:data.term_id,
                        		student_id:data.student_id,
                        		score_id:data.score_id,
                        		name:data.name,
                        		studentAccount:data.studentAccount,
                        		id:data.id,
                        		lung:data.lung,
                        		pullups_situps:data.pullups_situps,
                        		standingleap:data.standingleap,
                        		sitreach:data.sitreach,
                        		endurance:data.endurance,
                        		height:data.height,
                        		weight:data.weight,
                        		dash:data.dash,
                        		onlineScore:data.onlineScore,
                        		sunScore:data.sunScore
                        };
                        alert("更新成功!");
                        consoleDlg.dialog("close");
                        $("#gridTable").jqGrid("setRowData", data.id, dataRow);
                   
                }
            });
        };

        var addEmployee = function() {
            var consoleDlg = $("#consoleDlg");

            var eanme = $.trim(consoleDlg.find("#eanme").val());
            var esex = $.trim(consoleDlg.find("#esex").val());
            var birthday = $.trim(consoleDlg.find("#birthday").val());
            var department = $.trim(consoleDlg.find("#department").val());
            var eremark = $.trim(consoleDlg.find("#eremark").val());

            var params = {
                "employee.ename" : ename,
                "employee.esex" : esex,
                "employee.birthday" : birthday,
                "employee.department" : department,
                "employee.eremark" : eremark
            };

            var actionUrl = "#";

            $.ajax( {
                url : actionUrl,
                data : params,
                dataType : "json",
                cache : false,
                error : function(textStatus, errorThrown) {
                    alert("系统ajax交互错误: " + textStatus);
                },
                success : function(data, textStatus) {
                    if(data.ajaxResult == "success") {
                        var dataRow = {
                            id : data.employee.eid,   // 从Server端得到系统分配的id
                            ename :ename,
                            esex : esex,
                            birthday: birthday,
                            department: department,
                            eremark:eremark
                        };

                        var srcrowid = $("#gridTable").jqGrid("getGridParam", "selrow");

                        if(srcrowid) {
                            $("#gridTable").jqGrid("addRowData", data.employee.eid, dataRow, "before", srcrowid);

                        } else {
                            $("#gridTable").jqGrid("addRowData", data.employee.eid, dataRow, "first");
                        }
                        consoleDlg.dialog("close");

                        alert("联系人添加操作成功!");

                    } else {
                        alert("添加操作失败!");
                    }
                }
            });
        };

        //数据删除
        var deleteEmployee = function() {
            var consoleDlg = $("#consoleDlg");

            var pId = $.trim(consoleDlg.find("#selectId").val());
            var params = {
                "employee.eid" : pId
            };
            var actionUrl = "employee_delete.action";
            $.ajax({
                url : actionUrl,
                data : params,
                dataType : "json",
                cache : false,
                error : function(textStatus, errorThrown) {
                    alert("系统ajax交互错误: " + textStatus);
                },
                success : function(data, textStatus) {
                    if (data.ajaxResult == "success") {
                        $("#gridTable").jqGrid("delRowData", pId);
                        consoleDlg.dialog("close");
                        alert("联系人删除成功!");
                    } else {
                        alert("删除操作失败!");
                    }
                }
            });
        };
    </script>

</head>
<body>
	

		<table id="gridTable"></table>
		<div id="gridPager"></div>
		<div>
			<!-- <button class="right-button02" onclick="openDialog4Adding()">添加</button> -->
			<button class="btn btn-primary" onclick="openDialog4Updating()">编辑成绩信息</button>
			<button class="btn btn-primary" onclick="openDialog4Deleting()">删除学生成绩</button>
			<button class="btn btn-primary" onclick="selTerm()">选择学期</button>

		</div>
		
		
		<div id="selTerm">
			<div id="selTerm1">
				<form id="selTerm2">
				<select id="mySelect" name="mySelect">
				<c:forEach items="${fns:getAllTerm() }" var="term">
				<option value="${term.id }">${term.mark }</option>
				</c:forEach>
				
				
				
				</select>
				
				</form>
				</div>
				</div>
		

		<div id="consoleDlg">
			<div id="formContainer">
				<form id="consoleForm">
					<input type="hidden" class="textField" id="id" />
					<input type="hidden" class="textField" id="student_id" />
					<input type="hidden" class="textField" id="score_id" />
					<input type="hidden" class="textField" id="term_id" />
					<table class="formTable">
						<tr>
							<th>姓名：</th>
							<td><input type="text" class="textField" id="name"
								name="name" /></td>
						</tr>
									
						<tr>
							<th id="thusergender">学号：</th>
							<td><input type="text" class="textField" id="studentAccount"
								name="studentAccount" /></td>
						</tr>
	
						<tr>
							<th>肺活量：</th>
							<td><input type="text" class="textField" id="lung"
								name="lung" /></td>
						</tr>
						<tr>
							<th>仰卧/引体：</th>
							<td><input type="text" class="textField" id="pullups_situps"
								name="pullups_situps" /></td>
						</tr>
						<tr>
							<th>立定跳远：</th>
							<td><input type="text" class="textField" id="standingleap"
								name="standingleap" /></td>
						</tr>
						
						
						<tr>
							<th>坐位体前屈：</th>
							<td><input type="text" class="textField" id="sitreach"
								name="sitreach" /></td>
						</tr>
						
						<tr>
							<th>耐力跑：</th>
							<td><input type="text" class="textField" id="endurance"
								name="endurance" /></td>
						</tr>

						<tr>
							<th>身高：</th>
							<td><input type="text" class="textField" id="height"
								name="height" /></td>
						</tr>
						
						<tr>
							<th>体重：</th>
							<td><input type="text" class="textField" id="weight"
								name="weight" /></td>
						</tr>
						<tr>
							<th>短跑：</th>
							<td><input type="text" class="textField" id="dash"
								name="dash" /></td>
						</tr>
						
						<tr>
							<th>在线测试：</th>
							<td><input type="text" class="textField" id="onlineScore"
								name="onlineScore" /></td>
						</tr>
						
						<tr>
							<th>阳光长跑：</th>
							<td><input type="text" class="textField" id="sunScore"
								name="sunScore" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
</body>
</html>