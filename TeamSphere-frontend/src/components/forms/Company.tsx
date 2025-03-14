import {useState} from "react";
import {useNavigate} from 'react-router-dom';
import {CompanyData} from "@/components/models/companyData.ts";
import api from '../../api/api';
import AddCompanyDialog from "./AddCompanyDialog";
import useFetchCompanies from "@/hooks/useFetchCompanies.ts";

const Company = () => {
    const navigate = useNavigate();
    const {data: companies, loading, error, fetchCompanies} = useFetchCompanies();
    const [showAddDialog, setShowAddDialog] = useState<boolean>(false);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    const handleDelete = async (id?: number) => {
        try {
            await api.delete(`/api/v1/company/${id}`);
            await fetchCompanies();
        } catch (error) {
            console.error("Error deleting company:", error);
        }
    };

    const handleEdit = (id?: number) => {
        navigate(`/company/edit/${id}`);
    };

    const handleAdd = () => {
        setShowAddDialog(true);
    };

    const handleAddCompany = async (newCompany: CompanyData) => {
        try {
            await api.post("/api/v1/company", newCompany);
            await fetchCompanies();
            setShowAddDialog(false);
        } catch (error) {
            console.error("Error adding company:", error);
        }
    };

    const handleBackToNav = () => {
        navigate("/main");
    };

    return (
        <div>
            <h1>Company List</h1>
            <button onClick={handleBackToNav}>Back to navigation</button>
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

            <AddCompanyDialog
                visible={showAddDialog}
                onHide={() => setShowAddDialog(false)}
                onAdd={handleAddCompany}
            />
        </div>
    );
};

export default Company;