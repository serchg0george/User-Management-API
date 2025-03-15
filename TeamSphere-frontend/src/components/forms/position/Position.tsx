import {useNavigate} from "react-router-dom";
import {useState} from "react";
import api from "../../../api/api.ts";
import {PositionData} from "../../models/positionData.ts";
import useFetchPositions from "@/hooks/useFetchPositions.ts";
import AddPositionDialog from "@/components/forms/position/AddPositionDialog.tsx";

const Position = () => {
    const navigate = useNavigate();
    const {data: positions, loading, error, fetchPositions} = useFetchPositions();
    const [showAddDialog, setShowAddDialog] = useState<boolean>(false);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>
    }

    const handleDelete = async (id: number | undefined) => {
        try {
            await api.delete(`/api/v1/position/${id}`);
            await fetchPositions();
        } catch (error) {
            console.error('Error deleting position:', error);
        }
    };

    const handleEdit = (id: number | undefined) => {
        navigate(`/position/edit/${id}`);
    };

    const handleAdd = () => {
        setShowAddDialog(true);
    };

    const handleAddPosition = async (newPosition: PositionData) => {
        try {
            await api.post("/api/v1/position", newPosition);
            await fetchPositions();
            setShowAddDialog(false);
        } catch (error) {
            console.error('Error deleting position', error);
        }
    };

    const handleBackToNav = () => {
        navigate('/main');
    };

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

            <AddPositionDialog
                visible={showAddDialog}
                onHide={() => setShowAddDialog(false)}
                onAdd={handleAddPosition}
            />
        </div>
    );
};

export default Position;