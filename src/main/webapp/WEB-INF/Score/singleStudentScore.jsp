<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	

<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>Grid</title> 

<link rel="stylesheet" type="text/css"  href="jqGrid/css/jquery-ui-1.7.1.custom.css"/>
<link rel="stylesheet" type="text/css"  href="jqGrid/css/ui.jqgrid.css" />

<script src="jqGrid/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="jqGrid/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="jqGrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script type="text/javascript" src="jqGrid/js/jquery-ui-1.7.2.custom.min.js"></script>


</head> 
<body> 
<body>


<select id="select">
<c:if test="${listTerms !=null}">
<c:forEach items="${listTerms }" var="term">
	<option id="${term.id }">${term.mark }</option>

</c:forEach>



</c:if>



</select>
<button>确认</button>
<!-- 数据列表 -->
<table id="gridTable"></table> 
<!-- 显示分页 -->
<div id="gridPager">
</div>


</body>


</body> 
<script type="text/javascript">
 $("button").click(function(){
	var x = jQuery("#select  option:selected");
	x=x.attr("id");
	
	jQuery("#gridTable").jqGrid("clearGridData");
	
$("#gridTable").jqGrid('setGridParam', {
        
        url : "/viewMyScoreByTerm?term_id="+x,
           datatype : "json"
             }).trigger("reloadGrid");  
	
	
	 
} )
    var x = "7"


    $(function(){$("#gridTable").jqGrid({
    	 
        url:"/viewMyScoreByTerm?term_id="+x,
        
        datatype: 'json',
        height: 580,
        autowidth:true,
        colNames:['肺活量','坐位体前屈','身高','体重','短跑' ,'长跑', '立定跳远' ,'引体向上/仰卧起坐','在线测试','阳光长跑'],

        colModel:[
        
       
            {name:'lung',index:'lung',editable : false},
            {name:'sitreach',index:'sitreach',editable : false},
            {name:'height',index:'height',editable : false},
            {name:'weight',index:'weight',editable : false},
            {name:'dash',index:'dash',editable : false},
            {name:'endurance',index:'endurance',editable : false},
            {name:'standingleap',index:'standingleap',editable : false},
            {name:'pullups_situps',index:'pullups_situps',editable : false},
            
            {name:'onlineScore',index:'onlineScore',editable : false},
            {name:'sunScore',index:'sunScore',editable : false}
      

//             {name:'esex', index:'esex', width:80},
//             {name:'birthday',    index:'birthday',width:120,  align:"right"},
//             {name:'department',  index:'department', width:120,  align:"right"},
//             {name:'eremark',   index:'eremark', width:150, sortable:false}
        ],
        
        
        
        loadComplete:function() {
        	  var grid = $("#gridTable");
        	  var ids = grid.getDataIDs();
        	  for (var i = 0; i < ids.length; i++) {
        	   grid.setRowData ( ids[i], false, {height: 100} );
        	  }
          },
        
  
        //sortorder:'asc',
        viewrecords:true,
        pgtext:'第{0}页 共{1}页',
        recordtext:'第 {0} - {1}条记录 ',
        rowNum:10,
        rowList:[10,20,30],
        jsonReader: {
       //      root:"dataRows",        // 数据行（默认为：rows）
         /*    page: "pages",    // 当前页
            total: "totals",  // 总页数
            records: "records",  // 总记录数   */
            repeatitems : false     // 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
        },
       
        pager:"#gridPager",
        caption: "学生成绩信息 "
    
  
      
         
        });
    })
    
    /* jQuery("#gridTable").jqGrid('navGrid', "#prowed3", {
      edit : false,
      add : false,
      del : false
    }); */
    
  /*   jQuery("#gridTable").setGridParam().hideCol("score_id").trigger("reloadGrid"); */
    



</script>
</script>
</html> 

