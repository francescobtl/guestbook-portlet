<%@include file="/html/init.jsp"%>


<%
    long guestbookId = Long.valueOf((Long) renderRequest
            .getAttribute("guestbookId"));
%>

    <portlet:renderURL var="addEntryURL">
        <portlet:param name="mvcPath" value="/html/guestbook/edit_entry.jsp"></portlet:param>
    </portlet:renderURL>
    
    <portlet:renderURL var="deleteEntryURL">
        	<portlet:param name="mvcPath" value="/html/guestbook/delete.jsp"/>
        	<portlet:param name="EntryId" value="EntryId"/>
    		</portlet:renderURL>


<liferay-ui:search-container
        total="<%=EntryLocalServiceUtil.getEntriesCount()%>"
>
    <liferay-ui:search-container-results
        results="<%=EntryLocalServiceUtil.getGuestbooks(scopeGroupId)%>" />

    <liferay-ui:search-container-row
        className="com.liferay.docs.guestbook.model.Entry" modelVar="entry">
        
        <portlet:actionURL var="deleteEntry" name="deleteEntry"> 
        	<portlet:param name="entryId" value="${entry.entryId}" /> 
        		</portlet:actionURL>
        			<liferay-ui:search-container-column-text name="Delete Student" value="Delete" href="${deleteEntry}"/>
            
    		
    		<liferay-ui:search-container-column-text property="entryId" />

        <liferay-ui:search-container-column-text property="message" />
        <liferay-ui:search-container-column-text property="name" />
        
        
        
        <liferay-ui:search-container-column-jsp path="/html/guestbook/delete.jsp"></liferay-ui:search-container-column-jsp>
        
        
         
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>



<aui:button-row cssClass="guestbook-buttons">



    <aui:button onClick="<%= addEntryURL.toString() %>" value="Add Entry"></aui:button>

</aui:button-row>