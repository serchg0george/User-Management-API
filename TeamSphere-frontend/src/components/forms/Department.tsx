import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import api from "../../api/api.ts";
import {DepartmentData} from "../models/departmentData.ts";

const Department = () => {
    const navigate = useNavigate();

    const [department, setDepartment] = useState<DepartmentData[]>(() => {
        return [];
    });

    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        const fetchDepartments = async () => {
            try {
                const response = await api.get('/api/v1/department');

                const departmentsData = response.data.content;

                if (!Array.isArray(departmentsData)) {
                    throw new Error('Data isn\'t array');
                }

                setDepartment(departmentsData);
                setError('');
            } catch (error) {
                console.error('Error:', error);
                setError((error as Error).message);
                setDepartment([]);
            } finally {
                setLoading(false);
            }
        };
        fetchDepartments();
    }, [navigate]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>
    }

    const handleDelete = async (id: number) => {
        try {
            await api.delete(`/api/v1/department/${id}`);
            setDepartment(department.filter(department => department.id !== id));
        } catch (error) {
            console.error('Error deleting department:', error);
        }
    };

    const handleEdit = (id: number) => {
        navigate(`/department/edit/${id}`);
    };

    const handleAdd = () => {
        navigate('/department/add');
    };

    const handleBackToNav = () => {
        navigate('/main');
    }

    return (
        <div>
            <h1>Department List</h1>
            <button onClick={handleBackToNav}>Back to navigation</button>
            <button onClick={handleAdd}>Add Department</button>
            <table>
                <thead>
                <tr>
                    <th>Department Name</th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
                {department.map((department) => (
                    <tr key={department.id}>
                        <td>{department.groupName}</td>
                        <td>{department.description}</td>
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

export default Department;