import {useState, useEffect} from "react";
import api from "@/api/api";
import {DepartmentData} from "@/components/models/departmentData";


const useFetchDepartments = () => {
    const [data, setData] = useState<DepartmentData[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>("");

    const fetchDepartments = async () => {
        try {
            setLoading(true);
            const response = await api.get("/api/v1/department");
            const departmentsData = response.data.content;

            if (!Array.isArray(departmentsData)) {
                throw new Error("Data isn't array");
            }

            setData(departmentsData);
            setError("");
        } catch (error) {
            console.error("Error fetching departments:", error);
            setError((error as Error).message);
            setData([]);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchDepartments();
    }, []);

    return {data, loading, error, fetchDepartments};
};

export default useFetchDepartments;
