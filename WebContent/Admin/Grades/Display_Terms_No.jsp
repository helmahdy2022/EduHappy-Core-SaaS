<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>New Grades</title>
<link href="../../_css/Admin_Main.css" rel="stylesheet" type="text/css">
</head>
<body> 
          <label>Number of Grade terms</label>
          <input name="terms" type="text" class="text" id="terms" size="2" onChange="showTerms(this.value,'${param.Grade_id}')">
</body>
</html>