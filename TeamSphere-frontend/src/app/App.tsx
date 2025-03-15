import React from 'react';
import '@/app/App.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import {BrowserRouter as Router, Routes, Route, Navigate} from 'react-router-dom';
import Company from '@/components/forms/company/Company.tsx';
import Auth from '@/components/forms/AuthForm.tsx';
import Navigation from '../components/forms/navigation/Navigation.tsx';
import Department from "@/components/forms/department/Department.tsx";
import Position from "@/components/forms/position/Position.tsx";
import Project from "@/components/forms/project/Project.tsx";
import Task from "@/components/forms/task/Task.tsx";
import Employee from "@/components/forms/employee/Employee.tsx";

const App: React.FC = () => {
    return (
        <Router>
            <div className="App">
                <Routes>
                    <Route path="/" element={<Auth/>}/>
                    <Route path="/navigation" element={<Navigation/>}/>
                    <Route path="/auth" element={<Auth/>}/>
                    <Route path="/company" element={<Company/>}/>
                    <Route path="/department" element={<Department/>}/>
                    <Route path="/position" element={<Position/>}/>
                    <Route path="/project" element={<Project/>}/>
                    <Route path="/task" element={<Task/>}/>
                    <Route path="/employee" element={<Employee/>}/>
                    <Route path="*" element={<Navigate to="/navigation"/>}/>
                </Routes>
            </div>
        </Router>
    );
};

export default App;