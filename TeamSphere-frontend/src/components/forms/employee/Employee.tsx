import {useNavigate} from "react-router-dom";
import {useState} from "react";
import api from "../../../api/api.ts";
import useFetchEmployees from "@/hooks/useFetchEmployees.ts";
import {EmployeeData} from "@/components/models/employeeData.ts";
import AddEmployeeDialog from "@/components/forms/employee/AddEmployeeDialog.tsx";

const Employee = () => {
    const navigate = useNavigate();
    const {data: employees, loading, error, fetchEmployees} = useFetchEmployees();
    const [showAddDialog, setShowAddDialog] = useState<boolean>(false);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>
    }

    const handleDelete = async (id: number | undefined) => {
        try {
            await api.delete(`/api/v1/employee/${id}`);
            await fetchEmployees();
        } catch (error) {
            console.error('Error deleting employee:', error);
        }
    };

    const handleEdit = (id: number | undefined) => {
        navigate(`/employee/edit/${id}`);
    };

    const handleAdd = () => {
        setShowAddDialog(true);
    };

    const handleAddEmployee = async (newEmployee: EmployeeData) => {
        try {
            await api.post("/api/v1/employee", newEmployee);
            await fetchEmployees();
            setShowAddDialog(false);
        } catch (error) {
            console.error('Error deleting employee:', error);
        }
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
                    <th>Task ID</th>
                    <th>Project Ids</th>
                </tr>
                </thead>
                <tbody>
                {employees.map((employee) => (
                    <tr key={employee.id}>
                        <td>{employee.firstName}</td>
                        <td>{employee.lastName}</td>
                        <td>{employee.pin}</td>
                        <td>{employee.address}</td>
                        <td>{employee.email}</td>
                        <td>{employee.departmentId}</td>
                        <td>{employee.positionId}</td>
                        <td>{employee.taskIds}</td>
                        <td>{employee.projectIds}</td>
                        <td>
                            <button onClick={() => handleEdit(employee.id)}>Edit</button>
                            <button onClick={() => handleDelete(employee.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>

            <AddEmployeeDialog
                visible={showAddDialog}
                onHide={() => setShowAddDialog(false)}
                onAdd={handleAddEmployee}
            />
        </div>
    );
};

export default Employee;