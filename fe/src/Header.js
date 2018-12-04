import logo from './so-icon.svg';
import React from 'react';

function Header() {
    return (<div className="page">
        <header className="App">
            <img src={logo} className="App-logo" alt="logo" />
            <h1 className="App-title"> StackOverflow Search </h1>
        </header>
    </div>);
}

export default Header;