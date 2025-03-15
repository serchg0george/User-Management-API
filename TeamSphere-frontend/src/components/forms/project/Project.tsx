import {useEffect, useState} from "react";
import api from '../../../api/api.ts';
import {useNavigate} from 'react-router-dom';
import {ProjectData} from "../../models/projectData.ts";

const Project = () => {
    const navigate = useNavigate();

    const [projects, setProjects] = useState<ProjectData[]>(() => {
        return [];
    });
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        const fetchProjects = async () => {
            try {
                const response = await api.get('/api/v1/project');

                const projectsData = response.data.content;

                if (!Array.isArray(projectsData)) {
                    throw new Error('Data isn\'t array');
                }

                setProjects(projectsData);
                setError('');
            } catch (error) {
                console.error('Error:', error);
                setError((error as Error).message);
                setProjects([]);
            } finally {
                setLoading(false);
            }
        };

        fetchProjects();
    }, [navigate]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    const handleDelete = async (id: number) => {
        try {
            await api.delete(`/api/v1/project/${id}`);
            setProjects(projects.filter(project => project.id !== id));
        } catch (error) {
            console.error('Error deleting project:', error);
        }
    };

    const handleEdit = (id: number) => {
        navigate(`/project/edit/${id}`);
    };

    const handleAdd = () => {
        navigate('/project/add');
    };

    const handleBackToNav = () => {
        navigate('/main');
    }

    return (
        <div>
            <h1>Project List</h1>
            <button onClick={handleBackToNav}>Back to navigation</button>
            <button onClick={handleAdd}>Add Project</button>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Start Date</th>
                    <th>Finish Date</th>
                    <th>Status</th>
                    <th>Company ID</th>
                </tr>
                </thead>
                <tbody>
                {projects.map((project) => (
                    <tr key={project.id}>
                        <td>{project.name}</td>
                        <td>{project.description}</td>
                        <td>{project.startDate}</td>
                        <td>{project.finishDate}</td>
                        <td>{project.status}</td>
                        <td>{project.companyId}</td>
                        <td>
                            <button onClick={() => handleEdit(project.id)}>Edit</button>
                            <button onClick={() => handleDelete(project.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default Project;