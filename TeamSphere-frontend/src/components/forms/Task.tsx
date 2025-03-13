import {useEffect, useState} from "react";
import api from '../../api/api.ts';
import {useNavigate} from 'react-router-dom';
import {TaskData} from "../models/taskData.ts";

const Task = () => {
    const navigate = useNavigate();

    const [tasks, setTasks] = useState<TaskData[]>(() => {
        return [];
    });
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        const fetchTasks = async () => {
            try {
                const response = await api.get('/api/v1/task');

                const tasksData = response.data.content;

                if (!Array.isArray(tasksData)) {
                    throw new Error('Data isn\'t array');
                }

                setTasks(tasksData);
                setError('');
            } catch (error) {
                console.error('Error:', error);
                setError((error as Error).message);
                setTasks([]);
            } finally {
                setLoading(false);
            }
        };

        fetchTasks();
    }, [navigate]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    const handleDelete = async (id: number) => {
        try {
            await api.delete(`/api/v1/task/${id}`);
            setTasks(tasks.filter(task => task.id !== id));
        } catch (error) {
            console.error('Error deleting task:', error);
        }
    };

    const handleEdit = (id: number) => {
        navigate(`/task/edit/${id}`);
    };

    const handleAdd = () => {
        navigate('/task/add');
    };

    const handleBackToNav = () => {
        navigate('/main');
    }

    return (
        <div>
            <h1>Task List</h1>
            <button onClick={handleBackToNav}>Back to navigation</button>
            <button onClick={handleAdd}>Add Task</button>
            <table>
                <thead>
                <tr>
                    <th>Time spent(minutes)</th>
                    <th>Task description</th>
                    <th>Role</th>
                </tr>
                </thead>
                <tbody>
                {tasks.map((task) => (
                    <tr key={task.id}>
                        <td>{task.timeSpentMinutes}</td>
                        <td>{task.taskDescription}</td>
                        <td>{task.role}</td>
                        <td>
                            <button onClick={() => handleEdit(task.id)}>Edit</button>
                            <button onClick={() => handleDelete(task.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default Task;