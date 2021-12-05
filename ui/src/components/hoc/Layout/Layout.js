import React, {Component} from 'react';

import classes from './Layout.module.css'
import AppHeader from "../../header/AppHeader";

class Layout extends Component {
    render() {
        return (
            <React.Fragment>
                <div className={classes.AppLayout}>
                    <AppHeader/>
                    <main className={classes.Content}>
                        {this.props.children}
                    </main>
                </div>
            </React.Fragment>
        );
    }
}

export default Layout;
