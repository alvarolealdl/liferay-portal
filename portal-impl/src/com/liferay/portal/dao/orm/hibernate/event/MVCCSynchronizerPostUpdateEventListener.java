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

package com.liferay.portal.dao.orm.hibernate.event;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.io.Serializable;

import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

/**
 * @author Shuyang Zhou
 */
public class MVCCSynchronizerPostUpdateEventListener
	implements PostUpdateEventListener {

	public static final MVCCSynchronizerPostUpdateEventListener INSTANCE =
		new MVCCSynchronizerPostUpdateEventListener();

	@Override
	public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
		Object entity = postUpdateEvent.getEntity();

		if (entity instanceof MVCCModel) {
			if (entity instanceof CTModel) {
				CTModel<?> ctModel = (CTModel<?>)entity;

				if (ctModel.getCtCollectionId() != 0) {
					return;
				}
			}

			MVCCModel mvccModel = (MVCCModel)entity;

			long mvccVersion = mvccModel.getMvccVersion();

			Class<?> modelClass = entity.getClass();

			BaseModel<?> baseModel = (BaseModel<?>)entity;

			Serializable primaryKeyObj = baseModel.getPrimaryKeyObj();

			Serializable localCacheResult = EntityCacheUtil.getLocalCacheResult(
				modelClass, primaryKeyObj);

			if (localCacheResult instanceof MVCCModel) {
				MVCCModel localCacheMVCCModel = (MVCCModel)localCacheResult;

				localCacheMVCCModel.setMvccVersion(mvccVersion);
			}

			PortalCache<Serializable, Serializable> portalCache =
				EntityCacheUtil.getPortalCache(modelClass);

			Serializable entityCacheResult = portalCache.get(primaryKeyObj);

			if (entityCacheResult instanceof MVCCModel) {
				MVCCModel entityCacheMVCCModel = (MVCCModel)entityCacheResult;

				entityCacheMVCCModel.setMvccVersion(mvccVersion);
			}
		}
	}

	/** @deprecated */
	@Deprecated
	@Override
	public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
		throw new UnsupportedOperationException();
	}

}