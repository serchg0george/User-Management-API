import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import api from "../../api/api.ts";
import {EmployeeData} from "../models/employeeData.ts";

const Employee = () => {
    const navigate = useNavigate();

    const [employee, setEmployee] = useState<EmployeeData[]>(() => {
        return [];
    });

    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        const fetchEmployees = async () => {
            try {
                const response = await api.get('/api/v1/employee');

                const employeesData = response.data.content;

                if (!Array.isArray(employeesData)) {
                    throw new Error('Data isn\'t array');
                }

                setEmployee(employeesData);
                setError('');
            } catch (error) {
                console.error('Error:', error);
                setError((error as Error).message);
                setEmployee([]);
            } finally {
                setLoading(false);
            }
        };
        fetchEmployees();
    }, [navigate]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>
    }

    const handleDelete = async (id: number) => {
        try {
            await api.delete(`/api/v1/employee/${id}`);
            setEmployee(employee.filter(employee => employee.id !== id));
        } catch (error) {
            console.error('Error deleting employee:', error);
        }
    };

    const handleEdit = (id: number) => {
        navigate(`/employee/edit/${id}`);
    };

    const handleAdd = () => {
        navigate('/employee/add');
    };

    const handleBackToNav = () => {
        navigate('/main');
    }

    return (
        <div>
            <h1>Employee List</h1>
            <button onClick={handleBackToNav}>Back to navigation</button>
            <button onClick={handleAdd}>Add Employee</button>
            <table>
                <thead>
                <tr>
                    <th>Employee Name</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Pin</th>
                    <th>Address</th>
                    <th>Department ID</th>
                    <th>Position ID</th>
                    <th>Timesheet ID</th>
                    <th>Project Ids</th>
                </tr>
                </thead>
                <tbody>
                {employee.map((department) => (
                    <tr key={department.id}>
                        <td>{department.firstName}</td>
                        <td>{department.lastName}</td>
                        <td>{department.pin}</td>
                        <td>{department.address}</td>
                        <td>{department.email}</td>
                        <td>{department.departmentId}</td>
                        <td>{department.positionId}</td>
                        <td>{department.timeSheetEmployeeId}</td>
                        <td>{department.projectIds}</td>
                        <td>
                            <button onClick={() => handleEdit(department.id)}>Edit</button>
                            <button onClick={() => handleDelete(department.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default Employee;