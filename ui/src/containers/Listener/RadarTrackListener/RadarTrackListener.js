import React from 'react';
import SockJsClient from 'react-stomp';
import {connect} from 'react-redux';

import trackEventBus from '../../../event/EventBus';
import {BASE_URL} from '../../../assets/constants/constants';

const RadarTrackListener = props => {
    let visibleClasses = props.visibleClassesSettings.loadVisibleTargets()

    const tracksArrived = tracks => {
        trackEventBus.dispatch("radar-track", {tracks: tracks.filter(d => visibleClasses.includes(d.probability))});
    }

    return (
        <React.Fragment>
            <SockJsClient url={BASE_URL + '/radar/ws/'}
                          topics={['radar-track-bulk']}
                          onMessage={tracksArrived}
                          debug={false}/>
        </React.Fragment>
    );
};

const mapStateToProps = state => {
    return {
        visibleClassesSettings: state.userPlatformSettings.platformSettings
    }
}

export default connect(mapStateToProps)(RadarTrackListener);
