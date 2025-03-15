import {useState, useEffect} from "react";
import api from "@/api/api";
import {TaskData} from "@/components/models/taskData.ts";

const useFetchTasks = () => {
    const [data, setData] = useState<TaskData[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>("");

    const fetchTasks = async () => {
        try {
            setLoading(true);
            const response = await api.get("/api/v1/task");
            const tasksData = response.data.content;

            if (!Array.isArray(tasksData)) {
                throw new Error("Data isn't array");
            }

            setData(tasksData);
            setError("");
        } catch (error) {
            console.error("Error fetching tasks:", error);
            setError((error as Error).message);
            setData([]);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchTasks();
    }, []);

    return {data, loading, error, fetchTasks};
};

export default useFetchTasks;