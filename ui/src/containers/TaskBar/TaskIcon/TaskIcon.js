import React from 'react';
import {Tooltip} from '@material-ui/core';

import classes from './TaskIcon.module.css'

const TaskIcon = props => {
    const {label, clicked, content} = props;
    return (
        <li>
            <Tooltip title={label}>
                {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
                <a className={classes.IconElement} onClick={clicked}>
                    <span className={classes.ButtonIcon}>
                        {content}
                    </span>
                </a>
            </Tooltip>
        </li>
    );
}

export default TaskIcon;
