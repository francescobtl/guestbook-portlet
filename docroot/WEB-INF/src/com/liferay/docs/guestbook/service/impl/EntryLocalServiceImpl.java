/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.docs.guestbook.service.impl;

import java.util.Date;
import java.util.List;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.base.EntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.docs.guestbook.service.EntryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Francesco
 * @see com.liferay.docs.guestbook.service.base.EntryLocalServiceBaseImpl
 * @see com.liferay.docs.guestbook.service.EntryLocalServiceUtil
 */
public class EntryLocalServiceImpl extends EntryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.docs.guestbook.service.EntryLocalServiceUtil} to access the entry local service.
	 */
	
	
	public List<Entry> getGuestbooks (long groupId) throws SystemException {
	    return entryPersistence.findByGroupId(groupId);
	}

	public List<Entry> getGuestbooks (long groupId, int start, int end)
	   throws SystemException {
	    return entryPersistence.findByGroupId(groupId, start, end);
	}
	
	public Entry addGuestbook(long userId, String name, 
		    ServiceContext serviceContext) throws SystemException, PortalException {
		long groupId = serviceContext.getScopeGroupId();

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		//validate(name);

		long guestbookId = counterLocalService.increment();

		Entry guestbook = entryPersistence.create(guestbookId);

		//guestbook.setUuid(serviceContext.getUuid());
		//guestbook.setUserId(userId);
		guestbook.setGroupId(groupId);
//		guestbook.setCompanyId(user.getCompanyId());
//		guestbook.setUserName(user.getFullName());
//		guestbook.setCreateDate(serviceContext.getCreateDate(now));
//		guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
		guestbook.setName(name);
		
		guestbook.setExpandoBridgeAttributes(serviceContext);

		entryPersistence.update(guestbook);

		return guestbook;
	}
}