import {useEffect, useState} from "react";
import api from '../../api/api.ts';
import {useNavigate} from 'react-router-dom';
import {TimesheetData} from "../models/timesheetData.ts";

const Timesheet = () => {
    const navigate = useNavigate();

    const [timesheets, setTimesheets] = useState<TimesheetData[]>(() => {
        return [];
    });
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        const fetchTimesheets = async () => {
            try {
                const response = await api.get('/api/v1/timesheet');

                const timesheetsData = response.data.content;

                if (!Array.isArray(timesheetsData)) {
                    throw new Error('Data isn\'t array');
                }

                setTimesheets(timesheetsData);
                setError('');
            } catch (error) {
                console.error('Error:', error);
                setError((error as Error).message);
                setTimesheets([]);
            } finally {
                setLoading(false);
            }
        };

        fetchTimesheets();
    }, [navigate]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    const handleDelete = async (id: number) => {
        try {
            await api.delete(`/api/v1/timesheet/${id}`);
            setTimesheets(timesheets.filter(timesheet => timesheet.id !== id));
        } catch (error) {
            console.error('Error deleting timesheet:', error);
        }
    };

    const handleEdit = (id: number) => {
        navigate(`/timesheet/edit/${id}`);
    };

    const handleAdd = () => {
        navigate('/timesheet/add');
    };

    const handleBackToNav = () => {
        navigate('/main');
    }

    return (
        <div>
            <h1>Timesheet List</h1>
            <button onClick={handleBackToNav}>Back to navigation</button>
            <button onClick={handleAdd}>Add Timesheet</button>
            <table>
                <thead>
                <tr>
                    <th>Time spent(minutes)</th>
                    <th>Task description</th>
                    <th>Role ID</th>
                </tr>
                </thead>
                <tbody>
                {timesheets.map((timesheet) => (
                    <tr key={timesheet.id}>
                        <td>{timesheet.timeSpentMinutes}</td>
                        <td>{timesheet.taskDescription}</td>
                        <td>{timesheet.roleId}</td>
                        <td>
                            <button onClick={() => handleEdit(timesheet.id)}>Edit</button>
                            <button onClick={() => handleDelete(timesheet.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default Timesheet;