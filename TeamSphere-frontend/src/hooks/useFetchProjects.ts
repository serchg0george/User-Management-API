import {useState, useEffect} from "react";
import api from "@/api/api";
import {ProjectData} from "@/components/models/projectData.ts";

const useFetchProjects = () => {
    const [data, setData] = useState<ProjectData[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>("");

    const fetchProjects = async () => {
        try {
            setLoading(true);
            const response = await api.get("/api/v1/project");
            const projectsData = response.data.content;

            if (!Array.isArray(projectsData)) {
                throw new Error("Data isn't array");
            }

            setData(projectsData);
            setError("");
        } catch (error) {
            console.error("Error fetching projects:", error);
            setError((error as Error).message);
            setData([]);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchProjects();
    }, []);

    return {data, loading, error, fetchProjects};
};

export default useFetchProjects;