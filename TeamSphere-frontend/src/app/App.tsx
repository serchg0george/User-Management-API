import React from 'react';
import '@/app/App.css';
import {BrowserRouter as Router, Routes, Route, Navigate} from 'react-router-dom';
import Company from '@/components/forms/Company.tsx';
import Auth from '@/components/forms/AuthForm.tsx';
import Navigation from '../components/forms/Navigation.tsx';
import Role from "@/components/forms/Role.tsx";
import Department from "@/components/forms/Department.tsx";
import Position from "@/components/forms/Position.tsx";
import Project from "@/components/forms/Project.tsx";
import Timesheet from "@/components/forms/Timesheet.tsx";
import Employee from "@/components/forms/Employee.tsx";

const App: React.FC = () => {
    return (
        <Router>
            <div className="App">
                <Routes>
                    <Route path="/" element={<Auth/>}/>
                    <Route path="/navigation" element={<Navigation/>}/>
                    <Route path="/auth" element={<Auth/>}/>
                    <Route path="/company" element={<Company/>}/>
                    <Route path="/role" element={<Role/>}/>
                    <Route path="/department" element={<Department/>}/>
                    <Route path="/position" element={<Position/>}/>
                    <Route path="/project" element={<Project/>}/>
                    <Route path="/timesheet" element={<Timesheet/>}/>
                    <Route path="/employee" element={<Employee/>}/>
                    <Route path="*" element={<Navigate to="/navigation"/>}/>
                </Routes>
            </div>
        </Router>
    );
};

export default App;