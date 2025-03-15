import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import api from "../../../api/api.ts";
import {PositionData} from "../../models/positionData.ts";

const Position = () => {
    const navigate = useNavigate();

    const [positions, setPositions] = useState<PositionData[]>(() => {
        return [];
    });

    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        const fetchPositions = async () => {
            try {
                const response = await api.get('/api/v1/position');

                const positionsData = response.data.content;

                if (!Array.isArray(positionsData)) {
                    throw new Error('Data isn\'t array');
                }

                setPositions(positionsData);
                setError('');
            } catch (error) {
                console.error('Error:', error);
                setError((error as Error).message);
                setPositions([]);
            } finally {
                setLoading(false);
            }
        };
        fetchPositions();
    }, [navigate]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>
    }

    const handleDelete = async (id: number) => {
        try {
            await api.delete(`/api/v1/position/${id}`);
            setPositions(positions.filter(position => position.id !== id));
        } catch (error) {
            console.error('Error deleting position:', error);
        }
    };

    const handleEdit = (id: number) => {
        navigate(`/position/edit/${id}`);
    };

    const handleAdd = () => {
        navigate('/position/add');
    };

    const handleBackToNav = () => {
        navigate('/main');
    }

    return (
        <div>
            <h1>Position List</h1>
            <button onClick={handleBackToNav}>Back to navigation</button>
            <button onClick={handleAdd}>Add Position</button>
            <table>
                <thead>
                <tr>
                    <th>Position Name</th>
                    <th>Years of Experience</th>
                </tr>
                </thead>
                <tbody>
                {positions.map((position) => (
                    <tr key={position.id}>
                        <td>{position.positionName}</td>
                        <td>{position.yearsOfExperience}</td>
                        <td>
                            <button onClick={() => handleEdit(position.id)}>Edit</button>
                            <button onClick={() => handleDelete(position.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default Position;