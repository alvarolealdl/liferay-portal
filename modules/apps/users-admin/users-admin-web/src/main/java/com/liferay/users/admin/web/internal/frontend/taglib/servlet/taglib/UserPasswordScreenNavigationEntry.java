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

package com.liferay.users.admin.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.portal.kernel.model.User;
import com.liferay.users.admin.constants.UserScreenNavigationEntryConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pei-Jung Lan
 */
@Component(
	property = "screen.navigation.entry.order:Integer=60",
	service = ScreenNavigationEntry.class
)
public class UserPasswordScreenNavigationEntry
	extends BaseUserScreenNavigationEntry {

	@Override
	public String getActionCommandName() {
		return "/users_admin/update_password";
	}

	@Override
	public String getCategoryKey() {
		return UserScreenNavigationEntryConstants.CATEGORY_KEY_GENERAL;
	}

	@Override
	public String getEntryKey() {
		return UserScreenNavigationEntryConstants.ENTRY_KEY_PASSWORD;
	}

	@Override
	public String getJspPath() {
		return "/user/password.jsp";
	}

	@Override
	public boolean isVisible(User user, User selUser) {
		if ((selUser == null) || selUser.isServiceAccountUser()) {
			return false;
		}

		return true;
	}

}