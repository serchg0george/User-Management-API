import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Company from '../src/components/forms/Company';
import Auth from '../src/components/forms/AuthForm';
import Navigation from './components/forms/Navigation.tsx';
import Role from "./components/forms/Role.tsx";
import Department from "./components/forms/Department.tsx";
import Position from "./components/forms/Position.tsx";

const App: React.FC = () => {
    return (
        <Router>
            <div className="App">
                <Routes>
                    <Route path="/" element={<Auth />} />
                    <Route path="/main" element={<Navigation />} />
                    <Route path="/auth" element={<Auth />} />
                    <Route path="/company" element={<Company />} />
                    <Route path="/role" element={<Role />} />
                    <Route path="/department" element={<Department />} />
                    <Route path="/position" element={<Position />} />
                    <Route path="*" element={<Navigate to="/" />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;