import React from 'react';
import {useNavigate} from 'react-router-dom';
import {Button} from 'primereact/button';
import logo from '@/assets/logo.png'
import './Navigation.css';

const Navigation: React.FC = () => {
    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
        navigate(path);
    };

    return (
        <div className="main-form">
            <img src={logo} alt="Logo" className="logo"/>
            <h1>Navigation</h1>
            <div className="nav-links">
                <Button label="Companies" className="nav-link" onClick={() => handleNavigation('/company')}/>
                <Button label="Departments" className="nav-link" onClick={() => handleNavigation('/department')}/>
                <Button label="Employees" className="nav-link" onClick={() => handleNavigation('/employee')}/>
                <Button label="Positions" className="nav-link" onClick={() => handleNavigation('/position')}/>
                <Button label="Projects" className="nav-link" onClick={() => handleNavigation('/project')}/>
                <Button label="Tasks" className="nav-link" onClick={() => handleNavigation('/task')}/>
            </div>
        </div>
    );
};

export default Navigation;
