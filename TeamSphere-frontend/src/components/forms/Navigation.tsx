import React from 'react';
import {useNavigate} from 'react-router-dom';
import './Navigation.css';

const Navigation: React.FC = () => {
    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
        navigate(path);
    };

    return (
        <div className="main-form">
            <h1>Navigation</h1>
            <div className="nav-links">
                <button
                    className="nav-link"
                    onClick={() => handleNavigation('/company')}
                >
                    Companies
                </button>
                <button
                    className="nav-link"
                    onClick={() => handleNavigation('/department')}
                >
                    Departments
                </button>
                <button
                    className="nav-link"
                    onClick={() => handleNavigation('/employee')}
                >
                    Employees
                </button>
                <button
                    className="nav-link"
                    onClick={() => handleNavigation('/position')}
                >
                    Positions
                </button>
                <button
                    className="nav-link"
                    onClick={() => handleNavigation('/project')}
                >
                    Projects
                </button>
                <button
                    className="nav-link"
                    onClick={() => handleNavigation('/task')}
                >
                    Tasks
                </button>
            </div>
        </div>
    );
};

export default Navigation;