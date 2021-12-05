import React, { Component } from 'react';

import Backdrop from '../BackdropOld/Backdrop';

import classes from './Modal.module.css';

class Modal extends Component {

    shouldComponentUpdate(nextProps, nextState, nextContext) {
        return nextProps.show !== this.props.show || nextProps.children !== this.props.children;
    }

    render () {
        return (
            <React.Fragment>
                <Backdrop show={this.props.show} clicked={this.props.modalClosed} />
                <div
                    className={classes.Modal}
                    style={{
                        transform: this.props.show ? 'translateY(-50%)' : 'translateY(-150vh)',
                        opacity: this.props.show ? '1' : '0'
                    }}>
                    {this.props.children}
                </div>
            </React.Fragment>
        )
    }
}

export default Modal;
