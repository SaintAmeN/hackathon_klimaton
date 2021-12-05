import * as yup from 'yup';

export const identityValidationSchema = yup.object({
    name: yup
        .string()
        .required('Name is required'),
    password: yup
        .string()
        .required('Password is required'),
    confirmPassword: yup
        .string()
        .oneOf([yup.ref('password'), null], 'Passwords must match')

});

export const editIdentityValidationSchema = yup.object({
    name: yup
        .string()
        .required('Name is required'),
    currentPassword: yup
        .string()
        .required('Old password is required'),
    password: yup
        .string()
        .required('Password is required'),
    confirmPassword: yup
        .string()
        .oneOf([yup.ref('password'), null], 'Passwords must match')
});

export const rolesValidationSchema = yup.object({
    ADMIN: yup
        .boolean(),
    USER: yup
        .boolean(),
    WATCHMAN: yup
        .boolean()
}).test('rolesTest',
    null,
    values => {
        if (values.ADMIN || values.USER || values.WATCHMAN) {
            return true;
        } else {
            return new yup.ValidationError(
                'Check at least one checkbox',
                null,
                'roles'
            );
        }
    }
);
