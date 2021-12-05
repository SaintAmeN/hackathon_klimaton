import React from 'react';
import classes from './TableComponent.module.css';
import {Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@material-ui/core";
import Paper from '@material-ui/core/Paper';
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles(theme => ({
    tableCell: {
        [`&.MuiTableCell-head`]: {
            backgroundColor: theme.palette.common.black,
            color: theme.palette.common.white,
        },
        [`&.MuiTableCell-body`]: {
            fontSize: 14,
        },
    },
    tableRow: {
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.action.hover,
        },
        // hide last border
        '&:last-child td, &:last-child th': {
            border: 0,
        },
    },
}));

const TableComponent = ({dataRows, dataHeaders}) => {
    const styledClasses = useStyles();
    const headerKeys = Object.keys(dataHeaders);

    return (
        <div className={classes.TableComponentContainer}>
            <TableContainer component={Paper}>
                <Table size="small">
                    <TableHead>
                        <TableRow>
                            {headerKeys.map((headerRow) => (<TableCell>{dataHeaders[headerRow]}</TableCell>))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {dataRows.map((dataRow) => (
                            <TableRow
                                key={dataRow.id}
                                classes={styledClasses.tableRow}
                                sx={{'&:last.-child td, &:last-child th': {border: 0}}}
                            >
                                {dataHeaders.map((headerRow) => (<TableCell classes={styledClasses.tableCell}>{dataRow.name}</TableCell>))}
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    )
}

export default TableComponent;