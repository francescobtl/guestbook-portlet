package com.liferay.docs.guestbook.portlet;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class GuestbookPortlet
 */
public class GuestbookPortlet extends MVCPortlet {
	
	
	public void addEntry(ActionRequest request, ActionResponse response)
	        throws PortalException, SystemException {

	    ServiceContext serviceContext = ServiceContextFactory.getInstance(
	        Entry.class.getName(), request);

	    String name = ParamUtil.getString(request, "name");

	    try {
	        EntryLocalServiceUtil.addGuestbook(serviceContext.getUserId(),
	                name, serviceContext);

	        SessionMessages.add(request, "guestbookAdded");

	    } catch (Exception e) {
	        SessionErrors.add(request, e.getClass().getName());

	        response.setRenderParameter("mvcPath",
	            "/html/guestbook/edit_guestbook.jsp");
	    }

	}
	
	
	public void deleteEntry(ActionRequest actionRequest,ActionResponse actionResponse) throws IOException, PortletException {
		//String backURL  = ParamUtil.getString(actionRequest, "backURL");
		int studentId = ParamUtil.getInteger(actionRequest, "entryId");
	      try {
	    	  System.out.println(studentId);
			EntryLocalServiceUtil.deleteEntry(studentId);
			System.out.println("Successfully Deleted Student of Id =>"+studentId);
			//actionResponse.sendRedirect(backURL); 
		  } catch (PortalException | SystemException e) {
			e.printStackTrace();
		}
	      
	  }
	
	@Override
	public void render(RenderRequest renderRequest,
	        RenderResponse renderResponse) throws PortletException, IOException {

	    try {
	        ServiceContext serviceContext = ServiceContextFactory.getInstance(
	        		Entry.class.getName(), renderRequest);

	        long groupId = serviceContext.getScopeGroupId();

	        long guestbookId = ParamUtil.getLong(renderRequest, "EntryId");

	        List<Entry> guestbooks = EntryLocalServiceUtil
	                .getGuestbooks(groupId);
	        //System.out.println(guestbooks);
	       
	        if (!(guestbookId > 0)) {
	            guestbookId = guestbooks.get(0).getEntryId();
	        }

	        renderRequest.setAttribute("guestbookId", guestbookId);

	    } catch (Exception e) {

	        throw new PortletException(e);
	    }

	    super.render(renderRequest, renderResponse);

	}
 

}
