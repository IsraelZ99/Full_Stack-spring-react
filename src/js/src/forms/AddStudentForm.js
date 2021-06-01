import React from 'react';
import { Formik } from 'formik';
import { Input, Button, Tag } from 'antd';
import { addNewStudent } from '../client';

const inputBottomMargin = { marginBottom: '15px' };
const tagStyle = { backgroundColor: '#f50', color: 'white', ...inputBottomMargin };

const AddStudentForm = (props) =>
(
    <div>
        <h1>Add a student</h1>
        <Formik
            initialValues={{ firstName: '', lastName: '', email: '', gender: '' }}
            validate={values => {
                const errors = {};

                if (!values.firstName) {
                    errors.firstName = 'First Name Required';
                }

                if (!values.lastName) {
                    errors.lastName = 'Last Name Required';
                }

                if (!values.email) {
                    errors.email = 'Email required';
                } else if (
                    !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
                ) {
                    errors.email = 'Invalid email address';
                }

                if (!values.gender) {
                    errors.gender = 'Gender Required';
                } else if (!['MALE', 'male', 'FEMALE', 'female'].includes(values.gender)) {
                    errors.gender = 'Gender must be (MALE, male, FEMALE, female)';
                }

                return errors;
            }}
            onSubmit={(student, { setSubmitting }) => {
                console.log(JSON.stringify(student, null, 2));
                addNewStudent(student)
                    .then(response => props.onSuccess())
                    .catch(err => props.onFailure(err))
                    .finally(() => setSubmitting(false));
            }} >
            {({
                values,
                errors,
                touched,
                handleChange,
                handleBlur,
                handleSubmit,
                isSubmitting,
                submitForm,
                isValid
                /* and other goodies */
            }) => (
                <form onSubmit={handleSubmit}>
                    <Input
                        style={inputBottomMargin}
                        name="firstName"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.firstName}
                        placeholder="First name. E.g Israel"
                        autoComplete="off"
                    />
                    {errors.firstName && touched.firstName &&
                        <Tag style={tagStyle}>{errors.firstName}</Tag>}
                    <Input
                        style={inputBottomMargin}
                        name="lastName"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.lastName}
                        placeholder="Last name. E.g Garcia"
                        autoComplete="off"
                    />
                    {errors.lastName && touched.lastName &&
                        <Tag style={tagStyle}>{errors.lastName}</Tag>}
                    <Input
                        style={inputBottomMargin}
                        name="email"
                        type="email"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.email}
                        placeholder="Email. E.g israel@hotmail.com"
                    />
                    {errors.email && touched.email &&
                        <Tag style={tagStyle}>{errors.email}</Tag>}
                    <Input
                        style={inputBottomMargin}
                        name="gender"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.gender}
                        placeholder="Gender. E.g Male or Female"
                        autoComplete="off"
                    />
                    {errors.gender && touched.gender &&
                        <Tag style={tagStyle}>{errors.gender}</Tag>}
                    <Button onClick={() => submitForm()} type="submit" disabled={isSubmitting | (touched && !isValid)}>
                        Submit </Button>
                </form>
            )}
        </Formik>
    </div>
);

export default AddStudentForm;