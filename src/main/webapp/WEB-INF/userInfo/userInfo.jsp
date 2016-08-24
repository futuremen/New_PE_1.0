<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tags/fns.tld" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>User Info</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #999999;
}
-->
</style>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
<link href="css001.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body,td,th {
	font-size: 12px;
}
-->
</style></head>

<body onLoad="MM_preloadImages('images/tworoad-gif001-2.gif','images/tworoad-gif002-2.gif','images/tworoad-gif003-2.gif','images/tworoad-gif004-2.gif','images/tworoad-gif005-2.gif','images/tworoad-gif006-2.gif','images/tworoad-gif007-2.gif','images/tworoad-gif008-2.gif','images/tworoad-gif009-2.gif')">
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td width="10"></td>
    <td width="730"><img src="images/tworoad-jpg001.jpg" width="730" height="170"></td>
    <td width="10"></td>
  </tr>
  <tr>
    <td></td>
    <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
       
      </tr>
    </table></td>
    <td></td>
  </tr>
</table>
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td width="10"></td>
    <td width="730"><img src="images/tworoad-gif010.gif" width="730" height="45"></td>
    <td width="10"></td>
  </tr>
  <tr>
    <td></td>
    <td background="images/tworoad-gif011.gif"><p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>    <p>&nbsp;</p></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td><img src="images/tworoad-gif012.gif" width="730" height="4"></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td height="18"></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td><img src="images/tworoad-gif013.gif" width="730" height="29"></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="10" class="l-r-b-1pxline">
      <tr>
        <td width="25%"><table width="26%"  border="0" cellpadding="0" cellspacing="0" class="l-r-t-b-1pxline">
          <tr>
            <td><img src="images/gif-180-200px.gif" width="180" height="200"></td>
          </tr>
        </table></td>
        <td width="75%"><table width="100%" height="180"  border="0" cellpadding="0" cellspacing="0" background="images/tworoad-gif014.gif">
        
        
       
        
        
          <tr>
            <td width="12%">学&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
            <td width="38%">&nbsp;${student.studentAccount }${teacher.account }</td>
            <td width="12%">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
            <td width="38%">&nbsp;${student.name }${teacher.name }</td>
          </tr>
          <tr>
            <td>性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
            <td>&nbsp;${student.sex }${teacher.sex }</td>
            <td>年龄：</td>
            <td>&nbsp;${student.age }</td>
          </tr>
          <tr>
            <td>Q&nbsp;&nbsp;&nbsp;Q：</td>
            <td>&nbsp;${student.qq }</td>
            <td>联&nbsp;系&nbsp;方&nbsp;式：</td>
            <td>&nbsp;${student.tel }</td>
          </tr>
          <tr>
            <td><p>邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</p>              </td>
            <td>&nbsp;${student.email }</td>
            <td>地址：</td>
            <td>&nbsp;${student.adress }</td>
          </tr>
		  <tr>
            <td>身&nbsp;&nbsp;份&nbsp;&nbsp;证：</td>
            <td>&nbsp;${student.idcard }</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>备注：</td>
            <td>&nbsp;${student.remarks }</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>

		  
        
        </table></td>
      </tr>
    </table>      </td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td height="30">&nbsp;</td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="0" class="r-t-b1pxline">
      <tr>
       
        
      </tr>
    </table></td>
    <td></td>
  </tr>
  <tr>
    <td height="10"></td>
    <td height="10"></td>
    <td height="10"></td>
  </tr>
</table>
</body>
</html>

