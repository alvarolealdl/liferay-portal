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

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function;

import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Paulo Albuquerque
 */
public class OldValueFunctionTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testApply1() {
		DefaultDDMExpressionParameterAccessor ddmExpressionParameterAccessor =
			new DefaultDDMExpressionParameterAccessor();

		ddmExpressionParameterAccessor.setGetObjectFieldsOldValuesSupplier(
			() -> HashMapBuilder.<String, Object>put(
				"aaa", "AAA"
			).put(
				"bbb", "BBB"
			).build());

		_oldValueFunction.setDDMExpressionParameterAccessor(
			ddmExpressionParameterAccessor);

		Assert.assertEquals("AAA", _oldValueFunction.apply("aaa"));
		Assert.assertEquals("BBB", _oldValueFunction.apply("bbb"));
		Assert.assertNull(
			_oldValueFunction.apply(RandomTestUtil.randomString()));
	}

	@Test
	public void testApply2() {
		Assert.assertNull(
			_oldValueFunction.apply(RandomTestUtil.randomString()));
	}

	private final OldValueFunction _oldValueFunction = new OldValueFunction();

}