import {useState} from "react";
import api from '../../../api/api.ts';
import {useNavigate} from 'react-router-dom';
import {TaskData} from "../../models/taskData.ts";
import useFetchTasks from "@/hooks/useFetchTasks.ts";
import AddTaskDialog from "@/components/forms/task/AddTaskDialog.tsx";

const Task = () => {
    const navigate = useNavigate();
    const {data: tasks, loading, error, fetchTasks} = useFetchTasks();
    const [showAddDialog, setShowAddDialog] = useState<boolean>(false);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    const handleDelete = async (id: number | undefined) => {
        try {
            await api.delete(`/api/v1/task/${id}`);
            await fetchTasks();
        } catch (error) {
            console.error('Error deleting task:', error);
        }
    };

    const handleEdit = (id: number | undefined) => {
        navigate(`/task/edit/${id}`);
    };

    const handleAdd = () => {
        setShowAddDialog(true);
    };

    const handleAddTask = async (newTask: TaskData) => {
        try {
            await api.post("/api/v1/task", newTask);
            await fetchTasks();
            setShowAddDialog(false);
        } catch (error) {
            console.error('Error deleting task:', error);
        }
    };

    const handleBackToNav = () => {
        navigate('/main');
    };

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

            <AddTaskDialog
                visible={showAddDialog}
                onHide={() => setShowAddDialog(false)}
                onAdd={handleAddTask}
            />
        </div>
    );
};

export default Task;