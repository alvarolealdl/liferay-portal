import React, {useContext, useState} from 'react';
import {useFormContext} from 'react-hook-form';

import {setSelectedProduct} from '../../../../../context/actions';
import {AppContext} from '../../../../../context/AppContext';

import {useStepWizard} from '../../../../../hooks/useStepWizard';
import {STORAGE_KEYS, Storage} from '../../../../../services/liferay/storage';
import {AVAILABLE_STEPS} from '../../../../../utils/constants';
import {CardFormActionsWithSave} from '../../../../fragments/Card/FormActionsWithSave';
import {BusinessTypeSearch} from './Search';
import {smoothScroll} from '../../../../../utils/scroll';

export const FormBasicBusinessType = ({form}) => {
	const {setSection} = useStepWizard();
	const [newSelectedProduct, setNewSelectedProduct] = useState('');
	const {dispatch, state} = useContext(AppContext);
	const {setValue} = useFormContext();

	const goToNextForm = () => {
		setSection(AVAILABLE_STEPS.BASICS_BUSINESS_INFORMATION);

		if (state.selectedProduct !== newSelectedProduct) {
			setValue('business', '');
			dispatch(setSelectedProduct(newSelectedProduct));
		}

		smoothScroll();
	};

	const goToPreviousPage = () => {
		window.location.href = '/web/raylife';
		if (Storage.itemExist(STORAGE_KEYS.BACK_TO_EDIT)) {
			Storage.removeItem(STORAGE_KEYS.BACK_TO_EDIT);
		}
	};

	return (
		<div className="card">
			<div className="card-content">
				<BusinessTypeSearch
					form={form}
					setNewSelectedProduct={setNewSelectedProduct}
				/>
			</div>
			<CardFormActionsWithSave
				isValid={!!form?.basics?.businessCategoryId}
				onNext={goToNextForm}
				onPrevious={goToPreviousPage}
			/>
		</div>
	);
};
