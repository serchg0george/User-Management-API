import {useState, useEffect} from "react";
import api from "@/api/api";
import {EmployeeData} from "@/components/models/employeeData.ts";

const useFetchEmployees = () => {
    const [data, setData] = useState<EmployeeData[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>("");

    const fetchEmployees = async () => {
        try {
            setLoading(true);
            const response = await api.get("/api/v1/employee");
            const employeesData = response.data.content;

            if (!Array.isArray(employeesData)) {
                throw new Error("Data isn't array");
            }

            setData(employeesData);
            setError("");
        } catch (error) {
            console.error("Error fetching employees:", error);
            setError((error as Error).message);
            setData([]);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchEmployees();
    }, []);

    return {data, loading, error, fetchEmployees};
};

export default useFetchEmployees;