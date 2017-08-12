<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file ="../default/commom.jsp" %>
<table class="table_">
  <tr>
    <th>  0              </th>
    <th>博客详细                          </th>
  </tr>
  <tr>
  	<td>0</td>
  	<td style="color: gray;">
  	${fn:substring(blogdata.content,0,30)}</td>
  </tr>
</table>

