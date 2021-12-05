import * as yup from 'yup';

export const identificationValidationSchema = yup.object({
    name: yup
        .string()
        .required('Name is required'),
    ip: yup
        .string()
        .matches(/(^(\d{1,3}\.){3}(\d{1,3})$)/, 'Enter valid IP address')
        .required('IP address is required')
});

export const positionValidationSchema = yup.object({
    latitude: yup
        .number('Enter latitude in valid format')
        .typeError('Latitude must be a number')
        .required('Latitude is required'),
    longitude: yup
        .number('Enter longitude in valid format')
        .typeError('Longitude must be a number')
        .required('Longitude is required'),
    height: yup
        .number('Enter height in a valid format')
        .typeError('Height must be a number')
        .moreThan(-1, 'Height must be a positive value')
        .required('Height is required')
});

export const areaValidationSchema = yup.object({
    azimuth: yup
        .number('It is not number')
        .typeError('Azimuth must be a number')
        .moreThan(-1, 'Azimuth values range is [0, 360]')
        .max(360, 'Azimuth values range is [0, 360]')
        .required('Azimuth is required'),
    radius: yup
        .number()
        .typeError('Radius must be a number')
        .moreThan(-1, 'Radius value cannot be less than 0')
        .required('Radius is required'),
    visionAngle: yup
        .number('Vision angle')
        .typeError('Vision angle must be a number')
        .positive('Vision angle values range is [1, 360]')
        .max(360, 'Vision angle values range is [1, 360]')
        .required('Vision angle is required')
});

export const focalSettingsValidationSchema = yup.object({
    focalMin: yup
        .number('It is not number')
        .default(4.3)
        .typeError('Focal must be a number')
        .moreThan(0, 'Focal values cannot be zero or negative')
        .max(1000, 'Given value is extremely high')
        .required('Focal is required'),
    focalMax: yup
        .number('It is not number')
        .default(129)
        .typeError('Focal must be a number')
        .moreThan(0,'Focal max cannot be lower than 0')
        .moreThan(yup.ref("focalMin"),'Focal max cannot be lower than min')
        .max(1000, 'Given value is extremely high')
        .required('Focal is required'),
    sensorWidth: yup
        .number('It is not number')
        .default(5.558)
        .typeError('Sensor width must be a number')
        .moreThan(0, 'Sensor width cannot be zero or negative')
        .max(50, 'Given value is unbelievably high')
        .required('Sensor width is required'),
    sensorHeight: yup
        .number('It is not number')
        .default(3.055)
        .typeError('Sensor height must be a number')
        .moreThan(0, 'Sensor height cannot be zero or negative')
        .max(50, 'Given value is unbelievably high')
        .required('Sensor height is required'),
    zoomScale: yup
        .number('It is not number')
        .default(10000)
        .typeError('Zoom scale must be a number')
        .moreThan(0, 'Zoom scale cannot be zero or negative')
        .required('Zoom scale is required'),
});

export const accessSettingsValidationSchema = yup.object({
    cameraUsername: yup
        .string()
        .typeError('Username is text value')
        .required('Username is required'),
    cameraPassword: yup
        .string()
        .required('Password is required'),
    streamHost: yup
        .string()
        .required('Camera stream host is required'),
    streamPort: yup
        .number('It is not a number')
        .moreThan(0, 'Port number has to be positive value')
        .max(65534, 'Max port number is 65534')
        .required('Camera stream port is required'),
    streamAddress: yup
        .string()
        .required('Stream address is required'),
});

export const steeringSettingsValidationSchema = yup.object({
    ptzMechanismType: yup
        .string()
        .oneOf(['NONE', 'CONTROLLER_REST_AXIS', 'CONTROLLER_PT'])
        .required('Should automatic radar control be enabled?'),
    enabledRadarCue: yup
        .boolean()
        .default(false)
        .typeError('Should be boolean'),
        // .required('Should automatic radar control be enabled?'),
    enabledManualCue: yup
        .boolean()
        .default(false)
        .typeError('Should be boolean'),
        // .required('Should manual control be enabled?'),
    enabledVisionDetectionCue: yup
        .boolean()
        .default(false)
        .typeError('Should be boolean'),
        // .required('Should automatic vision detection control be enabled?'),
    enableAutoZoom: yup
        .boolean()
        .default(false)
        .typeError('Should be boolean'),
        // .required('Should automatic zoom be enabled?'),
});
