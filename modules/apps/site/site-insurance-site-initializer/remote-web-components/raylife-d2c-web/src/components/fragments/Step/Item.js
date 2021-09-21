import React from 'react';

import {ProgressRing} from '../ProgressRing';
import ClayIcon from '@clayui/icon';

import classNames from 'classnames';

export const StepItem = ({children, percentage = 0, selected = false}) => {
	const completed = percentage === 100;
	return (
		<div
			className={classNames('step-item', {
				completed,
				selected,
			})}
		>
			<i>
				{selected && (
					<ProgressRing
						className="progress-ring"
						diameter={32}
						percent={percentage}
						strokeWidth={3}
					/>
				)}
			</i>
			{completed && (
				<div>
					<ClayIcon symbol="check" />
				</div>
			)}
			{children}
		</div>
	);
};
