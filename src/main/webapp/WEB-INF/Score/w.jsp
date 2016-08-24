<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>


<html>

<head>
    <title>员工信息</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">





    <!-- The jQuery library is a prerequisite for all jqSuite products -->
    <script type="text/ecmascript" src="xx/jquery.min.js"></script>
    <!-- We support more than 40 localizations -->
    <script type="text/ecmascript" src="xx/js/i18n/grid.locale-en.js"></script>

    <!-- This is the Javascript file of jqGrid -->
    <script type="text/ecmascript" src="xx/js/jquery.jqGrid.src.js"></script>
    <!-- This is the localization file of the grid controlling messages, labels, etc.
    <!-- A link to a jQuery UI ThemeRoller theme, more than 22 built-in and many more custom -->
    <link rel="stylesheet" type="text/css" media="screen" href="xx/css/ui.jqgrid.css" />
    <!-- The link to the CSS that the grid needs -->
    <link rel="stylesheet" type="text/css" media="screen" href="xx/css/jquery-ui.css" />
    <meta charset="utf-8" />
    <title>jqGrid Loading Data - Million Rows from a REST service</title>


</head>



<body>

<table id="jqGrid"></table>
<div id="jqGridPager"></div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#jqGrid").jqGrid({
            url: 'viewClassScore1',
            mtype: "GET",
            datatype: "json",
            colModel: [
                { label: 'id', name: 'id', key: true, width: 75}/* ,
                { label: 'Customer ID', name: 'CustomerID', width: 150 },
                { label: 'Order Date', name: 'OrderDate', width: 150 },
                { label: 'Freight', name: 'Freight', width: 150 },
                { label:'Ship Name', name: 'ShipName', width: 150 } */
            ],
//            viewrecords: true,
//            width: 780,
//            height: 250,
//            rowNum: 20,
          /*   pager: "#jqGridPager" */
          jsonReader : { repeatitems: false }
        });
    });

</script>










</body>
</html>