import {useNavigate} from "react-router-dom";
import {useState} from "react";
import api from "../../../api/api.ts";
import {DepartmentData} from "../../models/departmentData.ts";
import AddDepartmentDialog from "./AddDepartmentDialog.tsx";
import useFetchDepartments from "@/hooks/useFetchDepartments.ts"

const Department = () => {
    const navigate = useNavigate();
    const {data: departments, loading, error, fetchDepartments} = useFetchDepartments();
    const [showAddDialog, setShowAddDialog] = useState<boolean>(false);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>
    }

    const handleDelete = async (id: number | undefined) => {
        try {
            await api.delete(`/api/v1/department/${id}`);
            await fetchDepartments();
        } catch (error) {
            console.error('Error deleting department:', error);
        }
    };

    const handleEdit = (id: number | undefined) => {
        navigate(`/department/edit/${id}`);
    };

    const handleAdd = () => {
        setShowAddDialog(true)
    };

    const handleAddDepartment = async (newDepartment: DepartmentData) => {
        try {
            await api.post("/api/v1/department", newDepartment);
            await fetchDepartments();
            setShowAddDialog(false);
        } catch (error) {
            console.error('Error deleting department:', error);
        }
    };

    const handleBackToNav = () => {
        navigate('/main');
    };

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
                {departments.map((department) => (
                    <tr key={department.id}>
                        <td>{department.departmentName}</td>
                        <td>{department.description}</td>
                        <td>
                            <button onClick={() => handleEdit(department.id)}>Edit</button>
                            <button onClick={() => handleDelete(department.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>

            <AddDepartmentDialog
                visible={showAddDialog}
                onHide={() => setShowAddDialog(false)}
                onAdd={handleAddDepartment}
            />
        </div>
    );
};

export default Department;