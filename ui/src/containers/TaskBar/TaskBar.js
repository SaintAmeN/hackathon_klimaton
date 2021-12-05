import React from 'react';
import {connect} from 'react-redux';

import AddIcon from '@material-ui/icons/Add';
import RemoveIcon from '@material-ui/icons/Remove';
import CameraAltIcon from '@material-ui/icons/CameraAlt';
import SettingsInputAntennaIcon from '@material-ui/icons/SettingsInputAntenna';
import LayersIcon from '@material-ui/icons/Layers';
import TrackChangesIcon from '@material-ui/icons/TrackChanges';
import SettingsInputCompositeIcon from '@material-ui/icons/SettingsInputComposite';
import SecurityIcon from '@material-ui/icons/Security';
import Icon from '@mdi/react'
import {mdiEyeSettings} from '@mdi/js';

import TaskIcon from './TaskIcon/TaskIcon';
import {monitoringPanels} from '../../store/actions/monitoringPanels';

import * as actions from '../../store/actions';
import classes from './TaskBar.module.css'

const LEFT_SECTION = [
    {
        label: "Jammer",
        content: <SettingsInputAntennaIcon fontSize={"inherit"}/>,
        clickActionPanelID: monitoringPanels.jammerPanel
    },
    {
        label: "Camera",
        content: <CameraAltIcon fontSize={"inherit"}/>,
        clickActionPanelID: monitoringPanels.cameraPanel
    }
];

const MIDDLE_SECTION = [
    {
        label: "Change map layer",
        content: <LayersIcon fontSize={"inherit"}/>,
        clickActionPanelID: monitoringPanels.mapLayerPanel
    },
    {
        label: "Manage protected areas",
        content: <SecurityIcon fontSize={"inherit"}/>,
        clickActionPanelID: monitoringPanels.protectedAreaPanel
    },
    // {
    //     label: "Present track heatmap",
    //     content: <Icon fontSize={"inherit"} path={mdiGoKartTrack} size={0.8}/>,
    //     clickActionPanelID: monitoringPanels.trackHeatmapPanel
    // },
    // {
    //     label: "Configure visible object types",
    //     content: <Icon fontSize={"inherit"} path={mdiSelectAll} size={0.8}/>,
    //     clickActionPanelID: monitoringPanels.visibleClassesPanel
    // },
    {
        label: "Visible layers configuration",
        content: <Icon fontSize={"inherit"} path={mdiEyeSettings} size={0.8}/>,
        clickActionPanelID: monitoringPanels.visibleLayersSettingsPanelOpen
    }
];

const RIGHT_SECTION = [
    {
        label: "Tracks",
        content: <TrackChangesIcon fontSize={"inherit"}/>,
        clickActionPanelID: monitoringPanels.trackerPanel
    },
    {
        label: "Sensor status",
        content: <SettingsInputCompositeIcon fontSize={"inherit"}/>,
        clickActionPanelID: monitoringPanels.sensorPanel
    },
];

const mapSectionToJsx = (section, props) => {
    return section.map(element =>
        <TaskIcon
                key={element.label}
            label={element.label}
            content={element.content}
            clicked={() => props.onIconClicked(element.clickActionPanelID)}/>
    )
}

const TaskBar = props => (
    <div className={classes.TaskBar}>
        <div className={classes.Section}>
            {<ul className={classes.LeftSectionList}>{mapSectionToJsx(LEFT_SECTION, props)}</ul>}
        </div>
        <div className={classes.Section}>
            {<ul className={classes.MiddleSectionList}>
                <TaskIcon
                    key='Zoom in'
                    label='Zoom in'
                    content={<AddIcon fontSize={"inherit"}/>}
                    clicked={props.zoomIn}/>
                <TaskIcon
                    key='Zoom out'
                    label='Zoom out'
                    content={<RemoveIcon fontSize={"inherit"}/>}
                    clicked={props.zoomOut}/>
                {mapSectionToJsx(MIDDLE_SECTION, props)}
            </ul>}
        </div>
        <div className={classes.Section}>
            {<ul className={classes.RightSectionList}>{mapSectionToJsx(RIGHT_SECTION, props)}</ul>}
        </div>
    </div>
);

const mapDispatchToProps = dispatch => {
    return {
        onIconClicked: panelID => dispatch(actions.toggleMonitoringPanel(panelID)),
        zoomIn: () => dispatch(actions.zoomIn()),
        zoomOut: () => dispatch(actions.zoomOut())
    }
}

export default connect(null, mapDispatchToProps)(TaskBar);
