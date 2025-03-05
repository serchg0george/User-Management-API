import {useEffect, useState} from "react";
import api from '../../api/api.ts';
import {useNavigate} from 'react-router-dom';
import {CompanyData} from "../models/companyData.ts";

const Company = () => {
    const navigate = useNavigate();

    const [companies, setCompanies] = useState<CompanyData[]>(() => {
        return [];
    });
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        const fetchCompanies = async () => {
            try {
                const response = await api.get('/api/v1/company');

                const companiesData = response.data.content;

                if (!Array.isArray(companiesData)) {
                    throw new Error('Data isn\'t array');
                }

                setCompanies(companiesData);
                setError('');
            } catch (error) {
                console.error('Error:', error);
                setError((error as Error).message);
                setCompanies([]);
            } finally {
                setLoading(false);
            }
        };

        fetchCompanies();
    }, [navigate]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    const handleDelete = async (id: number) => {
        try {
            await api.delete(`/api/v1/company/${id}`);
            setCompanies(companies.filter(company => company.id !== id));
        } catch (error) {
            console.error('Error deleting company:', error);
        }
    };

    // Handle edit company (redirect to edit page)
    const handleEdit = (id: number) => {
        navigate(`/company/edit/${id}`); // Redirect to an edit page (you can create this later)
    };

    // Handle add company (redirect to add page)
    const handleAdd = () => {
        navigate('/company/add'); // Redirect to an add page (you can create this later)
    };

    return (
        <div>
            <h1>Company List</h1>
            <button onClick={handleAdd}>Add Company</button>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Industry</th>
                    <th>Address</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {companies.map((company) => (
                    <tr key={company.id}>
                        <td>{company.name}</td>
                        <td>{company.industry}</td>
                        <td>{company.address}</td>
                        <td>{company.email}</td>
                        <td>
                            <button onClick={() => handleEdit(company.id)}>Edit</button>
                            <button onClick={() => handleDelete(company.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default Company;