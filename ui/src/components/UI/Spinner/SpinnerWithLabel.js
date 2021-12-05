import React from 'react';

import {makeStyles} from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import CircularProgress from '@material-ui/core/CircularProgress';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
    spinner: {
        color: '#1af7ff'
    },
    label: {
        color: '#ffffff'
    }
});

const SpinnerWithLabel = props => {
    const classes = useStyles();
    return (
        <Grid container justifyContent="center" alignItems="center">
            <Box position="relative" display="inline-flex">
                <CircularProgress classes={{
                    root: classes.spinner
                }}/>
                <Box top={0}
                     left={0}
                     bottom={0}
                     right={0}
                     display="flex"
                     alignItems="center"
                     justifyContent="center"
                     position="absolute">
                    <Typography classes={{
                        root: classes.label
                    }} variant="caption" component="span">{`${Math.round(props.value)}%`}</Typography>
                </Box>
            </Box>
        </Grid>
    );
}

export default SpinnerWithLabel;
