import {useState, useEffect} from "react";
import api from "@/api/api";
import {PositionData} from "@/components/models/positionData";

const useFetchPositions = () => {
    const [data, setData] = useState<PositionData[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>("");

    const fetchPositions = async () => {
        try {
            setLoading(true);
            const response = await api.get("/api/v1/position");
            const positionsData = response.data.content;

            if (!Array.isArray(positionsData)) {
                throw new Error("Data isn't array");
            }

            setData(positionsData);
            setError("");
        } catch (error) {
            console.error("Error fetching positions:", error);
            setError((error as Error).message);
            setData([]);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchPositions();
    }, []);

    return {data, loading, error, fetchPositions};
};

export default useFetchPositions;
