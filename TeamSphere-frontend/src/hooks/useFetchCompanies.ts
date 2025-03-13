import { useState, useEffect } from "react";
import api from "@/api/api";
import { CompanyData } from "@/components/models/companyData";


const useFetchCompanies = () => {
    const [data, setData] = useState<CompanyData[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>("");

    const fetchCompanies = async () => {
        try {
            setLoading(true);
            const response = await api.get("/api/v1/company");
            const companiesData = response.data.content;

            if (!Array.isArray(companiesData)) {
                throw new Error("Data isn't array");
            }

            setData(companiesData);
            setError("");
        } catch (error) {
            console.error("Error fetching companies:", error);
            setError((error as Error).message);
            setData([]);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchCompanies();
    }, []);

    return { data, loading, error, fetchCompanies };
};

export default useFetchCompanies;
