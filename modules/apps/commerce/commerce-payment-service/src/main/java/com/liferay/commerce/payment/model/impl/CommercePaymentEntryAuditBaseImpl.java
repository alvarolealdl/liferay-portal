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

package com.liferay.commerce.payment.model.impl;

import com.liferay.commerce.payment.model.CommercePaymentEntryAudit;
import com.liferay.commerce.payment.service.CommercePaymentEntryAuditLocalServiceUtil;

/**
 * The extended model base implementation for the CommercePaymentEntryAudit service. Represents a row in the &quot;CommercePaymentEntryAudit&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommercePaymentEntryAuditImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommercePaymentEntryAuditImpl
 * @see CommercePaymentEntryAudit
 * @generated
 */
public abstract class CommercePaymentEntryAuditBaseImpl
	extends CommercePaymentEntryAuditModelImpl
	implements CommercePaymentEntryAudit {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce payment entry audit model instance should use the <code>CommercePaymentEntryAudit</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommercePaymentEntryAuditLocalServiceUtil.
				addCommercePaymentEntryAudit(this);
		}
		else {
			CommercePaymentEntryAuditLocalServiceUtil.
				updateCommercePaymentEntryAudit(this);
		}
	}

}