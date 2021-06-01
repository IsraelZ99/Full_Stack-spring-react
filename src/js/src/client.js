import fetch from 'unfetch';

const checkStatus = async response => {
    if (response.ok) {
        return response;
    } else {
        let error = new Error(response.statusText);
        error.response = response;
        const errorPayload = await response.json();
        return Promise.reject(errorPayload);
    }

};

export const getAllStudents = () => fetch('api/students').then(checkStatus);

export const addNewStudent = student => fetch('api/students', {
    headers: { 'Content-Type': 'application/json' },
    method: 'POST',
    body: JSON.stringify(student)
}).then(checkStatus);