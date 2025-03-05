import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import {RoleData} from "../models/roleData.ts";
import api from "../../api/api.ts";

const Role = () => {
    const navigate = useNavigate();

    const [roles, setRoles] = useState<RoleData[]>(() => {
        return [];
    });

    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        const fetchRoles = async () => {
            try {
                const response = await api.get('/api/v1/role');

                const rolesData = response.data.content;

                if (!Array.isArray(rolesData)) {
                    throw new Error('Data isn\'t array');
                }

                setRoles(rolesData);
                setError('');
            } catch (error) {
                console.error('Error:', error);
                setError((error as Error).message);
                setRoles([]);
            } finally {
                setLoading(false);
            }
        };
        fetchRoles();
    }, [navigate]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>
    }

    const handleDelete = async (id: number) => {
        try {
            await api.delete(`/api/v1/role/${id}`);
            setRoles(roles.filter(role => role.id !== id));
        } catch (error) {
            console.error('Error deleting role:', error);
        }
    };

    const handleEdit = (id: number) => {
        navigate(`/role/edit/${id}`);
    };

    const handleAdd = () => {
        navigate('/role/add');
    };

    const handleBackToNav = () => {
        navigate('/main');
    }

    return (
        <div>
            <h1>Role List</h1>
            <button onClick={handleBackToNav}>Back to navigation</button>
            <button onClick={handleAdd}>Add Role</button>
            <table>
                <thead>
                <tr>
                    <th>Role Name</th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
                {roles.map((role) => (
                    <tr key={role.id}>
                        <td>{role.roleName}</td>
                        <td>{role.description}</td>
                        <td>
                            <button onClick={() => handleEdit(role.id)}>Edit</button>
                            <button onClick={() => handleDelete(role.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default Role;