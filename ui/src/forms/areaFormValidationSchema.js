import * as yup from 'yup';

export const nameValidationSchema = yup.object({
    name: yup
        .string()
        .required('Name is required'),
});

export const heightValidationSchema = yup.object().shape({
    from: yup
        .number('Enter lower bound in a valid format')
        .typeError('Lower bound must be a number')
        .when('to', {
            is: to => to !== undefined,
            then: yup.number().lessThan(yup.ref('to'), 'Lower bound must be less than upper bound')
        }),
    to: yup
        .number('Enter upper bound in a valid format')
        .typeError('Upper bound must be a number')
        .when('from', {
            is: from => from !== undefined,
            then: yup.number().moreThan(yup.ref('from'), 'Upper bound must be greater than upper bound')
        }),
}, ['to', 'from']);

export const circleAreaPropertiesSchema = yup.object({
    circleMiddle: yup.object({
        latitude: yup
            .number('Enter latitude in valid format')
            .typeError('Latitude must be a number')
            .required('Latitude is required'),
        longitude: yup
            .number('Enter longitude in valid format')
            .typeError('Longitude must be a number')
            .required('Longitude is required'),
    }),
    radius: yup
        .number()
        .typeError('Radius must be a number')
        .moreThan(-1, 'Radius value cannot be less than 0')
        .required('Radius is required')
});

export const polygonAreaPropertiesSchema = yup.object({
    vertices: yup
        .array()
        .of(
            yup.object({
                latitude: yup
                    .number('Enter latitude in valid format')
                    .typeError('Latitude must be a number')
                    .required('Latitude is required'),
                longitude: yup
                    .number('Enter longitude in valid format')
                    .typeError('Longitude must be a number')
                    .required('Longitude is required')
            })
        )
});
