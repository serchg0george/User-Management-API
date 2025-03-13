export interface EmployeeData {
    id: number;
    firstName: string;
    lastName: string;
    pin: string;
    address: string;
    email: string;
    departmentId: number;
    positionId: number;
    taskIds: [number];
    projectIds: [number];
}