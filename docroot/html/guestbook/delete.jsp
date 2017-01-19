
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@include file="/html/init.jsp"%>

<%
	long entryId1 = ParamUtil.getLong(renderRequest, "entryId");
	long entryId2 = ParamUtil.getLong(renderRequest, "EntryId");
	//String s = "i_______d: "+entryId1 +"-"+ entryId2;
	//System.out.println(s);
%>


<portlet:actionURL name="delEntry" var="delEntryURL"></portlet:actionURL>


<aui:button type="button" name='delete' value="delete"></aui:button>